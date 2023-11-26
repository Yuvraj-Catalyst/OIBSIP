 package OnlineExamination;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

 public class Question

{
    boolean timeBoolean = true;
    JPanel panel2;
    int answer[] = new int[25];
    String examSubject = "";
    String user = "";
    JLabel time,labelTime;
    String timeString="00:00:00";
    String timeLabel = "";
    int minute = 0;
    int second = 0;
    Timer t = new Timer();

    Question(String examSubject,String user){
        this.examSubject = examSubject;
        this.user = user;
    }
    String str = new String("");
    public void question(int questionNumber){
        JFrame frame1 = new JFrame();
        frame1.setBounds(500,150,500,400);
        frame1.setLayout(null);
        frame1.setDefaultCloseOperation(frame1.EXIT_ON_CLOSE);
        frame1.setResizable(false);
        frame1.setUndecorated(true);


        JPanel panel = new JPanel();
        panel.setBounds(0,0,frame1.getWidth(),frame1.getHeight());
        panel.setBorder(BorderFactory.createLineBorder(Color.red,4));
        frame1.add(panel);
        panel.setLayout(null);
        JLabel label1 = new JLabel(examSubject+" Exam");
        label1.setBounds(0,0,500,50);
        label1.setFont(new Font("Arial",Font.ITALIC,40));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label1);
        time = new JLabel(timeString);
        time.setBounds(380,50,100,30);
        time.setFont(new Font("Arial",Font.ITALIC,22));
        time.setForeground(Color.green);
        panel.add(time);
        if(timeBoolean){
            timeBoolean = false;
            t.schedule(new TimerTask() {
                public void run(){
                    time.setText(timeString);
                    second += 1;
                    printTime();
                    if(minute == 10 && second == 0){
                        JOptionPane.showMessageDialog(null,"Time Over","Information",
                                JOptionPane.INFORMATION_MESSAGE);
                        t.cancel();
                        frame1.dispose();
                        submit();
                    }
                }
            },1,1000);
        }

//        labelTime = new JLabel(timeLabel);
//        labelTime.setBounds(100,50,200,30);
//        labelTime.setFont(new Font("Arial",Font.ITALIC,22));
//        labelTime.setForeground(Color.black);
//        panel.add(labelTime);
        JPanel panel1 = new JPanel();
        panel1.setBounds(5,70,panel.getWidth()-10,panel.getHeight()-80);
        panel.add(panel1);
        panel1.setLayout(null);
        JTextArea questionArea = new JTextArea();
        questionArea.setBounds(15,20,panel1.getWidth()-30,90);
        questionArea.setFont(new Font("Arial",Font.BOLD,21));
        questionArea.setForeground(Color.black);
        questionArea.setLineWrap(true);
        questionArea.setText((questionNumber+1)+")"+getLineString(questionNumber));
        questionArea.setEditable(false);
        panel1.add(questionArea);

        str = getLineStringOption((questionNumber*4)+0);
        JRadioButton option1 = new JRadioButton(str);
        option1.setBounds(15,110,400,40);
        option1.setFont(new Font("Arial",Font.BOLD,20));
        panel1.add(option1);

        str = getLineStringOption((questionNumber*4)+1);
        JRadioButton option2 = new JRadioButton(str);
        option2.setBounds(15,150,400,40);
        option2.setFont(new Font("Arial",Font.BOLD,20));
        panel1.add(option2);

        str = getLineStringOption((questionNumber*4)+2);
        JRadioButton option3 = new JRadioButton(str);
        option3.setBounds(15,190,400,40);
        option3.setFont(new Font("Arial",Font.BOLD,20));
        panel1.add(option3);
        if(str.equals("null")){
            option3.setVisible(false);
        }

