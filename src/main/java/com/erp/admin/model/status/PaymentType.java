package com.erp.admin.model.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentType {

    CARD(0, "신용 카드", "신용 카드 결제"),
    CHECK_CARD(1, "체크카드", "체크카드 결제"),
    BANK_TRANSFER(2, "계좌이체", "계좌이체 결제");

    private Integer id;
    private String title;
    private String description;
}
