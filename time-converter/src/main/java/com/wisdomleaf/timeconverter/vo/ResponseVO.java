package com.wisdomleaf.timeconverter.vo;

import java.io.Serializable;

public class ResponseVO implements Serializable {

	private static final long serialVersionUID = -2046860581088920476L;

	private String responseCode;
	private String responseMessage;
	private String resultString;

	public ResponseVO() {
		super();
	}

	public ResponseVO(String responseCode, String responseMessage) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getResultString() {
		return resultString;
	}

	public void setResultString(String resultString) {
		this.resultString = resultString;
	}

}
