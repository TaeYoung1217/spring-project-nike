package com.github.supercodingspring.nike.repository;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "paymentId")
@Table(name = "payment")
@ToString
@Builder
@Entity
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer paymentId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "g_user_id")
    private GeneralUser generalUser;

    @JoinColumn(name = "order_id")
    @OneToOne(fetch = FetchType.EAGER)
    private Orders orders;

    @Column(name = "type")
    private String type;

    @Column(name = "payment_at")
    private LocalDateTime paymentAt;
}
