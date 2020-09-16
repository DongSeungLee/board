package com.example.board;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@NoArgsConstructor
public class VendorAuthentication implements Authentication {
    private Object name;
    private boolean isAuthenticated;
    private Object username;
    private Object principal;
    private Collection<? extends GrantedAuthority> authorities;
    private Object credentials;
    private Object details;
    @Builder
    public VendorAuthentication(Object name, boolean isAuthenticated, Object username, Object principal, Object credentials,Object details,Collection<? extends GrantedAuthority>authorities) {
        this.name = name;
        this.isAuthenticated = isAuthenticated;
        this.username = username;
        this.principal = principal;
        this.credentials = credentials;
        this.details = details;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
