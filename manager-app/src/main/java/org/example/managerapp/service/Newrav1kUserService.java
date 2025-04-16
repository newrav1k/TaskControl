package org.example.managerapp.service;

import lombok.RequiredArgsConstructor;
import org.example.managerapp.repository.Newrav1kUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Newrav1kUserService implements UserDetailsService {

    private final Newrav1kUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findNewrav1kUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with " + username + " not found"));
    }

}