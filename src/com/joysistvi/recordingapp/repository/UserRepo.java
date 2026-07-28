/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.model.User;
import java.util.List;

public interface UserRepo {

    boolean register(User user);

    User login(String username, String password);

    public List<User> getAllUser();

    public User checkUserId(int id);

    boolean updateUser(User user);

    boolean deleteUser(int id);

}
