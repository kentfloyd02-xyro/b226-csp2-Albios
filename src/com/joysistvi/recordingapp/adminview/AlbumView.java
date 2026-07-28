/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.adminview;

import com.joysistvi.recordingapp.controller.AlbumController;
import com.joysistvi.recordingapp.controller.ArtistController;
import com.joysistvi.recordingapp.model.Album;
import com.joysistvi.recordingapp.model.Artist;

import static com.joysistvi.recordingapp.utils.ClearScreen.clearScreen;
import static com.joysistvi.recordingapp.utils.Scan.scanner;

public class AlbumView {

    private final AlbumController albumController;
    private final ArtistController artistController;

    public AlbumView(AlbumController albumController, ArtistController artistController) {
        this.albumController = albumController;
        this.artistController = artistController;
    }

    public void dashboard() {

        while (true) {

            clearScreen();

            System.out.println("===== ALBUM MENU =====");
            System.out.println("1. Add Album");
            System.out.println("2. View Albums");
            System.out.println("3. Update Album");
            System.out.println("4. Delete Album");
            System.out.println("5. Back");
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
                    addAlbum();
                    break;

                case 2:
                    viewAlbums();
                    break;

                case 3:
                    updateAlbum();
                    break;

                case 4:
                    deleteAlbum();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid Input");
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private void addAlbum() {
        while (true) {

            System.out.println("=== ADD ALBUM ===");
            System.out.println("1. ADD ALBUM");
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

                    String title;

                    while (true) {
                        System.out.print("Album Title: ");
                        title = scanner.nextLine().trim();

                        if (!title.isEmpty()) {
                            break;
                        }

                        System.out.println("Album title cannot be empty.");
                    }

                    int year;

                    while (true) {

                        System.out.print("Album Year: ");

                        if (!scanner.hasNextInt()) {
                            System.out.println("Year must be a number.");
                            scanner.nextLine();
                            continue;
                        }

                        year = scanner.nextInt();
                        scanner.nextLine();

                        if (year < 1900 || year > 2100) {
                            System.out.println("Invalid year.");
                            continue;
                        }

                        break;
                    }

                    int artistId;

                    while (true) {

                        System.out.print("Artist ID: ");

                        if (!scanner.hasNextInt()) {
                            System.out.println("Artist ID must be a number.");
                            scanner.nextLine();
                            continue;
                        }

                        artistId = scanner.nextInt();
                        scanner.nextLine();

                        Artist artist = artistController.checkArtistId(artistId);

                        if (artist != null) {
                            break;
                        }

                        System.out.println("Artist does not exist.");
                    }

                    Album album = new Album(year, title, artistId, artistId);

                    if (albumController.createAlbum(album)) {
                        System.out.println("Album added successfully!");
                    } else {
                        System.out.println("Failed to add album.");
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
    }

    public void viewAlbums() {

        while (true) {

            System.out.println("=== VIEW ===");
            System.out.println("1. VIEW ALL ALBUMS");
            System.out.println("2. VIEW SPECIFIC ALBUM");
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

                    if (albumController.listAlbums().isEmpty()) {
                        System.out.println("No albums found.");
                        break;
                    }

                    System.out.println("+----+------------------------------+------+--------------------------+");
                    System.out.printf("| %-2s | %-28s | %-4s | %-24s |%n",
                            "ID", "Album", "Year", "Artist");
                    System.out.println("+----+------------------------------+------+--------------------------+");

                    for (Album album : albumController.listAlbums()) {

                        System.out.printf("| %-2d | %-28s | %-4d | %-24s |%n",
                                album.getId(),
                                album.getTitle(),
                                album.getYear(),
                                album.getArtistName());
                    }

                    System.out.println("+----+------------------------------+------+--------------------------+");

                    break;

                case 2:

                    System.out.print("Enter Album ID: ");

                    if (!scanner.hasNextInt()) {
                        System.out.println("NUMBER ONLY!");
                        scanner.nextLine();
                        break;
                    }

                    int id = scanner.nextInt();
                    scanner.nextLine();

                    Album album = albumController.checkAlbumId(id);

                    if (album == null) {
                        System.out.println("Album does not exist.");
                    } else {

                        System.out.println("Artist : " + album.getArtistName());
                        System.out.println();

                        System.out.printf("%-5s %-30s %-10s%n",
                                "ID", "Album", "Year");

                        System.out.println("-----------------------------------------------");

                        System.out.printf("%-5d %-30s %-10d%n",
                                album.getId(),
                                album.getTitle(),
                                album.getYear());
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

    private void updateAlbum() {

        while (true) {

            System.out.println("=== UPDATE ===");
            System.out.println("1. UPDATE AN ALBUM");
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

                    int id;

                    while (true) {

                        System.out.print("Enter Album ID: ");

                        if (!scanner.hasNextInt()) {
                            System.out.println("Album ID must be a number.");
                            scanner.nextLine();
                            continue;
                        }

                        id = scanner.nextInt();
                        scanner.nextLine();

                        Album album = albumController.checkAlbumId(id);

                        if (album != null) {
                            break;
                        }

                        System.out.println("Album does not exist.");
                    }

                    String title;

                    while (true) {

                        System.out.print("New Title: ");
                        title = scanner.nextLine().trim();

                        if (!title.isEmpty()) {
                            break;
                        }

                        System.out.println("Title cannot be empty.");
                    }

                    int year;

                    while (true) {

                        System.out.print("New Year: ");

                        if (!scanner.hasNextInt()) {
                            System.out.println("Year must be a number.");
                            scanner.nextLine();
                            continue;
                        }

                        year = scanner.nextInt();
                        scanner.nextLine();

                        if (year < 1900 || year > 2100) {
                            System.out.println("Invalid year.");
                            continue;
                        }

                        break;
                    }


                    Album updated = new Album(id, title, year);

                    if (albumController.updateAlbum(updated)) {
                        System.out.println("Album updated successfully!");
                    } else {
                        System.out.println("Failed to update album.");
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

    }

    private void deleteAlbum() {
        while (true) {

            System.out.println("=== DELETE ===");
            System.out.println("1. DELETE AN ALBUM");
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

                    System.out.print("Enter Album ID: ");

                    if (!scanner.hasNextInt()) {
                        System.out.println("NUMBER ONLY!");
                        scanner.nextLine();
                        break;
                    }

                    int id = scanner.nextInt();
                    scanner.nextLine();

                    Album album = albumController.checkAlbumId(id);

                    if (album == null) {
                        System.out.println("Album does not exist.");
                        break;
                    }

                    System.out.print("Are you sure? (YES/NO): ");
                    String confirm = scanner.nextLine();

                    if (confirm.equalsIgnoreCase("YES")) {

                        if (albumController.deleteAlbum(id)) {
                            System.out.println("Album deleted successfully.");
                        } else {
                            System.out.println("Failed to delete album.");
                        }

                    } else {
                        System.out.println("Delete cancelled.");
                    }

                    break;

                case 2:
                    return;

                default:
                    System.out.println("Invalid Input!");
            }

            System.out.println("Press Enter to continue...");
            scanner.nextLine();
        }
    }
}
