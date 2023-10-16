package application;

import db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Statement;
import java.sql.ResultSet;

public class Program {

    public static void main(String[] args) {
        // Variáveis
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();

            st = conn.prepareStatement("INSERT INTO seller (Name , Email, BirthDate , BaseSalary , DepartmentId) VALUES(?, ?, ?, ?, ? ) ",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, "Carl Purple");
            st.setString(2, "carl@gmail.com");
            st.setString(3, "carl@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("24/11/2023").getTime()));
            st.setDouble(4, 3000.0);
            st.setDouble(5, 4);
            int rowsAffected = st.executeUpdate(); // Comando usado para fazer a inserção de dados .
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()){
                int id = rs.getInt(1);
                    System.out.println("Done! Id = " + id);
            }
                System.out.println("No rown affected!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }

}
