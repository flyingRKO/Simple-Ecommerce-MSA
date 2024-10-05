package com.simple.memberservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Getter
@RequiredArgsConstructor
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String loginId;

    @Setter
    private String userName;

    private UserEntity(String loginId, String userName) {
        this.loginId = loginId;
        this.userName = userName;
    }

    public static UserEntity of(String loginId, String userName) {
        return new UserEntity(loginId, userName);
    }
}
