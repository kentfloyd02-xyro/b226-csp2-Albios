/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.controller;

import com.joysistvi.recordingapp.model.Playlist;
import com.joysistvi.recordingapp.model.PlaylistSong;
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

    public boolean deletePlaylist(int id) {
        return playlistService.deletePlaylist(id);
    }

    public boolean addSongToPlaylist(int playlistId, int songId) {
        return playlistService.addSongToPlaylist(playlistId, songId);
    }

    public boolean removeSongFromPlaylist(int playlistId, int songId) {
        return playlistService.removeSongFromPlaylist(playlistId, songId);
    }

    public boolean songAlreadyExists(int playlistId, int songId) {
        return playlistService.songAlreadyExists(playlistId, songId);
    }

    public List<PlaylistSong> getSongsInPlaylist(int playlistId) {
        return playlistService.getSongsInPlaylist(playlistId);
    }

}
