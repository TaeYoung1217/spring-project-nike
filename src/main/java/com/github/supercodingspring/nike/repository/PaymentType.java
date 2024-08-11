package com.github.supercodingspring.nike.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public enum PaymentType {
    PAY_BANK_ACCOUNT(1,"계좌 이체"),
    PAY_CARD(2, "카드 결제");

    private final Integer code;
    private final String desc;

    public static PaymentType fromDesc(String desc){
        for(PaymentType paymentType : PaymentType.values()){
            if(Objects.equals(paymentType.getDesc(), desc))
                return paymentType;
        }
        return null;
    }
}
