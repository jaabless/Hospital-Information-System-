import db_config.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ui.HospitalUI.launch(ui.HospitalUI.class);//to launch the application
    }
//    public static void main(String[] args) {
//        Connection conn = DBConnection.getConnection();
//        System.out.println("Connected to database!");
//    }

}