package CA3.Project.src.utility;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
        public MyFrame(String title){
            this.setTitle(title);
            this.setSize(850, 600);
            this.getContentPane().setBackground(new Color(0x2B2A3c));
//            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setLayout(new BorderLayout(10,10));

//            this.setResizable(false);
            this.setVisible(false);
        }
}
