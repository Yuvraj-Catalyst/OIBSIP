package DigitalLibraryManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Calendar;

public class Database {
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/libraryManagement", "root",
                    "root");
            return con;
        } catch (Exception e) {
            System.out.println("Error is " + e);
        }
        return null;
    }

    public boolean validateLogin(String username, char[] password, String userType) {
        try {
            String pass = "";
            boolean check = true;
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "select password from loginInformation where username = '" + username + "' and loginType = '" +
                    userType + "'";
            ResultSet set = stmt.executeQuery(query);
            while (set.next()) {
                pass = set.getString("password");
            }
            if (pass.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Username doesn't exist",
                        "Warning", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            for (int i = 0; i < pass.length(); i++) {
                if (pass.charAt(i) != password[i]) {
                    JOptionPane.showMessageDialog(null, "Incorrect Password",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                    check = false;
                    break;
                }
            }
            return check;
        } catch (Exception e) {
            System.out.println("Error is " + e);
        }
        return false;
    }

    public boolean addBook(String obj[]) {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            int price = (Integer.parseInt(obj[3]));
            int year = (Integer.parseInt(obj[4]));
            int bid = Integer.parseInt(obj[0]);
            String query = "select bookId from bookTable where bookId = " + bid;
            ResultSet set = stmt.executeQuery(query);
            while (set.next()) {
                if (set.getInt("bookId") == bid) {
                    return false;
                }
            }
            query = "insert into bookTable (bookId,name,publisher,price,publisherYear) values('" + bid + "','" +
                    obj[1] + "','" + obj[2] + "','" + price + "','" + year + "')";
            int i = stmt.executeUpdate(query);
            if (i > 0) {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error is " + e, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean addStudent(String obj[]) {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            int sid = Integer.parseInt(obj[0]);
            String query = "select studentId from studentTable where studentId = " + sid;
            ResultSet set = stmt.executeQuery(query);
            while (set.next()) {
                if (set.getInt("studentId") == sid) {
                    return false;
                }
            }
            query = "insert into studentTable (studentId,name,fatherName,courseName,branchName) values('" + obj[0] + "','" +
                    obj[1] + "','" + obj[2] + "','" + obj[3] + "','" + obj[4] + "')";
            int i = stmt.executeUpdate(query);
            if (i > 0) {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error is " + e, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean updateBook(String obj[]) {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "update bookTable set name = '" + obj[1] + "',publisher = '" + obj[2] + "',price = " + obj[3] +
                    ",publisherYear = " + obj[4] + " where bookId = " + obj[0];
            int i = stmt.executeUpdate(query);
            if (i > 0) {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error is " + e, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean updateStudent(String obj[]) {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "update studentTable set name = '" + obj[1] + "',fatherName = '" + obj[2] + "',courseName = " + obj[3] +
                    ",branchName = " + obj[4] + " where studentId = " + obj[0];
            int i = stmt.executeUpdate(query);
            if (i > 0) {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error is " + e, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean removeBook(int bid) {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "Delete from bookTable where bookId = " + bid;
            int i = stmt.executeUpdate(query);
            if (i > 0) {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error is " + e, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean removeStudent(int sid, String username) {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "Delete from loginInformation where username = '" + username + "' and " +
                    "loginType = 'Local User'";
            stmt.executeUpdate(query);
            query = "Delete from studentTable where studentId = " + sid;
            int i = stmt.executeUpdate(query);
            if (i > 0) {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error is " + e, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean validateBookId(int bid, String obj[]) {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "select * from bookTable where bookId = " + bid;
            ResultSet set = stmt.executeQuery(query);
            while (set.next()) {
                if (set.getInt("bookId") == bid) {
                    obj[0] = String.valueOf(bid);
                    obj[1] = set.getString("name");
                    obj[2] = set.getString("publisher");
                    obj[3] = String.valueOf(set.getInt("price"));
                    obj[4] = String.valueOf(set.getInt("publisherYear"));
                    return true;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error is " + e, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    public boolean validateStudentId(int sid, String obj[]) {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "select * from studentTable where studentId = " + sid;
            ResultSet set = stmt.executeQuery(query);
            while (set.next()) {
                if (set.getInt("studentId") == sid) {
                    obj[0] = String.valueOf(sid);
                    obj[1] = set.getString("name");
                    obj[2] = set.getString("fatherName");
                    obj[3] = set.getString("CourseName");
                    obj[4] = set.getString("branchName");
                    return true;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error is " + e, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    public void setStudentCredentials(String username, String password) {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "insert into loginInformation(username,password,loginType) values " +
                    "('" + username + "','" + password + "','Local User')";
            int i = stmt.executeUpdate(query);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error is " + e,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void createStudentTable(DefaultTableModel model) {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "select * from studentTable";
            Object obj[] = new Object[5];
            ResultSet set = stmt.executeQuery(query);
            while (set.next()) {
                obj[0] = set.getInt("studentId");
                obj[1] = set.getString("name");
                obj[2] = set.getString("FatherName");
                obj[3] = set.getString("CourseName");
                obj[4] = set.getString("BranchName");
                model.addRow(obj);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error is " + e, "Erorr",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void createBookTable(DefaultTableModel model) {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "select * from bookTable";
            Object obj[] = new Object[5];
            ResultSet set = stmt.executeQuery(query);
            while (set.next()) {
                obj[0] = set.getInt("bookId");
                obj[1] = set.getString("name");
                obj[2] = set.getString("publisher");
                obj[3] = set.getInt("price");
                obj[4] = set.getInt("publisherYear");
                model.addRow(obj);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error is " + e, "Erorr",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void createStatisticsTable(DefaultTableModel model, int mode) {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "select * from bookTable where mode = " + mode;
            Object obj[] = new Object[5];
            ResultSet set = stmt.executeQuery(query);
            while (set.next()) {
                obj[0] = set.getInt("bookId");
                obj[1] = set.getString("name");
                obj[2] = set.getString("publisher");
                obj[3] = set.getInt("price");
                obj[4] = set.getInt("publisherYear");
                model.addRow(obj);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error is " + e, "Erorr",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void createLibraryBookHistory(DefaultTableModel model) {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "Select * from bookHistory";
            Object[] obj = new Object[7];
            ResultSet set = stmt.executeQuery(query);
            while (set.next()) {
                obj[0] = set.getInt("bookId");
                obj[1] = set.getInt("studentId");
                obj[2] = set.getString("issueDate");
                obj[3] = set.getString("returnDate");
                if(obj[3].equals("1900-01-01")){{
                    obj[3] = "Not Return";
                }}
                obj[4] = set.getString("dueDate");
                obj[5] = set.getInt("lateFine");
                obj[6] = getTime()-getDay(obj[2].toString());
                model.addRow(obj);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error is " + e, "Erorr",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void allEmail(DefaultTableModel model) {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "Select * from emails";
            Object obj[] = new Object[3];
            ResultSet set = stmt.executeQuery(query);
            while (set.next()) {
                obj[0] = set.getString("email");
                obj[1] = set.getInt("studentId");
                obj[2] = set.getString("time");
                model.addRow(obj);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error is " + e, "Erorr",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getUsername(int id) {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String str = "";
            ResultSet set = stmt.executeQuery("Select name from studentTable where studentId = " + id);
            while (set.next()) {
                str = set.getString("name");
            }
            return str;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error is " + e, "Erorr",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public boolean emailPost(String email, int id) {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "insert into emails (email,studentId) values ('" + email + "'," + id + ")";
            int i = stmt.executeUpdate(query);
            if (i > 0) {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error is " + e, "Erorr",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    class getDueAndLate{
        String dueDate = "";
        int lFine = 0;
        getDueAndLate(String dueDate,int lFine){
            this.dueDate = dueDate;
            this.lFine = lFine;
        }
    }
    public getDueAndLate validateBookAndStudentId(String bookId, String studentId) {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            int bid = Integer.parseInt(bookId);
            int sid = Integer.parseInt(studentId);
            String dueDate = "";
            int lFine = 0;
            String query = "select duedate,milliTime from bookHistory where bookId = '" + bid + "' and studentId = '" + sid + "' and" +
                    " returnDate = '1900-01-01'";
            ResultSet set = stmt.executeQuery(query);
            while (set.next()) {
                dueDate = set.getString("duedate");
                lFine = set.getInt("milliTime");
            }
            lFine = 7*(getTime()-lFine);
            return new getDueAndLate(dueDate,lFine);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error is " + e, "Erorr",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    public boolean bookIssue(String obj[]) {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            int bid = Integer.parseInt(obj[0]);
            String query = "select * from bookTable where bookId = " + bid;
            ResultSet set = stmt.executeQuery(query);
            while (set.next()) {
                if (set.getInt("Mode") == 1) {
                    JOptionPane.showMessageDialog(null, "Book is already alloted to Other Student",
                            "Warning",JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
            query = "select * from bookTable";
            set = stmt.executeQuery(query);
            boolean check = false;
            while(set.next()){
                if(set.getInt("bookId")==bid){
                    check = true;
                }
            }
            if(!(check)){
                JOptionPane.showMessageDialog(null, "Book Id not exist",
                        "Warning",JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if(!(validateStudentId(Integer.parseInt(obj[1]),new String[5]))){
                JOptionPane.showMessageDialog(null, "student Id not exist",
                        "Warning",JOptionPane.WARNING_MESSAGE);
                return false;
            }
            query = "select returnDate from bookHistory where studentId = '"+obj[1]+"' and dueDate = '"+obj[3]+"'";
            set = stmt.executeQuery(query);
            while(set.next()){
                if(!(set.getString("returnDate")).equals("1900-01-01")){
                    JOptionPane.showMessageDialog(null, "First You have to return last Book\n" +
                                    "Then You can get Next Book",
                            "Warning",JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }

            query = "update bookTable set Mode = 1 where bookId = "+bid;

            stmt.executeUpdate(query);
            int dayTime = getTime();
            query = "insert into bookHistory (bookId,studentId,issueDate,dueDate,milliTime) " +
                    " values('" + obj[0] + "','" + obj[1] + "','" + obj[2] + "','" + obj[3] + "','"+dayTime+"')";

            int i = stmt.executeUpdate(query);
            if(i>0){
                return true;
            }
            else{
                JOptionPane.showMessageDialog(null, "Book is not issue",
                        "Warning",JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error is " + e, "Erorr",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    public boolean bookReturn(String obj[]){
        try{
           Connection con = getConnection();
           Statement stmt = con.createStatement();
           String query = "update bookHistory set returnDate = '"+obj[2]+"'," +
                   "lateFine = '"+obj[4]+"',milliTime = '"+(7+Integer.parseInt(obj[4])/7)+"'" +
                   " where bookId = '"+obj[0]+"' and " +
                   "studentId = '"+obj[1]+"' and dueDate = '"+obj[3]+"'";
           int i = stmt.executeUpdate(query);
           if(i<=0){
               return false;
           }
            query = "update bookTable set mode = 0 where bookId = '"+obj[0]+"'";
           i = stmt.executeUpdate(query);
           if(i>0){
               return true;
           }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error is " + e, "Erorr",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    public int getTime(){
        Calendar c = Calendar.getInstance();
        long time = c.getTimeInMillis()/1000/60/60/24;
        return (int)(time);
    }
    public boolean leapYear(int year){
        if((year%4==0 && year%100!=0)||year%400==0){
            return true;
        }
        return false;
    }
    public int getDay(String issueDate){
        int fIdx = issueDate.indexOf('-');
        int sIdx = issueDate.lastIndexOf('-');
        int year = Integer.parseInt(issueDate.substring(0,fIdx));
        int month = Integer.parseInt(issueDate.substring(fIdx+1,sIdx));
        int day = Integer.parseInt(issueDate.substring(sIdx+1));
        int countDays = 0;
        for(int i = 1970;i<year;i++){
            if(leapYear(i)){
                countDays = countDays+366;
            }
            else{
                countDays += 365;
            }
        }
        for(int i= 1;i<=month;i++){
            if(i == 1 || i == 3|| i ==5 || i==7||i==8 || i==10|| i==12){
                countDays += 31;
            }
            else if(i == 2){
                if(leapYear(year)){
                    countDays += 29;
                }
                else{
                    countDays += 28;
                }
            }
            else{
                countDays += 30;
            }
        }
        countDays += day-1;
        return countDays;
    }
}
