package com.apy_si.CBSUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

import com.apy_si.config.ApplicationConfig;
import com.apy_si.entity.APY_MASTER_TRANSACTIONS;

import lombok.RequiredArgsConstructor;

@Service
//@RequiredArgsConstructor
public class Rural_CBSUtilAPY implements ItemProcessor<APY_MASTER_TRANSACTIONS, APY_MASTER_TRANSACTIONS> {

	public static Logger logger = LogManager.getLogger(Rural_CBSUtilAPY.class);
	boolean isDone = false;
//	private final DatabaseMaster databaseMaster;

//	private static Properties properties = null;
//	private StringBuilder sbrRequest = new StringBuilder();
	private Rural_Util utils = new Rural_Util();
	String strTxnDate = "";
	String strInstDateFinal = "";
	List<APY_MASTER_TRANSACTIONS> apy_MASTER_TRANSACTIONSs = null;
	// APY_MASTER_TRANSACTIONS apy_MASTER_TRANSACTIONS2 = null;
	Rural_Util rural_Util = new Rural_Util();
	char[] chars = null;
	int iBufferSize = 1536;
	Socket socket = null;
	String dateStart = null;
	String dateStop = null;
	String strNarrative = null;
	String strSupID = null;
	String strSupDateTime = null;
	String strTxnAmount1 = null;
	String strPaise = null;
	String strAmountRecieved = null;

