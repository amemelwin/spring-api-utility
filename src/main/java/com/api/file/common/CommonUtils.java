package com.api.file.common;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.springframework.web.multipart.MultipartFile;


public class CommonUtils {

	/* CHECK EXTENSION UTILITY */

	private static final ArrayList<String> allowImagesExtensions = new ArrayList<String>(Arrays.asList("jpg", "jpeg", "png"));
	private static final ArrayList<String> allowCSVExtensions = new ArrayList<String>(Arrays.asList("csv"));

	public static enum Extensions {
		IMAGES, CSV
	}

	/**
	 * Check Extension
	 * 
	 * @param filename
	 * @return
	 */
	public static Boolean checkExtension(String filename, Extensions allowType) {

		String fileExtension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());

		if (allowType.equals(Extensions.IMAGES)) {
			return allowImagesExtensions.contains(fileExtension.toLowerCase());
		} else if (allowType.equals(Extensions.CSV)) {
			return allowCSVExtensions.contains(fileExtension.toLowerCase());
		}

		return false;
	}

	/* CHECK EXTENSION UTILITY */
	
	/* CHECK FILE SIZE */
	
	public static enum FileSize{
		ONE_MB, FIVE_MB, EIGHT_MB,TEN_MB, TWENTY_MB, THIRTY_MB
	}
	
	public static boolean isExceedLimit(long size,FileSize maxLimit) {
		int limit = 0;
		if(maxLimit.equals(FileSize.ONE_MB) ) {
			limit = 1*1024*1024;
		}
		if(maxLimit.equals(FileSize.FIVE_MB) ) {
			limit = 5*1024*1024;
		}
		if(maxLimit.equals(FileSize.EIGHT_MB) ) {
			limit = 8*1024*1024;
		}
		if(maxLimit.equals(FileSize.TEN_MB) ) {
			limit = 10*1024*1024;
		}
		if(maxLimit.equals(FileSize.TWENTY_MB) ) {
			limit = 20*1024*1024;
		}
		if(maxLimit.equals(FileSize.THIRTY_MB) ) {
			limit = 30*1024*1024;
		}
		return size> limit;
	}
	
	/* CHECK FILE SIZE */

	/* SAVE FILE UTILTIY */

	public static enum Locations {
		PRODUCTS, PAYMENTS
	}

	private static final String productBaseUrl = "assets/images/products/";
	private static final String paymentSlipBaseUrl = "assets/pdf/payments/";

	/**
	 * saveFile Util
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static String saveFile(MultipartFile file, Locations location) throws Exception {
		String saveFileLocation = "";
		if (location.equals(Locations.PRODUCTS)) {
			saveFileLocation = productBaseUrl;
		} else if (location.equals(Locations.PAYMENTS)) {
			saveFileLocation = paymentSlipBaseUrl;
		}
		Path uploadPath = Paths.get(saveFileLocation);

		// Create Folder if not exist
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		String fileName = file.getOriginalFilename();

		String fileCode = String.valueOf(new Date().getTime());

		try (InputStream inputStream = file.getInputStream()) {
			Path filePath = uploadPath.resolve(fileCode + "-" + fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Could not save file: " + fileName, ioe);
		}

		return saveFileLocation + fileCode + "-" + fileName;
	}

	/* SAVE FILE UTILTIY */
	
	/* DELETE FILE UTILITY */
	
	public static boolean deleteFile(String fileURL) {
		Path filePath = Paths.get(fileURL);
		try {
			return Files.deleteIfExists(filePath);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/* DELETE FILE UTILITY */
	

	/* CHECK PHONE FORMAT */
	public static boolean isValidPhoneNumber(String phoneNumber) {
		return phoneNumber.matches(CommonData.PHONE_REGEX_PATTERN_MM);
	}
	/* CHECK PHONE FORMAT */

	/* CHECK GENDER FORMAT */
	public static boolean isValidGender(String gender) {
		return gender.matches(CommonData.GENDER_REGEX_PATTERN);
	}
	/* CHECK GENDER FORMAT */

	/* CHECK VALID AUTHORITY */
	public static boolean isValidAuthority(String authority) {
		return authority.matches(CommonData.AUTHORITY_REGEX_PATTERN);
	}
	/* CHECK VALID AUTHORITY */

	/* CHECK USER STATUS */
	public static boolean isValidUserStatus(String status) {
		return status.matches(CommonData.USER_STATUS_REGEX_PATTERN);
	}
	/* CHECK USER STATUS */
	
	/* CHECK EMPTY */
	public static boolean isEmpty(String str) {
		if (str == null || CommonData.BLANK.equals(str)) {
			return true;
		}
		return false;
	}
	/* CHECK EMPTY */
	
	public static boolean isDateEmpty(LocalDate str) {
		if (str == null || CommonData.BLANK.equals(str)) {
			return true;
		}
		return false;
	}

	/* CHECK VALID MAIL FORMAT */
	public static boolean isValidEmail(String email) {
		return email.matches(CommonData.MAIL_REGEX_PATTERN);
	}
	/* CHECK VALID MAIL FORMAT */

	/* CHECK VALID DATE FORMAT */
	public static boolean isValidDate(String date) {
		try {
			DateFormat df = new SimpleDateFormat(CommonData.DATE_FORMAT_YYYYMMDD);
			df.setLenient(false);
			df.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
	/* CHECK VALID DATE FORMAT */
	
	/* CHECK LAST FOUR DIGITS */
	 public static boolean matchesLastFourDigits(String input) {
	        Pattern pattern = Pattern.compile(CommonData.LAST_FOUR_DIGITS_REGEX_PATTERN);
	        Matcher matcher = pattern.matcher(input);

	        return matcher.find();
	    }


}
