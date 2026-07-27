/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.config.dbconnection;
import com.joysistvi.recordingapp.model.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SongRepoImpl implements SongRepo {

    private final dbconnection db = new dbconnection();

    @Override
    public List<Song> getAllSongs() {

        List<Song> songs = new ArrayList<>();

        String sql = "SELECT * FROM songs";

        try (
                Connection conn = db.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Song song = new Song(
                        rs.getInt("song_id"),
                        rs.getString("song_title"),
                        rs.getString("song_length"),
                        rs.getString("song_genre"),                     
                        rs.getInt("album_id")
                );

                songs.add(song);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return songs;
    }

    @Override
    public boolean createSong(Song song) {

        String query = "INSERT INTO songs(song_title,song_genre,song_length,album_id) VALUES(?,?,?,?)";

        try (
                Connection conn = db.connect();
                PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, song.getTitle());
            prep.setString(2, song.getGenre());
            prep.setString(3, song.getLength());
            prep.setInt(4, song.getAlbum_id());

            return prep.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateSong(Song song) {

        String query = "UPDATE songs SET song_title=?, song_genre=?, song_length=?, album_id=? WHERE song_id=?";

        try (
                Connection conn = db.connect();
                PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, song.getTitle());
            prep.setString(2, song.getGenre());
            prep.setString(3, song.getLength());
            prep.setInt(4, song.getAlbum_id());
            prep.setInt(5, song.getId());

            return prep.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteSong(int id) {

        String query = "DELETE FROM songs WHERE song_id=?";

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

    public boolean archiveSong(int id) {

        String query = "UPDATE songs SET archived = 1 WHERE song_id=?";

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

    public boolean restoreSong(int id) {

        String query = "UPDATE songs SET archived = 0 WHERE song_id=?";

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
    
    public Song checkSongId(int id) {
        String query = "SELECT song_id, song_title,song_length,song_genre,album_id FROM songs WHERE song_id = ?";

        try (Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);
            
            ResultSet result = prep.executeQuery();

            if (result.next()) {

            return new Song(
                    result.getInt("song_id"),
                    result.getString("song_title"),
                    result.getString("song_length"),
                    result.getString("song_genre"),
                    result.getInt("album_id")
            );
        }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
     public boolean TruncateSong() {
        String query = "TRUNCATE TABLE songs";

        try (Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {
            prep.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}