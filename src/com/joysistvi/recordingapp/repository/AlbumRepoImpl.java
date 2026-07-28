/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.config.dbconnection;
import com.joysistvi.recordingapp.model.Album;

import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumRepoImpl implements AlbumRepo {

    private final dbconnection db = new dbconnection();

    @Override
    public List<Album> getAllAlbums() {

        List<Album> albums = new ArrayList<>();

        String query = "SELECT a.album_id, a.album_title, a.album_year, a.artist_id, ar.artist_name "
                + "FROM albums a "
                + "JOIN artists ar ON a.artist_id = ar.artist_id "
                + "ORDER BY a.album_id";

         try (Connection conn = db.connect();PreparedStatement prep = conn.prepareStatement(query); ResultSet rs = prep.executeQuery()) {

        while (rs.next()) {

            Album album = new Album();

            album.setId(rs.getInt("album_id"));
            album.setTitle(rs.getString("album_title"));
            album.setYear(rs.getInt("album_year"));
            album.setArtist_id(rs.getInt("artist_id"));
            album.setArtistName(rs.getString("artist_name"));

            albums.add(album);
        }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return albums;
    }

    @Override
    public boolean createAlbum(Album album) {

        String query = "INSERT INTO albums(album_title, album_year, artist_id) VALUES(?,?,?)";

        try (
                Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, album.getTitle());
            prep.setInt(2, album.getYear());
            prep.setInt(3, album.getArtist_id());

            return prep.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateAlbum(Album album) {

        String query = "UPDATE albums SET album_title=?, album_year=?, artist_id=? WHERE album_id=?";

        try (
                Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, album.getTitle());
            prep.setInt(2, album.getYear());
            prep.setInt(3, album.getArtist_id());
            prep.setInt(4, album.getId());

            return prep.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteAlbum(int id) {

        String query = "DELETE FROM albums WHERE album_id=?";

        try (
                Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            return prep.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Album checkAlbumId(int id) {

        String query = "SELECT a.album_id, a.album_title, a.album_year, a.artist_id, ar.artist_name "
                + "FROM albums a "
                + "JOIN artists ar ON a.artist_id = ar.artist_id "
                + "WHERE a.album_id = ?";

        try (
                Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            ResultSet rs = prep.executeQuery();

            if (rs.next()) {
                Album album = new Album(
                        rs.getInt("album_id"),
                        rs.getString("album_title"),
                        rs.getInt("album_year"),
                        rs.getInt("artist_id")
                );

                album.setArtistName(rs.getString("artist_name"));
                return album;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
