package com.github.supercodingspring.nike.repository;

import com.github.supercodingspring.nike.web.dto.OrderBody;
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

    @ManyToOne
    @JoinColumn(name = "model_id")
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
    @Enumerated
    private OrderStatus orderStatus;

    @Column(name = "order_at")
    private LocalDateTime orderAt;

    public Orders(OrderBody orderBody, Sneaker sneaker, GeneralUser generalUser) {
        this.sneaker = sneaker;
        this.generalUser = generalUser;
        this.shippingAddress = orderBody.getShippingAddress();
        this.sneakerSize = orderBody.getSneakerSize();
        this.orderQuantity = orderBody.getOrderQuantity();
    }
}
