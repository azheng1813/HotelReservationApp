package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.*;

public class HotelResource {

    //private static HotelResource hotelResource;
    //private static final CustomerService customerService = CustomerService.getInstance();
    //private static final ReservationService reservationService = ReservationService.getInstance();

    /*
    public HotelResource getInstance(){
        if(hotelResource == null){
            hotelResource = new HotelResource();
        }
        return hotelResource;
    }
    */

    public static Customer getCustomer(String email){
        return CustomerService.getCustomer(email);
    }

    public static void createACustomer(String email, String firstName, String lastName){
        CustomerService.addCustomer(email, firstName, lastName);

    }

    public static IRoom getRoom(String roomNumber){
        return ReservationService.getARoom(roomNumber);
    }

    public static Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutdate){
        return ReservationService.reserveARoom(CustomerService.getCustomer(customerEmail) , room, checkInDate, checkOutdate);
    }

    public static Collection<Reservation> getCustomerReservations(String customerEmail){
        return ReservationService.getCustomersReservation(CustomerService.getCustomer(customerEmail));
    }

    public static Collection <IRoom> findARoom(Date checkIn, Date checkOut){
        return ReservationService.findRooms(checkIn, checkOut);
    }
    public static void printThing(){
        ReservationService.printAllReservations();
    }
}
