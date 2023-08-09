package com.preonboarding.global.utils;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorityUtils {

    private final List<String> USER_ROLES = List.of("ROLE_USER");

    public static List<SimpleGrantedAuthority> getAuthorities(List<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public List<String> createRoles() {
        return USER_ROLES;
    }
}
