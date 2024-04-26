package CA3.Project.src.component;

import CA3.Project.src.component.rightSideBar.LeaderBoardFrame;
import CA3.Project.src.utility.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EastPanel extends JPanel implements ActionListener {
    MyButton gamesBtn = new MyButton("Games");
    MyButton leaderBoardBtn = new MyButton("Leaderboard");
    MyButton guideBtn = new MyButton("Instruction");
    public EastPanel(){
        this.setBackground(new Color(0x2D3250));
        this.add(gamesBtn);
        this.add(leaderBoardBtn);
//        this.add(guideBtn);

        gamesBtn.addActionListener(this);
        leaderBoardBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == gamesBtn){
           MyFrame games  = new MyFrame("Games");
            games.setDefaultCloseOperation(games.DISPOSE_ON_CLOSE);
            System.out.println("hello");
        }
        if(e.getSource() == leaderBoardBtn){
           new LeaderBoardFrame();
        }
//        if(e.getSource()== guideBtn){
//            new GuideFrame();
//        }
    }
}

