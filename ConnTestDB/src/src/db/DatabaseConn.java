package src.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import oracle.jdbc.pool.OracleDataSource;

public class DatabaseConn {
	
	private final static Logger logger = Logger.getLogger(DatabaseConn.class);


	public static Connection getConnection() {
		Connection connection = null;
		OracleDataSource oracleDataSource = null;
		boolean connObj = false;
		int dbcheckCounter = 0;
//		logger.info("Inside get getConnection()  ");
		do {
			try {
//				DBURL=jdbc:oracle:thin:@10.45.37.23:1523:sbilifeuat
//						DBUSERNAME=SBILIFEUATDB
//						DBPASSWORD=SBILIFEUATDB_123
				connObj = false;
				oracleDataSource = new OracleDataSource();
				oracleDataSource.setURL("jdbc:oracle:thin:@10.43.1.211:1552:pmidb");
				oracleDataSource.setUser("bancslink");
				oracleDataSource.setPassword("bancslink_123");
				connection = oracleDataSource.getConnection();
				
			} catch (Exception e) {
			if (connection == null) {
					dbcheckCounter++;
					connObj = true;
					e.printStackTrace();
				}
			}
		} while (connObj);	
		

		return connection;
	
	}
	
	public static String insertIntoContribution( String frequency) {
		
		APY_MASTER_TRANSACTIONS apy_MASTER_TRANSACTIONS=new APY_MASTER_TRANSACTIONS();
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			String sql = "Insert into APY_CONTRIBUTION_TEST (BANK_CODE,BRANCH_CODE,SCHEME_CODE,ACCOUNT_NO,TRANSACTION_DATE,JOURNAL_NO,PRAN,TRANSACTION_TYPE,\r\n"
					+ "OVER_DUE,TOTAL_CONTRIBUTION,CONTRIBUTION_TYPE,CONTRIBUTION_MONTH,CONTRIBUTION_YEAR,TELLER_NO,CIF_CODE,LAST_MODIFIED,ERROR_MESSAGE)\r\n"
					+ "values ('SHUBHAM','SHUBHAM','APY','77048633084','14/08/2024','002924442','SHUBHAM','SHUBHAM','0','46','C','08','2024','0999230','27040877687',\r\n"
					+ "to_timestamp('14-AUG-24 05.26.21.206000000 AM','DD-MON-RR HH.MI.SSXFF AM'),null);";
			connection = DatabaseConn.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			// preparedStatement.setString(1, "id");
//			preparedStatement.setString(1, apy_MASTER_TRANSACTIONS
//					.getBANK_CODE());
//			preparedStatement.setString(2, apy_MASTER_TRANSACTIONS
//					.getBRANCH_CODE());
//			preparedStatement.setString(3, "APY");
//			preparedStatement.setString(4, apy_MASTER_TRANSACTIONS
//					.getCUSTOMER_ACC_NUMBER());
//			preparedStatement.setString(5, "14/08/2024");
//			preparedStatement.setString(6, apy_MASTER_TRANSACTIONS
//					.getJOURNAL_NUMBER());
//			preparedStatement.setString(7, apy_MASTER_TRANSACTIONS
//					.getPRAN_NO());
//			preparedStatement.setString(8, apy_MASTER_TRANSACTIONS
//					.getOutputType());
//			preparedStatement.setString(9, apy_MASTER_TRANSACTIONS
//					.getOVERDUEAMOUNT());
//			preparedStatement.setString(10, apy_MASTER_TRANSACTIONS
//					.getCONTRIBUTION_AMT());
//			if (frequency.equalsIgnoreCase("M"))
//				preparedStatement.setString(11, "C");
//			else if (frequency.equalsIgnoreCase("Q"))
//				preparedStatement.setString(11, "Q");
//			else if (frequency.equalsIgnoreCase("H"))
//				preparedStatement.setString(11, "H");
//			else
//				preparedStatement.setString(11, "C");
//			preparedStatement.setString(12, apy_MASTER_TRANSACTIONS
//					.getCONTRIBUTION_MONTH());
//			preparedStatement.setString(13, apy_MASTER_TRANSACTIONS
//					.getCONTRIBUTION_YEAR());
//			preparedStatement.setString(14, apy_MASTER_TRANSACTIONS
//					.getTELLER_ID());
//			preparedStatement.setString(15, apy_MASTER_TRANSACTIONS
//					.getCIF_CODE());
//			preparedStatement.setTimestamp(16, new java.sql.Timestamp(
//					System.currentTimeMillis()));
//			preparedStatement.setString(17, apy_MASTER_TRANSACTIONS
//					.getErrorMessage());

			int executeUpdate = preparedStatement.executeUpdate();
			
			if (executeUpdate>0) {
				
				logger.debug("Data is inserted");
				return "Inserted";
			}
			logger.debug("Insert Query-->INSERT INTO   APY_CONTRIBUTION_TEST (BANK_CODE, BRANCH_CODE, SCHEME_CODE, ACCOUNT_NO, TRANSACTION_DATE, JOURNAL_NO, PRAN, TRANSACTION_TYPE," +
					" OVER_DUE, TOTAL_CONTRIBUTION, CONTRIBUTION_TYPE, CONTRIBUTION_MONTH, CONTRIBUTION_YEAR, TELLER_NO, CIF_CODE," +
					" LAST_MODIFIED, ERROR_MESSAGE) values ("+ apy_MASTER_TRANSACTIONS
					.getBANK_CODE()+","+apy_MASTER_TRANSACTIONS
					.getBRANCH_CODE()+",APY,"+apy_MASTER_TRANSACTIONS
					.getCUSTOMER_ACC_NUMBER()+",14/08/2024,"+apy_MASTER_TRANSACTIONS
					.getJOURNAL_NUMBER()+","+apy_MASTER_TRANSACTIONS
					.getPRAN_NO()+","+apy_MASTER_TRANSACTIONS
					.getOutputType()+","+apy_MASTER_TRANSACTIONS
					.getOVERDUEAMOUNT()+","+apy_MASTER_TRANSACTIONS
					.getCONTRIBUTION_AMT()+","+frequency+","+apy_MASTER_TRANSACTIONS
					.getCONTRIBUTION_MONTH()+","+apy_MASTER_TRANSACTIONS
					.getCONTRIBUTION_YEAR()+","+apy_MASTER_TRANSACTIONS
					.getTELLER_ID()+","+apy_MASTER_TRANSACTIONS
					.getCIF_CODE()+","+new java.sql.Timestamp(
							System.currentTimeMillis())+","+apy_MASTER_TRANSACTIONS
							.getErrorMessage()+")");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return "";
	}
	
	public static void main(String[] args) {
		logger.debug(DatabaseConn.getConnection());
	 
	 logger.debug(DatabaseConn.insertIntoContribution("M"));
	}

}