	public APY_MASTER_TRANSACTIONS prepareCBSString(APY_MASTER_TRANSACTIONS apy_MASTER_TRANSACTIONS,
			String siFrequency) {
		List<APY_MASTER_TRANSACTIONS> apyList = new ArrayList<>();
		StringWriter stack = new StringWriter();

		String newPaidDate = null;
		String transactionsType = null;
		boolean isPenaltyIncrement = false;
		String finalCBSRequest = null;

		apy_MASTER_TRANSACTIONSs = new ArrayList<APY_MASTER_TRANSACTIONS>();

		int frequencyFactory = this.ferquencyFactor(siFrequency);

		int pendingInstallmentsCount = Integer.parseInt(apy_MASTER_TRANSACTIONS.getPENALITY_INST_COUNT());

		String paidUptoDate = apy_MASTER_TRANSACTIONS.getPAID_UPTO_DATE();
		int month = 0;
		int n1 = DateMaster.getCurrentNumericMonth();
		if ((paidUptoDate != null)) {
			month = Integer.parseInt(paidUptoDate.substring(0, 2));
		} else {
			month = n1 - 1;
			paidUptoDate = String.valueOf(month) + "/" + DateMaster.getCurrentYear();
		}
		finalCBSRequest = null;
		int monthsDifference = 0;
		if ((paidUptoDate.substring(paidUptoDate.length() - 4)).equals(DateMaster.getCurrentYear()))
			monthsDifference = n1 - month;
		else {
			dateStart = paidUptoDate;
			dateStop = DateMaster.getCurrentDate();
			Date d1 = null;
			Date d2 = null;
			try {
				d1 = (new SimpleDateFormat("MM/yyyy")).parse(dateStart);
				d2 = (new SimpleDateFormat("dd/MM/yyyy")).parse(dateStop);
			} catch (ParseException e) {
				e.printStackTrace(new PrintWriter(stack));
				logger.info("ParseException while formatting date : " + stack.toString());
				logger.error("Exception =:", e);
			}
			final Calendar d11 = Calendar.getInstance();
			d11.setTime(d1);
			final Calendar d22 = Calendar.getInstance();
			d22.setTime(d2);
			int diff = (d22.get(Calendar.YEAR) - d11.get(Calendar.YEAR)) * 12 + d22.get(Calendar.MONTH)
					- d11.get(Calendar.MONTH);
			monthsDifference = diff;
		}
		monthsDifference /= 1;

		int temp2 = 0;
		for (int i = 1; ((i <= monthsDifference)
				&& ((DateMaster.getMonthAndYearfromString(apy_MASTER_TRANSACTIONS.getPAID_UPTO_DATE()).compareTo(
						DateMaster.getMonthAndYearfromString(DateMaster.getCurrentMonthAndYear()))) < 0)); i++) {

			strAmountRecieved = apy_MASTER_TRANSACTIONS.getCONTRIBUTION_AMT();
			apy_MASTER_TRANSACTIONS.setCOMPANY_ACC_NUMBER(
					ApplicationConfig.getProperty("APY_AC" + apy_MASTER_TRANSACTIONS.getBANK_CODE().trim()));
			apy_MASTER_TRANSACTIONS
					.setTELLER_ID(ApplicationConfig.getProperty("TellerNo" + apy_MASTER_TRANSACTIONS.getBANK_CODE()));
			strNarrative = apy_MASTER_TRANSACTIONS.getNARRATIVE();
			strSupID = apy_MASTER_TRANSACTIONS.getSUPERVISORID();
			strSupDateTime = apy_MASTER_TRANSACTIONS.getSUPDATETIME();
			strNarrative = strNarrative + " " + (apy_MASTER_TRANSACTIONS.getCUSTOMER_ACC_NUMBER()) + " "
					+ DateMaster.getCurrentMonth(
							(Integer.parseInt((apy_MASTER_TRANSACTIONS.getPAID_UPTO_DATE()).substring(0, 2))),
							siFrequency);
			strTxnAmount1 = strAmountRecieved;
			strTxnAmount1 = strTxnAmount1.replaceAll(",", "");
			strPaise = "";
			if (strTxnAmount1.contains(".")) {
				strPaise = strTxnAmount1.substring(strTxnAmount1.indexOf(".") + 1);
			} else {
				strPaise = "000";
			}
			try {
				if (strAmountRecieved.isEmpty())
					strAmountRecieved = "";
			} catch (NullPointerException npe) {
				strAmountRecieved = "";
			}
			try {
				if (strSupID.isEmpty())
					strSupID = "";
			} catch (NullPointerException npe) {
				strSupID = "";
			}
			try {
				if (strSupDateTime.isEmpty())
					strSupDateTime = "";
			} catch (NullPointerException npe) {
				strSupDateTime = "";
			}
			String strChar1 = "";

			finalCBSRequest = this.prepareCBSStringApy(apy_MASTER_TRANSACTIONS).toString();
			logger.info("APY CBS Request ====>" + finalCBSRequest);

			String a_sReturn = this.cbsResponse(apy_MASTER_TRANSACTIONS, finalCBSRequest);

			logger.info("APY CBS Response -->" + a_sReturn);

			apy_MASTER_TRANSACTIONS.setRequestString(finalCBSRequest);
			apy_MASTER_TRANSACTIONS.setResponseString(a_sReturn.toString());
			apy_MASTER_TRANSACTIONS.setJOURNAL_NUMBER((a_sReturn.toString()).substring(63, 72));

			String outputType = (a_sReturn.toString()).substring(133, 135);
			logger.info("outputType is:" + outputType);
			apy_MASTER_TRANSACTIONS.setOutputType(outputType);

			if (outputType.equalsIgnoreCase("08")) {
				newPaidDate = DateMaster.getNextPaidUptoDate((apy_MASTER_TRANSACTIONS.getPAID_UPTO_DATE()),
						frequencyFactory);
				apy_MASTER_TRANSACTIONS.setPAID_UPTO_DATE(newPaidDate);
				apy_MASTER_TRANSACTIONS.setPENALITY_INST_COUNT(String
						.valueOf(this.pendingInstallmentCount(outputType, siFrequency, pendingInstallmentsCount)));
				temp2 = Integer.parseInt(newPaidDate.substring(0, 2));
				apy_MASTER_TRANSACTIONS.setCONTRIBUTION_MONTH(DateMaster.getCurrentMonth(--temp2, siFrequency));
				apy_MASTER_TRANSACTIONS.setOutputType("08");
				transactionsType = apy_MASTER_TRANSACTIONS.getOutputType();
				apy_MASTER_TRANSACTIONS.setCONTRIBUTION_YEAR(
						DateMaster.getContributionYear(newPaidDate, siFrequency, transactionsType));

				logger.info(apy_MASTER_TRANSACTIONS.getPAID_UPTO_DATE());
//					logger.info(apy_MASTER_TRANSACTIONS.getCONTRIBUTION_MONTH());
				apy_MASTER_TRANSACTIONS.setCURR_STATUS("ACTIVE");

				apyList.add(apy_MASTER_TRANSACTIONS);

//					isDone = databaseMaster.updateApyMasterTransactions(apy_MASTER_TRANSACTIONS);
//					if (isDone) {
//						logger.info("Database Updated Successfully");
//					} else {
//						logger.info("Database Update Failure");
//						isDone = false;
//					}

//					isDone = databaseMaster.insertIntoContribution(apy_MASTER_TRANSACTIONS, siFrequency);
//					if (isDone) {
//						logger.info("Records inserted into Contribution Table.");
//					} else {
//						logger.info("Records inserted into Contribution Table.");
//						isDone = false;
//					}

			} else if (outputType.equalsIgnoreCase("0001")) {
				apy_MASTER_TRANSACTIONS.setErrorMessage("Bank's CBS is not reachable");
				apy_MASTER_TRANSACTIONS.setErrorCode("0001");
				apy_MASTER_TRANSACTIONS.setOutputType("20");
				transactionsType = apy_MASTER_TRANSACTIONS.getOutputType();
				apy_MASTER_TRANSACTIONS.setCONTRIBUTION_MONTH(DateMaster.getCurrentMonth(
						(Integer.parseInt((apy_MASTER_TRANSACTIONS.getPAID_UPTO_DATE()).substring(0, 2))),
						siFrequency));
				apy_MASTER_TRANSACTIONS.setCONTRIBUTION_YEAR(DateMaster.getContributionYear(
						(apy_MASTER_TRANSACTIONS.getPAID_UPTO_DATE()), siFrequency, transactionsType));

				if (DateMaster.getMaxDayfromMonth()) {
					isPenaltyIncrement = true;
					if (isPenaltyIncrement) {
						int dateComparator = 1;
						String strlastPenaltyUpdated = null;
						try {
							strlastPenaltyUpdated = apy_MASTER_TRANSACTIONS.getPENALTY_COUNT_UPDATED_ON();
						} catch (NullPointerException np) {
							strlastPenaltyUpdated = null;
						}
						if (strlastPenaltyUpdated != null)
							dateComparator = DateMaster
									.getDatefromString(apy_MASTER_TRANSACTIONS.getPENALTY_COUNT_UPDATED_ON())
									.compareTo(DateMaster.getDatefromString(DateMaster.getCurrentDate()));
						if (dateComparator != 0) {
							pendingInstallmentsCount++;
							apy_MASTER_TRANSACTIONS.setPENALITY_INST_COUNT(String.valueOf(pendingInstallmentsCount));
							apy_MASTER_TRANSACTIONS.setPENALTY_COUNT_UPDATED_ON(DateMaster.getCurrentDate());
						}
					}
				}

				apyList.add(apy_MASTER_TRANSACTIONS);
//					isDone = databaseMaster.updateApyMasterTransactions(apy_MASTER_TRANSACTIONS);
//					if (isDone) {
//						logger.info("Database Updated Successfully");
//
//					} else {
//						logger.info("Database Update Failure");
//						isDone = false;
//					}

//					isDone = databaseMaster.insertIntoContribution(apy_MASTER_TRANSACTIONS, siFrequency);
//					if (isDone) {
//						logger.info("Records inserted into Contribution Table.");
//					} else {
//						logger.info("Records inserted into Contribution Table.");
//						isDone = false;
//					}

//					logger.info("breaking the loop due to cbs for PRAN:-"+apy_MASTER_TRANSACTIONS.getPRAN_NO());
////					System.out.println("breaking the loop due to cbs error for PRAN:-"+apy_MASTER_TRANSACTIONS.getPRAN_NO());
				break;

			}

			else {
				apy_MASTER_TRANSACTIONS.setErrorMessage(
						(a_sReturn.toString()).substring(139, 143) + " " + (a_sReturn.toString()).substring(147, 170));
				apy_MASTER_TRANSACTIONS.setErrorCode((a_sReturn.toString()).substring(139, 143));
				apy_MASTER_TRANSACTIONS.setOutputType("20");
				transactionsType = apy_MASTER_TRANSACTIONS.getOutputType();
				apy_MASTER_TRANSACTIONS.setCONTRIBUTION_MONTH(DateMaster.getCurrentMonth(
						(Integer.parseInt((apy_MASTER_TRANSACTIONS.getPAID_UPTO_DATE()).substring(0, 2))),
						siFrequency));
				apy_MASTER_TRANSACTIONS.setCONTRIBUTION_YEAR(DateMaster.getContributionYear(
						(apy_MASTER_TRANSACTIONS.getPAID_UPTO_DATE()), siFrequency, transactionsType));

				if (DateMaster.getMaxDayfromMonth()) {
					isPenaltyIncrement = true;
					if (isPenaltyIncrement) {
						int dateComparator = 1;
						String strlastPenaltyUpdated = null;
						try {
							strlastPenaltyUpdated = apy_MASTER_TRANSACTIONS.getPENALTY_COUNT_UPDATED_ON();
						} catch (NullPointerException np) {
							strlastPenaltyUpdated = null;
						}
						if (strlastPenaltyUpdated != null)
							dateComparator = DateMaster
									.getDatefromString(apy_MASTER_TRANSACTIONS.getPENALTY_COUNT_UPDATED_ON())
									.compareTo(DateMaster.getDatefromString(DateMaster.getCurrentDate()));
						if (dateComparator != 0) {
							pendingInstallmentsCount++;
							apy_MASTER_TRANSACTIONS.setPENALITY_INST_COUNT(String.valueOf(pendingInstallmentsCount));
							apy_MASTER_TRANSACTIONS.setPENALTY_COUNT_UPDATED_ON(DateMaster.getCurrentDate());
						}
					}
				}

				apyList.add(apy_MASTER_TRANSACTIONS);
//					isDone = databaseMaster.updateApyMasterTransactions(apy_MASTER_TRANSACTIONS);
//					if (isDone) {
//						logger.info("Database Updated Successfully");
//					} else {
//						logger.info("Database Update Failure");
//						isDone = false;
//					}

//					isDone = databaseMaster.insertIntoContribution(apy_MASTER_TRANSACTIONS, siFrequency);
//					if (isDone) {
//						logger.info("Records inserted into Contribution Table.");
//					} else {
//						logger.info("Records failed to insert in Contribution Table.");
//						isDone = false;
//					}
//					logger.info("breaking the loop due to cbs for PRAN:-"+apy_MASTER_TRANSACTIONS.getPRAN_NO()+" CBS_ERROR:-"+(a_sReturn.toString()).substring(139, 143) + " "
//							+ (a_sReturn.toString()).substring(147, 170));
//					System.out.println(
//							"breaking the loop due to cbs error for PRAN:-" + apy_MASTER_TRANSACTIONS.getPRAN_NO());
				break;
			}

		}

//		if (!((DateMaster.getMonthAndYearfromString(apy_MASTER_TRANSACTIONS.getPAID_UPTO_DATE())
//				.compareTo(DateMaster.getMonthAndYearfromString(DateMaster.getCurrentMonthAndYear()))) < 0)) {
//			logger.info("ALL APY SI are cleared for Pran:" + apy_MASTER_TRANSACTIONS.getPRAN_NO());
//		}

//		System.gc();

		return apy_MASTER_TRANSACTIONS;

	}

