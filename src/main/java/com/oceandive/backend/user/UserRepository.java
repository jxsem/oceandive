package com.oceandive.backend.user;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User,  UUID> {
    //Busca un usuario por su ID
    Optional<User> findByid(UUID id);
    //Busca a todos los usuarios por 
    Optional<User> findByEmail(String email);
}
