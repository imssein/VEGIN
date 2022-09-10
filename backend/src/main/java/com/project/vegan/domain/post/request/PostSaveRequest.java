package com.project.vegan.domain.post.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PostSaveRequest {
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    private List<String> hashTags = new ArrayList<>();
    private List<MultipartFile> multipartFiles = new ArrayList<>();

    @Builder
    public PostSaveRequest(String title, String content, List<String> hashTags, List<MultipartFile> multipartFiles) {
        this.title = title;
        this.content = content;
        this.hashTags = hashTags;
        this.multipartFiles = multipartFiles;
    }
}
