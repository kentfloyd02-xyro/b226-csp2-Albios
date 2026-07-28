/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Album;
import com.joysistvi.recordingapp.repository.AlbumRepo;
import com.joysistvi.recordingapp.repository.AlbumRepoImpl;

import java.util.List;

public class AlbumService {

    private final AlbumRepo albumRepo;

    public AlbumService() {
        albumRepo = new AlbumRepoImpl();
    }

    public List<Album> getAllAlbums() {
        return albumRepo.getAllAlbums();
    }

    public Album checkAlbumId(int id) {
        return albumRepo.checkAlbumId(id);
    }

    public boolean createAlbum(Album album) {
        return albumRepo.createAlbum(album);
    }

    public boolean updateAlbum(Album album) {
        return albumRepo.updateAlbum(album);
    }

    public boolean deleteAlbum(int id) {
        return albumRepo.deleteAlbum(id);
    }

}