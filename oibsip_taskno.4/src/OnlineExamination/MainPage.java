package OnlineExamination;
import javax.swing.*;
import javax.print.DocFlavor;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.nio.file.*;
import java.util.*;
public class MainPage {
    String user = "Guest";
    JPanel panel1;
    JFrame frame1,frame2,frame3;
    String information[] = new String[5];

    public void mainPage(){
        frame1 = new JFrame();
        frame1.setBounds(400,90,800,630);
        frame1.setResizable(false);
        frame1.setUndecorated(true);
        frame1.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0,0,frame1.getWidth(),frame1.getHeight());
        panel.setBorder(BorderFactory.createLineBorder(Color.black,5));
        panel.setBackground(Color.white);
        panel.setLayout(null);
        frame1.add(panel);

        JLabel label1 = new JLabel("Online Examination");
        label1.setBounds(10,10,panel.getWidth()-20,80);
        label1.setFont(new Font("Arial",Font.ITALIC,60));
        label1.setForeground(Color.black);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label1);

        JMenuBar mb = new JMenuBar();
        mb.setBounds(5,100,panel.getWidth()-10,40);
        mb.setBackground(Color.gray);
        mb.setLayout(null);
        JMenu exam = new JMenu("Exam");
        exam.setFont(new Font("Arial",Font.BOLD,19));
        exam.setBounds(30,5,80,30);
        JMenuItem python = new JMenuItem("Python");
        JMenuItem c = new JMenuItem("C Language");
        JMenuItem java = new JMenuItem("Java");
        JMenuItem html = new JMenuItem("HTML");
        JMenuItem css = new JMenuItem("CSS");
        exam.add(python);
        exam.add(c);
        exam.add(java);
        exam.add(html);
        exam.add(css);
        JMenu update = new JMenu("Update");
        update.setFont(new Font("Arial",Font.BOLD,19));
        update.setBounds(120,5,80,30);
        JMenuItem profile = new JMenuItem("Profile");
        JMenuItem password = new JMenuItem("Password");
        update.add(profile);
        update.add(password);
        JMenu login = new JMenu("Log in");
        login.setFont(new Font("Arial",Font.BOLD,19));
        login.setBounds(panel.getWidth()-210,5,80,30);
        JMenu logout = new JMenu("Log Out");
        logout.setFont(new Font("Arial",Font.BOLD,19));
        logout.setBounds(panel.getWidth()-120,5,90,30);
        exam.setForeground(Color.black);
        update.setForeground(Color.black);
        login.setForeground(Color.black);
        logout.setForeground(Color.black);

        mb.add(exam);
        mb.add(update);
        mb.add(login);
        mb.add(logout);
        panel.add(mb);

        JLabel label2 = new JLabel("Welcome");
        label2.setBounds(10,140,panel.getWidth()-20,60);
        label2.setFont(new Font("Arial",Font.ITALIC,50));
        label2.setForeground(Color.black);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label2);

        JLabel label3 = new JLabel();
        label3.setBounds(10,180,panel.getWidth()-20,60);
        label3.setFont(new Font("Arial",Font.ITALIC,30));
        label3.setForeground(Color.black);
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label3);
        label3.setText(user);
        if(!(user.equalsIgnoreCase("guest"))){
            Database db = new Database();
            db.getData(information,user);
        }
        JPanel panel3 = new JPanel();
        panel3.setLayout(null);
        panel3.setBounds(150,240,500,350);
        panel3.setBorder(BorderFactory.createLineBorder(Color.black));
        panel3.setBackground(Color.white);
        panel.add(panel3);
        JLabel label7 = new JLabel();
        label7.setBounds(0,5,panel3.getWidth(),40);
        label7.setFont(new Font("Arial",Font.BOLD,30));
        label7.setHorizontalAlignment(SwingConstants.CENTER);
        label7.setText(user+" Detail");
        panel3.add(label7);
        JLabel label8 = new JLabel("Email:  ");
        label8.setBounds(5,60,140,40);
        label8.setFont(new Font("Arial",Font.BOLD,30));
        panel3.add(label8);
        JLabel label9 = new JLabel(information[0]);
        label9.setBounds(140,60,350,40);
        label9.setFont(new Font("Arial",Font.BOLD,22));
        panel3.add(label9);

        JLabel label10 = new JLabel("WhatsApp No. :  ");
        label10.setBounds(5,110,220,40);
        label10.setFont(new Font("Arial",Font.BOLD,25));
        panel3.add(label10);
        JLabel label11 = new JLabel(information[1]);
        label11.setBounds(230,110,300,40);
        label11.setFont(new Font("Arial",Font.BOLD,30));
        panel3.add(label11);

        JLabel label12 = new JLabel("College Name:  ");
        label12.setBounds(5,160,210,40);
        label12.setFont(new Font("Arial",Font.BOLD,25));
        panel3.add(label12);
        JLabel label13 = new JLabel(information[2]);
        label13.setBounds(220,160,300,40);
        label13.setFont(new Font("Arial",Font.BOLD,22));
        panel3.add(label13);

        JLabel label14 = new JLabel("City:  ");
        label14.setBounds(5,210,100,40);
        label14.setFont(new Font("Arial",Font.BOLD,30));
        panel3.add(label14);
        JLabel label15 = new JLabel(information[3]);
        label15.setBounds(120,210,300,40);
        label15.setFont(new Font("Arial",Font.BOLD,30));
        panel3.add(label15);

        JLabel label16 = new JLabel("Course:  ");
        label16.setBounds(5,260,170,40);
        label16.setFont(new Font("Arial",Font.BOLD,30));
        panel3.add(label16);
        JLabel label17 = new JLabel(information[4]);
        label17.setBounds(170,260,300,40);
        label17.setFont(new Font("Arial",Font.BOLD,30));
        panel3.add(label17);

        JLabel label18 = new JLabel("Your Account is Updated");
        label18.setBounds(0,305,panel3.getWidth(),35);
        label18.setFont(new Font("Arial",Font.BOLD,30));
        label18.setHorizontalAlignment(SwingConstants.CENTER);
        panel3.add(label18);
        if(!(user.equalsIgnoreCase("guest"))){
            for(int i =0;i<information.length;i++){
                if(information[i].equals("Not Set")){
                    label18.setText("Kindly Update Your Account");
                    break;
                }
            }
        }

        if(user.equalsIgnoreCase("guest")){
            panel3.setVisible(false);
        }

        panel1 = new JPanel();
        panel1.setBounds(200,260,400,275);
        panel1.setBorder(BorderFactory.createLineBorder(Color.black));
        panel1.setBackground(Color.lightGray);
        panel.add(panel1);
        panel1.setLayout(null);

        JLabel label4 = new JLabel("");
        label4.setBounds(10,20,panel1.getWidth()-20,60);
        label4.setFont(new Font("Arial",Font.ITALIC,50));
        label4.setForeground(Color.black);
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        panel1.add(label4);

        JLabel label5 = new JLabel();
        label5.setBounds(10,100,panel1.getWidth()-20,40);
        label5.setFont(new Font("Arial",Font.ITALIC,19));
        label5.setForeground(Color.black);
        label5.setHorizontalAlignment(SwingConstants.CENTER);
        panel1.add(label5);

        JLabel label6 = new JLabel("Time = 10:00 Minute");
        label6.setBounds(10,140,panel1.getWidth()-20,40);
        label6.setFont(new Font("Arial",Font.ITALIC,21));
        label6.setForeground(Color.black);
        label6.setHorizontalAlignment(SwingConstants.CENTER);
        panel1.add(label6);

        JButton startQuiz = new JButton("Start Quiz");
        startQuiz.setBounds(130,200,130,40);
        startQuiz.setFont(new Font("Arial",Font.ITALIC,21));
        startQuiz.setForeground(Color.white);
        startQuiz.setBackground(Color.green);
        startQuiz.setHorizontalAlignment(SwingConstants.CENTER);
        panel1.add(startQuiz);
        panel1.setVisible(false);

        JButton close = new JButton("Close");
        close.setFont(new Font("Arial",Font.BOLD,22));
        close.setBounds(panel.getWidth()-130,frame1.getHeight()-70,100,40);
        close.setBackground(Color.black);
        close.setForeground(Color.white);
        panel.add(close);

        password.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(user.equalsIgnoreCase("guest"))){
                    frame1.dispose();
                    passwordUpdate();
                }
            }
        });
        profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(user.equalsIgnoreCase("guest"))){
                    frame1.dispose();
                    updateProfile();
                }
            }
        });
        if(user.equalsIgnoreCase("guest")){
            logout.setEnabled(false);
        }
        else{
            login.setEnabled(false);
        }
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(user.equalsIgnoreCase("guest")) {
                    Login l = new Login();
                    frame1.dispose();
                    l.loginPage();
                }
            }
        });
        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!(user.equalsIgnoreCase("guest"))){
                    int selection = JOptionPane.showConfirmDialog(null,"Do you want to Logged out the Session",
                            "Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
                    if(selection == JOptionPane.YES_OPTION){
                        JOptionPane.showMessageDialog(null,"Session is Closing....","Information",JOptionPane.INFORMATION_MESSAGE);
                        frame1.dispose();
                        user = "Guest";
                        mainPage();
                    }

                }
            }
        });
        startQuiz.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(user.equalsIgnoreCase("guest")){
                    JOptionPane.showMessageDialog(null,"You have To First Log in with any account for participating in exam",
                            "Error",JOptionPane.PLAIN_MESSAGE);
                }
                else{
//                    if(label4.getText().equalsIgnoreCase("python")){
//                        Question q = new Question("Python",user);
//                        q.question(0);
//                    }
                    String str = label4.getText();
                    str = str.charAt(0)+str.substring(1).toLowerCase();
                    Question q = new Question(str,user);
                    frame1.dispose();
                    q.question(0);
                }
            }
        });
        python.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label4.setText("PYTHON");
                label5.setText("25 Questions covering the basics of Python");
                panel3.setVisible(false);
                panel1.setVisible(true);
                            }
        });
        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label4.setText("C");
                label5.setText("25 Questions covering the basics of C");
                panel3.setVisible(false);
                panel1.setVisible(true);
            }
        });
        java.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label4.setText("JAVA");
                label5.setText("25 Questions covering the basics of Java");
                panel3.setVisible(false);
                panel1.setVisible(true);
            }
        });
        html.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label4.setText("HTML");
                label5.setText("25 Questions covering the basics of Html");
                panel3.setVisible(false);
                panel1.setVisible(true);
            }
        });
        css.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label4.setText("CSS");
                label5.setText("25 Questions covering the basics of Css");
                panel3.setVisible(false);
                panel1.setVisible(true);
            }
        });
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1.dispose();
            }
        });
        frame1.setVisible(true);
    }
    private void passwordUpdate(){
        frame2 = new JFrame();
        frame2.setBounds(500,150,400,350);
        frame2.setUndecorated(true);
        frame2.setResizable(false);
        frame2.setLayout(null);
        JPanel panel2 = new JPanel();
        panel2.setBounds(0,0,frame2.getWidth(),frame2.getHeight());
        panel2.setBackground(Color.white);
        panel2.setBorder(BorderFactory.createLineBorder(Color.black,5));
        panel2.setLayout(null);
        frame2.add(panel2);
        JLabel label1 = new JLabel("Update Password");
        label1.setBounds(5,5,panel2.getWidth()-10,60);
        label1.setFont(new Font("Arial",Font.BOLD,35));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        panel2.add(label1);
        JLabel label2 = new JLabel("Enter Old Password");
        label2.setBounds(5,90,panel2.getWidth()-10,40);
        label2.setFont(new Font("Arial",Font.BOLD,22));
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        panel2.add(label2);

        JPasswordField oldPass = new JPasswordField();
        oldPass.setBounds(75, 130, 200, 35);
        oldPass.setFont(new Font("Arial", Font.BOLD, 20));
        oldPass.setEchoChar('*');
        ImageIcon image = new ImageIcon("C:\\Users\\TARKESHWAR PRASAD\\Desktop\\program\\Task 5\\images1.jpg");
        ImageIcon image2 = new ImageIcon("C:\\Users\\TARKESHWAR PRASAD\\Desktop\\program\\Task 5\\images.jpg");
        JLabel image2Label = new JLabel(image);
        image2Label.setBounds(275, 130, image.getIconWidth(), image.getIconHeight());
        panel2.add(image2Label);
        JLabel image3Label = new JLabel(image2);
        image3Label.setBounds(275, 130, image2.getIconWidth(), image.getIconHeight());
        panel2.add(image3Label);
        image2Label.setVisible(false);
        panel2.add(oldPass);
        image3Label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                image3Label.setVisible(false);
                image2Label.setVisible(true);
                oldPass.setEchoChar((char) 0);
            }
        });
        image2Label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                image2Label.setVisible(false);
                image3Label.setVisible(true);
                oldPass.setEchoChar('*');
            }
        });
        JLabel label3 = new JLabel("Enter Old Password");
        label3.setBounds(5,180,panel2.getWidth()-10,40);
        label3.setFont(new Font("Arial",Font.BOLD,22));
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        panel2.add(label3);
        JPasswordField newPass = new JPasswordField();
        newPass.setBounds(75, 220, 200, 35);
        newPass.setFont(new Font("Arial", Font.BOLD, 20));
        newPass.setEchoChar('*');
        JLabel imageLabel = new JLabel(image);
        imageLabel.setBounds(275, 220, image.getIconWidth(), image.getIconHeight());
        panel2.add(imageLabel);
        JLabel image1Label = new JLabel(image2);
        image1Label.setBounds(275, 220, image2.getIconWidth(), image.getIconHeight());
        panel2.add(image1Label);
        imageLabel.setVisible(false);
        panel2.add(newPass);
        image1Label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                image1Label.setVisible(false);
                imageLabel.setVisible(true);
                newPass.setEchoChar((char) 0);
            }
        });
        imageLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                imageLabel.setVisible(false);
                image1Label.setVisible(true);
                newPass.setEchoChar('*');
            }
        });
        JButton home = new JButton("Home");
        home.setBounds(20,290,100,40);
        home.setFont(new Font("Arial",Font.BOLD,20));
        panel2.add(home);
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.dispose();
                frame1.setVisible(true);
            }
        });
        JButton confirm = new JButton("Change");
        confirm.setBounds(260,290,120,40);
        confirm.setFont(new Font("Arial",Font.BOLD,20));
        panel2.add(confirm);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char opass[] = oldPass.getPassword();
                char npass[] = newPass.getPassword();
                if(opass.length == 0){
                    JOptionPane.showMessageDialog(null,"Enter old Password",
                            "Warning",JOptionPane.WARNING_MESSAGE);
                }
                else if(npass.length == 0){
                    JOptionPane.showMessageDialog(null,"Enter New Password",
                            "Warning",JOptionPane.WARNING_MESSAGE);
                }
                else{
                    Database db = new Database();
                    if(db.changePassword(user,opass,npass)){
                        frame2.dispose();
                        frame1.setVisible(true);
                    }
                }
            }
        });
        frame2.setVisible(true);
    }
    private void updateProfile(){
        frame3 = new JFrame();
        frame3.setBounds(500,150,400,600);
        frame3.setUndecorated(true);
        frame3.setResizable(false);
        frame3.setLayout(null);
        JPanel panel2 = new JPanel();
        panel2.setBounds(0,0,frame3.getWidth(),frame3.getHeight());
        panel2.setBackground(Color.white);
        panel2.setBorder(BorderFactory.createLineBorder(Color.black,5));
        panel2.setLayout(null);
        frame3.add(panel2);
        JLabel label1 = new JLabel("Update Profile");
        label1.setBounds(5,5,panel2.getWidth()-10,60);
        label1.setFont(new Font("Arial",Font.BOLD,35));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        panel2.add(label1);

        JLabel label2 = new JLabel("Enter your Email");
        label2.setBounds(20,80,200,30);
        label2.setFont(new Font("Arial",Font.BOLD,22));
        panel2.add(label2);

        JTextField email = new JTextField();
        email.setBounds(10,115,300,30);
        email.setFont(new Font("Arial",Font.BOLD,22));
        panel2.add(email);

        JLabel label3 = new JLabel("Enter Whats App no.");
        label3.setBounds(20,170,300,30);
        label3.setFont(new Font("Arial",Font.BOLD,22));
        panel2.add(label3);

        JTextField number = new JTextField();
        number.setBounds(10,205,300,30);
        number.setFont(new Font("Arial",Font.BOLD,22));
        panel2.add(number);

        JLabel label4 = new JLabel("Enter College Name");
        label4.setBounds(20,260,300,30);
        label4.setFont(new Font("Arial",Font.BOLD,22));
        panel2.add(label4);

        JTextField college = new JTextField();
        college.setBounds(10,295,300,30);
        college.setFont(new Font("Arial",Font.BOLD,22));
        panel2.add(college);

        JLabel label5 = new JLabel("Enter City");
        label5.setBounds(20,350,300,30);
        label5.setFont(new Font("Arial",Font.BOLD,22));
        panel2.add(label5);

        JTextField city = new JTextField();
        city.setBounds(10,385,300,30);
        city.setFont(new Font("Arial",Font.BOLD,22));
        panel2.add(city);

        JLabel label6 = new JLabel("Enter Course");
        label6.setBounds(20,440,300,30);
        label6.setFont(new Font("Arial",Font.BOLD,22));
        panel2.add(label6);

        String[] chooseCourse = {"BCA","B-TECH","M-TECH","BA","MA","MCA","BSC-IT"};
        JComboBox course = new JComboBox<>(chooseCourse);
        course.setBounds(10,475,300,30);
        course.setFont(new Font("Arial",Font.BOLD,22));
        panel2.add(course);

        if(!information[0].equals("Not Set")){
            email.setText(information[0]);
        }
        if(!information[1].equals("Not Set")){
            number.setText(information[1]);
        }
        if(!information[2].equals("Not Set")){
            college.setText(information[2]);
        }
        if(!information[3].equals("Not Set")){
            city.setText(information[3]);
        }
        if(!information[4].equals("Not Set")){
            course.setSelectedItem(information[4]);
        }
        JButton home = new JButton("Home");
        home.setBounds(20,panel2.getHeight()-70,100,40);
        home.setFont(new Font("Arial",Font.BOLD,20));
        panel2.add(home);
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame3.dispose();
                frame1.setVisible(true);
            }
        });
        String obj[] = new String[5];
        JButton update = new JButton("Update");
