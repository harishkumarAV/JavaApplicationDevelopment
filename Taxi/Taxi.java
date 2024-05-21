import java.util.*;

public class Taxi {
    static int taxicount = 0;
    int id;
    boolean booked;
    char current_spot;
    int freetime;
    int total_earnings;
    List<String> trip;

    public Taxi() {
        booked = false;
        current_spot = 'A';
        freetime = 0;
        total_earnings = 0;
        trip = new ArrayList<>();
        taxicount = taxicount + 1;
        id = taxicount;
    }

    public void setDetails(boolean booked, char current_spot, int freetime, int total_earnings, String TripDetail) {
        this.booked = booked;
        this.current_spot = current_spot;
        this.freetime = freetime;
        this.total_earnings = total_earnings;
        this.trip.add(TripDetail);
    }

    public void print() {
        System.out.println("Taxi No:" + this.id + " Total Earnings:" + this.total_earnings);
        System.out.println("BookingID CustomerID From To PickupTime DropTime Amount");
        for (String t : trip) {
            System.out.println(t);
        }
        System.out.println("----------------------------------------------------------");
    }
}
