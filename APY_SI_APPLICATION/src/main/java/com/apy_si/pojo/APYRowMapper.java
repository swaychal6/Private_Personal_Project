package com.apy_si.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class APYRowMapper implements RowMapper<APY_MASTER_TRANSACTIONS> {

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
