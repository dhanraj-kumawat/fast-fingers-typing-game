package CA3.Project.src;

import CA3.Project.src.component.EastPanel;
import CA3.Project.src.component.MainBoard;

import CA3.Project.src.component.UserLogin;

import CA3.Project.src.utility.MyFrame;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.sql.*;

public class App  {
    public static JProgressBar progressBar = new JProgressBar(0);
    static JLabel userInfo= new JLabel("Guest_01");

    public static String getUserName(){
        return userInfo.getText().trim().substring(8).toLowerCase();
    }
    public static void resetProgressbar() {
        progressBar.setValue(0);
    }

    public void startApp() {
        MyFrame f= new MyFrame("Fast FingersXD");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setIconImage(new ImageIcon("C:\\Users\\Raj\\Desktop\\CSE school\\sem8\\CSE408 java\\CSE406\\src\\CA3\\Project\\src\\asset\\images\\typingLogo.png").getImage());

        EastPanel eastPanel = new EastPanel();
        JPanel westPanel = new JPanel();
        JPanel northPanel = new JPanel();

        UserLogin login = new UserLogin(northPanel);
        northPanel.setBackground(new Color(0x2D3250));
        northPanel.add(login);

        userInfo.setForeground(new Color(0xFBA834));
        userInfo.setText("Guest_01");
        userInfo.setFont(new Font("MV Boli", Font.BOLD,20));
        northPanel.add(userInfo);




        progressBar = new JProgressBar(JProgressBar.VERTICAL);
        progressBar.setStringPainted(true);
        progressBar.setValue(0);
        progressBar.setBackground(new Color(0));
        progressBar.setForeground(new Color(0xFC6736));
        progressBar.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(progressBar.getValue()== 100){
                    int newSpeed = MainBoard.getSpeed();
                    int res =  JOptionPane.showConfirmDialog(null, "Time's up!/n"+"Your Speed is: " +newSpeed+" WPM with accuracy : "+MainBoard.getAccuracy());
                   if(res== JOptionPane.OK_OPTION){

                       Connection connection =  FastFingersXD.getDbConnection();
                       String query = "SELECT * From users where username=?";
                       String userName = App.getUserName();
                       System.out.println(userName);
                       try {
                           assert connection != null;
                           PreparedStatement ps = connection.prepareStatement(query);
                           ps.setString(1,userName);
                           ResultSet resultSet = ps.executeQuery();

                           if(resultSet.next()){
                               String username = resultSet.getString("username");
                               int prevSpeed = resultSet.getInt("BEST_SPEED");

                               float accuracy = MainBoard.getAccuracy();
                               if(newSpeed > prevSpeed){
                                   String q2 = "update users set speed=? where username=?";
                                   PreparedStatement p2  = connection.prepareStatement(q2);
                                   p2.setInt(1, newSpeed);
                                   p2.setString(2, username);
                                   int response = p2.executeUpdate();
                                   if(response>0)
                                       System.out.println("success");
                                   else
                                       System.out.println("Failed");
                               }
                               System.out.println(username + prevSpeed);
                           }
                           else{
                               String q = "Insert into users(username,best_speed) values(?,?)";
                               PreparedStatement ps1= connection.prepareStatement(q);
                               ps1.setString(1,userName);
                               ps1.setInt(2,MainBoard.getSpeed());
                               int response = ps1.executeUpdate();
                               if(response>0)
                                   System.out.println("success");
                               else
                                   System.out.println("Failed");
                           }
                       } catch (SQLException ex) {
                           throw new RuntimeException(ex);
                       }

                   }
                }
            }
        });



        westPanel.add(progressBar);
        westPanel.setBackground(new Color(0x2D3250));
//        westPanel.setPreferredSize(new Dimension(100,100));

        eastPanel.setPreferredSize(new Dimension(150,100));
        eastPanel.setLayout(new FlowLayout());

        MainBoard board = new MainBoard(this);
        board.setPreferredSize(new Dimension(400,400));



        f.add(northPanel, BorderLayout.NORTH);
        f.add(board, BorderLayout.CENTER);
        f.add(eastPanel, BorderLayout.EAST);
        f.add(westPanel, BorderLayout.WEST);
        f.setVisible(true);



    }

}
