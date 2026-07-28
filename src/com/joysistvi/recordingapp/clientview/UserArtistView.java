/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.clientview;

import com.joysistvi.recordingapp.controller.ArtistController;
import com.joysistvi.recordingapp.model.Artist;
import static com.joysistvi.recordingapp.utils.Scan.scanner;

/**
 *
 * @author ktagl
 */
public class UserArtistView {
    
    private final ArtistController artistController;
    
    public UserArtistView(ArtistController artistController) {
        this.artistController = artistController;
    }
    
    public void dashboard(){
    
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
}
