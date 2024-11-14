package com.sparta.ordermanagement.bootstrap.admin.dto;

import com.sparta.ordermanagement.application.domain.user.User;
import com.sparta.ordermanagement.framework.persistence.entity.region.RegionEntity;
import com.sparta.ordermanagement.framework.persistence.entity.user.Role;
import jakarta.persistence.*;

public record UserGetResponse(
        Long id,
        String userStringId,
        String role,
        String regionId,
        String regionName

) {
    public static UserGetResponse from(final User user) {

        return new UserGetResponse(user.getId(), user.getUserStringId(), user.getRole().name(), "", "");
    }
}
