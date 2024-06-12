package com.uam.s5.services;

import com.uam.s5.models.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List <User> findAllUsers();
    Optional<User> findUserById(Long id);
}
