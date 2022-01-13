package model;

public class Room implements IRoom{

    public  String roomNumber;
    public Double price;
    public RoomType enumeration;

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    @Override
    public boolean isFree() {
        if(price == 0){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return "Room number: " + roomNumber + "\n Room price: " + price;
    }
}
