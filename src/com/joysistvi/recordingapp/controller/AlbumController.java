/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.controller;

import com.joysistvi.recordingapp.model.Album;
import com.joysistvi.recordingapp.service.AlbumService;

import java.util.List;

public class AlbumController {

    private final AlbumService albumService;

    public AlbumController() {
        albumService = new AlbumService();
    }

    public List<Album> listAlbums() {
        return albumService.getAllAlbums();
    }

    public Album checkAlbumId(int id) {
        return albumService.checkAlbumId(id);
    }

    public boolean createAlbum(Album album) {
        return albumService.createAlbum(album);
    }

    public boolean updateAlbum(Album album) {
        return albumService.updateAlbum(album);
    }

    public boolean deleteAlbum(int id) {
        return albumService.deleteAlbum(id);
    }

    public boolean truncateAlbum() {
        return albumService.truncateAlbum();
    }

}
