/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.controller;

import com.joysistvi.recordingapp.model.Artist;
import com.joysistvi.recordingapp.service.ArtistService;
import com.joysistvi.recordingapp.service.SongService;
import java.util.List;


/**
 *
 * @author ktagl
 */
public class ArtistController {
    private final ArtistService artistService;

    public ArtistController() {
        artistService = new ArtistService();
    }
    
    public List<Artist> getAllArtist(){
        return artistService.getAllArtist();
    }
    
    public boolean createArtist(Artist artist){
        return artistService.createArtist(artist);
    }
    
    public boolean updateArtist(Artist artist) {
        return artistService.updateArtist(artist);
    }
    
    public boolean deleteArtist(int id){
        return artistService.deleteArtist(id);
    }
    
    public Artist checkArtistId(int id) {
        return artistService.checkArtistId(id);
    }
}
