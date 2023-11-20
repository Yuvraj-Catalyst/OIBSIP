package AtmInterface;
import javax.swing.plaf.nimbus.State;
import java.io.FileWriter;
import java.sql.*;
public class Database {
    public Connection getConnection(){
        try{
//            Class.forName("com.mysql:cj:jdbc:Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atmaccount","root","root");
            return con;
        }
        catch(Exception e){
            System.out.println("Error is: "+e);
        }
        return null;
    }
    public boolean verifyCredentials(String userId,String userPass){
        try{
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "Select password from userInformation where username = '"+userId+"'";
            ResultSet set = stmt.executeQuery(query);
            while(set.next()) {
                if (set.wasNull()) {
                    System.out.println("Invalid UserId");
                    return false;
                }
                if (userPass.equals(set.getString("password"))) {
                    return true;
                }
            }
        }
        catch(Exception e){
            System.out.println("Error is: "+e);
        }
        return false;
    }
    class passAmountStatus{
        int amount=0;
        boolean status=false;
        passAmountStatus(int amount,boolean withdrawStatus){
            this.amount = amount;
            this.status = withdrawStatus;
        }
    }
    public int deposit(int amount,String userId){
        try{
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            int amt = getAmount(userId);
            String query = "update userInformation set amount="+(amt+amount)+" where username = '"+userId+
                    "'";
            stmt.executeUpdate(query);
            query = "insert into transactionHistory (destinationUserId,amount) values('"+userId+"',"+amount+")";
            stmt.executeUpdate(query);
            con.close();
            return amt+amount;
        }
        catch(Exception e){
            System.out.println("Error is: "+e);
        }
        return Integer.MIN_VALUE;
    }
    public boolean verifyUserId(String username){
        try{
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "select username from userInformation";
            ResultSet set = stmt.executeQuery(query);
            while(set.next()){
                if(set.getString("username").equals(username)){
                    return true;
                }
            }
            con.close();
        }
        catch(Exception e){
            System.out.println("Error is "+e);
        }
        return false;
    }
    public passAmountStatus transfer(String userId,String targetUserId,int amount){
        try{
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            if(verifyUserId(targetUserId)){
                int sourceAmt = getAmount(userId);
                int targetAmt = getAmount(targetUserId);

                if(sourceAmt<amount){
                    System.out.println("Your transfer amount is too more than your account balance");
                    return new passAmountStatus(amount,false);
                }
                sourceAmt = sourceAmt - amount;
                targetAmt = targetAmt + amount;
                String query = "insert into transactionHistory (sourceUserId,destinationUserId,amount) " +
                        "values('"+userId+"','"+targetUserId+"',"+amount+")";
                stmt.executeUpdate(query);
                query = "update userInformation set amount="+(sourceAmt)+" where username = '"+userId+"'";
                stmt.executeUpdate(query);
                 query = "update userInformation set amount="+(targetAmt)+" where username = '"+targetUserId+"'";
                 stmt.executeUpdate(query);
                return new passAmountStatus(sourceAmt,true);
            }
            else{
                System.out.println(targetUserId+" has no account");
            }
        }
        catch (Exception e){
            System.out.println("Error is "+e);
        }
        return new passAmountStatus(amount,false);
    }
    public int getAmount(String userId){
        try{
            int amt = 0;
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "Select amount from userInformation where username = '"+userId+"'";
            ResultSet set = stmt.executeQuery(query);
            while(set.next()){
                amt = set.getInt("amount");
            }
            return amt;
        }
        catch(Exception e){
            System.out.println("Error is "+e);
        }
        return Integer.MIN_VALUE;
    }
    public passAmountStatus passAmount(int amount,String userId){
        try{
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            int amt = getAmount(userId);
            if(amt<amount){
                return new passAmountStatus(amt,false);
            }
            else{
                amt = amt-amount;
                String query = "insert into transactionhistory (sourceUserId,amount) values('"+userId+"',"+amount+")";
                stmt.executeUpdate(query);
                query= "update userInformation set amount = "+amt+" where username = '"+userId+"'";

                int i = stmt.executeUpdate(query);
                con.close();
                if(i>0){
                    return new passAmountStatus(amt,true);
                }
                else{
                    return new passAmountStatus(Integer.MIN_VALUE,false);
                }
            }
        }
        catch (Exception e){
            System.out.println("Error is: "+e);
        }
        return new passAmountStatus(Integer.MIN_VALUE,false);
    }
    public void transactionHistory(String userId,String mode){
        try{
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "select * from transactionHistory where sourceUserId = '"+userId+"'";
            ResultSet set = stmt.executeQuery(query);
                FileWriter fileWriter = new FileWriter("C:\\Users\\TARKESHWAR PRASAD\\Desktop\\program\\Tasks" +
                        "\\Oasis Infobyte\\Task 3\\src\\AtmInterface\\transactionFile");
                if(mode.equalsIgnoreCase("w") || mode.equalsIgnoreCase("a")) {
                    fileWriter.write("--------Withdraw Report---------\n");
                    fileWriter.write("Target\t\tAmount\t\tTime\n");
                    while (set.next()) {
                        String result = set.getString("destinationUserId") + "\t\t"
                                + set.getInt("amount") + "\t\t" + set.getDate("time") + "\n";
                        fileWriter.write(result);
                    }
                }
                if(mode.equalsIgnoreCase("a")|| mode.equalsIgnoreCase("d")) {
                    query = "select * from transactionHistory where destinationUserId = '" + userId + "'";
                    set = stmt.executeQuery(query);
                    fileWriter.write("\n--------Deposit Report---------\n");
                    fileWriter.write("Source\t\tAmount\t\tTime\n");
                    while (set.next()) {
                        String result = set.getString("sourceUserId") + "\t\t"
                                + set.getInt("amount") + "\t\t" + set.getDate("time") + "\n";
                        fileWriter.write(result);
                    }
                }
                fileWriter.close();
                con.close();
        }

        catch(Exception e){
            System.out.println("Error is "+e);
        }
    }
}
