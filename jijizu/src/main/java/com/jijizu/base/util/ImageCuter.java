package com.jijizu.base.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class ImageCuter implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -691230298274398929L;
	
	public static void cutImage(File srcFile,File destFile, int width, int height) throws IOException {     
	    //File srcFile = new File(srcPath);      
	    BufferedImage image = ImageIO.read(srcFile);      
	    int srcWidth = image.getWidth(null);      
	    int srcHeight = image.getHeight(null);      
	    int newWidth = 0, newHeight = 0;      
	    int x = 0, y = 0;      
	    double scale_w = (double)width/srcWidth;      
	    double scale_h = (double)height/srcHeight;      
	    System.out.println("scale_w="+scale_w+",scale_h="+scale_h);      
	    //按原比例缩放图片      
	    if(scale_w < scale_h) {      
	        newHeight = height;      
	        newWidth = (int)(srcWidth * scale_h);      
	        x = (newWidth - width)/2;      
	    } else {      
	        newHeight = (int)(srcHeight * scale_w);      
	        newWidth = width;      
	        y = (newHeight - height)/2;      
	    }      
	    BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);      
	    newImage.getGraphics().drawImage(      
	    image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);      
	    // 保存缩放后的图片     
	    String fileSufix = srcFile.getName().substring(srcFile.getName().lastIndexOf(".") + 1);  
	   // File destFile = new File(srcFile.getParent(), UUID.randomUUID().toString() + "." + fileSufix     );      
	    // ImageIO.write(newImage, fileSufix, destFile);      
	    // 保存裁剪后的图片      
	    ImageIO.write(newImage.getSubimage(x, y, width, height), fileSufix, destFile);     
	}
	
	public static void main(String [] args){
		/*File file = new File("G:\\abcd.JPG");
		ImageCuter mc = new ImageCuter();
		
		try {
			mc.cutImage(file, 145, 145);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		/*ImageSizer is = new ImageSizer();
		File file2 = new File("G:\\abcd_s.JPG");
		try {
			is.resize(file, file2, 145, "gif");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
}

