/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joysistvi.recordingapp.adminview;

import com.joysistvi.recordingapp.controller.UserController;
import com.joysistvi.recordingapp.model.User;

import static com.joysistvi.recordingapp.utils.ClearScreen.clearScreen;
import static com.joysistvi.recordingapp.utils.Scan.scanner;

public class UserView {

    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void dashboard() {

        while (true) {

            clearScreen();

            System.out.println("===== USER MENU =====");
            System.out.println("1. View Users");
            System.out.println("2. Update User");
            System.out.println("3. Delete User");
            System.out.println("4. Back");
            System.out.print("Choose: ");

            if (!scanner.hasNextInt()) {
                System.out.println("NUMBER ONLY!");
                scanner.nextLine();

                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    viewUsers();
                    break;

                case 2:
                    updateUser();
                    break;

                case 3:
                    deleteUser();
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid Input");
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private void viewUsers() {

        if (userController.getAllUser().isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        System.out.println("+----+----------------------+---------------+-------------+");
        System.out.printf(
                "| %-2s | %-20s | %-13s | %-11s |%n",
                "ID",
                "USERNAME",
                "ROLE",
                "PLAYLIST ID"
        );
        System.out.println("+----+----------------------+---------------+-------------+");

        for (User user : userController.getAllUser()) {

            System.out.printf(
                    "| %-2d | %-20s | %-13s | %-11d |%n",
                    user.getId(),
                    user.getUsername(),
                    user.getRole(),
                    user.getPlaylistID()
            );
        }

        System.out.println("+----+----------------------+---------------+-------------+");
    }

    private void updateUser() {

        System.out.print("Enter User ID: ");

        if (!scanner.hasNextInt()) {
            System.out.println("User ID must be a number.");
            scanner.nextLine();
            return;
        }

        int id = scanner.nextInt();
        scanner.nextLine();

        User user = userController.checkUserId(id);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("Current Username: " + user.getUsername());
        System.out.println("Current Role: " + user.getRole());

        
        System.out.print("New Role: ");
        String role = scanner.nextLine();

        User updatedUser = new User(
                id,
                role
        );

        if (userController.updateUser(updatedUser)) {
            System.out.println("User updated successfully.");
        } else {
            System.out.println("Failed to update user.");
        }
    }

    private void deleteUser() {

        System.out.print("Enter User ID: ");

        if (!scanner.hasNextInt()) {
            System.out.println("User ID must be a number.");
            scanner.nextLine();
            return;
        }

        int id = scanner.nextInt();
        scanner.nextLine();

        User user = userController.checkUserId(id);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println(
                "User found: "
                + user.getUsername()
        );

        System.out.print("Are you sure you want to delete this user? (Yes/No): ");
        String confirmation = scanner.nextLine();

        if (!confirmation.equalsIgnoreCase("Yes")) {
            System.out.println("Delete cancelled.");
            return;
        }

        if (userController.deleteUser(id)) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("Failed to delete user.");
        }
    }
}
