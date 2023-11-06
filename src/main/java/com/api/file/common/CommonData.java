package com.api.file.common;

public class CommonData {
	public static final String PHONE_REGEX_PATTERN = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
	public static final String PHONE_REGEX_PATTERN_JP = "^(\\+81|0)[1-9]\\d{8,9}$";

    public static final String PHONE_REGEX_PATTERN_MM = "^(09\\d{9}|\\+959\\d{7,10})$";
//    "09555555555",
//    "+959555555555",
//    "+9591234567",
//    "+95912345678",
//    "1234567890", // Invalid format
//    "09-555-555-55", // Invalid format
    public static final String GENDER_REGEX_PATTERN = "[123]";
    public static final String AUTHORITY_REGEX_PATTERN = "[1234]";	
	public static final String USER_STATUS_REGEX_PATTERN = "[12]";
	public static final String BLANK = "";
	public static final String MAIL_REGEX_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	public static final String DATE_FORMAT_YYYYMMDD = "yyyy-MM-dd";
	public static final String DATE_FORMAT_YYYYMM_NIHON = "yyyy年MM月";
	public static final String ORDER_STATUS_REGEX_PATTERN = "[1234]";
	public static final String JAPANESE_POSTAL_CODE_REGEX_PATTERN = "^\\d{7}$";
//	 "1234567",   // Valid Japanese postal code
//     "123-4567",  // Invalid format with hyphen
//     "123-45678", // Invalid format with extra digit
//     "123456",    // Invalid format with 6 digits
//     "ABCDEFG",   // Invalid format with non-digits
	public static final String LAST_FOUR_DIGITS_REGEX_PATTERN = "\\d{4}$";



}
