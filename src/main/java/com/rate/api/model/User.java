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
@NoArgsConstructor
@AllArgsConstructor
//@ToString()
@MappedSuperclass
public abstract class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "`login`")
    private String login;

    @Column(name = "`password`")
    private String password;

    @Column(name = "`fullName`")
    private String fullName;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "faculty_id", referencedColumnName = "id", nullable = false)
//    @ToString.Exclude
//    private Faculty faculty;

    @Column(name = "`email`")
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean isExpired;

    private Boolean isLocked;

    private Boolean isCredentialsExpired;

    private Boolean isEnabled;


    public User(String login,
                String password,
                String fullName,
                String email,
                Role role,
                Boolean isExpired,
                Boolean isLocked,
                Boolean isCredentialsExpired,
                Boolean isEnabled) {
        this.login = login;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.isExpired = isExpired;
        this.isLocked = isLocked;
        this.isCredentialsExpired = isCredentialsExpired;
        this.isEnabled = isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isCredentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", isExpired=" + isExpired +
                ", isLocked=" + isLocked +
                ", isCredentialsExpired=" + isCredentialsExpired +
                ", isEnabled=" + isEnabled +
                '}';
    }
}
