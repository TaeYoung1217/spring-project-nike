package com.github.supercodingspring.nike.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SneakerDto {
    @Schema(description = "The Unique ID of sneaker", example = "1")
    private Integer modelId;

    @Schema(description = "The description of sneaker", example = "최적의 쿠셔닝 제공")
    private List<String> descriptions;
}
