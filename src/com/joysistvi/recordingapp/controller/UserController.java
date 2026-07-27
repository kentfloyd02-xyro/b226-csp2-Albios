/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.controller;

import com.joysistvi.recordingapp.model.User;
import com.joysistvi.recordingapp.service.UserService;

public class UserController {

    private UserService service = new UserService();

    public User login(String username, String password) {
        return service.login(username, password);
    }

    public boolean register(User user) {
        return service.register(user);
    }
    
}
