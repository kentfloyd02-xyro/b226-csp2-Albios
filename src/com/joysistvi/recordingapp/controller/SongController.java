/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joysistvi.recordingapp.controller;

import com.joysistvi.recordingapp.config.model.Song;
import com.joysistvi.recordingapp.service.SongService;
import java.util.List;

public class SongController {

    private final SongService songService;

    public SongController() {
        songService = new SongService();
    }

    public List<Song> listSongs() {
        return songService.getAllSongs();
    }

    public boolean createSong(Song song) {
        return songService.createSong(song);
    }

    public boolean updateSong(Song song) {
        return songService.updateSong(song);
    }

    public boolean deleteSong(int id) {
        return songService.deleteSong(id);
    }

    public boolean archiveSong(int id) {
        return songService.archiveSong(id);
    }

    public boolean restoreSong(int id) {
        return songService.restoreSong(id);
    }
}
