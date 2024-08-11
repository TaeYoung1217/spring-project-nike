package com.github.supercodingspring.nike.repository;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "abroadInventoryId")
@Table(name = "abroad_inventory")
@ToString
@Builder
@Entity
public class AbroadInventory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "abroad_inventory_id")
    private Integer abroadInventoryId;

    @JoinColumn(name = "model_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sneaker sneaker;

    @Column(name = "sneaker_size")
    private Integer sneakerSize;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "expected_delivered_day")
    private Integer expectedDeliveredDay;
}
