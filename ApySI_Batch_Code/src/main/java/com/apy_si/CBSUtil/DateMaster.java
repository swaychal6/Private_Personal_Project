package com.apy_si.CBSUtil;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateMaster {


	private static final Logger logger = LogManager.getLogger(DateMaster.class);

	public static String getDate() {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");
		String today = dateFormat.format(cal.getTime());
		return today;
	}

	public static String getDate1() {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today = dateFormat.format(cal.getTime());
		return today;
	}

	public static String getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String today = dateFormat.format(cal.getTime());
		return today;
	}

	public static String getCurrentMonthAndYear() {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
		String today = dateFormat.format(cal.getTime());
		return today;
	}

	public static String getCurrentDateINddFormat() {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd");
		String today = dateFormat.format(cal.getTime());
		return today;
	}

	public static String getContributionYear(String paidUptoDate, String siFrequency, String transactionsType) {
		String contributionYear = null;
		int inputMonth = Integer.parseInt(paidUptoDate.substring(0, 2));
		if (inputMonth == 12 && transactionsType.equalsIgnoreCase("20"))
			contributionYear = Integer
					.toString(Integer.parseInt(paidUptoDate.substring(paidUptoDate.length() - 4)) + 1);
		else
			contributionYear = paidUptoDate.substring(paidUptoDate.length() - 4);
		inputMonth = inputMonth % 12;
		if (!(siFrequency.equalsIgnoreCase("M"))) {
			if (!transactionsType.equalsIgnoreCase("20"))
				inputMonth -= 1;
			if (inputMonth >= 0 && inputMonth <= 2)
				contributionYear = Integer.toString(Integer.parseInt(contributionYear) - 1) + "-" + contributionYear;
			else if (inputMonth >= 3 && inputMonth <= 11)
				contributionYear = contributionYear + "-" + Integer.toString(Integer.parseInt(contributionYear) + 1);
			else if (inputMonth < 0)
				contributionYear = contributionYear + "-" + Integer.toString(Integer.parseInt(contributionYear) + 1);
		}
		return contributionYear;
	}

	public static String getCurrentMonth(int numericMonth, String siFrequency) {
		numericMonth = numericMonth % 12;
		Rural_Util utils = new Rural_Util();
		String contriMonth = null;
		if ((siFrequency.equalsIgnoreCase("Q"))) {
			if (numericMonth >= 0 && numericMonth <= 2)
				contriMonth = "Q4";
			else if (numericMonth >= 3 && numericMonth <= 5)
				contriMonth = "Q1";
			else if (numericMonth >= 6 && numericMonth <= 8)
				contriMonth = "Q2";
			else if (numericMonth >= 9 && numericMonth <= 11)
				contriMonth = "Q3";
		} else if ((siFrequency.equalsIgnoreCase("H"))) {
			if (numericMonth >= 3 && numericMonth <= 8)
				contriMonth = "H1";
			else
				contriMonth = "H2";
		} else if ((siFrequency.equalsIgnoreCase("M"))) {
			contriMonth = (Integer.toString(numericMonth + 1));
			contriMonth = utils.paddin(contriMonth, 2, "0", "left");
		} else
			contriMonth = "wrong";
		return contriMonth;
	}

	public static String getCurrentYear() {
		Calendar now = Calendar.getInstance();

		return String.valueOf(now.get(Calendar.YEAR));

	}

	public static String getMonthForInt(int num) {
		String month = "wrong";
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		if (num >= 0 && num <= 11) {
			month = months[num];
		}
		return month;
	}

	public static String getMonthAndYear(int numericMonth) {
		Calendar now = Calendar.getInstance();
		int numericYear = now.get(Calendar.YEAR);
		String temp = String.valueOf(numericMonth);
		if (temp.length() == 1)
			temp = "0" + temp;
		return (temp + "/" + String.valueOf(numericYear));
	}

	public static int getCurrentNumericMonth() {
		Calendar now = Calendar.getInstance();
		int numericMonth = now.get(Calendar.MONTH) + 1;
		return numericMonth;
	}

	public static String getNextPaidUptoDate(String prevPaidDate, int increment) {
		SimpleDateFormat format = new SimpleDateFormat("MM/yyyy");
		Date d1 = null;
		Calendar d11 = Calendar.getInstance();
		String newDate = null;

		try {
			d1 = format.parse(prevPaidDate);
			d11.setTime(d1);
			d11.add(Calendar.MONTH, increment);
			newDate = format.format(d11.getTime());
		} catch (ParseException e) {
			logger.error("Exception =:", e);
		}
		return newDate;
	}

	public static boolean getMaxDayfromMonth() {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd");
		String today = dateFormat.format(cal.getTime());
		String maxDay = Integer.toString(cal.getActualMaximum(Calendar.DAY_OF_MONTH));

		if (today.equalsIgnoreCase(maxDay))
			return true;
		else
			return false;
//			return true;
	}

	public static String getMaxDayofCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date d = cal.getTime();
		return dateFormat.format(d);
	}

	public static String getPreviousMonthMaxDay() {
		String previousMonthMaxDay = null;
		Calendar aCalendar = Calendar.getInstance();
//		int previousYear = aCalendar.get(Calendar.YEAR);
//		int currentMonth = aCalendar.get(Calendar.MONTH);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		aCalendar.set(Calendar.DATE, 1);
		aCalendar.add(Calendar.DAY_OF_MONTH, -1);

		previousMonthMaxDay = dateFormat.format(aCalendar.getTime());
		return previousMonthMaxDay;
	}

	public static boolean getFirstWeekOfCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		int week = calendar.get(Calendar.WEEK_OF_MONTH);
		if (week == 1)
			return true;
		else
			return false;

	}

	public static Date getMonthAndYearfromString(String inputDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
		Date dt = null;
		try {
			dt = sdf.parse(inputDate);
		} catch (ParseException e) {
			logger.error("Exception =:", e);
		}
		return dt;
	}

	public static Date getDatefromString(String inputDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dt = null;
		try {
			dt = sdf.parse(inputDate);
		} catch (ParseException e) {
			logger.error("Exception =:", e);
		}
		return dt;
	}


}
