package com.github.supercodingspring.nike.repository;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.core.annotation.Order;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "shippingId")
@Table(name = "shipping")
@ToString
@Builder
@Entity
public class Shipping {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipping_id")
    private Integer shippingId;

    @JoinColumn(name = "admin_id")
    @OneToOne(fetch = FetchType.LAZY)
    private AdminUser adminUser;

    @JoinColumn(name = "model_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Sneaker sneaker;

    @JoinColumn(name = "order_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Orders orders;

    @Column(name = "shipping_address", length = 30)
    private String shippingAddress;

    @Column(name = "shipping_at")
    private LocalDateTime shippingAt;
}
