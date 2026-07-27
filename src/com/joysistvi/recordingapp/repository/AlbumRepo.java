/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.model.Album;
import java.util.List;

public interface AlbumRepo {

    List<Album> getAllAlbums();
    Album checkAlbumId(int id);
    boolean createAlbum(Album album);
    boolean updateAlbum(Album album);
    boolean deleteAlbum(int id);
    boolean truncateAlbum();
}
