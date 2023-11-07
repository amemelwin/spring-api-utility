package com.api.file.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.file.common.CommonData;
import com.api.file.common.CommonUtils;
import com.api.file.common.CommonUtils.FileSize;
import com.api.file.common.CommonUtils.Locations;

@RestController
public class Controller {
	
	// 1. file upload
		// 1.1 check file extension
		// 1.2 Desire location save with format
	
	// 2. file upload multiple
		

	
	@PostMapping("/file-upload")
	public String fileUpload(@RequestParam("file") MultipartFile file ) {
		// check file size
		if(CommonUtils.isExceedLimit(file.getSize(), FileSize.FIVE_MB)) {
			return "File size exceed limit ";
		}
		
		// check file extensions
		if(!CommonUtils.checkExtension(file.getOriginalFilename(), CommonUtils.Extensions.IMAGES)) {
			// do Error Response type
			//return "Wrong extension";
		}
		
		 //save file
		try {
			String filePath = CommonUtils.saveFile(file,Locations.PAYMENTS);
			return "file is save on: http://localhost:8080/" + filePath;
		}catch(Exception e) {
			// do Error Response
			return "error";
		}
		// delete file
		//Utility.deleteFile("assets/images/1699107788031-logo.jpeg");
		//CommonUtils.deleteFile("assets/images/1699107788031-logo.jpeg");
		 //return "";
	}
	
	
	
	
}
 