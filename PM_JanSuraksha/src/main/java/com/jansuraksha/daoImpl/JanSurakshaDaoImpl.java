package com.jansuraksha.daoImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Repository;

import com.jansuraksha.bean.CommonBeans;
import com.jansuraksha.dao.JanSurakshaTransactionRepository;
import com.jansuraksha.dto.enrollmentJansurakshaDto.EnrollmentDetailsResponse;
import com.jansuraksha.dto.enrollmentJansurakshaDto.EnrollmentRequestDto;
import com.jansuraksha.dto.updatetranJansurakshaDto.UpdateTransactionDetailsResponse;
import com.jansuraksha.entity.JansurakshaTransactions;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JanSurakshaDaoImpl<T> implements JanSurakshaTransactionRepository {

	private static final Logger logger = LogManager.getLogger(JansurakshaTransactions.class);

	private T id;

	protected final JdbcTemplate jdbcTemplate;
//	protected final JdbcTemplate jdbcTemplate;

	@Retryable()
	@Override
	public int saveJansurakshaDetails(JansurakshaTransactions jns) {
		// TODO Auto-generated method stub
		int insert = 0;
		try {
			String sql = "Insert into JansurakshaTransactions values(?,?,?,?,?,?,?)";
			insert = jdbcTemplate.update(sql,
					new Object[] { jns.getJansurakshaUrnNumber(), jns.getCustomer_acc_number(), jns.getCif_code(),
							jns.getBank_code(), jns.getScheme(), jns.getCoi_document_code(), jns.getTimestamp() });

		} catch (Exception e) {
			logger.error("Error got while inserting the Jansuraksha Data for scheme:" + jns.getScheme()
					+ " for bank_code=" + jns.getBank_code() + "  and cif:" + jns.getCif_code() + " and customer:"
					+ jns.getCustomer_acc_number());
		}

		return insert;
	}

	protected JansurakshaTransactions mappedToJansurakshaTransactions(String urn,String customer_acc_number, String cif_code,
			String bankCode, UpdateTransactionDetailsResponse response) {

		return 
		JansurakshaTransactions.builder()
		.bank_code(bankCode)
		.cif_code(cif_code)
		.customer_acc_number(customer_acc_number)
		.scheme(id.getClass().getSimpleName())
		.coi_document_code(response.getCoi().getDocument())
		.timestamp(response.getTimestamp())
		.JansurakshaUrnNumber(urn)
		.status(response.getStatus())
		.Transaction_status(response.getMessage())
		.build();
	}
	
}
