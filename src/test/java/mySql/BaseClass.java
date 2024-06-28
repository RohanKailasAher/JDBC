package mySql;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseClass {
    Connection con;
    Statement stmt;

    @BeforeMethod
    public void setup() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rohandb1", "root", "Neosoft@2024");
        stmt = con.createStatement();
    }

    @Test(priority = 1)
    public void getDataFromDatabase() throws SQLException {
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM student_info");
        while (resultSet.next()) {
            int student_id = resultSet.getInt("student_id");
            String name = resultSet.getString("name");
            int rollno = resultSet.getInt("rollno");
            String address = resultSet.getString("address");
            String state = resultSet.getString("state");
            int pincode = resultSet.getInt("pincode");
            System.out.println("Student ID: " + student_id + ", Name: " + name + ", Roll No: " + rollno +
                    ", Address: " + address + ", State: " + state + ", Pincode: " + pincode);
        }
    }

    @Test(priority = 2)
    public void insertDataIntoDatabase() throws SQLException {
        String insertQuery = "INSERT INTO student_info (name, rollno, address, state, pincode) VALUES ('John Doe', 2021006, '789, RST Street', 'California', 90001)";
        int rowsAffected = stmt.executeUpdate(insertQuery);
        System.out.println(rowsAffected + " row(s) inserted successfully.");
    }

    @Test(priority = 3)
    public void updateDataInDatabase() throws SQLException {
        String updateQuery = "UPDATE student_info SET address = '456, XYZ Avenue' WHERE student_id = 2";
        int rowsAffected = stmt.executeUpdate(updateQuery);
        System.out.println(rowsAffected + " row(s) updated successfully.");
    }

    @Test(priority = 4)
    public void deleteDataFromDatabase() throws SQLException {
        String deleteQuery = "DELETE FROM student_info WHERE student_id = 5";
        int rowsAffected = stmt.executeUpdate(deleteQuery);
        System.out.println(rowsAffected + " row(s) deleted successfully.");
    }

    @AfterMethod
    public void teardown() throws SQLException {
        stmt.close();
        con.close();
    }
}