package com.apy_si.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.apy_si.CBSUtil.DateMaster;
import com.apy_si.entity.APY_MASTER_TRANSACTIONS;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApyDataReader {

	private final DataSource dataSource;

	public JdbcCursorItemReader<APY_MASTER_TRANSACTIONS> cursorItemReader(String bank,String frequency){
		
		return new JdbcCursorItemReaderBuilder<APY_MASTER_TRANSACTIONS>()
				.dataSource(dataSource)
				.name("APYReader")
				.sql(this.getTheQuery(bank,frequency))
				.rowMapper(new APYRowMapper())
				.build();
			
	}

	
	private String getTheQuery(String bank,String frequency) {
		String sql;
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
		
		return sql;
	}
	
	
	
	private class APYRowMapper implements RowMapper<APY_MASTER_TRANSACTIONS> {

		@Override
		public APY_MASTER_TRANSACTIONS mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			// TODO Auto-generated method stub

			APY_MASTER_TRANSACTIONS apy_MASTER_TRANSACTIONS = new APY_MASTER_TRANSACTIONS();
			
			apy_MASTER_TRANSACTIONS.setTXN_ID(resultSet.getString("TXN_ID"));
			apy_MASTER_TRANSACTIONS.setBRANCH_CODE(resultSet.getString("BRANCH_CODE"));
			apy_MASTER_TRANSACTIONS.setCUSTOMER_ACC_NUMBER(resultSet.getString("CUSTOMER_ACC_NUMBER"));
			apy_MASTER_TRANSACTIONS.setCONTRIBUTION_AMT(resultSet.getString("CONTRIBUTION_AMT"));
			apy_MASTER_TRANSACTIONS.setOVERDUEAMOUNT(resultSet.getString("OVERDUEAMOUNT"));
			apy_MASTER_TRANSACTIONS.setNARRATIVE(resultSet.getString("NARRATIVE"));
			apy_MASTER_TRANSACTIONS.setBANK_CODE(resultSet.getString("BANK_CODE"));
			apy_MASTER_TRANSACTIONS.setTELLER_ID(resultSet.getString("TELLER_ID"));
			apy_MASTER_TRANSACTIONS.setTRAN_NUMBER(resultSet.getString("TRAN_NUMBER"));
			apy_MASTER_TRANSACTIONS.setSI_DATE(resultSet.getString("SI_DATE"));
			apy_MASTER_TRANSACTIONS.setSUPERVISORID(resultSet.getString("SUPERVISORID"));
			apy_MASTER_TRANSACTIONS.setCOMPANY_ACC_NUMBER(resultSet.getString("COMPANY_ACC_NUMBER"));
			apy_MASTER_TRANSACTIONS.setPENALITY_CHARGE(resultSet.getString("PENALITY_CHARGE"));
			apy_MASTER_TRANSACTIONS.setPENALITY_INST_COUNT(resultSet.getString("PENALITY_INST_COUNT"));
			apy_MASTER_TRANSACTIONS.setNO_OF_INST_RECD(resultSet.getString("NO_OF_INST_RECD"));
			apy_MASTER_TRANSACTIONS.setCOMMISSION_AMT(resultSet.getString("COMMISSION_AMT"));
			apy_MASTER_TRANSACTIONS.setPAID_UPTO_DATE(resultSet.getString("PAID_UPTO_DATE"));
			apy_MASTER_TRANSACTIONS.setCURR_STATUS(resultSet.getString("POLICY_STATUS"));
			apy_MASTER_TRANSACTIONS.setPENALITY_INST_COUNT(resultSet.getString("PENALITY_INST_COUNT"));
			apy_MASTER_TRANSACTIONS.setCIF_CODE(resultSet.getString("CIF_CODE"));
			apy_MASTER_TRANSACTIONS.setPRAN_NO(resultSet.getString("PRAN_NO"));
			apy_MASTER_TRANSACTIONS.setFREQUENCY(resultSet.getString("FREQUENCY"));
			apy_MASTER_TRANSACTIONS.setDOB(resultSet.getString("DOB"));
			apy_MASTER_TRANSACTIONS.setPENALTY_COUNT_UPDATED_ON(resultSet.getString("PENALTY_COUNT_UPDATED_ON"));
			return apy_MASTER_TRANSACTIONS;
			
		}

	}
}
