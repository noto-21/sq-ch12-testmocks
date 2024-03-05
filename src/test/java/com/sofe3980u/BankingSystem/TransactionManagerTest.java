package com.sofe3980u.BankingSystem;

import org.junit.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import java.sql.*;

public class TransactionManagerTest {
    @Mock
    private Account sourceMock;
    @Mock
    private Account destinationMock;
    private TransactionManager transactionManager;

    @Before
    public void setup() {
        sourceMock = mock(Account.class);
        destinationMock = mock(Account.class);
        transactionManager = new TransactionManager();
    }

    @Test
    public void testUpdateMasterLog() throws SQLException {
        // Mock the Connection, PreparedStatement, and DriverManager classes
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

        // Stubbing the DriverManager.getConnection method to return the mock connection
        when(DriverManager.getConnection(anyString())).thenReturn(mockConnection);

        // Stubbing the prepareStatement method of the mock connection
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Stubbing the executeUpdate method of the mock PreparedStatement
        when(mockPreparedStatement.executeUpdate()).thenReturn(1); // Assuming one row is affected

        // Assert the result
        Assert.assertTrue(transactionManager.updateMasterLog(1, "TransactionType", sourceMock, destinationMock)); // Assuming that true is returned when the update is successful
    }

    @Test
    public void testFetchTransaction() throws SQLException {
        // Mock the Connection, PreparedStatement, ResultSet, and DriverManager classes
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Stubbing the DriverManager.getConnection method to return the mock connection
        when(DriverManager.getConnection(anyString())).thenReturn(mockConnection);

        // Stubbing the prepareStatement method of the mock connection
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Stubbing the executeQuery method of the mock PreparedStatement
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        // Stubbing the result set to simulate a single row with transaction details
        when(mockResultSet.next()).thenReturn(true); // Simulating that there is at least one row
        when(mockResultSet.getInt("transactionId")).thenReturn(1);
        when(mockResultSet.getString("transactionType")).thenReturn("TransactionType");
        when(mockResultSet.getString("sourceAccount")).thenReturn("SourceAccountNumber");
        when(mockResultSet.getString("destinationAccount")).thenReturn("DestinationAccountNumber");

        // Execute the method to be tested
        String transactionDetails = transactionManager.fetchTransaction(1);

        // Assert the result
        Assert.assertEquals("Transaction ID: 1Transaction Type: TransactionTypeSource Account: SourceAccountNumberDestination Account: DestinationAccountNumber", transactionDetails);
    }
}
