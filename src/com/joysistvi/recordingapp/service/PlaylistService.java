/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Playlist;
import com.joysistvi.recordingapp.model.PlaylistSong;
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

    public boolean deletePlaylist(int id) {
        return playlistRepo.deletePlaylist(id);
    }

    public boolean addSongToPlaylist(int playlistId, int songId) {
        return playlistRepo.addSongToPlaylist(playlistId, songId);
    }

    public boolean removeSongFromPlaylist(int playlistId, int songId) {
        return playlistRepo.removeSongFromPlaylist(playlistId, songId);
    }

    public boolean songAlreadyExists(int playlistId, int songId) {
        return playlistRepo.songAlreadyExists(playlistId, songId);
    }

    public List<PlaylistSong> getSongsInPlaylist(int playlistId) {
        return playlistRepo.getSongsInPlaylist(playlistId);
    }

}
