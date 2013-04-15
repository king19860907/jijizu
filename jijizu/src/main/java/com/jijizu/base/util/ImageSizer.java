package com.jijizu.base.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图像压缩工具
 * 
 */
public class ImageSizer {
	public static final MediaTracker tracker = new MediaTracker(
			new Component() {
				private static final long serialVersionUID = 1234188888955668507L;
			});

	public static float maxWidth =550.f;//以前压缩成550.f;
	
	public static float maxOriginalWidth = 1700f; //原始图片最大宽度
	
	/**
	 * @param originalFile
	 *            原图像
	 * @param resizedFile
	 *            压缩后的图像
	 * @param width
	 *            图像宽
	 * @param format
	 *            图片格式 jpg, png, gif(非动画)
	 * @throws IOException
	 */
	public static File resize(File originalFile, File resizedFile, float width,
			String format) throws IOException {

		// if (format != null && "gif".equals(format.toLowerCase())) {
		String fileName = originalFile.getName();
		if (fileName.length()>4){
			String ext = fileName.substring(
					fileName.lastIndexOf('.')+1);
			if ("gif".equals(ext)){
				/*if (maxWidth==width){
					return originalFile;
				}*/
				resizeGif(originalFile, resizedFile,width, 1);
				return resizedFile;
			}
		}
		resize(originalFile, resizedFile, width, 1);
		return resizedFile;
		// }
		// FileInputStream fis = new FileInputStream(originalFile);
		// ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		// int readLength = -1;
		// int bufferSize = 1024;
		// byte bytes[] = new byte[bufferSize];
		// while ((readLength = fis.read(bytes, 0, bufferSize)) != -1) {
		// byteStream.write(bytes, 0, readLength);
		// }
		// byte[] in = byteStream.toByteArray();
		// fis.close();
		// byteStream.close();
		//
		// Image inputImage = Toolkit.getDefaultToolkit().createImage(in);
		// waitForImage(inputImage);
		// int imageWidth = inputImage.getWidth(null);
		// if (imageWidth < 1)
		// throw new IllegalArgumentException("image width " + imageWidth
		// + " is out of range");
		// int imageHeight = inputImage.getHeight(null);
		// if (imageHeight < 1)
		// throw new IllegalArgumentException("image height " + imageHeight
		// + " is out of range");
		//
		// // Create output image.
		// int height = -1;
		// double scaleW = (double) imageWidth / (double) width;
		// double scaleY = (double) imageHeight / (double) height;
		// if (scaleW >= 0 && scaleY >= 0) {
		// if (scaleW > scaleY) {
		// height = -1;
		// } else {
		// width = -1;
		// }
		// }
		// Image outputImage = inputImage.getScaledInstance(width, height,
		// java.awt.Image.SCALE_DEFAULT);
		// checkImage(outputImage);
		// encode(new FileOutputStream(resizedFile), outputImage, format);
		//
		// return resizedFile;
	}

	/** Checks the given image for valid width and height. */
	private static void checkImage(Image image) {
		waitForImage(image);
		int imageWidth = image.getWidth(null);
		if (imageWidth < 1)
			throw new IllegalArgumentException("image width " + imageWidth
					+ " is out of range");
		int imageHeight = image.getHeight(null);
		if (imageHeight < 1)
			throw new IllegalArgumentException("image height " + imageHeight
					+ " is out of range");
	}