	private int ferquencyFactor(String siFrequency) {
		if (siFrequency.equalsIgnoreCase("M"))
			return 1;
		else if (siFrequency.equalsIgnoreCase("Q"))
			return 3;
		else if (siFrequency.equalsIgnoreCase("H"))
			return 6;
		else
			return 1;
	}

	private int pendingInstallmentCount(String outputType, String siFrequency, int pendingInstallmentsCount) {
//		if (outputType.equalsIgnoreCase("08")) {
		if (pendingInstallmentsCount > 0 && siFrequency.equalsIgnoreCase("M"))
			return pendingInstallmentsCount--;

		if ((pendingInstallmentsCount >= 0 && (pendingInstallmentsCount <= 2)) && siFrequency.equalsIgnoreCase("Q")) {
			return pendingInstallmentsCount = 0;
		} else if ((pendingInstallmentsCount > 2) && siFrequency.equalsIgnoreCase("Q")) {
			return pendingInstallmentsCount = pendingInstallmentsCount - 3;
		}

		if ((pendingInstallmentsCount >= 0 && (pendingInstallmentsCount <= 5)) && siFrequency.equalsIgnoreCase("H")) {
			return pendingInstallmentsCount = 0;
		} else if ((pendingInstallmentsCount > 5) && siFrequency.equalsIgnoreCase("H")) {
			return pendingInstallmentsCount = pendingInstallmentsCount - 6;
		}
//		}
		
		return pendingInstallmentsCount;
	}

