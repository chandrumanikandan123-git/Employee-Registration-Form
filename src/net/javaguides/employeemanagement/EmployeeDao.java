package net.javaguides.employeemanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeDao {


    public int registerEmployee(Employee employees) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "INSERT INTO employees(first_name, last_name, username, password, address, contact) VALUES (?, ?, ?, ?, ?, ?);";
        int result = 0;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp-servlet-jdbc-mysql-example?useSSL=false", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1,  employees.getFirstName());
            preparedStatement.setString(2,  employees.getLastName());
            preparedStatement.setString(3,  employees.getUsername());
            preparedStatement.setString(4,  employees.getPassword());
            preparedStatement.setString(5,  employees.getAddress());
            preparedStatement.setString(6,  employees.getContact());

            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}

