package BusResv;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;

public class BusDemo {
    public static void main(String[] args)  {

        BusDAO busdao = new BusDAO();
        try {
            busdao.displayBusInfo();

            Scanner sc = new Scanner(System.in);

            int userOpt = 1;
            while (userOpt == 1){
                System.out.println("Enter 1 to book and 2 to exit");
                userOpt = sc.nextInt();
                if (userOpt==1) {
                    Booking booking = new Booking();
                    if (booking.isAvailable()) {
                        BookingDAO bookingdao = new BookingDAO();
                        bookingdao.addBooking(booking);
                    } else {
                        System.out.println("Sorry , the tickets are reserved. Please try another Bus or Date");
                    }
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println(e);

        }
    }
}
