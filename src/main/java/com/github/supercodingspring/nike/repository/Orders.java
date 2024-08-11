package com.github.supercodingspring.nike.repository;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "orderId")
@Table(name = "orders")
@ToString
@Builder
@Entity
public class Orders {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @JoinColumn(name = "g_user_id")
    @OneToOne(fetch = FetchType.LAZY)
    private GeneralUser generalUser;

    @JoinColumn(name = "model_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sneaker sneaker;

    @Column(name = "sneaker_size")
    private Integer sneakerSize;

    @Column(name = "order_quantity")
    private Integer orderQuantity;

    @Column(name = "shipping_address", length = 30)
    private String shippingAddress;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "order_at")
    private LocalDateTime orderAt;

}
