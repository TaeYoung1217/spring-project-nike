package com.github.supercodingspring.nike.repository;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "userId")
@Table(name = "user")
@ToString
@Builder
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "email", length = 30)
    private String email;

    @Column(name = "phone_num", length = 15)
    private String phoneNum;
}
