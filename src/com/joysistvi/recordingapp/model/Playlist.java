/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.model;

/**
 *
 * @author ktagl
 */
public class Playlist {

    private int id;
    private String created_at;
    private int song_id;
    private String songTitle;

    public Playlist() {
    }

    public Playlist(int id, String created_at, int song_id) {
        this.id = id;
        this.created_at = created_at;
        this.song_id = song_id;
    }

    public Playlist(String created_at, int song_id) {
        this.created_at = created_at;
        this.song_id = song_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }
}
