/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.config.model.Song;
import java.util.List;


public interface SongRepo {
    
    List<Song> getAllSongs();
    boolean createSong(Song song);
    boolean updateSong(Song song);
    boolean deleteSong(int id);
    boolean archiveSong(int id);
    boolean restoreSong(int id);
}
