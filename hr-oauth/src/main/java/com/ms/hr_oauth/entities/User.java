package com.ms.hr_oauth.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String name;
    private String email;
    private String password;
    private Set<Role> roles = new HashSet<>();
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Converte as roles em GrantedAuthority
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : roles) {
            authorities.add(() -> role.getName()); // Assumindo que Role tem um método getName() para retornar o nome da role.
        }
        return authorities;
    }
    
    @Override
    public String getUsername() {
        return email; // Aqui você pode retornar o que for adequado, por exemplo, o e-mail do usuário.
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true; // Este é um valor simplificado. Pode adicionar a lógica necessária.
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Este é um valor simplificado. Pode adicionar a lógica necessária.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Este é um valor simplificado. Pode adicionar a lógica necessária.
    }

    @Override
    public boolean isEnabled() {
        return true; // Este é um valor simplificado. Pode adicionar a lógica necessária.
    }
}
