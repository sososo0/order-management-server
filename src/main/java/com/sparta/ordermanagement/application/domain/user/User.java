package com.sparta.ordermanagement.application.domain.user;

import com.sparta.ordermanagement.framework.persistence.entity.region.RegionEntity;
import com.sparta.ordermanagement.framework.persistence.entity.user.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {

    private Long id;
    private String userStringId;
    private String password;
    private Role role;
    private RegionEntity regionEntity;

}
