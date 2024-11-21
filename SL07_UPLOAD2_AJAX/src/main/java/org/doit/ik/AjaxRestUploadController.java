package org.doit.ik;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class AjaxRestUploadController {
	
	@PostMapping("/ajax/uploadAjax")
	   public void uploadAjax(
	         @RequestParam("attachList") List<MultipartFile> attachList
	         ) {
	      log.info("> AjaxRestUploadController.uploadAjax()... Post");   
	      
	      // C:\\upload 폴더 생성.
	      String uploadFolder = "C:\\upload";
	      
	      for (MultipartFile attach : attachList) {
	         if( ! attach.isEmpty() ) {   
	            // 1. 첨부된 파일의 정보 출력
	            log.info("-".repeat(30));
	            log.info("> originalFilename : " + attach.getOriginalFilename());
	            log.info("> size : " + attach.getSize());          
	            // 2. 첨부파일 저장
	            File saveFile = new File(uploadFolder, attach.getOriginalFilename() );
	            try {
	               attach.transferTo(saveFile);
	            } catch (Exception e) {
	               log.error(e.getMessage());
	            }
	         } // if
	      } // for      

	      log.info(" = end ="); 
	   }
	
} // class
