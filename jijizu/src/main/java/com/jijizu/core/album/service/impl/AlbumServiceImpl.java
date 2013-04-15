package com.jijizu.core.album.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jijizu.base.util.StringUtil;
import com.jijizu.core.album.dao.AlbumDAO;
import com.jijizu.core.album.dto.AlbumInfo;
import com.jijizu.core.album.dto.PhotoInfo;
import com.jijizu.core.album.service.AlbumService;
import com.jijizu.core.check.CheckContext;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.CommonConstant;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.status.dao.StatusDAO;
import com.jijizu.core.status.dto.StatusInfoWithFwdSrc;
import com.jijizu.core.user.dto.UserInfo;

public class AlbumServiceImpl implements AlbumService {

	private AlbumDAO albumDAO;
	
	private StatusDAO statusDAO;
	
	/**
	 * 创建相册验证
	 */
	private CheckContext createAlbumCheckContext;
	
	/**
	 * 删除相册验证
	 */
	private CheckContext deleteAlbumCheckContext;
	
	/**
	 * 编辑相册验证
	 */
	private CheckContext updateAlbumCheckContext;
	
	/**
	 * 删除照片验证
	 */
	private CheckContext deletePhotoCheckContext;
	
	/**
	 * 更新照片验证
	 */
	private CheckContext updatePhotoCheckContext;
	
	/**
	 * 批量移动照片验证
	 */
	private CheckContext movePhotoBatchCheckContext;
	
	/**
	 * 批量删除照片验证
	 */
	private CheckContext deletePhotoBatchCheckContext;
	
	/**
	 * 批量更新照片
	 */
	private CheckContext updatePhotoBatchCheckContext;
	
