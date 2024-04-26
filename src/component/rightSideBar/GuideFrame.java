package CA3.Project.src.component.rightSideBar;

import CA3.Project.src.component.MyFrame;

import javax.swing.*;
import java.awt.*;

public class GuideFrame {
    public GuideFrame(){
        MyFrame leaderboardFrame   = new MyFrame("Guide");

        //
        JLabel title = new JLabel("Leaderboard");
        title.setFont(new Font("mv boli", Font.BOLD, 25));
        title.setForeground(Color.white);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);

        leaderboardFrame.add(title, BorderLayout.NORTH);

        JPanel westPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(400, 400));
        westPanel.add(new UserInfoPanel());
        // adding the leaderboardpanel
        leaderboardFrame.add(new LeaderboardPanel());
        leaderboardFrame.add(westPanel, BorderLayout.WEST);




        leaderboardFrame.setDefaultCloseOperation(leaderboardFrame.DISPOSE_ON_CLOSE);
        leaderboardFrame.repaint();
        System.out.println("leaderboardFrame");
    }
}
