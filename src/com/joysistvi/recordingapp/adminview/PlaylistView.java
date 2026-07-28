/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.adminview;

import com.joysistvi.recordingapp.controller.PlaylistController;
import com.joysistvi.recordingapp.model.Playlist;

import static com.joysistvi.recordingapp.utils.ClearScreen.clearScreen;
import static com.joysistvi.recordingapp.utils.Scan.scanner;

public class PlaylistView {

    private final PlaylistController playlistController;

    public PlaylistView(PlaylistController playlistController) {
        this.playlistController = playlistController;
    }

    public void dashboard() {

        while (true) {

            clearScreen();

            System.out.println("===== PLAYLIST MENU =====");
            System.out.println("1. Add Playlist");
            System.out.println("2. View Playlists");
            System.out.println("3. Update Playlist");
            System.out.println("4. Delete Playlist");
            System.out.println("5. Truncate Playlist");
            System.out.println("0. Back");
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
                    addPlaylist();
                    break;

                case 2:
                    viewPlaylists();
                    break;

                case 3:
                    updatePlaylist();
                    break;

                case 4:
                    deletePlaylist();
                    break;

                case 5:
                    truncatePlaylist();
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Invalid Input");
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private void addPlaylist() {

        System.out.print("Created At (YYYY-MM-DD): ");
        String createdAt = scanner.nextLine();

        System.out.print("Song ID: ");
        int songId = scanner.nextInt();
        scanner.nextLine();

        Playlist playlist = new Playlist(createdAt, songId);

        if (playlistController.createPlaylist(playlist)) {
            System.out.println("Playlist added successfully.");
        } else {
            System.out.println("Failed to add playlist.");
        }
    }

    private void viewPlaylists() {

        if (playlistController.listPlaylists().isEmpty()) {
            System.out.println("No playlists found.");
            return;
        }

        System.out.println("+----+---------------------+---------+");
        System.out.printf("| %-2s | %-19s | %-7s |%n",
                "ID", "Created At", "Song ID");
        System.out.println("+----+---------------------+---------+");

        for (Playlist playlist : playlistController.listPlaylists()) {

            System.out.printf("| %-2d | %-19s | %-7d |%n",
                    playlist.getId(),
                    playlist.getCreated_at(),
                    playlist.getSong_id());
        }

        System.out.println("+----+---------------------+---------+");
    }

    private void updatePlaylist() {

        System.out.print("Enter Playlist ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Playlist playlist = playlistController.checkPlaylistId(id);

        if (playlist == null) {
            System.out.println("Playlist not found.");
            return;
        }

        System.out.print("New Created At (YYYY-MM-DD): ");
        String createdAt = scanner.nextLine();

        System.out.print("New Song ID: ");
        int songId = scanner.nextInt();
        scanner.nextLine();

        Playlist updated = new Playlist(createdAt, id);

        if (playlistController.updatePlaylist(updated)) {
            System.out.println("Playlist updated successfully.");
        } else {
            System.out.println("Failed to update playlist.");
        }
    }

    private void deletePlaylist() {

        System.out.print("Enter Playlist ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Playlist playlist = playlistController.checkPlaylistId(id);

        if (playlist == null) {
            System.out.println("Playlist not found.");
            return;
        }

        if (playlistController.deletePlaylist(id)) {
            System.out.println("Playlist deleted successfully.");
        } else {
            System.out.println("Failed to delete playlist.");
        }
    }

    private void truncatePlaylist() {

        System.out.print("Are you sure? (YES/NO): ");
        String answer = scanner.nextLine();

        if (!answer.equalsIgnoreCase("YES")) {
            System.out.println("Cancelled.");
            return;
        }

        if (playlistController.truncatePlaylist()) {
            System.out.println("Playlist table truncated.");
        } else {
            System.out.println("Failed.");
        }
    }
}
