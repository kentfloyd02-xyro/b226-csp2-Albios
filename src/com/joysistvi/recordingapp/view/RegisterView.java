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

        User user = new User(email, password);
        boolean success = userController.register(user);

        if (success) {
            System.out.println("Registration successful!\n");
        } else {
            System.out.println("Registration failed. Email may already exist.\n");
        }
    }
}
