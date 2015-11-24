package ru.bogdanov.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ru.bogdanov.domain.Component;

public class ActionsForDB {
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/myWarehouse";
    private static final String DB_USER = "user";
    private static final String DB_PASSWORD = "password";
    
    private static Connection getDBConnect(){
	Connection con = null;
	
	try {
	    Class.forName(DB_DRIVER);
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}	
	try {
	    con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	    return con;
	} catch (SQLException e){
	    e.printStackTrace();	    
	}
	return con;		
    }

    public static List<Component> getAllData(String tableName) throws SQLException {
	
	List<Component> list = new ArrayList<>();
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String query = "SELECT * FROM " + tableName + " ORDER BY name";
	
	try {
	    con = getDBConnect();
	    pstmt = con.prepareStatement(query);
	    rs = pstmt.executeQuery();	    
	    while(rs.next()){
		String s = rs.getString("name");
		Integer i = rs.getInt("quantity");
		list.add(new Component(s, i));
	    }	    
	} catch (SQLException e){
	    e.printStackTrace();
	} finally {	    
	    if (rs != null) {rs.close();}
	    if (pstmt != null) {pstmt.close();}
	    if (con != null) {con.close();}	    
	}
	return list;	
    }
    
    public static void addDataInDB (List<Component> list) throws SQLException {
	
    }
    
    public static void createTable(String tableName) throws SQLException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String query = "CREATE TABLE " + tableName + " (name VARCHAR(30), quantity INT(5), PRIMARY KEY (name))";
			
	try {
	    con = getDBConnect();
	    pstmt = con.prepareStatement(query);
	    pstmt.executeUpdate();		   		    
	} catch (SQLException e){
	    e.printStackTrace();
	} finally {	    
	    if (pstmt != null) {pstmt.close();}
	    if (con != null) {con.close();}
	    
	}
	
    }
    
    public static void deleteTable(String tableName) throws SQLException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String query = "DROP TABLE " + tableName ;
	
	try {
	    con = getDBConnect();
	    pstmt = con.prepareStatement(query);
	    pstmt.executeUpdate();		   		    
	} catch (SQLException e){
	    e.printStackTrace();
	} finally {	    
	    if (pstmt != null) {pstmt.close();}
	    if (con != null) {con.close();}
	    
	}
	
    }
    
}
