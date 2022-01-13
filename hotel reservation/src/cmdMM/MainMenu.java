package cmdMM;

import api.HotelResource;
import model.IRoom;
import model.Reservation;
import service.ReservationService;

import javax.imageio.plugins.tiff.ExifParentTIFFTagSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MainMenu {

    public static void main(String args[]){
        mainMenu();
    }


    public static void mainMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Main Menu");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservation");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("Please type and enter 1 to 5");
        String menuInput = scanner.nextLine();
        while(!(menuInput.equals("1") || menuInput.equals("2") || menuInput.equals("3") || menuInput.equals("4") || menuInput.equals("5"))){
            System.out.println("Invalid input, please type and enter 1 to 5");
            menuInput = scanner.nextLine();
        }
        resultOfMM(menuInput, scanner);
    }


    public static void resultOfMM(String userInput, Scanner scanner){
        switch(userInput){
            case "1":
                findReserveRoom(scanner);
                break;
            case "2":
                seeReservation(scanner);
                break;
            case "3":
                createAccount(scanner);
                break;
            case "4":
                adminMenu();
                break;
            case "5":
                break;
        }
    }

    public static void findReserveRoom(Scanner scanner){
        DateFormat formatter = new SimpleDateFormat("dd/mm/yyy");
        System.out.println("Do you have an account? Yes or no?");
        String accountSetUp = scanner.nextLine();
        while(!(accountSetUp.toLowerCase().equals("yes") || accountSetUp.toLowerCase().equals("no"))){
            System.out.println("Invalid response, please type yes or no");
            accountSetUp = scanner.nextLine();
        }
        if(accountSetUp.toLowerCase().equals("yes")){
            System.out.println("Please enter your email");
            String customerEmail = scanner.nextLine();
            if(HotelResource.getCustomer(customerEmail) == null){
                throw new IllegalArgumentException("Account linked with email not found. Please make an account or use another email");
            }
            System.out.println("What is your check in date in the day/month/year format?");
            String checkInString = null;
            Date checkInDate = null;
            try {
                checkInString = scanner.nextLine();
                checkInDate = formatter.parse(checkInString);
            } catch(ParseException ex){
                System.out.println("you entered a date in the wrong format, please type the check in date in the day/month/year format");
                findReserveRoom(scanner);
            }
            System.out.println("What is your check out date in the day/month/year format?");
            String checkOutString = null;
            Date checkOutDate = null;
            try {
                checkOutString = scanner.nextLine();
                checkOutDate = formatter.parse(checkOutString);
            } catch(ParseException ex){
                System.out.println("You entered a date in the wrong format, please type the check out date in the day/month/year format");
                findReserveRoom(scanner);
            }

            if(checkInDate.after(checkOutDate)){
                System.out.println("Invalid dates, the check in date is after the check out date");
                mainMenu();
            }
            Collection<IRoom> possibleRooms = HotelResource.findARoom(checkInDate, checkOutDate);
            if(possibleRooms.size() == 0){
                System.out.println("Unfortunately there are no rooms available for those days");
                mainMenu();
            }
            else{
                System.out.println("Here are the available rooms for those days: ");
                for(IRoom room : possibleRooms)
                System.out.print(room.toString() + ", ");
            }
            System.out.println("\nWhat room do you want");
            System.out.println("Exit");
            String wantedRoom = scanner.nextLine();
            if(wantedRoom.toLowerCase().equals("exit")){
                mainMenu();
            }
            String roomNumRegex = "^([0-9]+)$";
            Pattern pattern = Pattern.compile(roomNumRegex);
            if(!pattern.matcher(wantedRoom).matches()){
                System.out.println("Input is not a number, please type a room number from the list");
            }
            Boolean exists = false;
            for(IRoom room : possibleRooms){
                if(room.getRoomNumber().equals(wantedRoom)){
                    exists = true;
                }
            }
            if(exists == false){
                System.out.println("Room number doesn't exist please enter a room number from the list");
                mainMenu();
            }
            IRoom room = HotelResource.getRoom(wantedRoom);
            Reservation reservation = HotelResource.bookARoom(customerEmail, room, checkInDate, checkOutDate);
            System.out.println("Reservation successful");
            System.out.println(reservation);
        }
        if(accountSetUp.toLowerCase().equals("no")) {
            createAccount(scanner);
            HotelResource.printThing();
            System.out.println("Please enter your email");
            String customerEmail = scanner.nextLine();
            if(HotelResource.getCustomer(customerEmail) == null){
                throw new IllegalArgumentException("Account linked with email not found. Please make an account or use another email");
            }
            System.out.println("What is your check in date in the day/month/year format?");
            String checkInString = null;
            Date checkInDate = null;
            try {
                checkInString = scanner.nextLine();
                checkInDate = formatter.parse(checkInString);
            } catch(ParseException ex){
                System.out.println("you entered a date in the wrong format, please type the check in date in the day/month/year format");
                findReserveRoom(scanner);
            }
            System.out.println("What is your check out date in the day/month/year format?");
            String checkOutString = null;
            Date checkOutDate = null;
            try {
                checkOutString = scanner.nextLine();
                checkOutDate = formatter.parse(checkOutString);
            } catch(ParseException ex){
                System.out.println("You entered a date in the wrong format, please type the check out date in the day/month/year format");
                findReserveRoom(scanner);
            }

            if(checkInDate.after(checkOutDate)){
                System.out.println("Invalid dates, the check in date is after the check out date");
                mainMenu();
            }
            Collection<IRoom> possibleRooms = HotelResource.findARoom(checkInDate, checkOutDate);
            if(possibleRooms.size() == 0){
                System.out.println("Unfortunately there are no rooms available for those days");
                mainMenu();
            }
            else{
                System.out.println("Here are the available rooms for those days: ");
                for(IRoom room : possibleRooms)
                    System.out.print(room.toString() + ", ");
            }
            System.out.println("\nWhat room do you want");
            System.out.println("Exit");
            String wantedRoom = scanner.nextLine();
            if(wantedRoom.toLowerCase().equals("exit")){
                mainMenu();
            }
            String roomNumRegex = "^([0-9]+)$";
            Pattern pattern = Pattern.compile(roomNumRegex);
            if(!pattern.matcher(wantedRoom).matches()){
                System.out.println("Input is not a number, please type a room number from the list");
                mainMenu();
            }
            Boolean exists = false;
            for(IRoom room : possibleRooms){
                if(room.getRoomNumber().equals(wantedRoom)){
                    exists = true;
                }
            }
            if(exists == false){
                System.out.println("Room number doesn't exist please enter a room number from the list");
                mainMenu();
            }
            IRoom room = HotelResource.getRoom(wantedRoom);
            Reservation reservation = HotelResource.bookARoom(customerEmail, room, checkInDate, checkOutDate);
            System.out.println("Reservation successful");
            System.out.println(reservation);

        }

    }

    public static void seeReservation(Scanner scanner){
        System.out.println("Please type your email");
        String customerEmail = scanner.nextLine();
        if(HotelResource.getCustomer(customerEmail) == null){
            System.out.println("The account linked to this email does not exist, please try another email or make a new account");

        }else{
            Collection<Reservation> reservations = HotelResource.getCustomerReservations(customerEmail);
                if (reservations.size() == 0) {
                    System.out.println("you do not have any reservations");
                }
                for(Reservation reservation : reservations){
                    System.out.println(reservation.toString());
                }
            }
        }



    public static void createAccount(Scanner scanner){
        System.out.println("Type out your email.");
        String customerEmail = scanner.nextLine();
        //check is email exists
        if(HotelResource.getCustomer(customerEmail) != null){
            System.out.println("You already have an account");

        }
        else {
            System.out.println("Type out your first name");
            String customerFirstName = scanner.nextLine();
            System.out.println("Type out your last name");
            String customerLastName = scanner.nextLine();
            HotelResource.createACustomer(customerEmail, customerFirstName, customerLastName);
            System.out.println("Account created successfully");
        }

    }

    public static void adminMenu(){

    }
}

