package com.poi.valeri_poi;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PointsOfInterest {

    static List<Place> pointOfInterests = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void managePointsOfInterest() {
        boolean running = true;

        while (running) {
           // System.out.println("1. Add Point of Interest");
            System.out.println("1. Search Points of Interest");
            System.out.println("2. List All Points of Interest"); 
            System.out.println("3. View Comments of a Point of Interest");
            System.out.println("4. Exit");
            System.out.println("=>>> Enter your choice: ");

            String choice = scanner.next();
            scanner.nextLine();

            switch (choice) {

                case "1":
                    searchPointsOfInterest();
                    break;
                case "2":
                    listAllPointsOfInterest(); 
                    break;
                case "3":
                    viewComments();
                    break;
                case "4":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    //Refactoring the method to allow adding POI without Scanner input
    public static void addPointOfInterestDirectly(String name, TypeOfPlace type) {
        int id = generatePlaceIdNewId(); // This method generates a new unique ID for the place
        Place place = new Place(id, name, type, 0); // Assuming 0 likes initially
        pointOfInterests.add(place);
        System.out.println(name + " has been added as a Point of Interest.");
    }

    public static void addPointOfInterest() {
        System.out.print("Enter the name(Location) of the Point of Interest: ");
        String location = scanner.nextLine();

        TypeOfPlace typeOfPlace = getTypeOfPlace(); // This method manages the input for selecting the type

        addPointOfInterestDirectly(location, typeOfPlace); // Use the new method here
    }
    


    public static TypeOfPlace getTypeOfPlace() {

        TypeOfPlace typeOfPlace = TypeOfPlace.City;
        boolean selected;

        do {

            System.out.println("=>>> Choose the type (1. City, 2. Historical Site, 3. Restaurant, 4. Pub/Bar): ");
            String type = scanner.nextLine();

            switch (type) {
                case "1":
                    typeOfPlace = TypeOfPlace.City;
                    selected = true;
                    break;
                case "2":
                    typeOfPlace = TypeOfPlace.HistoricalSite;
                    selected = true;
                    break;
                case "3":
                    typeOfPlace = TypeOfPlace.Restaurant;
                    selected = true;
                    break;
                case "4":
                    typeOfPlace = TypeOfPlace.Pubs;
                    selected = true;
                    break;
                default:
                    selected = false;
                    System.out.println("=>>> Choose one of the available options....");
                    break;
            }
        } while (selected == false);

        return typeOfPlace;
    }

    
    private static void searchPointsOfInterest() {
        // Store the result
        List<Place> result = new ArrayList<>();
        System.out.println("Hint: You can search by typing the name of a location or by specifying a type.");
        System.out.println("Types to search for: HistoricalSite, City, Restaurant, Pubs");
        System.out.println("Location to search for: Eiffel Tower, Central Park, Gordon Ramsay, The Irish Pub\n");
        System.out.print("=>>> Enter the name (Location) or type to search for: ");
    
        String query = scanner.nextLine();

        for (Place place : pointOfInterests) {
            if (place.getLocation().equalsIgnoreCase(query) || place.getType().name().equalsIgnoreCase(query)) {
                result.add(place);
            }
        }

        if (result.isEmpty()) {
            printLine();
            System.out.println("There is no Point of Interest called: " + query);
            printLine();
        } else {
            printLine();
            System.out.println("Result.....");

            for (Place place : result) {
                System.out.println();
                System.out.println("Location Id: " + place.getLocationId());
                System.out.println("Location: " + place.getLocation());
                System.out.println("Type: " + place.getType());
                System.out.println("Likes: " + place.getLikes());
                printLine();
            }

            System.out.print("=>>> Enter the location Id to Like or Comment: ");
            int selectedPlaceId = scanner.nextInt();

            // get selected from places
            Place selectedPlace = null;
            for (Place place : result) {
                if (place.getLocationId() == selectedPlaceId) {
                    selectedPlace = place;
                }
            }

            boolean running = true;

            while (running) {
                System.out.println("1. Like " + selectedPlace.getLocation());
                System.out.println("2. Add Comment to " + selectedPlace.getLocation());
                System.out.println("3. View Previous Comments");
                System.out.println("4. Modify Comment");
                System.out.println("5. Back To Main Menu");
                System.out.print("=>>> Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        likePointOfInterest(selectedPlace.getLocationId());
                        break;
                    case 2:
                        addCommentToPOI(selectedPlace.getLocationId());
                        break;
                    case 3:
                        viewPreviousComments(selectedPlace.getLocationId());
                        break;
                    case 4:
                        PointsOfInterest.modifyCommentById(selectedPlace.getLocationId());
                        break;
                    case 5:
                        running = false;
                        System.out.println("Main Screen.........");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            }

        }
    }


    private static void likePointOfInterest(int locationId) {
        // Find the place with the given locationId
        for (Place place : pointOfInterests) {
            if (place.getLocationId() == locationId) {
                // Increment the like counter for the place
                place.likePlace();
                System.out.println(place.getLocation() + " has been liked.\n");
                return;
            }
        }
        System.out.println("Place with ID " + locationId + " not found.");
    }
  //have to make them public to be accesed by application.java
    public static void listAllPointsOfInterest() {
        System.out.println("List of All Points of Interest:");
        for (Place place : pointOfInterests) {
            System.out.println("ID: " + place.getLocationId() + ", Location: " + place.getLocation() + ", Type: " + place.getType());
        }
    }
   
   
   
   //have to make them public to be accesed by application.java
    public static void viewComments() {
        if (pointOfInterests.isEmpty()) {
            System.out.println("There are no Points of Interest to display comments from.");
            return;
        }
    
        for (Place place : pointOfInterests) {
            List<Review> comments = place.getComments();
            if (comments.isEmpty()) {
                System.out.println("No comments available for " + place.getLocation() + ".");
            } else {
                System.out.println("Comments for " + place.getLocation() + ":");
                for (Review comment : comments) {
                    System.out.println("Comment ID: " + comment.getCommentId() + " - " + comment.getComment());
                }
            }
            System.out.println(); // Add a blank line for better readability between places
        }
    }

    
    
    private static void addCommentToPOI(int locationId) {

        System.out.println("=>>> Enter your comment on: ");

        String comment = scanner.nextLine();

        Optional<Place> placeToComment = pointOfInterests.stream().filter(item -> item.getLocationId() == locationId)
                .findFirst();

        int id = generateCommentIdNewId(placeToComment);

        Review review = new Review(id, comment);

        placeToComment.ifPresentOrElse(item -> item.addComment(review),
                () -> System.err.println("Can it find place with Id" + locationId));

        printLine();
        System.out.println("Comment added");
        printLine();
    }

    private static void viewPreviousComments(long locationId) {
        System.out.println("-_-_-_-_-Comments-_-_-_-_-");
        Optional<Place> place = pointOfInterests.stream()
                .filter(item -> item.getLocationId() == locationId)
                .findFirst();
    
        place.ifPresentOrElse(item -> {
            List<Review> comments = item.getComments();
            if (comments.isEmpty()) {
                System.out.println("No comments available for " + item.getLocation() + ".");
            } else {
                for (Review comment : comments) {
                    System.out.println("Comment ID: " + comment.getCommentId() + " - " + comment.getComment());
                }
            }
        },
        () -> System.err.println("Cannot find place with Id " + locationId));
    }

    private static void modifyCommentById(int locationId) {

        Optional<Place> place = pointOfInterests.stream().filter(item -> item.getLocationId() == locationId)
                .findFirst();

        System.out.println("=>>> Enter the comment Id to modify: ");
        int selectedCommetId = scanner.nextInt();

        Review reviewToModify = place.get().getCommReviewById(selectedCommetId);

        System.out.println("Id: " + reviewToModify.getCommentId() + " Content: " + reviewToModify.getComment());

        System.out.println("=>>> Enter a new comment: ");
        String newCommet = scanner.next();

        place.get().modifyReview(selectedCommetId, newCommet);

        System.out.println("Comment has been modified......");

    }

    public static void deleteCommentByAdmin() {
        if (pointOfInterests.isEmpty()) {
            System.out.println("There are no Points of Interest available.");
            return;
        }
    
        listAllPointsOfInterest(); // Assuming this method lists all points of interest
    
        System.out.println("=>>> Enter the ID of the place from which you want to delete a comment: ");
        int selectedPlaceId = scanner.nextInt();
    
        Optional<Place> selectedPlace = pointOfInterests.stream()
                .filter(item -> item.getLocationId() == selectedPlaceId)
                .findFirst();
    
        if (selectedPlace.isPresent()) {
            List<Review> comments = selectedPlace.get().getComments();
            if (comments.isEmpty()) {
                System.out.println("No comments available for deletion in " + selectedPlace.get().getLocation() + ".");
                return;
            }
    
            comments.forEach(comment -> System.out.println("Comment ID: " + comment.getCommentId() + " - " + comment.getComment()));
    
            System.out.println("=>>> Enter the ID of the comment to delete: ");
            int commentId = scanner.nextInt();
    
            boolean
            removed = selectedPlace.get().removeComment(commentId);
            if (removed) {
                 System.out.println("Comment deleted successfully.");
             } else {
            System.out.println("No comment found with the specified ID.");
            }
            } else {
            System.out.println("No place found with the specified ID.");
        }
    }


    private static void printLine() {
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
        System.out.println();
    }

    private static int generatePlaceIdNewId() {

        if (pointOfInterests.isEmpty()) {
            return 1;
        }

        int maxId = pointOfInterests.stream()
                .mapToInt(Place::getLocationId)
                .max()
                .orElse(0);

        return maxId + 1;
    }

    private static int generateCommentIdNewId(Optional<Place> placeToComment) {

        if (placeToComment.isEmpty()) {
            return 1;
        }
        return placeToComment.get().getCommentIdMax() + 1;
    }
}