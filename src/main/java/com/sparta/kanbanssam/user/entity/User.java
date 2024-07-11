package com.sparta.kanbanssam.user.entity;

import com.sparta.kanbanssam.common.entity.Timestamped;
import com.sparta.kanbanssam.common.enums.UserRole;
import com.sparta.kanbanssam.common.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table(name="User")
@NoArgsConstructor
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    private String accountId;
    private String password;
    private String email;
    private String name;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Setter
    private String refreshToken;


//    @Builder
//    public User(Long id) {
//        this.id = id;
//    }

    @Builder
    public User(String accountId, String password, String email, String name) {
        this.accountId = accountId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.userStatus = UserStatus.UNAUTHORIZED;
        this.userRole = UserRole.USER;
    }


}
