
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBOperation {
	   
	public Connection getConnection() {
	    Connection connection = null;
	    
	    try {
	        // Load the MySQL JDBC driver
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        // Establish a database connection
	        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "root");

	        if (connection != null) {
	            System.out.println("Connected to the database.");
	        }
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        System.err.println("JDBC driver not found.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.err.println("Failed to connect to the database.");
	    }

	    return connection;
	}


public boolean userSignup(String firstname,String lastname, String email, String password) throws SQLException {

	  String sql =
	  "INSERT INTO userdata (firstname, lastname, email, password) VALUES (?, ?, ?, ?)"
	  ;
	  Connection connection=getConnection();
	  // Create a PreparedStatement 
	  PreparedStatement preparedStatement = connection.prepareStatement(sql);
	
	// Set the parameter values 
	  preparedStatement.setString(1, firstname);
	  preparedStatement.setString(2, lastname);
	  preparedStatement.setString(3,
	  email);
	  preparedStatement.setString(4, password);
	  
	  // Execute the INSERT statement int rowsInserted =
	  int rowsInserted = preparedStatement.executeUpdate(); 
	  if (rowsInserted > 0) {
		  
	  System.out.println("Data inserted successfully."); 
	  return true;
	  }
	  else {
	  System.out.println("Data insertion failed."); }
	  
	  // Close the resources preparedStatement.close(); connection.close();
	 
	return false;
}

public boolean userLogin(String email, String password) throws SQLException {
    String sql = "SELECT * FROM userdata WHERE email = ? AND password = ?";
    Connection connection = getConnection();
    
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                // User found; login successful
                System.out.println("Login successful.");
                return true;
            } else {
                // User not found or incorrect credentials
                System.out.println("Login failed. User not found or incorrect credentials.");
                return false;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    } finally {
        // Close the connection
        if (connection != null) {
            connection.close();
        }
    }
}


   }
	