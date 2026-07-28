/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.adminview;

import com.joysistvi.recordingapp.controller.AlbumController;
import com.joysistvi.recordingapp.model.Album;

import static com.joysistvi.recordingapp.utils.ClearScreen.clearScreen;
import static com.joysistvi.recordingapp.utils.Scan.scanner;

public class AlbumView {

    private final AlbumController albumController;

    public AlbumView(AlbumController albumController) {
        this.albumController = albumController;
    }

    public void dashboard() {

        while (true) {

            clearScreen();

            System.out.println("===== ALBUM MENU =====");
            System.out.println("1. Add Album");
            System.out.println("2. View Albums");
            System.out.println("3. Update Album");
            System.out.println("4. Delete Album");
            System.out.println("5. Truncate Albums");
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
                    truncateAlbum();
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

    private void addAlbum() {

        System.out.print("Album Title: ");
        String title = scanner.nextLine();

        System.out.print("Album Year: ");
        int year = scanner.nextInt();

        System.out.print("Artist ID: ");
        int artistId = scanner.nextInt();
        scanner.nextLine();

        Album album = new Album(title, year, artistId);

        if (albumController.createAlbum(album)) {
            System.out.println("Album added successfully.");
        } else {
            System.out.println("Failed to add album.");
        }

    }

    void viewAlbums() {

        if (albumController.listAlbums().isEmpty()) {
            System.out.println("No albums found.");
            return;
        }

        System.out.println("+----+------------------------------+------+-----------+");
        System.out.printf("| %-2s | %-28s | %-4s | %-9s |%n",
                "ID", "Title", "Year", "Artist ID");
        System.out.println("+----+------------------------------+------+-----------+");

        for (Album album : albumController.listAlbums()) {

            System.out.printf("| %-2d | %-28s | %-4d | %-9d |%n",
                    album.getId(),
                    album.getTitle(),
                    album.getYear(),
                    album.getArtist_id());

        }

        System.out.println("+----+------------------------------+------+-----------+");

    }

    private void updateAlbum() {

        System.out.print("Enter Album ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Album album = albumController.checkAlbumId(id);

        if (album == null) {
            System.out.println("Album not found.");
            return;
        }

        System.out.print("New Title: ");
        String title = scanner.nextLine();

        System.out.print("New Year: ");
        int year = scanner.nextInt();

        System.out.print("New Artist ID: ");
        int artistId = scanner.nextInt();
        scanner.nextLine();

        Album updated = new Album(title, id, year);

        if (albumController.updateAlbum(updated)) {
            System.out.println("Album updated successfully.");
        } else {
            System.out.println("Failed to update album.");
        }

    }

    private void deleteAlbum() {

        System.out.print("Enter Album ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Album album = albumController.checkAlbumId(id);

        if (album == null) {
            System.out.println("Album not found.");
            return;
        }

        if (albumController.deleteAlbum(id)) {
            System.out.println("Album deleted successfully.");
        } else {
            System.out.println("Failed to delete album.");
        }

    }

    private void truncateAlbum() {

        System.out.print("Are you sure? (YES/NO): ");
        String answer = scanner.nextLine();

        if (!answer.equalsIgnoreCase("YES")) {
            System.out.println("Cancelled.");
            return;
        }

        if (albumController.truncateAlbum()) {
            System.out.println("Albums table truncated.");
        } else {
            System.out.println("Failed.");
        }

    }

}
