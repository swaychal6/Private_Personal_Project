package com.apy_si.CBSUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Rural_Util {

	public static Logger logger = LogManager.getLogger(Rural_Util.class);

	public String strGetDate() {
		String strDate = new String();
		strDate = "";
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		strDate = dateFormat.format(date);
		return strDate;

	}

	public String extract(String a_strInpXMLString, String a_strInpTagName, int a_iRestrictLength) {
		String strExtract = "";
		try {
			if (a_strInpXMLString.indexOf("<" + a_strInpTagName + ">") != -1) {
				strExtract = a_strInpXMLString.substring(
						a_strInpXMLString.indexOf("<" + a_strInpTagName + ">") + a_strInpTagName.length() + 2,
						a_strInpXMLString.indexOf("</" + a_strInpTagName + ">"));
				if ((a_iRestrictLength != -1) && (strExtract.length() > a_iRestrictLength)) {
					strExtract = strExtract.substring(0, a_iRestrictLength);
				}
			}
		} catch (Exception oExcep) {
			oExcep.printStackTrace();
		}

		return strExtract;

	}

	public String paddin(String data, int length, String padChar, String side) {
		String padData = null;
		String tempStr = "";
		try {
			if (data.equalsIgnoreCase(null)) {
				data = " ";
			}
			int diffLength = length - data.length();
			if (diffLength > 0) {
				for (int i = 0; i < diffLength; ++i) {
					tempStr = tempStr + padChar;
				}
			}
			if (side.equalsIgnoreCase("left")) {
				padData = tempStr + data;
			}
			if (side.equalsIgnoreCase("right")) {
				padData = data + tempStr;
			}
		}

		catch (Exception e) {
			logger.error("Exception =:", e);
		}
		return padData;
	}

	public String checkNull(String str) {
		if (str == null) {
			return new String(" ");
		}
		return str;
	}

	public int getPenaltyAmount(int amount) {
		int penalityAmount = (amount % 100 == 0) ? amount / 100 : amount / 100 + 1;
		return penalityAmount;
		// DatabaseMaster databaseMaster = new DatabaseMaster();
		// Map<Integer, Integer> penelityMap =
		// databaseMaster.getPenalityDetails();
		// int penalityAmount = 0;
		// if (amount <= 100)
		// penalityAmount = penelityMap.get(100);
		// else if (amount > 100 && amount <= 200)
		// penalityAmount = penelityMap.get(200);
		// else if (amount > 200 && amount <= 300)
		// penalityAmount = penelityMap.get(300);
		// else if (amount > 300 && amount <= 400)
		// penalityAmount = penelityMap.get(400);
		// else if (amount > 400 && amount <= 500)
		// penalityAmount = penelityMap.get(500);
		// else if (amount > 500 && amount <= 600)
		// penalityAmount = penelityMap.get(600);
		// else if (amount > 600 && amount <= 700)
		// penalityAmount = penelityMap.get(700);
		// else if (amount > 700 && amount <= 800)
		// penalityAmount = penelityMap.get(800);
		// else if (amount > 800 && amount <= 900)
		// penalityAmount = penelityMap.get(900);
		// else if (amount > 900 && amount <= 1000)
		// penalityAmount = penelityMap.get(1000);
		// else if (amount > 1000 && amount <= 1100)
		// penalityAmount = penelityMap.get(1100);
		// else if (amount > 1100 && amount <= 1200)
		// penalityAmount = penelityMap.get(1200);
		// else if (amount > 1200 && amount <= 1300)
		// penalityAmount = penelityMap.get(1300);
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		//
		// else if (amount > 1400 && amount <= 1500)
		// penalityAmount = penelityMap.get(1400);
		//
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		//
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		//
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		//
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		//
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		//
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		//
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		// else if (amount > 1300 && amount <= 1400)
		// penalityAmount = penelityMap.get(1400);
		//
		// return penalityAmount;
		//
	}

}
