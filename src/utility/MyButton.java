package CA3.Project.src.utility;

import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {
    public MyButton(String title){
        this.setText(title);
        this.setBackground(new Color(0xFBA834));
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setMargin(new Insets(3,5,3,5));
    }
}
