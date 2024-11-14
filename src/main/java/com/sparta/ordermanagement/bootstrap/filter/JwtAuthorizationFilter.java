package com.sparta.ordermanagement.bootstrap.filter;

import com.sparta.ordermanagement.bootstrap.auth.UserDetailsImpl;
import com.sparta.ordermanagement.bootstrap.util.JwtUtil;

import com.sparta.ordermanagement.framework.persistence.entity.user.Role;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import java.util.Set;


@RequiredArgsConstructor
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private static final Set<String> FILTERING_URIS_ADMIN = Set.of(
            "/admin/v1"
    );

    private static final Set<String> NO_FILTERING_URIS = Set.of(
            "/api/v1/users/signin",
            "/api/v1/users/signup"
    );


    public static boolean isFilteringUri(String uri) {
        // 필터가 필요 없는 URI는 제외
        if (NO_FILTERING_URIS.contains(uri)) {
            return false;
        }
        // 필터링이 필요한 admin URI 또는 /shops/{숫자} 패턴 확인
        return FILTERING_URIS_ADMIN.stream().anyMatch(uri::startsWith);
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 필터 로직을 수행하지 않고 다음 필터로 이동
        if (!isFilteringUri(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        String tokenValue = jwtUtil.getTokenFromRequest(request);

        if(!(StringUtils.hasText(tokenValue) && jwtUtil.validateToken(tokenValue))){
            throw new ServletException("Invalid token");
        }


        Claims info = jwtUtil.getUserInfoFromToken(tokenValue);

        try {
            setAuthentication(info.getSubject(), Role.valueOf((String) info.get(jwtUtil.AUTHORIZATION_KEY)));
        } catch (Exception e) {
            log.error(e.getMessage());
            return;
        }

        filterChain.doFilter(request, response);

    }
    // 인증 처리
    public void setAuthentication(String userStringId, Role role) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = createAuthentication(userStringId, role);
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

    // 인증 객체 생성
    private Authentication createAuthentication(String userStringId, Role role) {
        UserDetails userDetails = new UserDetailsImpl(userStringId, role);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
