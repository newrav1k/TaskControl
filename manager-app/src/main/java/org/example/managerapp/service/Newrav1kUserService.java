package org.example.managerapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.managerapp.entity.Newrav1kUser;
import org.example.managerapp.repository.Newrav1kUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class Newrav1kUserService implements UserDetailsService {

    private final Newrav1kUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(String username, String email, String password) {
        log.info("Creating new user with username: {}", username);
        Newrav1kUser user = Newrav1kUser.builder()
                .username(username)
                .email(email)
                .roles(Set.of("ROLE_USER"))
                .password(this.passwordEncoder.encode(password))
                .build();
        this.userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Loading user with username: {}", username);
        return this.userRepository.findNewrav1kUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with " + username + " not found"));
    }

}