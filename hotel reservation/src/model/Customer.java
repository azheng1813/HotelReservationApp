package model;

import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;

    public Customer(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

        String regEx = "^(.+)@(.+)\\.(.+)$";
        Pattern pattern = Pattern.compile(regEx);

        if(!pattern.matcher(this.email).matches()){
            throw new IllegalArgumentException("Invalid email");
        }

    }

    public String getEmail(){
        return this.email;
    }

    @Override
    public String toString(){
        return "First name: " + firstName + "\nLast name: " + lastName + "\nEmail: " + email;
    }


}
