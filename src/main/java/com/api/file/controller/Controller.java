package com.api.file.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.file.common.Utility;
import com.api.file.common.Utility.FileSize;
import com.api.file.common.Utility.Locations;

@RestController
public class Controller {
	
	// 1. file upload
		// 1.1 check file extension
		// 1.2 Desire location save with format
	
	// 2. file upload multple
		
	
	@PostMapping("/file-upload")
	public String fileUpload(@RequestParam("file") MultipartFile file ) {
		// check file size
		if(Utility.isExceedLimit(file.getSize(), FileSize.FIVE_MB)) {
			return "File size exceed limit ";
		}
		
		// check file extensions
		if(!Utility.checkExtension(file.getOriginalFilename(), Utility.Extensions.IMAGES)) {
			// do Error Response type
			//return "Wrong extension";
		}
		
		// save file
		try {
			String filePath = Utility.saveFile(file,Locations.PAYMENTS);
			return "file is save on: http://localhost:8080/" + filePath;
		}catch(Exception e) {
			// do Error Response
			return "error";
		}
		// delete file
//		Utility.deleteFile("assets/pdf/payments/1699187876182-TestingFile.txt");
		// return "";
	}
	
	
	
	
}
 