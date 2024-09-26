package com.apy_si.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.apy_si.CBSUtil.DateMaster;
import com.apy_si.entity.APY_CONTRIBUTION;
import com.apy_si.entity.APY_MASTER_TRANSACTIONS;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ApyItemWriter implements ItemWriter<APY_MASTER_TRANSACTIONS> {

	private final static Logger logger = LogManager.getLogger(ApyItemWriter.class);

	private final JdbcTemplate jdbcTemplate;

	@Override
	public void write(List<? extends APY_MASTER_TRANSACTIONS> chunk) throws Exception {
		// TODO Auto-generated method stub

		String newFreq = null;
		for (APY_MASTER_TRANSACTIONS apy_MASTER_TRANSACTIONS : chunk) {

			try {

				int i = jdbcTemplate.update(
						"UPDATE APY_MASTER_TRANSACTIONS set PENALITY_CHARGE =?, PENALITY_INST_COUNT= ?, NO_OF_INST_RECD=?,"
								+ " PAID_UPTO_DATE=?, POLICY_STATUS =?,PENALTY_COUNT_UPDATED_ON=? where CUSTOMER_ACC_NUMBER = ? and CIF_CODE =?",
						new Object[] { apy_MASTER_TRANSACTIONS.getPENALITY_CHARGE(),
								apy_MASTER_TRANSACTIONS.getPENALITY_INST_COUNT(),
								apy_MASTER_TRANSACTIONS.getNO_OF_INST_RECD(),
								apy_MASTER_TRANSACTIONS.getPAID_UPTO_DATE(), apy_MASTER_TRANSACTIONS.getCURR_STATUS(),
								apy_MASTER_TRANSACTIONS.getPENALTY_COUNT_UPDATED_ON(),
								apy_MASTER_TRANSACTIONS.getCUSTOMER_ACC_NUMBER(),
								apy_MASTER_TRANSACTIONS.getCIF_CODE() });

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
				logger.info(i + "  row Updated");
				
				if(i>0) {
//					PreparedStatement preparedStatement = null;
//					Connection connection = null;
					try {
						String sql = "INSERT INTO  APY_CONTRIBUTION (BANK_CODE, BRANCH_CODE, SCHEME_CODE, ACCOUNT_NO, TRANSACTION_DATE,"
								+ " JOURNAL_NO, PRAN, TRANSACTION_TYPE, OVER_DUE, TOTAL_CONTRIBUTION, CONTRIBUTION_TYPE, CONTRIBUTION_MONTH,"
								+ " CONTRIBUTION_YEAR, TELLER_NO, CIF_CODE, LAST_MODIFIED, ERROR_MESSAGE) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//						connection = getConnection();

						if (apy_MASTER_TRANSACTIONS.getFREQUENCY().equalsIgnoreCase("M")) {
							newFreq = "C";
						} else {
							newFreq = apy_MASTER_TRANSACTIONS.getFREQUENCY();
						}


						int insert = jdbcTemplate.update(sql,
								new Object[] { apy_MASTER_TRANSACTIONS.getBANK_CODE(), apy_MASTER_TRANSACTIONS.getBRANCH_CODE(),
										"APY", apy_MASTER_TRANSACTIONS.getCUSTOMER_ACC_NUMBER(), DateMaster.getCurrentDate(),
										apy_MASTER_TRANSACTIONS.getJOURNAL_NUMBER(), apy_MASTER_TRANSACTIONS.getPRAN_NO(),
										apy_MASTER_TRANSACTIONS.getOutputType(), apy_MASTER_TRANSACTIONS.getOVERDUEAMOUNT(),
										apy_MASTER_TRANSACTIONS.getCONTRIBUTION_AMT(),
										newFreq, apy_MASTER_TRANSACTIONS.getCONTRIBUTION_MONTH(),
										apy_MASTER_TRANSACTIONS.getCONTRIBUTION_YEAR(), apy_MASTER_TRANSACTIONS.getTELLER_ID(),
										apy_MASTER_TRANSACTIONS.getCIF_CODE(), new java.sql.Timestamp(System.currentTimeMillis()),
										apy_MASTER_TRANSACTIONS.getErrorMessage() });

						if(insert>0) {
							logger.info("Data Inserted Successfully");
						}
					
						logger.info(
								"Insert Query-->INSERT INTO   APY_CONTRIBUTION (BANK_CODE, BRANCH_CODE, SCHEME_CODE, ACCOUNT_NO, TRANSACTION_DATE, JOURNAL_NO, PRAN, TRANSACTION_TYPE,"
										+ " OVER_DUE, TOTAL_CONTRIBUTION, CONTRIBUTION_TYPE, CONTRIBUTION_MONTH, CONTRIBUTION_YEAR, TELLER_NO, CIF_CODE,"
										+ " LAST_MODIFIED, ERROR_MESSAGE) values (" + apy_MASTER_TRANSACTIONS.getBANK_CODE() + ","
										+ apy_MASTER_TRANSACTIONS.getBRANCH_CODE() + ",APY,"
										+ apy_MASTER_TRANSACTIONS.getCUSTOMER_ACC_NUMBER() + "," + DateMaster.getCurrentDate() + ","
										+ apy_MASTER_TRANSACTIONS.getJOURNAL_NUMBER() + "," + apy_MASTER_TRANSACTIONS.getPRAN_NO()
										+ "," + apy_MASTER_TRANSACTIONS.getOutputType() + ","
										+ apy_MASTER_TRANSACTIONS.getOVERDUEAMOUNT() + ","
										+ apy_MASTER_TRANSACTIONS.getCONTRIBUTION_AMT() + "," + newFreq + ","
										+ apy_MASTER_TRANSACTIONS.getCONTRIBUTION_MONTH() + ","
										+ apy_MASTER_TRANSACTIONS.getCONTRIBUTION_YEAR() + ","
										+ apy_MASTER_TRANSACTIONS.getTELLER_ID() + "," + apy_MASTER_TRANSACTIONS.getCIF_CODE() + ","
										+ new java.sql.Timestamp(System.currentTimeMillis()) + ","
										+ apy_MASTER_TRANSACTIONS.getErrorMessage() + ")");
					} catch (Exception e) {
						logger.error("Exception got in the insert apy con query =:", e);
					}
				}

			} catch (Exception e) {
				logger.info("Error While Updating Records !!!!");
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

				logger.error("Exception =:", e);
			}
		}
	}

//	@Override
//	public void write(List<? extends APY_MASTER_TRANSACTIONS> arg0) throws Exception {
//		// TODO Auto-generated method stub
//		
//	}

}
