/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.view;

import com.joysistvi.recordingapp.adminview.PlaylistView;
import com.joysistvi.recordingapp.adminview.SongView;
import com.joysistvi.recordingapp.adminview.UserView;
import com.joysistvi.recordingapp.adminview.AlbumView;
import com.joysistvi.recordingapp.controller.AlbumController;
import com.joysistvi.recordingapp.controller.PlaylistController;
import com.joysistvi.recordingapp.controller.SongController;
import com.joysistvi.recordingapp.controller.UserController;
import static com.joysistvi.recordingapp.utils.ClearScreen.clearScreen;
import static com.joysistvi.recordingapp.utils.Scan.scanner;

/**
 *
 * @author ktagl
 */
public class UserDB {

    private final SongView songView;
    private final AlbumView albumView;
    private final PlaylistView playlistView;
    private final UserView userView;

    public UserDB() {

        SongController songController = new SongController();
        AlbumController albumController = new AlbumController();
        PlaylistController playlistController = new PlaylistController();
        UserController userController = new UserController();

        songView = new SongView(songController);
        albumView = new AlbumView(albumController);
        playlistView = new PlaylistView(playlistController);
        userView = new UserView(userController);
    }

    public void userMenu() {

        boolean running = true;

        while (running) {
            clearScreen();
            System.out.println("\n=== User Dashboard ===");
            System.out.println("1. View Songs");
            System.out.println("2. Search Song");
            System.out.println("3. View Albums");
            System.out.println("4. Create Playlist");
            System.out.println("5. View My Playlist");
            System.out.println("6. Update Profile");
            System.out.println("0. Exit");
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
                    songView.dashboard();
                    break;

                case 2:
//                    songView.searchSong();
                    break;

                case 3:
                    albumView.dashboard();
                    break;

                case 4:
                    playlistView.dashboard();
                    break;

                case 5:
                    playlistView.dashboard();
                    break;

                case 6:
                    userView.dashboard();
                    break;

                case 0:
                    
                    running = false;
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}
