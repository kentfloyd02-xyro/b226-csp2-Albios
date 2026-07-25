/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joysistvi.recordingapp.view;

import com.joysistvi.recordingapp.controller.SongController;
import com.joysistvi.recordingapp.config.model.Song;
import java.util.Scanner;


public class SongView {

    private final SongController songController;

    public SongView(SongController songController) {
        this.songController = songController;
    }
           
    
    public void dashboard() {
        Scanner scanner = new Scanner(System.in);
         
        boolean running = true;
        while (running) {
            System.out.println("\n=== Song Dashboard ===");
            System.out.println("1. Add Song");
            System.out.println("2. View All Songs");
            System.out.println("3. Update Song");
            System.out.println("4. Delete Song");
            System.out.println("5. Archive Song");
            System.out.println("6. Restore Song");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1: 
                    break;
                case 2:
                    for (Song song : songController.listSongs()) {
                        System.out.println(song.getId() + " - " + song.getTitle());
                    }
                    //songController.listSongs().forEach(song -> System.out.println(song.getId() + " - " + song.getTitle()));
                    break;
                case 3:
                    System.out.print("Enter song title: ");
                    String title = scanner.nextLine();
                    //boolean created = songController.createSong(new Song(title));
                    //System.out.println(created ? "Song added successfully!" : "Failed to add");
            }
        }
    }
    
    public static void main(String[] args) {
        
    }
}
