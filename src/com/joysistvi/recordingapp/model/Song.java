/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.model;

/**
 *
 * @author ktagl
 */
public class Song {
    // private fields
    private int id;
    private String title;
    private String length;
    private String genre;
    private int album_id;
    private int is_archived;

    public Song() {
    }

    public Song(int id, String title, String length, String genre, int album_id) {
        this.id = id;
        this.title = title;
        this.length = length;
        this.genre = genre;
        this.album_id = album_id;
    }

    public Song(String title, String length, String genre, int album_id) {
        this.title = title;
        this.length = length;
        this.genre = genre;
        this.album_id = album_id;

    }

    public int getIs_archived() {
        return is_archived;
    }

    public void setIs_archived(int is_archived) {
        this.is_archived = is_archived;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }
}
