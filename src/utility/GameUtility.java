package CA3.Project.src.utility;

import java.util.Random;

public class GameUtility {
    public static String GenerateWord(char[] keys){
        StringBuilder word = new StringBuilder();
        Random random = new Random();
        int index =(int)( Math.random() *10)%keys.length;
        word.append((char)('A' + (keys[index]-'a')));
        for(int i=0; i<4; i++){
            char ch = (char) ('a'+random.nextInt(25));
            word.append(ch);
        }
        word.append(keys[index]);
        return word.toString();
    }
}
