import java.util.*;

public class Booking {
    public static List<Taxi> createTaxis(int n) {
        List<Taxi> taxis = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            Taxi t = new Taxi();
            taxis.add(t);
        }
        return taxis;
    }

    public static List<Taxi> getFreeTaxis(List<Taxi> l, char pickup_point, char drop_point, int pickup_time) {
        List<Taxi> freeTaxis = new ArrayList<>();
        for (Taxi li : l) {
            if (li.freetime <= pickup_time && 
                (Math.abs((li.current_spot - 'A') - (pickup_point - 'A')) <= pickup_time - li.freetime)) {
                freeTaxis.add(li);
            }
        }
        return freeTaxis;
    }

    public static void bookTaxis(int customer_id, char pickup_point, char drop_point, int pickup_time, List<Taxi> freeTaxis) {
        int min = Integer.MAX_VALUE;
        int d_pick_drop = 0;
        int earning = 0;
        int next_freetime = 0;
        char nextSpot = 'Z';
        Taxi bookedTaxi = null;
        String TripDetail = "";
        
        for (Taxi t : freeTaxis) {
            int d_customer_taxi = Math.abs((t.current_spot - 'A') - (pickup_point - 'A')) * 15;
            if (d_customer_taxi < min) {
                bookedTaxi = t;
                d_pick_drop = Math.abs((pickup_point - 'A') - (drop_point - 'A')) * 15;
                earning = 100 + (d_pick_drop - 5) * 10;
                int drop_time = pickup_time + d_pick_drop / 15;
                next_freetime = drop_time;
                nextSpot = drop_point;
                TripDetail = customer_id + " " + pickup_point + " " + drop_point + " " + pickup_time + " " + drop_time + " " + earning;
                min = d_customer_taxi;
            }
        }

        if (bookedTaxi != null) {
            bookedTaxi.setDetails(true, nextSpot, next_freetime, bookedTaxi.total_earnings + earning, TripDetail);
            System.out.println("Taxi " + bookedTaxi.id + " Booked Successfully");
        } else {
            System.out.println("No suitable taxi found.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Taxi> l = createTaxis(4);
        int id = 1;

        while (true) {
            System.out.println("0 -> Book Taxi");
            System.out.println("1 -> Print Taxi Details");
            int choice = sc.nextInt();

            System.out.println("1 -> Print Taxi Details");
            switch (choice) {
                case 0:
                    int customer_id = id;
                    System.out.println("Enter the pickup point (A-F)");
                    char pickup_point = sc.next().charAt(0);
                    System.out.println("Enter the dropping point (A-F)");
                    char drop_point = sc.next().charAt(0);
                    System.out.println("Enter the pickup time");
                    int pickup_time = sc.nextInt();

                    if (pickup_point > 'F' || pickup_point < 'A') {
                        System.out.println("Please enter a valid pickup point. It should be in range from A-F");
                        break;
                    }
                    if (drop_point > 'F' || drop_point < 'A') {
                        System.out.println("Please enter a valid dropping point. It should be in range from A-F");
                        break;
                    }

                    List<Taxi> freeTaxis = getFreeTaxis(l, pickup_point, drop_point, pickup_time);
                    if (freeTaxis.isEmpty()) {
                        System.out.println("Sorry, there are no free taxis available at the moment. Please try again after some time. Thank you.");
                    } else {
                        Collections.sort(freeTaxis, Comparator.comparingInt(a -> a.total_earnings));
                        bookTaxis(customer_id, pickup_point, drop_point, pickup_time, freeTaxis);
                        id++;
                    }
                    break;

                case 1:
                    for (Taxi t : l) {
                        t.print();
                    }
                    break;

                default:
                    return;
            }
        }
    }
}
