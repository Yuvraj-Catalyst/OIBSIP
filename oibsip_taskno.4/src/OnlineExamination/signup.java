package OnlineExamination;
// import Train.Reservation.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class signup{
    JFrame frame1;
    JLabel label1,label2,imageLabel,image1Label,label3,label4;
    JTextField t1;
    JPasswordField pass;
    ImageIcon image,image2;
    JButton signUp,close;
    int storeId = 0;
    boolean frame3Status = false;
    String imagePath = "";
    boolean validateId = false;
    Database db = new Database();
    String str="";
    public void signupPage(){
        frame1 = new JFrame("Signup Page");
        frame1.setSize(500,300);
        frame1.setLocation(400,200);
        Container con = frame1.getContentPane();
        con.setBackground(Color.black);
        frame1.setLayout(null);
        frame1.setDefaultCloseOperation(frame1.EXIT_ON_CLOSE);
        label3 = new JLabel("Log in");
        label3.setBounds(410,10,80,40);
        label3.setFont(new Font("Arial",Font.BOLD,18));
        frame1.add(label3);
        label3.setForeground(Color.green);

        label4 = new JLabel("Already have an account?");
        label4.setBounds(205,10,240,40);
        label4.setFont(new Font("Arial",Font.BOLD,16));
        frame1.add(label4);
        label4.setForeground(Color.white);

        label1 = new JLabel("USERNAME");
        label1.setBounds(50,50,180,50);
        label1.setFont(new Font("Arial",Font.BOLD,25));
        frame1.add(label1);
        label1 = new JLabel("USERNAME");
        label1.setBounds(50,50,180,50);
        label1.setFont(new Font("Arial",Font.BOLD,25));
        frame1.add(label1);
        t1 = new JTextField();
        t1.setBounds(250,55,150,40);
        t1.setFont(new Font("Arial",Font.BOLD,20));
        frame1.add(t1);
        label2 = new JLabel("PASSWORD");
        label2.setBounds(50,120,180,50);
        label2.setFont(new Font("Arial",Font.BOLD,25));
        frame1.add(label2);
        pass = new JPasswordField();
        pass.setBounds(250,125,100,40);
        pass.setFont(new Font("Arial",Font.BOLD,16));
        pass.setEchoChar('*');
        image = new ImageIcon("C:\\Users\\TARKESHWAR PRASAD\\Desktop\\program\\Task 5\\images1.jpg");
        image2 = new ImageIcon("C:\\Users\\TARKESHWAR PRASAD\\Desktop\\program\\Task 5\\images.jpg");
        imageLabel = new JLabel(image);
        imageLabel.setBounds(350,125,image.getIconWidth(),image.getIconHeight());
        frame1.add(imageLabel);
        image1Label = new JLabel(image2);
        image1Label.setBounds(350,125,image.getIconWidth(),image.getIconHeight());
        frame1.add(image1Label);
        imageLabel.setVisible(false);
        frame1.add(pass);
        image1Label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                image1Label.setVisible(false);
                imageLabel.setVisible(true);
                pass.setEchoChar((char)0);
            }
        });
        imageLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                imageLabel.setVisible(false);
                image1Label.setVisible(true);
                pass.setEchoChar('*');
            }
        });
        signUp = new JButton("SIGN UP");
        signUp.setFont(new Font("Arial",Font.BOLD,20));
        signUp.setBounds(50,200,120,50);
        frame1.add(signUp);
        signUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                str = t1.getText();
                char [] str1 = pass.getPassword();
                if(str.isEmpty() ){
                    JOptionPane.showMessageDialog(frame1,"Enter Username", "Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(str.equalsIgnoreCase("guest") || (!(str.matches("^[a-zA-Z0-9-_&@#]+$")))){
                    JOptionPane.showMessageDialog(frame1,"Invalid Username", "Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(str1.length == 0){
                    JOptionPane.showMessageDialog(frame1,"Enter Password", "Error",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    if(db.validateSignUp(str, str1)) {
                        JOptionPane.showMessageDialog(frame1, "SignUp Successful", "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                        Login l = new Login();
                        frame1.dispose();
                        l.loginPage();
                    }
                }
            }
        });
        close = new JButton("CLOSE");
        close.setFont(new Font("Arial",Font.BOLD,20));
        close.setBounds(330,200,120,50);
        frame1.add(close);
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                int selection = JOptionPane.showConfirmDialog(frame1,"Are you sure to close the application","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                if(selection == JOptionPane.YES_OPTION){
                    frame1.dispose();
                }
            }
        });
        label3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Login l = new Login();
                frame1.dispose();
                l.loginPage();
            }
        });
        frame1.setVisible(true);
    }
    public static void main(String args[]){
        signup su = new signup();
        su.signupPage();
    }
}
