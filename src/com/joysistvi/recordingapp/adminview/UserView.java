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
            System.out.println("1. Add User");
            System.out.println("2. View Users");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("0. Back");
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
                    addUser();
                    break;

                case 2:
                    viewUsers();
                    break;

                case 3:
                    updateUser();
                    break;

                case 4:
                    deleteUser();
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Invalid input.");
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private void addUser() {

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Role: ");
        String role = scanner.nextLine();

        User user = new User(
                username,
                password,
                role
        );

        if (userController.register(user)) {
            System.out.println("User added successfully.");
        } else {
            System.out.println("Failed to add user.");
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

        System.out.print("New Username: ");
        String username = scanner.nextLine();

        System.out.print("New Password: ");
        String password = scanner.nextLine();

        System.out.print("New Role: ");
        String role = scanner.nextLine();

        User updatedUser = new User(
                id,
                username,
                password,
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

        System.out.print("Are you sure you want to delete this user? (Y/N): ");
        String confirmation = scanner.nextLine();

        if (!confirmation.equalsIgnoreCase("Y")) {
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