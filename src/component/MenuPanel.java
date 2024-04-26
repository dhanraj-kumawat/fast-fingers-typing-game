package CA3.Project.src.component;

import CA3.Project.src.App;
import CA3.Project.src.utility.MinuteTimer;
import CA3.Project.src.utility.MyButton;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener{
    MyButton abc;
    String punctext = null;
    boolean isLowerCase = false;
    MyButton restartbtn;
    MyButton punctuation;
    MainBoard board;
    JTextArea writeArea;
    MinuteTimer minuteTimer = new MinuteTimer();
    public MenuPanel(MainBoard board){
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(new Color(0x2B2A3c));
        this.board = board;

        this.writeArea = board.writeArea;

        abc = new MyButton("abc");
        abc.addActionListener(this);
        abc.setFocusable(false);
        this.add(abc);

        punctuation = new MyButton("Punctuation");
        punctuation.addActionListener(this);
        punctuation.setFocusable(false);
        this.add(punctuation);

        restartbtn = new MyButton("Restart");
        restartbtn.addActionListener(this);
        restartbtn.setFocusable(false);
        this.add(restartbtn);

        this.add(minuteTimer);

    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == abc){
            System.out.println("abc");
            if(isLowerCase)
                return;
            Component[] components =  board.getComponents();
            JTextArea jta = (JTextArea) components[1];
            String text = jta.getText();

            if(punctext ==null){
                punctext = text;
            }

            text = removePunctuation(text);
            jta.setText(text.toLowerCase());
            isLowerCase = true;
        }
        if(e.getSource() == punctuation){
            System.out.println("punctuation");
            if(isLowerCase){
            Component[] components =  board.getComponents();
            JTextArea jta = (JTextArea) components[1];
            jta.setText(punctext);
            isLowerCase = false;
            }
            MainBoard.clearWriteArea();
        }

        if(e.getSource() == restartbtn){
            System.out.println("restartbtn");
                board.loadContent();
                MainBoard.clearWriteArea();
        }
    }
    public static String removePunctuation(String str){
       str = str.toLowerCase();
       StringBuilder sb  = new StringBuilder("");
        for(int i=0; i<str.length(); i++){
            char c  = str.charAt(i);
            if(c == ' ' || (c>='a' && c<='z')){
                sb.append(c);
            }
        }
        MainBoard.clearWriteArea();
        return sb.toString();
    }


}
