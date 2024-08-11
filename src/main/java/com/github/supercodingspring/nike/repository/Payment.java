package com.github.supercodingspring.nike.repository;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.relational.core.sql.In;

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
    private Integer type;

    @Column(name = "payment_at")
    private LocalDateTime paymentAt;
}
