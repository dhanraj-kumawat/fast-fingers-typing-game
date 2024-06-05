package CA3.Project.src.database;

import CA3.Project.src.model.Key;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbUtility {
    private static final Connection dbConnection = Db.getDatabseConnection();
    public static List<Key> getMistypedKeys(){
        List<Key> misTypedKeys = new ArrayList<>();
        try {
            Connection con = dbConnection;
            String query = "SELECT *from mistyped_keys";
            assert con != null;
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                char wrong_key = rs.getString("mistyped_key").charAt(0);
                int wrong_count = rs.getInt("count");
                System.out.println(wrong_key+" "+ wrong_count);
                misTypedKeys.add(new Key(wrong_key, wrong_count));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return misTypedKeys;
    }
    public static void updateMistypedKeys(List<Key> updatedKeysList){
        try {
            Connection con = dbConnection;
            String query = "update mistyped_keys set count=? where mistyped_key=?";
            assert con != null;
            PreparedStatement ps = con.prepareStatement(query);
            updatedKeysList.forEach((key) -> {
                char misTypedKey = key.getWrongKey();
                int wrongCount = key.getWrongCount();
                try {
                    ps.setString(1,""+misTypedKey);
                    ps.setInt(2, wrongCount);
                    int response = ps.executeUpdate();
                    if(response == 0) System.out.println("key update failed");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
