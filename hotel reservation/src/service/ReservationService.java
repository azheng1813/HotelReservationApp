package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.sql.Array;
import java.util.*;

public class ReservationService {

    public Collection<IRoom> reservedRooms = new HashSet<>();
    public static List<Reservation> reservedList = new ArrayList<>();
    public static List<IRoom> rooms = new ArrayList<>();
    private static Map<IRoom, Reservation> reservations = new HashMap<>();

    private static ReservationService reservationService;

    public static ReservationService getInstance(){
        if(reservationService == null){
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    public void addRoom(IRoom room){
        reservedRooms.add(room);
    }

    public static IRoom getARoom(String roomId){
        for(IRoom room : rooms){
            if(roomId.equals(room.getRoomNumber())){
                return room;
            }
        }
        System.out.println("This room is not available with us");
        return null;
    }

    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation newRoom = new Reservation(customer, room, checkInDate, checkOutDate);
        System.out.println("Room reserved");

        reservedList.add(newRoom);

        return newRoom;
    }

    public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        Collection<IRoom> possibleRooms = new HashSet<>();

        if(reservedList.size() == 0){
            possibleRooms = rooms;
            return possibleRooms;
        }
        else{
            for(Reservation reservation : reservedList){
                for(IRoom possRoom : rooms){
                    if((possRoom.getRoomNumber().equals(reservation.room.getRoomNumber())) && ((checkInDate.before(reservation.getCheckInDate()) && checkOutDate.before(reservation.getCheckOutDate())) || (checkInDate.after(reservation.getCheckOutDate()) && checkOutDate.after(reservation.getCheckOutDate()))) || (!reservation.room.getRoomNumber().contains(possRoom.getRoomNumber()))){
                        possibleRooms.add(possRoom);
                    }
                    else{
                        if(possRoom.getRoomNumber().equals(reservation.room.getRoomNumber())){
                            possibleRooms.remove(possRoom);
                        }
                    }

                }
            }
        }
        return possibleRooms;
    }

    public static Collection<Reservation> getCustomersReservation(Customer customer){
        Collection<Reservation> customersReserving = new ArrayList<>();
        for(Reservation cusReserving : reservedList){
            if(cusReserving.customer.equals(customer)){
                customersReserving.add(cusReserving);
            }
        }
        return customersReserving;
    }

    public static void printAllReservations(){
        for(Reservation reservation : reservedList){
            System.out.println(reservation);
        }
    }


}
