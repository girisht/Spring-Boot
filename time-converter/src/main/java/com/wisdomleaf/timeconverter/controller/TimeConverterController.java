package com.wisdomleaf.timeconverter.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wisdomleaf.timeconverter.enums.DateFormatsEnum;
import com.wisdomleaf.timeconverter.service.TimeConverterService;
import com.wisdomleaf.timeconverter.util.JsonUtil;
import com.wisdomleaf.timeconverter.util.TimeUtils;
import com.wisdomleaf.timeconverter.vo.ResponseVO;

@RestController
@RequestMapping("/")
public class TimeConverterController {

	@Autowired
	private TimeConverterService timeConverterService;

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET, headers = "Accept=*/*", produces = "application/json")
	public String convertCurrentTimeToWords() throws Exception {
		ResponseVO responseVO = new ResponseVO();
		try {
			Date currentDate = new Date(System.currentTimeMillis());
			String formattedTimeString = TimeUtils.formatDateToTimeFormatString(currentDate,
					DateFormatsEnum.TIME_FORMAT_HHMMSS);
			String resultString = timeConverterService.convertTimeToWords(formattedTimeString);
			responseVO.setResponseCode("200");
			responseVO.setResponseMessage("Success");
			responseVO.setResultString(resultString);
		} catch (Exception e) {
			responseVO.setResponseCode("500");
			responseVO.setResponseMessage(
					"An Exception has occured in TimeConverterController defaultTime() : " + e.getMessage());
			e.printStackTrace();
		}
		return JsonUtil.toJson(responseVO);
	}
	
	@ResponseBody
	@RequestMapping(value = "/timeformat", method = RequestMethod.GET, headers = "Accept=*/*", produces = "application/json")
	public String convertCurrentTimeToWords(@RequestParam("time") String timeString) throws Exception {
		ResponseVO responseVO = new ResponseVO();
		try {			
			String resultString = timeConverterService.convertTimeToWords(timeString);
			responseVO.setResponseCode("200");
			responseVO.setResponseMessage("Success");
			responseVO.setResultString(resultString);
		} catch (Exception e) {
			responseVO.setResponseCode("500");
			responseVO.setResponseMessage(
					"An Exception has occured in TimeConverterController defaultTime() : " + e.getMessage());
			e.printStackTrace();
		}
		return JsonUtil.toJson(responseVO);
	}

}
