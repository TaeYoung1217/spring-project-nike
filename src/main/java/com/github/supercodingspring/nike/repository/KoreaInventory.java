package com.github.supercodingspring.nike.repository;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "koreaInventoryId")
@Table(name = "korea_inventory")
@ToString(exclude = "sneaker")
@Builder
@Entity
public class KoreaInventory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "korea_inventory_id")
    private Integer koreaInventoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private Sneaker sneaker;

    @Column(name = "sneaker_size")
    private Integer sneakerSize;

    @Column(name = "stock")
    private Integer stock;
}
