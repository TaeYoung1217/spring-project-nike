package com.github.supercodingspring.nike.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentBody {
    @JsonProperty("general_user_id")
    private Integer generalUserId;
    @JsonProperty("order_id")
    private Integer orderId;
    @JsonProperty("type")
    private String type;
}
