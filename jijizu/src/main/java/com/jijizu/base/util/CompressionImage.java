package com.jijizu.base.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
/**      
 * 项目名称：压缩缩放图片
 * 类名称：ImagesUtilTools   
 * 类描述:  压缩图片的容积
 * 创建人：王养锋 
 * 创建时间：2011-11-10 上午09:24:57
 *    
 */

public class CompressionImage {
    /** 
	 * 图片文件读取 
	 *  
	 * @param srcImgPath 
	 * @return 
	 */  
	   private static BufferedImage InputImage(String srcImgPath) {  
	 
	       BufferedImage srcImage = null;  
	       try {  
	           // 构造BufferedImage对象   
	           File file = new File(srcImgPath);  
	           FileInputStream in = new FileInputStream(file);  
	           byte[] b = new byte[5];  
	           in.read(b);  
	           srcImage = javax.imageio.ImageIO.read(file);  
	       } catch (IOException e) {  
	           System.out.println("读取图片文件出错！" + e.getMessage());  
	           e.printStackTrace();  
	       }  
	       return srcImage;  
	   }  
	   /** 
	     * * 将图片文件输出到指定的路径，并可设定压缩质量 
	     *  
	     * @param outImgPath 
	     * @param newImg 
	     * @param per 
	     */  
	    private static void OutImage(String outImgPath, BufferedImage newImg,  
	            float per) {  
	        // 判断输出的文件夹路径是否存在，不存在则创建   
	        File file = new File(outImgPath);  
	        if (!file.getParentFile().exists()) {  
	            file.getParentFile().mkdirs();  
	        }// 输出到文件流   
	        try {  
	            FileOutputStream newimage = new FileOutputStream(outImgPath);  
	            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);  
	            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(newImg);  
	            // 压缩质量   
	            jep.setQuality(per, true);  
	            encoder.encode(newImg, jep);  
	            newimage.close();  
	        } catch (FileNotFoundException e) {  
	        } catch (ImageFormatException e) {  
	        } catch (IOException e) {  
	        }  
	    }  
	    /** 
	     * * 将图片按照指定的图片尺寸、图片质量压缩 
	     *  
	     * @param srcImgPath 
	     *            :源图片路径 
	     * @param outImgPath 
	     *            :输出的压缩图片的路径 
	     * @param new_w 
	     *            :压缩后的图片宽 
	     * @param new_h 
	     *            :压缩后的图片高 
	     * @param per 
	     *            :百分比 
	     */  
	public static void Compressionpic(String srcImgPath, String outImgPath, float per) {  
     // 得到图片   
     BufferedImage src = InputImage(srcImgPath);  
     int old_w = src.getWidth();  
     // 得到源图宽   
     int old_h = src.getHeight();  
     // 得到源图长   
     // 根据原图的大小生成空白画布   
     BufferedImage tempImg = new BufferedImage(old_w, old_h,  
             BufferedImage.TYPE_INT_RGB);  
     // 在新的画布上生成原图的缩略图   
     Graphics2D g = tempImg.createGraphics();  
     g.setColor(Color.white);  
     g.fillRect(0, 0, old_w, old_h);  
     g.drawImage(src, 0, 0, old_w, old_h, Color.white, null);  
     g.dispose();  
     BufferedImage newImg = new BufferedImage(old_w, old_h,  
             BufferedImage.TYPE_INT_RGB);  
     newImg.getGraphics().drawImage(  
             tempImg.getScaledInstance(old_w, old_h, Image.SCALE_SMOOTH), 0,  
             0, null);  
     // 调用方法输出图片文件   
     OutImage(outImgPath, newImg, per);  
   } 
	   /**
	    * 
	    * fileSize 返回文件大小  单位 k
	    * @param fpath
	    * @return
	    */
	public static long fileSize(String fpath){
		long fs = 0;
		File file = new File(fpath);
     FileInputStream fis = null;
     try{
         fis = new FileInputStream(file);  
         fs = fis.available()/1000;
     }catch(IOException e1){   
         System.out.println("IO出错！");   
     }
		return fs;
	} 
	/** 
	* 压缩图片方法 
	* 
	* @param oldFile 将要压缩的图片 
	* @param width 压缩宽 
	* @param height 压缩高 
	* @param quality 压缩清晰度 建议为1.0 
	* @param smallIcon 压缩图片后,添加的扩展名（在图片后缀名前添加） 
	* @param percentage 是否等比压缩 若true宽高比率将将自动调整 
	* @author zhengsunlei 
	* @return 如果处理正确返回压缩后的文件名 null则参数可能有误 
	*/ 
	public static String doCompress(String oldFile, int width, int height,
			float quality, String smallIcon, boolean percentage) {
		if (oldFile != null && width > 0 && height > 0) {
			String newImage = null;
			try {
				File file = new File(oldFile);
				// 文件不存在
				if (!file.exists()) {
					return null;
				}
				/* 读取图片信息 */
				Image srcFile = ImageIO.read(file);
				int new_w = width;
				int new_h = height;
				if (percentage) {
					// 为等比缩放计算输出的图片宽度及高度
					double rate1 = ((double) srcFile.getWidth(null))
							/ (double) width + 0.1;
					double rate2 = ((double) srcFile.getHeight(null))
							/ (double) height + 0.1;
					double rate = rate1 > rate2 ? rate1 : rate2;
					new_w = (int) (((double) srcFile.getWidth(null)) / rate);
					new_h = (int) (((double) srcFile.getHeight(null)) / rate);
				}
				/* 宽高设定 */
				BufferedImage tag = new BufferedImage(new_w, new_h,
						BufferedImage.TYPE_INT_RGB);
				tag.getGraphics().drawImage(srcFile, 0, 0, new_w, new_h, null);
				/* 压缩后的文件名 */
				String filePrex = oldFile
						.substring(0, oldFile.lastIndexOf('.'));
				newImage = filePrex + smallIcon
						+ oldFile.substring(filePrex.length());
				/* 压缩之后临时存放位置 */
				FileOutputStream out = new FileOutputStream(newImage);
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
				/* 压缩质量 */
				jep.setQuality(quality, true);
				encoder.encode(tag, jep);
				out.close();
				srcFile.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return newImage;
		} else {
			return null;
		}
	}

	/**
	 * 获得文件后缀
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExt(String filename) {
		return filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
	}
 
}

