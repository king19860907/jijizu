package com.jijizu.web.action;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jijizu.base.util.ImageUtils;

@Controller
@Scope("prototype")
public class FileUploadAjax extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1051601976201594642L;
	
	private Log log = LogFactory.getLog(this.getClass());
	
	private File myFile;
	
	private String fileName;
	
	private float rate = 0.85f;

	public String fileUploadStatusAjax(){
		try {
			
			String errorMsg = ImageUtils.validateImgFile(myFile, fileName,5);
			if (errorMsg != null && !"".equals(errorMsg)) {
				this.outString(errorMsg);
				return NONE;
			}
			
			String pictureURL = ImageUtils.fileUpload(fileName, myFile, request,rate);
			this.outString(pictureURL);
		} catch (Exception e) {
			log.error(e);
		}
		
		return NONE;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void setMyFileFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

}
