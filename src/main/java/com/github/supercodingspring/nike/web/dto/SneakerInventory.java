package com.github.supercodingspring.nike.web.dto;

import com.github.supercodingspring.nike.repository.KoreaInventory;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SneakerInventory {
    private Integer sneakerSize;
    private Integer stock;

    public SneakerInventory (KoreaInventory koreaInventory){
        this.sneakerSize = koreaInventory.getSneakerSize();
        this.stock = koreaInventory.getStock();
    }
}
