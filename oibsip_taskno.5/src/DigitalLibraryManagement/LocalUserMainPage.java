package DigitalLibraryManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;


public class LocalUserMainPage{
    JFrame frame1;

    JPanel panel1;
    JLabel label;
    Database db = new Database();
    JMenu book,issue,returnBook,search,email,logout;
    String[] obj = new String[5];
    String date="";
    Validate v= new Validate();
    DateMonth dm;
    int id;
    public void localUserMainPage(String user,String userType){
        String str2="";
        for(int i = 0;i<user.length();i++){
            if(user.charAt(i)>=48 && user.charAt(i)<=57){
                str2 = user.substring(i);
                break;
            }
        }
        id = Integer.parseInt(str2);
        user = db.getUsername(id);
        frame1 = new JFrame("");
        frame1.setBounds(400,100,800,650);
        frame1.setResizable(false);
        frame1.setUndecorated(true);
        frame1.setLayout(null);
        frame1.setDefaultCloseOperation(frame1.EXIT_ON_CLOSE);

        panel1 = new JPanel();
        panel1.setBounds(0,0,frame1.getWidth(),frame1.getHeight());
        panel1.setBorder(BorderFactory.createLineBorder(Color.red,4));
        panel1.setLayout(null);
        frame1.add(panel1);

        JLabel label1 = new JLabel("Welcome "+user);
        label1.setFont(new Font("Arial",Font.ITALIC,35));
        label1.setBounds(5,5,panel1.getWidth()-10,50);
        label1.setForeground(Color.blue);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        panel1.add(label1);
        JMenuBar mb = new JMenuBar();
        mb.setBounds(5,60,panel1.getWidth()-10,40);
        mb.setBackground(Color.darkGray);
        mb.setLayout(null);
        panel1.add(mb);

        book= new JMenu("Book");
        book.setBounds(10,5,70,30);
        book.setFont(new Font("Arial",Font.BOLD,22));
        book.setForeground(Color.black);

        issue= new JMenu("Issue");
        issue.setBounds(90,5,80,30);
        issue.setFont(new Font("Arial",Font.BOLD,22));
        issue.setForeground(Color.black);

        returnBook= new JMenu("Return");
        returnBook.setBounds(170,5,90,30);
        returnBook.setFont(new Font("Arial",Font.BOLD,22));
        returnBook.setForeground(Color.black);

        search= new JMenu("Search");
        search.setBounds(260,5,90,30);
        search.setFont(new Font("Arial",Font.BOLD,22));
        search.setForeground(Color.black);

        email= new JMenu("Email");
        email.setBounds(350,5,80,30);
        email.setFont(new Font("Arial",Font.BOLD,22));
        email.setForeground(Color.black);

        logout= new JMenu("Logout");
        logout.setBounds(panel1.getWidth()-100,5,100,30);
        logout.setFont(new Font("Arial",Font.BOLD,22));
        logout.setForeground(Color.black);
        mb.add(book);
        mb.add(issue);
        mb.add(returnBook);
        mb.add(search);
        mb.add(email);
        mb.add(logout);

        label = new JLabel(userType+" Login");
        label.setFont(new Font("Arial",Font.BOLD,40));
        label.setBounds(5,110,panel1.getWidth()-10,panel1.getHeight()-200);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        panel1.add(label);

        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!logout.isEnabled()){
                    return;
                }
                int selection = JOptionPane.showConfirmDialog(frame1,"Do you want to logout","Confirmation",
                        JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
                if(selection == JOptionPane.YES_OPTION){
                    frame1.dispose();
                    Login l = new Login();
                    l.login();
                }
            }
        });
        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String bid = JOptionPane.showInputDialog(null,"Enter the Book Id");
                if(bid == null){
                    return;
                }
                if(!(db.validateBookId(Integer.parseInt(bid),obj))){
                    JOptionPane.showMessageDialog(frame1,"Id not matched","Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                label.setVisible(false);
                setMenu(false);
                searchMethod();

            }
        });
        book.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                label.setVisible(false);
                setMenu(false);
                createTable();
            }
        });
        email.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                label.setVisible(false);
                setMenu(false);
                emailPost();
            }
        });
        issue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                label.setVisible(false);
                setMenu(false);
                issueAndReturn("Issue");
            }
        });
        returnBook.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                label.setVisible(false);
                setMenu(false);
                issueAndReturn("Search");
            }
        });
        frame1.setVisible(true);
    }
    public void searchMethod(){
        JPanel panel2 = new JPanel();
        panel2.setBounds(150,150,500,400);
        panel2.setBackground(Color.green);
        panel1.add(panel2);
        panel2.setLayout(null);
        JLabel label1 = new JLabel("Book Id");
        label1.setBounds(40,30,140,40);
        label1.setFont(new Font("Arial",Font.BOLD,22));
        label1.setForeground(Color.BLACK);
        panel2.add(label1);
        JTextField t1 = new JTextField(obj[0]);
        t1.setBounds(220,30,220,40);
        t1.setFont(new Font("Arial",Font.BOLD,22));
        panel2.add(t1);

        JLabel label2 = new JLabel("Name");
        label2.setBounds(40,90,140,40);
        label2.setFont(new Font("Arial",Font.BOLD,22));
        label2.setForeground(Color.BLACK);
        panel2.add(label2);
        JTextField t2 = new JTextField(obj[1]);
        t2.setBounds(220,90,220,40);
        t2.setFont(new Font("Arial",Font.BOLD,22));
        panel2.add(t2);

        JLabel label3 = new JLabel("Publisher");
        label3.setBounds(40,150,140,40);
        label3.setFont(new Font("Arial",Font.BOLD,22));
        label3.setForeground(Color.BLACK);
        panel2.add(label3);
        JTextField t3 = new JTextField(obj[2]);
        t3.setBounds(220,150,220,40);
        t3.setFont(new Font("Arial",Font.BOLD,22));
        panel2.add(t3);

        JLabel label4 = new JLabel("Price");
        label4.setBounds(40,210,140,40);
        label4.setFont(new Font("Arial",Font.BOLD,22));
        label4.setForeground(Color.BLACK);
        panel2.add(label4);
        JTextField t4 = new JTextField(obj[3]);
        t4.setBounds(220,210,220,40);
        t4.setFont(new Font("Arial",Font.BOLD,22));
        panel2.add(t4);

        JLabel label5 = new JLabel("Publisher Year");
        label5.setBounds(40,270,150,40);
        label5.setFont(new Font("Arial",Font.BOLD,20));
        label5.setForeground(Color.BLACK);
        panel2.add(label5);
        JTextField t5 = new JTextField(obj[4]);
        t5.setBounds(220,270,220,40);
        t5.setFont(new Font("Arial",Font.BOLD,22));
        panel2.add(t5);

        JButton close = new JButton("Close");
        close.setBounds(200,340,100,35);
        close.setFont(new Font("Arial",Font.BOLD,22));
        panel2.add(close);
        close.setBackground(Color.white);
        close.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t1.setEditable(false);
        t2.setEditable(false);
        t3.setEditable(false);
        t4.setEditable(false);
        t5.setEditable(false);

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel2.setVisible(false);
                setMenu(true);
                label.setVisible(true);
                obj = new String[5];
            }
        });
    }
    public void createTable(){
        JPanel panel5 = new JPanel();
        panel5.setLayout(null);
        panel5.setBounds(75,120,650,500);
        panel5.setBackground(Color.green);
        panel1.add(panel5);

        String []columnName = {"Book Id","Name","Publisher","Price","Publish Year"};
        Object [][] obj = {};
        DefaultTableModel model = new DefaultTableModel(obj,columnName);
        model.addRow(columnName);
        db.createBookTable(model);

        JLabel label1 = new JLabel("Current Book of Library");
        label1.setBounds(5,8,panel5.getWidth()-16,30);
        label1.setFont(new Font("Arial",Font.BOLD,20));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        panel5.add(label1);
        JTable table = new JTable(model);
        table.setEnabled(false);
        table.setBounds(10,50,panel5.getWidth()-20,340);
        table.setBackground(Color.black);
        table.setForeground(Color.white);
        panel5.add(table);

        JButton close = new JButton("Close");
        close.setFont(new Font("Arial",Font.BOLD,20));
        close.setBounds(280,395,100,40);
        panel5.add(close);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel5.setVisible(false);
                setMenu(true);
                label.setVisible(true);
            }
        });
    }
    public void emailPost(){
        JPanel panel2 = new JPanel();
        panel2.setBounds(150,150,500,400);
        panel2.setBackground(Color.green);
        panel1.add(panel2);
        panel2.setLayout(null);

        JLabel label1 = new JLabel("Email");
        label1.setBounds(10,8,panel2.getWidth()-20,40);
        label1.setFont(new Font("Arial",Font.BOLD,22));
        label1.setForeground(Color.BLACK);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        panel2.add(label1);

        JLabel label2 = new JLabel("Message");
        label2.setBounds(10,120,120,40);
        label2.setFont(new Font("Arial",Font.BOLD,24));
        label2.setForeground(Color.BLACK);
        panel2.add(label2);

        JTextArea email = new JTextArea();
        email.setBounds(140,60,340,250);
        email.setFont(new Font("Arial",Font.BOLD,22));
        email.setForeground(Color.black);
        email.setLineWrap(true);
        panel2.add(email);

        JButton send = new JButton("Send");
        send.setBounds(140,330,100,35);
        send.setFont(new Font("Arial",Font.BOLD,20));
        panel2.add(send);

        JButton close = new JButton("Close");
        close.setBounds(380,330,100,35);
        close.setFont(new Font("Arial",Font.BOLD,20));
        panel2.add(close);

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emailPost = email.getText();
                if(db.emailPost(emailPost,id)){
                    JOptionPane.showMessageDialog(panel2,"Email send to Admin Successfully","Information",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(panel2,"Email not send to Admin ","Information",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                panel2.setVisible(false);
                email.setText("");
                label.setVisible(true);
                setMenu(true);
            }
        });
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel2.setVisible(false);
                email.setText("");
                label.setVisible(true);
                setMenu(true);
            }
        });
    }
    public void issueAndReturn(String mode){
        JPanel panel2 = new JPanel();
        panel2.setBounds(150,150,500,400);
        panel2.setBackground(Color.green);
        panel1.add(panel2);
        panel2.setLayout(null);
        JLabel label1 = new JLabel("Book Id");
        label1.setBounds(40,30,140,40);
        label1.setFont(new Font("Arial",Font.BOLD,22));
        label1.setForeground(Color.BLACK);
        panel2.add(label1);
        JTextField t1 = new JTextField();
        t1.setBounds(220,30,220,40);
        t1.setFont(new Font("Arial",Font.BOLD,22));
        panel2.add(t1);

        JLabel label2 = new JLabel("studentId");
        label2.setBounds(40,90,140,40);
        label2.setFont(new Font("Arial",Font.BOLD,22));
        label2.setForeground(Color.BLACK);
        panel2.add(label2);
        JTextField t2 = new JTextField();
        t2.setBounds(220,90,220,40);
        t2.setFont(new Font("Arial",Font.BOLD,22));
        panel2.add(t2);

        JLabel label3 = new JLabel();

        label3.setBounds(40,150,140,40);
        label3.setFont(new Font("Arial",Font.BOLD,22));
        label3.setForeground(Color.BLACK);
        panel2.add(label3);
        JTextField t3 = new JTextField();
        t3.setBounds(220,150,220,40);
        t3.setFont(new Font("Arial",Font.BOLD,22));
        panel2.add(t3);
        dm = getDate();
        t3.setText(dm.year+"-"+(dm.month+1)+"-"+dm.date);
        t3.setEditable(false);
        JLabel label4 = new JLabel("Due Date");
        label4.setBounds(40,210,140,40);
        label4.setFont(new Font("Arial",Font.BOLD,22));
        label4.setForeground(Color.BLACK);
        panel2.add(label4);
        JTextField t4 = new JTextField();
        t4.setBounds(220,210,220,40);
        t4.setFont(new Font("Arial",Font.BOLD,22));
        panel2.add(t4);
        t4.setEditable(false);
        JLabel label5 = new JLabel("Late Fine");
        label5.setBounds(40,270,150,40);
        label5.setFont(new Font("Arial",Font.BOLD,20));
        label5.setForeground(Color.BLACK);
        panel2.add(label5);
        JTextField t5 = new JTextField();
        t5.setBounds(220,270,220,40);
        t5.setFont(new Font("Arial",Font.BOLD,22));
        panel2.add(t5);
        t5.setEditable(false);

        if(mode.equals("Issue")){
            label3.setText("Issue Date");
            t5.setText("7 Rs one day");
            switch (dm.month+1){
                case 1: case 3: case 5: case 7: case 8: case 10: case 12:{
                    if((dm.date+7)>31){
                        t4.setText(dm.year+"-"+(dm.month+1+1)+"-"+(dm.date+7-31));
                        break;
                    }
                }
                case 4: case 6: case 9: case 11:{
                    if((dm.date+7)>30){
                        t4.setText(dm.year+"-"+(dm.month+1+1)+"-"+(dm.date+7-30));
                        break;
                    }
                }
                case 2:
                {
                    if((dm.year%4==0 && dm.year%100!=0)||dm.year%400==0){
                        if((dm.date+7)>29){
                            t4.setText(dm.year+"-"+(dm.month+1+1)+"-"+(dm.date+7-29));
                            break;
                        }
                    }
                    else{
                        if((dm.date+7)>28){
                            t4.setText(dm.year+"-"+(dm.month+1+1)+"-"+(dm.date+7-28));
                            break;
                        }
                    }
                }
                default:
                    t4.setText(dm.year+"-"+(dm.month+1)+"-"+(dm.date+7));
            }
        }
        if(mode.equals("Search")){
            label3.setText("Return Date");
        }

        JButton btn = new JButton(mode);
        btn.setBounds(220,320,110,35);
        btn.setFont(new Font("Arial",Font.BOLD,22));
        panel2.add(btn);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBackground(Color.white);

        JButton close = new JButton("Close");
        close.setBounds(340,320,100,35);
        close.setFont(new Font("Arial",Font.BOLD,22));
        panel2.add(close);
        close.setBackground(Color.white);
        close.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obj[0] = t1.getText();
                obj[1] = t2.getText();
                obj[2] = t3.getText();
                obj[3] = t4.getText();
                obj[4] = t5.getText();
                if(btn.getText().equalsIgnoreCase("issue")){
                    if(!v.validateLocalBook(obj[0],obj[1])){
                        return;
                    }
                   if(db.bookIssue(obj)){
                        JOptionPane.showMessageDialog(panel2,"Book Issue","Information",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                   else{
                       return;
                   }
                }
                else if(btn.getText().equalsIgnoreCase("search")){
                    Database.getDueAndLate gdal = db.validateBookAndStudentId(obj[0],obj[1]);
                    if(gdal.dueDate.isEmpty()) {
                        JOptionPane.showMessageDialog(panel2, "Book Id or Student Id not matched",
                                "Warning",JOptionPane.WARNING_MESSAGE);
                    }
                    else{
                        t4.setText(gdal.dueDate);
                        t5.setText(gdal.lFine+"");
                        btn.setText("Return");
                    }
                    return;
                }
                else if(btn.getText().equalsIgnoreCase("return")){
                    if(db.bookReturn(obj)){
                        JOptionPane.showMessageDialog(panel2,"Book Return","Information",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(panel2,"Book is not return","Warning",
                                JOptionPane.WARNING_MESSAGE);
                    }

                }
                panel2.setVisible(false);
                setMenu(true);
                label.setVisible(true);
                obj = new String[5];
            }
        });
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel2.setVisible(false);
                setMenu(true);
                label.setVisible(true);
                obj = new String[5];

            }
        });
    }
    public void setMenu(Boolean b){
        book.setEnabled(b);
        issue.setEnabled(b);
        returnBook.setEnabled(b);
        search.setEnabled(b);
        email.setEnabled(b);
        logout.setEnabled(b);
    }
    class DateMonth{
        int date;
        int month;
        int year;
        DateMonth(int date,int month,int year){
            this.date = date;
            this.month = month;
            this.year = year;
        }
    }
    public DateMonth getDate(){
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DATE);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        return new DateMonth(day,month,year);
    }

}
