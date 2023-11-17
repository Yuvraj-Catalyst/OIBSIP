//Program for Guess The Number Game in Gui
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.*;
public class NumberGuessGame{
    public static void main(String [] args){
        GuessGame gg = new GuessGame();
        gg.guessTheNumber();
    }
}
class GuessGame{
    private int round = 0;
    private int totalAttempt = 5;
    private int winRound = 0;
    private void helperGuessTheNumber(int attempt){
        round++;
        JFrame frame1 = new JFrame("Guess The Number Game");
        frame1.setUndecorated(true);
        frame1.setResizable(false);
        frame1.setLayout(null);
        frame1.setDefaultCloseOperation(frame1.EXIT_ON_CLOSE);
        frame1.setBounds(500,200,450,300);
        JPanel panel = new JPanel();
        panel.setLayout(null);        
        panel.setBounds(0,0,450,300);
        frame1.add(panel);
        panel.setBackground(Color.black);
        panel.setBorder(BorderFactory.createLineBorder(Color.red));
        JLabel label1 = new JLabel("Round "+String.valueOf(round));
        label1.setBounds(0,60,450,70);
        label1.setFont(new Font("Arial",Font.ITALIC,80));
        // label1.setLocation(0,0);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setVisible(true);
        label1.setForeground(Color.white);
        panel.add(label1);
        JLabel label2 = new JLabel("Total Attempt = "+String.valueOf(attempt));
        label2.setBounds(0,135,450,30);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setFont(new Font("Arial",Font.BOLD,25));
        label2.setForeground(Color.white);
        panel.add(label2);
        JButton play = new JButton("Play");
        play.setBounds(180,180,100,40);
        play.setFont(new Font("Arial",Font.BOLD,25));
        panel.add(play);
        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame1.dispose();
               int takenAttempt = gameFile(attempt);
               finalPage(takenAttempt);
            }
        });
        frame1.setVisible(true);
    }
    public int randomNumber(int max){
        Random random = new Random();
        int min = 1;
        return random.nextInt(max-min+1)+min;
    }
    public int gameFile(int attempt){
        int matchNumber = randomNumber(100);
        int i ;
        for(i = attempt-1;i>=0;i--){
            String str = JOptionPane.showInputDialog(null,"Guess The Number");
            if(str == null){
                JOptionPane.showMessageDialog(null,String.valueOf(i)+" Attempt left","Status",JOptionPane.INFORMATION_MESSAGE);
                // continue;
            }
            else if(str.matches("^[0-9]+$")){
                int number = Integer.parseInt(str);
                if(number==matchNumber){
                    JOptionPane.showMessageDialog(null,"Number Matched!!!","Status",JOptionPane.INFORMATION_MESSAGE);
                    winRound++;
                    break;
                }
                else if(number>matchNumber){
                    JOptionPane.showMessageDialog(null,"Guess The Lower Number\n"+i+" Attempt left","Status",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Guess The higher Number\n"+i+" Attempt left","Status",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else{
                    JOptionPane.showMessageDialog(null,i+" Attempt left","Status",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        return attempt-i;
    }
    public void finalPage(int takenChance){
        JFrame frame1 = new JFrame("Result");
        frame1.setUndecorated(true);
        frame1.setResizable(false);
        frame1.setLayout(null);
        frame1.setDefaultCloseOperation(frame1.EXIT_ON_CLOSE);
        frame1.setBounds(500,200,450,300);
        JPanel panel = new JPanel();
        panel.setLayout(null);        
        panel.setBounds(0,0,450,300);
        frame1.add(panel);
        panel.setBorder(BorderFactory.createLineBorder(Color.red));
        panel.setBackground(Color.black);
        JLabel label1 = new JLabel();
        if(takenChance<=totalAttempt){
            label1.setText("You Win");
        }
        else{
            label1.setText("You Lose");
        }
        label1.setBounds(0,80,450,70);
        label1.setFont(new Font("Arial",Font.ITALIC,90));
        // label1.setLocation(0,0);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setVisible(true);
        label1.setForeground(Color.white);
        panel.add(label1);
        JLabel label2 = new JLabel("Score = "+String.valueOf(takenChance));
        label2.setBounds(0,160,450,30);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setFont(new Font("Arial",Font.BOLD,30));
        label2.setForeground(Color.white);
        panel.add(label2);
        JButton close = new JButton("Exit");
        close.setBounds(20,250,100,40);
        close.setFont(new Font("Arial",Font.BOLD,25));
        panel.add(close);
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame1.dispose();
                firstPage();
            }
        });
        JButton next = new JButton("Next");
        next.setBounds(320,250,100,40);
        next.setFont(new Font("Arial",Font.BOLD,25));
        panel.add(next);
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(round ==3){
                    frame1.dispose();
                    finalPage(winRound);
                }
                else{
                    frame1.dispose();
                    helperGuessTheNumber(--totalAttempt);
                }
            }
        });
        if(round==3){  
            label1.setFont(new Font("Arial",Font.ITALIC,65));
            label1.setText("Final Result");
            label2.setBounds(0,150,450,30);        
            label2.setText("Win Round = "+winRound);
            next.setVisible(false);
            close.setBounds(180,190,100,40);
        }
        frame1.setVisible(true);
    }
    public void firstPage(){
        JFrame frame1 = new JFrame("Guess The Number Game");
        frame1.setUndecorated(true);
        frame1.setResizable(false);
        frame1.setLayout(null);
        frame1.setDefaultCloseOperation(frame1.EXIT_ON_CLOSE);
        frame1.setBounds(500,200,450,300);
        JPanel panel = new JPanel();
        panel.setLayout(null);        
        panel.setBounds(0,0,450,300);
        frame1.add(panel);
        panel.setBackground(Color.black);
        panel.setBorder(BorderFactory.createLineBorder(Color.red));
        JLabel label1 = new JLabel("Guess Number Game");
        label1.setBounds(0,100,450,70);
        label1.setFont(new Font("Arial",Font.ITALIC,40));
        // label1.setLocation(0,0);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setVisible(true);
        label1.setForeground(Color.white);
        panel.add(label1);
        JLabel label2 = new JLabel("Welcome To");
        label2.setBounds(0,80,450,35);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setFont(new Font("Arial",Font.ITALIC,35));
        label2.setForeground(Color.white);
        panel.add(label2);
        JLabel label3 = new JLabel("Guess between(1 To 100)");
        label3.setBounds(0,150,450,30);
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setFont(new Font("Arial",Font.BOLD,16));
        label3.setForeground(Color.white);
        panel.add(label3);
        JButton start = new JButton("Start");
        start.setBounds(330,250,100,40);
        start.setFont(new Font("Arial",Font.BOLD,25));
        start.setBackground(Color.white);
        start.setForeground(Color.black);
        panel.add(start);
        JButton close = new JButton("Close");
        close.setBounds(20,250,100,40);
        close.setFont(new Font("Arial",Font.BOLD,25));
        close.setForeground(Color.black);
        panel.add(close);
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame1.dispose();
            }
        });
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame1.dispose();
               helperGuessTheNumber(totalAttempt);
            }
        });
        frame1.setVisible(true);
        
    }
    public void guessTheNumber(){
        firstPage();
        // for(int i = 0;i<3;i++){
            //     helperGuessTheNumber(roundAttempt-i);
            // }
        }
    }