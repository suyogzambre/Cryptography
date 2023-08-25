package com.crypto.project.controller;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Optional;
import java.util.Random;

import javax.activation.MimetypesFileTypeMap;
import javax.mail.MessagingException;
import javax.servlet.annotation.MultipartConfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Timestamp;
import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.crypto.project.configs.NotificationService;
import com.crypto.project.helpers.ImgEncHelper;
import com.crypto.project.model.ApplicationUser;
import com.crypto.project.model.EncData;
import com.crypto.project.model.Users;
import com.crypto.project.service.ApplicationUserRepository;
import com.crypto.project.service.EncDataStore;
import com.crypto.project.service.UsersRepository;


@RequestMapping("/users")
@RestController

public class UsersController {

	@Autowired
	//private UsersService usersService;
	private UsersRepository userRepository;
	//private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private ApplicationUserRepository applicationUserRepository;
	@Autowired
	Environment env;
	@Autowired
	private EncDataStore EncData;
	
	@Autowired
	public NotificationService sendMail;
	
	
	public MultipartFile multipart;
	
	

	@PostMapping("/fetchUser/{userId}")
	@CrossOrigin("*")
	public Optional<Users> retrieveByUserId(@PathVariable String userId) {
		System.out.println("userId : "+userId);
		
		Optional<Users> usr = userRepository.findByUserId(userId);
		usr.get().setOtp(null);
		usr.get().setPassword(null);
//		usr.get().set
		return usr;
	}

	@GetMapping("/fetchUA/{userId}")
	public Optional<Users> retrieveByUserType(@PathVariable String userId) {
		System.out.println("userType : "+userId);
		return userRepository.findByUserId(userId);
	}
	
	@GetMapping("/fetchUA")
	public List<Users> retrieveAllUsers() {
		return userRepository.findAll();
	}

	
	@GetMapping("/fetchU/{id}")
	public Optional<Users> retriveUser(@PathVariable long id) {
		System.out.println("hello");
		return userRepository.findById(id);
	}
	
	@GetMapping("/fetchUN/{userNumId}")
	public Users retriveUserByUserNumId(@PathVariable long userNumId) {
		System.out.println("hello");
		return userRepository.findByUserNumId(userNumId);
	}
	
	@GetMapping("/fetchUId/{userId}")
	public Optional<Users> retriveUser(@PathVariable String userId) {
		System.out.println("hello");
		return userRepository.findByUserId(userId);
	}
	
	@PostMapping("/updateUser")
	
	public ResponseEntity<Object> updateUser(@RequestBody Users user) {

		//user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		ApplicationUser appUser =new ApplicationUser();
	    ApplicationUser optionalAppUser = applicationUserRepository.findByUsername(user.getUserId()).get();
	    optionalAppUser.setRole(user.getRole());
	    //optionalAppUser.setPassword(user.getPassword());
	    
	    applicationUserRepository.save(optionalAppUser);
	    String completeName= user.getfName().concat(" "+user.getlName());
	    user.setCompleteName(completeName);
		userRepository.save(user);
	
		return ResponseEntity.ok().build();
	}
	@PostMapping("/signUp")
	@CrossOrigin("*")
	public Users addUser(@RequestBody Users user) {
			
		String txtPwd=user.getPassword();				
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		ApplicationUser appUser =new ApplicationUser();
		appUser.setUsername(user.getUserId());
		appUser.setPassword(user.getPassword());
		appUser.setRole(user.getRole());
		applicationUserRepository.save(appUser);
		userRepository.save(user);
		return user;

	}
//	 @PostMapping("/sign-up")
//	    public void signUp(@RequestBody ApplicationUser user) {
//	        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
//	        applicationUserRepository.save(user);
//	    }
	

	@PostMapping("/sendOTPThroughEmail")
	@CrossOrigin("*")
	public ResponseEntity<Object> sendEmail(@RequestBody Long userNumId) throws MessagingException{

			Users user=  userRepository.findByUserNumId(userNumId);
		  		   
		    if(user != null) {
		    	Random random = new Random();
		    	String otp1 = String.format("%04d", random.nextInt(10000));
		    	Long otp =Long.parseLong(otp1);
		    	user.setOtp(otp);
				userRepository.save(user);
				try {
					String mailBody = "<html>" + "<body>" + "<div style=\"font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2\">\r\n"
							+ "  <div style=\"margin:50px auto;width:70%;padding:20px 0\">\r\n"
							+ "    <div style=\"border-bottom:1px solid #eee\">\r\n"
							+ "      <a href=\"\" style=\"font-size:1.4em;color: #00466a;text-decoration:none;font-weight:600\">SECURED WORLD!</a>\r\n"
							+ "    </div>\r\n"
							+ "    <p style=\"font-size:1.1em\">Hi "+user.getCompleteName()+" ,</p>\r\n"
							+ "    <p>Hey , We had recived an request for reseting your password.,The OTP is valid for 5 minutes</p>\r\n"
							+ "    <h2 style=\"background: #00466a;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;\">"+otp+"</h2>\r\n"
							+ "    <p style=\"font-size:0.9em;\">Regards,<br />Secureed World</p>\r\n"
							+ "    <hr style=\"border:none;border-top:1px solid #eee\" />\r\n"
							+ "  </div>\r\n"
							+ "</div> "+ "</body>" + "</html>";
					
					sendMail.sendNotification(user.getEmailId(), "Secured World : OTP for User Account Password Reset", mailBody);
					return ResponseEntity.ok().build();

				} catch (Exception e) {
					// TODO: handle exception
					return  null;
				}
				
		    }else {
		        return null;

		    }
		
	}
	 
