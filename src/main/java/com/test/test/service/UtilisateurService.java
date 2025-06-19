package com.test.test.service;


import com.test.test.model.Product;
import com.test.test.model.User;
import com.test.test.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public List<User> getAll() {
        return utilisateurRepository.findAll();
    }

    public User create(User user) {
        return utilisateurRepository.save(user);
    }

    public Optional<User> getById(String id) {
        return utilisateurRepository.findById(id);
    }

    public void delete(String id) {
        utilisateurRepository.deleteById(id);
    }

    public Optional<User> update(String id, User newUtilisateur) {
        return utilisateurRepository.findById(id).map(u -> {
            u.setUsername(newUtilisateur.getUsername());
            u.setEmail(newUtilisateur.getEmail());
            u.setPassword(newUtilisateur.getPassword());
            u.setRoles(newUtilisateur.getRoles());
            return utilisateurRepository.save(u);
        });
    }
}
