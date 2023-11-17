package DigitalLibraryManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class AdminMainPage {

    JFrame frame1;
    String obj[] = new String[5];
    Database db = new Database();
    Validate v = new Validate();
    JPanel panel1,panel2;
    JButton btn,close,btn1,close1;
    JLabel label;
    JTextField t1;
    JMenu book,student,statistics,report,email,logout;
    public void adminMainPage(String userType,String user){
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
        JMenuItem addBook = new JMenuItem("Add Book");
        book.add(addBook);
        JMenuItem updateBook = new JMenuItem("Update Book");
        book.add(updateBook);
        JMenuItem removeBook = new JMenuItem("Remove Book ");
        book.add(removeBook);
        JMenuItem displayAllBook = new JMenuItem("Display All");
        book.add(displayAllBook);

        student = new JMenu("Student");
        student.setBounds(90,5,100,30);
        student.setFont(new Font("Arial",Font.BOLD,22));
        student.setForeground(Color.black);
        JMenuItem addStudent = new JMenuItem("Add Student");
        student.add(addStudent);
        JMenuItem updateStudent = new JMenuItem("Update Student");
        student.add(updateStudent);
        JMenuItem removeStudent = new JMenuItem("Remove Student");
        student.add(removeStudent);
        JMenuItem displayAllStudent = new JMenuItem("Display All");
        student.add(displayAllStudent);

        statistics = new JMenu("Statistics");
        statistics.setBounds(200,5,120,30);
        statistics.setFont(new Font("Arial",Font.BOLD,22));
        statistics.setForeground(Color.black);
        JMenuItem issue = new JMenuItem("Issue Books");
        statistics.add(issue);
        JMenuItem returnBook = new JMenuItem("Current Library Book");
        statistics.add(returnBook);

        report = new JMenu("Report");
        report.setBounds(320,5,80,30);
        report.setFont(new Font("Arial",Font.BOLD,22));
        report.setForeground(Color.black);

        email = new JMenu("Email");
        email.setBounds(415,5,80,30);
        email.setFont(new Font("Arial",Font.BOLD,22));
        email.setForeground(Color.black);

        logout = new JMenu("Logout");
        logout.setBounds(panel1.getWidth()-100,5,100,30);
        logout.setFont(new Font("Arial",Font.BOLD,22));
        logout.setForeground(Color.black);
        mb.add(book);
        mb.add(student);
        mb.add(statistics);
        mb.add(report);
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

        addBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setVisible(false);
                setMenu(false);
                bookPanel();
                btn.setText("Save");
            }
        });
        updateBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                bookPanel();
                t1.setEditable(false);
                btn.setText("Update");
            }
        });
        removeBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                bookPanel();
                t1.setEditable(false);
                btn.setText("Remove");

            }
        });
        displayAllBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setVisible(false);
                setMenu(false);
                createTable("All Book");
            }
        });
        addStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setVisible(false);
                setMenu(false);
                studentPanel();
                btn1.setText("Save");
            }
        });
        updateStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sid = JOptionPane.showInputDialog(null,"Enter the Student Id");
                if(sid == null){
                    return;
                }
                if(!(db.validateStudentId(Integer.parseInt(sid),obj))){
                    JOptionPane.showMessageDialog(frame1,"Id not matched","Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                label.setVisible(false);
                setMenu(false);
                studentPanel();
                t1.setEditable(false);
                btn1.setText("Update");
            }
        });
        removeStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sid = JOptionPane.showInputDialog(null,"Enter the student Id");
                if(sid == null){
                    return;
                }
                if(!(db.validateStudentId(Integer.parseInt(sid),obj))){
                    JOptionPane.showMessageDialog(frame1,"Id not matched","Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                label.setVisible(false);
                setMenu(false);
                studentPanel();
                t1.setEditable(false);
                btn1.setText("Remove");
            }
        });
        displayAllStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setVisible(false);
                setMenu(false);
                createTable("All Student");
            }
        });
        issue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setVisible(false);
                setMenu(false);
                createTable("Issue Book");
            }
        });
        returnBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setVisible(false);
                setMenu(false);
                createTable("Current Book in Library");
            }
        });
        email.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if((report.isEnabled())){
                    label.setVisible(false);
                    setMenu(false);
                    createTable("All Emails");
                }
            }
        });
        report.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if((report.isEnabled())){
                    label.setVisible(false);
                    setMenu(false);
                    createTable("Report of Library Book");
                }
            }
        });
        frame1.setVisible(true);

    }
    public void bookPanel(){
        panel2 = new JPanel();
        panel2.setBounds(150,150,500,400);
        panel2.setBackground(Color.green);
        panel1.add(panel2);
        panel2.setLayout(null);
        JLabel label1 = new JLabel("Book Id");
        label1.setBounds(40,30,140,40);
        label1.setFont(new Font("Arial",Font.BOLD,22));
        label1.setForeground(Color.BLACK);
        panel2.add(label1);
        t1 = new JTextField(obj[0]);
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

        btn = new JButton();
        btn.setBounds(220,320,110,35);
        btn.setFont(new Font("Arial",Font.BOLD,18));
        panel2.add(btn);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBackground(Color.white);

        close = new JButton("Close");
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

                if(btn.getText().equalsIgnoreCase("save")){
                    if(!(v.validateBook(obj))){
                        return;
                    }
                    if(db.addBook(obj)) {
                        JOptionPane.showMessageDialog(frame1, "New Book added",
                                "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(frame1,"Book is not added",
                                "Information",JOptionPane.INFORMATION_MESSAGE);
                    }

                }
                if(btn.getText().equalsIgnoreCase("update")){
                    if(!(v.validateBook(obj))){
                        return;
                    }
                    if(db.updateBook(obj)){
                        JOptionPane.showMessageDialog(frame1,"Update Book Record",
                                "Information",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(frame1,"Book Record is not Updated",
                                "Information",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                if(btn.getText().equalsIgnoreCase("remove")){
                    if(db.removeBook(Integer.parseInt(obj[0]))){
                        JOptionPane.showMessageDialog(frame1,"Remove Book Record",
                                "Information",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(frame1,"Record of this book is not removed",
                                "Information",JOptionPane.INFORMATION_MESSAGE);
                    }

                }
                panel2.setVisible(false);
                setMenu(true);
                btn.setText("");
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
    public void studentPanel(){
        JPanel panel3 = new JPanel();
        panel3.setBounds(150,150,500,400);
        panel3.setBackground(Color.green);
        panel1.add(panel3);
        panel3.setLayout(null);
        JLabel label1 = new JLabel("Student Id");
        label1.setBounds(40,30,160,40);
        label1.setFont(new Font("Arial",Font.BOLD,22));
        label1.setForeground(Color.BLACK);
        panel3.add(label1);
        t1 = new JTextField(obj[0]);
        t1.setBounds(220,30,220,40);
        t1.setFont(new Font("Arial",Font.BOLD,22));
        panel3.add(t1);

        JLabel label2 = new JLabel("Name");
        label2.setBounds(40,90,160,40);
        label2.setFont(new Font("Arial",Font.BOLD,22));
        label2.setForeground(Color.BLACK);
        panel3.add(label2);
        JTextField t2 = new JTextField(obj[1]);
        t2.setBounds(220,90,220,40);
        t2.setFont(new Font("Arial",Font.BOLD,18));
        panel3.add(t2);

        JLabel label3 = new JLabel("Father Name");
        label3.setBounds(40,150,160,40);
        label3.setFont(new Font("Arial",Font.BOLD,22));
        label3.setForeground(Color.BLACK);
        panel3.add(label3);
        JTextField t3 = new JTextField(obj[2]);
        t3.setBounds(220,150,220,40);
        t3.setFont(new Font("Arial",Font.BOLD,18));
        panel3.add(t3);

        JLabel label4 = new JLabel("Course Name");
        label4.setBounds(40,210,160,40);
        label4.setFont(new Font("Arial",Font.BOLD,22));
        label4.setForeground(Color.BLACK);
        panel3.add(label4);
        String course[] = {"BCA", "MCA", "B-Tech", "M-Tech", "B.A", "M.A", "Bsc-It"};
//        JTextField t4 = new JTextField(obj[3]);
        JComboBox t4 = new JComboBox<>(course);
        t4.setBounds(220,210,220,40);
        t4.setFont(new Font("Arial",Font.BOLD,22));
        t4.setSelectedItem(obj[3]);
        panel3.add(t4);

        JLabel label5 = new JLabel("Branch Name");
        label5.setBounds(40,270,160,40);
        label5.setFont(new Font("Arial",Font.BOLD,20));
        label5.setForeground(Color.BLACK);
        panel3.add(label5);

        String branch[] = {"IT", "Management", "Sport", "Digital Club"};
//        JTextField t5 = new JTextField(obj[4]);
        JComboBox t5 = new JComboBox<String>(branch);
        t5.setBounds(220,270,220,40);
        t5.setFont(new Font("Arial",Font.BOLD,22));
        t5.setSelectedItem(obj[4]);
        panel3.add(t5);

        btn1 = new JButton();
        btn1.setBounds(220,320,110,35);
        btn1.setFont(new Font("Arial",Font.BOLD,18));
        panel3.add(btn1);
        btn1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn1.setBackground(Color.white);

        close1 = new JButton("Close");
        close1.setBounds(340,320,100,35);
        close1.setFont(new Font("Arial",Font.BOLD,22));
        panel3.add(close1);
        close1.setBackground(Color.white);
        close1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        if(t4.getSelectedItem()==null){
            t4.setSelectedIndex(0);
        }
        if(t5.getSelectedItem()==null){
            t5.setSelectedIndex(0);
        }
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obj[0] = t1.getText();
                obj[1] = t2.getText();
                obj[2] = t3.getText();
                obj[3] = (String)t4.getSelectedItem();
                obj[4] = (String) t5.getSelectedItem();

                if(btn1.getText().equalsIgnoreCase("save")){
                    if(!(v.validateStudent(obj))){
                        return;
                    }
                    if(db.addStudent(obj)) {
                        JOptionPane.showMessageDialog(frame1, "New student added",
                                "Information", JOptionPane.INFORMATION_MESSAGE);
                        panel3.setVisible(false);
                        createPassword(obj[0],obj[2]);
                        return;
                    }
                    else{
                        JOptionPane.showMessageDialog(frame1,"Student is not added",
                                "Information",JOptionPane.INFORMATION_MESSAGE);
                    }

                }
                if(btn1.getText().equalsIgnoreCase("update")){
                    if(!(v.validateStudent(obj))){
                        return;
                    }
                    if(db.updateStudent(obj)){
                        JOptionPane.showMessageDialog(frame1,"Update Student Record",
                                "Information",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(frame1,"Student Record is not Updated",
                                "Information",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                if(btn1.getText().equalsIgnoreCase("remove")){
                    String username = obj[2]+obj[0];
                    if(db.removeStudent(Integer.parseInt(obj[0]),username)){
                        JOptionPane.showMessageDialog(frame1,"Remove Student Record",
                                "Information",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(frame1,"Record of this Student is not removed",
                                "Information",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                obj = new String[5];
                panel3.setVisible(false);
                setMenu(true);
                label.setVisible(true);
            }
        });
        close1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel3.setVisible(false);
                setMenu(true);
                label.setVisible(true);
                obj = new String[5];
            }
        });
    }

    public int getRandomNumber(){
        Random random = new Random();
        int min = 1;
        int max = 30;
        return random.nextInt(max-min+1)+min;
    }
    public void createPassword(String id,String fname){
        StringBuilder username = new StringBuilder("");
        StringBuilder password = new StringBuilder("");
        fname = fname.trim();
        for(int i =0;i<fname.length();i++){
            int num = getRandomNumber();
            if((fname.charAt(i)-num)>25){
                password.append((char)(fname.charAt(i)-num));
            }
            else if((fname.charAt(i)+num)<127){
                password.append((char)(fname.charAt(i)+num));
            }
        }
        username.append(fname+id);
        JPanel panel4 = new JPanel();
        panel4.setBounds(150,150,500,400);
        panel4.setBackground(Color.green);
        panel1.add(panel4);
        panel4.setLayout(null);
        JLabel label6 = new JLabel("Your Username and Password");
        label6.setBounds(10,50,panel4.getWidth()-20,50);
        label6.setFont(new Font("Arial",Font.BOLD,25));
        label6.setHorizontalAlignment(SwingConstants.CENTER);
        panel4.add(label6);

        JLabel label1 = new JLabel("Username = ");
        label1.setBounds(50,130,150,50);
        label1.setFont(new Font("Arial",Font.BOLD,22));
        panel4.add(label1);
        JLabel label2 = new JLabel(username.toString());
        label2.setBounds(220,130,280,50);
        label2.setFont(new Font("Arial",Font.BOLD,22));
        panel4.add(label2);
        JLabel label3 = new JLabel("Password = ");
        label3.setBounds(50,180,150,50);
        label3.setFont(new Font("Arial",Font.BOLD,22));
        panel4.add(label3);
        JLabel label4 = new JLabel(password.toString());
        label4.setBounds(220,180,280,50);
        label4.setFont(new Font("Arial",Font.BOLD,22));
        panel4.add(label4);
        JLabel label5 = new JLabel("Remember Your password Forget password is not possible");
        label5.setFont(new Font("Arial",Font.BOLD,16));
        label5.setBounds(10,260,panel4.getWidth()-20,40);
        label5.setHorizontalAlignment(SwingConstants.CENTER);
        panel4.add(label5);
        JButton close = new JButton("Close");
        close.setFont(new Font("Arial",Font.BOLD,20));
        close.setBounds(350,320,100,40);
        panel4.add(close);

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.setStudentCredentials(username.toString(),password.toString());
                panel4.setVisible(false);
                setMenu(true);
                label.setVisible(true);
            }
        });
    }
    public void createTable(String mode){
        JPanel panel5 = new JPanel();
        panel5.setLayout(null);
        panel5.setBounds(75,120,650,500);
        panel5.setBackground(Color.green);
        panel1.add(panel5);
        DefaultTableModel model = null;
        if(mode.equals("All Student")){
            String []columnName = {"Student Id","Name","Father Name","Course Name","Branch Name"};
            Object [][] obj = {};
            model = new DefaultTableModel(obj,columnName);
            model.addRow(columnName);
            db.createStudentTable(model);
        }
        else if(mode.equals("All Book")){
            String []columnName = {"Book Id","Name","Publisher","Price","Publish Year"};
            Object [][] obj = {};
            model = new DefaultTableModel(obj,columnName);
            model.addRow(columnName);
            db.createBookTable(model);
        }
        else if(mode.equals("Issue Book")){
            String []columnName = {"Book Id","Name","Publisher","Price","Publish Year"};
            Object [][] obj = {};
            model = new DefaultTableModel(obj,columnName);
            model.addRow(columnName);
            db.createStatisticsTable(model,1);
        }
        else if(mode.equals("Current Book in Library")){
            String []columnName = {"Book Id","Name","Publisher","Price","Publish Year"};
            Object [][] obj = {};
            model = new DefaultTableModel(obj,columnName);
            model.addRow(columnName);
            db.createStatisticsTable(model,0);
        }
        else if(mode.equals("Report of Library Book")){
            String [] columnName = {"Book Id","Student Id","Issue Date","Return Date","Due Date","Late Fine","Total Day"};
            Object [][] obj = {};
            model = new DefaultTableModel(obj,columnName);
            model.addRow(columnName);
            db.createLibraryBookHistory(model);
        }
        else if(mode.equals("All Emails")){
            String [] columnName = {"Email","Student Id","Post Time"};
            Object [][] obj = {};
            model = new DefaultTableModel(obj,columnName);
            model.addRow(columnName);
            db.allEmail(model);
        }

        JLabel label1 = new JLabel(mode+" Record");
        label1.setBounds(5,8,panel5.getWidth()-16,30);
        label1.setFont(new Font("Arial",Font.BOLD,20));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        panel5.add(label1);
        JTable table = new JTable(model);
//        if(mode.equals("All Emails"))
//        table.getColumn(1).setWidth(35);
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
    public void setMenu(Boolean b){
        book.setEnabled(b);
        student.setEnabled(b);
        statistics.setEnabled(b);
        report.setEnabled(b);
        email.setEnabled(b);
        logout.setEnabled(b);
    }

}
