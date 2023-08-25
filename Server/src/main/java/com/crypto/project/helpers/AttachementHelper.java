package com.crypto.project.helpers;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class AttachementHelper {

	/*
	 * public static void main(String[] args) { // TODO Auto-generated method stub
	 * 
	 * }
	 */
	public static String saveAttachment(String companyName,String policyNo,String fileName, String localLocPath, String storeLocPath) {
		
		String docLoc=storeLocPath+File.separator+companyName+File.separator+policyNo;
		String storeDoc=docLoc+File.separator+fileName;
		try {
			/*
			 * EmailDownloadController enwm =new EmailDownloadController();
			 * enwm.saveAttachment(104, "");
			 */
			System.out.println(storeDoc);
			recursiveFolderCration(docLoc);
			copyFile(localLocPath, storeDoc);
			return storeDoc;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		
		return storeDoc;
		
	}
	public static String remEscapeCharEmailSub(String emailSub,String emailInvalidChar) {
		for(int i=0;i<emailInvalidChar.length();i++) {
			emailSub=emailSub.replace(emailInvalidChar.charAt(i)+"", "");
		System.out.println(emailSub);
		}
		return emailSub;
	}
	
	
	public static boolean saveAttachmentList() {
		
		return true;
		
	}
	
	public static ArrayList<String> getAllAttachment() {
		
		return null;
		
	}
	
	public static boolean getAttachmentForAgent() {
		return true;
		
	}
	
	public static void recursiveFolderCration(String Path)
	{
		try
		{
		File directory=new File(Path);
		if(!directory.exists())
		{
			directory.mkdirs();
		}
	//	Logging.debug("RecursiveDirectoryCreation","Path Created: "+Path);
	}catch(Exception e){
		System.out.println(e.getMessage());
		}
	}
	
	public static void copyFile(String source, String dest) throws IOException {
		File sourceObj=new File(source);
		File destObj=new File(dest);
		copyFileObject(sourceObj, destObj);
	}
	
	public static void copyFileObject(File source, File dest) throws IOException {
		Files.copy(source.toPath(), dest.toPath());
	}
	
	public static void createZip(ArrayList attList,String destPath) throws IOException {
		
		FileOutputStream out;

		ZipOutputStream outzip1 = null;
		try {
			outzip1 = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(destPath)));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int a = 0; a < attList.size(); a++) {
			String filePath=(String) attList.get(a);
			File file = new File(filePath);
			File newfile;

			boolean bol = file.isDirectory();
			if (!bol) {
				try {
					// System.out.println("inside of not a directory");
					String fileNameUpper = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
					// System.out.println("ftpFileNameUpper"+ftpFileNameUpper);
					// System.out.println("inside of attachment adding");
					newfile = new File(file.getName().trim());
					// out = new FileOutputStream(attachFiles[a]);

					FileInputStream fin;
					fin = new FileInputStream(filePath);

					ZipEntry n1 =new ZipEntry(fileNameUpper);
					
					outzip1.putNextEntry(new ZipEntry(fileNameUpper));
					int countzip;
					byte[] data1 = new byte[1000];
					while ((countzip = fin.read(data1, 0, 1000)) != -1) {
						outzip1.write(data1, 0, countzip);
					}
					// out.close();
					fin.close();
					/*
					 * File newfile1 = new File(file .getName().trim()); if (newfile1.delete()) {
					 * System.out.println("File deleted"); }
					 */

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {

				}
			}

		}
		if (outzip1 != null)
			outzip1.close();
	}
	
	public static void pack(final Path folder, final Path zipFilePath) throws IOException {
		try (FileOutputStream fos = new FileOutputStream(zipFilePath.toFile());
				ZipOutputStream zos = new ZipOutputStream(fos)) {
			Files.walkFileTree(folder, new SimpleFileVisitor<Path>() {
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					zos.putNextEntry(new ZipEntry(folder.relativize(file).toString()));
					Files.copy(file, zos);
					zos.closeEntry();
					return FileVisitResult.CONTINUE;
				}

				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
					zos.putNextEntry(new ZipEntry(folder.relativize(dir).toString() + File.separator));
					zos.closeEntry();
					return FileVisitResult.CONTINUE;
				}
			});
		}
	}

	public static void zipFolder(String OUTPUT_FOLDER, String ZIP_FILE_PATH) throws IOException {
		pack(Paths.get(OUTPUT_FOLDER), Paths.get(ZIP_FILE_PATH));
	}
	
	public static void recursiveFolderFileCration(String Path, String conFileName) throws IOException
	{
		BufferedWriter br=null;
		try
		{
		File directory=new File(Path);
		if(!directory.exists())
		{
			directory.mkdirs();
		}
		System.out.println(Path);
		//Logging.debug("RecursiveDirectoryCreation","Path Created: "+Path);
		File file= new File(Path+File.separator+conFileName);
		if(!file.exists())
		{
		FileWriter table = new FileWriter(Path+File.separator+conFileName);
		System.out.println(Path+File.separator+conFileName);
		br = new BufferedWriter(table);
		br.write("");
		br.close();
		}
	}catch(Exception e){
		System.out.println(e.getMessage());
		throw e;
		}
		finally {
			
		}
	}

}
