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

    public User login() {
        System.out.println("\n===== LOGIN =====");
        String email;
        String password;
        
        while (true) {
            System.out.print("Email: ");
            email = scanner.nextLine();

            if (email.contains("@") && email.contains(".")) {
                break;
            } else {
                System.out.println("Invalid email format. Please try again.");
            }
        }

        while (true) {
            System.out.print("Password: ");
            password = scanner.nextLine();

            if (password.length() >= 8) {
                break;
            } else {
                System.out.println("Password must be at least 8 characters long.");
            }
        }
        User user = userController.login(email, password);

        if (user != null) {
            System.out.println("Login successful!\n");
            return user;
        } else {
            System.out.println("Invalid username or password.\n");
            return null;
        }
    }

}
