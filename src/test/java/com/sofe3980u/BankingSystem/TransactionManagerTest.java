package com.sofe3980u.BankingSystem;

import org.junit.*;
import static org.mockito.Mockito.*;

import java.sql.DriverManager;

public class TransactionManagerTest {
	private Account sourceMock;
	private Account destinationMock;
	private TransactionManager transactionManager;
	private DriverManager dataSource;
	
	@Before
	public void setup() {
		sourceMock = mock(Account.class);
		destinationMock = mock(Account.class);
		transactionManager = new TransactionManager();
	}
	
	@Test
	public void testupdateMasterLog() {
		
	}
}
