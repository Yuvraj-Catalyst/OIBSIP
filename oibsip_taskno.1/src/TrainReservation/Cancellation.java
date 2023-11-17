 package TrainReservation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

public class Cancellation {
    FillFormDesign ffd;
    Database db = new Database();
    Object obj[] = new Object[12];

    Cancellation(FillFormDesign ffd){
        this.ffd = ffd;
    }
    public void cancelled(){
        // ffd.insert.setText("Delete");
        if(ffd.label2.isEnabled()){
            ffd.insert.setText("OK");
        }
        ffd.label1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ffd.label1.setForeground(Color.black);
                ffd.label1.setEnabled(true);
                ffd.label2.setEnabled(false);
                ffd.insert.setText("Insert");
                ffd.name.setText("");
                ffd.fName.setText("");
                ffd.email.setText("");
                ffd.mNumber.setText("");
                ffd.age.setText("");
                ffd.trainName.setText("");
                ffd.trainNumber.setText("");
                ffd.male.setSelected(true);
                ffd.date.setSelectedIndex(0);
                ffd.monthAndYear.setSelectedIndex(0);
                ffd.source.setSelectedIndex(0);
                ffd.destination.setSelectedIndex(0);
            }
        });
        String str = JOptionPane.showInputDialog(ffd.frame1,"Enter Your PNR Number");
        if(str == null){
            ffd.frame1.dispose();
            ffd.formDesign();
            return;

        }
        if(str.isEmpty()){
            JOptionPane.showMessageDialog(ffd.frame1,"Please Enter PNR Number","Information",JOptionPane.INFORMATION_MESSAGE);
            ffd.label2.setEnabled(false);
            ffd.label1.setEnabled(true);
            ffd.label1.setBackground(Color.black);
            return;
        }
        if(!(str.matches("^[0-9]+$"))){
            JOptionPane.showMessageDialog(ffd.frame1,"Pnr Number not matched","Information",JOptionPane.INFORMATION_MESSAGE);
            ffd.insert.setText("Insert");
            ffd.label2.setEnabled(false);
            ffd.label1.setEnabled(true);
            ffd.label1.setBackground(Color.black);
            return;
        }
        if(db.validatePnrNumber(Integer.parseInt(str))){
            JOptionPane.showMessageDialog(ffd.frame1,"Pnr number matched","Information",JOptionPane.INFORMATION_MESSAGE);
            db.select(obj,Integer.parseInt(str));
            setData();
            ffd.insert.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    if(ffd.insert.getText().equalsIgnoreCase("ok")){
                        int selection = JOptionPane.showConfirmDialog(ffd.frame1,"Are you sure to cancel " +
                                "this ticket?","confirmation",JOptionPane.YES_NO_OPTION);
                        if(JOptionPane.YES_OPTION == selection){
                            db.delete(Integer.parseInt(str));
                            ffd.resetAllComponent();
                            ffd.label2.setEnabled(false);
                            ffd.label1.setEnabled(true);
                            ffd.label1.setBackground(Color.black);
                            ffd.insert.setText("Insert");
                        }
                        // ffd.frame1.dispose();
                        // ffd.formDesign();
                        return;
                    }
                }
            });
            return;
        }
        else{
            JOptionPane.showMessageDialog(ffd.frame1,"Pnr Number not matched","Information",JOptionPane.INFORMATION_MESSAGE);
            ffd.insert.setText("Insert");
            ffd.label2.setEnabled(false);
            ffd.label1.setEnabled(true);
            ffd.label1.setBackground(Color.black);
            // ffd.frame1.dispose();
            // ffd.formDesign();
            return;
        }
    }
    public void setData(){
        String name = obj[0].toString();
        String fName = obj[1].toString();
        String email = obj[2].toString();
        String number = obj[3].toString();
        String trainName = obj[4].toString();
        String trainNumber = obj[5].toString();
        String age = obj[6].toString();
        String gender = obj[7].toString();
        String classType = obj[8].toString();
        String source = obj[9].toString();
        String destination = obj[10].toString();
        String date = obj[11].toString();
        String day = date.substring(0,2);
        String monthAndYear = date.substring(2).trim();
        ffd.name.setText(name);
        ffd.fName.setText(fName);
        ffd.email.setText(email);
        ffd.mNumber.setText(number);
        ffd.trainName.setText(trainName);
        ffd.trainNumber.setText(trainNumber);
        ffd.age.setText(age);
        if(gender == "male"){
            ffd.male.setSelected(true);
        }
        else if(gender == "female"){
            ffd.female.setSelected(true);
        }
        else{
            ffd.other.setSelected(true);
        }
        ffd.classType.setSelectedItem(classType);
        ffd.source.setSelectedItem(source);
        ffd.destination.setSelectedItem(destination);
        ffd.date.setSelectedItem(day);
        ffd.monthAndYear.setSelectedItem(monthAndYear);
    }

}