	@Override
	public APY_MASTER_TRANSACTIONS process(APY_MASTER_TRANSACTIONS item) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	private StringBuilder prepareCBSStringApy(APY_MASTER_TRANSACTIONS apy_MASTER_TRANSACTIONS) {

		StringBuilder sbrRequest = new StringBuilder();

		try {
			int pendingInstallmentsCount = Integer.parseInt(apy_MASTER_TRANSACTIONS.getPENALITY_INST_COUNT());
			String strChar1 = "";
			sbrRequest.setLength(0);
			// added by shubham
			sbrRequest.append(utils.paddin(strChar1, 1, " ", "left"));
			sbrRequest.append("1053");
			sbrRequest.append(utils.paddin(strChar1, 20, " ", "left"));
			sbrRequest.append(ApplicationConfig.getProperty("SegmentNo"));
			sbrRequest.append(utils.paddin(strChar1, 2, " ", "left"));
			sbrRequest.append(utils.paddin(strChar1, 4, "0", "left"));
			sbrRequest.append(utils.paddin(strChar1, 6, " ", "left"));
			sbrRequest.append(utils.paddin("3", 3, "0", "left"));
			sbrRequest.append(
					utils.paddin((ApplicationConfig.getProperty("BranchNo" + apy_MASTER_TRANSACTIONS.getBANK_CODE())),
							5, "0", "left"));
			sbrRequest.append(ApplicationConfig.getProperty("WorkstationNo"));
			sbrRequest.append(
					utils.paddin((ApplicationConfig.getProperty("TellerNo" + apy_MASTER_TRANSACTIONS.getBANK_CODE())),
							7, "0", "left"));
			sbrRequest.append(utils.paddin((apy_MASTER_TRANSACTIONS.getTRAN_NUMBER()), 6, "0", "left"));
			sbrRequest.append(ApplicationConfig.getProperty("JournalNo"));
			sbrRequest.append(utils.paddin(strChar1, 5, " ", "left"));
			sbrRequest.append(ApplicationConfig.getProperty("Filler5"));
			sbrRequest.append(utils.paddin(strChar1, 2, " ", "left"));
			sbrRequest.append(utils.paddin("", 1, " ", "left"));
			sbrRequest.append(utils.paddin(strChar1, 2, " ", "left"));
			sbrRequest.append(ApplicationConfig.getProperty("BATCH-TANDEM"));
			sbrRequest.append(utils.paddin(strChar1, 3, " ", "left"));
			sbrRequest.append(ApplicationConfig.getProperty("Flag7"));
			sbrRequest.append(utils.paddin(strSupID, 7, "0", "left"));
			sbrRequest.append(utils.paddin(strSupDateTime, 8, " ", "left"));
			sbrRequest.append(ApplicationConfig.getProperty("CheckerId"));
			sbrRequest.append(ApplicationConfig.getProperty("ParentBancsLinkJournalNo"));
			sbrRequest.append(ApplicationConfig.getProperty("CheckerId1"));
			sbrRequest.append(utils.paddin(strChar1, 8, "0", "left"));
			sbrRequest.append(utils.paddin((apy_MASTER_TRANSACTIONS.getCUSTOMER_ACC_NUMBER()), 17, "0", "left"));
			sbrRequest.append(utils.paddin(strChar1, 18, " ", "left"));
			int amount = Integer.parseInt(strTxnAmount1);
			int penalityAmount = 0;
			int totalPenality = 0;
			penalityAmount = rural_Util.getPenaltyAmount(amount);
			if ((apy_MASTER_TRANSACTIONS.getFREQUENCY().equalsIgnoreCase("M"))
					|| (apy_MASTER_TRANSACTIONS.getFREQUENCY().equalsIgnoreCase("Q"))
					|| (apy_MASTER_TRANSACTIONS.getFREQUENCY().equalsIgnoreCase("H"))) {
				totalPenality = penalityAmount * pendingInstallmentsCount;
				apy_MASTER_TRANSACTIONS.setOVERDUEAMOUNT(String.valueOf(totalPenality));
				amount = amount + (totalPenality);
			}
			strTxnAmount1 = String.valueOf(amount);
			sbrRequest.append(utils.paddin(strTxnAmount1, 14, "0", "left"));
			sbrRequest.append(utils.paddin(strPaise, 3, "0", "left"));
			sbrRequest.append("+");
			sbrRequest.append(utils.paddin(strChar1, 10, " ", "left"));
			sbrRequest.append("NF");
			sbrRequest.append(utils.paddin(
					(ApplicationConfig.getProperty("APY_AC" + apy_MASTER_TRANSACTIONS.getBANK_CODE().trim())), 17, "0",
					"left"));
			sbrRequest.append(utils.paddin(strChar1, 4, " ", "left"));
			sbrRequest.append(utils.paddin(strChar1, 4, "0", "left"));
			sbrRequest.append(utils.paddin(strChar1, 62, " ", "left"));
			sbrRequest.append("INR");
			sbrRequest.append(utils.paddin(strTxnAmount1, 14, "0", "left"));
			sbrRequest.append(utils.paddin(strPaise, 3, "0", "left"));
			sbrRequest.append("+");
			sbrRequest.append("INR");
			sbrRequest.append(utils.paddin(strTxnAmount1, 14, "0", "left"));
			sbrRequest.append(utils.paddin(strPaise, 3, "0", "left"));
			sbrRequest.append("+");
			sbrRequest.append(utils.paddin("0", 14, "0", "left"));
			sbrRequest.append(utils.paddin(strPaise, 3, "0", "right"));
			sbrRequest.append("+");
			sbrRequest.append(utils.paddin("0", 14, "0", "left"));
			sbrRequest.append(utils.paddin(strPaise, 3, "0", "right"));
			sbrRequest.append("+");
			sbrRequest.append("00");
			sbrRequest.append(utils.paddin(strChar1, 375, " ", "left"));
			sbrRequest.append(utils.paddin(strNarrative, 50, " ", "right"));
			sbrRequest.append(utils.paddin(strChar1, 132, " ", "left"));
			sbrRequest.append(utils.paddin(strChar1, 17, "0", "left"));
			sbrRequest.append(utils.paddin(strChar1, 96, " ", "left"));
			sbrRequest.append(utils.paddin(strChar1, 23, "0", "left"));
		} catch (Exception e) {
			logger.error("Error while creating the CBS request for PRAN:" + apy_MASTER_TRANSACTIONS.getPRAN_NO());
		}

		return sbrRequest;
	}

