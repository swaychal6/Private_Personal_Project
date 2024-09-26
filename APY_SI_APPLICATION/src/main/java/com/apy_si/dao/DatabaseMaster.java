package com.apy_si.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.apy_si.CBSUtil.DateMaster;
import com.apy_si.CBSUtil.LogManagementService;
import com.apy_si.pojo.APYRowMapper;
import com.apy_si.pojo.APY_MASTER_TRANSACTIONS;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DatabaseMaster {

	private final static Logger classLogger = LogManager.getLogger(DatabaseMaster.class);

	private final JdbcTemplate jdbcTemplate;

//	public static Connection getConnection() {
//		Connection connection = null;
////		Properties properties = new Properties();
//		OracleDataSource oracleDataSource = null;
//		boolean connObj = false;
//		int dbcheckCounter = 0;
//		do {
//			try {
//
//				if (dbcheckCounter > 1) {
//					logger.info("Thread is Sleeping for 30sec");
//					logger.info("DB Check Counter -" + dbcheckCounter);
//					Thread.sleep(30000);
//
//				}
//
//				connObj = false;
////				properties.load(PropertyMaster.getDatabaseProperty());
//				oracleDataSource = new OracleDataSource();
////				oracleDataSource.setURL(properties.getProperty("DBURL"));
//				oracleDataSource.setURL(PropertyMaster.getDbProperties().getProperty("DBURL").trim());
//				oracleDataSource.setUser(PropertyMaster.getDbProperties().getProperty("DBUSERNAME").trim());
//				oracleDataSource.setPassword(PropertyMaster.getDbProperties().getProperty("DBPASSWORD").trim());
//				connection = oracleDataSource.getConnection();
//
//			} catch (Exception e) {
//				if (connection == null) {
//					dbcheckCounter++;
//					logger.error("connection is null =:", e);
//					connObj = true;
//					logger.error("Exception =:", e);
//				}
//			}
//		} while (connObj);
//		return connection;
//	}

	public List<APY_MASTER_TRANSACTIONS> getApyMasterTransactions(String bank, String frequency) {
		Logger logger = LogManagementService.getLoggerByBankCode(bank);
		String sql = null;
		List<APY_MASTER_TRANSACTIONS> queryForList = new ArrayList<>();
		logger.info("SI Frequency is - " + frequency);
		if (DateMaster.getMaxDayfromMonth()) {
			sql = "Select  * from APY_MASTER_TRANSACTIONS  WHERE  BANK_CODE=" + bank + " AND FREQUENCY='" + frequency
					+ "' AND upper(policy_status)='ACTIVE' AND (PAID_UPTO_DATE <> '0' AND trunc(to_date(PAID_UPTO_DATE,'MM/YYYY')) < trunc(to_date(to_char(sysdate,'MM/YYYY'), 'MM/YYYY')) "
					+ " and trunc(TO_DATE(SI_DATE,'DD/MM/YYYY')) <= trunc(sysdate)"
					+ " and trunc(sysdate) < trunc(to_date(DOB, 'DD/MM/YYYY')+ interval '60' year))"
					+ "	 OR (BANK_CODE=" + bank + "   AND FREQUENCY='" + frequency
					+ "' AND upper(policy_status)='ACTIVE' AND TO_NUMBER(PENALITY_INST_COUNT) > 0 "
					+ " and trunc(sysdate) < trunc(to_date(DOB, 'DD/MM/YYYY')+ interval '60' year) "
					+ " and trunc(TO_DATE(SI_DATE,'DD/MM/YYYY')) <= trunc(sysdate))";
		} else {
			sql = "Select  * from APY_MASTER_TRANSACTIONS  WHERE  BANK_CODE=" + bank + " AND FREQUENCY='" + frequency
					+ "' AND upper(policy_status)='ACTIVE' AND (PAID_UPTO_DATE <> '0' AND trunc(to_date(PAID_UPTO_DATE,'MM/YYYY')) < trunc(to_date(to_char(sysdate,'MM/YYYY'), 'MM/YYYY')) "
					+ " and trunc(TO_DATE(SI_DATE,'DD/MM/YYYY')) <= trunc(sysdate)"
					+ " and to_number(to_char(trunc(TO_DATE(SI_DATE,'DD/MM/YYYY')),'DD')) <= to_number(to_char(trunc(sysdate),'DD'))"
					+ " and trunc(sysdate) < trunc(to_date(DOB, 'DD/MM/YYYY')+ interval '60' year))"
					+ "	 OR (BANK_CODE=" + bank + "   AND FREQUENCY='" + frequency
					+ "' AND upper(policy_status)='ACTIVE' AND TO_NUMBER(PENALITY_INST_COUNT) > 0 "
					+ " and trunc(sysdate) < trunc(to_date(DOB, 'DD/MM/YYYY')+ interval '60' year) "
					+ " and trunc(TO_DATE(SI_DATE,'DD/MM/YYYY')) <= trunc(sysdate)"
					+ " and to_number(to_char(trunc(TO_DATE(SI_DATE,'DD/MM/YYYY')),'DD')) <= to_number(to_char(trunc(sysdate),'DD')))";
		}

		try {
			queryForList = jdbcTemplate.query(sql, new APYRowMapper());
			logger.info(queryForList.size() + ":is the Data Size for frequecy:"+frequency);
		} catch (Exception e) {
			classLogger
					.error("Getting error while taking the list of apy si customers from main table:" + e.getMessage());
		}
		return queryForList;
	}

//	public static boolean getNSDLHOLIDAY(String date1) {
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//		Connection connection = null;
//		boolean flag = false;
//		try {
//			connection = getConnection();
//			String sql = "select * from APY_NSDL_HOLIDAY where DATE_1=?";
//			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setString(1, date1);
//			resultSet = preparedStatement.executeQuery();
//			if (resultSet.next())
//				flag = true;
//		} catch (Exception e) {
//			logger.error("Exception =:", e);
//			logger.info("Database Failure");
//			System.exit(0);
//		}
//		return flag;
//	}

	public boolean insertIntoContribution(APY_MASTER_TRANSACTIONS apy_MASTER_TRANSACTIONS, String frequency) {

		Logger logger = LogManagementService.getLoggerByBankCode(apy_MASTER_TRANSACTIONS.getBANK_CODE());
		boolean isDone = false;
		String newFreq = null;
		int i = 0;
//		PreparedStatement preparedStatement = null;
//		Connection connection = null;
		do {
			try {
				String sql = "INSERT INTO  APY_CONTRIBUTION (BANK_CODE, BRANCH_CODE, SCHEME_CODE, ACCOUNT_NO, TRANSACTION_DATE,"
						+ " JOURNAL_NO, PRAN, TRANSACTION_TYPE, OVER_DUE, TOTAL_CONTRIBUTION, CONTRIBUTION_TYPE, CONTRIBUTION_MONTH,"
						+ " CONTRIBUTION_YEAR, TELLER_NO, CIF_CODE, LAST_MODIFIED, ERROR_MESSAGE) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//			connection = getConnection();

				if (frequency.equalsIgnoreCase("M")) {
					newFreq = "C";
				} else {
					newFreq = frequency;
				}

				i = jdbcTemplate.update(sql,
						new Object[] { apy_MASTER_TRANSACTIONS.getBANK_CODE(), apy_MASTER_TRANSACTIONS.getBRANCH_CODE(),
								"APY", apy_MASTER_TRANSACTIONS.getCUSTOMER_ACC_NUMBER(), DateMaster.getCurrentDate(),
								apy_MASTER_TRANSACTIONS.getJOURNAL_NUMBER(), apy_MASTER_TRANSACTIONS.getPRAN_NO(),
								apy_MASTER_TRANSACTIONS.getOutputType(), apy_MASTER_TRANSACTIONS.getOVERDUEAMOUNT(),
								apy_MASTER_TRANSACTIONS.getCONTRIBUTION_AMT(), newFreq,
								apy_MASTER_TRANSACTIONS.getCONTRIBUTION_MONTH(),
								apy_MASTER_TRANSACTIONS.getCONTRIBUTION_YEAR(), apy_MASTER_TRANSACTIONS.getTELLER_ID(),
								apy_MASTER_TRANSACTIONS.getCIF_CODE(),
								new java.sql.Timestamp(System.currentTimeMillis()),
								apy_MASTER_TRANSACTIONS.getErrorMessage() });

				if (i > 0) {
					isDone = true;
					logger.info(i + ": row is inserted");
				}
				logger.info(
						"Insert Query-->INSERT INTO   APY_CONTRIBUTION (BANK_CODE, BRANCH_CODE, SCHEME_CODE, ACCOUNT_NO, TRANSACTION_DATE, JOURNAL_NO, PRAN, TRANSACTION_TYPE,"
								+ " OVER_DUE, TOTAL_CONTRIBUTION, CONTRIBUTION_TYPE, CONTRIBUTION_MONTH, CONTRIBUTION_YEAR, TELLER_NO, CIF_CODE,"
								+ " LAST_MODIFIED, ERROR_MESSAGE) values (" + apy_MASTER_TRANSACTIONS.getBANK_CODE()
								+ "," + apy_MASTER_TRANSACTIONS.getBRANCH_CODE() + ",APY,"
								+ apy_MASTER_TRANSACTIONS.getCUSTOMER_ACC_NUMBER() + "," + DateMaster.getCurrentDate()
								+ "," + apy_MASTER_TRANSACTIONS.getJOURNAL_NUMBER() + ","
								+ apy_MASTER_TRANSACTIONS.getPRAN_NO() + "," + apy_MASTER_TRANSACTIONS.getOutputType()
								+ "," + apy_MASTER_TRANSACTIONS.getOVERDUEAMOUNT() + ","
								+ apy_MASTER_TRANSACTIONS.getCONTRIBUTION_AMT() + "," + frequency + ","
								+ apy_MASTER_TRANSACTIONS.getCONTRIBUTION_MONTH() + ","
								+ apy_MASTER_TRANSACTIONS.getCONTRIBUTION_YEAR() + ","
								+ apy_MASTER_TRANSACTIONS.getTELLER_ID() + "," + apy_MASTER_TRANSACTIONS.getCIF_CODE()
								+ "," + new java.sql.Timestamp(System.currentTimeMillis()) + ","
								+ apy_MASTER_TRANSACTIONS.getErrorMessage() + ")");
			} catch (Exception e) {
				classLogger.error("Exception got in the insert apy con query for"
						+ " Bank_Code: "+apy_MASTER_TRANSACTIONS.getBANK_CODE()+" -->"+ e);
				classLogger.error(
						"Insert Query-->INSERT INTO   APY_CONTRIBUTION (BANK_CODE, BRANCH_CODE, SCHEME_CODE, ACCOUNT_NO, TRANSACTION_DATE, JOURNAL_NO, PRAN, TRANSACTION_TYPE,"
								+ " OVER_DUE, TOTAL_CONTRIBUTION, CONTRIBUTION_TYPE, CONTRIBUTION_MONTH, CONTRIBUTION_YEAR, TELLER_NO, CIF_CODE,"
								+ " LAST_MODIFIED, ERROR_MESSAGE) values (" + apy_MASTER_TRANSACTIONS.getBANK_CODE()
								+ "," + apy_MASTER_TRANSACTIONS.getBRANCH_CODE() + ",APY,"
								+ apy_MASTER_TRANSACTIONS.getCUSTOMER_ACC_NUMBER() + "," + DateMaster.getCurrentDate()
								+ "," + apy_MASTER_TRANSACTIONS.getJOURNAL_NUMBER() + ","
								+ apy_MASTER_TRANSACTIONS.getPRAN_NO() + "," + apy_MASTER_TRANSACTIONS.getOutputType()
								+ "," + apy_MASTER_TRANSACTIONS.getOVERDUEAMOUNT() + ","
								+ apy_MASTER_TRANSACTIONS.getCONTRIBUTION_AMT() + "," + frequency + ","
								+ apy_MASTER_TRANSACTIONS.getCONTRIBUTION_MONTH() + ","
								+ apy_MASTER_TRANSACTIONS.getCONTRIBUTION_YEAR() + ","
								+ apy_MASTER_TRANSACTIONS.getTELLER_ID() + "," + apy_MASTER_TRANSACTIONS.getCIF_CODE()
								+ "," + new java.sql.Timestamp(System.currentTimeMillis()) + ","
								+ apy_MASTER_TRANSACTIONS.getErrorMessage() + ")");
			}

		} while (i == 0);

		return isDone;
	}

	public boolean updateApyMasterTransactions(APY_MASTER_TRANSACTIONS apy_MASTER_TRANSACTIONS) {
		boolean isDone = false;
		Logger logger = LogManagementService.getLoggerByBankCode(apy_MASTER_TRANSACTIONS.getBANK_CODE());
		int i = 0;
//		PreparedStatement preparedStatement = null;
//		Connection connection = null;
		do {
			try {

				String sql = "UPDATE APY_MASTER_TRANSACTIONS set PENALITY_CHARGE =?, PENALITY_INST_COUNT= ?, NO_OF_INST_RECD=?, PAID_UPTO_DATE=?, POLICY_STATUS =?,PENALTY_COUNT_UPDATED_ON=? where CUSTOMER_ACC_NUMBER = ? and CIF_CODE =?";

				i = jdbcTemplate.update(sql, new Object[] { apy_MASTER_TRANSACTIONS.getPENALITY_CHARGE(),
						apy_MASTER_TRANSACTIONS.getPENALITY_INST_COUNT(), apy_MASTER_TRANSACTIONS.getNO_OF_INST_RECD(),
						apy_MASTER_TRANSACTIONS.getPAID_UPTO_DATE(), apy_MASTER_TRANSACTIONS.getCURR_STATUS(),
						apy_MASTER_TRANSACTIONS.getPENALTY_COUNT_UPDATED_ON(),
						apy_MASTER_TRANSACTIONS.getCUSTOMER_ACC_NUMBER(), apy_MASTER_TRANSACTIONS.getCIF_CODE() });

				if (i > 0) {
					isDone = true;
				}
				
				logger.info(i + ": row Updated");
				logger.info("Update Query -->UPDATE APY_MASTER_TRANSACTIONS set PENALITY_CHARGE ="
						+ apy_MASTER_TRANSACTIONS.getPENALITY_CHARGE() + "" + ", PENALITY_INST_COUNT="
						+ apy_MASTER_TRANSACTIONS.getPENALITY_INST_COUNT() + ", NO_OF_INST_RECD="
						+ apy_MASTER_TRANSACTIONS.getNO_OF_INST_RECD() + ", PAID_UPTO_DATE="
						+ apy_MASTER_TRANSACTIONS.getPAID_UPTO_DATE() + ", POLICY_STATUS ="
						+ apy_MASTER_TRANSACTIONS.getCURR_STATUS() + " PENALTY_COUNT_UPDATED_ON="
						+ apy_MASTER_TRANSACTIONS.getPENALTY_COUNT_UPDATED_ON() + " where CUSTOMER_ACC_NUMBER ="
						+ apy_MASTER_TRANSACTIONS.getCUSTOMER_ACC_NUMBER() + " and CIF_CODE ="
						+ apy_MASTER_TRANSACTIONS.getCIF_CODE() + " and BANK_CODE="
						+ apy_MASTER_TRANSACTIONS.getBANK_CODE() + " PRAN_NO=" + apy_MASTER_TRANSACTIONS.getPRAN_NO());

			} catch (Exception e) {
				logger.info(i + ": row Updated");
				classLogger.error("Error While Updating Records !!!!  for  Bank_Code:"+apy_MASTER_TRANSACTIONS.getBANK_CODE());
				classLogger.error("Update Query -->UPDATE APY_MASTER_TRANSACTIONS set PENALITY_CHARGE ="
						+ apy_MASTER_TRANSACTIONS.getPENALITY_CHARGE() + "" + ", PENALITY_INST_COUNT="
						+ apy_MASTER_TRANSACTIONS.getPENALITY_INST_COUNT() + ", NO_OF_INST_RECD="
						+ apy_MASTER_TRANSACTIONS.getNO_OF_INST_RECD() + ", PAID_UPTO_DATE="
						+ apy_MASTER_TRANSACTIONS.getPAID_UPTO_DATE() + ", POLICY_STATUS ="
						+ apy_MASTER_TRANSACTIONS.getCURR_STATUS() + " PENALTY_COUNT_UPDATED_ON="
						+ apy_MASTER_TRANSACTIONS.getPENALTY_COUNT_UPDATED_ON() + " where CUSTOMER_ACC_NUMBER ="
						+ apy_MASTER_TRANSACTIONS.getCUSTOMER_ACC_NUMBER() + " and CIF_CODE ="
						+ apy_MASTER_TRANSACTIONS.getCIF_CODE() + " and BANK_CODE="
						+ apy_MASTER_TRANSACTIONS.getBANK_CODE() + " PRAN_NO=" + apy_MASTER_TRANSACTIONS.getPRAN_NO());

				classLogger.error("Exception =:", e);
			}
		} while (i == 0);

		return isDone;
	}

//	public Map<Integer, Integer> getPenalityDetails() {
//		Map<Integer, Integer> penalityMap = new HashMap<Integer, Integer>();
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//		Connection connection = null;
//		try {
//			connection = getConnection();
//			String sql = "SELECT * FROM APY_PENALITY_MASTER";
//			preparedStatement = connection.prepareStatement(sql);
//			resultSet = preparedStatement.executeQuery();
//			while (resultSet.next()) {
//				penalityMap.put(resultSet.getInt("AMOUNT"), resultSet.getInt("PENALITY_AMOUNT"));
//			}
//		} catch (Exception e) {
//			logger.error("Exception =:", e);
//		}
//		return penalityMap;
//	}

//	public boolean updateResponseApyMaster(List<ResponseFile> list) {
//		boolean isDone = false;
//		for (ResponseFile responseFile : list) {
//			PreparedStatement preparedStatement = null;
//			Connection connection = null;
//			try {
//				String sql = "UPDATE APY_MASTER_TRANSACTIONS SET POLICY_STATUS ='APPROVED' WHERE PRAN_NO =? AND CUSTOMER_ACC_NUMBER = ? and POLICY_STATUS = 'PENDING'";
//				connection = getConnection();
//				preparedStatement = connection.prepareStatement(sql);
//				preparedStatement.setString(1, responseFile.getPran());
//				preparedStatement.setString(2, responseFile.getBankAccountNumber());
//				int i = preparedStatement.executeUpdate();
//				logger.info(i + "  row Updated");
//				isDone = true;
//			} catch (Exception e) {
//				logger.error("Exception =:", e);
//			} finally {
//				try {
//					if (preparedStatement != null)
//						preparedStatement.close();
//					if (connection != null)
//						connection.close();
//				} catch (SQLException e) {
//					logger.error("Exception =:", e);
//				}
//			}
//		}
//		return isDone;
//	}

//	public boolean updateResponsePranMaster(List<ResponseFile> list) {
//		boolean isDone = false;
//		for (ResponseFile responseFile : list) {
//			PreparedStatement preparedStatement = null;
//			Connection connection = null;
//			try {
//				String sql = "UPDATE APY_PRAN_NOS SET STATUS ='ACTIVE', ACTIVE_DATE = ? WHERE PRAN_NO =? and STATUS = 'PENDING'";
//				connection = getConnection();
//				preparedStatement = connection.prepareStatement(sql);
//				preparedStatement.setString(1, DateMaster.getCurrentDate());
//				preparedStatement.setString(2, responseFile.getPran());
//				int i = preparedStatement.executeUpdate();
//				logger.info(i + "  row Updated");
//				isDone = true;
//			} catch (Exception e) {
//				logger.error("Exception =:", e);
//			} finally {
//				try {
//					if (preparedStatement != null)
//						preparedStatement.close();
//					if (connection != null)
//						connection.close();
//				} catch (SQLException e) {
//					logger.error("Exception =:", e);
//				}
//			}
//		}
//		return isDone;
//	}

//	public List<String> getBanks() {
//		ArrayList<String> list = new ArrayList<>();
//		try {
//			
////			list.add("101");
////			list.add("1664");
////			list.add("102");
////			list.add("61");
////			list.add("96");
////			list.add("99");
////			list.add("2213");
////			list.add("27");
////			list.add("374");
////			list.add("59");
////			list.add("108");
////			list.add("1645");
////			list.add("1653");
////			list.add("1833");
////			list.add("62");
////			list.add("251");
////			list.add("1833");
//			list.add("95");
////			list.add("1859");
//
//		} catch (Exception e) {
//
//			logger.info("Error in getBanks()");
//			logger.error(e);
//		}
//		return list;
//	}

	public APY_MASTER_TRANSACTIONS getRevisedPenaltyCount(APY_MASTER_TRANSACTIONS apy_MASTER_TRANSACTIONS) {

		String paidUptoDate = apy_MASTER_TRANSACTIONS.getPAID_UPTO_DATE();
		int year = 0;
		int Penalty_inst = 0;
		int new_penalty = 0;
		int cur_year = Calendar.getInstance().get(Calendar.YEAR);
		int cur_month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		String strarray[] = paidUptoDate.split("/");
		int PUD_month = Integer.parseInt(strarray[0]);
		int PUD_year = Integer.parseInt(strarray[1]);
		year = cur_year - PUD_year;
		if (year == 0) {
			Penalty_inst = (cur_month - PUD_month) - 1;
		} else {
			Penalty_inst = ((year - 1) * 12) + ((12 - PUD_month) - 1) + (cur_month);

		}
		new_penalty = Penalty_inst;
//		logger.info("Pran:" + apy_MASTER_TRANSACTIONS.getPRAN_NO());
		String strpreviousMonthMaxDay = DateMaster.getPreviousMonthMaxDay();
		apy_MASTER_TRANSACTIONS.setPENALITY_INST_COUNT(Integer.toString(new_penalty));
		apy_MASTER_TRANSACTIONS.setPENALTY_COUNT_UPDATED_ON(strpreviousMonthMaxDay);

		return apy_MASTER_TRANSACTIONS;
	}

//	public void insertCsvFile(String filename) {
//		Connection connection = null;
//		Statement stmt = null;
//		try {
//			connection = getConnection();
//			stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//			System.out.println("Inserting APY_CONTRIBUTION Data Values");
//			String query = " LOAD DATA LOCAL INFILE '" + filename
//					+ "' INTO TABLE APY_CONTRIBUTION FIELDS TERMINATED BY '|' (BANK_CODE, BRANCH_CODE, SCHEME_CODE, ACCOUNT_NO, TRANSACTION_DATE,"
//					+ " JOURNAL_NO, PRAN, TRANSACTION_TYPE, OVER_DUE, TOTAL_CONTRIBUTION, CONTRIBUTION_TYPE, CONTRIBUTION_MONTH,"
//					+ " CONTRIBUTION_YEAR, TELLER_NO, CIF_CODE,ERROR_MESSAGE)";
//			System.out.println(query);
//			stmt.executeUpdate(query);
//
//		} catch (Exception e) {
//			logger.error(e);
//		} finally {
//			try {
//				if (connection != null) {
//					connection.close();
//				}
//				if (stmt != null) {
//					stmt.close();
//				}
//			} catch (SQLException e) {
//				logger.error(e);
//			}
//
//		}
//	}

}
