package com.wisdomleaf.timeconverter.service;

import org.springframework.stereotype.Service;

@Service
public interface TimeConverterService {

	public String convertTimeToWords(String timeString);

}
