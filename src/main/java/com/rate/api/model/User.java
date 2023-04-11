package com.rate.api.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString()
@MappedSuperclass
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "`login`")
    private String login;

    @Column(name = "`password`")
    private String password;

    @Column(name = "`role`")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "`fullName`")
    private String fullName;

    @Column(name = "`isAccountExpired`")
    private Boolean isAccountExpired;

    @Column(name = "`isAccountLocked`")
    private Boolean isAccountLocked;

    @Column(name = "`isCredentialsExpired`")
    private Boolean isCredentialsExpired;

    @Column(name = "`isEnabled`")
    private Boolean isEnabled;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isAccountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isAccountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isCredentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
