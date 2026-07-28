/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.model;

public class User {

    private int id;
    private String username;
    private String password;
    private String role;
    private int playlist_id;

    public User(int id, String username, String password, String role, int playlist_id) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.playlist_id = playlist_id;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = "user";
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(int id, String username , String password , String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(int id, String username, String role, int playlist_id) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.playlist_id = playlist_id;
    }
    
    public User(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public User(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public int getPlaylistID() {
        return playlist_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPlaylistId(int playlist_id) {
        this.playlist_id = playlist_id;
    }
}
