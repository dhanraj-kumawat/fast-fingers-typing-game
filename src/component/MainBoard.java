package CA3.Project.src.component;


import CA3.Project.src.App;
import CA3.Project.src.utility.GameUtility;
import CA3.Project.src.utility.MinuteTimer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;


public class MainBoard extends  JLayeredPane {
    static JTextArea contentArea = new JTextArea("");
    static JTextArea writeArea = new JTextArea();

   public static int writtenChars = 0;
   public static int correctChars = 0;

    public static void clearWriteArea(){
        writeArea.setText("");
        correctChars=0;
        writtenChars = 0;
        App.resetProgressbar();
        MinuteTimer.restartTimer();
    }
    public MainBoard(App app){

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(0x35374B));

        MenuPanel boardMenuPanel = new MenuPanel(this);


        contentArea = new JTextArea(2,35);
        contentArea.setName("contentArea");
        contentArea.setLayout(new FlowLayout());
        contentArea.setBackground(new Color(0x2B2A4C));
        contentArea.setEditable(false);

        // load the content
        loadContent();


        contentArea.setFont(new Font("Consolas", Font.PLAIN, 24));
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setMargin(new Insets(10, 15, 10, 15));
        contentArea.setFocusable(false);
        contentArea.setForeground(new Color(0xF6B17A));
        contentArea.setPreferredSize(new Dimension(400,400));


//        writeArea = new JTextArea(4, 45);
        writeArea.setBackground(new Color(0x424769));
        writeArea.setCaretColor(new Color(0xFC6736));
        writeArea.setText("");

        JScrollPane writeScrollPane = new JScrollPane(writeArea);

        writeArea.setFont(new Font("Consolas", Font.PLAIN, 24));
        writeArea.setForeground(new Color(0xffffff));
        writeArea.setLineWrap(true);
        writeArea.setWrapStyleWord(true);
        writeArea.setMargin(new Insets(10, 15, 10, 15));

        writeArea.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                int offset = e.getOffset();
                char c = writeArea.getText().charAt(offset);

               if(c == contentArea.getText().charAt(offset))
                   correctChars++;
                writtenChars++;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });



        class wakeylistener implements KeyListener{

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println(writtenChars + " and correct : "+ correctChars);
                if(writeArea.getText().length() == 1){
                    MinuteTimer.restartTimer();
                }
                if(!contentArea.getText().startsWith(writeArea.getText())){
                    writeArea.setForeground(Color.red);
                }
                else{
                    writeArea.setForeground(Color.white);
                }
                if(e.getKeyChar()=='`'){
                    System.out.println("write-area cleared");
                    MainBoard.clearWriteArea();
                }else{
                    App.progressBar.setValue((correctChars*100)/contentArea.getText().length());
                }

            }
        }

        writeArea.addKeyListener(new wakeylistener());


        this.add(boardMenuPanel);
        this.add(contentArea);
        this.add(writeScrollPane);

    }



    public  void loadContent(){
        try{
            char[] difficultKeys = {'x','y','z'};
            Path path = Paths.get("src/CA3/Project/src/asset/content");
            ArrayList<Path> contentList = new ArrayList<>();
            Files.list(path).forEach((p)->{contentList.add(p);});

            int randomIndex = new Random().nextInt(contentList.size());

            BufferedReader reader = 	Files.newBufferedReader(contentList.get(randomIndex)) ;
            reader.lines().forEach((e)-> {
                StringBuilder difficultWord=new StringBuilder();
               for(int i = 0; i<5; i++){
                    difficultWord.append(GameUtility.GenerateWord(difficultKeys)).append(" ");
               }
                contentArea.setText(difficultWord+e+" "+difficultWord);
            });


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static int getSpeed(){
        long timeTaken = MinuteTimer.getTimeinSeconds();
        MinuteTimer.stopTimer();
        int words = correctChars/5;
        int speed =Math.round((float) (words * 60) /timeTaken);
        return speed;
    }
    public static float getAccuracy() {
        if(writtenChars == 0)
            return 0;
        float accuracy = (correctChars*100)/writtenChars;
        return accuracy;
    }
}
