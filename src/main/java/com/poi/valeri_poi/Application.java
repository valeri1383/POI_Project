/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poi.valeri_poi;

import java.util.ArrayList;
import java.util.Scanner;

public class Application {

    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<User> admins = new ArrayList<>();

    public static void main(String[] args) {
   
        Scanner scanner = new Scanner(System.in);


        while (true) {
            // Display main menu options
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    signUp(); 
                    break;
                case 2:
                    logIn(); 
                    break;
                case 3:
                    System.out.println("Exiting program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void signUp() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();


        System.out.println("User created successfully.");
    }

    private static void logIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User loggedInUser = authenticateUser(username, password);

        if (loggedInUser != null) {
            if (isAdmin(loggedInUser)) {
                adminMenu(loggedInUser);
                System.out.println("Logged in as an admin user.");
            } else {
                System.out.println("Logged in as a regular user.");
                PointsOfInterest.managePointsOfInterest(); // Calling the static method

            }
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static User authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }

        // Check admin users
        for (User admin : admins) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                return admin;
            }
        }

        return null;
    }

    private static boolean isAdmin(User user) {
        return admins.contains(user);
    }

    private static void adminMenu(User adminUser) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display admin menu options
            System.out.println("Admin Menu:");
            System.out.println("1. View List of All Users");
            System.out.println("2. Change User Details");
            System.out.println("3. Delete User");
            System.out.println("4. Add Point of Interest");
            System.out.println("5. List All Points of Interest"); 
            System.out.println("6. View Comments of a Point of Interest");
            System.out.println("7. Delete a Comment");

            System.out.println("8. Log Out");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAllUsers();
                    break;
                case 2:
                    changeUserDetails();
                    break;
                case 3:
                    deleteUser();
                    break;
                case 4:
                    PointsOfInterest.addPointOfInterest();
                    break;
                case 5:
                    PointsOfInterest.listAllPointsOfInterest();
                    break;
                case 6:
                    PointsOfInterest.viewComments();
                    break;
                    case 7:
                    PointsOfInterest.deleteCommentByAdmin();
                    break;
                case 8:
                    System.out.println("Logging out.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewAllUsers() {
        System.out.println("List of All Users:");
        for (User user : users) {
            System.out.println(user.getUsername());
        }
    }

    private static void changeUserDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the username to change details: ");
        String username = scanner.nextLine();

        
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();

                user.setUsername(newName); // Update the username
                System.out.print("Enter new password: ");
                String newPassword = scanner.nextLine();
                user.setPassword(newPassword);

                System.out.println("User details updated successfully.");

              
                for (User admin : admins) {
                    if (admin.getUsername().equals(username)) {
                        admin.setUsername(newName); // Update the username
                        admin.setPassword(newPassword);
                        break;
                    }
                }

                return;
            }
        }

        System.out.println("User not found.");
    }

    private static void deleteUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the username to delete: ");
        String username = scanner.nextLine();

        User userToDelete = null;

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                userToDelete = user;
                break;
            }
        }

        if (userToDelete != null) {
            users.remove(userToDelete);

            // Remove the user from the admins list if it exists
            admins.removeIf(admin -> admin.getUsername().equals(username));

            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User not found. Deletion failed.");
        }

        
    }

}