package com.wisdomleaf.timeconverter.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdomleaf.timeconverter.common.PropertyManager;
import com.wisdomleaf.timeconverter.service.TimeConverterService;
import com.wisdomleaf.timeconverter.util.TimeUtils;

@Service
public class TimeConverterServiceImpl implements TimeConverterService {

	@Autowired
	PropertyManager propertyManager;

	@Override
	public String convertTimeToWords(String timeString) {
		String resultString = "Given time is not valid..";
		try {
		if (Objects.nonNull(timeString)) {
			if (TimeUtils.isValidTime(timeString)) {
				resultString = TimeUtils.checkMidDayOrNight(timeString);
				if (Objects.nonNull(resultString)) {
					return resultString;
				}
				List<String> positionHoldingList = TimeUtils.getListOfValidCharactersFromString(timeString);
				if (Objects.nonNull(positionHoldingList) && positionHoldingList.size() == 4) {
					StringBuilder resultTimeString = new StringBuilder("It's ");
					int hours = TimeUtils.getHours(positionHoldingList);
					String hoursDescription = "";
					if(hours > 20) {
						int tensNumber = getNumberInTens(hours);
						hoursDescription += propertyManager.getPropertyByKey(String.valueOf(tensNumber));
						hoursDescription += " ";
						int onesNumber = getNumberInOnes(hours);
						hoursDescription += propertyManager.getPropertyByKey(String.valueOf(onesNumber));
					} else {
						hoursDescription = propertyManager.getPropertyByKey(String.valueOf(hours));
					}		
					
					resultTimeString.append(hoursDescription);
					resultTimeString.append(" ");
					int minutes = TimeUtils.getMinutes(positionHoldingList);
					String minutesDescription = "";
					if(minutes > 20) {
						int tensNumber = getNumberInTens(minutes);
						minutesDescription += propertyManager.getPropertyByKey(String.valueOf(tensNumber));
						minutesDescription += " ";
						int onesNumber = getNumberInOnes(minutes);
						minutesDescription += propertyManager.getPropertyByKey(String.valueOf(onesNumber));
					} else {
						minutesDescription = propertyManager.getPropertyByKey(String.valueOf(minutes));
					}					
					resultTimeString.append(minutesDescription);
					return resultTimeString.toString();
				}
			}
		}
		} catch (Exception e) {
			System.out.println("Exception occured TimeConverterServiceImpl - convertTimeToWords()");
			e.printStackTrace();
		}
		return Objects.nonNull(resultString) ? resultString.trim() : "Given time is not valid..";
	}
	
	private int getNumberInTens(int number) {
		return (number/10) * 10;
	}
	
	private int getNumberInOnes(int number) {
		return (number%10);
	}
}
