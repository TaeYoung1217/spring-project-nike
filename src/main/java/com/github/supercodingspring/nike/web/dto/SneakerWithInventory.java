package com.github.supercodingspring.nike.web.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SneakerWithInventory {
    private Integer modelId;
    private List<String> model_traits;
    private Double price;
    private List<SneakerInventory> sneaker_inventories;
}
