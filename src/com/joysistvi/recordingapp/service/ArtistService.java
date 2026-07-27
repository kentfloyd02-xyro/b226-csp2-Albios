/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Artist;
import com.joysistvi.recordingapp.repository.ArtistRepo;
import com.joysistvi.recordingapp.repository.ArtistRepoImpl;
import java.util.List;

/**
 *
 * @author ktagl
 */
public class ArtistService {
    private final ArtistRepo artistRepo;

    public ArtistService() {
        artistRepo = new ArtistRepoImpl();
    }
    
    public List<Artist> getAllArtist(){
        return artistRepo.getAllArtist();
    }
    
    public boolean createArtist(Artist artist){
        return artistRepo.createArtist(artist);
    }
    
    public boolean updateArtist(Artist artist) {
        return artistRepo.updateArtist(artist);
    }
    
    public boolean deleteArtist(int id){
        return artistRepo.deleteArtist(id);
    }
    
    public Artist checkArtistId(int id) {
        return artistRepo.checkArtistId(id);
    }
}
