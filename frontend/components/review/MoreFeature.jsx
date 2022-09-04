import Link from "next/link";
import { useRouter } from "next/router";
import React, { useEffect, useState } from "react";
import { BsPencil, BsTrash } from "react-icons/bs";
import PostService from "../../services/post.service";
import ReviewService from "../../services/review.service";
import UserService from "../../services/user.service";

function MoreFeature({ storeId, id, nickname }) {
  const [ user, setUser ] = useState('');
  const router = useRouter();


  // useEffect(() => {   
  //       UserService.getMemberDetail().then(
  //         (response) => {
  //           setUser(response.data);
  //         },
  //         (error) => {
  //           console.log(error);
  //         }
  //       );
  // }, [setUser]);

  // console.log(user)
  
  function handleDelete() {

    ReviewService.deleteReview(storeId, id).then(
      () => {
        alert("게시글 삭제");
        router.push(`/restaurants/${storeId}`);
      }, 
      (error) => {
        console.log(error)
      }
    );
  }
  return (
    <div className="border float-right  ">
      <div className="flex px-8 py-4 border-b">
        <Link href={`/editReview/${storeId}/${id}`}>
        <button className="flex">
          <p className="mr-4 my-auto">수정하기</p> <BsPencil size={20}/>
        </button>
        </Link> 
      </div>
      <div className="flex px-8 py-4 border-b text-red-500">
        <button className="flex" onClick={handleDelete}>
        <p className="mr-4 my-auto">삭제하기</p> <BsTrash size={20}/>
        </button>
      </div>
    </div>
  );
}

export default MoreFeature;
