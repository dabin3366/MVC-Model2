package com.itwillbs.order.db;

import java.sql.Date;

import lombok.Data;

@Data
public class OrderDTO {
	
	private int o_num;								// 주문테이블 번호
	private String o_trade_num;						// 상품 주문번호 (uq)
	
	private int o_g_num;							// 구매 상품 번호
	private String o_g_name;						// 구매 상품 이름
	private int o_g_amount;							// 구매 상품 수량
	private String o_g_size;						// 구매상품 옵션(사이즈)
	private String o_g_color;						// 구매상품 옵션(컬러)
	
	private String o_m_id;							// 구매한 ID
	
	private String o_r_name;						// 배송받는 사람명
	private String o_r_addr1;						// 배송 주소
	private String o_r_addr2;						// 배송 상세주소
	private String o_r_phone;						// 배송받는 연락처
	private String o_r_msg;							// 배송 요구사항 메세지
	
	private int o_sum_money;						// 총 구매 금액
	
	private String o_trade_type;					// 구매타입(카드,페이,입금..)
	private String o_trade_payer;					// 결제한 사람이름
	private Date o_trade_date;						// 결제일시
	
	private String o_trans_num;						// 배송번호(운송장번호)
	
	private Date o_date;							// 주문일시
	private int o_status;							// 주문상태(물건대기중,배송중...)
	
	
}