        str = getLineStringOption((questionNumber*4)+3);
        JRadioButton option4 = new JRadioButton(str);
        option4.setBounds(15,230,400,40);
        option4.setFont(new Font("Arial",Font.BOLD,20));
        panel1.add(option4);
        if(str.equals("null")){
            option4.setVisible(false);
        }
        if(answer[questionNumber]==1){
            option1.setSelected(true);
        }
        else if(answer[questionNumber]==2){
            option2.setSelected(true);
        }
        else if(answer[questionNumber] == 3){
            option3.setSelected(true);
        }
        else if(answer[questionNumber]==4){
            option4.setSelected(true);
        }

        ButtonGroup btngroup = new ButtonGroup();
        btngroup.add(option1);
        btngroup.add(option2);
        btngroup.add(option3);
        btngroup.add(option4);

        JButton back = new JButton("Back");
        back.setBounds(30,273,100,40);
        back.setFont(new Font("Arial",Font.BOLD,20));
        panel1.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(questionNumber>0){
                    frame1.dispose();
                    question(questionNumber-1);
                }

            }
        });

        JButton next = new JButton("Next");
        next.setBounds(360,273,100,40);
        next.setFont(new Font("Arial",Font.BOLD,20));
        panel1.add(next);
        if(questionNumber == 24){
            next.setText("Submit");
        }
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(option1.isSelected()){
                    answer[questionNumber] = 1;
                }
                else if(option2.isSelected()){
                    answer[questionNumber] = 2;
                }
                else if(option3.isSelected()){
                    answer[questionNumber] = 3;
                }
                else if(option4.isSelected()){
                    answer[questionNumber] = 4;
                }
                if(questionNumber<24){
                    frame1.dispose();
                    question(questionNumber+1);
                }
                if(questionNumber == 24){
                    frame1.dispose();
                    t.cancel();
                    submit();
                }
            }
        });
        frame1.setVisible(true);
    }
    public String getLineString(int lineNumber){
        String line_n = new String("");
        try{
            line_n =  Files.readAllLines(Paths.get("C:\\Users\\TARKESHWAR PRASAD\\Desktop\\program\\" +
                    "Tasks\\Oasis Infobyte\\Task 4\\src\\" +
                    "OnlineExamination\\"+examSubject+"Questions")).get(lineNumber);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return line_n;
    }
    public String getLineStringOption(int lineNumber){
        String line_n = new String("");
        try{
            line_n =  Files.readAllLines(Paths.get("C:\\Users\\TARKESHWAR PRASAD\\Desktop\\program\\Tasks\\Oasis Infobyte\\Task 4\\src" +
                    "\\OnlineExamination\\"+examSubject+"QuestionAnswer")).get(lineNumber);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return line_n;
    }

    public  void printTime(){
        if(minute < 9 && second==60){
            second = 0;
            timeString = "00:0"+(++minute)+":0"+(second);
//            System.out.println("00:0"+(++minute)+":0"+(second));
        }
        else if(minute>=9 && second == 60){
            second = 0;
            timeString = "00:"+(++minute)+":0"+(second);
//            System.out.println("00:"+(++minute)+":0"+(second));
        }

        else if(second<10){
            timeString = "00:0"+minute+":0"+(second);
//            System.out.println("00:0"+minute+":0"+(second));
        }
        else if(second>=10){
            timeString = "00:0"+minute+":"+(second);
//            System.out.println("00:0"+minute+":"+(second));
        }

    }
    public void submit(){
        int right= getResult();
        JFrame frame1 = new JFrame();
        frame1.setBounds(500,150,500,400);
        frame1.setLayout(null);
        frame1.setDefaultCloseOperation(frame1.EXIT_ON_CLOSE);
        frame1.setResizable(false);
        frame1.setUndecorated(true);
        JPanel panel = new JPanel();
        panel.setBounds(0,0,frame1.getWidth(),frame1.getHeight());
        panel.setBorder(BorderFactory.createLineBorder(Color.red,4));
        frame1.add(panel);
        panel.setLayout(null);
        JLabel label1 = new JLabel(examSubject+" Exam ");
        label1.setBounds(0,0,500,50);
        label1.setFont(new Font("Arial",Font.ITALIC,40));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label1);

        JPanel panel1 = new JPanel();
        panel1.setBounds(5,70,panel.getWidth()-10,panel.getHeight()-80);
        panel.add(panel1);
        panel1.setLayout(null);
        JLabel label2 = new JLabel("Result:");
        label2.setFont(new Font("Arial",Font.BOLD,35));
        label2.setBounds(0,10,panel1.getWidth(),50);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        panel1.add(label2);

        JLabel label3 = new JLabel(right+" out of 25");
        label3.setFont(new Font("Arial",Font.BOLD,25));
        label3.setBounds(0,60,panel1.getWidth(),50);
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        panel1.add(label3);

        int percentage = right*4;
        JLabel label4 = new JLabel(right+"%");
        label4.setFont(new Font("Arial",Font.BOLD,25));
        label4.setBounds(0,110,panel1.getWidth(),40);
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        panel1.add(label4);

        JLabel label5 = new JLabel();
        label5.setFont(new Font("Arial",Font.BOLD,25));
        label5.setBounds(0,150,panel1.getWidth(),50);
        label5.setHorizontalAlignment(SwingConstants.CENTER);
        panel1.add(label5);
        if(right<=15){
            label5.setText("Well,you have a lot of studying to do!");
        }
        else if(right>=20){
            label5.setText("Well Studying");
        }
        else if(right>20 && right<=24){
            label5.setText("Good Knowledge in "+examSubject);
        }
        else if(right == 25){
            label5.setText("Impressing Knowledge in "+examSubject);
        }
        JLabel label6 = new JLabel("Time Spent");
        label6.setFont(new Font("Arial",Font.BOLD,20));
        label6.setBounds(0,200,panel1.getWidth(),40);
        label6.setHorizontalAlignment(SwingConstants.CENTER);
        panel1.add(label6);

        JLabel label7 = new JLabel(timeString);
        label7.setFont(new Font("Arial",Font.BOLD,20));
        label7.setBounds(0,240,panel1.getWidth(),40);
        label7.setHorizontalAlignment(SwingConstants.CENTER);
        panel1.add(label7);

        JButton home = new JButton("Home");
        home.setBounds(30,273,120,40);
        home.setFont(new Font("Arial",Font.BOLD,20));
        panel1.add(home);
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1.dispose();
                MainPage mp = new MainPage();
                mp.user = user;
                mp.mainPage();
            }
        });

        JButton logout = new JButton("Logout");
        logout.setBounds(360,273,120,50);
        logout.setFont(new Font("Arial",Font.BOLD,20));

        panel1.add(logout);
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selection = JOptionPane.showConfirmDialog(null,"Do you want to Logged out the Session",
                        "Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
                if(selection == JOptionPane.YES_OPTION){
                    JOptionPane.showMessageDialog(null,"Session is Closing....","Information",JOptionPane.INFORMATION_MESSAGE);
                    frame1.dispose();
                    MainPage mp = new MainPage();
                    mp.user = "Guest";
                    mp.mainPage();
                }
            }
        });
        frame1.setVisible(true);
    }
    public int getResult(){
        try {
            File answerKey = new File("C:\\Users\\TARKESHWAR PRASAD\\Desktop\\program\\Tasks\\Oasis Infobyte\\Task 4\\" +
                    "src\\OnlineExamination\\" + examSubject + "AnswerKey");
            Scanner sc = new Scanner(answerKey);
            int right = 0;
            for (int i = 0; i < answer.length; i++) {
                String answers = sc.nextLine();
                if ( Integer.parseInt(answers) == answer[i]) {
                    right = right + 1;
                }
            }
            return right;
        }
        catch(Exception e){
            System.out.println("Error is "+e);
        }
        return Integer.MIN_VALUE;
    }
}
