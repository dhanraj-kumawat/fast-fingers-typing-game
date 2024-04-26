package CA3.Project.src;


import CA3.Project.src.database.Db;

import java.sql.Connection;

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
