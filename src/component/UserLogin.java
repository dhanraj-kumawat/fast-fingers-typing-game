package CA3.Project.src.component;

import CA3.Project.src.utility.InputPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserLogin extends JPanel implements ActionListener {
    JButton button;
    InputPanel namePanel;
    JPanel northPanel;
        public UserLogin(JPanel northPanel) {
            this.northPanel= northPanel;
            namePanel = new InputPanel("User Name: ");

            this.add(namePanel);

            button = new JButton("Enter in to the game ->");
            button.setBackground(new Color(0xFBA834));
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            button.addActionListener(this);

            this.add(button);

            this.setLayout(new FlowLayout());
            Border border = BorderFactory.createLineBorder(new Color(0x190482),3);
            this.setBorder(border);
            this.setBackground(new Color(0x8E8FFA));
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            Component[] components = namePanel.getComponents();
            JTextField name = (JTextField) components[1];
            String username = name.getText();

            Component[] components1 = northPanel.getComponents();
            JLabel userInfo = (JLabel) components1[1];
            if(!username.trim().equals(""))
            userInfo.setText("Welcome "+username);
            else
                userInfo.setText("Welcome Guest_001");
            northPanel.remove(0);
            northPanel.repaint();
        }
    }

    public InputPanel getNamePanel() {
        return namePanel;
    }

    public void setNamePanel(InputPanel namePanel) {
        this.namePanel = namePanel;
    }
}
