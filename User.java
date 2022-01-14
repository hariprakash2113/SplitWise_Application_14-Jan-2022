import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
    String Name;
    String userName;
    String password;
    String mobile;
    String pays = "";
    Integer walletAmount = 5000;
    List<Due> dues = new ArrayList<>();

    public User(String name, String userName, String password, String mobile) {
        Name = name;
        this.userName = userName;
        this.password = password;
        this.mobile = mobile;
    }

    static int findUserInd(String userName) {
        for (int i = 0; i < Main.users.size(); i++) {
            if (Main.users.get(i).userName.equals(userName)) {
                return i;
            }
        }
        return -1;
    }

    static void login() {
        System.out.print("\033[H\033[2J");
        System.out.println("-----SPLIT WISE APPLICATION-----");
        System.out.print("Enter User name : ");
        String userName = Main.sc.nextLine();
        int user_index = findUserInd(userName);
        if (user_index == -1) {
            System.out.println("User with Username " + userName + " not Found !");
            System.out.println("Press any key to continue......");
            Main.sc.nextLine();
            login();
        }
        System.out.print("Enter Password or 0 to Forgot Password : ");
        String password = Main.sc.nextLine();
        if (password.equals("0")) {
            forgetPassword(user_index);
        }
        if (Main.users.get(user_index).password.equals(password)) {
            userPage(user_index);
        } else {
            System.out.println("Incorrect password !\nTry Again");
            System.out.println("Press any key to continue......");
            Main.sc.nextLine();
            login();
        }
    }

    private static void forgetPassword(int user_index) {
        System.out.print("\033[H\033[2J");
        System.out.print("Enter Your Mobile number or 0 to Exit : ");
        String mobile = Main.sc.nextLine();
        if (mobile.equals("0")) {
            login();
        }
        if (Main.users.get(user_index).mobile.equals(mobile)) {
            System.out.print("Enter OTP : ");
            String otp = Main.sc.nextLine();
            if (otp.equals("123456")) {
                System.out.println("Your Password is => " + Main.users.get(user_index).password);
                System.out.println("Press any key to continue......");
                Main.sc.nextLine();
                login();
            } else {
                System.out.println("Wrong OTP !\nTry again");
                System.out.println("Press any key to continue......");
                Main.sc.nextLine();
                forgetPassword(user_index);
            }
        } else {
            System.out.println("Entered Mobile number Doesn't match");
            System.out.println("Press any key to continue......");
            Main.sc.nextLine();
            forgetPassword(user_index);
        }
    }

    private static void userPage(int user_index) {
        System.out.print("\033[H\033[2J");
        System.out.printf(">>>>>>>>>> Welcome , %s <<<<<<<<<<\n", Main.users.get(user_index).Name);
        System.out.println("    -> Enter 1 to add expense");
        System.out.println("    -> Enter 2 to add a Partner");
        System.out.println("    -> Enter 3 to view/Repay Expense");
        System.out.println("    -> Enter 4 to view All your Transactions");
        System.out.println("    -> Enter 5 to View/Add money to Wallet");
        System.out.println("    -> Enter 6 to Change Password");
        System.out.println("    -> Enter 7 to Logout");
        System.out.print("Enter Choice : ");
        int n = Integer.parseInt(Main.sc.nextLine());
        switch (n) {
            case 1:
                addExpense(user_index);
                break;
            case 2:
                addPartner(user_index);
                break;
            case 3:
                // viewOrRepay();
                break;
            case 4:
                viewTransactions(user_index);
                break;
            case 5:
                addMoney(user_index);
                break;
            case 6:
                changePassword(user_index);
                break;
            case 7:
                login();
                break;
            default:
                System.out.println("Invalid choice !\nTry again");
                System.out.println("Press any key to continue......");
                Main.sc.nextLine();
                userPage(user_index);
        }
    }

    private static void addExpense(int user_index) {
        System.out.print("\033[H\033[2J");
        System.out.print("Enter Name of the Expense or 0 to exit: ");
        String name = Main.sc.nextLine();
        if (name.equals("0")) {
            userPage(user_index);
        }
        System.out.print("Enter details of Expense : ");
        String details = Main.sc.nextLine();
        System.out.print("Enter Amount : ");
        Integer amount = Integer.parseInt(Main.sc.nextLine());
        System.out.println("Select partners for this Expense");
        selectPartners(user_index, amount, name, details);
    }

    private static void selectPartners(int user_index, int amount, String name, String details) {
        System.out.println("Select 1 if Only you");
        System.out.println("Select 2 if all (including you)");
        System.out.println("Select 3 if all (Excluding you)");
        System.out.println("Select 4 for custom Selection");
        System.out.println("Select 5 to cancel action");

        int n = Integer.parseInt(Main.sc.nextLine());
        if (n == 1) {
            selfDue(user_index, amount, name);
        } else if (n == 2) {
            selfDue(user_index, amount, name);
            dueforAll(user_index, amount, name, details);
        } else if (n == 3) {
            dueforAll(user_index, amount, name, details);
        } else if (n == 4) {
            customDueallocate(user_index, amount, name, details);
        } else if (n == 5) {
            System.out.println("Action has been Dropped");
            System.out.println("Press any key to continue......");
            Main.sc.nextLine();
            userPage(user_index);
        } else {
            System.out.println("Invalid option \nTry Again");
            System.out.println("Press any key to continue......");
            Main.sc.nextLine();
            selectPartners(user_index, amount, name, details);
        }
    }

    private static void customDueallocate(int user_index, int amount, String name2, String details) {
        System.out.println();
        for (int i = 0; i < Main.users.size(); i++) {
            if (Main.users.get(i).equals(Main.users.get(user_index))) {
                System.out.println((i + 1) + " " + Main.users.get(i).Name + " (You)");
                continue;
            }
            System.out.println((i + 1) + " " + Main.users.get(i).Name);
        }
        System.out.println("Enter number of the users seperated by Space or 0 to exit");
        String s = Main.sc.nextLine();
        if (s.equals("0")) {
            userPage(user_index);
        }
        if (s.equals("")) {
            System.out.println("Enter valid numbers or 0 to exit");
            customDueallocate(user_index, amount, name2, details);
        } else {
            boolean flag = false;
            String[] cms = s.split(" ");
            amount=amount/cms.length;
            for (int i = 0; i < cms.length; i++) {
                int n = Integer.parseInt(cms[i]);
                if (Main.users.get(n - 1).equals(Main.users.get(user_index))) {
                    selfDueTwo(user_index, amount, name2);
                    continue;
                }
                flag = true;
                Main.users.get(n - 1).dues
                        .add(new Due(name2, details, amount, LocalDateTime.now(), Main.users.get(user_index)));
            }
            if(flag)
                System.out.println("Due has been added SuccessFully");
                System.out.println("Press any key to continue......");
                Main.sc.nextLine();
                userPage(user_index);
        }

    }

    private static void dueforAll(int user_index, int amount, String name2, String details) {
        amount = amount / Main.users.size();
        for (int i = 0; i < Main.users.size(); i++) {
            if (Main.users.get(i).equals(Main.users.get(user_index))) {
                continue;
            }
            Main.users.get(i).dues
                    .add(new Due(name2, details, amount, LocalDateTime.now(), Main.users.get(user_index)));
        }
        System.out.println("Successfully Due has been added for " + name2);
        System.out.println("Press any key to continue......");
        Main.sc.nextLine();
        userPage(user_index);
    }

    static void selfDue(int user_index, int amount, String name) {
        Main.users.get(user_index).walletAmount -= amount;
        String det = String.format(" => Amount Rs.%d has been Paid for %s\n", amount, name);
        System.out.print(det);
        Main.users.get(user_index).pays += det;
        System.out.println("Press any key to continue......");
        Main.sc.nextLine();
        userPage(user_index);
    }
    static void selfDueTwo(int user_index, int amount, String name) {
        Main.users.get(user_index).walletAmount -= amount;
        String det = String.format(" => Amount Rs.%d has been Paid for %s\n", amount, name);
        System.out.print(det);
        Main.users.get(user_index).pays += det;
        return;
    }

    private static void changePassword(int user_index) {
        System.out.print("\033[H\033[2J");
        System.out.print("Enter old Password or 0 to exit : ");
        String oldPass = Main.sc.nextLine();
        if (oldPass.equals("0")) {
            userPage(user_index);
        }
        if (Main.users.get(user_index).password.equals(oldPass)) {
            System.out.print("Enter new Password : ");
            Main.users.get(user_index).password = Main.sc.nextLine();
            System.out.println("Your Password has been Upated\nYou have been logged out");
            System.out.println("Press any key to continue......");
            Main.sc.nextLine();
            login();
        } else {
            System.out.println("Enter your Old Password Correctly");
            System.out.println("Press any key to continue......");
            Main.sc.nextLine();
            changePassword(user_index);
        }
    }

    private static void addPartner(int user_index) {
        System.out.print("\033[H\033[2J");
        System.out.print("Enter new Partner Name or 0 to exit : ");
        String name = Main.sc.nextLine();
        System.out.print("Enter username of Partner : ");
        String userName = Main.sc.nextLine();
        System.out.print("Enter Mobile number of  : ");
        String mobile = Main.sc.nextLine();
        Main.users.add(new User(name, userName, "1234", mobile));
        System.out.printf(" =>> %s has been added as a Partner\n", name);
        System.out.println("Password is 1234");
        System.out.println();
        System.out.print("Enter 1 to add one more Partner or 0 to Exit : ");
        int n = Integer.parseInt(Main.sc.nextLine());
        if (n == 1) {
            addPartner(user_index);
        }
        userPage(user_index);
    }

    private static void addMoney(int user_index) {
        System.out.print("\033[H\033[2J");
        System.out.println("Current Amount in Wallet => " + Main.users.get(user_index).walletAmount);
        System.out.print("Enter amount you Want to add in your Wallet or 0 to exit : ");
        Integer amt = Integer.parseInt(Main.sc.nextLine());
        if (amt == 0) {
            userPage(user_index);
        }
        Main.users.get(user_index).walletAmount += amt;
        String det = String.format(" => Amount Rs.%d has been added to your wallet\n", amt);
        System.out.print(det);
        Main.users.get(user_index).pays += det;
        System.out.println("Your Updated Balance => " + Main.users.get(user_index).walletAmount);
        System.out.println("Press any key to continue......");
        Main.sc.nextLine();
        userPage(user_index);
    }

    private static void viewTransactions(int user_index) {
        if (Main.users.get(user_index).pays.equals("")) {
            System.out.println("You haven't Done any transactions Yet !");
            System.out.println("Press any key to continue......");
            Main.sc.nextLine();
            userPage(user_index);
        } else {
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.print(Main.users.get(user_index).pays);
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println();
            System.out.println("Press any key to continue......");
            Main.sc.nextLine();
            userPage(user_index);
        }
    }
}