	/**   
	 *******************************************************************************
	 * @function : 批量更新照片信息
	 * @param sessionUserInfo
	 * @param photoList
	 * @param cover
	 * @param albumId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-16   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult updatePhotoBatch(UserInfo sessionUserInfo,List<PhotoInfo> photoList,Long cover,Long albumId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.ALBUMID, albumId);
		JsonResult result = updatePhotoBatchCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		for(PhotoInfo photo: photoList){
			albumDAO.updatePhotoInfo(photo.getPhotoId(), photo.getName(), photo.getContent());
		}
		
		if(cover != null){
			albumDAO.updateAlbumDefaultPhoto(cover, albumId);
		}
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 批量删除照片
	 * @param sessionUserInfo
	 * @param albumId
	 * @param photoIds
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-15   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult deletePhotoBatch(UserInfo sessionUserInfo,Long albumId,String photoIds){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.ALBUMID, albumId);
		JsonResult result = deletePhotoBatchCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		List<Long> photoList = this.getPhotoIdList(photoIds);
		
		albumDAO.deletePhotoBatch(photoList);
		
		AlbumInfo albumInfo = albumDAO.getAlbumById(albumId,para);
		//如果删除的照片为原来相册的封面，则修改原相册的封面
		for(Long photoId : photoList){
			if(albumInfo.getDefaultPhotoId() != null && 
					albumInfo.getDefaultPhotoId().longValue() == photoId.longValue()){
				this.setAlbumDefaultPhoto(albumId, para);
			}
		}
		
		albumDAO.updateAlbumPhotoNum(albumId);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 批量移动照片
	 * @param sessionUserInfo
	 * @param albumId
	 * @param photoIds
	 * @param sourceAlbumId 原相册ID
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-15   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult movePhotoBatch(UserInfo sessionUserInfo,Long albumId,String photoIds,Long sourceAlbumId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.ALBUMID, albumId);
		JsonResult result = movePhotoBatchCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		List<Long> photoList = this.getPhotoIdList(photoIds);
		
		albumDAO.updatePhotoAlbumIdBatch(albumId, photoList);
		
		AlbumInfo sourceAlbumInfo = albumDAO.getAlbumById(sourceAlbumId);
		//如果移动的照片为原来相册的封面，则修改原相册的封面
		for(Long photoId : photoList){
			if(sourceAlbumInfo.getDefaultPhotoId() != null && 
					sourceAlbumInfo.getDefaultPhotoId().longValue() == photoId.longValue()){
				this.setAlbumDefaultPhoto(sourceAlbumId, para);
			}
		}
		
		//如果目标相册没有封面，则设置一张移动的照片为相册封面
		AlbumInfo albumInfo = albumDAO.getAlbumById(albumId, para);
		if(albumInfo.getDefaultPhotoId() == null){
			albumDAO.updateAlbumDefaultPhoto(photoList.get(0), albumId);
		}
		
		albumDAO.updateAlbumPhotoNum(albumId);
		albumDAO.updateAlbumPhotoNum(sourceAlbumId);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
	}
	
	private List<Long> getPhotoIdList(String photoIds){
		List<Long> photoList = new ArrayList<Long>();
		if(StringUtil.isNotNullOrEmpty(photoIds)){
			String [] ids = photoIds.split("-");
			for(String id:ids){
				photoList.add(Long.parseLong(id));
			}
		}
		return photoList;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 更新照片信息
	 * @param sessionUserInfo
	 * @param photoId
	 * @param content
	 * @param name
	 * @param isSetCover	是否设置为相册封面
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-13   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult updatePhoto(UserInfo sessionUserInfo,Long photoId,String content,String name,boolean isSetCover){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.PHOTOID, photoId);
		JsonResult result = updatePhotoCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		if(isSetCover){
			PhotoInfo photo = albumDAO.getPhotoInfoById(photoId, para);
			albumDAO.updateAlbumDefaultPhoto(photoId, photo.getAlbumId());
		}
		
		albumDAO.updatePhotoInfo(photoId, name, content);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 删除照片
	 * @param sessionUserInfo
	 * @param photoId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-12   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult deletePhoto(UserInfo sessionUserInfo,Long photoId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.PHOTOID, photoId);
		JsonResult result = deletePhotoCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		albumDAO.deletePhoto(photoId);
		
		PhotoInfo photoInfo = albumDAO.getPhotoInfoById(photoId, para);
		AlbumInfo albumInfo = albumDAO.getAlbumById(photoInfo.getAlbumId(), para);
		//如果删除的照片为相册封面，则将最近的一张照片设为专辑封面
		if(albumInfo.getDefaultPhotoId().longValue() == photoInfo.getPhotoId().longValue()){
			setAlbumDefaultPhoto(photoInfo.getAlbumId(), para);
		}
		
		albumDAO.updateAlbumPhotoNum(photoInfo.getAlbumId());
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 设置相册封面
	 * @param albumId
	 * @param para
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-15   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	private void setAlbumDefaultPhoto(Long albumId,Map<String,Object> para){
		PaginationDTO<PhotoInfo> page = albumDAO.findPhotoPage(1, 1, albumId);
		if(page.getRecordCnt() == 0){
			albumDAO.updateAlbumDefaultPhoto(null, albumId);
		}else{
			albumDAO.updateAlbumDefaultPhoto(page.getResult().get(0).getPhotoId(), albumId);
		}
	}
	
	/**   
	 *******************************************************************************
	 * @function : 编辑相册信息
	 * @param sessionUserInfo
	 * @param albumId
	 * @param name
	 * @param content
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-12   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult updateAlbum(UserInfo sessionUserInfo,Long albumId,String name,String content){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.ALBUMID, albumId);
		para.put(CheckParam.NAME, name);
		JsonResult result = updateAlbumCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		albumDAO.updateAlbum(albumId, name, content);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 删除相册
	 * @param sessionUserInfo
	 * @param albumId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-10   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult deleteAlbum(UserInfo sessionUserInfo,Long albumId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.ALBUMID, albumId);
		JsonResult result = deleteAlbumCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		albumDAO.deleteAlbum(albumId);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 创建相册
	 * @param sessionUserInfo
	 * @param name
	 * @param content
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-10   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult createAlbum(UserInfo sessionUserInfo,String name,String content){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.NAME, name);
		JsonResult result = createAlbumCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		Long albumId = albumDAO.saveAlbum(name, content, null, null, sessionUserInfo.getUserId(), sessionUserInfo.getUserId());
		
		AlbumInfo album = albumDAO.getAlbumById(albumId);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG,album);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 创建默认相册
	 * @param userId
	 * @param createUserId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-8   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void createDefaultAlbum(Long userId,Long createUserId){
		
		//默认创建默认相册
		if(albumDAO.getUserDefaultAlbum(userId) == null){
			albumDAO.saveAlbum(CommonConstant.ALBUM_NAME_DEFAULT, null, null, null, userId, createUserId);
		}
		
		//创建微薄配图相册
		if(albumDAO.getUserStatusAlbum(userId) == null){
			albumDAO.saveAlbum(CommonConstant.ALBUM_NAME_STATUS, null, null, null, userId, createUserId);
		}
		
	}
	
	/**   
	 *******************************************************************************
	 * @function : 发布微博照片
	 * @param userId
	 * @param mediaUrl
	 * @param statusId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-9   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public Long postStatusPhoto(Long userId,String mediaUrl,Long statusId){
		
		this.createDefaultAlbum(userId, userId);
		
		AlbumInfo album = albumDAO.getUserStatusAlbum(userId);
		
		Long photoId = albumDAO.savePhoto(null, null, album.getAlbumId(), mediaUrl, statusId, null, userId);
		
		//更改相册默认照片
		albumDAO.updateAlbumDefaultPhoto(photoId, album.getAlbumId());
		
		//更新照片相册数量
		albumDAO.updateAlbumPhotoNum(album.getAlbumId());
		
		return photoId;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查询用户相册
	 * @param pageNum
	 * @param pageSize
	 * @param userId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-9   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PaginationDTO<AlbumInfo> findUserAlbumPage(long pageNum, long pageSize,Long userId){
		return albumDAO.findUserAlbumPage(pageNum, pageSize, userId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查询相册照片
	 * @param pageNum
	 * @param pageSize
	 * @param albumId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-12   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PaginationDTO<PhotoInfo> findPhotoPage(long pageNum, long pageSize,Long albumId){
		return albumDAO.findPhotoPage(pageNum, pageSize, albumId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取相册信息
	 * @param albumId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-12   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public AlbumInfo getAlbumById(Long albumId){
		return albumDAO.getAlbumById(albumId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取照片信息
	 * @param photoId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-12   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PhotoInfo getPhotoInfoById(Long photoId){
		return albumDAO.getPhotoInfoById(photoId);
	}
	
	
	/**   
	 *******************************************************************************
	 * @function : 获取照片信息
	 * @param photoId
	 * @param isLoadStatus 是否加载微博信息
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-15   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PhotoInfo getPhotoInfoById(Long photoId,boolean isLoadStatus){
		PhotoInfo photo = albumDAO.getPhotoInfoById(photoId);
		if(isLoadStatus && photo.getStatusId() != null){
			StatusInfoWithFwdSrc status = statusDAO.getStatusWithFwdSrc(photo.getStatusId());
			photo.setStatus(status);
		}
		return photo;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取上一张照片
	 * @param photoId
	 * @param albumId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-15   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PhotoInfo getPrePhotoById(Long photoId,Long albumId){
		return albumDAO.getPrePhotoById(photoId, albumId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取下一张照片
	 * @param photoId
	 * @param albumId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-15   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PhotoInfo getNextPhotoById(Long photoId,Long albumId){
		return albumDAO.getNextPhotoById(photoId, albumId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 保存照片
	 * @param name
	 * @param content
	 * @param albumId
	 * @param imgUrl
	 * @param statusId
	 * @param groupId
	 * @param createUserId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-9   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public Long savePhoto(String name,String content,Long albumId,String imgUrl,
			Long statusId,Long groupId,Long createUserId){
		Long photoId = albumDAO.savePhoto(name, content, albumId, imgUrl, statusId, groupId, createUserId);
		
		AlbumInfo albumInfo = albumDAO.getAlbumById(albumId);
		if(photoId != null && albumInfo != null && albumInfo.getDefaultPhotoId() == null){
			albumDAO.updateAlbumDefaultPhoto(photoId, albumId);
		}
		albumDAO.updateAlbumPhotoNum(albumId);
		return photoId;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取用户微博配图
	 * @param userId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-9   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public AlbumInfo getUserStatusAlbum(Long userId){
		return albumDAO.getUserStatusAlbum(userId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查询相册照片
	 * @param pageNum
	 * @param pageSize
	 * @param userId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-17   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PaginationDTO<PhotoInfo> findPhotoByUserIdPage(long pageNum, long pageSize,Long userId){
		return albumDAO.findPhotoByUserIdPage(pageNum, pageSize, userId);
	}
	/**   
	 *******************************************************************************
	 * @function : 通过groupId查询照片
	 * @param pageNum
	 * @param pageSize
	 * @param groupId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-17   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PaginationDTO<PhotoInfo> findPhotoByGroupIdPage(long pageNum, long pageSize,Long groupId){
		return albumDAO.findPhotoByGroupIdPage(pageNum, pageSize, groupId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 集集组获取下一张照片
	 * @param photoId
	 * @param groupId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-18   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PhotoInfo getGroupNextPhotoById(Long photoId,Long groupId){
		return albumDAO.getGroupNextPhotoById(photoId, groupId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : getGroupPrePhotoById
	 * @param photoId
	 * @param groupId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-18   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PhotoInfo getGroupPrePhotoById(Long photoId,Long groupId){
		return albumDAO.getGroupPrePhotoById(photoId, groupId);
	}

	public void setAlbumDAO(AlbumDAO albumDAO) {
		this.albumDAO = albumDAO;
	}

	public void setCreateAlbumCheckContext(CheckContext createAlbumCheckContext) {
		this.createAlbumCheckContext = createAlbumCheckContext;
	}

	public void setDeleteAlbumCheckContext(CheckContext deleteAlbumCheckContext) {
		this.deleteAlbumCheckContext = deleteAlbumCheckContext;
	}

	public void setUpdateAlbumCheckContext(CheckContext updateAlbumCheckContext) {
		this.updateAlbumCheckContext = updateAlbumCheckContext;
	}

	public void setDeletePhotoCheckContext(CheckContext deletePhotoCheckContext) {
		this.deletePhotoCheckContext = deletePhotoCheckContext;
	}

	public void setUpdatePhotoCheckContext(CheckContext updatePhotoCheckContext) {
		this.updatePhotoCheckContext = updatePhotoCheckContext;
	}

	public void setMovePhotoBatchCheckContext(
			CheckContext movePhotoBatchCheckContext) {
		this.movePhotoBatchCheckContext = movePhotoBatchCheckContext;
	}

	public void setDeletePhotoBatchCheckContext(
			CheckContext deletePhotoBatchCheckContext) {
		this.deletePhotoBatchCheckContext = deletePhotoBatchCheckContext;
	}

	public void setStatusDAO(StatusDAO statusDAO) {
		this.statusDAO = statusDAO;
	}

	public void setUpdatePhotoBatchCheckContext(
			CheckContext updatePhotoBatchCheckContext) {
		this.updatePhotoBatchCheckContext = updatePhotoBatchCheckContext;
	}
	
}
