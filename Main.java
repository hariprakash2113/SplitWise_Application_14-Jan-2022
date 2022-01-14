import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static List<User> users =new ArrayList<>();
    static{
        users.add(new User("Hari", "hari", "1108", "99650"));
        users.add(new User("Sundaresan","sundar","1603","93457"));
        users.add(new User("Niresh","niresh","0309", "63476"));
        users.add(new User("Nithish","newton","0502","824823"));
    }
    public static void main(String[] args) {
        User.login();
    }
}
