/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.clientview;

import com.joysistvi.recordingapp.controller.AlbumController;
import com.joysistvi.recordingapp.model.Album;

import static com.joysistvi.recordingapp.utils.Scan.scanner;

/**
 *
 * @author ktagl
 */
public class UserAlbumView {

    private final AlbumController albumController;

    public UserAlbumView(AlbumController albumController) {
        this.albumController = albumController;
    }

    public void dashboard() {
        while (true) {
            System.out.println("=== ALBUM VIEW ===");
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

}
