/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joysistvi.recordingapp.view;

import com.joysistvi.recordingapp.config.dbconnection;
import com.joysistvi.recordingapp.controller.SongController;
import com.joysistvi.recordingapp.controller.UserController;
import com.joysistvi.recordingapp.model.User;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UserController userController = new UserController();

        while (true) {

            System.out.println("=================================");
            System.out.println("  WELCOME RECORDING STUDIO APP!");
            System.out.println("=================================");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    LoginView login = new LoginView(userController);
                    User user = login.login();

                    if (user != null) {

                        if (user.getRole().equalsIgnoreCase("ADMIN")) {
                            AdminDB adminDB = new AdminDB();
                            adminDB.adminMenu();
                        } else {
                            UserDB userDB = new UserDB();
                            userDB.userMenu();
                        }

                    }

                    break;

                case 2:
                    RegisterView register = new RegisterView(userController);
                    register.register();
                    break;

                case 3:
                    System.out.println("Goodbye!");
                    System.exit(0);
            }
        }
    }
}
