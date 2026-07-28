/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.model;

public class Playlist {

    private int id;
    private String playlistName;
    private String createdAt;
    private int userId;

    public Playlist() {
    }

    public Playlist(String playlistName, String createdAt, int userId) {
        this.playlistName = playlistName;
        this.createdAt = createdAt;
        this.userId = userId;
    }

    public Playlist(int id, String playlistName, String createdAt, int userId) {
        this.id = id;
        this.playlistName = playlistName;
        this.createdAt = createdAt;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}