public class User {
    String Name;
    String userName;
    String password;
    String mobile;
    String Transactions;
    Integer walletAmount;


    public User(String name, String userName, String password, String mobile) {
        Name = name;
        this.userName = userName;
        this.password = password;
        this.mobile = mobile;
    }

    static int findUserInd(String userName){
        for(int i=0;i<Main.users.size();i++){
            if(Main.users.get(i).userName.equals(userName)){
                return i;
            }
        }
        return -1;
    }
    
    static void login(){
        System.out.print("\033[H\033[2J");
        System.out.println("-----SPLIT WISE APPLICATION-----");
        System.out.print("Enter User name : ");
        String userName = Main.sc.nextLine();
        int user_index = findUserInd(userName);
        if(user_index==-1){
            System.out.println("User with Username "+userName+" not Found !");
            System.out.println("Press any key to continue......");
            Main.sc.nextLine();
            login();
        }
        System.out.print("Enter Password : ");
        String password = Main.sc.nextLine();
        if(Main.users.get(user_index).password.equals(password)){
            System.out.println("welcome");
        }
        else{
            System.out.println("Incorrect password !\nTry Again");
            System.out.println("Press any key to continue......");
            Main.sc.nextLine();
            login();
        }
    }

}
