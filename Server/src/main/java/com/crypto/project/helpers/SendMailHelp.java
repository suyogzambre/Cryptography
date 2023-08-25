//package com.crypto.project.helpers;
//
//import java.util.Optional;
//
//import javax.mail.MessagingException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.crypto.project.configs.NotificationService;
//import com.crypto.project.model.Users;
//
//public class SendMailHelp {
//
//    @Autowired
//	public static NotificationService sendMail;
//    
//	public static void sendMsg(Optional<Users> sender, String email,String Ip,String time) throws MessagingException{
//		try {
//			System.out.println("Hello");
//		String mailBody ="<!doctype html>\r\n"
//				+ "<html lang=\"en-US\">\r\n"
//				+ "<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px; background-color: #f2f3f8;\" leftmargin=\"0\">\r\n"
//				+ "    <!--100% body table-->\r\n"
//				+ "    <table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f2f3f8\"\r\n"
//				+ "        <tr>\r\n"
//				+ "            <td>\r\n"
//				+ "                <table style=\"background-color: #f2f3f8; max-width:670px;  margin:0 auto;\" width=\"100%\" border=\"0\"\r\n"
//				+ "                    align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
//				+ "                    <tr>\r\n"
//				+ "                        <td style=\"height:80px;\">&nbsp;</td>\r\n"
//				+ "                    </tr>\r\n"
//				+ "                    <tr>\r\n"
//				+ "                        <td style=\"text-align:center;\">\r\n"
//				+ "                          <h1 style=\"text-decoration: bold;\">SECURED WORLD</h1>\r\n"
//				+ "                        </td>\r\n"
//				+ "                    </tr>\r\n"
//				+ "                    <tr>\r\n"
//				+ "                        <td style=\"height:20px;\">&nbsp;</td>\r\n"
//				+ "                    </tr>\r\n"
//				+ "                    <tr>\r\n"
//				+ "                        <td>\r\n"
//				+ "                            <table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"\r\n"
//				+ "                                style=\"max-width:670px;background:#fff; border-radius:3px; text-align:center;-webkit-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);-moz-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);box-shadow:0 6px 18px 0 rgba(0,0,0,.06);\">\r\n"
//				+ "                                <tr>\r\n"
//				+ "                                    <td style=\"height:40px;\">&nbsp;</td>\r\n"
//				+ "                                </tr>\r\n"
//				+ "                                <tr>\r\n"
//				+ "                                    <td style=\"padding:0 35px;\">\r\n"
//				+ "                                        <h1 style=\"color:#1e1e2d; font-weight:500; margin:0;font-size:32px;font-family:'Rubik',sans-serif;\">You have New Message\r\n"
//				+ "                                           </h1>\r\n"
//				+ "                                        <span\r\n"
//				+ "                                            style=\"display:inline-block; vertical-align:middle; margin:29px 0 26px; border-bottom:1px solid #cecece; width:100px;\"></span>\r\n"
//				+ "                                        <p style=\"color:#455056; font-size:15px;line-height:24px; margin:0;\">\r\n"
//				+ "                                           Hey "+sender.get().getCompleteName()+" , You recived new message at "+time
//				+ "                                           <br>\r\n"
//				+ "                                           You can check the details in portal.\r\n"
//				+ "                                        </p>\r\n"
//				+ "                                        <a href=\"https://whatismyipaddress.com/ip/"+Ip+""
//				+ "                                            style=\"background:#20e277;text-decoration:none !important; font-weight:500; margin-top:35px; color:#fff;text-transform:uppercase; font-size:14px;padding:10px 24px;display:inline-block;border-radius:50px;\">\r\n"
//				+ "                                            Check Details</a>\r\n"
//				+ "                                    </td>\r\n"
//				+ "                                </tr>\r\n"
//				+ "                                <tr>\r\n"
//				+ "                              ;      <td style=\"height:40px;\">&nbsp;</td>\r\n"
//				+ "                                </tr>\r\n"
//				+ "                            </table>\r\n"
//				+ "                        </td>\r\n"
//				+ "                    <tr>\r\n"
//				+ "                        <td style=\"height:20px;\">&nbsp;</td>\r\n"
//				+ "                    </tr>\r\n"
//				+ "                    <tr>\r\n"
//				+ "                        <td style=\"text-align:center;\">\r\n"
//				+ "                            <p style=\"font-size:14px; color:rgba(69, 80, 86, 0.7411764705882353); line-height:18px; margin:0 0 0;\">&copy; <strong>www.securedWorld.com</strong></p>\r\n"
//				+ "                        </td>\r\n"
//				+ "                    </tr>\r\n"
//				+ "                    <tr>\r\n"
//				+ "                        <td style=\"height:80px;\">&nbsp;</td>\r\n"
//				+ "                    </tr>\r\n"
//				+ "                </table>\r\n"
//				+ "            </td>\r\n"
//				+ "        </tr>\r\n"
//				+ "    </table>\r\n"
//				+ "    <!--/100% body table-->\r\n"
//				+ "</body>\r\n"
//				+ "\r\n"
//				+ "</html>";
//		
//		try {
//			sendMail.sendNotification(email, "Secured World : Regarding Message", mailBody);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.getMessage();
//		}
//	}
//}
