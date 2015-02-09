/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author fahirmehovic
 */
@ManagedBean
@ApplicationScoped
public class StoreStudent {

        String id = "";
    String name = "";
    String lastName = "";
    
    private PreparedStatement prepState;
    
    /**
     * Creates a new instance of StoreStudent
     */
    public StoreStudent() {
        prepareConnection();
    }
    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void prepareConnection() {
        
        try {
			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded");
			
			Connection connection = 
					(Connection) DriverManager.getConnection("jdbc:mysql://localhost/Students",
							 "user", "user");
			System.out.println("Database connected");
			
			prepState = connection.prepareStatement("INSERT INTO `Student` (`studentID`,"
                                + " `name`, `lastName`) VALUES (?, ?, ?)");
			
			
			
		} catch(Exception e) {
			System.out.println("Error: " + e.toString());
		}
        
    }
    
    public void addStudent() throws Exception {
        
        prepState.setString(1, id);
            prepState.setString(2, name);
            prepState.setString(3, lastName);
            prepState.executeUpdate();
    }
    
}
