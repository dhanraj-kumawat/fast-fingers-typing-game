package CA3.Project.src.component.rightSideBar;

import CA3.Project.src.FastFingersXD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

public class LeaderboardPanel extends JPanel {

    public LeaderboardPanel(){
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Rank");
        tableModel.addColumn("Name");
        tableModel.addColumn("Speed(WPM)");



        // db
        try{

            Connection connection = FastFingersXD.getDbConnection();
            String query = "SELECT * From players_tb order by speed Desc limit 25";

            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();  // for dql


            int rank = 1;
            while(resultSet.next()){
                String username = resultSet.getString("username");
                int speed = resultSet.getInt("speed");

                tableModel.addRow(new Object[]{rank++,username, speed});
                System.out.println(username+speed);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        tableModel.addRow(UserInfoPanel.getUserInfo());
        System.out.println(Arrays.toString(UserInfoPanel.getUserInfo()));
        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font
        table.setRowHeight(30); // Set row height
        table.setShowGrid(true); // Show grid lines
        table.setGridColor(Color.LIGHT_GRAY); // Set grid color
        table.setDefaultEditor(Object.class, null); // Disable cell editing


        JScrollPane jScrollPane = new JScrollPane(table);
        add(jScrollPane);
        setBackground(new Color(0x2B2A3c));
    }

}
