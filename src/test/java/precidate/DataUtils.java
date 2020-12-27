package precidate;

import java.util.ArrayList;
import java.util.List;

public class DataUtils {
    private static List<Employee> emps = new ArrayList<>();

    static {
        emps.add(new Employee(1L, "Morton", 22, 8000.0));
        emps.add(new Employee(2L, "Dahlia", 20, 13000.0));
        emps.add(new Employee(3L, "Babb", 23, 7000.0));
        emps.add(new Employee(4L, "Rice", 21, 15000.0));
        emps.add(new Employee(5L, "Handy", 26, 13000.0));
        emps.add(new Employee(6L, "Rudy", 30, 14000.0));
        emps.add(new Employee(7L, "Grady", 27, 12000.0));
        emps.add(new Employee(8L, "Brenton", 32, 6000.0));
        emps.add(new Employee(9L, "Vinson", 33, 7000.0));
        emps.add(new Employee(10L, "Kemp", 23, 14000.0));
        emps.add(new Employee(11L, "Sebastian", 22, 12000.0));
        emps.add(new Employee(12L, "Evangeline", 24, 18000.0));
        emps.add(new Employee(13L, "Lisette", 29, 8000.0));
        emps.add(new Employee(14L, "Wilkes", 25, 7000.0));
        emps.add(new Employee(15L, "Leach", 33, 6000.0));
        emps.add(new Employee(16L, "Geiger", 32, 12000.0));
        emps.add(new Employee(17L, "Holden", 34, 13000.0));
        emps.add(new Employee(18L, "Thorpe", 26, 15000.0));
        emps.add(new Employee(19L, "Adrienne", 23, 16000.0));
        emps.add(new Employee(20L, "Calderon", 21, 14000.0));
    }
    public static List<Employee> getEmployees() {
        return emps;
    }
}