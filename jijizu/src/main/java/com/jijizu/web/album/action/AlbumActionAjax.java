package com.jijizu.web.album.action;

import java.io.File;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jijizu.base.util.ImageUtils;
import com.jijizu.core.album.dto.PhotoInfo;
import com.jijizu.core.album.service.AlbumService;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.user.dto.UserInfo;
import com.jijizu.web.action.BaseAction;

@Controller
@Scope("prototype")
public class AlbumActionAjax extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3287717800957932632L;
	
	private AlbumService albumService;
	
	private String name;
	
	private String content;
	
	private Long albumId;
	
	private Long photoId;
	
	private boolean isCover;
	
	private String photoIds;
	
	private Long sourceAlbumId;
	
	private File file;
	 
	private float rate = 0.85f;
	
	//设置为相册分面的photoId
	private Long coverId;
	
	private List<PhotoInfo> photoList;
	
	private Long userId;
	
	public String updatePhotoList(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = albumService.updatePhotoBatch(sessionUserInfo, photoList, coverId, albumId);
		
		this.outObjectToJson(result);
		
		return NONE;
		
	}
	
	public String fileUpload(){
		try {
			
			/*UserInfo sessionUserInfo = getSessionUserInfo();
			
			if(sessionUserInfo == null){
				return NONE;
			}*/
			
			String errorMsg = ImageUtils.validateImgFile(file, name,20);
			if (errorMsg != null && !"".equals(errorMsg)) {
				this.outString(errorMsg);
				System.out.println(errorMsg);
				return NONE;
			}
			
			String pictureURL = ImageUtils.fileUpload(name, file, request,rate);
			albumService.savePhoto(null, null, albumId, pictureURL, null, null, userId);
		} catch (Exception e) {
			log.error(e);
		}
		
		return NONE;
	}
	
	public String deletePhotoBatch(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = albumService.deletePhotoBatch(sessionUserInfo, albumId, photoIds);
		
		this.outObjectToJson(result);
		
		return NONE;
		
	}
	
	public String movePhotoBatch(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = albumService.movePhotoBatch(sessionUserInfo, albumId, photoIds,sourceAlbumId);
		
		this.outObjectToJson(result);
		
		return NONE;
		
	}
	
	public String updatePhoto(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = albumService.updatePhoto(sessionUserInfo, photoId, content, name, isCover);
		
		this.outObjectToJson(result);
		
		return NONE;
		
	}
	
	public String deletePhoto(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = albumService.deletePhoto(sessionUserInfo, photoId);
		
		this.outObjectToJson(result);
		
		return NONE;
		
	}
	
	public String updateAlbum(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = albumService.updateAlbum(sessionUserInfo, albumId, name, content);
		
		this.outObjectToJson(result);
		
		return NONE;
	}
	
	public String createAlbum(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = albumService.createAlbum(sessionUserInfo, name, content);
		
		this.outObjectToJson(result);
		
		return NONE;
	}
	
	public String deleteAlbum(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = albumService.deleteAlbum(sessionUserInfo, albumId);
		
		this.outObjectToJson(result);
		
		return NONE;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setAlbumService(AlbumService albumService) {
		this.albumService = albumService;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	public Long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}

	public void setCover(boolean isCover) {
		this.isCover = isCover;
	}

	public void setPhotoIds(String photoIds) {
		this.photoIds = photoIds;
	}

	public void setSourceAlbumId(Long sourceAlbumId) {
		this.sourceAlbumId = sourceAlbumId;
	}
	
	public void setRate(float rate) {
		this.rate = rate;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public List<PhotoInfo> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(List<PhotoInfo> photoList) {
		this.photoList = photoList;
	}

	public boolean isCover() {
		return isCover;
	}

	public void setCoverId(Long coverId) {
		this.coverId = coverId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
