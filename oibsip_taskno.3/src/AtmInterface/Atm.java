package AtmInterface;

import java.io.IOException;
import java.util.*;
public class Atm {
    String userId,userPass;
    Scanner sc = new Scanner(System.in);
    public void atm() {
        System.out.println("-----------Welcome To Atm--------\n");
        checkCredentials();
    }
    public void checkCredentials(){
        System.out.print("Press q or Q for quit: ");
        String quit = sc.nextLine();
        if(quit.equalsIgnoreCase("q")){
            System.out.println("Closing the Program");
            return;
        }
        System.out.println();
        System.out.print("Enter User Id:  ");
        userId = sc.next();
        System.out.print("Enter User Password:  ");
        userPass = sc.next();
        Database db = new Database();

        if(db.verifyCredentials(userId,userPass)){
            System.out.println("Credentials are matched");
            BasicOperation bo = new BasicOperation(userId,userPass);
            bo.operation();
        }
        else{
            System.out.println("Incorrect Credentials");
            System.out.println();
//            checkCredentials();
        }
    }
}
