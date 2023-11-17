package DigitalLibraryManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Login {
    String userType = "";
    public void login(){
        JFrame frame1 = new JFrame("");
        frame1.setBounds(500,150,600,500);
        frame1.setResizable(false);
        frame1.setUndecorated(true);
        frame1.setLayout(null);
        frame1.setDefaultCloseOperation(frame1.EXIT_ON_CLOSE);
        JPanel panel1 = new JPanel();
        panel1.setBounds(0,0,frame1.getWidth(),frame1.getHeight());
        panel1.setBorder(BorderFactory.createLineBorder(Color.red,4));
        panel1.setLayout(null);
        frame1.add(panel1);
        JLabel label1 = new JLabel("Digital Library Management");
        label1.setFont(new Font("Arial",Font.ITALIC,35));
        label1.setBounds(5,5,panel1.getWidth()-10,50);
        label1.setForeground(Color.blue);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        panel1.add(label1);
        JMenuBar mb = new JMenuBar();
        mb.setBounds(5,60,panel1.getWidth()-10,40);
        mb.setBackground(Color.darkGray);
        JMenu admin = new JMenu("Admin");
        admin.setBounds(10,5,100,30);
        admin.setFont(new Font("Arial",Font.BOLD,22));
        admin.setForeground(Color.black);
        JMenu local = new JMenu("Local");
        local.setBounds(120,5,100,30);
        local.setFont(new Font("Arial",Font.BOLD,22));
        local.setForeground(Color.black);
        JMenu exit = new JMenu("Exit");
        exit.setBounds(panel1.getWidth()-100,5,100,30);
        exit.setFont(new Font("Arial",Font.BOLD,22));
        exit.setForeground(Color.black);
        mb.add(admin);
        mb.add(local);
        mb.add(exit);
        panel1.add(mb);
        mb.setLayout(null);

        JLabel label2 = new JLabel("Welcome");
        label2.setFont(new Font("Arial",Font.BOLD,40));
        label2.setBounds(5,110,panel1.getWidth()-10,panel1.getHeight()-200);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setVerticalAlignment(SwingConstants.CENTER);
        panel1.add(label2);
        JPanel panel2 = new JPanel();
        panel2.setBounds(100,150,400,300);
        panel2.setLayout(null);
        panel2.setBorder(BorderFactory.createLineBorder(Color.black));
        panel2.setBackground(Color.green);
        panel1.add(panel2);
        panel2.setVisible(false);
        JLabel label5 = new JLabel();
        label5.setBounds(10,20,panel2.getWidth()-20,30);
        label5.setFont(new Font("Arial",Font.BOLD,25));
        label5.setForeground(Color.black);
        label5.setHorizontalAlignment(SwingConstants.CENTER);
        panel2.add(label5);

        JLabel label3 = new JLabel("Username");
        label3.setBounds(10,80,130,30);
        label3.setFont(new Font("Arial",Font.BOLD,25));
        label3.setForeground(Color.black);
        panel2.add(label3);

        JTextField t1 = new JTextField();
        t1.setBounds(180,80,170,30);
        t1.setFont(new Font("Arial",Font.BOLD,20));
        t1.setForeground(Color.black);
        panel2.add(t1);

        JLabel label4 = new JLabel("Password");
        label4.setBounds(10,150,130,30);
        label4.setFont(new Font("Arial",Font.BOLD,25));
        label4.setForeground(Color.black);
        panel2.add(label4);

        JPasswordField pass= new JPasswordField();
        pass.setBounds(180,150,170,30);
        pass.setFont(new Font("Arial",Font.BOLD,20));
        pass.setForeground(Color.black);
        pass.setEchoChar('*');
        panel2.add(pass);

        JButton loginbtn = new JButton("Login");
        loginbtn.setBounds(140,230,130,35);
        loginbtn.setFont(new Font("Arial",Font.BOLD,28));
        loginbtn.setBackground(Color.green);
        loginbtn.setForeground(Color.black);
        panel2.add(loginbtn);

        admin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(!(panel2.isVisible())){
                    panel2.setVisible(true);
                }
                t1.setText("");
                pass.setText("");
                label2.setVisible(false);
                label5.setText("Admin");
                userType = "Admin";
            }
        });

        local.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!(panel2.isVisible())){
                    panel2.setVisible(true);
                }
                t1.setText("");
                pass.setText("");
                label2.setVisible(false);
                label5.setText("Local User");
                userType = "Local User";
            }
        });
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null,"Exit from the application",
                        "Information",JOptionPane.INFORMATION_MESSAGE);
                frame1.dispose();
            }
        });
        loginbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = t1.getText();
                char password[] = pass.getPassword();
                Database db = new Database();
                if(username.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Please Enter Username",
                            "Warning",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                else if(password.length == 0){
                    JOptionPane.showMessageDialog(null,"Please Enter Password",
                            "Warning",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(db.validateLogin(username,password,userType)){
                    JOptionPane.showConfirmDialog(frame1,"Login Successfull","Information",
                            JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
                    AdminMainPage amp = new AdminMainPage();
                    frame1.dispose();
                    amp.adminMainPage(userType,username);

                }
            }
        });
        frame1.setVisible(true);
    }

}
