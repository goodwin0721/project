package pers.goodwin.shopSystem.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class LoadFileTest {
	public void loadFile() throws IOException {
		File f = new File(this.getClass().getClassLoader().getResource("../../goodsPictures").getPath()+ "/2.txt");
		String realPath	=  f.getAbsolutePath();		
		System.out.println(realPath);
		
		//保存的服务器地址
		if(!f.exists())
			f.createNewFile();
		
		FileOutputStream out1 = null;
			try {
				out1 = new FileOutputStream(f);
			
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}

			try {
			
				byte[] b = new byte[1024];
				for (int i = 0; i < b.length; ++i) {
					b[i] = (byte) i;
				}
				out1.write(b);
				out1.flush();
				out1.close();
			} catch (Exception e) {
			}
		
		
		
	}
}
