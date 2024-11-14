package com.sparta.ordermanagement.bootstrap.auth;

import com.sparta.ordermanagement.bootstrap.rest.dto.user.UserSecurityInfo;
import com.sparta.ordermanagement.framework.persistence.entity.user.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private String userStringId;
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        String authority = role.name();

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);

        return authorities;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }


}
