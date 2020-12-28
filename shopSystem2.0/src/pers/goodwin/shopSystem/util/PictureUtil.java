package pers.goodwin.shopSystem.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

import org.junit.Test;

public class PictureUtil {

	/**
	 * @author goodwin
	 * @param base64Img 图片的base64数据
	 * @return url 		返回base64转换成图片的文件名
	 */
	public String savePictureByBase64(String base64Img) {// 将base64格式文件转成图片，存起来，返回图片名
		String url = "1.jpg";	//默认
		String[] pictureMsg = base64Img.split(",");
		//生成图片名
		String time = String.valueOf(new Date().getTime());			//形如：1604854998966
		int beginIndex = pictureMsg[0].indexOf("/") + 1;
		int endIndex =  pictureMsg[0].indexOf(";");
		String type = pictureMsg[0].substring(beginIndex, endIndex);//形如"jpeg"
		url = time + "." + type;	//形如：1604854998966.jpeg
		// /shopSystem2.0/WebContent/goodsPictures
		File file = new File(this.getClass().getClassLoader().getResource("../../goodsPictures").getPath()+ "/" + url);
		//String realPath	=  file.getAbsolutePath();		
		//System.out.println(realPath);
		//输出G:\Java\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\shopSystem\goodsPictures\1604928268193.jpeg
		
		//保存的服务器地址
		if(!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e2) {
				return "1.jpg";
			}
		
		Base64.Decoder decoder = Base64.getDecoder();
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
		} catch (FileNotFoundException e1) {
			return "1.jpg";
		}
		try {
			// Base64解码
			byte[] b = decoder.decode(pictureMsg[1]);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			out.write(b);
			out.flush();
			out.close();
			return url;
		} catch (Exception e) {
			return "1.jpg";		//异常返回默认路径
		}
	}
	
	@Test
	public void imgTest() {
		System.out.println(this.getClass().getClassLoader().getResource("./").getPath());
		String base64Img = "data:image/jpeg;base64,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";
		String[] s = base64Img.split(",");
		new PictureUtil().savePictureByBase64(base64Img);
		System.out.println(new Date().getTime());
		int beginIndex = s[0].indexOf("/") + 1;
		int endIndex =  s[0].indexOf(";");
		String type = s[0].substring(beginIndex, endIndex);
		System.out.println(type);
		System.out.println(System.getProperty("user.dir"));
	}
}
