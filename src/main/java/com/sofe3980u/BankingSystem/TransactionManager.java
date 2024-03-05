package com.sofe3980u.BankingSystem;

import java.sql.*;

public class TransactionManager {
	static final String DB_URL = "DATABASE_URL";
	
	public boolean updateMasterLog(int transactionId, String transactionType, Account sourceAccount, Account destinationAccount) {

		boolean returnResponse = false;
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
	            returnResponse = rowsAffected >= 0 ? true : false;
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
	
	public String fetchTransaction(int transactionId) {
		String transactionDetails = "";
	    try (Connection conn = DriverManager.getConnection(DB_URL)) {
	        String query = "SELECT * FROM TransactionMaster WHERE transactionId = ?";
	        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	            // Set the transactionId as a parameter in the PreparedStatement
	            pstmt.setInt(1, transactionId);

	            // Execute the query
	            ResultSet resultSet = pstmt.executeQuery();

	            // Process the result set
	            while (resultSet.next()) {
	                // Retrieve transaction details from the result set
	                int id = resultSet.getInt("transactionId");
	                String type = resultSet.getString("transactionType");
	                String sourceAccount = resultSet.getString("sourceAccount");
	                String destinationAccount = resultSet.getString("destinationAccount");

	                // Output or process retrieved transaction details
	                transactionDetails = "Transaction ID: " + id +
	                "Transaction Type: " + type +
	                "Source Account: " + sourceAccount +
	                "Destination Account: " + destinationAccount;
	            }
	        } catch (SQLException e) {
	            // Handle PreparedStatement errors
	            e.printStackTrace();
	        }
	    } catch (SQLException e) {
	        // Handle connection errors
	        e.printStackTrace();
	    }
		return transactionDetails;
	}
}
