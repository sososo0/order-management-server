package com.sparta.ordermanagement.framework.persistence.entity.user;

import com.sparta.ordermanagement.framework.persistence.entity.BaseEntity;
import com.sparta.ordermanagement.framework.persistence.entity.region.RegionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "p_user")
public class UserEntity extends BaseEntity {

    @Id
    @Column(name ="id")
    private String userId;

    @Column(nullable = false, columnDefinition = "varchar(255)")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(255)")
    private Role role;

    @JoinColumn(name = "region_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private RegionEntity regionEntity;

    private UserEntity(String userId) {
        this.userId = userId;
    }

    public static UserEntity valueOf(String userId) {
        return new UserEntity(userId);
    }
}
