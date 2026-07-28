/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.config.dbconnection;
import com.joysistvi.recordingapp.model.Playlist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistRepoImpl implements PlaylistRepo {

    private final dbconnection db = new dbconnection();

    @Override
    public List<Playlist> getAllPlaylists() {

        List<Playlist> playlists = new ArrayList<>();

        String sql = "SELECT\n"
                + "    p.playlist_id,\n"
                + "    p.created_at,\n"
                + "    p.song_id,\n"
                + "    s.title\n"
                + "FROM playlists p\n"
                + "JOIN songs s\n"
                + "ON p.song_id = s.song_id\n"
                + "ORDER BY p.playlist_id;";

        try (
                Connection conn = db.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Playlist playlist = new Playlist();

                playlist.setId(rs.getInt("playlist_id"));
                playlist.setCreated_at(rs.getString("created_at"));
                playlist.setSong_id(rs.getInt("song_id"));
                playlist.setSongTitle(rs.getString("title"));

                playlists.add(playlist);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return playlists;
    }

    @Override
    public boolean createPlaylist(Playlist playlist) {

        String query = "INSERT INTO playlists(created_at,song_id) VALUES(?,?)";

        try (
                Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, playlist.getCreated_at());
            prep.setInt(2, playlist.getSong_id());

            return prep.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updatePlaylist(Playlist playlist) {

        String query = "UPDATE playlists SET created_at=?, song_id=? WHERE playlist_id=?";

        try (
                Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, playlist.getCreated_at());
            prep.setInt(2, playlist.getSong_id());
            prep.setInt(3, playlist.getId());

            return prep.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deletePlaylist(int id) {

        String query = "DELETE FROM playlists WHERE playlist_id=?";

        try (
                Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            return prep.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Playlist checkPlaylistId(int id) {

        String query
                = "SELECT p.playlist_id, p.created_at, p.song_id, s.title "
                + "FROM playlists p "
                + "JOIN songs s ON p.song_id = s.song_id "
                + "WHERE p.playlist_id = ?";

        try (
                Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            ResultSet rs = prep.executeQuery();

            if (rs.next()) {

                Playlist playlist = new Playlist();

                playlist.setId(rs.getInt("playlist_id"));
                playlist.setCreated_at(rs.getString("created_at"));
                playlist.setSong_id(rs.getInt("song_id"));
                playlist.setSongTitle(rs.getString("title"));

                return playlist;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
