/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.view;

import com.joysistvi.recordingapp.controller.UserController;
import com.joysistvi.recordingapp.model.User;   
import java.util.Scanner;

public class RegisterView {

    private UserController userController;
    private Scanner scanner;

    public RegisterView(UserController userController) {
        this.userController = userController;
        this.scanner = new Scanner(System.in);
    }

    public void register() {
        
        System.out.println("\n===== REGISTER =====");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        User user = new User(username, password);
        boolean success = userController.register(user);

        if (success) {
            System.out.println("Registration successful!\n");
        } else {
            System.out.println("Registration failed. Username may already exist.\n");
        }
    }
}
