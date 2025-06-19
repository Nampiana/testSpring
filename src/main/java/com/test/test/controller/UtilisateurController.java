package com.test.test.controller;

import com.test.test.model.Product;
import com.test.test.model.User;
import com.test.test.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/utilisateur")
@RequiredArgsConstructor
public class UtilisateurController {
    private final UtilisateurService utilisateurService;

    @GetMapping
    public List<User> all() {
        return utilisateurService.getAll();
    }

    @PostMapping
    public User create(@RequestBody User User) {
        return utilisateurService.create(User);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable String id) {
        return utilisateurService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        utilisateurService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable String id, @RequestBody User newUtilisateur) {
        return utilisateurService.update(id, newUtilisateur)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
