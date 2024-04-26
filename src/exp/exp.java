package CA3.Project.src.exp;

import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Student Marks");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Marks");


        model.addRow(new Object[]{"Alice", 85});
        model.addRow(new Object[]{"Bob", 90});
        model.addRow(new Object[]{"Charlie", 75});
        model.addRow(new Object[]{"David", 80});
        model.addRow(new Object[]{"Eve", 95});


        JTable table = new JTable(model);


        table.setFillsViewportHeight(true);
        table.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font
        table.setRowHeight(30);
        table.setShowGrid(true);
        table.setGridColor(Color.LIGHT_GRAY);



        JScrollPane scrollPane = new JScrollPane(table);


        frame.add(scrollPane);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}
