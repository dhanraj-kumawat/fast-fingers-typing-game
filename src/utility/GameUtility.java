package CA3.Project.src.utility;

import CA3.Project.src.database.DbUtility;
import CA3.Project.src.model.Key;

import java.util.*;

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
    public static char[] getdifficultKeys(){
        Map<Character, Integer> keyToCountMap = DbUtility.getMistypedKeys();
        char[] difficult = new char[5];
        ArrayList<Key> allKeysList = new ArrayList<>();
        PriorityQueue<Key> difficultKeysPq = new PriorityQueue<>(new Comparator<Key>() {
            @Override
            public int compare(Key k1, Key k2) {
                return k1.getWrongCount()-k2.getWrongCount();
            }
        });
        for(char c : keyToCountMap.keySet()){
            allKeysList.add(new Key(c, keyToCountMap.get(c)));
        }

        // finding difficult keys using minHeap
        for(int i = 0; i<5; i++){
            difficultKeysPq.add(allKeysList.get(i));
        }
        for(int i = 5; i<allKeysList.size();  i++){
            if(difficultKeysPq.size() == 5){
                difficultKeysPq.remove();
                difficultKeysPq.add(new Key(allKeysList.get(i).getWrongKey(), allKeysList.get(i).getWrongCount()));
            }
        }



        // updating difficult array
        int index = 0;
        while(!difficultKeysPq.isEmpty()){
            char c = difficultKeysPq.remove().getWrongKey();
            difficult[index++] = c;
        }
        return difficult;
    }

}
