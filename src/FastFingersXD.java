package CA3.Project.src;


import CA3.Project.src.database.Db;
import CA3.Project.src.database.DbUtility;
import CA3.Project.src.utility.GameUtility;

import java.sql.Connection;
import java.util.Arrays;

public class FastFingersXD {
    private static final Connection dbConnection = Db.getDatabseConnection();

    public static Connection getDbConnection(){
        return dbConnection;
    }

    public static void main(String[] args) {
        App app = new App();
        app.startApp();
    }
}
