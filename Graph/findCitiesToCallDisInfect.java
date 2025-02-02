package Graph;
import java.util.*;
public class findCitiesToCallDisInfect {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        int cities= scanner.nextInt();
        int buses = scanner.nextInt();
        System.out.println(cities + " " + buses);
        String nextLine = scanner.nextLine();
        while(!scanner.nextLine().isEmpty()) {
            int org = scanner.nextInt();
            int dest = scanner.nextInt();
            System.out.println("You entered: " + org + dest);

        }
        
        scanner.close();
    }
}