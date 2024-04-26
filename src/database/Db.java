package CA3.Project.src.database;

import java.sql.Connection;
import java.sql.DriverManager;


public class Db {
    private static String userName = "root";
    private static String password = "Dk#1252";
    public static Connection getDatabseConnection(){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fastfingers_db", Db.userName, Db.password);
            return connection;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
