package com.jansuraksha.utils;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jansuraksha.PmJanSurakshaApplication;
import com.jansuraksha.entity.LIC_PMJJBY_TRANSACTIONS;
import com.jansuraksha.entity.NIA_PMSBY_TRANSACTIONS;
import com.jansuraksha.entity.NIC_PMSBY_TRANSACTIONS;
import com.jansuraksha.entity.SBI_PMJJBY_TRANSACTIONS;
import com.jansuraksha.entity.UNITED_PMSBY_TRANSACTIONS;

public class CommonUtility {

	private static final Logger logger = LogManager.getLogger(CommonUtility.class);

	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int LENGTH = 8;

	private final static String generateCode() {
		final SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder(LENGTH);
		for (int i = 0; i < LENGTH; i++) {
			int index = random.nextInt(CHARACTERS.length());
			sb.append(CHARACTERS.charAt(index));
		}
		return sb.toString();
	}

	public static String generateJansurakshaToken(String bankCode, String channelSource) {
		String bankShortName = PmJanSurakshaApplication.bankDetails.stream()
				.filter(t -> bankCode.equalsIgnoreCase(t.getBANK_CODE())).map(t -> t.getBANKSHORTNAME()).findFirst()
				.get();
		String janSurakshaToken = "JNS" + bankShortName + channelSource + getTodaysDate() + generateCode() + "";
		return janSurakshaToken;
	}

	public static String getTodaysDate() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmSSS");
		return now.format(formatter);
	}

	public static long calculateAge(String age) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate birthDate = LocalDate.parse(age, formatter);
		LocalDate currentDate = LocalDate.now();
		return ChronoUnit.YEARS.between(birthDate, currentDate);
	}

	public static String dobFormatter(String dob) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(dob, formatter).toString();
	}

	public static double getPREMIUM_AMOUNT(String scheme) {

		double PREMIUM_AMOUNT = 0;

		if (scheme.equals("PMJJBY")) {
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
			String month = String.valueOf(sdf.format(now)).substring(0, 2);
			int mm = Integer.parseInt(month);

			if ((mm == 6 || mm == 7 || mm == 8)) {
				PREMIUM_AMOUNT = 436;
				logger.info("Q1,PREMIUM_AMOUNT is:" + PREMIUM_AMOUNT);
			} else if ((mm == 9 || mm == 10 || mm == 11)) {

				PREMIUM_AMOUNT = 342;
				logger.info("Q2,PREMIUM_AMOUNT amount is:" + PREMIUM_AMOUNT);
			} else if ((mm == 12 || mm == 01 || mm == 02)) {

				PREMIUM_AMOUNT = 228;
				logger.info("Q3,PREMIUM_AMOUNT amount is:" + PREMIUM_AMOUNT);
			} else if ((mm == 03 || mm == 04 || mm == 05)) {

				PREMIUM_AMOUNT = 114;
				logger.info("Q4,PREMIUM_AMOUNT is:" + PREMIUM_AMOUNT);

			}
		} else {
			PREMIUM_AMOUNT = 20;
		}

		return PREMIUM_AMOUNT;
	}
	
	public static Object getTableByScheme(String scheme){
		
		if("SBI".equals(scheme)) {
			return SBI_PMJJBY_TRANSACTIONS.class;
		}
		if("LIC".equals(scheme)) {
			return LIC_PMJJBY_TRANSACTIONS.class;
		}
		if("NIC".equals(scheme)) {
			return NIC_PMSBY_TRANSACTIONS.class;
		}
		if("NIA".equals(scheme)) {
			return NIA_PMSBY_TRANSACTIONS.class;
		}
		if("UNITED".equals(scheme)) {
			return UNITED_PMSBY_TRANSACTIONS.class;
		}
		
		return null;
	}
}
