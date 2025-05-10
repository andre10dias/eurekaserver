package com.ms.hr_oauth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ms.hr_oauth.entities.User;
import com.ms.hr_oauth.feignclients.UserFeignClient;

@Service
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Usando o UserFeignClient para buscar o usuário pelo e-mail (ou username)
        User user = userFeignClient.findByEmail(username).getBody();
        
        // Se o usuário não for encontrado, lança uma exceção
        if (user == null) {
            logger.error("Email not found: " + username);
            throw new UsernameNotFoundException("Email not found");
        }
        
        // Caso o usuário seja encontrado, registra e retorna o usuário
        logger.info("Email found: " + username);
        return user; // Retorna o usuário como um UserDetails
    }

    // Método auxiliar para buscar usuário por email (opcional, conforme sua necessidade)
    public User findByEmail(String email) {
        logger.info("Searching user by email: " + email);
        return userFeignClient.findByEmail(email).getBody();
    }
}
