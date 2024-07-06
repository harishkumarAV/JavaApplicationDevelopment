import java.util.*;
public class Tickets {
    static int avail_lb = 20;
    static int avail_mb = 20;
    static int avail_ub = 20;
    static int avail_rac = 18;
    static int avail_wl = 10;
    static Queue<Integer> rac = new LinkedList<>();
    static Queue<Integer> wl = new LinkedList<>();
    static List<Integer> booked_tickets = new ArrayList<>();
    static List<Integer> lb_positions = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20));
    static List<Integer> mb_positions = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20));
    static List<Integer> ub_positions = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20));
    static List<Integer> rac_positions = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18));
    static List<Integer> wl_positions = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
    static Map<Integer,Passenger> passengers = new HashMap<>();
    public void book(Passenger p , int berth_info , String allocated_berth){
        p.seat_number = berth_info;
        p.allocated_berth = allocated_berth;
        passengers.put(p.passenger_id , p);
        booked_tickets.add(p.passenger_id);
        System.out.println("-----------------------------------Booked!");
    }
    public void addRac(Passenger p,int rac_info,String allocated_RAC){
        p.seat_number = rac_info;
        p.allocated_berth = allocated_RAC;
        passengers.put(p.passenger_id , p);
        rac.add(p.passenger_id);
        rac_positions.remove(0);
        avail_rac--;
        System.out.println("-----------------------------------Added to RAC!");
    }
    public void addWL(Passenger p,int wl_info , String allocated_WL){
        p.seat_number = wl_info;
        p.allocated_berth = allocated_WL;
        passengers.put(p.passenger_id , p);
        wl.add(p.passenger_id);
        wl_positions.remove(0);
        avail_wl--;
        System.out.println("-----------------------------------Added to WL!");
    }
    public void cancel(int id){
        Passenger p = passengers.get(id);
        passengers.remove(id);
        int position = p.seat_number;
        System.out.println("Your Ticket has been Cancelled! Thank you.");
        if(p.allocated_berth.equals("U")){
            avail_ub++;
            ub_positions.add(position);
        } 
        else if(p.allocated_berth.equals("M")){
            avail_mb++;
            mb_positions.add(position);
            
        } 
        else if(p.allocated_berth.equals("L")){
            avail_lb++;
            lb_positions.add(position);
        }
        //RAC
        if(rac.size() > 0){
            Passenger prac = passengers.get(rac.poll());
            int position_rac = prac.seat_number;
            rac_positions.add(position_rac);
            rac.remove(Integer.valueOf(prac.passenger_id));
            avail_rac++;

            //WL
            if(wl.size()>0){
                Passenger pwl = passengers.get(wl.poll());
                int position_wl = pwl.seat_number;
                wl_positions.add(position_wl);
                wl.remove(Integer.valueOf(pwl.passenger_id));

                pwl.seat_number = rac_positions.get(0);
                pwl.allocated_berth = "RAC";
                rac_positions.remove(0);
                rac.add(pwl.passenger_id);

                avail_wl++;
                avail_rac--;
            }
            Booking.book(prac);
        } 
    }
    public static void printavail(){
        System.out.println("Available Lower berth: " + avail_lb);
        System.out.println("Available Middle berth: " + avail_mb);
        System.out.println("Available Upper berth: " + avail_ub);
        System.out.println("Available RAC : " + avail_rac);
        System.out.println("Available WL : " + avail_wl);
    }
    public static void print(){
        if (passengers.size() == 0) {
            System.out.print("No Passengers at the moment!");
            return;
        }
        for(Passenger p :passengers.values()){
            System.out.println("Passenger id: " + p.passenger_id);
            System.out.println("Passenger name: " + p.name);
            System.out.println("Passenger age: " + p.age);
            System.out.println("Seat :" + p.seat_number + p.allocated_berth);
            System.out.println("-----------------------------------------");
        }
    }
}
