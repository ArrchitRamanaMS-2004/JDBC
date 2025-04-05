package BusResv;

import java.util.Scanner;
import java.util.ArrayList;

public class BusDemo {
    public static void main(String[] args) {

        ArrayList<Bus> buses = new ArrayList<Bus>();

        buses.add(new Bus(1,true,45));
        buses.add(new Bus(2,false,50));
        buses.add(new Bus(3,true,48));

        Scanner sc = new Scanner(System.in);

        for (Bus b : buses){
            b.displayBusInfo();
        }

        int userOpt = 1;
        while (userOpt == 1){
            System.out.println("Enter 1 to book and 2 to exit");
            userOpt = sc.nextInt();
            if (userOpt==1)
                System.out.println("booking");
        }
    }
}
