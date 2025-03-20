package com.shieldteq.auth.services;

import com.shieldteq.auth.data.User;
import com.shieldteq.auth.data.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> get(String id) {
        return repository.findById(id);
    }

    public User save(User entity) {
        return repository.save(entity);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public Page<User> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public int count() {
        return (int) repository.count();
    }

}
