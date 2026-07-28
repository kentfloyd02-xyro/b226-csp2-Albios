/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.controller;

import com.joysistvi.recordingapp.model.Playlist;
import com.joysistvi.recordingapp.service.PlaylistService;
import java.util.List;

public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController() {
        playlistService = new PlaylistService();
    }

    public List<Playlist> listPlaylists() {
        return playlistService.getAllPlaylists();
    }

    public Playlist checkPlaylistId(int id) {
        return playlistService.checkPlaylistId(id);
    }

    public boolean createPlaylist(Playlist playlist) {
        return playlistService.createPlaylist(playlist);
    }

    public boolean updatePlaylist(Playlist playlist) {
        return playlistService.updatePlaylist(playlist);
    }

    public boolean deletePlaylist(int id) {
        return playlistService.deletePlaylist(id);
    }
}
