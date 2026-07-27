/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.model.Playlist;
import java.util.List;

public interface PlaylistRepo {

    List<Playlist> getAllPlaylists();

    Playlist checkPlaylistId(int id);

    boolean createPlaylist(Playlist playlist);

    boolean updatePlaylist(Playlist playlist);

    boolean deletePlaylist(int id);

    boolean truncatePlaylist();
}