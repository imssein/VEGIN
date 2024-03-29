package com.project.vegan.domain.review.controller;

import com.project.vegan.domain.member.entity.Member;
import com.project.vegan.domain.review.request.ReviewRequest;
import com.project.vegan.domain.review.request.ReviewSaveRequest;
import com.project.vegan.domain.review.response.ReviewDto;
import com.project.vegan.domain.review.service.ReviewService;
import com.project.vegan.global.security.annotation.LoginMember;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("v1/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @ApiOperation("리뷰 전체 조회")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewDto> getReviews(){
        return reviewService.getReviews();
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @ApiOperation("리뷰 작성")
    @PostMapping(value = "/{storeId}/review", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "사용자 인증을 위한 accessToken", paramType = "header", required = true)
    })
    public ReviewDto review(@PathVariable("storeId") Long storeId,
                            @Validated @RequestPart("requestData") ReviewRequest requestData,
                            @RequestPart(value = "requestFiles", required = false) List<MultipartFile> requestFiles,
                            @LoginMember Member member) {
        return reviewService.save(new ReviewSaveRequest(requestData.getStarRating(),
                requestData.getContent(), requestFiles), member, storeId);
    }

    @ApiOperation("식당에 따른 리뷰 조회")
    @GetMapping("/{storeId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewDto> getReviewsByStore(@PathVariable("storeId") Long storeId){
        return reviewService.getReviewsByStore(storeId);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @ApiOperation("리뷰 수정")
    @PostMapping(value = "/{storeId}/{reviewId}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "사용자 인증을 위한 accessToken", paramType = "header", required = true)
    })
    public ReviewDto updateReview(@PathVariable("storeId") Long storeId,
                                  @PathVariable("reviewId") Long reviewId,
                                  @Validated @RequestPart("requestData") ReviewRequest requestData,
                                  @RequestPart(value = "requestFiles", required = false) List<MultipartFile> requestFiles,
                                  @LoginMember Member member){
        return reviewService.update(new ReviewSaveRequest(requestData.getStarRating(),
                requestData.getContent(), requestFiles), member, storeId, reviewId);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @ApiOperation("리뷰 삭제")
    @DeleteMapping("/{storeId}/{reviewId}")
    @ResponseStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "사용자 인증을 위한 accessToken", paramType = "header", required = true)
    })
    public void delete(@PathVariable("storeId") Long storeId,
                       @PathVariable("reviewId") Long reviewId,
                       @LoginMember Member member) {
        reviewService.delete(storeId, reviewId, member);
    }

}