	/**
	 * Waits for given image to load. Use before querying image
	 * height/width/colors.
	 */
	private static void waitForImage(Image image) {
		try {
			tracker.addImage(image, 0);
			tracker.waitForID(0);
			tracker.removeImage(image, 0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/** Encodes the given image at the given quality to the output stream. */
	private static void encode(OutputStream outputStream, Image outputImage,
			String format) throws java.io.IOException {
		int outputWidth = outputImage.getWidth(null);
		if (outputWidth < 1)
			throw new IllegalArgumentException("output image width "
					+ outputWidth + " is out of range");
		int outputHeight = outputImage.getHeight(null);
		if (outputHeight < 1)
			throw new IllegalArgumentException("output image height "
					+ outputHeight + " is out of range");

		// Get a buffered image from the image.
		BufferedImage bi = new BufferedImage(outputWidth, outputHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D biContext = bi.createGraphics();
		biContext.drawImage(outputImage, 0, 0, null);
		ImageIO.write(bi, format, outputStream);
		outputStream.flush();
	}

	/**
	 * 缩放gif图片
	 * 
	 * @param originalFile
	 *            原图片
	 * @param resizedFile
	 *            缩放后的图片
	 * @param newWidth
	 *            宽度
	 * @param quality
	 *            缩放比例 (等比例)
	 * @throws IOException
	 */
	private static void resize(File originalFile, File resizedFile,
			float newWidth, float quality) throws IOException {
		if (quality < 0 || quality > 1) {
			throw new IllegalArgumentException(
					"Quality has to be between 0 and 1");
		}
		ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());
		Image i = ii.getImage();
		Image resizedImage = null;
		int iWidth = i.getWidth(null);
		int iHeight = i.getHeight(null);

		Image src = javax.imageio.ImageIO.read(originalFile); // 构造Image对象

		BufferedImage originalBufferedImage = new BufferedImage(src.getWidth(null),
				src.getHeight(null), BufferedImage.TYPE_INT_RGB);

		// 获得缩放的比例
		double ratio = 1.0;
		/*if (originalBufferedImage.getHeight() > newWidth
				|| originalBufferedImage.getWidth() > newWidth) {
			if (originalBufferedImage.getHeight() > originalBufferedImage.getWidth()) {
				ratio = newWidth / originalBufferedImage.getHeight();
			} else {
				ratio = newWidth / originalBufferedImage.getWidth();
			}
		}*/
		
		if(newWidth >= maxWidth){
			if(originalBufferedImage.getWidth() > newWidth){
				ratio = newWidth / originalBufferedImage.getWidth();
			}
		}else{
			if (originalBufferedImage.getHeight() > newWidth
					|| originalBufferedImage.getWidth() > newWidth) {
				if (originalBufferedImage.getHeight() > originalBufferedImage.getWidth()) {
					ratio = newWidth / originalBufferedImage.getHeight();
				} else {
					ratio = newWidth / originalBufferedImage.getWidth();
				}
			}
		}
		
		
		// 计算新的图面宽度和高度
		int realWidth = (int) (originalBufferedImage.getWidth() * ratio);
		int realHeight = (int) (originalBufferedImage.getHeight() * ratio);

		
		
		resizedImage = i.getScaledInstance(realWidth,realHeight, Image.SCALE_SMOOTH);
		// This code ensures that all the pixels in the image are loaded.
		Image temp = new ImageIcon(resizedImage).getImage();
		// Create the buffered image.
		BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null),
				temp.getHeight(null), BufferedImage.TYPE_INT_RGB);
		// Copy image to buffered image.
		Graphics g = bufferedImage.createGraphics();
		// Clear background and paint the image.
		g.setColor(Color.white);
		g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));
		g.drawImage(temp, 0, 0, null);
		g.dispose();
		// Soften.
		float softenFactor = 0.05f;
		float[] softenArray = { 0, softenFactor, 0, softenFactor,
				1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };
		Kernel kernel = new Kernel(3, 3, softenArray);
		ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
		bufferedImage = cOp.filter(bufferedImage, null);
		// Write the jpeg to a file.
		FileOutputStream out = new FileOutputStream(resizedFile);
		try {
			// Encodes image as a JPEG data stream
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder
					.getDefaultJPEGEncodeParam(bufferedImage);
			param.setQuality(quality, true);
			encoder.setJPEGEncodeParam(param);
			encoder.encode(bufferedImage);
		} finally {
			out.close();
		}

	}

	/**
	 * 创建图片缩略图(等比缩放)
	 * 
	 * @param src
	 *            源图片文件完整路径
	 * @param dist
	 *            目标图片文件完整路径
	 */
	public static void createThumbnail(String src, String dist) {
		try {
			File srcfile = new File(src);
			if (!srcfile.exists()) {
				System.out.println("文件不存在");
				return;
			}
			BufferedImage image = ImageIO.read(srcfile);

			// 获得缩放的比例
			double ratio = 0.0;
			if (image.getHeight() > 200 || image.getWidth() > 200) {
				if (image.getHeight() > image.getWidth()) {
					ratio = 200.0 / image.getHeight();
				} else {
					ratio = 200.0 / image.getWidth();
				}
			}
			// 计算新的图面宽度和高度
			int newWidth = (int) (image.getWidth() * ratio);
			int newHeight = (int) (image.getHeight() * ratio);

			BufferedImage bfImage = new BufferedImage(newWidth, newHeight,
					BufferedImage.TYPE_INT_RGB);
			bfImage.getGraphics().drawImage(
					image.getScaledInstance(newWidth, newHeight,
							Image.SCALE_SMOOTH), 0, 0, null);

			FileOutputStream os = new FileOutputStream(dist);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
			encoder.encode(bfImage);
			os.close();
			System.out.println("创建缩略图成功");
		} catch (Exception e) {
			System.out.println("创建缩略图发生异常" + e.getMessage());
		}
	}

	/**
		 * 
		 */
	private static void resizeGif(File originalFile, File resizedFile,
			float quality) {
		try {
			Image src = javax.imageio.ImageIO.read(originalFile); // 构造Image对象

			BufferedImage bufferedImage = new BufferedImage(src.getWidth(null),
					src.getHeight(null), BufferedImage.TYPE_INT_RGB);

			// 获得缩放的比例
			double ratio = 1.0;
			/*if (bufferedImage.getHeight() > thumbMaxPx
					|| bufferedImage.getWidth() > thumbMaxPx) {
				if (bufferedImage.getHeight() > bufferedImage.getWidth()) {
					ratio = thumbMaxPx / bufferedImage.getHeight();
				} else {
					ratio = thumbMaxPx / bufferedImage.getWidth();
				}
			}*/
			
			if(bufferedImage.getWidth() > thumbMaxPx){
				ratio = thumbMaxPx / bufferedImage.getWidth();
			}
			
			// 计算新的图面宽度和高度
			int newWidth = (int) (bufferedImage.getWidth() * ratio);
			int newHeight = (int) (bufferedImage.getHeight() * ratio);

			BufferedImage tag = new BufferedImage(newWidth, newHeight,
					BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(src, 0, 0, newWidth, newHeight, null); // 绘制缩小后的图
			FileOutputStream out = new FileOutputStream(resizedFile);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder
			.getDefaultJPEGEncodeParam(bufferedImage);
			param.setQuality(quality, true);
			encoder.encode(tag); // 近JPEG编码
			out.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * 指定最大高和宽的压缩。
	 * 
	 * @param originalFile
	 * @param resizedFile
	 * @param maxPx
	 * @param quality
	 */
	private static void resizeGif(File originalFile, File resizedFile,float maxPx,
			float quality) {
		try {
			Image src = javax.imageio.ImageIO.read(originalFile); // 构造Image对象

			BufferedImage bufferedImage = new BufferedImage(src.getWidth(null),
					src.getHeight(null), BufferedImage.TYPE_INT_RGB);

			// 获得缩放的比例
			double ratio = 1.0;
			/*if (bufferedImage.getHeight() > maxPx
					|| bufferedImage.getWidth() > maxPx) {
				if (bufferedImage.getHeight() > bufferedImage.getWidth()) {
					ratio = maxPx / bufferedImage.getHeight();
				} else {
					ratio = maxPx / bufferedImage.getWidth();
				}
			}*/
			
			if(maxPx>=maxWidth){
				if(bufferedImage.getWidth() > maxPx){
					ratio = maxPx / bufferedImage.getWidth();
				}else if(bufferedImage.getHeight() <= maxPx
						&& bufferedImage.getWidth() <= maxPx){
					return;
				}
			}else{
				if (bufferedImage.getHeight() > maxPx
						|| bufferedImage.getWidth() > maxPx) {
					if (bufferedImage.getHeight() > bufferedImage.getWidth()) {
						ratio = maxPx / bufferedImage.getHeight();
					} else {
						ratio = maxPx / bufferedImage.getWidth();
					}
				}
			}
			
			// 计算新的图面宽度和高度
			int newWidth = (int) (bufferedImage.getWidth() * ratio);
			int newHeight = (int) (bufferedImage.getHeight() * ratio);

			BufferedImage tag = new BufferedImage(newWidth, newHeight,
					BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(src, 0, 0, newWidth, newHeight, null); // 绘制缩小后的图
			FileOutputStream out = new FileOutputStream(resizedFile);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder
			.getDefaultJPEGEncodeParam(bufferedImage);
			param.setQuality(quality, true);
			encoder.encode(tag); // 近JPEG编码
			out.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * 拼装缩略图URL
	 * 
	 * @param originalImg
	 * @return
	 */
	public static String getThumbImg(String originalImg) {
		return rebuildImgURL(originalImg, "t");

	}
	
	/**
	 * 查看上传的原始图片
	 * @param originalImg
	 * @return
	 */
	public static String getInitBigImg(String originalImg) {
		return rebuildImgURL(originalImg, "o");
	}

	/**
	 * 拼装放大后的图URL
	 * 
	 * @param originalImg
	 * @return
	 */
	public static String getMiddleImg(String originalImg) {
		return rebuildImgURL(originalImg, "m");

	}

	/**
	 * 拼装图片的URL，根据原始图片来重组。
	 * 
	 * @param originalImg
	 * @param suffix
	 * @return
	 */
	private static String rebuildImgURL(String originalImg, String suffix) {
		if (originalImg != null && originalImg.indexOf('.') > 1) {
			String imgNameWithoutExt = originalImg.substring(0,
					originalImg.lastIndexOf('.'));

			return imgNameWithoutExt + "_" + suffix
					+ originalImg.substring(originalImg.lastIndexOf('.'));
		}
		return "http://imgblog.yesmyimg.com/microblog/images/headerPhoto.jpg";
	}

	public static float thumbMaxPx = 640.0f;

	public static int width = 550;

	public static int getWidth() {
		return width;
	}

	public static void setWidth(int width1) {
		width = width1;
	}

	/**
	 * 读取文件夹下的所有图片。
	 * 
	 * @param filepath
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static boolean readfile(String filepath)
			throws FileNotFoundException, IOException {
		try {

			File file = new File(filepath);
			if (!file.isDirectory()) {
				System.out.println("文件");
				System.out.println("path=" + file.getPath());
				System.out.println("absolutepath=" + file.getAbsolutePath());
				System.out.println("name=" + file.getName());

			} else if (file.isDirectory()) {
				System.out.println("文件夹");
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File readfile = new File(filepath + "\\" + filelist[i]);
					if (!readfile.isDirectory()) {
						ImageSizer.resize(readfile, new File("d:\\thumbImg\\"
								+ getThumbImg(filelist[i])),
								ImageSizer.thumbMaxPx, "gif");
						// System.out.println("path=" + readfile.getPath());
						// System.out.println("absolutepath="
						// + readfile.getAbsolutePath());
						System.out.println("name=" + readfile.getName());

						// readfile.delete();

					} else if (readfile.isDirectory()) {
						readfile(filepath + "\\" + filelist[i]);
					}
				}

			}

		} catch (FileNotFoundException e) {
			System.out.println("readfile()   Exception:" + e.getMessage());
		}
		return true;
	}

	/**
	 * 用URL来下载线上的图片到本地目录。
	 * 
	 * @param imgURL
	 * @return
	 */
	public static int httpURLConnectionDownload(String imgURL) {
		URL url;
		try {
			String imgPath = "D:\\thumbImg";
			// http://www.yesmywine.com/images/goods_images/3462/88_190.jpg
			url = new URL(imgURL);
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			BufferedInputStream inBuff = new BufferedInputStream(
					urlConnection.getInputStream());

			// 新建文件输出流并对它进行缓冲
			String imgName = imgURL.substring(imgURL.lastIndexOf("/"));
			FileOutputStream output = new FileOutputStream(imgPath + "/"
					+ imgName);
			BufferedOutputStream outBuff = new BufferedOutputStream(output);
			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
			inBuff.close();
			outBuff.close();
			output.close();
			urlConnection.getInputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static void main(String[] args) throws IOException {
	    File file = new File("D:\\thumbImg\\headimg.txt");
	    FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    DataInputStream dis = null;

	      try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	      // Here BufferedInputStream is added for fast reading.
	      bis = new BufferedInputStream(fis);
	      dis = new DataInputStream(bis);

	      // dis.available() returns 0 if the file does not have more lines.
	      while (dis.available() != 0) {

	      // this statement reads the line from the file and print it to
	        // the console.
	    	  String imgName = dis.readLine().substring(dis.readLine().lastIndexOf("/")+1);
				File file1 = new File("D:\\thumbImg" + "\\"
						+ imgName);
				if (!file1.exists()){
					httpURLConnectionDownload(dis.readLine());
					System.out.println(dis.readLine());

				}else{
					
					System.out.println(dis.readLine()+" existed file");
				}
				
	      }

	      // dispose all the resources after using them.
	      fis.close();
	      bis.close();
	      dis.close();
	   

//		try {
//			resize(new File("D:\\uploadImages\\images\\j.jpg"),new File("D:\\uploadImages\\images\\积分1.jpg"),1,"");
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		try {
//			readfile("D:\\uploadImages\\images");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		// System.out.println(getMiddleImg("http://10.1.8.19:9080/UploadImages/1309773548602.jpg"));

		/*
		 * // createThumbnail(
		 * "C:\\Documents and Settings\\All Users\\Documents\\My Pictures\\示例图片\\Windows XP.jpg"
		 * ,
		 * "C:\\Documents and Settings\\All Users\\Documents\\My Pictures\\示例图片\\suc.jpg"
		 * );
		 * 
		 * File sourecFile = new File(
		 * "C:\\Documents and Settings\\All Users\\Documents\\My Pictures\\示例图片\\Windows XP.jpg"
		 * ); File targetFile = new File(
		 * "C:\\Documents and Settings\\All Users\\Documents\\My Pictures\\示例图片\\xp2.jpg"
		 * ); File targetFile1 = new File(
		 * "C:\\Documents and Settings\\All Users\\Documents\\My Pictures\\示例图片\\xp3.jpg"
		 * );
		 * 
		 * ImageSizer.getMiddleImg(
		 * "C:\\Documents and Settings\\All Users\\Documents\\My Pictures\\示例图片\\xp3.jpg"
		 * ); //// File sourecFile = new File(
		 * "C:\\Documents and Settings\\All Users\\Documents\\My Pictures\\示例图片\\积分.gif"
		 * ); //// File targetFile = new File(
		 * "C:\\Documents and Settings\\All Users\\Documents\\My Pictures\\示例图片\\suc.gif"
		 * ); try { ImageSizer.resize(sourecFile, targetFile, 250,"gif");
		 * ImageSizer.resize(sourecFile, targetFile1, 150,"gif"); } catch
		 * e.printStackTrace(); }
		 */

	}
}
