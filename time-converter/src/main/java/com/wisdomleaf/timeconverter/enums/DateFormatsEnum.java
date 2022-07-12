package com.wisdomleaf.timeconverter.enums;

public enum DateFormatsEnum {

	TIME_FORMAT_HHMMSS("HH:mm");

	private final String value;

	private DateFormatsEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
