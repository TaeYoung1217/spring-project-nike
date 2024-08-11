package com.github.supercodingspring.nike.repository;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "traitId")
@Table(name = "sneaker_traits")
@ToString
@Builder
@Entity
public class SneakerTraits {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trait_id")
    private String traitId;

    @Column(name = "descriptions")
    private String descriptions;

    @Column(name = "english_desc")
    private String englishDesc;


}
