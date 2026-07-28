/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.main;

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

    public User login() {
        while (true) {

            System.out.println("\n===== LOGIN =====");

            String username;
            String password;

            while (true) {
                System.out.print("Username: ");
                username = scanner.nextLine();

                if (username.length() >= 5) {
                    break;
                } else {
                    System.out.println("Username must be at least 5 characters long.");
                }
            }

            while (true) {
                System.out.print("Password: ");
                password = scanner.nextLine();

                if (password.length() >= 5) {
                    break;
                } else {
                    System.out.println("Password must be at least 5 characters long.");
                }
            }

            User user = userController.login(username, password);

            if (user != null) {
                System.out.println("Login successful!\n");
                return user;
            }

            System.out.println("Invalid username or password. Please try again.\n");
        }
    }
}
