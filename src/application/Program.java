package application;

import db.DB;
import java.sql.Connection;

public class Program {

    public static void main(String[] args) {
        // Connection conn = DB.getConnection();
        DB conn = new DB ();
        DB.getConnection();
        DB.closeConnection();
       

}
}