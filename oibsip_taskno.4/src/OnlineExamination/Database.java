package OnlineExamination;
import java.sql.*;
import javax.swing.JOptionPane;
public class Database {
    boolean usernameMatched = false;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/onlineexamination","root","root");

            return con;
        } catch (Exception e) {
            System.out.println("Connection doesn't established");
        }
        return null;
    }
    public boolean changePassword(String user,char oldpass[],char newpass[]){
        try{
            String oldP = "";
            StringBuilder newP = new StringBuilder("");
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "select password from userDetails where username = '"+user+"'";
            ResultSet set = stmt.executeQuery(query);
            while(set.next()){
                oldP = set.getString(1);
            }
            if(oldP.length() == oldpass.length){
                for(int i = 0;i<oldpass.length;i++){
                    if(oldP.charAt(i)!=oldpass[i]){
                        JOptionPane.showMessageDialog(null,"Old Password is Incorrect",
                                "Information",JOptionPane.INFORMATION_MESSAGE);
                        return false;
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"Old Password is Incorrect",
                        "Information",JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            for(int i = 0;i<newpass.length;i++){
                newP.append(newpass[i]);
            }
            query = "update userDetails set password = '"+newP+"'"+"where username = '"+user+"'";
            int result = stmt.executeUpdate(query);
            if(result>0){
                JOptionPane.showMessageDialog(null,"Password is Changed Successfully",
                        "Information",JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
            else{
                JOptionPane.showMessageDialog(null,"Password is not Changed Successfully",
                        "Information",JOptionPane.INFORMATION_MESSAGE);
                return false;
            }

        }
        catch(Exception e){
            System.out.println("Error is "+e);
        }
        return false;
    }

    public boolean validateSignUp(String username, char[] password) {
        try {
            StringBuilder pass = new StringBuilder("");
            if(existUsername(username)){
                JOptionPane.showMessageDialog(null,"Choose Other username","Warning",JOptionPane.WARNING_MESSAGE);
                return false;
            }
            for (int i = 0; i < password.length; i++) {
                pass.append(password[i]);
            }
            System.out.println(pass);
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "insert into userDetails (username,password) values('" + username + "','" + pass.toString() + "')";
            int i = stmt.executeUpdate(query);
            if (i > 0) {
                return true;
            }
            con.close();
        } catch (Exception es) {
            es.printStackTrace();
        }
        return false;
    }

    private boolean existUsername(String username) {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "select * from userDetails";
            ResultSet set = stmt.executeQuery(query);
            while (set.next()) {
                if (username.equalsIgnoreCase(set.getString(1)) /*&& password.equals(set.getString(3))*/) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean validateLogin(String username, char[] password) {
        try {
            boolean check = false;
            String checkPassword = "";
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "select * from userDetails";
            ResultSet set = stmt.executeQuery(query);
            while (set.next()) {
                if (username.equalsIgnoreCase(set.getString(1)) /*&& password.equals(set.getString(3))*/) {
                    checkPassword = set.getString(2);
                    check = true;
                    break;
                }
            }
            if (check) {
                if (password.length == checkPassword.length()) {
                    for (int i = 0; i < password.length; i++) {
                        if (password[i] != checkPassword.charAt(i)) {
                            check = false;
                            break;
                        }
                    }
                } else {
                    check = false;
                }
                if (check) {
                    con.close();
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Password", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect Username", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            con.close();
        } catch (Exception es) {
            System.out.println(es);
        }
        return false;
    }
    public boolean profileUpdate(String obj[],String user){
        try{
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "Select username,email,number from userDetails ";
            ResultSet set = stmt.executeQuery(query);
            while(set.next()){
                if(user.equals(set.getString("username"))){
                    continue;
                }
                if(obj[0].equalsIgnoreCase(set.getString("email")) && set.getString("email") != null){
                    JOptionPane.showMessageDialog(null,"Choose Another Email Id",
                            "Warning",JOptionPane.WARNING_MESSAGE);
                    return false;
                }
                if(obj[1].equals(set.getString("number")) &&  set.getString("number") != null){
                    JOptionPane.showMessageDialog(null,"Choose Another Whats App Number",
                            "Warning",JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
            query = "update userDetails set email = '"+obj[0]+"',number = '"+obj[1]+"',college = '"+
                    obj[2]+"',city = '"+obj[3]+"',course = '"+obj[4]+"' where username = '"+user+"'";
            int i = stmt.executeUpdate(query);
            if(i>0){
                return true;
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error is "+e,"Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        JOptionPane.showMessageDialog(null,"Account Not updated",
                "Warning",JOptionPane.WARNING_MESSAGE);
        return false;
    }
    public void getData(String[] obj,String user){
        try{
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "Select email,number,college,city,course from userDetails where username = '"+user+"'";
            ResultSet set = stmt.executeQuery(query);
            while(set.next()){

                obj[0] = set.getString("email");
                obj[1] = set.getString("number");
                obj[2] = set.getString("college");
                obj[3] = set.getString("city");
                obj[4] = set.getString("course");
                if(set.getString("email") == null){
                    obj[0] = "Not Set";
                }if(set.getString("number") == null){
                    obj[1] = "Not Set";
                }if(set.getString("college") == null){
                    obj[2] = "Not Set";
                }if(set.getString("city") == null){
                    obj[3] = "Not Set";
                }if(set.getString("course") == null){
                    obj[4] = "Not Set";
                }
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error is: "+e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
//    public boolean insert(Object obj[]){
//        try{
//            Connection con = getConnection();
//            String query = "insert into passengerInformation(Name,FatherName,Email,ContactNumber,TrainName,TrainNumber,Age,Gender,class,sourceAddress,destinationAddress,date) values ('"+obj[0]+"','"+obj[1]+"','"+obj[2]+"','"+obj[3]+"','"+obj[4]+"','"+obj[5]+"','"+obj[6]+"','"+obj[7]+"','"+obj[8]+"','"+obj[9]+"','"+obj[10]+"','"+obj[11]+"')";
//            /*String query = "insert into passengerInformation values ("+i+",'"+obj[0]+"','"+obj[1]+"','"+obj[2]+"','"+obj[3]+"','"+obj[4]+"','"+obj[5]+"','"+obj[6]+"','"+obj[7]+"','"+obj[8]+"','"+obj[9]+"','"+obj[10]+"','"+obj[11]+"')";*/
//            Statement stmt = con.createStatement();
//            int i = stmt.executeUpdate(query);
//            con.close();
//            if(i>0){
//                return true;
//            }
//        }
//        catch(Exception e){
//            JOptionPane.showMessageDialog(null,"Error is: "+e,"Warning",JOptionPane.WARNING_MESSAGE);
//        }
//        return false;
//    }
   /* public void select(Object obj[],int pnr){
        try{
            Connection con = getConnection();
            String query = "Select * from passengerInformation where pnr = "+pnr;
            Statement stmt = con.createStatement();
            ResultSet set = stmt.executeQuery(query);
            while(set.next()){
                obj[0] = set.getString("name");
                obj[1] = set.getString("fatherName");
                obj[2] = set.getString("email");
                obj[3] = set.getString("contactNumber");
                obj[4] = set.getString("trainName");
                obj[5] = set.getString("trainNumber");
                obj[6] = set.getString("age");
                obj[7] = set.getString("gender");
                obj[8] = set.getString("class");
                obj[9] = set.getString("sourceAddress");
                obj[10] = set.getString("DestinationAddress");
                obj[11] = set.getString("date");
            }
            con.close();
        }
        catch(Exception e){

            JOptionPane.showMessageDialog(null,"Error is: "+e,"Warning",JOptionPane.WARNING_MESSAGE);
        }
    }
    public boolean validatePnrNumber(int pnr){
        try{
            Connection con = getConnection();
            String query = "Select pnr from passengerInformation";
            Statement stmt = con.createStatement();
            ResultSet set = stmt.executeQuery(query);
            while(set.next()){
                if(set.getInt(1)==pnr){
                    con.close();
                    return true;
                }
            }
            con.close();
            return false;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }*/
//}

