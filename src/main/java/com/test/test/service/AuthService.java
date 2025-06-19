package com.test.test.service;


import com.test.test.dto.LoginResponse;
import com.test.test.model.JwtUtil;
import com.test.test.model.User;
import com.test.test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email déjà utilisé !");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return "Utilisateur enregistré !";
    }

    public LoginResponse login(Map<String, String> loginData) {
        User user = userRepository.findByEmail(loginData.get("email"))
                .orElseThrow(() -> new RuntimeException("Email invalide"));

        if (!passwordEncoder.matches(loginData.get("password"), user.getPassword())) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponse(token, user.getUsername(), user.getEmail(), user.getRoles());
    }

}
