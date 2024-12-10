package com.softrelic.procuratio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softrelic.procuratio.domain.User;
import com.softrelic.procuratio.repository.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    public User saveUser(User userDomain) {
        return userRepo.save(userDomain);
    }
}