	@PostMapping("/checkOtp")
	@CrossOrigin("*")
	public String checkOTP(@RequestParam("userNumId") Long userNumId,
            @RequestParam("otp") Long otp ){
		
		Users user = userRepository.findByUserNumId(userNumId);
		System.out.println("otp :"+user.getOtp());
		System.out.println("otp enterd:"+otp );
		if(user.getOtp().equals(otp)){
			return "Done!";
		}else {
			return "Pw not matched";
		}	
	}
	
	
	
	@PostMapping("/setProfilePic")
	public ResponseEntity<Object> setImg(@RequestParam("file") MultipartFile profilePic ,@RequestParam("userNumId") Long userNumId) throws IllegalStateException, IOException {
		Users user = userRepository.findByUserNumId(userNumId);
		try {
			
//			File exFile = new File(user.getProfile_pic());
//			
//			if(exFile.exists()){
//				exFile.delete();
//			}
//			
			File file1 = ImgEncHelper.convert(profilePic);
			byte[] content = ImgEncHelper.getFile(file1);
			System.out.println(content);
			String fileName = file1.getName();
			
			String filePath = "E:\\CryptoGraphy_project\\EncData\\"+user.getUserId();
	    	File f = new File(filePath);
	    	f.mkdir();
	    	
	        FileOutputStream fos = new FileOutputStream(filePath+"\\"+fileName);
	        fos.write(content);
	        fos.close();
	        File encrtfile = new File(filePath+"\\"+fileName);
	        user.setUserNumId(userNumId);
	        user.setProfile_pic(encrtfile.getAbsolutePath());
	        userRepository.save(user);
			return ResponseEntity.ok().build();

		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return ResponseEntity.ok().build();
		
	}
	
	@GetMapping("/getProfilePic/{userNumId}")
	public ResponseEntity<Object> getImg(@PathVariable Long userNumId) throws IllegalStateException, IOException {
		Users user = userRepository.findByUserNumId(userNumId);
		try {
			if(user.getProfile_pic() != null) {
				File pp = new File(user.getProfile_pic());
				
				MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
				String mimeType = fileTypeMap.getContentType(pp.getName());
				MediaType mediaType = MediaType.parseMediaType(mimeType);
				System.out.println("fileName: " + pp.getName());
				System.out.println("mediaType: " + mediaType);
				InputStreamResource resource = new InputStreamResource(new FileInputStream(pp));
				System.out.println(resource);
				return ResponseEntity.ok()
						// Content-Disposition
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + pp.getName())
						.contentType(mediaType)
						.contentLength(pp.length()) //
						.body(resource);
						}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return ResponseEntity.ok().build();
		
	}
	
	
	@PostMapping("/UpdPwd")
	@CrossOrigin("*")
	    public Users updUserPassword(@RequestBody Users user) {
			Users exUser = userRepository.findByUserNumId(user.getUserNumId());
			try {
				if(exUser.getOtp().equals(user.getOtp())) {
					String userPassword = user.getPassword();
					user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
					ApplicationUser appUser =new ApplicationUser();
				    ApplicationUser optionalAppUser = applicationUserRepository.findByUsername(user.getUserId()).get();
				    //optionalAppUser.setRole(user.getRole());
				    optionalAppUser.setPassword(user.getPassword());
				    applicationUserRepository.save(optionalAppUser);
					userRepository.save(user);
					Users exUser2 = userRepository.findByUserNumId(user.getUserNumId());
					return  exUser2;

				}else {
					return null;
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.getCause();
			}
			return null;
			
	    }
	 
		
	 
	 static char[] generatePassword(int len) 
	    { 
	        System.out.println("Generating password using random() : "); 
	        System.out.print("Your new password is : "); 
	  
	        // A strong password has Cap_chars, Lower_chars, 
	        // numeric value and symbols. So we are using all of 
	        // them to generate our password 
	        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
	        String Small_chars = "abcdefghijklmnopqrstuvwxyz"; 
	        String numbers = "0123456789"; 
	                String symbols = "!@#$%^&*_=+-/.?<>)"; 
	  
	  
	        //String values = Capital_chars + Small_chars + 
	          //              numbers + symbols; 
	  
	                String values = numbers;
	                
	        // Using random method 
	        Random rndm_method = new Random(); 
	  
	        char[] password = new char[len]; 
	  
	        for (int i = 0; i < len; i++) 
	        { 
	            // Use of charAt() method : to get character value 
	            // Use of nextInt() as it is scanning the value as int 
	            password[i] = 
	              values.charAt(rndm_method.nextInt(values.length())); 
	  
	        } 
	        System.out.println(password);
	        return password; 
	    }
	 
	 public void store(String token,Long userNumId){
	    	Users user=userRepository.findByUserNumId(userNumId);
	    	if(user.getToken() == null) {
	    		user.setToken(token);
	    	}else{
	    		user.setToken(token);
	    	}
	    	userRepository.save(user);
	    }
	   
	 public void logout(Long userNumId){
		   Users user=userRepository.findByUserNumId(userNumId);
		   user.setToken(null);
		   userRepository.save(user);
	   }
	   
	 @PostMapping("/logout")
		
public ResponseEntity<Object> logout(@RequestBody Users user) {
	 return null;
	 }
	 
	 
	 
	 
}
