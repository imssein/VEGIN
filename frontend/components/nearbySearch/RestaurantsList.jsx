import React, {useEffect, useState} from 'react';
import {
    AiOutlineEdit,
    AiFillStar,
    AiFillHeart,
    AiOutlineTag,
  } from "react-icons/ai";
  import Image from "next/image";
  import Link from "next/link";
  import FilterLogo from "../searchfilter/FilterLogo";

function RestaurantsList({content}) {
   
    return (
      <div className="px-4 mb-36">
      {/* 자치구명 | 필터 로고*/}
      <div className="flex">
        <FilterLogo />
      </div>
      <div className="mt-5 text-sm">검색된 식당 {content.length}개</div>

      {/* 식당리스트 */}
      <div className="my-4">
        {content && content.map((item) => (
          <Link href={`/restaurants/${item.id}`} key={item.id}>
            <div className="flex mb-7">
              <div className="border my-auto">
                <Image src="/images/foodex.jpeg" width={180} height={150} alt="식당 사진" />
              </div>
              <div className="pt-2 pb-5 ml-5 sm:ml-10">
                <p className="text-lime-700 font-bold mb-2">
                  {item.vegetarianTypes}
                </p>
                <p className="text-lg mb-1">{item.name}</p>
                <p className="text-xs text-gray-600 mb-1">{item.district}</p>
                <p className="mb-2">{item.category}</p>
                <div className="flex">
                  <div className="flex mr-2">
                    <AiFillStar size="20" color="orange" />
                    <p className="pl-1 text-sm">{item.starRating}</p>
                  </div>
                  <div className="flex mr-2">
                    <AiOutlineEdit size="20" />
                    <p className="pl-1 text-sm">{item.reviewCount}</p>
                  </div>
                  <div className="flex mr-2">
                    <AiFillHeart size="20" color="pink" />
                    <p className="pl-1 text-sm">{item.reviewCount}</p>
                  </div>
                </div>
              </div>
            </div>
          </Link>
        ))}
      </div>
    </div>
    );
}

export default RestaurantsList;