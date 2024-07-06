public class Passenger {
    static int id = 1;
    String name;
    int age;
    String preferred_berth;
    int passenger_id;
    String allocated_berth;
    int seat_number;
    public Passenger(String name, int age,String preferred_berth){
        this.name =  name;
        this.age = age;
        this.preferred_berth = preferred_berth;
        this.passenger_id = id++;
        allocated_berth = "";
        seat_number = -1;
    }
}
