package com.github.supercodingspring.nike.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public enum OrderStatus {
    ORDER_COMPLETED(0,"주문 완료"),
    PAY_COMPLETED(1, "결제 완료");

    private final Integer code;
    private final String desc;

    public static OrderStatus fromCode(Integer code){
        for(OrderStatus status:OrderStatus.values()){
            if(Objects.equals(status.getCode(), code))
                return status;
        }
        throw new IllegalArgumentException("");
    }
}
