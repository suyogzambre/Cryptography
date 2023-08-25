package com.crypto.project.controller;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;

import com.crypto.project.configs.NotificationService;
import com.crypto.project.helpers.GetIp;
//import com.crypto.project.helpers.SendMailHelp;
import com.crypto.project.helpers.TextHelper;
import com.crypto.project.model.Users;
import com.crypto.project.model.EncData;
import com.crypto.project.model.TextEncDcrtModel;
import com.crypto.project.service.EncDataStore;
import com.crypto.project.service.RequestService;
import com.crypto.project.service.UsersRepository;


@RestController
@RequestMapping("/txt")
public class TextEncrpription {

	@Autowired
	private UsersRepository userServise;
	
	@Autowired
	private EncDataStore EncDStored;
	@Autowired 
	private HttpServletRequest request;
	
    @Autowired
    private RequestService ipLoc;
    @Autowired
	public NotificationService sendMail;
	


	
	@PostMapping("/encTxt")
	public ResponseEntity<Object> encrptTxt(@RequestBody TextEncDcrtModel txtObj) throws NoSuchElementException{
		  String EncTxt = TextHelper.encrypt(txtObj.getText(), txtObj.getKey());
		  
		  try {
			  String reciversList = txtObj.getUserId();
		      String[] array = reciversList.split(",");
		      for(String value:array) {
		    	  try {
		    	    	Optional<Users> userOptionall =  userServise.findByUserId(value);
		    	    	long userNumid = userOptionall.get().getUserNumId();
		    	    	EncData enObj = new EncData(0, EncTxt, txtObj.getTime(), userNumid, txtObj.getSenderName(),"text","","");
		    	        EncDStored.save(enObj);   

//		    	        SendMailHelp.sendMsg(userOptionall, userOptionall.get().getEmailId(), "", txtObj.getTime());
		    	        
		    	  }catch (Exception e) {
		    		  e.getMessage();
					// TODO: handle exception
				}
		      }
		      return ResponseEntity.ok().build();
			
		  } catch (Exception e) {
				// TODO: handle exception
			}
		return null;


	}
	
	@PostMapping("/dcrtTxt")
	public ResponseEntity<Object> dcrptTxt(@RequestBody TextEncDcrtModel txtObj,HttpServletRequest request) throws NoSuchElementException, MessagingException {
		HashMap<String, String> map = new HashMap<>();
		try {
			Optional<EncData> data = EncDStored.findById(txtObj.getId());
			String EncTxt = TextHelper.decrypt(txtObj.getText(), txtObj.getKey());
			String userIp = ipLoc.getClientIp(request);                 
		    map.put("txt", EncTxt);
	    	map.put("ip",userIp);
	    	data.get().setReciverLocation(userIp);
	    	EncDStored.save(data.get());
	    	
	    	
	    	//getting the sender 
	    	Users sender = userServise.findByUserNumId(null);
	    	
			} catch (Exception e) {
			// TODO: handle exception
				e.getMessage();
		}
		sendMsg(null, "aadeshpatil650@gmail.com", "103.146.240.239", "17 Feb");
		return new ResponseEntity<>(map, HttpStatus.OK);

			
	}
	

	
	private void sendMsg(Optional<Users> sender, String email,String Ip,String time) throws MessagingException{
		try {
			String Site = "https://whatismyipaddress.com/ip/".concat(Ip);
		String mailBody = "<html lang=\"en-US\">\r\n"
				+ "<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px; background-color: #f2f3f8;\" leftmargin=\"0\">\r\n"
				+ "    <!--100% body table-->\r\n"
				+ "    <table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f2f3f8\">\r\n"
				+ "         <tr>\r\n"
				+ "            <td>\r\n"
				+ "                <table style=\"background-color: #f2f3f8; max-width:670px;  margin:0 auto;\" width=\"100%\" border=\"0\"\r\n"
				+ "                    align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td style=\"height:80px;\">&nbsp;</td>\r\n"
				+ "                    </tr>\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td style=\"text-align:center;\">\r\n"
				+ "                          <h1 style=\"text-decoration: bold;\">SECURED WORLD</h1>\r\n"
				+ "                        </td>\r\n"
				+ "                    </tr>\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td style=\"height:20px;\">&nbsp;</td>\r\n"
				+ "                    </tr>\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td>\r\n"
				+ "                            <table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"\r\n"
				+ "                                style=\"max-width:670px;background:#fff; border-radius:3px; text-align:center;-webkit-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);-moz-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);box-shadow:0 6px 18px 0 rgba(0,0,0,.06);\">\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td style=\"height:40px;\">&nbsp;</td>\r\n"
				+ "                                </tr>\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td style=\"padding:0 35px;\">\r\n"
				+ "                                        <h1 style=\"color:#1e1e2d; font-weight:500; margin:0;font-size:32px;font-family:'Rubik',sans-serif;\">Someone have\r\n"
				+ "                                            successfully decrypted your message</h1>\r\n"
				+ "                                        <span\r\n"
				+ "                                            style=\"display:inline-block; vertical-align:middle; margin:29px 0 26px; border-bottom:1px solid #cecece; width:100px;\"></span>\r\n"
				+ "                                        <p style=\"color:#455056; font-size:15px;line-height:24px; margin:0;\">\r\n"
				+ "                                           Hey Aadesh Patil , Your message Sended to admin on 17 Jan has decrypted at ip "+ Ip+ " .\r\n"
				+ "                                           <br>\r\n"
				+ "                                           You can check the details of decrypter.\r\n"
				+ "                                        </p>\r\n"
				+ "                                        <a href="+Site
				+ "                                            style=\"background:#20e277;text-decoration:none !important; font-weight:500; margin-top:35px; color:#fff;text-transform:uppercase; font-size:14px;padding:10px 24px;display:inline-block;border-radius:50px;\">\r\n"
				+ "                                            Check Details</a>\r\n"
				+ "                                    </td>\r\n"
				+ "                                </tr>\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td style=\"height:40px;\">&nbsp;</td>\r\n"
				+ "                                </tr>\r\n"
				+ "                            </table>\r\n"
				+ "                        </td>\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td style=\"height:20px;\">&nbsp;</td>\r\n"
				+ "                    </tr>\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td style=\"text-align:center;\">\r\n"
				+ "                            <p style=\"font-size:14px; color:rgba(69, 80, 86, 0.7411764705882353); line-height:18px; margin:0 0 0;\">&copy; <strong>www.securedWorld.com</strong></p>\r\n"
				+ "                        </td>\r\n"
				+ "                    </tr>\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td style=\"height:80px;\">&nbsp;</td>\r\n"
				+ "                    </tr>\r\n"
				+ "                </table>\r\n"
				+ "            </td>\r\n"
				+ "        </tr>\r\n"
				+ "    </table>\r\n"
				+ "    <!--/100% body table-->\r\n"
				+ "</body>\r\n"
				+ "\r\n"
				+ "</html>";
		
		
		sendMail.sendNotification(email, "Secured World : Regarding Message", mailBody);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
	}
}
