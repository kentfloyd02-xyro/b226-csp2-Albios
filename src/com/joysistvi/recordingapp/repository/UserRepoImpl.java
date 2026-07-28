/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.config.dbconnection;
import com.joysistvi.recordingapp.model.User;

import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

public class UserRepoImpl implements UserRepo {

    private final dbconnection db = new dbconnection();

    @Override
    public List<User> getAllUser() {

        List<User> users = new ArrayList<>();

        String query = "SELECT * FROM users";

        try (Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query); ResultSet rs = prep.executeQuery()) {

            while (rs.next()) {

                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("role"),
                        rs.getInt("playlist_id")
                );

                users.add(user);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public boolean register(String username, String password, String role) {

        String query = "INSERT INTO users (username, password, role) VALUES(?, ?, ?)";
        
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        try (Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, username);
            prep.setString(2, hashedPassword);
            prep.setString(3, "user");

            return prep.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public User login(String username, String password) {

    String query = "SELECT * FROM users WHERE username = ?";

    try (Connection conn = db.connect();PreparedStatement prep = conn.prepareStatement(query)) {

        prep.setString(1, username);

        try (ResultSet rs = prep.executeQuery()) {

            if (rs.next()) {

                String hashedPassword = rs.getString("password");

                if (BCrypt.checkpw(password, hashedPassword)) {

                    return new User(
                            rs.getString("username"),
                            hashedPassword,
                            rs.getString("role")
                    );
                }
            }

        }

    } catch (SQLException e) {
        System.out.println("Login Error: " + e.getMessage());
    }

    return null;
}

    public boolean updateUser(User user) {

        String query = "UPDATE users SET role = ? WHERE user_id = ?";

        try (Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {
            
            prep.setString(1, user.getRole());
            prep.setInt(2, user.getId());

            return prep.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteUser(int id) {

        String query = "DELETE FROM users WHERE user_id = ?";

        try (Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {
            prep.setInt(1, id);

            return prep.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public User checkUserId(int id) {

        String query = "SELECT * FROM users WHERE user_id = ?";

        try (Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            try (ResultSet rs = prep.executeQuery()) {

                if (rs.next()) {

                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getString("role"),
                            rs.getInt("playlist_id")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public User checkUsername(String username) {

    String query = "SELECT * FROM users WHERE username = ?";

    try (Connection conn = db.connect();
         PreparedStatement prep = conn.prepareStatement(query)) {

        prep.setString(1, username);

        try (ResultSet rs = prep.executeQuery()) {

            if (rs.next()) {

                return new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("role"),
                        rs.getInt("playlist_id")
                );
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}
}
