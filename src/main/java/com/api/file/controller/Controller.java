package com.api.file.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.file.common.Utility;

@RestController
public class Controller {
	
	// 1. file upload
		// 1.1 check file extension
		// 1.2 Desire location save with format
	
	// 2. file upload multple
		
	
	@PostMapping("/file-upload")
	public String fileUpload(@RequestParam("file") MultipartFile file ) {
		
		// check file extensions
		if(!Utility.checkExtension(file.getOriginalFilename(), Utility.Extensions.IMAGES)) {
			// do Error Response type
		}
		
		// check file size eg 10MB  10 * 1024 * 1024 
		if(file.getSize()>(10*1024*1024)) {
			// do Error Response for max size
		}
		
		
		// save file
		try {
			String filePath = Utility.saveFile(file);
			return "file is save on: http://localhost:8080/" + filePath;
		}catch(Exception e) {
			// do Error Response
			return "error";
		}
		
	}
	
	
}
 