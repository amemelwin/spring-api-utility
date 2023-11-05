package com.api.file.common;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Utility {
	
	// default setting
	
	// AllowExtesnsions
	private static ArrayList<String> allowImagesExtensions = new ArrayList<String>(Arrays.asList("jpg","jpeg","png"));
	private static ArrayList<String> allowCSVExtensions = new ArrayList<String>(Arrays.asList("csv"));
	
	public static enum Extensions {
		IMAGES, CSV
	}
	
	// Stroage File Base Path
	
	private static String saveFileBasePath = "assets/images/";
	private static String saveFileBasePath2 = "assets/docs/";
	
	/**
	 * Check Extension
	 * @param filename
	 * @return
	 */
	public static Boolean checkExtension(String filename,Extensions allowType) {
		
		String fileExtension = filename.substring( filename.lastIndexOf(".")+1, filename.length() );
		
		if(allowType.equals(Extensions.IMAGES)) {
			return allowImagesExtensions.contains(fileExtension.toLowerCase());
		}else if(allowType.equals(Extensions.CSV)) {
			return allowCSVExtensions.contains(fileExtension.toLowerCase());
		}
		
		return false;
	}
	
	/**
	 * saveFile Util
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static String saveFile(MultipartFile file) throws Exception {
		Path uploadPath = Paths.get(saveFileBasePath);
		
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
		
		return saveFileBasePath + fileCode + "-" + fileName;
	}
	
}
