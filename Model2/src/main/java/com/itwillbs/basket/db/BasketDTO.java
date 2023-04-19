package com.itwillbs.basket.db;

import java.sql.Date;

import lombok.Data;


@Data
public class BasketDTO {
	private int b_num;				// 장바구니 번호
	private String b_m_id;			// 장바구니 주인(아이디)
	private int b_g_num;			// 장바구니 상품번호
	private int b_g_amount;			// 장바구니 구매수량
	private String b_g_size;		// 장바구니 구매옵션(크기)
	private String b_g_color;		// 장바구니 구매옵션(색상)
	private Date b_date;			// 장바구니 등록일 	
	
}
