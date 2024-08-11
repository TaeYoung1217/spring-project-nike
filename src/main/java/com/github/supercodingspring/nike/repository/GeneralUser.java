package com.github.supercodingspring.nike.repository;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "gUserId")
@Table(name = "general_user")
@ToString
@Builder
@Entity
public class GeneralUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "g_user_id")
    private Integer gUserId;

    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(name = "favorite_shopping_address", length = 30)
    private String favoriteShoppingAddress;

    @Column(name = "my_card_num", length = 20)
    private String myCardNum;

    @Column(name = "my_bank_account", length = 30)
    private String myBackAccount;

    public GeneralUser(User user) {
        this.user = user;
    }
}
