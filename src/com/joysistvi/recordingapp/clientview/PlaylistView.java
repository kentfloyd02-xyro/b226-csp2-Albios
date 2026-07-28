/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.clientview;

import com.joysistvi.recordingapp.controller.PlaylistController;
import com.joysistvi.recordingapp.controller.SongController;
import com.joysistvi.recordingapp.controller.UserController;
import com.joysistvi.recordingapp.model.Playlist;
import com.joysistvi.recordingapp.model.PlaylistSong;
import com.joysistvi.recordingapp.model.Song;
import com.joysistvi.recordingapp.model.User;

import static com.joysistvi.recordingapp.utils.ClearScreen.clearScreen;
import static com.joysistvi.recordingapp.utils.Scan.scanner;
import java.util.List;

public class PlaylistView {

    private final PlaylistController playlistController;
    private final SongController songController;
    private final UserController userController;

    public PlaylistView(PlaylistController playlistController, SongController songController, UserController userController) {
        this.playlistController = playlistController;
        this.songController = songController;
        this.userController = userController;
    }

    public void dashboard() {

        while (true) {
            clearScreen();
            System.out.println("===== MY PLAYLISTS =====");
            System.out.println("1. View My Playlists");
            System.out.println("2. Create Playlist");
            System.out.println("3. Delete Playlist");
            System.out.println("4. View Songs in Playlist");
            System.out.println("5. Add Song to Playlist");
            System.out.println("6. Remove Song from Playlist");
            System.out.println("7. Back");
            System.out.print("Choose: ");

            if (!scanner.hasNextInt()) {
                System.out.println("NUMBER ONLY!");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    viewPlaylists();
                    break;

                case 2:
                    createPlaylist();
                    break;

                case 3:
                    deletePlaylist();
                    break;

                case 4:
                    viewSongsInPlaylist();
                    break;

                case 5:
                    addSongToPlaylist();
                    break;

                case 6:
                    removeSongFromPlaylist();
                    break;

                case 7:
                    return;

                default:
                    System.out.println("Invalid Input!");
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private void viewPlaylists() {

        if (playlistController.listPlaylists().isEmpty()) {
            System.out.println("No playlists found.");
            return;
        }

        System.out.println("+----+--------------------------+----------------------+---------+");
        System.out.printf("| %-2s | %-24s | %-20s | %-7s |%n",
                "ID", "Playlist", "Created At", "User");
        System.out.println("+----+--------------------------+----------------------+---------+");

        for (Playlist playlist : playlistController.listPlaylists()) {

            System.out.printf("| %-2d | %-24s | %-20s | %-7d |%n",
                    playlist.getId(),
                    playlist.getPlaylistName(),
                    playlist.getCreatedAt(),
                    playlist.getUserId());

        }

        System.out.println("+----+--------------------------+----------------------+---------+");
    }

    private void createPlaylist() {

        String playlistName;

        while (true) {

            System.out.print("Playlist Name: ");
            playlistName = scanner.nextLine().trim();

            if (!playlistName.isEmpty()) {
                break;
            }

            System.out.println("Playlist name cannot be empty.");
        }

        String createdAt;

        while (true) {

            System.out.print("Created At (YYYY-MM-DD): ");
            createdAt = scanner.nextLine().trim();

            if (createdAt.matches("\\d{4}-\\d{2}-\\d{2}")) {
                break;
            }

            System.out.println("Invalid date format.");
        }

        int userId;

        while (true) {

            System.out.print("User ID: ");

            if (!scanner.hasNextInt()) {
                System.out.println("NUMBER ONLY!");
                scanner.nextLine();
                continue;
            }

            userId = scanner.nextInt();
            scanner.nextLine();

            if (userId <= 0) {
                System.out.println("User ID must be greater than 0.");
                continue;
            }

            User user = userController.checkUserId(userId);

            if (user == null) {
                System.out.println("User ID does not exist. Please enter a valid User ID.");
                continue;
            }

            break;
        }

        Playlist playlist = new Playlist(
                playlistName,
                createdAt,
                userId
        );

        if (playlistController.createPlaylist(playlist)) {
            System.out.println("Playlist created successfully.");
        } else {
            System.out.println("Failed to create playlist.");
        }

    }

    private void deletePlaylist() {

        int playlistId;

        while (true) {

            System.out.print("Enter Playlist ID: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Playlist ID must be a number.");
                scanner.nextLine();
                continue;
            }

            playlistId = scanner.nextInt();
            scanner.nextLine();

            Playlist playlist = playlistController.checkPlaylistId(playlistId);

            if (playlist != null) {
                break;
            }

            System.out.println("Playlist does not exist.");
        }

        while (true) {

            System.out.print("Are you sure? (YES/NO): ");
            String answer = scanner.nextLine().trim().toUpperCase();

            switch (answer) {

                case "YES":

                    if (playlistController.deletePlaylist(playlistId)) {
                        System.out.println("Playlist deleted successfully.");
                    } else {
                        System.out.println("Failed to delete playlist.");
                    }
                    return;

                case "NO":
                    System.out.println("Delete cancelled.");
                    return;

                default:
                    System.out.println("Please enter YES or NO.");
            }
        }
    }

    private void viewSongsInPlaylist() {

        int playlistId;

        while (true) {

            System.out.print("Enter Playlist ID: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Playlist ID must be a number.");
                scanner.nextLine();
                continue;
            }

            playlistId = scanner.nextInt();
            scanner.nextLine();

            Playlist playlist = playlistController.checkPlaylistId(playlistId);

            if (playlist != null) {
                break;
            }

            System.out.println("Playlist does not exist.");
        }

        List<PlaylistSong> songs = playlistController.getSongsInPlaylist(playlistId);

        if (songs.isEmpty()) {
            System.out.println("This playlist has no songs.");
            return;
        }

        System.out.println("\nPlaylist : " + songs.get(0).getPlaylistName());

        System.out.println("+----+------------------------------+----------------+----------+");
        System.out.printf("| %-2s | %-28s | %-14s | %-8s |%n",
                "ID", "Title", "Genre", "Length");
        System.out.println("+----+------------------------------+----------------+----------+");

        for (PlaylistSong song : songs) {

            System.out.printf("| %-2d | %-28s | %-14s | %-8s |%n",
                    song.getSongId(),
                    song.getSongTitle(),
                    song.getSongGenre(),
                    song.getSongLength());

        }

        System.out.println("+----+------------------------------+----------------+----------+");
    }

    private void addSongToPlaylist() {

        int playlistId;

        while (true) {

            System.out.print("Playlist ID: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Playlist ID must be a number.");
                scanner.nextLine();
                continue;
            }

            playlistId = scanner.nextInt();
            scanner.nextLine();

            if (playlistController.checkPlaylistId(playlistId) != null) {
                break;
            }

            System.out.println("Playlist does not exist.");
        }

        int songId;

        while (true) {

            System.out.print("Song ID: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Song ID must be a number.");
                scanner.nextLine();
                continue;
            }

            songId = scanner.nextInt();
            scanner.nextLine();

            if (songController.checkSongId(songId) == null) {
                System.out.println("Song does not exist.");
                continue;
            }

            if (playlistController.songAlreadyExists(playlistId, songId)) {
                System.out.println("Song already exists in this playlist.");
                return;
            }

            break;
        }

        if (playlistController.addSongToPlaylist(playlistId, songId)) {
            System.out.println("Song added successfully.");
        } else {
            System.out.println("Failed to add song.");
        }
    }

    private void removeSongFromPlaylist() {

        int playlistId;

        while (true) {

            System.out.print("Playlist ID: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Playlist ID must be a number.");
                scanner.nextLine();
                continue;
            }

            playlistId = scanner.nextInt();
            scanner.nextLine();

            if (playlistController.checkPlaylistId(playlistId) != null) {
                break;
            }

            System.out.println("Playlist does not exist.");
        }

        int songId;

        while (true) {

            System.out.print("Song ID: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Song ID must be a number.");
                scanner.nextLine();
                continue;
            }

            songId = scanner.nextInt();
            scanner.nextLine();

            if (songController.checkSongId(songId) != null) {
                break;
            }

            System.out.println("Song does not exist.");
        }

        if (playlistController.removeSongFromPlaylist(playlistId, songId)) {
            System.out.println("Song removed successfully.");
        } else {
            System.out.println("Song is not in this playlist.");
        }
    }
}
