package com.image.Controller;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.imageio.ImageIO;

public class GetImage {

	
	public static String authentication() {
		
		
		
		FileInputStream fn,fn2;
		
		try {
		
			fn = new FileInputStream("D:/upload/naturer.jpg");  // getting uploaded image
			BufferedImage detectface = ImageIO.read(fn);
			
			
			int	width_u = detectface.getWidth();
	//		System.out.println("img recived "+upload+"width "+width_u);
			
		//	int	height_u = upload.getHeight();
			
			fn2 = new FileInputStream("D:/saved/naturer.jpg");   // getting saved image
			BufferedImage saved = ImageIO.read(fn2);
			int	width_s = saved.getWidth();
			int	height_s = saved.getHeight();
	//		System.out.println("img recived "+saved+"height "+height_s);	
		
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return "true";
		
		}

	public static String faceDetection() {
		
		
		
		
					
		return "true";
	}
	
	
	
	
		
		
		

	

}
