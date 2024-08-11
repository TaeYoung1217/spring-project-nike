package com.github.supercodingspring.nike.repository;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "wishId")
@Table(name = "wish")
@ToString
@Builder
@Entity
public class wish {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wish_id")
    private Integer wishId;

    @Column(name = "g_user_id")
    private Integer gUserId;

    @Column(name = "model_id")
    private Integer modelId;

    @Column(name = "sneaker_size")
    private Integer sneakerSize;

    @Column(name = "expected_replenishment_date")
    private LocalDateTime expectedReplenishmentDate;

    @Column(name = "wish_at")
    private LocalDateTime wishAt;
}
