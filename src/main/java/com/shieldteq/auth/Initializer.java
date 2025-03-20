package com.shieldteq.auth;

import com.shieldteq.auth.data.Role;
import com.shieldteq.auth.data.User;
import com.shieldteq.auth.data.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
public class Initializer {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public Initializer(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }
    @PostConstruct
    public void init() {
        String id = UUID.nameUUIDFromBytes("admin".getBytes(StandardCharsets.UTF_8)).toString();
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            return;
        }
        User user = new User();
        user.setId(id);
        user.setUsername("admin");
        user.setHashedPassword(encoder.encode("admin"));
        user.setRoles(Set.of(Role.ADMIN));
        user.setName("admin");
        userRepository.save(user);
    }
}
