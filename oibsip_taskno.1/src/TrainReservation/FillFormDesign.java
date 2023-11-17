 package TrainReservation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class FillFormDesign {
    JFrame frame1;
    JPanel panel,panel2,panel3;
    JLabel label1,label2,label3,label4,label5,label6,label7,label8,label9,label10,label11,label12,label13,label14;
    JTextField name,fName,age,trainNumber,trainName,email,mNumber;
    JComboBox classType,date,monthAndYear,source,destination;
    JButton insert,logout;
    JRadioButton male,female,other;
    public void formDesign(){
        frame1 = new JFrame("Train Reservation and Cancelation form");
        frame1.setLayout(null);
        frame1.setBounds(300,100,800,650);
        panel = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        frame1.setDefaultCloseOperation(frame1.EXIT_ON_CLOSE);
        frame1.add(panel);
        frame1.add(panel2);
        frame1.add(panel3);
        panel.setBounds(0,0,800,50);
        panel2.setBounds(0,50,800,500);
        panel3.setBounds(0,550,800,100);
        panel.setBackground(Color.red);
        panel2.setBackground(Color.yellow);
        panel3.setBackground(Color.green);
        // panel.setSize(200,800);
        panel.setLayout(null);
        panel2.setLayout(null);
        panel3.setLayout(null);
        label1 = new JLabel("Registration");
        label1.setFont(new Font("Arial",Font.BOLD,23));
        label1.setForeground(Color.white);
        label1.setBounds(10,10,150,30);
        panel.add(label1);
        label2 = new JLabel("Cancellation");
        label2.setFont(new Font("Arial",Font.BOLD,23));
        label2.setForeground(Color.black);
        label2.setBounds(210,10,150,30);
        panel.add(label2);
        if((label1.isEnabled())){
            label1.setForeground(Color.black);
            label2.setEnabled(false);
        }
        Cancellation c = new Cancellation(this);
        label1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                if(!(label1.isEnabled())){
                    label1.setForeground(Color.black);
                    label1.setEnabled(true);
                    label2.setEnabled(false);
                }
            }
        });
        label2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                if(!(label2.isEnabled())){
                    label2.setForeground(Color.black);
                    label2.setEnabled(true);
                    label1.setEnabled(false);
                    c.cancelled();
                }
            }
        });
        logout = new JButton("Logout");
        logout.setFont(new Font("Arial",Font.PLAIN,22));
        logout.setBounds(600,10,120,40);
        panel3.add(logout);
        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame1.dispose();
                LoginDesign ld = new LoginDesign();
                ld.loginPage();
            }
        });
        
        Reserved r = new Reserved(this);
        insert = new JButton();
        insert.setFont(new Font("Arial",Font.PLAIN,22));
        insert.setBounds(340,10,120,40);
        panel3.add(insert);
        if(label1.isEnabled()){
            insert.setText("Insert");
        }
        else{
            insert.setText("OK");
        }
        insert.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(insert.getText().equalsIgnoreCase("insert")){
                        r.reservation();
                }
            }
        });
        label3 = new JLabel("Enter Your Name");
        label3.setFont(new Font("Arial",Font.PLAIN,22));
        label3.setBounds(10,10,250,30);
        panel2.add(label3);
        name = new JTextField();
        name.setFont(new Font("Arial",Font.PLAIN,22));
        name.setBounds(350,10,300,30);
        panel2.add(name);
        label4 = new JLabel("Enter Your Father's Name");
        label4.setFont(new Font("Arial",Font.PLAIN,22));
        label4.setBounds(10,50,300,30);
        panel2.add(label4);
        fName = new JTextField();
        fName.setFont(new Font("Arial",Font.PLAIN,22));
        fName.setBounds(350,50,300,30);
        panel2.add(fName);
        label11 = new JLabel("Enter Your Email Id");
        label11.setFont(new Font("Arial",Font.PLAIN,22));
        label11.setBounds(10,90,300,30);
        panel2.add(label11);
        email = new JTextField();
        email.setFont(new Font("Arial",Font.PLAIN,22));
        email.setBounds(350,90,300,30);
        panel2.add(email);
        label12 = new JLabel("Enter Your Contact Number");
        label12.setFont(new Font("Arial",Font.PLAIN,22));
        label12.setBounds(10,130,300,30);
        panel2.add(label12);
        mNumber = new JTextField();
        mNumber.setFont(new Font("Arial",Font.PLAIN,22));
        mNumber.setBounds(350,130,300,30);
        panel2.add(mNumber);
        label5 = new JLabel("Enter Age ");
        label5.setFont(new Font("Arial",Font.PLAIN,22));
        label5.setBounds(10,220,150,30);
        panel2.add(label5);
        age = new JTextField();
        age.setFont(new Font("Arial",Font.PLAIN,22));
        age.setBounds(170,220,100,30);
        panel2.add(age);
        label6 = new JLabel("Enter Train Number");
        label6.setFont(new Font("Arial",Font.PLAIN,22));
        label6.setBounds(350,220,200,30);
        panel2.add(label6);
        trainNumber = new JTextField();
        trainNumber.setFont(new Font("Arial",Font.PLAIN,22));
        trainNumber.setBounds(600,220,150,30);
        panel2.add(trainNumber);
        label7 = new JLabel("Enter Train Name");
        label7.setFont(new Font("Arial",Font.PLAIN,22));
        label7.setBounds(10,170,300,30);
        panel2.add(label7);
        trainName = new JTextField();
        trainName.setFont(new Font("Arial",Font.PLAIN,24));
        trainName.setBounds(350,170,300,30);
        panel2.add(trainName);
        label8 = new JLabel("Enter Gender");
        label8.setFont(new Font("Arial",Font.PLAIN,22));
        label8.setBounds(10,260,200,30);
        panel2.add(label8);
        ButtonGroup btngroup = new ButtonGroup();
        // panel2.add(btngroup);
        male = new JRadioButton("Male");
        male.setFont(new Font("Arial",Font.PLAIN,24));
        male.setBounds(200,260,100,30);
        male.setBackground(Color.yellow);
        panel2.add(male);
        female = new JRadioButton("Female");
        female.setFont(new Font("Arial",Font.PLAIN,24));
        female.setBounds(320,260,120,30);
        female.setBackground(Color.yellow);
        panel2.add(female);
        other = new JRadioButton("Other");
        other.setFont(new Font("Arial",Font.PLAIN,24));
        other.setBounds(460,260,100,30);
        other.setBackground(Color.yellow);
        panel2.add(other);
        male.setSelected(true);
        btngroup.add(male);
        btngroup.add(female);
        btngroup.add(other);     
        label9 = new JLabel("Class Type");
        label9.setFont(new Font("Arial",Font.PLAIN,26));
        label9.setBounds(10,300,250,30);
        panel2.add(label9);
        String[] className = {"Sleeper","AC 3 Tier","Second","Ac Chair","Ac First","Executive class Coach","Second Ac Berths","Fc","General"};
        classType = new JComboBox<>(className);
        classType.setFont(new Font("Arial",Font.PLAIN,22));
        classType.setBounds(300,300,300,30);
        panel2.add(classType);
        label10 = new JLabel("Enter Date of Journey");
        label10.setFont(new Font("Arial",Font.PLAIN,24));
        label10.setBounds(10,340,300,30);
        panel2.add(label10);
        String dateTravel[] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        String monthAndYearString[]={"January 2023","February 2023","March 2023","April 2023","May 2023","June 2023","July 2023","August 2023","September 2023","October 2023","November 2023","December 2023"};
        date = new JComboBox<>(dateTravel);
        date.setFont(new Font("Arial",Font.PLAIN,24));
        date.setBounds(320,340,70,30);
        panel2.add(date);
        monthAndYear = new JComboBox<>(monthAndYearString);
        monthAndYear.setFont(new Font("Arial",Font.PLAIN,24));
        monthAndYear.setBounds(390,340,200,30);
        panel2.add(monthAndYear);
        label13 = new JLabel("Source Location");
        label13.setFont(new Font("Arial",Font.PLAIN,26));
        label13.setBounds(10,390,250,30);
        panel2.add(label13);
        String location[] = {"Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh","Goa","Gujrat","Haryana","Himachal Pradesh","Jammu and Kashmir","Jharkhand","Karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana","Tripura","Uttar Pradesh","Uttarakhand","West Bengal"};
        source = new JComboBox<>(location);
        source.setFont(new Font("Arial",Font.PLAIN,22));
        source.setBounds(300,390,300,30);
        panel2.add(source);
        label14 = new JLabel("Destination Location");
        label14.setFont(new Font("Arial",Font.PLAIN,26));
        label14.setBounds(10,440,250,30);
        panel2.add(label14);
        destination = new JComboBox<>(location);
        destination.setFont(new Font("Arial",Font.PLAIN,22));
        destination.setBounds(300,440,300,30);
        panel2.add(destination);
        
        frame1.setVisible(true);
        
    }
    public void resetAllComponent(){
        name.setText("");
        fName.setText("");
        email.setText("");
        mNumber.setText("");
        male.setSelected(true);
        age.setText("");
        trainName.setText("");
        trainNumber.setText("");
        classType.setSelectedIndex(0);
        date.setSelectedIndex(0);
        monthAndYear.setSelectedIndex(0);
        source.setSelectedIndex(0);
        destination.setSelectedIndex(0);
    }
    public Object getDataInObject(){
        Object obj[] = new Object[12];
        String pname =(String)name.getText();
       String pfName = (String)fName.getText();
       String pemail = (String)email.getText();
       String pnumber = mNumber.getText();
       String pgender = new String("");
        if(male.isSelected()){
            pgender = "male";
        }
        else if(female.isSelected()){
            pgender = "female";
        }
        else{
            pgender = "other";
        }
        String page = (String)age.getText();
        String ptrainName =(String) trainName.getText();
        String ptrainNumber = (String)trainNumber.getText();
        String pclassType = classType.getSelectedItem().toString();
        String pmonthAndYear = String.valueOf(monthAndYear.getSelectedIndex());
        String pdate = String.valueOf(date.getSelectedIndex());
        String psourceAddress = source.getSelectedItem().toString();
        String pdestinationAddress = destination.getSelectedItem().toString();
        obj[0] = pname;
        obj[1] = pfName;
        obj[2] = pemail;;
        obj[3] = pnumber;
        obj[4] = ptrainName;
        obj[5] = ptrainNumber;
        obj[6] = page;
        obj[7] = pgender;
        obj[8] = pclassType;
        obj[9] = psourceAddress;
        obj[10] = pdestinationAddress;
        String dateDisplay = pdate+pmonthAndYear;
        obj[11] = dateDisplay;
        return obj;
    }
}
