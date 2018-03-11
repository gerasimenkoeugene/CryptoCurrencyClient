package com.iege.crypto.client.repository;

import com.iege.crypto.client.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    public User findById(String Id);
    public User findByUserName(String userName);
    public User findByEmail(String email);
    public List<User> findAll();

}

