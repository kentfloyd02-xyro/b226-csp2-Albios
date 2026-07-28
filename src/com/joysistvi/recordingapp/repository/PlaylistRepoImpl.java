/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.config.dbconnection;
import com.joysistvi.recordingapp.model.Playlist;
import com.joysistvi.recordingapp.model.PlaylistSong;

import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistRepoImpl implements PlaylistRepo {

    private final dbconnection db = new dbconnection();

    @Override
    public List<Playlist> getAllPlaylists() {

        List<Playlist> playlists = new ArrayList<>();

        String query = "SELECT playlist_id, playlist_name, created_at, user_id "
                + "FROM playlists "
                + "ORDER BY playlist_name";

        try (Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query); ResultSet rs = prep.executeQuery()) {

            while (rs.next()) {

                Playlist playlist = new Playlist();

                playlist.setId(rs.getInt("playlist_id"));
                playlist.setPlaylistName(rs.getString("playlist_name"));
                playlist.setCreatedAt(rs.getString("created_at"));
                playlist.setUserId(rs.getInt("user_id"));

                playlists.add(playlist);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playlists;
    }

    @Override
    public Playlist checkPlaylistId(int id) {

        String query = "SELECT * FROM playlists WHERE playlist_id=?";

        try (
                Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            ResultSet rs = prep.executeQuery();

            if (rs.next()) {

                Playlist playlist = new Playlist();

                playlist.setId(rs.getInt("playlist_id"));
                playlist.setPlaylistName(rs.getString("playlist_name"));
                playlist.setCreatedAt(rs.getString("created_at"));
                playlist.setUserId(rs.getInt("user_id"));

                return playlist;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public boolean createPlaylist(Playlist playlist) {

        String query
                = "INSERT INTO playlists(playlist_name,created_at,user_id) VALUES(?,?,?)";

        try (
                Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, playlist.getPlaylistName());
            prep.setString(2, playlist.getCreatedAt());
            prep.setInt(3, playlist.getUserId());

            return prep.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updatePlaylist(Playlist playlist) {

        String query
                = "UPDATE playlists "
                + "SET playlist_name=?, created_at=?, user_id=? "
                + "WHERE playlist_id=?";

        try (
                Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, playlist.getPlaylistName());
            prep.setString(2, playlist.getCreatedAt());
            prep.setInt(3, playlist.getUserId());
            prep.setInt(4, playlist.getId());

            return prep.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deletePlaylist(int id) {

        try (Connection conn = db.connect()) {

            conn.setAutoCommit(false);

            PreparedStatement prep = conn.prepareStatement("DELETE FROM playlist_songs WHERE playlist_id=?");

            prep.setInt(1, id);
            prep.executeUpdate();

            PreparedStatement prep2 = conn.prepareStatement("DELETE FROM playlists WHERE playlist_id=?");

            prep2.setInt(1, id);

            boolean deleted = prep2.executeUpdate() > 0;

            conn.commit();

            return deleted;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean addSongToPlaylist(int playlistId, int songId) {

        String query = "INSERT INTO playlist_songs(playlist_id,song_id) VALUES(?,?)";

        try (
                Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, playlistId);
            prep.setInt(2, songId);

            return prep.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    @Override
    public boolean removeSongFromPlaylist(int playlistId, int songId) {

        String query = "DELETE FROM playlist_songs WHERE playlist_id=? AND song_id=?";

        try (
                Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, playlistId);
            prep.setInt(2, songId);

            return prep.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    @Override
    public boolean songAlreadyExists(int playlistId, int songId) {

        String query = "SELECT * FROM playlist_songs WHERE playlist_id=? AND song_id=?";

        try (
                Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, playlistId);
            prep.setInt(2, songId);

            ResultSet rs = prep.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    @Override
    public List<PlaylistSong> getSongsInPlaylist(int playlistId) {

        List<PlaylistSong> songs = new ArrayList<>();

        String query = "SELECT p.playlist_name,"
                + " s.song_id,"
                + " s.song_title,"
                + " s.song_genre,"
                + " s.song_length "
                + "FROM playlist_songs ps "
                + "JOIN playlists p ON ps.playlist_id=p.playlist_id "
                + "JOIN songs s ON ps.song_id=s.song_id "
                + "WHERE ps.playlist_id=? "
                + "ORDER BY s.song_title";

        try (
                Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, playlistId);

            ResultSet rs = prep.executeQuery();

            while (rs.next()) {

                PlaylistSong ps = new PlaylistSong();

                ps.setPlaylistName(rs.getString("playlist_name"));
                ps.setSongId(rs.getInt("song_id"));
                ps.setSongTitle(rs.getString("song_title"));
                ps.setSongGenre(rs.getString("song_genre"));
                ps.setSongLength(rs.getString("song_length"));

                songs.add(ps);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return songs;

    }
}
