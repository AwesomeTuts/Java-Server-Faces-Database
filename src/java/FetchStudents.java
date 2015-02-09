/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mysql.jdbc.Connection;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author fahirmehovic
 */
@ManagedBean
@ApplicationScoped
public class FetchStudents {

    private PreparedStatement statement;
    
    /**
     * Creates a new instance of FetchStudents
     */
    public FetchStudents() {
        prepareConnection();
    }
    
    public void prepareConnection() {
        
        try {
			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded");
			
			Connection connection = 
					(Connection) DriverManager.getConnection("jdbc:mysql://localhost/Students",
							 "user", "user");
			System.out.println("Database connected");
			
			
			statement = connection.prepareStatement("SELECT * FROM `Student`");
			
			
		} catch(Exception e) {
			System.out.println("Error: " + e.toString());
		}
        
    }
    
    public ResultSet getStudents() throws SQLException {
        
            CachedRowSet rowSet = new CachedRowSetImpl();
            rowSet.populate(statement.executeQuery());
            
            return rowSet;   
    }
    
}
