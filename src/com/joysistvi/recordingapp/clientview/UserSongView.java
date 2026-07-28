/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.clientview;

import com.joysistvi.recordingapp.controller.SongController;
import com.joysistvi.recordingapp.model.Song;
import static com.joysistvi.recordingapp.utils.ClearScreen.clearScreen;
import static com.joysistvi.recordingapp.utils.Scan.scanner;
import java.util.List;

/**
 *
 * @author ktagl
 */
public class UserSongView {

    private final SongController songController;

    public UserSongView(SongController songController) {
        this.songController = songController;
    }

    public void dashboard() {
        while (true) {
            clearScreen();
            System.out.println("\n=== SONG VIEW ===");
            System.out.println("1. VIEW ALL SONG");
            System.out.println("2. VIEW SPECIFIC COLUMN SONG");
            System.out.println("3. SEARCH SONG");
            System.out.println("4. BACK");
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

                    System.out.println("\n=== SEARCH SONG ===");

                    System.out.print("Enter Song Title: ");
                    String keyword = scanner.nextLine().trim();

                    if (keyword.isEmpty()) {
                        System.out.println("Search keyword cannot be empty.");
                        break;
                    }

                    List<Song> searchResults = songController.searchSong(keyword);

                    if (searchResults.isEmpty()) {

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

                        System.out.println(
                                "------------------------------------------------------------"
                        );

                        for (Song searchSong : searchResults) {

                            System.out.printf(
                                    "%-5d %-30s %-15s %-10s%n",
                                    searchSong.getId(),
                                    searchSong.getTitle(),
                                    searchSong.getGenre(),
                                    searchSong.getLength()
                            );
                        }
                    }

                    break;
                case 4:
                    return;

                default:
                    System.out.println("Invalid Input!");
            }
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
        }
    }
}
