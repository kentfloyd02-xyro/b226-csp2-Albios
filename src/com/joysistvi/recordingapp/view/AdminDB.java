/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.view;

import com.joysistvi.recordingapp.controller.AlbumController;
import com.joysistvi.recordingapp.controller.PlaylistController;
import com.joysistvi.recordingapp.controller.SongController;
import com.joysistvi.recordingapp.controller.UserController;
import static com.joysistvi.recordingapp.utils.Scan.scanner;

public class AdminDB {

    private final SongView songView;
    private final AlbumView albumView;
    private final PlaylistView playlistView;

    public AdminDB() {
        SongController songController = new SongController();
        AlbumController albumController = new AlbumController();
        PlaylistController playlistController = new PlaylistController();

        songView = new SongView(songController);
        albumView = new AlbumView(albumController);
        playlistView = new PlaylistView(playlistController);
    }

    public void adminMenu() {

        int choice;

        do {
            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. Songs");
            System.out.println("2. Albums");
            System.out.println("3. Artists");
            System.out.println("4. Playlists");
            System.out.println("5. Users");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    songView.dashboard();
                    break;

                case 2:
                    albumView.dashboard();
                    break;

                case 3:
                    System.out.println("\nArtist Management");
                    // create ArtistView later
                    break;

                case 4:
                    playlistView.dashboard();
                    break;

                case 5:
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

    }
}
