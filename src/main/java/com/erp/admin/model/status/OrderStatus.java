package com.erp.admin.model.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {

    CONFIRM(0, "확인", "상품 주문 확인 상태"),
    ORDERING(1,"주문중", "상품 준비중 상태"),
    COMPLETE(2, "주문 확인", "상품 배송 완툐");

    private Integer id;

    private String title;

    private String description;
}
