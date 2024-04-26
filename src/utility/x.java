package CA3.Project.src.utility;

import javax.swing.*;
import java.awt.*;

class MyLabel extends JLabel {

    public MyLabel() {


        ImageIcon imageIcon = new ImageIcon("C:\\Users\\Raj\\Desktop\\CSE school\\sem8\\CSE408 java\\CSE406\\src\\CA3\\Project\\src\\asset\\images\\typingLogo.png");
        setText("Fast FingersXD");
        setIcon(imageIcon);
        setVerticalTextPosition(JLabel.BOTTOM);
        setHorizontalTextPosition(JLabel.CENTER);
        setForeground(Color.BLUE);
        setFont(new Font("MV Boli", Font.BOLD,20));
        setIconTextGap(-70);
        setBorder(BorderFactory.createLineBorder(Color.BLUE,3));
        setVerticalAlignment(JLabel.CENTER);
        setHorizontalAlignment(JLabel.CENTER);



    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Image Panel Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        MyLabel label = new MyLabel();
        label.setSize(new Dimension(10, 20));
        frame.getContentPane().add(label);

        frame.pack();
        frame.setVisible(true);
    }
}

