package com.github.supercodingspring.nike.web.dto;

import com.github.supercodingspring.nike.repository.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class GeneralUserOrder {
    private Integer orderId;
    private Integer modelId;
    private Integer sneakerSize;
    private Integer orderQuantity;
    private Double totalPrice;
    private OrderStatus orderStatus;
    private LocalDateTime orderAt;
}
