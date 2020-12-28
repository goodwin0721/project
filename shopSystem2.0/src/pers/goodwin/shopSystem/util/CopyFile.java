package pers.goodwin.shopSystem.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

public class CopyFile {
	
	
	/**
	 * @param fromAddr 源文件路径
	 * @param toAddr 目标文件路径
	 * @throws IOException 
	 */
	public static void copyFile(String fromAddr,String toAddr) throws IOException {
		File fFrom = new File(fromAddr);
		InputStream input = new FileInputStream(fFrom);
		File fTo = new File(toAddr);
		OutputStream output = new FileOutputStream(fTo);
		byte []b = new byte[1024];
		while((input.read(b)) != -1) {
			output.write(b);
			output.flush();
		}
		input.close();
		output.close();
	}
	
	
	/**
	 * @param fromAddr 源文件路径
	 * @param toAddr 目标文件路径
	 * @throws IOException
	 */
	public static void copyPicture(String fromAddr,String toAddr) throws IOException {
		if(isPicture(fromAddr) && isPicture(toAddr))
			copyFile(fromAddr,toAddr);
	}
	
	/**
	 * @param fileAddr 文件地址
	 * @return 
	 */
	public static boolean isPicture(String fileAddr) {
		String fileTypes = "image/apng,image/bmp,image/gif,image/jpeg,image/jpg,image/pjpeg,image/png,image/svg+xml,image/tiff,image/webp,image/x-icon";
		int index = fileAddr.lastIndexOf(".");
		String fileType = fileAddr.substring(index + 1, fileAddr.length());
	//	System.out.println(fileType);
		if(fileTypes.contains(fileType)) 
			return true;
		else return false;
	}
	
	
	@Test
	public void test() throws IOException {
		CopyFile.copyPicture("G:\\Java\\shopSystem\\src\\pers\\goodwin\\shopSystem\\test\\1.jpg", "G:\\Java\\shopSystem\\src\\pers\\goodwin\\shopSystem\\test\\3.jpg");
	}
}


















