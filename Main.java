import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static List<User> users =new ArrayList<>();
    static{
        users.add(new User("Hari", "hari", "1108", "99650"));
    }
    public static void main(String[] args) {
        User.login();
    }
}
