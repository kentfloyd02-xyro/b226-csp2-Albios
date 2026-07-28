/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.adminview;

import com.joysistvi.recordingapp.controller.ArtistController;
import com.joysistvi.recordingapp.model.Artist;
import static com.joysistvi.recordingapp.utils.ClearScreen.clearScreen;
import static com.joysistvi.recordingapp.utils.Scan.scanner;

public class ArtistView {

    private final ArtistController artistController;

    public ArtistView(ArtistController artistController) {
        this.artistController = artistController;
    }

    public void dashboard() {
        while (true) {

            clearScreen();

            System.out.println("===== ARTIST MENU =====");
            System.out.println("1. Add Artist");
            System.out.println("2. View Artist");
            System.out.println("3. Update Artist");
            System.out.println("4. Delete Artist");
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
                    addArtist();
                    break;

                case 2:
                    viewArtist();
                    break;

                case 3:
                    updateArtist();
                    break;

                case 4:
                    deleteArtist();
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

    private void addArtist() {

        System.out.print("Artist Name: ");
        String name = scanner.nextLine();

        Artist artist = new Artist(name);

        if (artistController.createArtist(artist)) {
            System.out.println("Album added successfully.");
        } else {
            System.out.println("Failed to add album.");
        }

    }

    void viewArtist() {

        if (artistController.getAllArtist().isEmpty()) {
            System.out.println("No albums found.");
            return;
        }

        System.out.println("+----+------------------------------+------+-----------+");
        System.out.printf("| %-2s | %-28s | %-4s | %-9s |%n",
                "ID", "Title", "Year", "Artist ID");
        System.out.println("+----+------------------------------+------+-----------+");

        for (Artist artist : artistController.getAllArtist()) {

            System.out.printf("| %-2d | %-28s | %-4d | %-9d |%n",
                    artist.getId(),
                    artist.getName());

        }

        System.out.println("+----+------------------------------+------+-----------+");

    }

    private void updateArtist() {

        System.out.print("Enter Album ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Artist artist = artistController.checkArtistId(id);

        if (artist == null) {
            System.out.println("Album not found.");
            return;
        }

        System.out.print("New Artist Name: ");
        String name = scanner.nextLine();

        Artist updated = new Artist(name);

        if (artistController.updateArtist(updated)) {
            System.out.println("Album updated successfully.");
        } else {
            System.out.println("Failed to update album.");
        }

    }

    private void deleteArtist() {

        System.out.print("Enter Album ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Artist artist = artistController.checkArtistId(id);

        if (artist == null) {
            System.out.println("Album not found.");
            return;
        }

        if (artistController.deleteArtist(id)) {
            System.out.println("Album deleted successfully.");
        } else {
            System.out.println("Failed to delete album.");
        }

    }
}
