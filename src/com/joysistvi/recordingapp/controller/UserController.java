/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.controller;

import com.joysistvi.recordingapp.model.User;
import com.joysistvi.recordingapp.service.UserService;
import java.util.List;

public class UserController {

    private UserService service = new UserService();

    public User login(String username, String password) {
        return service.login(username, password);
    }

    public boolean register(String username, String password, String role) {
        return service.register(username, password, role);
    }

    public List<User> getAllUser() {
        return service.getAllUser();
    }

    public User checkUserId(int id) {
        return service.checkUserId(id);
    }

    public boolean updateUser(User user) {
        return service.updateUser(user);
    }

    public boolean deleteUser(int id) {
        return service.deleteUser(id);
    }
    
    public User checkUsername(String username){
        return service.checkUsername(username);
    }
}
