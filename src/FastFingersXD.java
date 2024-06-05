package CA3.Project.src;


import CA3.Project.src.database.Db;
import CA3.Project.src.utility.GameUtility;

import java.sql.Array;
import java.sql.Connection;

public class FastFingersXD {
    private static final Connection dbConnection = Db.getDatabseConnection();

    public static Connection getDbConnection(){
        return dbConnection;
    }

    public static void main(String[] args) {
        System.out.println(GameUtility.GenerateWord(new char[]{'a','x','c','p'}));
        App app = new App();
        app.startApp();
    }
}
