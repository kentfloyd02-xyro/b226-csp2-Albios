/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joysistvi.recordingapp.adminview;

import com.joysistvi.recordingapp.controller.AlbumController;
import com.joysistvi.recordingapp.controller.SongController;
import com.joysistvi.recordingapp.model.Album;
import com.joysistvi.recordingapp.model.Song;
import static com.joysistvi.recordingapp.utils.ClearScreen.clearScreen;
import static com.joysistvi.recordingapp.utils.Scan.scanner;
import java.util.List;

public class SongView {

    private final SongController songController;
    private final AlbumController albumController;

    public SongView(SongController songController, AlbumController albumController) {
        this.songController = songController;
        this.albumController = albumController;
    }

    public void dashboard() {

        while (true) {
            clearScreen();
            System.out.println("\n=== Song Menu ===");
            System.out.println("1. Add Song");
            System.out.println("2. View Song");
            System.out.println("3. Search Song");
            System.out.println("4. Update Song");
            System.out.println("5. Delete Song");
            System.out.println("6. Archive Song");
            System.out.println("7. Restore Song");
            System.out.println("8. BACK");
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
                    searchSong();
                    break;

                case 4:
                    updateSong();
                    break;

                case 5:
                    deleteSong();
                    break;

                case 6:
                    archiveSong();
                    break;

                case 7:
                    restoreSong();
                    break;

                case 8:
                    return;

                default:
                    System.out.println("Invalid Input!");
            }
        }
    }

    private void addSong() {

        while (true) {
            System.out.println("=== ADD SONG ===");
            System.out.println("1. ADD SONG");
            System.out.println("2. BACK");
            System.out.print("Choose an option: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Album ID must be a number.");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    String title;
                    while (true) {
                        System.out.print("Enter Song Title: ");
                        title = scanner.nextLine().trim();

                        if (!title.isEmpty()) {
                            break;
                        }

                        System.out.println("Song title cannot be empty.");
                    }

                    String genre;
                    while (true) {
                        System.out.print("Enter Genre: ");
                        genre = scanner.nextLine().trim();

                        if (!genre.isEmpty()) {
                            break;
                        }

                        System.out.println("Genre cannot be empty.");
                    }

                    String length;
                    while (true) {
                        System.out.print("Enter Length (HH:MM:SS): ");
                        length = scanner.nextLine().trim();

                        if (length.matches("^\\d{2}:\\d{2}:\\d{2}$")) {

                            String[] time = length.split(":");

                            int hours = Integer.parseInt(time[0]);
                            int minutes = Integer.parseInt(time[1]);
                            int seconds = Integer.parseInt(time[2]);

                            if (minutes <= 59 && seconds <= 59) {
                                break;
                            }
                        }

                        System.out.println("Invalid length. Format must be HH:MM:SS (Example: 00:03:45)");
                    }

                    int albumId;

                    while (true) {

                        System.out.print("Enter Album ID: ");

                        if (!scanner.hasNextInt()) {
                            System.out.println("Album ID must be a number.");
                            scanner.nextLine();
                            continue;
                        }

                        albumId = scanner.nextInt();
                        scanner.nextLine();

                        Album album = albumController.checkAlbumId(albumId);

                        if (album != null) {
                            break;
                        }

                        System.out.println("Album does not exist. Please try again.");
                    }

                    Song newSong = new Song(title, length, genre, albumId);

                    if (songController.createSong(newSong)) {
                        System.out.println("Song added successfully!");
                    } else {
                        System.out.println("Failed to add song.");
                    }

                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();

                    break;

                case 2:
                    return;

                default:
                    System.out.println("Invalid Input");
            }

        }

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
                        System.out.println("No songs found.");
                        break;
                    }

                    int currentAlbumId = -1;

                    for (Song song : songController.listSongs()) {

                        if (song.getAlbum_id() != currentAlbumId) {

                            currentAlbumId = song.getAlbum_id();

                            System.out.println();
                            System.out.println("============================================================");
                            System.out.println("Album : " + song.getAlbumName());
                            System.out.println("Artist: " + song.getArtistName());
                            System.out.println("============================================================");

                            System.out.printf("%-5s %-30s %-15s %-10s%n",
                                    "ID", "Title", "Genre", "Length");

                            System.out.println("------------------------------------------------------------");
                        }

                        if (song.getId() == 0) {
                            continue;
                        }

                        System.out.printf("%-5d %-30s %-15s %-10s%n",
                                song.getId(),
                                song.getTitle(),
                                song.getGenre(),
                                song.getLength());
                    }

                    break;
                case 2:
                    System.out.print("Enter Song ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    Song song = songController.checkSongId(id);

                    if (song == null) {
                        System.out.println("Song does not exist.");
                    } else {
                        System.out.println("Album : " + song.getAlbumName());
                        System.out.println("Artist: " + song.getArtistName());
                        System.out.println();

                        System.out.printf("%-5s %-30s %-15s %-10s%n",
                                "ID", "Title", "Genre", "Length");

                        System.out.println("--------------------------------------------------");

                        System.out.printf("%-5d %-30s %-15s %-10s%n",
                                song.getId(),
                                song.getTitle(),
                                song.getGenre(),
                                song.getLength());
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
        System.out.println("=== UPDATE ===");
        System.out.println("1. UPDATE A SONG");
        System.out.println("2. BACK");
        System.out.print("Choose an option: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Song ID must be a number.");
            scanner.nextLine();
            return;
        }
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {

            case 1:
                System.out.print("Enter Song ID: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Song ID must be a number.");
                    scanner.nextLine();
                    return;
                }

                int updateId = scanner.nextInt();
                scanner.nextLine();

                Song song = songController.checkSongId(updateId);

                if (song == null) {
                    System.out.println("Song not found.");
                    return;
                }

                String newTitle;
                do {
                    System.out.print("New Title: ");
                    newTitle = scanner.nextLine().trim();

                    if (newTitle.isEmpty()) {
                        System.out.println("Title cannot be empty.");
                    }

                } while (newTitle.isEmpty());

                String newGenre;
                do {
                    System.out.print("New Genre: ");
                    newGenre = scanner.nextLine().trim();

                    if (newGenre.isEmpty()) {
                        System.out.println("Genre cannot be empty.");
                    }

                } while (newGenre.isEmpty());

                // LENGTH
                String newLength;
                while (true) {

                    System.out.print("New Length (HH:MM:SS): ");
                    newLength = scanner.nextLine().trim();

                    if (newLength.matches("^([0-1]?\\d|2[0-3]):[0-5]\\d:[0-5]\\d$")) {
                        break;
                    }

                    System.out.println("Invalid time format. Example: 00:03:45");
                }

                int newAlbumId;

                while (true) {

                    System.out.print("New Album ID: ");

                    if (!scanner.hasNextInt()) {
                        System.out.println("Album ID must be a number.");
                        scanner.nextLine();
                        continue;
                    }

                    newAlbumId = scanner.nextInt();
                    scanner.nextLine();

                    Album album = albumController.checkAlbumId(newAlbumId);

                    if (album == null) {
                        System.out.println("Album does not exist.");
                        continue;
                    }

                    break;
                }

                Song updatedSong = new Song(updateId, newTitle, newLength, newGenre, newAlbumId);

                if (songController.updateSong(updatedSong)) {
                    System.out.println("Song updated successfully.");
                } else {
                    System.out.println("Failed to update song.");
                }

                System.out.println("Press Enter to continue...");
                scanner.nextLine();

                break;

            case 2:
                return;

            default:
                System.out.println("Invalid Input!");
        }
    }

    private void deleteSong() {

        while (true) {
            System.out.println("=== DELETE ===");
            System.out.println("1. DELETE A SONG");
            System.out.println("2. BACK");
            System.out.print("Choose an option: ");

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

                    if (!scanner.hasNextInt()) {
                        System.out.println("NUMBER ONLY!");
                        scanner.nextLine();
                        break;
                    }

                    int deleteId = scanner.nextInt();
                    scanner.nextLine();

                    Song deleteSong = songController.checkSongId(deleteId);

                    if (deleteSong == null) {
                        System.out.println("Song does not exist.");
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
                    return;

                default:
                    System.out.println("Invalid Input");
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private void searchSong() {

        System.out.println("\n=== SEARCH SONG ===");

        System.out.print("Enter Song Title: ");
        String keyword = scanner.nextLine().trim();

        if (keyword.isEmpty()) {
            System.out.println("Search keyword cannot be empty.");

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
            return;
        }

        List<Song> songs = songController.searchSong(keyword);

        if (songs.isEmpty()) {
            System.out.println("No songs found.");
        } else {

            System.out.println("\n=== SEARCH RESULTS ===");

            System.out.printf(
                    "%-5s %-30s %-15s %-10s%n",
                    "ID",
                    "Title",
                    "Genre",
                    "Length"
            );

            System.out.println("------------------------------------------------------------");

            for (Song song : songs) {
                System.out.printf(
                        "%-5d %-30s %-15s %-10s%n",
                        song.getId(),
                        song.getTitle(),
                        song.getGenre(),
                        song.getLength()
                );
            }
        }

        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    private void archiveSong() {

        while (true) {
            System.out.println("=== ARCHIVE SONG ===");
            System.out.println("1. ARCHIVE A SONG");
            System.out.println("2. BACK");
            System.out.println("Choice an option: ");

            if (!scanner.hasNextInt()) {
                System.out.println("NUMBER ONLY!");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("\n=== ARCHIVE ===");
                    System.out.print("Enter Song ID to archive: ");

                    if (!scanner.hasNextInt()) {
                        System.out.println("Song ID must be a number.");

                        scanner.nextLine();

                        System.out.println("\nPress Enter to continue...");
                        scanner.nextLine();

                        return;
                    }

                    int archiveId = scanner.nextInt();
                    scanner.nextLine();

                    Song song = songController.checkSongId(archiveId);

                    if (song == null) {
                        System.out.println("Song does not exist.");

                        System.out.println("\nPress Enter to continue...");
                        scanner.nextLine();

                        return;
                    }

                    System.out.println("Song: " + song.getTitle());

                    System.out.print("Are you sure you want to archive this song? (YES/NO): ");

                    String confirm = scanner.nextLine().trim();

                    if (confirm.equalsIgnoreCase("YES")) {

                        if (songController.archiveSong(archiveId)) {
                            System.out.println("Song archived successfully.");
                        } else {
                            System.out.println("Failed to archive song.");
                        }

                    } else {
                        System.out.println("Archive cancelled.");
                    }

                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;

                case 2:
                    return;

                default:
                    System.out.println("Invalid Input");

            }
        }
    }

    private void restoreSong() {

        while (true) {

            System.out.println("=== RESTORE SONG ===");
            System.out.println("1. RESTORE A SONG");
            System.out.println("2. BACK");
            System.out.print("Choose an option: ");

            if (!scanner.hasNextInt()) {
                System.out.println("NUMBER ONLY!");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    if (songController.getArchivedSongs().isEmpty()) {

                        System.out.println("\nNo archived songs available to restore.");

                        System.out.println("\nPress Enter to continue...");
                        scanner.nextLine();
                        break;
                    }

                    System.out.println("\n=== RESTORE ===");
                    System.out.print("Enter Archived Song ID: ");

                    if (!scanner.hasNextInt()) {

                        System.out.println("Song ID must be a number.");
                        scanner.nextLine();

                        System.out.println("\nPress Enter to continue...");
                        scanner.nextLine();
                        break;
                    }

                    int restoreId = scanner.nextInt();
                    scanner.nextLine();

                    Song archivedSong = songController.checkArchivedSongId(restoreId);

                    if (archivedSong == null) {
                        System.out.println("Archived song does not exist.");

                        System.out.println("\nPress Enter to continue...");
                        scanner.nextLine();
                        break;
                    }

                    System.out.println(
                            "Song: "
                            + archivedSong.getTitle()
                    );

                    String confirm;

                    while (true) {

                        System.out.print(
                                "Are you sure you want to restore this song? (YES/NO): "
                        );

                        confirm = scanner.nextLine().trim();

                        if (confirm.equalsIgnoreCase("YES")) {

                            if (songController.restoreSong(restoreId)) {
                                System.out.println("Song restored successfully.");
                            } else {
                                System.out.println("Failed to restore song.");
                            }

                            break;

                        } else if (confirm.equalsIgnoreCase("NO")) {

                            System.out.println("Restore cancelled.");

                            break;

                        } else {

                            System.out.println(
                                    "Invalid input! Please enter YES or NO only."
                            );
                        }
                    }

                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;

                case 2:
                    return;

                default:
                    System.out.println("Invalid Input!");
            }
        }
    }
}
