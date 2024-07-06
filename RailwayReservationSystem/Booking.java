import java.util.*;
public class Booking{
    public static void book(Passenger p){
        Tickets t = new Tickets();
        if(t.avail_wl == 0){
            System.out.println("No Tickets Available");
            return;
        }
        if((p.preferred_berth.equals("L") && t.avail_lb>0) || 
        (p.preferred_berth.equals("M") && t.avail_mb>0) ||
        (p.preferred_berth.equals("U") && t.avail_ub>0)){
            System.out.println("Preferred Berth Available");
            if(p.preferred_berth.equals("U")){
                System.out.print("Upper berth Given");
                t.book(p, t.ub_positions.get(0), "U");
                t.ub_positions.remove(0);
                t.avail_ub--;
            }else if(p.preferred_berth.equals("M")){
                System.out.println("Middle berth Given");
                t.book(p, t.mb_positions.get(0), "M");
                t.mb_positions.remove(0);
                t.avail_mb--;
            }else{
                System.out.println("Lower berth Given");
                t.book(p, t.lb_positions.get(0), "L");
                t.lb_positions.remove(0);
                t.avail_lb--;
            }
        }
        else if(t.avail_lb>0){
            System.out.println("Lower berth Given");
            t.book(p, t.lb_positions.get(0), "L");
            t.avail_lb--;
            
        }
        else if(t.avail_mb>0){
            System.out.println("Middle berth Given");
            t.book(p, t.mb_positions.get(0), "M");
            t.avail_mb--;
            
        }else if(t.avail_ub>0){
            System.out.print("Upper berth Given");
            t.book(p, t.ub_positions.get(0), "U");
            t.avail_ub--;
            
        }
        else if(t.avail_rac>0){
            System.out.print("Added to RAC");
            t.addRac(p,t.rac_positions.get(0),"RAC");
            
        }else if(t.avail_wl>0){
            System.out.print("Added to Waiting List");
            t.addWL(p,t.wl_positions.get(0),"WL");
        }
    }
    public  static void cancel(int id){
        Tickets t = new Tickets();
        if(!t.passengers.containsKey(id))
        {
            System.out.println("Invalid id.Check it properly");
        }else{
            t.cancel(id);
        }
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while(flag){
            System.out.println(" 1. Book Ticket \n 2. Cancel Ticket \n 3. Available Tickets \n 4. Booked Tickets \n 5. Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter the name");
                    sc.nextLine();
                    String name = sc.nextLine();
                    System.out.println("Enter the age");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter the preferred berth(U/M/L)");
                    String preferred_berth = sc.nextLine();
                    Passenger p = new Passenger(name, age, preferred_berth);
                    book(p);
                    break;
            
                case 2:
                    System.out.println("Enter the passenger id to cancel the ticket");
                    int id = sc.nextInt();
                    cancel(id);
                    break;
            
                case 3:
                    Tickets t = new Tickets();
                    t.printavail();
                    break;
                    
                case 4:
                    Tickets tr = new Tickets();
                    tr.print();
                    break;
            
                case 5:
                    flag = false;
                    break;
            
                default:
                    break;
            }
        }
        sc.close();
    }
}
