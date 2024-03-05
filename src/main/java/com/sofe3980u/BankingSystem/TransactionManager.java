package com.sofe3980u.BankingSystem;

import javax.sql.DataSource;
import java.sql.*;

public class TransactionManager {
    private DataSource dataSource;

    public TransactionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean updateMasterLog(int transactionId, String transactionType, int amount, Account sourceAccount, Account destinationAccount) {
        boolean returnResponse = false;
        try (Connection conn = dataSource.getConnection()) {
            String query = "INSERT INTO TransactionMaster (transactionId, transactionType, amount, sourceAccount, destinationAccount) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, transactionId);
                pstmt.setString(2, transactionType);
                pstmt.setInt(3, amount);
                pstmt.setString(4, sourceAccount.getAccountNumber());
                pstmt.setString(5, destinationAccount.getAccountNumber());

                int rowsAffected = pstmt.executeUpdate();
                returnResponse = rowsAffected >= 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnResponse;
    }

    public String fetchTransaction(int transactionId) {
        String transactionDetails = "";
        try (Connection conn = dataSource.getConnection()) {
            String query = "SELECT * FROM TransactionMaster WHERE transactionId = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, transactionId);
                ResultSet resultSet = pstmt.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("transactionId");
                    String type = resultSet.getString("transactionType");
                    String sourceAccount = resultSet.getString("sourceAccount");
                    String destinationAccount = resultSet.getString("destinationAccount");

                    transactionDetails = "Transaction ID: " + id +
                            "Transaction Type: " + type +
                            "Source Account: " + sourceAccount +
                            "Destination Account: " + destinationAccount;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionDetails;
    }
}