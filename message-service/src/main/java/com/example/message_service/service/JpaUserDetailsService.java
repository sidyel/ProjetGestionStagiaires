// src/main/java/com/example/message_service/security/JpaUserDetailsService.java
package com.example.message_service.service;
// src/main/java/com/example/message_service/security/JpaUserDetailsService.java

import com.example.message_service.entity.Personne;
import com.example.message_service.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonneRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Personne p = repo.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur inconnu"));

        return User.withUsername(p.getLogin())
                .password(p.getMotpasse())              // mot de passe déjà haché
                .authorities(p.getRole())               // ROLE_STAGIAIRE, ROLE_ENCADRANT, …
                .build();
    }
}
