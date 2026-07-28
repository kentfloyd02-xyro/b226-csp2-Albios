/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.model;

/**
 *
 * @author ktagl
 */
public class Album {

    private int id;
    private String title;
    private int year;
    private int artist_id;
    private String artistName;

    public Album() {
    }

    public Album(int id, String title, int year, int artistId) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.artist_id = artistId;
    }

    public Album(String title, int year, int artistId) {
        this.title = title;
        this.year = year;
        this.artist_id = artistId;
    }
    
    public Album(int id, String title, int year) {
        this.id = id;
        this.title = title;
        this.year = year;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
