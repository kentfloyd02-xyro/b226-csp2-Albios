/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.view.dashboard;

import com.joysistvi.recordingapp.controller.AlbumController;
import com.joysistvi.recordingapp.controller.SongController;
import com.joysistvi.recordingapp.controller.UserController;
import com.joysistvi.recordingapp.controller.ArtistController;
import static com.joysistvi.recordingapp.utils.Scan.scanner;
import com.joysistvi.recordingapp.adminview.AlbumView;
import com.joysistvi.recordingapp.adminview.ArtistView;
import com.joysistvi.recordingapp.adminview.SongView;
import com.joysistvi.recordingapp.adminview.UserView;

public class AdminDB {

    private final SongView songView;
    private final AlbumView albumView;
    private final ArtistView artistView;
    private final UserView userView;

    public AdminDB() {

        SongController songController = new SongController();
        AlbumController albumController = new AlbumController();
        ArtistController artistController = new ArtistController();
        UserController userController = new UserController();

        songView = new SongView(songController, albumController);
        albumView = new AlbumView(albumController, artistController);
        artistView = new ArtistView(artistController);
        userView = new UserView(userController);
    }

    public void adminMenu() {

        int choice;

        do {
            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. Songs Management");
            System.out.println("2. Albums Management");
            System.out.println("3. Artists Management");
            System.out.println("4. Manageme Users");
            System.out.println("5. Logout");
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
                    artistView.dashboard();
                    break;

                case 4:
                    userView.dashboard();
                    break;

                case 5:
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

    }
}
