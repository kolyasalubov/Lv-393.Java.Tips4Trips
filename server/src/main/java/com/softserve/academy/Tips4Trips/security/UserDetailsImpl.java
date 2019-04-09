package com.softserve.academy.Tips4Trips.security;


import com.softserve.academy.Tips4Trips.entity.Role;
import com.softserve.academy.Tips4Trips.entity.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {
    private Long id;

    @JsonIgnore
    private String login;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String login, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl create(User user, Role role) {
        List<GrantedAuthority> authorities =new ArrayList<>();
        switch(role) {
            case ADMIN:
                authorities.add(new SimpleGrantedAuthority(
                        RoleConstant.ROLE_ADMIN)
                );
                /*falls through*/
            case MODERATOR:
                authorities.add(new SimpleGrantedAuthority(
                        RoleConstant.ROLE_MODERATOR)
                );
                /*falls through*/
            case USER:
                authorities.add(new SimpleGrantedAuthority(
                        RoleConstant.ROLE_USER)
                );

        }

        return new UserDetailsImpl(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                authorities
        );
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailsImpl that = (UserDetailsImpl) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}