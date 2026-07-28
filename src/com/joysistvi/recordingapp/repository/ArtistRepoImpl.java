/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.config.dbconnection;
import com.joysistvi.recordingapp.model.Artist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArtistRepoImpl implements ArtistRepo {

    private final dbconnection db = new dbconnection();

    @Override
    public List<Artist> getAllArtist() {

        List<Artist> artists = new ArrayList<>();

        String sql = "SELECT * FROM artits";

        try (
                Connection conn = db.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Artist artist = new Artist(
                        rs.getInt("artits_id"),
                        rs.getString("artits_name")
                );

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return artists;
    }

    @Override
    public boolean createArtist(Artist artist) {

        String query = "INSERT INTO artists (artist_name) VALUES(?)";

        try (
                Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, artist.getName());

            return prep.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateArtist(Artist artist) {

        String query = "UPDATE artists SET artist_name = ? WHERE artist_id";

        try (
                Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, artist.getName());

            return prep.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteArtist(int id) {

        String query = "DELETE FROM artists WHERE artist_id=?";

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
    public Artist checkArtistId(int id) {
        String query = "SELECT * FROM artists WHERE artist_id=?";

        try (Connection conn = db.connect(); PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            ResultSet result = prep.executeQuery();

            if (result.next()) {

                return new Artist(
                        result.getInt("artist_id"),
                        result.getString("artist_name")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
