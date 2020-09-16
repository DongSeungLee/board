package com.example.board;

import WithMockVendor.WithMockVendorUser;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WithMockVendorUserSecurityContextFactory implements WithSecurityContextFactory<WithMockVendorUser> {

    WithMockVendorUserSecurityContextFactory() {

    }

    @Override
    public SecurityContext createSecurityContext(WithMockVendorUser withMockVendorUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        List<GrantedAuthority> grantedAuthorities = new ArrayList();
        String[] var4 = withMockVendorUser.roles();
        int var5 = var4.length;
        String role;
        for(int var6 = 0; var6 < var5; ++var6) {
            role = var4[var6];
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role));
        }
        // Authentication interface에 있는
        VendorAuthentication authenticated = VendorAuthentication.builder()
                .principal(withMockVendorUser.username())
                .details(withMockVendorUser.name())
                .isAuthenticated(true)
                .build();
        User principal = new User(withMockVendorUser.username(), withMockVendorUser.password(), true, true, true, true, grantedAuthorities);
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
        context.setAuthentication(authentication);
        return context;
    }
}
