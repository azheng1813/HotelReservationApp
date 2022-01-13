package cmdMM;

import javax.naming.PartialResultException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class testingClass {
    public static <Dateformat> void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Date:");
        String stringDate = scanner.nextLine();
        Date dateDate = null;
        try {
            dateDate = formatter.parse(stringDate);
        } catch (ParseException ex) {
            System.out.println("not a date");
        }
        System.out.println(dateDate);
    }
}
