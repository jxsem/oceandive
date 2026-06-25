package com.oceandive.backend.user;

import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public Optional<User> findByid(UUID id){
        return userRepository.findByid(id);
    }
}
