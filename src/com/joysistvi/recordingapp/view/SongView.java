/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joysistvi.recordingapp.view;

import com.joysistvi.recordingapp.controller.SongController;
import com.joysistvi.recordingapp.model.Song;
import static com.joysistvi.recordingapp.utils.ClearScreen.clearScreen;
import static com.joysistvi.recordingapp.utils.Scan.scanner;

public class SongView {

    private final SongController songController;

    public SongView(SongController songController) {
        this.songController = songController;
    }

    public void dashboard() {

        boolean running = true;

        while (running) {
            clearScreen();
            System.out.println("\n=== Admin Dashboard ===");
            System.out.println("1. Add Song");
            System.out.println("2. View Song");
            System.out.println("3. Update Song");
            System.out.println("4. Delete Song");
            System.out.println("5. Archive Song");
            System.out.println("6. Restore Song");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            if (!scanner.hasNextInt()) {
                System.out.println("NUMBER ONLY, TRY AGAIN!");

                scanner.nextLine();
                continue;
            }

            int input = scanner.nextInt();
            scanner.nextLine();

            switch (input) {
                case 1:
                    addSong();
                    break;
                case 2:
                    viewSong();
                    break;
                case 3:
                    updateSong();
                    break;
                case 4:
                    deleteSong();
                    break;
                case 5:
                    archiveSong();
                    break;
                case 6:
                    restoreSong();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid Input!");
            }
        }
    }

    private void addSong() {

        System.out.print("Enter Song Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine();

        System.out.print("Enter Length (HH:MM:SS): ");
        String length = scanner.nextLine();

        System.out.print("Enter Album ID: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Album ID must be a number.");
            scanner.nextLine();
            return;
        }

        int albumId = scanner.nextInt();
        scanner.nextLine();

        Song newSong = new Song(title, length, genre, albumId);

        if (songController.createSong(newSong)) {
            System.out.println("Song added successfully!");
        } else {
            System.out.println("Failed to add song.");
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    private void viewSong() {

        while (true) {
            System.out.println("=== VIEW ===");
            System.out.println("1. VIEW ALL SONG");
            System.out.println("2. VIEW SPECIFIC COLUMN SONG");
            System.out.println("3. BACK");
            System.out.print("Choose an option: ");

            if (!scanner.hasNextInt()) {
                System.out.println("NUMBER ONLY!");
                scanner.nextLine();
                continue;
            }

            int view = scanner.nextInt();
            scanner.nextLine();

            switch (view) {
                case 1:
                    if (songController.listSongs().isEmpty()) {
                        System.out.println("Song not found");
                    } else {
                        System.out.println("+----+------------------------------+----------+----------+----------+");
                        System.out.printf("| %-2s | %-28s | %-8s | %-8s | %-8s |%n",
                                "ID", "Title", "Genre", "Length", "Album ID");
                        System.out.println("+----+------------------------------+----------+----------+----------+");

                        for (Song song : songController.listSongs()) {

                            System.out.printf("| %-2d | %-28s | %-8s | %-8s | %-8d |%n",
                                    song.getId(),
                                    song.getTitle(),
                                    song.getGenre(),
                                    song.getLength(),
                                    song.getAlbum_id());
                        }

                        System.out.println("+----+------------------------------+----------+----------+----------+");
                    }
                    break;

                case 2:
                    System.out.print("Enter Song ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    Song song = songController.checkSongId(id);

                    if (song == null) {
                        System.out.println("Song not found.");
                    } else {
                        System.out.println("+----+------------------------------+----------+----------+----------+");
                        System.out.printf("| %-2s | %-28s | %-8s | %-8s | %-8s |%n",
                                "ID", "Title", "Genre", "Length", "Album ID");
                        System.out.println("+----+------------------------------+----------+----------+----------+");

                        System.out.printf("| %-2d | %-28s | %-8s | %-8s | %-8d |%n",
                                song.getId(),
                                song.getTitle(),
                                song.getGenre(),
                                song.getLength(),
                                song.getAlbum_id());

                        System.out.println("+----+------------------------------+----------+----------+----------+");
                    }
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Invalid Input!");
            }
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
        }
    }

    private void updateSong() {
        System.out.print("Enter Song ID: ");
        int updateId = scanner.nextInt();
        scanner.nextLine();

        Song song = songController.checkSongId(updateId);

        if (song == null) {
            System.out.println("Song not found.");
            return;
        }

        System.out.print("New Title: ");
        String newTitle = scanner.nextLine();

        System.out.print("New Genre: ");
        String newGenre = scanner.nextLine();

        System.out.print("New Length: ");
        String newLength = scanner.nextLine();

        System.out.print("New Album ID: ");
        int newAlbumId = scanner.nextInt();
        scanner.nextLine();

        Song updatedSong = new Song(updateId, newTitle, newLength, newGenre, newAlbumId);

        if (songController.updateSong(updatedSong)) {
            System.out.println("Song updated successfully.");
        } else {
            System.out.println("Failed to update song.");
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    private void deleteSong() {

        while (true) {
            System.out.println("=== DELETE ===");
            System.out.println("1. DELETE A SONG");
            System.out.println("2. TRUNCATE THE TABLE");
            System.out.println("3. BACK");

            if (!scanner.hasNextInt()) {
                System.out.println("NUMBER ONLY!");
                scanner.nextLine();
                continue;
            }

            int delete = scanner.nextInt();
            scanner.nextLine();

            switch (delete) {
                case 1:

                    System.out.print("Enter Song ID to delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();

                    Song deleteSong = songController.checkSongId(deleteId);

                    if (deleteSong == null) {
                        System.out.println("Song not found.");
                        break;
                    }

                    System.out.print("Are you sure? (YES/NO): ");
                    String confirm = scanner.nextLine();

                    if (confirm.equalsIgnoreCase("YES")) {

                        if (songController.deleteSong(deleteId)) {
                            System.out.println("Song deleted successfully.");
                        } else {
                            System.out.println("Failed to delete song.");
                        }

                    } else {
                        System.out.println("Delete cancelled.");
                    }

                    break;

                case 2:

                    System.out.println("WARNING: This will delete ALL songs from the table.");
                    System.out.print("ARE YOU SURE? (YES/NO): ");
                    String answer = scanner.nextLine();

                    if (answer.equalsIgnoreCase("YES")) {

                        if (songController.TruncateSong()) {
                            System.out.println("All songs deleted successfully.");
                        } else {
                            System.out.println("Failed to truncate table.");
                        }

                    } else {
                        System.out.println("Truncate cancelled.");
                    }

                    break;

                case 3:
                    return;

                default:
                    System.out.println("Invalid Input");
            }
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
        }
    }

    private void archiveSong() {
        System.out.print("Enter Song ID to archive: ");
        int archiveId = scanner.nextInt();
        scanner.nextLine();

        Song archiveSong = songController.checkSongId(archiveId);

        if (archiveSong == null) {
            System.out.println("Song not found.");
            return;
        }

        if (songController.archiveSong(archiveId)) {
            System.out.println("Song archived successfully.");
        } else {
            System.out.println("Failed to archive song.");
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    private void restoreSong() {
        System.out.print("Enter Song ID to restore: ");

        int restoreId = scanner.nextInt();
        scanner.nextLine();

        Song restoreSong = songController.checkSongId(restoreId);

        if (restoreSong == null) {
            System.out.println("Song not found.");
            return;
        }

        if (songController.restoreSong(restoreId)) {
            System.out.println("Song restored successfully.");
        } else {
            System.out.println("Failed to restore song.");
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

}
