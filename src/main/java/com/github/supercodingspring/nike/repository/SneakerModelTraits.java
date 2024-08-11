package com.github.supercodingspring.nike.repository;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sneaker_model_traits")
public class SneakerModelTraits {
    @Id @Column(name = "model_trait_id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer modelTraitId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private Sneaker sneaker;

    @OneToOne
    @JoinColumn(name = "trait_id")
    private SneakerTraits sneakerTraits;
}
