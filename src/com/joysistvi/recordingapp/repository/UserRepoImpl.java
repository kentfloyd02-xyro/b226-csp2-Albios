/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.config.dbconnection;
import com.joysistvi.recordingapp.model.User;
import com.joysistvi.recordingapp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepoImpl implements UserRepo {

    private final dbconnection db = new dbconnection();

    @Override
    public boolean register(User user) {

        String query = "INSERT INTO users (username, password, role) VALUES(?, ?, ?)";

        try (
                Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, user.getUsername());
            prep.setString(2, user.getPassword());
            prep.setString(3, user.getRole());
            
            return prep.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public User login(String username, String password) {

        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (
                Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, username);
            prep.setString(2, password);

            ResultSet rs = prep.executeQuery();

            if (rs.next()) {

                return new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
