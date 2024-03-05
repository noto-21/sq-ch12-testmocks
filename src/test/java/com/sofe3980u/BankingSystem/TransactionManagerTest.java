package com.sofe3980u.BankingSystem;

import org.junit.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionManagerTest {
    @Mock
    private DataSource mockDataSource;

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @Mock
    private Account sourceMock;

    @Mock
    private Account destinationMock;

    private TransactionManager transactionManager;

    @Before
    public void setup() throws SQLException {
        // Initialize mocks
        mockDataSource = mock(DataSource.class);
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        sourceMock = mock(Account.class);
        destinationMock = mock(Account.class);

        // Confirm mockConnection is established
        when(mockDataSource.getConnection()).thenReturn(mockConnection);

        // Confirm preparedStatement has a string
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Confirm executeUpdate is configured to return 1
        when(mockPreparedStatement.executeUpdate()).thenReturn(1); // Assuming one row is affected

        // Confirm source and destination account numbers are correctly retrieved
        when(sourceMock.getAccountNumber()).thenReturn("sourceAccountNumber");
        when(destinationMock.getAccountNumber()).thenReturn("destinationAccountNumber");

        // Instantiate TransactionManager with mock DataSource
        transactionManager = new TransactionManager(mockDataSource);
    }

    @Test
    public void testUpdateMasterLog() throws SQLException {
        // Execute the method to be tested
        boolean result = transactionManager.updateMasterLog(1, "Credit", 500, sourceMock, destinationMock);

        // Verify that the correct methods were called
        verify(mockConnection).prepareStatement(anyString());

        // Capture the PreparedStatement argument
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockConnection).prepareStatement(stringArgumentCaptor.capture());
        String sqlQuery = stringArgumentCaptor.getValue();

        // Confirm that the correct SQL query was prepared
        Assert.assertEquals("INSERT INTO TransactionMaster (transactionId, transactionType, amount, sourceAccount, destinationAccount) VALUES (?, ?, ?, ?, ?)", 
        		sqlQuery);

        // Execute the captured PreparedStatement
        PreparedStatement capturedPreparedStatement = mockConnection.prepareStatement(sqlQuery);

        // Verify that the correct values were set on the PreparedStatement
        verify(capturedPreparedStatement).setInt(1, 1);
        verify(capturedPreparedStatement).setString(2, "Credit");
        verify(capturedPreparedStatement).setInt(3, 500);
        verify(capturedPreparedStatement).setString(4, "sourceAccountNumber");
        verify(capturedPreparedStatement).setString(5, "destinationAccountNumber");
        verify(capturedPreparedStatement).executeUpdate();

        // Assert the result
        Assert.assertTrue(result); // Assuming that true is returned when the update is successful
    }
}
