/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.User;
import com.joysistvi.recordingapp.repository.UserRepo;
import com.joysistvi.recordingapp.repository.UserRepoImpl;

public class UserService {

    private UserRepo repo = new UserRepoImpl();

    public User login(String username, String password) {
        return repo.login(username, password);
    }

    public boolean register(User user) {
        return repo.register(user);
    }
}
