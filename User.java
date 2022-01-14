public class User {
    String Name;
    String userName;
    String password;
    String mobile;
    String pays="";
    Integer walletAmount=0;
    Due due;

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
        System.out.printf(">>>>>>>>>> Welcome , %s <<<<<<<<<<\n",Main.users.get(user_index).Name);
        System.out.println("    -> Enter 1 to add expense");
        System.out.println("    -> Enter 2 to add a Partner");
        System.out.println("    -> Enter 3 to view/Repay Expense");
        System.out.println("    -> Enter 4 to view All your Transactions");
        System.out.println("    -> Enter 5 to View/Add money to Wallet");
        System.out.println("    -> Enter 6 to Logout");
        System.out.print("Enter Choice : ");
        int n = Integer.parseInt(Main.sc.nextLine());
        switch(n){
            case 1:
                // addExpense();
                break;
            case 2:
                addPartner(user_index);
                break;
            case 3:
                //viewOrRepay();
                break;
            case 4:
                viewTransactions(user_index);
                break;
            case 5:
                addMoney(user_index);
                break;
            case 6:
                login();
                break;
            default:
                System.out.println("Invalid choice !\nTry again");
                System.out.println("Press any key to continue......");
                Main.sc.nextLine();
                userPage(user_index);
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
        System.out.printf(" =>> %s has been added as a Partner\n",name);
        System.out.println("Password is 1234");
        System.out.println();
        System.out.print("Enter 1 to add one more Partner or 0 to Exit : ");
        int n = Integer.parseInt(Main.sc.nextLine());
        if(n==1){
            addPartner(user_index);
        }
        userPage(user_index);
    }

    private static void addMoney(int user_index) {
        System.out.print("\033[H\033[2J");
        System.out.println("Current Amount in Wallet => "+Main.users.get(user_index).walletAmount);
        System.out.print("Enter amount you Want to add in your Wallet or 0 to exit : ");
        Integer amt = Integer.parseInt(Main.sc.nextLine());
        if(amt==0){
            userPage(user_index);
        }
        Main.users.get(user_index).walletAmount+=amt;
        String det = String.format(" => Amount Rs.%d has been added to your wallet\n",amt);
        System.out.print(det);
        Main.users.get(user_index).pays+=det;
        System.out.println("Your Updated Balance => "+Main.users.get(user_index).walletAmount);
        System.out.println("Press any key to continue......");
        Main.sc.nextLine();
        userPage(user_index);
    }

    private static void viewTransactions(int user_index) {
        if(Main.users.get(user_index).pays.equals("")){
            System.out.println("You haven't Done any transactions Yet !");
            System.out.println("Press any key to continue......");
            Main.sc.nextLine();
            userPage(user_index);
        }
        else{
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
