package AtmInterface;

import java.io.BufferedOutputStream;
import java.io.File;
import java.nio.Buffer;
import java.util.*;


public class BasicOperation {
    String userId = "";
    String userPass = "";
    BasicOperation(String userId,String userPass){
        this.userId = userId;
        this.userPass = userPass;
    }
    Scanner sc = new Scanner(System.in);
    Database db = new Database();
    int amount;
    String i = "0";
    public void operation(){
        while(true){
            System.out.println();
            System.out.println("1:-Transaction history");
            System.out.println("2:-Withdraw");
            System.out.println("3:-Deposit");
            System.out.println("4:-Transfer");
            System.out.println("5:-Logout");
            System.out.print("Choose The Option: ");
            byte c = sc.nextByte();
            switch(c){
                case 1:
                    transaction();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:{
                    logout();
                    break;
                }
                default:
                    System.out.println("Choose Other Option");
            }
        }
    }
    public void transaction(){
        System.out.println("Your transaction History\n");
        sc.nextLine();
        String i = "";
        while(true){
            System.out.println("Enter the mode of transaction\nW for Withdraw\n" +
                    "D for Deposit\nA for All");
            i = sc.nextLine();
            if(i.equalsIgnoreCase("W")){
                break;
            }
            else if(i.equalsIgnoreCase("D")){
                break;
            }
            else if(i.equalsIgnoreCase("A")){
                break;
            }
        }
        db.transactionHistory(userId,i);
        try {
            File myFile = new File("C:\\Users\\TARKESHWAR PRASAD\\Desktop\\program\\Tasks\\Oasis Infobyte\\" +
                    "Task 3\\src\\AtmInterface\\transactionFile");
            Scanner input = new Scanner(myFile);
            while (input.hasNextLine()) {
                String line = input.nextLine();
                System.out.println(line);
            }
        }
        catch (Exception e){
            System.out.println("Error is "+e);
        }
        exit();
    }
    public void withdraw(){
        System.out.println("\n----------Withdraw------------");
        System.out.print("Enter the amount:  ");
        amount = sc.nextInt();
        Database.passAmountStatus result = db.passAmount(amount,userId);
        if(result.status){
            System.out.println("Withdraw Successfull");
            System.out.println("Your New Balance is = "+result.amount);
        }
        else{
            System.out.println("UnSuccessful Withdraw");
            System.out.println("Your Balance is = "+result.amount);
        }
        sc.nextLine();
       exit();
    }
    public void deposit(){
        System.out.println("\n--------Deposit---------");
        System.out.print("Enter the amount:  ");
        amount = sc.nextInt();
        int newAmt = db.deposit(amount,userId);
        System.out.println("Deposit Successfull");
        System.out.println("New Balance "+newAmt);
        sc.nextLine();
        exit();
    }
    public void transfer(){
        System.out.print("Enter the userId in which you want to transfer the money: ");
        sc.nextLine();
        String targetUserId = sc.nextLine();
        System.out.print("Enter the amount:  ");
        amount = sc.nextInt();
        Database.passAmountStatus result = db.transfer(userId,targetUserId,amount);
        if(result.status){
            System.out.println("Transfer in "+targetUserId+"'s account successfull");
            System.out.println("Your New Balance is = "+result.amount);
        }
        else{
            System.out.println("Current Balance = "+db.getAmount(userId));
        }
        sc.nextLine();
        exit();
    }
    public void logout(){
        sc.nextLine();
        System.out.println("Logout ....");
        try{
            Thread.sleep(2000);
        }
        catch(Exception e){
            System.out.println("Error is: "+e);
        }
        Atm a = new Atm();
        a.atm();
    }

     public void exit(){
            System.out.print("\nEnter q or Q for closing the application other wise press any key:\t ");
            String i = sc.nextLine();
            if(i.equalsIgnoreCase("q")){
                try{
                    Thread.sleep(2000);
                }
                catch (Exception e){
                    System.out.println("Error is "+e);
                }
                System.exit(0);
            }
     }
}
