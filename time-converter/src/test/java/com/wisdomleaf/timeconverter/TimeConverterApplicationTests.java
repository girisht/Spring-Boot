package com.wisdomleaf.timeconverter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wisdomleaf.timeconverter.service.TimeConverterService;

@SpringBootTest
class TimeConverterApplicationTests {

	@Autowired
	TimeConverterService timeConverterService;

	@Test
	void contextLoads() {
	}

	@Test
	public void testMidday() {
		String timeString = "12:00";

		String result = timeConverterService.convertTimeToWords(timeString);

		Assertions.assertEquals("It's midday", result);
	}
	
	@Test
	public void testMidnight() {
		String timeString = "00:00";

		String result = timeConverterService.convertTimeToWords(timeString);

		Assertions.assertEquals("It's midnight", result);
	}
	
	@Test
	public void testPositiveScenario() {
		String timeString = "01:05";

		String result = timeConverterService.convertTimeToWords(timeString);

		Assertions.assertEquals("It's one five", result);
	}
	
	@Test
	public void testPositiveScenario2() {
		String timeString = "08:34";

		String result = timeConverterService.convertTimeToWords(timeString);

		Assertions.assertEquals("It's eight thirty four", result);
	}
	
	@Test
	public void testNegativeScenario() {
		String timeString = "sdasd";

		String result = timeConverterService.convertTimeToWords(timeString);

		Assertions.assertEquals("Given time is not valid..", result);
	}
	
	@Test
	public void testNegativeScenario2() {
		String timeString = "123:1231";

		String result = timeConverterService.convertTimeToWords(timeString);

		Assertions.assertEquals("Given time is not valid..", result);
	}

}