//        update.setBounds(20,panel2.getHeight()-70,100,40);
        update.setFont(new Font("Arial",Font.BOLD,20));
        update.setBounds(250,panel2.getHeight()-70,120,40);
        panel2.add(update);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obj[0] = (String)email.getText();
                obj[1] = (String)number.getText();
                obj[2] = (String)college.getText();
                obj[3] = (String)city.getText();
                obj[4] = (String)course.getSelectedItem();
                if(validate(obj)){
                    Database db = new Database();
                    if(db.profileUpdate(obj,user)){
                        JOptionPane.showMessageDialog(frame3,"Account Updated","Information",
                                JOptionPane.INFORMATION_MESSAGE);
                        frame3.dispose();
                        frame1.setVisible(true);
                    }
//                    else{
//                        JOptionPane.showMessageDialog(frame3,"Account Not Updated","Information",
//                                JOptionPane.INFORMATION_MESSAGE);
//                    }
                }
            }
        });
        frame3.setVisible(true);
    }
    private boolean validate(String[] obj){
        if(!(obj[0].trim().matches("^[0-9a-zA-Z._-]+@[a-zA-Z]+.[a-zA-Z]+$"))){
            JOptionPane.showMessageDialog(frame3,"Enter Valid Email(John.james@gmail.com)",
                    "Warning",JOptionPane.WARNING_MESSAGE);
         return false;
        }
        if((!(obj[1].trim().matches("^[0-9]+$")))){
            JOptionPane.showMessageDialog(frame3,"Enter Valid Mobile Number",
                    "Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(!(obj[1].isEmpty()) && !(obj[1].length()>=10 && obj[1].length()<13)){
            JOptionPane.showMessageDialog(frame3,"Enter Valid Mobile Number",
                    "Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
//
//    public static void main(String args[]){
//        MainPage mp = new MainPage();
////        mp.mainPage();
////        mp.passwordUpdate();
//        mp.updateProfile();
//    }
}
