package com.github.supercodingspring.nike.repository;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "modelId")
@Table(name = "sneaker")
@ToString
@Builder
@Entity
public class Sneaker {
    //DB와 연결하기 위한 객체
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private Integer modelId;

    @Column(name = "price")
    private Double price;

    @OneToMany(mappedBy = "sneaker")
    private List<SneakerModelTraits> sneakerModelTraits;
}
