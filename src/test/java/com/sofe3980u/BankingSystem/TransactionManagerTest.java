package com.sofe3980u.BankingSystem;

import org.junit.*;
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

        // Configure mock DataSource
        when(mockDataSource.getConnection()).thenReturn(mockConnection);

        // Configure mock Connection
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Configure mock PreparedStatement
        when(mockPreparedStatement.executeUpdate()).thenReturn(1); // Assuming one row is affected
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
        verify(mockPreparedStatement).setInt(1, 1);
        verify(mockPreparedStatement).setString(2, "Credit");
        verify(mockPreparedStatement).setInt(3, 500);
        verify(mockPreparedStatement).setString(4, "sourceAccountNumber");
        verify(mockPreparedStatement).setString(5, "destinationAccountNumber");
        verify(mockPreparedStatement).executeUpdate();

        // Assert the result
        Assert.assertTrue(result); // Assuming that true is returned when the update is successful
    }
}