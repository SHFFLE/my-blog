package com.codeup.auth;

import org.springframework.data.repository.CrudRepository;

public interface Users extends CrudRepository<User, Long> {
    public User findByUsername(String username);
}