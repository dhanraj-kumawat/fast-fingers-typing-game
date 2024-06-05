package CA3.Project.src.database;

import CA3.Project.src.model.Key;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbUtility {
    private static final Connection dbConnection = Db.getDatabseConnection();
    public static Map<Character, Integer> getMistypedKeys(){
        HashMap<Character, Integer> keyToCount = new HashMap<>();
        try {
            Connection con = dbConnection;
            String query = "SELECT *from mistyped_keys";
            assert con != null;
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                char wrong_key = rs.getString("mistyped_key").charAt(0);
                int wrong_count = rs.getInt("count");
                keyToCount.put(wrong_key, wrong_count);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return keyToCount;
    }
    public static void updateMistypedKeys(Map<Character, Integer> keyToCount){
        try {
            Connection con = dbConnection;
            String query = "update mistyped_keys set count=? where mistyped_key=?";
            assert con != null;
            PreparedStatement ps = con.prepareStatement(query);
            for(char key : keyToCount.keySet()){
                int wrongCount = keyToCount.get(key);
                try {
                    ps.setString(1,""+ key);
                    ps.setInt(2, wrongCount);
                    int response = ps.executeUpdate();
                    if(response == 0) System.out.println("key update failed");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
