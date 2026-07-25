/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.view;

import com.joysistvi.recordingapp.controller.UserController;
import com.joysistvi.recordingapp.model.User;
import java.util.Scanner;

public class LoginView {

    private UserController userController;
    private final Scanner scanner; 

    public LoginView(UserController userController) {
        this.userController = userController;
        this.scanner = new Scanner(System.in);
    }

    public boolean login() {
        System.out.println("\n===== LOGIN =====");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        User user = userController.login(username, password);

        if (user != null) {
            System.out.println("Login successful!\n");
            return true;
        } else {
            System.out.println("Invalid username or password.\n");
            return false;
        }
    }
}
