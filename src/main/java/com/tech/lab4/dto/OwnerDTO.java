package com.tech.lab4.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tech.lab4.entities.Cat;
import com.tech.lab4.entities.Owner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Getter
public class OwnerDTO implements UserDetails {
    private Long id;
    private String name;
    private LocalDate birthdate;
    @JsonIgnore
    @Getter
    private String password;
    private List<Cat> cats;
    private Collection<? extends GrantedAuthority> authorities;

    public OwnerDTO() {}

    @Override
    public String getUsername() {
        return name;
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

    public static OwnerDTO build(Owner owner) {
        List<GrantedAuthority> authorities = owner.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new OwnerDTO(
                owner.getId(),
                owner.getName(),
                owner.getBirthdate(),
                owner.getPassword(),
                owner.getCats(),
                authorities);
    }
}
