/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.view.dashboard;

import com.joysistvi.recordingapp.clientview.UserAlbumView;
import com.joysistvi.recordingapp.clientview.UserArtistView;
import com.joysistvi.recordingapp.clientview.UserSongView;
import com.joysistvi.recordingapp.clientview.PlaylistView;

import com.joysistvi.recordingapp.controller.AlbumController;
import com.joysistvi.recordingapp.controller.ArtistController;
import com.joysistvi.recordingapp.controller.PlaylistController;
import com.joysistvi.recordingapp.controller.SongController;
import com.joysistvi.recordingapp.controller.UserController;

import static com.joysistvi.recordingapp.utils.ClearScreen.clearScreen;
import static com.joysistvi.recordingapp.utils.Scan.scanner;

public class UserDB {

    private final UserSongView songView;
    private final UserAlbumView albumView;
    private final UserArtistView artistView;
    private final PlaylistView playlistView;

    public UserDB() {

        SongController songController = new SongController();
        AlbumController albumController = new AlbumController();
        ArtistController artistController = new ArtistController();
        PlaylistController playlistController = new PlaylistController();
        UserController userController = new UserController();

        songView = new UserSongView(songController);
        albumView = new UserAlbumView(albumController);
        artistView = new UserArtistView(artistController);
        playlistView = new PlaylistView(playlistController, songController, userController);
    }

    public void dashboard() {

        while (true) {

            clearScreen();

            System.out.println("===== USER DASHBOARD =====");
            System.out.println("1. Browse Songs");
            System.out.println("2. Browse Albums");
            System.out.println("3. Browse Artists");
            System.out.println("4. My Playlists");
            System.out.println("5. Logout");
            System.out.print("Choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("NUMBER ONLY!");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    songView.dashboard();
                    break;

                case 2:
                    albumView.dashboard();
                    break;

                case 3:
                    artistView.dashboard();
                    break;

                case 4:
                    playlistView.dashboard();
                    break;

                case 5:
                    System.out.println("Logging out...");
                    System.exit(0);

                default:
                    System.out.println("Invalid Input!");
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }
}
