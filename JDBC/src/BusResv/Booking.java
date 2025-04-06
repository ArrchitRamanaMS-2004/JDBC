package BusResv;

import java.sql.SQLException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Booking {
    private String passengerName;
    private final int busNo;
    private Date date;
    public Booking (){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Passenger Name : ");
        this.passengerName = sc.nextLine();
        System.out.println("Enter Bus Number : ");
        this.busNo = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter departure date (dd-MM-yyyy) : ");
        String dateInput = sc.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            java.util.Date utildate = dateFormat.parse(dateInput);
            this.date = new Date(utildate.getTime());
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format",e);
        }
    }
    public String getPassengerName(){ return passengerName; }
    public int getBusNo() { return busNo; }
    public Date getDate() { return date; }
    public void setPassengerName(String passengerName){ this.passengerName = passengerName; }
    public void setDate(Date date) { this.date = date; }

    public boolean isAvailable() throws SQLException {

        BusDAO busdao = new BusDAO();
        BookingDAO bookingdao = new BookingDAO();

        int checkCapacity = busdao.getCapacity(this.busNo);

        //checking no of bookings already happened in the bus ( which we want to include our booking ) and in the date ( in which we want to include our booking )
        int booked = bookingdao.getBookedCount(this.busNo,this.date);

        return booked < checkCapacity;
    }
}
