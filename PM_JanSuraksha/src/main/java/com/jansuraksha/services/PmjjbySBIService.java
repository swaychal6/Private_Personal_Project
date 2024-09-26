package com.jansuraksha.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.jansuraksha.daoImpl.CommonRepoDaoImpl;
import com.jansuraksha.dto.enrollmentJansurakshaDto.EnrollmentRequestDto;
import com.jansuraksha.entity.JansurakshaTransactions;
import com.jansuraksha.entity.SBI_PMJJBY_TRANSACTIONS;

@Service
//@AllArgsConstructor
public class PmjjbySBIService extends CommonRepoDaoImpl<SBI_PMJJBY_TRANSACTIONS> {

	private final JanSurakshaServices<SBI_PMJJBY_TRANSACTIONS> janSurakshaServices;

	private static final Logger logger = LogManager.getLogger(PmjjbySBIService.class);

	public PmjjbySBIService(JdbcTemplate jdbcTemplate,
			JanSurakshaServices<SBI_PMJJBY_TRANSACTIONS> janSurakshaServices) {
		super(jdbcTemplate);
		this.janSurakshaServices = janSurakshaServices;
		// TODO Auto-generated constructor stub
	}

	public void prepareEnrollmentRequestSBI(String bankCode) {

		List<EnrollmentRequestDto> sbiTransactionDetails = super.getAllTransactionDetails(bankCode);

		sbiTransactionDetails.stream().forEach(t -> {
			try {
				JansurakshaTransactions jns = janSurakshaServices.callEnrollmentAndUpdateDetails(t, bankCode);

				if (jns.getCoi_document_code() != null && jns.getStatus() == 200) {
					logger.info("ENrollment and Account status are updates for Customer:" + jns.getCustomer_acc_number()
							+ " cif:" + jns.getCif_code() + " for scheme:" + jns.getScheme());
				}

			} catch (Exception e) {
				logger.error("");
			}
		});

	}


}
