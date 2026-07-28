/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.main;

import com.joysistvi.recordingapp.controller.UserController;
import com.joysistvi.recordingapp.model.User;
import com.joysistvi.recordingapp.view.dashboard.UserDB;
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
            System.out.print("Username: ");
            email = scanner.nextLine();

            if (email.length() >= 5) {
                break;
            } else {
                System.out.println("Username  must be at least 5 characters long. Please try again.");
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

        User user = new User(email, password);
        boolean success = userController.register(user);

        if (success) {
            System.out.println("Registration successful!");

            UserDB userDB = new UserDB();
            userDB.userMenu();

        } else {
            System.out.println("Registration failed. Username may already exist.");
        }
    }
}
