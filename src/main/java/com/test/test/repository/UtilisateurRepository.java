package com.test.test.repository;

import com.test.test.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UtilisateurRepository extends MongoRepository<User, String> {
}
