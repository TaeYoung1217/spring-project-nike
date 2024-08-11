package com.github.supercodingspring.nike.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderBody {
    @Schema(description = "model_id", example = "1")
    @JsonProperty("model_id")
    private Integer modelId;

    @Schema(description = "user_id", example = "2")
    @JsonProperty("user_id")
    private Integer userId;

    @Schema(description = "배송 주소", example = "서울시 강남구 도곡동")
    @JsonProperty("shipping_address")
    private String shippingAddress;

    @Schema(description = "신발 사이즈", example = "300")
    @JsonProperty("sneaker_size")
    private Integer sneakerSize;

    @Schema(description = "구매할 수량", example = "3")
    @JsonProperty("order_quantity")
    private Integer orderQuantity;
}
