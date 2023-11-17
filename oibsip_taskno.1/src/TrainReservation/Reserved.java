 package TrainReservation;
import javax.swing.JOptionPane;
public class Reserved{
    FillFormDesign ffd;
    Database db = new Database();
    Object obj[] = new String[12];
    String name="",fName="",email="",number="",gender="",age="",trainName="",trainNumber="",classType="",monthAndYear="",date="",sourceAddress="",destinationAddress="";
    Reserved(FillFormDesign ffd){
        this.ffd = ffd;
    }
    public void reservation(){
        if(validateComponent()) {
            obj[0] = name;
            obj[1] = fName;
            obj[2] = email;
            ;
            obj[3] = number;
            obj[4] = trainName;
            obj[5] = trainNumber;
            obj[6] = age;
            obj[7] = gender;
            obj[8] = classType;
            obj[9] = sourceAddress;
            obj[10] = destinationAddress;
            String dateDisplay = ffd.date.getSelectedItem().toString() + " " + ffd.monthAndYear.getSelectedItem().toString();
            obj[11] = dateDisplay;
            if (db.insert(obj)) {
                ffd.name.setText("");
                ffd.fName.setText("");
                ffd.email.setText("");
                ffd.mNumber.setText("");
                ffd.male.setSelected(true);
                ffd.age.setText("");
                ffd.trainName.setText("");
                ffd.trainNumber.setText("");
                ffd.classType.setSelectedIndex(0);
                ffd.date.setSelectedIndex(0);
                ffd.monthAndYear.setSelectedIndex(0);
                ffd.source.setSelectedIndex(0);
                ffd.destination.setSelectedIndex(0);
                int pnr = db.getPnr(obj);
                JOptionPane.showMessageDialog(null, "PNR Number is " + pnr +
                                "\nYour Ticket is confirmed",
                        "Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(ffd.frame1, "Your Ticket is not booked\nEither" +
                                "Your Ticket Will be booked or any data will wrong",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    public boolean validateComponent(){
        try{
            name =(String)ffd.name.getText();
            fName = (String)ffd.fName.getText();
            email = (String)ffd.email.getText();
            number = ffd.mNumber.getText();
            gender = new String("");
            if(ffd.male.isSelected()){
                gender = "male";
            }
            else if(ffd.female.isSelected()){
                gender = "female";
            }
            else{
                gender = "other";
            }
            age = (String)ffd.age.getText();
            trainName =(String) ffd.trainName.getText();
            trainNumber = (String)ffd.trainNumber.getText();
            classType = ffd.classType.getSelectedItem().toString();
            monthAndYear = String.valueOf(ffd.monthAndYear.getSelectedIndex());
            date = String.valueOf(ffd.date.getSelectedIndex());
            sourceAddress = ffd.source.getSelectedItem().toString();
            destinationAddress = ffd.destination.getSelectedItem().toString();
        if(name.isEmpty()){
            JOptionPane.showMessageDialog(ffd.frame1,"Enter  Name","Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(!(name.trim().matches("^[a-zA-Z' ']+[a-zA-Z]+$"))){
            JOptionPane.showMessageDialog(ffd.frame1,"Invalid Name","Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(email.trim().equals("")){
            JOptionPane.showMessageDialog(ffd.frame1,"Enter Email","Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(!(email.trim().matches("^[a-zA-Z0-9_.-]+[a-zA-Z0-9]+@[a-zA-Z0-9.]+$"))){
            JOptionPane.showMessageDialog(ffd.frame1,"Invalid Email","Warning",JOptionPane.WARNING_MESSAGE);   
            return false;
        }
        if(number.trim().equals("")){
            JOptionPane.showMessageDialog(ffd.frame1,"Enter Mobile Number","Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(!(number.trim().matches("^[0-9]+$"))){
            JOptionPane.showMessageDialog(ffd.frame1,"Invalid Mobile Number","Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if((number.trim().length()<10 || number.trim().length()>12)){
            JOptionPane.showMessageDialog(ffd.frame1,"Incorrect Mobile Number","Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(fName.trim().equals("")){
            JOptionPane.showMessageDialog(ffd.frame1,"Enter Father's Name","Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(trainName.trim().equals("")){
            JOptionPane.showMessageDialog(ffd.frame1,"Enter Train Name","Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(trainNumber.trim().equals("")){
            JOptionPane.showMessageDialog(ffd.frame1,"Enter Train Number","Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(age.trim().equals("")){
            JOptionPane.showMessageDialog(ffd.frame1,"Enter Age","Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(!(age.trim().matches(("^[0-9]+$")))){
            JOptionPane.showMessageDialog(ffd.frame1,"Invalid Age","Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(Integer.parseInt(age)>100){
            JOptionPane.showMessageDialog(ffd.frame1,"Enter Correct Age","Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(sourceAddress.equals(destinationAddress)){
            JOptionPane.showMessageDialog(ffd.frame1,"Source and Destination Address are matched.","Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        switch(monthAndYear){
            case "3":case "5":case "8":case "10":{
                if(Integer.parseInt(date)  > 30){
                    JOptionPane.showMessageDialog(ffd.frame1,"Incorrect Date.","Warning",JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
            case "1":{
                if(Integer.parseInt(date) >28){
                    JOptionPane.showMessageDialog(ffd.frame1,"Incorrect Date.","Warning",JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
        }
        return true;
    }
    catch(Exception e){
        System.out.println("Error is : "+e);
    }
    return false;
    }

}
