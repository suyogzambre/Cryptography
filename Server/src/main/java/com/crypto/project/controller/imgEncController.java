package com.crypto.project.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.crypto.project.configs.NotificationService;
import com.crypto.project.helpers.ImgEncHelper;
import com.crypto.project.model.CustomResponseBody;
import com.crypto.project.model.EncData;
import com.crypto.project.model.Users;
import com.crypto.project.service.EncDataStore;
import com.crypto.project.service.RequestService;
import com.crypto.project.service.UsersRepository;

import io.swagger.annotations.Extension;

@RestController
@RequestMapping("/img")
public class imgEncController {


	@Autowired
	private UsersRepository userServise;
	@Autowired
	private EncDataStore EncDStored;
	@Autowired
	private RequestService ipLoc;
	@Autowired
	public NotificationService sendMail;
		

	
	
	@PostMapping("/encrpt")
	public ResponseEntity<InputStreamResource> encFile(@RequestParam("file") MultipartFile file2,
			@RequestParam("key") String key,@RequestParam("sender") String sender) throws IOException {
		try {
			System.out.println(file2.getName());
			File f = ImgEncHelper.convert(file2);
			byte[] content = ImgEncHelper.getFile(f);
			System.out.println(content);
			String fileName = f.getName();
			File file = ImgEncHelper.encryptPdfFile(key, content,sender,fileName);
			
			MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
			String mimeType = fileTypeMap.getContentType(file.getName());
			MediaType mediaType = MediaType.parseMediaType(mimeType);
			System.out.println("fileName: " + file.getName());
			System.out.println("mediaType: " + mediaType);
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
			System.out.println(resource);
			return ResponseEntity.ok()
					// Content-Disposition
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
					.contentType(mediaType)
					.contentLength(file.length()) //
					.body(resource);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();

		}

    	return ResponseEntity.ok().build();
		
	}
	
	
	@PostMapping("/dcrpt")
	public ResponseEntity<InputStreamResource> dcrptFile(@RequestParam("file") MultipartFile file,
			@RequestParam("key") String key, @RequestParam("sender") String sender) throws IOException {
		try {
			
			System.out.println(file.getName());
			File f = ImgEncHelper.convert(file);
			byte[] content = ImgEncHelper.getFile(f);
			System.out.println(content);
			String fileName = f.getName();
			File encrtFile = ImgEncHelper.decryptPdfFile(key, content,sender,fileName);

			MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
			String mimeType = fileTypeMap.getContentType(encrtFile.getName());
			MediaType mediaType = MediaType.parseMediaType(mimeType);
			System.out.println("fileName: " + encrtFile.getName());
			System.out.println("mediaType: " + mediaType);
			InputStreamResource resource = new InputStreamResource(new FileInputStream(encrtFile));
			System.out.println(resource);
			return ResponseEntity.ok()
					// Content-Disposition
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + encrtFile.getName())
					.contentType(mediaType)
					.contentLength(encrtFile.length()) //
					.body(resource);
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();

		}
    	return ResponseEntity.ok().build();
	}
	
	@PostMapping("/sendEncrpt")
	public ResponseEntity<Object> sendEncFile(@RequestParam("file") MultipartFile file,
			@RequestParam("key") String key,@RequestParam("sender") String sender,@RequestParam("reciver") String reciver,
			@RequestParam("time") String time,@RequestParam("fileType") String fileType) throws IOException {

		try {
		    String[] array = reciver.split(",");
		    for(String value:array) {
		    	System.out.println(value);
		    	Optional<Users> userOptionall =  userServise.findByUserId(value);
		    	File f = ImgEncHelper.convert(file);
				byte[] content = ImgEncHelper.getFile(f);
				String fileName = f.getName();
				File encrtFile = ImgEncHelper.encryptPdfFile(key, content,sender,fileName);
				String filepath = encrtFile.getAbsolutePath();
			  	EncData enObj = new EncData(0,"", time, userOptionall.get().getUserNumId(), sender,fileType,"",filepath);
	        	EncDStored.save(enObj);
		    }
		    return ResponseEntity.ok().build();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();

		}
		return null;
		
	}
	@PostMapping("/dcrptMsgImg")
	public ResponseEntity<InputStreamResource> dcrptSendedFile(@RequestParam("filePath") String filePath,@RequestParam("id") String id,
			@RequestParam("key") String key, @RequestParam("sender") String sender,HttpServletRequest request) throws IOException {
		try {
			File encFile = new File(filePath);
			
			byte[] content = ImgEncHelper.getFile(encFile);

			String fileName = encFile.getName();
			File encrtFile = ImgEncHelper.decryptPdfFile(key, content,sender,fileName);

			MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
			String mimeType = fileTypeMap.getContentType(encrtFile.getName());
			MediaType mediaType = MediaType.parseMediaType(mimeType);
			System.out.println("fileName: " + encrtFile.getName());
			System.out.println("mediaType: " + mediaType);
			InputStreamResource resource = new InputStreamResource(new FileInputStream(encrtFile));
		
//		
//			Optional<EncData> data = EncDStored.findById(Long.parseLong(id));
//			String userIp = ipLoc.getClientIp(request);
//	    	data.get().setReciverLocation(userIp);
			
			return ResponseEntity.ok()
					// Content-Disposition
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + encrtFile.getName())
					.contentType(mediaType)
					.contentLength(encrtFile.length()) //
					.body(resource);
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();

		}
    	return ResponseEntity.ok().build();
	}


}