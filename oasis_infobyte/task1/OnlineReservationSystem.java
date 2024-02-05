package oasis_infobyte.task1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {

    String password;

    User(String password) 
    {
        this.password = password;
    }

}

class Reservation {

    String trainNumber;
    String trainName;
    String classType;
    String date;
    String source;
    String destination;

    Reservation(String trainNumber, String trainName, String classType, String date, String source, String destination) 
    {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.date = date;
        this.source = source;
        this.destination = destination;

    }
}

public class OnlineReservationSystem 
{

    private static Map<String, User> usersDatabase = new HashMap<>();
    private static Map<String, Reservation> reservationsDatabase = new HashMap<>();

    public static void main(String[] args) 
    {

        initializeUsers();

        Scanner scanner = new Scanner(System.in);

        // Login
        System.out.println("Welcome to Online Reservation System");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (authenticateUser(username, password)) 
        {
            System.out.println("Login successful! You can now access the main system.");

            // Reservation
            System.out.println("Reservation System");
            System.out.print("Enter train number: ");
            String trainNumber = scanner.nextLine();
            System.out.print("Enter train name: ");
            String trainName = scanner.nextLine();
            System.out.print("Enter class type: ");
            String classType = scanner.nextLine();
            System.out.print("Enter date of journey: ");
            String date = scanner.nextLine();
            System.out.print("Enter source: ");
            String source = scanner.nextLine();
            System.out.print("Enter destination: ");
            String destination = scanner.nextLine();

            Reservation reservation = new Reservation(trainNumber, trainName, classType, date, source, destination);
            reservationsDatabase.put(generatePNR(), reservation);
            System.out.println("Reservation successful! PNR: " + generatePNR());

            // Cancellation
            System.out.println("Cancellation Form");
            System.out.print("Enter PNR number for cancellation: ");
            String pnr = scanner.nextLine();
            cancelReservation(pnr);

        } else 
        {
            System.out.println("Invalid username or password. Please try again.");
        }

        scanner.close();
    }

    private static void initializeUsers() 
    {

        usersDatabase.put("user1", new User("password1"));
        usersDatabase.put("Aniruddha", new User("Password@123"));
        
    }

    private static boolean authenticateUser(String username, String password) 
    {

        return usersDatabase.containsKey(username) && usersDatabase.get(username).password.equals(password);
    }

    private static String generatePNR() 
    {

        return "PNR" + System.currentTimeMillis();

    }

    private static void cancelReservation(String pnr) 
    {

        if (reservationsDatabase.containsKey(pnr)) 
        {
            System.out.println("Reservation details for PNR " + pnr + ":");
            Reservation reservation = reservationsDatabase.get(pnr);

            // Display reservation details
            System.out.println("Train Number: " + reservation.trainNumber);
            System.out.println("Train Name: " + reservation.trainName);
            System.out.println("Class Type: " + reservation.classType);
            System.out.println("Date of Journey: " + reservation.date);
            System.out.println("Source: " + reservation.source);
            System.out.println("Destination: " + reservation.destination);

            // Ask for confirmation
            System.out.print("Press OK to confirm cancellation: ");
            Scanner scanner = new Scanner(System.in);
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("OK")) 
            {
                reservationsDatabase.remove(pnr);
                System.out.println("Reservation canceled successfully.");
            } 
            else {
                System.out.println("Cancellation aborted.");
            }
        } 
        else 
        {
            System.out.println("Invalid PNR number. No reservation found.");
        }

    }

}

