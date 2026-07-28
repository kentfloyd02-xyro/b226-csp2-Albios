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
            System.out.println("\n===== ARTIST MENU =====");
            System.out.println("1. Add Artist");
            System.out.println("2. View Artist");
            System.out.println("3. Update Artist");
            System.out.println("4. Delete Artist");
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

                case 5:
                    return;

                default:
                    System.out.println("Invalid Input");
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private void addArtist() {

        while (true) {

            System.out.println("=== ADD ARTIST ===");
            System.out.println("1. ADD ARTIST");
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

                    String name;

                    while (true) {

                        System.out.print("Artist Name: ");
                        name = scanner.nextLine().trim();

                        if (!name.isEmpty()) {
                            break;
                        }

                        System.out.println("Artist name cannot be empty.");
                    }

                    Artist artist = new Artist(name);

                    if (artistController.createArtist(artist)) {
                        System.out.println("Artist added successfully!");
                    } else {
                        System.out.println("Failed to add artist.");
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

    void viewArtist() {

        while (true) {

            System.out.println("=== VIEW ===");
            System.out.println("1. VIEW ALL ARTISTS");
            System.out.println("2. VIEW SPECIFIC ARTIST");
            System.out.println("3. BACK");
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

                    if (artistController.getAllArtist().isEmpty()) {
                        System.out.println("No artists found.");
                        break;
                    }

                    System.out.println("+----+------------------------------+");
                    System.out.printf("| %-2s | %-28s |%n", "ID", "Artist Name");
                    System.out.println("+----+------------------------------+");

                    for (Artist artist : artistController.getAllArtist()) {

                        System.out.printf("| %-2d | %-28s |%n",
                                artist.getId(),
                                artist.getName());
                    }

                    System.out.println("+----+------------------------------+");
                    break;

                case 2:

                    System.out.print("Enter Artist ID: ");

                    if (!scanner.hasNextInt()) {
                        System.out.println("NUMBER ONLY!");
                        scanner.nextLine();
                        break;
                    }

                    int id = scanner.nextInt();
                    scanner.nextLine();

                    Artist artist = artistController.checkArtistId(id);

                    if (artist == null) {
                        System.out.println("Artist does not exist.");
                    } else {

                        System.out.println("+----+------------------------------+");
                        System.out.printf("| %-2s | %-28s |%n", "ID", "Artist Name");
                        System.out.println("+----+------------------------------+");

                        System.out.printf("| %-2d | %-28s |%n",
                                artist.getId(),
                                artist.getName());

                        System.out.println("+----+------------------------------+");
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

    private void updateArtist() {

        while (true) {

            System.out.println("=== UPDATE ===");
            System.out.println("1. UPDATE AN ARTIST");
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

                        System.out.print("Enter Artist ID: ");

                        if (!scanner.hasNextInt()) {
                            System.out.println("Artist ID must be a number.");
                            scanner.nextLine();
                            continue;
                        }

                        id = scanner.nextInt();
                        scanner.nextLine();

                        Artist artist = artistController.checkArtistId(id);

                        if (artist != null) {
                            break;
                        }

                        System.out.println("Artist does not exist.");
                    }

                    String name;

                    while (true) {

                        System.out.print("New Artist Name: ");
                        name = scanner.nextLine().trim();

                        if (!name.isEmpty()) {
                            break;
                        }

                        System.out.println("Artist name cannot be empty.");
                    }

                    Artist updated = new Artist(id, name);

                    if (artistController.updateArtist(updated)) {
                        System.out.println("Artist updated successfully!");
                    } else {
                        System.out.println("Failed to update artist.");
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

    private void deleteArtist() {

        while (true) {

            System.out.println("=== DELETE ===");
            System.out.println("1. DELETE AN ARTIST");
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

                    System.out.print("Enter Artist ID: ");

                    if (!scanner.hasNextInt()) {
                        System.out.println("Artist ID must be a number.");
                        scanner.nextLine();
                        break;
                    }

                    int id = scanner.nextInt();
                    scanner.nextLine();

                    Artist artist = artistController.checkArtistId(id);

                    if (artist == null) {
                        System.out.println("Artist does not exist.");
                        break;
                    }

                    while (true) {

                        System.out.print("Are you sure? (YES/NO): ");
                        String confirm = scanner.nextLine().trim().toUpperCase();

                        switch (confirm) {

                            case "YES":

                                if (artistController.deleteArtist(id)) {
                                    System.out.println("Artist deleted successfully.");
                                } else {
                                    System.out.println("Failed to delete artist.");
                                }

                                break;

                            case "NO":
                                System.out.println("Delete cancelled.");
                                break;

                            default:
                                System.out.println("Please enter YES or NO.");
                                continue;
                        }

                        break;
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