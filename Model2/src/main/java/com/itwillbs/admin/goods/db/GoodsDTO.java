package com.itwillbs.admin.goods.db;

import java.sql.Date;

import lombok.Data;

// @Data : lombok 라이브러리 사용해서 
//         자동으로 set/get/toString() 형태를 생성

//@Getter
//@Setter
//@ToString
@Data
public class GoodsDTO {
	private int gno;							// 상품번호 PK
	private String category;                    // 카테고리
	private String name;						// 상품명
	private String content;						// 상품 상세정보
	private String size;						// 상품 크기
	private String color;						// 상품 색상
	private int amount;							// 상품 수량
	private int price;							// 상품 가격
	private String image;						// 상품 이미지
	private int best;							// 인기상품 (0- 일반 1-인기)
	private Date date;							// 상품 등록일
	
	
}
