package com.github.supercodingspring.nike.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WishBody {
    @JsonProperty("model_id")
    private Integer modelId;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("sneaker_size")
    private Integer sneakerSize;
}
