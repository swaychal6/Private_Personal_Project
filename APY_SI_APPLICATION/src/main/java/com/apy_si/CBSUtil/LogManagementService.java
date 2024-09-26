package com.apy_si.CBSUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogManagementService {

	private static final Logger logger_95 = LogManager.getLogger("95_JRGB");
	private static final Logger logger_96 = LogManager.getLogger("96_MBGB");
	private static final Logger logger_99 = LogManager.getLogger("99_UKGB");
	private static final Logger logger_102 = LogManager.getLogger("102_KVGB");
	private static final Logger logger_101 = LogManager.getLogger("101_ARGB");
	private static final Logger logger_1645 = LogManager.getLogger("1645_UTGB");
	private static final Logger logger_1653 = LogManager.getLogger("1653_CHGB");
	private static final Logger logger_1833 = LogManager.getLogger("1833_ELGB");
	private static final Logger logger_61 = LogManager.getLogger("61_ACRB");
	private static final Logger logger_62 = LogManager.getLogger("62_MZGB");
	private static final Logger logger_1859 = LogManager.getLogger("1859_APGVB");
	private static final Logger logger_1664 = LogManager.getLogger("1664_TGB");
	private static final Logger logger_374 = LogManager.getLogger("374_MGRB");
	private static final Logger logger_59 = LogManager.getLogger("59_NGRB");
	private static final Logger logger_108 = LogManager.getLogger("108_BOM");
	private static final Logger logger_27 = LogManager.getLogger("27_RMGB");
	private static final Logger logger_2213 = LogManager.getLogger("2213_SRGB");
	private static final Logger logger_251 = LogManager.getLogger("251_SPCB");

	public static Logger getLoggerByBankCode(String bankCode) {
		switch (bankCode) {
		case "95":
			return logger_95;
		case "96":
			return logger_96;
		case "99":
			return logger_99;
		case "102":
			return logger_102;
		case "101":
			return logger_101;
		case "1645":
			return logger_1645;
		case "1653":
			return logger_1653;
		case "1833":
			return logger_1833;
		case "61":
			return logger_61;
		case "62":
			return logger_62;
		case "1859":
			return logger_1859;
		case "1664":
			return logger_1664;
		case "374":
			return logger_374;
		case "59":
			return logger_59;
		case "108":
			return logger_108;
		case "27":
			return logger_27;
		case "2213":
			return logger_2213;
		case "251":
			return logger_251;
		default:
			throw new IllegalArgumentException("Invalid bank code: " + bankCode);
		}

	}
}
