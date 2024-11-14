package com.sparta.ordermanagement.framework.persistence.entity.user;

import com.sparta.ordermanagement.application.domain.user.User;
import com.sparta.ordermanagement.framework.persistence.entity.BaseEntity;
import com.sparta.ordermanagement.framework.persistence.entity.region.RegionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "p_user")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="user_id")
    private Long id;

    @Column(unique = true, nullable = false, columnDefinition = "varchar(255)")
    private String userStringId;

    @Column(nullable = false, columnDefinition = "varchar(255)")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(255)")
    private Role role;

    @JoinColumn(name = "region_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private RegionEntity regionEntity;

    private UserEntity(String userId) {
        this.userStringId = userId;
    }

    public static UserEntity valueOf(String userId) {
        return new UserEntity(userId);
    }

    public UserEntity(Long id, String userStringId, String password, Role role, RegionEntity regionEntity) {
        super(userStringId, userStringId);
        this.id = id;
        this.userStringId = userStringId;
        this.password = password;
        this.role = role;
        this.regionEntity = regionEntity;
    }

    public User toDomain(){

        return new User(
                this.id,
                this.userStringId,
                this.password,
                this.role,
                this.regionEntity
        );

    }

    public static UserEntity from(User user){
        return new UserEntity(
                null,
                user.getUserStringId(),
                user.getPassword(),
                user.getRole(),
                user.getRegionEntity()
        );
    }

    public static UserEntity from(User user, String encodedPassword){

        return new UserEntity(
                null,
                user.getUserStringId(),
                encodedPassword,
                user.getRole(),
                user.getRegionEntity()
        );
    }
}
