package com.softserve.academy.Tips4Trips.security;

import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.administration.User;
import com.softserve.academy.Tips4Trips.entity.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

import static com.softserve.academy.Tips4Trips.entity.enums.Role.USER;

public class UserDetailsImpl implements OAuth2User, UserDetails {
    private Long id;

    private String login;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    private Map<String, Object> attributes;

    public UserDetailsImpl(Long id, String login, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.authorities = authorities;
    }

    public UserDetailsImpl(Long id, String login, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.login = login;
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
                user.getAccount().getId(),
                user.getLogin(),
                user.getPassword(),
                authorities
        );
    }

    public static UserDetailsImpl createAccount(Account account, Role role) {
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
                account.getId(),
                account.getEmail(),
                authorities
        );
    }

    public static UserDetailsImpl createAccount(Account account, Map<String, Object> attributes) {
        UserDetailsImpl userDetails = UserDetailsImpl.createAccount(account, USER);
        userDetails.setAttributes(attributes);
        return userDetails;
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
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
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

    @Override
    public String getName() {
        return String.valueOf(id);
    }
}