package DigitalLibraryManagement;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static java.lang.Integer.parseInt;

public class Validate {
    public boolean validateBook(Object obj[]){
        String bookId = obj[0].toString();
        String name = obj[1].toString();
        String publisher = obj[2].toString();
        String price = obj[3].toString();
        String publishYear = obj[4].toString();
       if(bookId.trim().isEmpty()){
           JOptionPane.showMessageDialog(null,"Enter the Book Id","Warning",
                   JOptionPane.WARNING_MESSAGE);
           return false;
       }
       if(!(bookId.matches("^[0-9]+$"))){
           JOptionPane.showMessageDialog(null,"Incorrect Format of Book Id","Warning",
                   JOptionPane.WARNING_MESSAGE);
           return false;
       }
       if(Integer.parseInt(bookId)<=0){
           JOptionPane.showMessageDialog(null,"Book Id can never be negative","Warning",
                   JOptionPane.WARNING_MESSAGE);
           return false;
       }
       if(name.trim().isEmpty()){
           JOptionPane.showMessageDialog(null,"Enter the book name","Warning",
                   JOptionPane.WARNING_MESSAGE);
           return false;
       }
       if(!(name.matches("^[a-zA-Z' ']+[a-zA-Z]+$"))){
           JOptionPane.showMessageDialog(null,"Invalid Format","Warning",
                   JOptionPane.WARNING_MESSAGE);
           return false;
       }
       if(publisher.trim().isEmpty()){
           JOptionPane.showMessageDialog(null,"Enter the Publisher Name","Warning",
                   JOptionPane.WARNING_MESSAGE);
           return false;
       }
       if(!(publisher.matches("^[a-zA-Z.' ']+[a-zA-Z]"))){
           JOptionPane.showMessageDialog(null,"Invalid Name","Warning",
                   JOptionPane.WARNING_MESSAGE);
           return false;
       }
       if(price.trim().isEmpty()){
           JOptionPane.showMessageDialog(null,"Enter the price of book","Warning",
                   JOptionPane.WARNING_MESSAGE);
           return false;
       }
       if(!(price.matches("^[0-9]+$"))){
           JOptionPane.showMessageDialog(null,"This is not the format of price","Warning",
                   JOptionPane.WARNING_MESSAGE);
           return false;
       }
       if(Integer.parseInt(price)<=0){
           JOptionPane.showMessageDialog(null,"Price can't negative","Warning",
                   JOptionPane.WARNING_MESSAGE);
           return false;
       }
       if(publishYear.trim().isEmpty()){
           JOptionPane.showMessageDialog(null,"Enter the Publisher Year","Warning",
                   JOptionPane.WARNING_MESSAGE);
           return false;
       }
//       if(Integer.parseInt(publishYear)>2023){
//           JOptionPane.showMessageDialog(null,"Enter The Correct Year","Warning",
//                   JOptionPane.WARNING_MESSAGE);
//           return false;
//       }
       return true;
    }
    public boolean validateStudent(Object obj[]){
        String studentId = obj[0].toString();
        String name = obj[1].toString();
        String fName = obj[2].toString();
        String course = obj[3].toString();
        String branch = obj[4].toString();
        if(studentId.trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"Enter the Student Id","Warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(!(studentId.matches("^[0-9]+$"))){
            JOptionPane.showMessageDialog(null,"Incorrect Format of student Id","Warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(Integer.parseInt(studentId)<=0){
            JOptionPane.showMessageDialog(null,"Student Id can never be negative",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(name.trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"Enter the student name","Warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(!(name.matches("^[a-zA-Z' ']+[a-zA-Z]+$"))){
            JOptionPane.showMessageDialog(null,"Invalid Format","Warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(fName.trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"Enter the Father name","Warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(!(fName.matches("^[a-zA-Z' ']+[a-zA-Z]+$"))){
            JOptionPane.showMessageDialog(null,"Invalid Format of Father Name","Warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    public boolean validateLocalBook(String bid,String sid){
        if(bid.trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"Book Id is Empty","Warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(!(bid.matches("^[0-9]+$"))){
            JOptionPane.showMessageDialog(null,"Incorrect Format of Book Id","Warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(Integer.parseInt(bid)<=0){
            JOptionPane.showMessageDialog(null,"Book Id can never be negative","Warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(sid.trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"Student Id is Empty","Warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(!(sid.matches("^[0-9]+$"))){
            JOptionPane.showMessageDialog(null,"Incorrect Format of student Id","Warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(Integer.parseInt(sid)<=0){
            JOptionPane.showMessageDialog(null,"Student Id can never be negative",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
}
