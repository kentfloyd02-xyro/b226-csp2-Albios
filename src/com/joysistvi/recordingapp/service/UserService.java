/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.User;
import com.joysistvi.recordingapp.repository.UserRepo;
import com.joysistvi.recordingapp.repository.UserRepoImpl;
import java.util.List;

public class UserService {

    private UserRepo repo = new UserRepoImpl();

    public User login(String username, String password) {
        return repo.login(username, password);
    }

    public boolean register(User user) {
        return repo.register(user);
    }

    public List<User> getAllUser() {
        return repo.getAllUser();
    }

    public User checkUserId(int id) {
        return repo.checkUserId(id);
    }

    public boolean updateUser(User user) {
        return repo.updateUser(user);
    }

    public boolean deleteUser(int id) {
        return repo.deleteUser(id);
    }
}
