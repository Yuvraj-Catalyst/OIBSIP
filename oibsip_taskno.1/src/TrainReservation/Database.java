 package TrainReservation;
import java.sql.*;
import javax.swing.JOptionPane;
public class Database {
    public Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/TrainReservation","root","root");
            return con;
        }
        catch(Exception e){
            System.out.println("Connection doesn't established");;
        }
        return null;
    }
    public boolean validateLogin(String username,char [] password){
        try{
            boolean check = false;
            String checkPassword = "";
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "select * from loginDetails";
            ResultSet set = stmt.executeQuery(query);
            while(set.next()){
                if(username.equalsIgnoreCase(set.getString(1)) /*&& password.equals(set.getString(3))*/){
                    checkPassword = set.getString(2);
                    check = true;
                    break;
                }    
            }
            if(check){ 
                if(password.length==checkPassword.length()){
                    for(int i=0;i<password.length;i++){
                        if(password[i] != checkPassword.charAt(i)){
                            check = false;
                            break;
                        }
                    }
                } 
                else{
                    check = false;
                }   
                if(check){
                    con.close();
                    return true;
                }
                else{
                    JOptionPane.showMessageDialog(null,"Incorrect Password","Warning",JOptionPane.WARNING_MESSAGE);   
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"Incorrect Username","Warning",JOptionPane.WARNING_MESSAGE);
            }
        con.close();
        }
        catch(Exception es){
            es.printStackTrace();
        }
        return false;
    }
    public int getPnr(Object obj[]){
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "Select pnr from passengerInformation where name = '" + obj[0] +
                    "'and FatherName= '" + obj[1] + "'and Email = '" + obj[2] + "' " +
                    "and ContactNumber = '" + obj[3] + "' and date='" + obj[11] + "'";
            ResultSet set = stmt.executeQuery(query);
            String pnr = null;
            while(set.next()){
                pnr = set.getString("pnr");
            }
            if(pnr == null){
                return -1;
            }
            return Integer.parseInt(pnr);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error is"+e,"Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
        return -1;
    }
    public boolean insert(Object obj[]){
        try{
            Connection con = getConnection();
            Statement stmt = con.createStatement();
          if(getPnr(obj)!= -1){
              return false;
          }
           String query = "insert into passengerInformation(Name,FatherName,Email,ContactNumber," +
                    "TrainName,TrainNumber,Age,Gender,class,sourceAddress,destinationAddress,date)" +
                    " values ('"+obj[0]+"','"+obj[1]+"','"+obj[2]+"','"+obj[3]+"','"+obj[4]+"','"+
                    obj[5]+"','"+obj[6]+"','"+obj[7]+"','"+obj[8]+"','"+obj[9]+"','"+obj[10]+"','"+
                    obj[11]+"')";
            int i = stmt.executeUpdate(query);

            con.close();
            if(i>0){
                return true;
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error is: "+e,"Warning",JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
    public void delete(int pnr){
        try{
            Connection con = getConnection();
            String query = "delete from passengerInformation where pnr = "+pnr;
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error is: "+e,"Warning",JOptionPane.WARNING_MESSAGE);

        }
    }
    public void select(Object obj[],int pnr){
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
    }
}
