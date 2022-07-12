package com.wisdomleaf.timeconverter.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wisdomleaf.timeconverter.enums.DateFormatsEnum;

public class TimeUtils {

	private static final String TIME_PATTERN = "(2[0-3]|[01]?[0-9]):([0-5]?[0-9])";
	private static final String SINGLE_DIGIT_HOUR_TIME_PATTERN = "([0-3]|[01]?[0-9]):([0-5]?[0-9])";
	private static final String SPLIT_CHARACTERS_FROM_STRING = "([0-2])([0-9]):([0-5])([0-9])";
	private static final String SINGLE_DIGIT_HOUR_SPLIT_CHARACTERS_FROM_STRING = "([0-9]):([0-5])([0-9])";

	public static String formatDateToTimeFormatString(Date date, DateFormatsEnum desiredDateFormat) throws Exception {
		SimpleDateFormat formatTo = new SimpleDateFormat(desiredDateFormat.getValue());
		String dateFormated = formatTo.format(date);
		return dateFormated;
	}

	public static boolean isValidTime(String formattedTimeString) {
		Pattern timePattern = Pattern.compile(TIME_PATTERN);
		Pattern singleDigitTimePattern = Pattern.compile(SINGLE_DIGIT_HOUR_TIME_PATTERN);
		Matcher timeMatcher = timePattern.matcher(formattedTimeString);
		if (timeMatcher.matches()) {
			return true;
		}
		
		timeMatcher = singleDigitTimePattern.matcher(formattedTimeString);
		if (timeMatcher.matches()) {
			return true;
		}
		return false;
	}

	public static List<String> getListOfValidCharactersFromString(String timeString) {
		Pattern splitPattern = Pattern.compile(SPLIT_CHARACTERS_FROM_STRING);
		Matcher matcher = splitPattern.matcher(timeString);
		List<String> positionHoldingList = new ArrayList<>();
		if (matcher.matches() && matcher.groupCount() == 4) {
			for (int i = 1; i < 5; i++) {
				positionHoldingList.add(matcher.group(i));
			}
		} else {
			splitPattern = Pattern.compile(SINGLE_DIGIT_HOUR_SPLIT_CHARACTERS_FROM_STRING);
			matcher = splitPattern.matcher(timeString);
			if(matcher.matches() && matcher.groupCount() == 3) {
				positionHoldingList.add("0");
				for (int i = 1; i < 4; i++) {
					positionHoldingList.add(matcher.group(i));
				}
			}
		}
		
		return positionHoldingList;
	}

	public static String checkMidDayOrNight(String timeString) {
		if ("00:00".equals(timeString)) {
			return "It's midnight";
		} else if ("12:00".equals(timeString)) {
			return "It's midday";
		}
		return null;
	}

	public static Integer getHours(List<String> timeStringList) {
		StringBuilder hoursTimeString = new StringBuilder(timeStringList.get(0));
		hoursTimeString.append(timeStringList.get(1));
		Integer currentTimeInHours = Integer.parseInt(hoursTimeString.toString());
		currentTimeInHours = Objects.nonNull(currentTimeInHours) ? currentTimeInHours : 0;
		return currentTimeInHours;
	}

	public static Integer getMinutes(List<String> timeStringList) {
		StringBuilder minutesTimeString = new StringBuilder(timeStringList.get(2));
		minutesTimeString.append(timeStringList.get(3));
		Integer currentTimeInMinutes = Integer.parseInt(minutesTimeString.toString());
		currentTimeInMinutes = Objects.nonNull(currentTimeInMinutes) ? currentTimeInMinutes : 0;
		return currentTimeInMinutes;
	}

}
