package com.apy_si.CBSUtil;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.apy_si.dao.DatabaseMaster;
import com.apy_si.pojo.APY_MASTER_TRANSACTIONS;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MainFlowAPY {

	private final DatabaseMaster databaseMaster;
	private final Rural_CBSUtilAPY rural_CBSUtilAPY;

	@Async
	public CompletableFuture<String> completableFuture(String frequency, String bank) {

		boolean isDone = false;
		Logger logger = LogManagementService.getLoggerByBankCode(bank);

		logger.info("Program Started");

		logger.info("Starting transaction for bank: " + bank);

		List<APY_MASTER_TRANSACTIONS> list = databaseMaster.getApyMasterTransactions(bank, frequency);

		if (!list.isEmpty()) {

			for (APY_MASTER_TRANSACTIONS apy_MASTER_TRANSACTIONS : list) {
				logger.info("Pran:-" + apy_MASTER_TRANSACTIONS.getPRAN_NO() + " SI_DATE:-"
						+ apy_MASTER_TRANSACTIONS.getSI_DATE() + " AND PAID_UPTO_DATE:-"
						+ apy_MASTER_TRANSACTIONS.getPAID_UPTO_DATE());

				APY_MASTER_TRANSACTIONS APY_PRAN = databaseMaster.getRevisedPenaltyCount(apy_MASTER_TRANSACTIONS);
				isDone = rural_CBSUtilAPY.prepareCBSString(APY_PRAN, frequency);
				if (isDone) {
					logger.info("APY SI FOR PRAN:" + apy_MASTER_TRANSACTIONS.getPRAN_NO() + " is Completed.");
				} else {
					logger.info("APY SI FOR PRAN:" + apy_MASTER_TRANSACTIONS.getPRAN_NO() + " is Failed.");
				}
			}

		}


		logger.info("Program completed for Frequency:" + frequency + " and Bank:" + bank);

//		}

		return CompletableFuture.completedFuture("COmpleted");
	}



}