	private String cbsResponse(APY_MASTER_TRANSACTIONS apy_MASTER_TRANSACTIONS, String finalCBSRequest) {
		StringWriter stack = new StringWriter();
		StringBuilder a_sReturn = new StringBuilder("");
		PrintWriter pwout;
		BufferedReader brin;
		InputStreamReader ins;
		try {
			socket = new Socket(
					ApplicationConfig.getProperty("CBSIP" + apy_MASTER_TRANSACTIONS.getBANK_CODE().trim()).trim(),
					Integer.parseInt(ApplicationConfig
							.getProperty("CBSPORT" + apy_MASTER_TRANSACTIONS.getBANK_CODE().trim()).trim()));
			socket.setSoTimeout(50000);
			socket.setReuseAddress(true);
			ins = new InputStreamReader(socket.getInputStream());
			brin = new BufferedReader(ins);
			pwout = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
			pwout.print(finalCBSRequest);
//			pwout = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
//			pwout.print(finalCBSRequest);
			pwout.flush();
			a_sReturn.setLength(0);
			chars = new char[iBufferSize];
			int numRead = 0;
			while ((numRead = brin.read(chars)) > -1) {
				a_sReturn.append(String.valueOf(chars, 0, numRead));
				if (numRead < iBufferSize)
					break;
			}
//		
			a_sReturn.append(
					" 0208    0078            00000000000006003040000060999230001045000414663000045460200   00000000        000000000000000000000000000000080000 O.K.                 MGB-COLLECTION ACCOUNT-APY-NPS                      \r\n");
//			  a_sReturn.
//			 append(" 1113    0983            00000000000007003000010070999230001045000000405000045470200   00000000        00000000000000000000000000000001ERR 3670 00 WDL NOT ALLW FOR SB BELOW 0                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                \r\n");			
////			

			logger.info("APY CBS Response -->" + a_sReturn);

			return a_sReturn.toString();

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("getting error while getting the response from CBS: ?", e.getMessage());
			e.printStackTrace(new PrintWriter(stack));
			logger.info("In Bank's CBS is not reachable Exception: " + stack.toString());
			return "0001";
		} finally {
			try {
				if (socket != null)
					socket.close();
			} catch (Exception e) {
				logger.error("Exception -- ", e);

			}

		}
	}
}
