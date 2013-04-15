package com.jijizu.base.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.swing.ImageIcon;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.struts2.ServletActionContext;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtils {

	private int name_x;// 文字的x轴坐标
	private int name_y;// 文字的y轴坐标

	private int logo_x;// 小图片的x轴坐标
	private int logo_y;// 小图片的x轴坐标

/*	*//**
	 * @param args
	 * @throws IOException
	 *//*
	public static void main(String[] args) throws IOException {
		ImageUtils c = new ImageUtils();

		String[] values = new String[] {
				"格拉夫普拉多城堡干红葡萄酒",
				"英文名字",
				"288",
				"2008",
				"干红",
				"11酒品介绍酒品介绍酒品介绍酒品介123456789<br>品介绍酒品12345678901234567介绍品介绍酒品介绍酒品介绍酒品介绍酒品介绍干红88123456789" };
		ByteArrayOutputStream out = c.createJpgByFont(
				"http://localhost:8080/microblog/images/background.jpg",
				"http://img05.yesmywine.com/9336/110x180.jpg", values,
				"C:\\temp.jpg");
		System.out.println(out.size());
	}*/

	/**
	 * 根据提供的文字生成jpg图片
	 * 
	 * @param backJpgURL
	 *            背景图片路径
	 * @param wineImageURL
	 *            酒的图片URL，如：http://img05.yesmywine.com/9336/110x180.jpg
	 * @param values
	 *            文字信息
	 * @param outputJpgName
	 *            生成的新图片名称
	 */
	public ByteArrayOutputStream createJpgByFont(String backJpgURL,
			String wineImageURL, String[] values, String outputJpgName) {
		try {

			// 宽度 高度
			setName_x(130);
			setName_y(43);

			setLogo_x(0);
			setLogo_y(10);
			// String s = "";
			// File fileOne = new File(backJpgPath);
			// BufferedImage bimage = ImageIO.read(fileOne);
			BufferedImage bimage = ImageIO.read(new URL(backJpgURL));
			Graphics2D g = bimage.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON); // 去除锯齿(当设置的字体过大的时候,会出现锯齿)

			Font font = Font.decode("宋体");// Courier New sans-serif

			g.setColor(Color.decode("#000000")); // 字的颜色
			g.setFont(font.deriveFont(Float.parseFloat("15"))); // 对象并应用新设置字体的大小
			g.drawString(values[0] == null ? "" : values[0], this.getName_x(),
					this.getName_y()); // 在指定坐标除添加文字

			g.setColor(Color.decode("#666666")); // 字的颜色
			g.setFont(font.deriveFont(Float.parseFloat("12"))); // 对象并应用新设置字体的大小
			g.drawString(values[1] == null ? "" : values[1], this.getName_x(),
					this.getName_y() + 20); // 在指定坐标除添加文字

			g.setColor(Color.decode("#666666")); // 字的颜色
			g.setFont(font.deriveFont(Float.parseFloat("12"))); // 对象并应用新设置字体的大小
			g.drawString("也买价：", this.getName_x(), this.getName_y() + 40); // 在指定坐标除添加文字
			g.setColor(Color.decode("#ff0000")); // 字的颜色
			g.setFont(font.deriveFont(Float.parseFloat("12"))); // 对象并应用新设置字体的大小
			g.drawString("￥" + values[2] == null ? "" : values[2], this
					.getName_x() + 70, this.getName_y() + 40); // 在指定坐标除添加文字

			g.setColor(Color.decode("#000000")); // 字的颜色
			g.setFont(font.deriveFont(Float.parseFloat("12"))); // 对象并应用新设置字体的大小
			g.drawString("参考年份：", this.getName_x(), this.getName_y() + 60); // 在指定坐标除添加文字
			g.setColor(Color.decode("#000000")); // 字的颜色
			g.setFont(font.deriveFont(Float.parseFloat("12"))); // 对象并应用新设置字体的大小
			g.drawString(values[3] == null ? "" : values[3],
					this.getName_x() + 70, this.getName_y() + 60); // 在指定坐标除添加文字

			g.setColor(Color.decode("#000000")); // 字的颜色
			g.setFont(font.deriveFont(Float.parseFloat("12"))); // 对象并应用新设置字体的大小
			g.drawString("葡萄酒种类：", this.getName_x(), this.getName_y() + 80); // 在指定坐标除添加文字
			g.setColor(Color.decode("#000000")); // 字的颜色
			g.setFont(font.deriveFont(Float.parseFloat("12"))); // 对象并应用新设置字体的大小
			g.drawString(values[4] == null ? "" : values[4],
					this.getName_x() + 70, this.getName_y() + 80); // 在指定坐标除添加文字

			g.setColor(Color.decode("#000000")); // 字的颜色
			g.setFont(font.deriveFont(Float.parseFloat("12"))); // 对象并应用新设置字体的大小
			g.drawString("产地：", this.getName_x(), this.getName_y() + 100); // 在指定坐标除添加文字

			writeNewLine(this.getName_x() + 70, this.getName_y() + 100,
					values[5], g, font);
			// g.setColor(Color.decode("#000000")); // 字的颜色
			// g.setFont(font.deriveFont(Float.parseFloat("12"))); //
			// 对象并应用新设置字体的大小
			// g.drawString("酒品介绍酒品介绍酒品介绍酒品介绍酒\n<br>品介绍酒品介绍酒品介\n绍酒品介绍酒品介绍酒品介绍酒品介绍酒品介绍干红88",
			// this.getName_x() + 70, this.getName_y() + 100); // 在指定坐标除添加文字

			g.dispose();// 释放资源

			// File filelogo = new File(wineImage);
			// BufferedImage bimageLogo = ImageIO.read(filelogo);
			// 酒的图片URL地址
			BufferedImage bimageLogo = ImageIO.read(new URL(wineImageURL));
			int width = bimageLogo.getWidth(); // 图片宽度
			int height = bimageLogo.getHeight(); // 图片高度
			// 从图片中读取RGB
			int[] ImageArrayOne = new int[width * height];
			ImageArrayOne = bimageLogo.getRGB(0, 0, width, height,
					ImageArrayOne, 0, width);
			bimage.setRGB(this.getLogo_x(), this.getLogo_y(), width, height,
					ImageArrayOne, 0, width);
			// 指定输出文件
			// FileOutputStream out = new FileOutputStream(outputJpgName); //
			// JPEGImageEncoder encoder1 = JPEGCodec.createJPEGEncoder(out);
			// JPEGEncodeParam param1 =
			// encoder1.getDefaultJPEGEncodeParam(bimage);
			// param1.setQuality(1.0f, true);
			// encoder1.encode(bimage, param1); // 存盘
			// out.flush();
			// out.close();
			java.io.ByteArrayOutputStream out1 = new ByteArrayOutputStream();
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out1);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);
			param.setQuality(1.0f, true);
			encoder.encode(bimage, param); // 存盘
			out1.flush();
			out1.close();
			return out1;
		} catch (Exception e) {
			System.out.println("createJpgByFont Failed!");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将多出30字的换行。
	 * 
	 * @param x
	 * @param y
	 * @param content
	 * @param g
	 */
	public void writeNewLine(int x, int y, String content, Graphics2D g,
			Font font) {
		if (content != null) {

			int countPerLine = 18;
			g.setColor(Color.decode("#000000")); // 字的颜色
			g.setFont(font.deriveFont(Float.parseFloat("12"))); // 对象并应用新设置字体的大小
			if (content.length() > countPerLine) {
				g.drawString(content.substring(0, countPerLine), x, y); // 在指定坐标除添加文字

				if (content.length() > 2 * countPerLine + 10) {
					g.drawString(content.substring(countPerLine,
							2 * countPerLine + 5)
							+ "...", x - 70, y + 20); // 在指定坐标除添加文字s
				} else {
					g.drawString(content.substring(countPerLine, content
							.length()), x - 70, y + 20); // 在指定坐标除添加文字s
				}

			} else {
				g.drawString(content, x, y); // 在指定坐标除添加文字
			}
		}
	}

	public int getLogo_x() {
		return logo_x;
	}

	public int getLogo_y() {
		return logo_y;
	}

	public int getName_x() {
		return name_x;
	}

	public int getName_y() {
		return name_y;
	}

	public void setName_y(int name_y) {
		this.name_y = name_y;
	}

	public void setName_x(int name_x) {
		this.name_x = name_x;
	}

	public void setLogo_y(int logo_y) {
		this.logo_y = logo_y;
	}

	public void setLogo_x(int logo_x) {
		this.logo_x = logo_x;
	}

	/**
	 * 
	 * 进行图片的裁切。
	 * 
	 * @param src_file
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @throws Exception
	 */
	public static File cutImageByDrag(File src_file, int x, int y, int w, int h)
		throws Exception {
			//将上传的图片文件和目标文件设成一个，即根据范围覆盖原图。
			File DEST_FILE = src_file;
			
			String fileName = src_file.getName();
			String format = "jpg";
			if (fileName.endsWith("jpg")){
				format = "jpg";
			}else if (fileName.endsWith("gif")){
				format = "gif";
			}else if (fileName.endsWith("png")){
				format = "png";
			}
			// 取得图片读入器
			Iterator readers = ImageIO.getImageReadersByFormatName(format);
			ImageReader reader = (ImageReader) readers.next();
			// 取得图片读入流
			InputStream source = new FileInputStream(src_file);
			ImageInputStream iis = ImageIO.createImageInputStream(source);
			reader.setInput(iis, true);
			
			// 图片参数
			ImageReadParam param = reader.getDefaultReadParam();
			
			//重新计算图片的裁切尺寸。开始
			BufferedImage image = ImageIO.read(src_file);
			
			if (image.getHeight() < y+h) {
				h = image.getHeight() - y;
			}
			if (image.getWidth() < x+w) {
				h = image.getWidth() - x;
			}
			if (x<0){
				x=0;
			}
			if (y<0){
				y=0;
			}
			//重新计算图片的裁切尺寸。结束
			
			Rectangle rect = new Rectangle(x, y, w, h);// 100，200是左上起始位置，300就是取宽度为300的，就是从100开始取300宽，就是横向100~400，同理纵向200~350的区域就取高度150
			param.setSourceRegion(rect);
			BufferedImage bi = reader.read(0, param);
			ImageIO.write(bi, format, DEST_FILE);
			
			reader.dispose();
			return DEST_FILE;
		}
	
	public static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 上传图片
	 * @param fileName
	 * @param picture
	 * @param request
	 * @param rate    压缩率
	 * @return
	 * @throws IOException
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-11-23   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public static String fileUpload(String fileName,File picture,HttpServletRequest request,float rate) throws IOException{
		String originalFileName = new Date().getTime() + "";

		String imageFileName = originalFileName + getExtention(fileName);
		String fn = getImgFilePath(null);
		File f1 = new File(ServletActionContext.getServletContext()
				.getRealPath(fn));
		if (!f1.exists()) {
			f1.mkdirs();
		}
		File imageFile = new File(ServletActionContext.getServletContext()
				.getRealPath(fn) + "/" + imageFileName);
		
		File originalFile = new File(ServletActionContext.getServletContext()
				.getRealPath(fn)
				+ "/" + originalFileName+"_o"+ getExtention(fileName));

		// 上传图片到服务器。
		copy(picture, imageFile);
		
		copy(imageFile,originalFile);

		if (!".gif".equals(getExtention(fileName))) {
			Thumbnails.of(imageFile).scale(1).toFile(imageFile);
		}

		// 对图片进行压缩处理。
		try {
			// 1.压缩成缩略图
			File thumb = new File(ServletActionContext.getServletContext()
					.getRealPath(fn)
					+ "/"
					+ originalFileName
					+ "_t"
					+ getExtention(fileName));

			ImageSizer.resize(imageFile, thumb, 120.0f, "gif");

			ImageIcon ii = new ImageIcon(imageFile.getCanonicalPath());
			Image i = ii.getImage();
			// 如果原图的高度大于长度的5倍则不进行等比压缩，应为这样会导致压缩后的图片看不清楚 add by majun
			// 2011-12-1
			if (i.getHeight(null) / i.getWidth(null) < 6) {

				// 原图压缩成550的大小
				ImageSizer.resize(imageFile, imageFile,
						550, "gif");
			}

			// 如果为gif图片则不进行有损压缩
			if (!".gif".equals(getExtention(fileName))) {
				CompressionImage
						.Compressionpic(imageFile.getAbsolutePath(),
								imageFile.getAbsolutePath(), rate);
			}
		} catch (IOException e) {

		}

		String absolutePath = request.getRequestURL().toString();
		String rootPath = absolutePath.replaceAll(request
				.getServletPath(), "");

		/*rootPath = rootPath.replace("http://www.yesmywine.com",
				MicroBlogUtil.IMG_PATH);*/
		String filepath = rootPath + fn + "/" + imageFileName;
		return filepath;

	}
	
	
	/**   
	 *******************************************************************************
	 * @function : 生成缩略图
	 * @param srcFile			源图
	 * @param originalFileName  原图文件名
	 * @param fileExt			原图后缀名
	 * @param imgPath			原图文件目录
	 * @param headImgSize		要生成的图片大小
	 * @throws IOException
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-15   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public static void createSmallImage(File srcFile,String originalFileName,String imgPath,String fileExt,int ...headImgSize) throws IOException{
		for(int size : headImgSize){
			File headImg = new File(ServletActionContext.getServletContext()
					.getRealPath(imgPath)
					+ "/"
					+ originalFileName + "_"+size + ImageUtils.getExtention(fileExt));
		
			ImageSizer.resize(srcFile, headImg, size, "gif");
		}
	}
	
	private static final int BUFFER_SIZE = 16 * 1024;
	private static void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**   
	 *******************************************************************************
	 * @function : 验证所上传的文件大小及格式是否正确 
	 * @param file
	 * @param fileName
	 * @param maxLength  最大长度，以M为单位
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2011-4-22   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public static String validateImgFile(File file,String fileName,int maxLength){
		String errorMsg = "";
		
		if(file == null){
			errorMsg = "对不起，您所要上传的文件不存在!";
		}
		
		//文件大小
		double fileSize = Arith.div(file.length(), 1024*1024, maxLength);
		if(fileSize > maxLength){
			errorMsg = "对不起，您上传的图片文件过大，请上传"+maxLength+"M以下的图片!";
		}
		
		int extStart = fileName.lastIndexOf(".");
		String ext = fileName.substring(extStart,fileName.length()).toLowerCase();
		if(!".jpg".equals(ext) && !".png".equals(ext) && !".gif".equals(ext) ){
			errorMsg = "对不起，您上传的图片格式不正确，仅支持Png,Jpg,Gif!";
		}
		return errorMsg;
	}
	
	/**
	 * 取得图片目录，存放于年月目录
	 * @param parentFilePath 父目录 null=默认UploadImages
	 * @return 图片所在目录
	 */
	public static String getImgFilePath(String parentFilePath){
		if(parentFilePath == null){
			parentFilePath = "UploadImages";
		}
		return "/" + parentFilePath + "/" + DateUtil.getYearByDate(new Date())+"/"+DateUtil.getMonthByDate(new Date())+"/"+DateUtil.getDayByDate(new Date());
	}
	
	/**   
	 *******************************************************************************
	 * @function : 判断两个文件是否相同
	 * @param file1
	 * @param file2
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-11-23   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public static boolean isEqualBetweenFiles(File file1,File file2) {
		
		if(file1.length() != file2.length()) return false;
		
		return isEqualBetweenFileWithByte(file1, file2);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 判断两个文件的二进制流是否相同
	 * @param file1
	 * @param file2
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-11-23   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	private static boolean isEqualBetweenFileWithByte(File file1,File file2) {
		
		InputStream inFile1 = null;
		InputStream inFile2 = null;
		
		try {
			inFile1 = new BufferedInputStream(new FileInputStream(file1),
					BUFFER_SIZE);
			inFile2 = new BufferedInputStream(new FileInputStream(file2),
					BUFFER_SIZE);
			byte[] buffer1 = new byte[BUFFER_SIZE];
			byte[] buffer2 = new byte[BUFFER_SIZE];
			MessageDigest digest = MessageDigest.getInstance("MD5");
			while (inFile1.read(buffer1) > 0 && inFile2.read(buffer2) > 0) {
				if(new BigInteger(buffer1).equals(digest.digest(buffer2))) {
					return false;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static void main(String [] args) {
		File file1 = new File("F:\\test.JPG");
		File file2 = new File("F:\\test2.JPG");
		
		System.out.println(isEqualBetweenFiles(file1, file2));
	}
	
}
