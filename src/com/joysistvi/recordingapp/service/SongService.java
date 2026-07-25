/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.config.model.Song;
import com.joysistvi.recordingapp.repository.SongRepo;
import com.joysistvi.recordingapp.repository.SongRepoImpl;
import java.util.List;

public class SongService {

    private final SongRepo songRepo;

    public SongService() {
        songRepo = new SongRepoImpl();
    }
    public List<Song> getAllSongs() {
        return songRepo.getAllSongs();
    }
    public boolean createSong(Song song) {
        return songRepo.createSong(song);
    }
    public boolean updateSong(Song song) {
        return songRepo.updateSong(song);
    }
    public boolean deleteSong(int id) {
        return songRepo.deleteSong(id);
    }
    public boolean archiveSong(int id) {
        return songRepo.archiveSong(id);
    }
    public boolean restoreSong(int id) {
        return songRepo.restoreSong(id);
    }
}