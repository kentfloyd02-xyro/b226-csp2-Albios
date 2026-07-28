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

        String query = "SELECT "
                + "s.song_id, "
                + "s.song_title, "
                + "s.song_genre, "
                + "s.song_length, "
                + "a.album_id, "
                + "a.album_title, "
                + "ar.artist_name "
                + "FROM songs s "
                + "JOIN albums a ON s.album_id = a.album_id "
                + "JOIN artists ar ON a.artist_id = ar.artist_id "
                + "ORDER BY a.album_id, s.song_id";

        try (
                Connection conn = db.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {

                Song song = new Song();

                song.setId(rs.getInt("song_id"));
                song.setTitle(rs.getString("song_title"));
                song.setGenre(rs.getString("song_genre"));
                song.setLength(rs.getString("song_length"));
                song.setAlbum_id(rs.getInt("album_id"));

                song.setAlbumName(rs.getString("album_title"));
                song.setArtistName(rs.getString("artist_name"));

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
                Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {

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

    @Override
    public boolean updateSong(Song song) {

        String query = "UPDATE songs SET song_title=?, song_genre=?, song_length=?, album_id=? WHERE song_id=?";

        try (
                Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {

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

    @Override
    public boolean deleteSong(int id) {

        String query = "DELETE FROM songs WHERE song_id=?";

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
    public boolean archiveSong(int id) {

        String query = "UPDATE songs SET archived = 1 WHERE song_id=?";

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
    public boolean restoreSong(int id) {

        String query = "UPDATE songs SET archived = 0 WHERE song_id=?";

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
    public Song checkSongId(int id) {

        String query
                = "SELECT "
                + "s.song_id, "
                + "s.song_title, "
                + "s.song_genre, "
                + "s.song_length, "
                + "a.album_id, "
                + "a.album_title, "
                + "ar.artist_name "
                + "FROM songs s "
                + "JOIN albums a ON s.album_id = a.album_id "
                + "JOIN artists ar ON a.artist_id = ar.artist_id "
                + "WHERE s.song_id = ? "
                + "ORDER BY a.album_id, s.song_id";

        try (
                Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            ResultSet result = prep.executeQuery();

            if (result.next()) {

                Song song = new Song();

                song.setId(result.getInt("song_id"));
                song.setTitle(result.getString("song_title"));
                song.setGenre(result.getString("song_genre"));
                song.setLength(result.getString("song_length"));
                song.setAlbum_id(result.getInt("album_id"));

                song.setAlbumName(result.getString("album_title"));
                song.setArtistName(result.getString("artist_name"));

                return song;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
