package com.sofe3980u.BankingSystem;

import java.sql.*;

public class TransactionManager {
	static final String DB_URL = "DATABASE_URL";
	
	public String updateMasterLog(int transactionId, String transactionType, Account sourceAccount, Account destinationAccount) {

		String returnResponse = "";
	    try (Connection conn = DriverManager.getConnection(DB_URL)) {
	        String query = "INSERT INTO TransactionMaster (transactionId, transactionType, sourceAccount, destinationAccount) VALUES (?, ?, ?, ?)";
	        // Create a PreparedStatement to safely insert values into the SQL statement
	        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	            // Set values for the placeholders in the PreparedStatement
	            pstmt.setInt(1, transactionId);
	            pstmt.setString(2, transactionType);
	            pstmt.setString(3, sourceAccount.getAccountNumber());
	            pstmt.setString(4, destinationAccount.getAccountNumber());

	            // Execute the SQL update
	            int rowsAffected = pstmt.executeUpdate();
	            returnResponse += "Transaction Recorded, " + rowsAffected + " row(s) affected.";
	        } catch (SQLException e) {
	            // Handle PreparedStatement errors
	            e.printStackTrace();
	        }
	    } catch (SQLException e) {
	        // Handle connection errors
	        e.printStackTrace();
	    }
		return returnResponse;
	}	

}
