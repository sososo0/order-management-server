package com.sparta.ordermanagement.bootstrap.rest.dto.user;

import com.sparta.ordermanagement.framework.persistence.entity.user.Role;


public record UserSecurityInfo(
        String userStringId,
        Role role
) {

}
