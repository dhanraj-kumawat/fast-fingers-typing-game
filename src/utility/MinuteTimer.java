package CA3.Project.src.utility;

import CA3.Project.src.App;
import CA3.Project.src.FastFingersXD;
import CA3.Project.src.component.MainBoard;
import CA3.Project.src.database.Db;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;

public class MinuteTimer extends JPanel {
    private static JLabel timerLabel;
    private static Timer timer;
    private static Instant startTime;

    private static  void setTimer(){
        timer.start();
    }
    private static void setStartTime(){
        startTime = Instant.now();
    }
    public static void restartTimer(){
        setTimer();
        setStartTime();
    }
    public static void stopTimer(){
        timer.stop();
        timerLabel.setText("00:00");
    }
    public static long getTimeinSeconds(){
        Duration elapsed = Duration.between(startTime, Instant.now());
        return elapsed.getSeconds();
    }
    public MinuteTimer() {

        setSize(200, 100);
        this.setBackground(new Color(0xFBA834));

        timerLabel = new JLabel("00:00");
        add(timerLabel);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Duration elapsed = Duration.between(startTime, Instant.now());
                long remainingSeconds = 60 - elapsed.getSeconds();
                if (remainingSeconds <= 0) {
                    timer.stop();
                    timerLabel.setText("00:00");
                    int newSpeed = MainBoard.getSpeed();
                    int res =  JOptionPane.showConfirmDialog(null, "Time's up!/n"+"Your Speed is: " +newSpeed+" WPM with accuracy : "+MainBoard.getAccuracy());
                    if(res== JOptionPane.OK_OPTION){

                        Connection connection =  FastFingersXD.getDbConnection();
                        String query = "SELECT * From players_tb where username=?";
                        String userName = App.getUserName();
                        System.out.println(userName);
                        try {
                            assert connection != null;
                            PreparedStatement ps = connection.prepareStatement(query);
                            ps.setString(1,userName);
                            ResultSet resultSet = ps.executeQuery();

                            if(resultSet.next()){
                                String username = resultSet.getString("username");
                                int prevSpeed = resultSet.getInt("SPEED");

                                float accuracy = MainBoard.getAccuracy();
                                if(newSpeed > prevSpeed){
                                    String q2 = "update players_tb set speed=? where username=?";
                                    PreparedStatement p2  = connection.prepareStatement(q2);
                                    p2.setInt(1, newSpeed);
                                    p2.setString(2, username);
                                    int response = p2.executeUpdate();
                                    if(response>0)
                                        System.out.println("success");
                                    else
                                        System.out.println("Failed");
                                }
                                System.out.println(username + "   " +  prevSpeed);
                                System.out.println(username+ "   " + newSpeed);
                            }
                            else{
                                String q = "Insert into players_tb(username,speed) values(?,?)";
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
                } else {
                    long minutes = remainingSeconds / 60;
                    long seconds = remainingSeconds % 60;
                    timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
                }
            }
        });

        MyButton startButton = new MyButton("Start");
        startButton.setBackground(Color.white);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainBoard.clearWriteArea();
            }
        });
        add(startButton, "South");

        setVisible(true);
    }
}

