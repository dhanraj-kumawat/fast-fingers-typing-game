package CA3.Project.src.utility;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel {
    public InputPanel(String label){

        JLabel nameLabel = new JLabel(); // Set the label text
        nameLabel.setText(label);
        JTextField nameTextField = new JTextField(16);
        this.setLayout(new FlowLayout(FlowLayout.LEFT)); // Use a layout manager
        this.add(nameLabel);
        this.add(nameTextField);
        this.setBackground(new Color(0x8E8FFA));
    }
}
