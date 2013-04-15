package com.jijizu.web.album.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jijizu.core.album.dto.AlbumInfo;
import com.jijizu.core.album.dto.PhotoInfo;
import com.jijizu.core.album.service.AlbumService;
import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.user.dto.UserInfo;
import com.jijizu.core.user.service.UserInfoService;
import com.jijizu.web.action.BaseAction;

@Controller
@Scope("prototype")
public class AlbumAction extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4160084739610168116L;
	
	private AlbumService albumService;
	
	private UserInfoService userInfoService;
	
	private Long userId;
	
	private UserInfo userInfo;
	
	private PaginationDTO<AlbumInfo> albumPage;

	private PaginationDTO<PhotoInfo> photoPage;
	
	private long page;
	
	private Long albumId;
	
	private AlbumInfo albumInfo;
	
	private UserInfo belongUser;
	
	private PhotoInfo photoInfo;
	
	private PhotoInfo prePhotoInfo;
	
	private PhotoInfo nextPhotoInfo;
	
	private Long photoId;
	
	private Long length;
	
	public String upload2(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		if(sessionUserInfo == null || albumId == null){
			return INDEX;
		}
		
		albumInfo = albumService.getAlbumById(albumId);
		
		if(albumInfo == null || !albumInfo.canUpload(sessionUserInfo)){
			return INDEX;
		}
		
		userInfo = userInfoService.getUserInfoById(sessionUserInfo.getUserId());
		
		photoPage = albumService.findPhotoPage(0, length, albumId);
		
		return SUCCESS;
		
	}
	
	public String upload(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		if(sessionUserInfo == null){
			return INDEX;
		}else{
			userInfo = userInfoService.getUserInfoById(sessionUserInfo.getUserId());
		}
		
		albumPage = albumService.findUserAlbumPage(0, 100, sessionUserInfo.getUserId());
		
		if(albumId == null && albumPage != null && albumPage.getRecordCnt() > 0){
			albumId = albumPage.getResult().get(0).getAlbumId();
		}
		
		return SUCCESS;
	}
	
	public String photoDetail(){
		
		if(photoId == null){
			return INDEX;
		}
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(sessionUserInfo != null){
			userInfo = userInfoService.getUserInfoById(sessionUserInfo.getUserId());
		}
		
		photoInfo = albumService.getPhotoInfoById(photoId,true);
		
		if(photoInfo == null){
			return INDEX;
		}
		
		albumInfo = albumService.getAlbumById(photoInfo.getAlbumId());
		if(albumInfo != null){
			belongUser = userInfoService.getUserInfoById(albumInfo.getUserId());
		}
		
		nextPhotoInfo = albumService.getNextPhotoById(photoId, photoInfo.getAlbumId());
		
		prePhotoInfo = albumService.getPrePhotoById(photoId, photoInfo.getAlbumId());
		
		return SUCCESS;
	}
	
	public String editPhotoBatch(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(albumId == null && sessionUserInfo == null){
			return INDEX;
		}
		
		albumInfo = albumService.getAlbumById(albumId);
		
		if(albumInfo == null || albumInfo.getCreateUserId().longValue() != sessionUserInfo.getUserId().longValue()){
			return INDEX;
		}
		
		userInfo = userInfoService.getUserInfoById(sessionUserInfo.getUserId());
		
		photoPage = albumService.findPhotoPage(page, 20, albumId);
		
		albumPage = albumService.findUserAlbumPage(0, 100, sessionUserInfo.getUserId());
		
		return SUCCESS;
	}
	
	public String album(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(userId == null && sessionUserInfo == null){
			return INDEX;
		}
		
		if(userId == null && sessionUserInfo != null){
			userId = sessionUserInfo.getUserId();
		}
		
		if(sessionUserInfo != null){
			userInfo = userInfoService.getUserInfoById(sessionUserInfo.getUserId());
		}
		
		albumPage = albumService.findUserAlbumPage(page, 12, userId);
		
		return SUCCESS;
	}
	
	public String photo(){
		
		if(albumId == null){
			return INDEX;
		}
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(sessionUserInfo != null){
			userInfo = userInfoService.getUserInfoById(sessionUserInfo.getUserId());
		}
		
		albumInfo = albumService.getAlbumById(albumId);
		if(albumInfo != null){
			belongUser = userInfoService.getUserInfoById(albumInfo.getUserId());
		}else{
			return "album-index";
		}
		
		photoPage = albumService.findPhotoPage(page, 12, albumId);
		
		return SUCCESS;
	}
	
	public boolean canCreateAlbum(UserInfo sessionUserInfo,Long userId){
		boolean canCreateAlbum = false;
		if(sessionUserInfo != null && userId != null 
				&& sessionUserInfo.getUserId().longValue() == userId.longValue()){
			canCreateAlbum = true;
		}
		return canCreateAlbum;
	}
	
	public boolean canUploadPhoto(UserInfo sessionUserInfo,Long userId){
		boolean canUploadPhoto = false;
		if(sessionUserInfo != null && userId != null 
				&& sessionUserInfo.getUserId().longValue() == userId.longValue()){
			canUploadPhoto = true;
		}
		return canUploadPhoto;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setAlbumService(AlbumService albumService) {
		this.albumService = albumService;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public PaginationDTO<AlbumInfo> getAlbumPage() {
		return albumPage;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	public PaginationDTO<PhotoInfo> getPhotoPage() {
		return photoPage;
	}

	public AlbumInfo getAlbumInfo() {
		return albumInfo;
	}

	public UserInfo getBelongUser() {
		return belongUser;
	}

	public PhotoInfo getPhotoInfo() {
		return photoInfo;
	}

	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}

	public PhotoInfo getPrePhotoInfo() {
		return prePhotoInfo;
	}

	public PhotoInfo getNextPhotoInfo() {
		return nextPhotoInfo;
	}

	public Long getAlbumId() {
		return albumId;
	}

	public void setLength(Long length) {
		this.length = length;
	}

}
