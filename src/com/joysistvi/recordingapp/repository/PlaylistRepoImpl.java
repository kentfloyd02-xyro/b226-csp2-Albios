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

        String sql = "SELECT * FROM playlists";

        try (
                Connection conn = db.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Playlist playlist = new Playlist(
                                                rs.getString("created_at"),
                        rs.getInt("playlist_id")
                );

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
                Connection conn = db.connect();
                PreparedStatement prep = conn.prepareStatement(query)) {

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
                Connection conn = db.connect();
                PreparedStatement prep = conn.prepareStatement(query)) {

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
                Connection conn = db.connect();
                PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            return prep.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Playlist checkPlaylistId(int id) {

        String query = "SELECT * FROM playlists WHERE playlist_id=?";

        try (
                Connection conn = db.connect();
                PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            ResultSet rs = prep.executeQuery();

            if (rs.next()) {

                return new Playlist(
                                                rs.getString("created_at"),
                        rs.getInt("playlist_id")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean truncatePlaylist() {

        String query = "TRUNCATE TABLE playlists";

        try (
                Connection conn = db.connect();
                PreparedStatement prep = conn.prepareStatement(query)) {

            prep.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}