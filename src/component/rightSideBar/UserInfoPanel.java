package CA3.Project.src.component.rightSideBar;

import CA3.Project.src.App;
import CA3.Project.src.database.Db;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoPanel extends JPanel {
    static Object[] userinfo = new Object[]{0,"user",0};
    public static Object[] getUserInfo(){
        return userinfo;
    }
    public UserInfoPanel()  {
        System.out.println("userinfopanel");
        setLayout(new FlowLayout());
        setBackground(Color.green);
        setPreferredSize(new Dimension(300,300));
        JLabel name = new JLabel("");
        try{
            Connection connection = Db.getDatabseConnection();




            String query = "Select * from players_tb where username=?";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(query);
            System.out.println(App.getUserName());
            ps.setString(1, App.getUserName());
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                String username = resultSet.getString("username") ;
                int speed = resultSet.getInt("speed");
                JLabel nameLabel = new JLabel(username);
                JLabel speedLabel = new JLabel(""+speed);

                String rankQuery = "Select count(*) from players_tb where speed>?";
                PreparedStatement ps2 = connection.prepareStatement(rankQuery);
                ps2.setInt(1,speed);
                int userrank = 0;
                ResultSet rs = ps2.executeQuery();
                if(rs.next()){
                    System.out.println(rs.getInt(1));
                    userrank = rs.getInt(1)+1;
                }

                userinfo = new Object[]{userrank, username, speed};
                add(nameLabel);
                add(speedLabel);
                System.out.println( username+" and  "+ speed );
            }
        }catch (SQLException e){
            System.out.println(e);
        }
    }
}
