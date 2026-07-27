/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Playlist;
import com.joysistvi.recordingapp.repository.PlaylistRepo;
import com.joysistvi.recordingapp.repository.PlaylistRepoImpl;
import java.util.List;

public class PlaylistService {

    private final PlaylistRepo playlistRepo;

    public PlaylistService() {
        playlistRepo = new PlaylistRepoImpl();
    }

    public List<Playlist> getAllPlaylists() {
        return playlistRepo.getAllPlaylists();
    }

    public Playlist checkPlaylistId(int id) {
        return playlistRepo.checkPlaylistId(id);
    }

    public boolean createPlaylist(Playlist playlist) {
        return playlistRepo.createPlaylist(playlist);
    }

    public boolean updatePlaylist(Playlist playlist) {
        return playlistRepo.updatePlaylist(playlist);
    }

    public boolean deletePlaylist(int id) {
        return playlistRepo.deletePlaylist(id);
    }

    public boolean truncatePlaylist() {
        return playlistRepo.truncatePlaylist();
    }
}
