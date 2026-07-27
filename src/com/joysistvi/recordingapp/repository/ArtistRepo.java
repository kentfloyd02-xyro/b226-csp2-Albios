/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.model.Artist;
import java.util.List;

/**
 *
 * @author ktagl
 */
public interface ArtistRepo {
    
    List<Artist> getAllArtist();
    Artist checkArtistId(int id);
    boolean createArtist(Artist artist);
    boolean updateArtist(Artist artist);
    boolean deleteArtist(int id);
    
}
