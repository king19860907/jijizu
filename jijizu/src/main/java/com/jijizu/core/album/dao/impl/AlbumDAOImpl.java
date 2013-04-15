package com.jijizu.core.album.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jijizu.core.album.dao.AlbumDAO;
import com.jijizu.core.album.dto.AlbumInfo;
import com.jijizu.core.album.dto.PhotoInfo;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.dao.IbatisBaseDAO;
import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.group.dto.GroupInfo;

public class AlbumDAOImpl extends IbatisBaseDAO
	implements AlbumDAO{

	
	/**   
	 *******************************************************************************
	 * @function : 保存相册
	 * @param name
	 * @param content
	 * @param groupId
	 * @param defaultPhotoId
	 * @param userId
	 * @param createUserId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-8   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public Long saveAlbum(String name,String content,Long groupId,
			Long defaultPhotoId,Long userId,Long createUserId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("name", name);
		para.put("content", content);
		para.put("groupId", groupId);
		para.put("defaultPhotoId", defaultPhotoId);
		para.put("userId", userId);
		para.put("createUserId", createUserId);
		return (Long)this.insert("saveAlbum", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取用户默认相册
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
	public AlbumInfo getUserDefaultAlbum(Long userId){
		return (AlbumInfo)this.selectOneObject("getUserDefaultAlbum", userId);
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
		return (AlbumInfo)this.selectOneObject("getUserStatusAlbum", userId);
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
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("name", name);
		para.put("content", content);
		para.put("albumId", albumId);
		para.put("imgUrl", imgUrl);
		para.put("statusId", statusId);
		para.put("groupId", groupId);
		para.put("createUserId", createUserId);
		return (Long)this.insert("savePhoto", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 更新相册默认照片
	 * @param defaultPhotoId
	 * @param albumId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-9   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void updateAlbumDefaultPhoto(Long defaultPhotoId,Long albumId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("defaultPhotoId", defaultPhotoId);
		para.put("albumId", albumId);
		this.update("updateAlbumDefaultPhoto",para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 更新相册照片数量
	 * @param albumId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-9   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void updateAlbumPhotoNum(Long albumId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("albumId", albumId);
		this.update("updateAlbumPhotoNum",para);
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
	public PaginationDTO<AlbumInfo> findUserAlbumPage(long pageNum, long pageSize,Long userId) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		PaginationDTO<AlbumInfo> pagination = new PaginationDTO<AlbumInfo>();
		pagination.setPara(para);
		pagination.setPerPageRecordNum(pageSize);
		pagination.setCurPageNum(pageNum);
		return this.selectPage("findUserAlbumPage", pagination);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取相册信息
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
	public AlbumInfo getAlbumById(Long albumId){
		return (AlbumInfo)this.selectOneObject("getAlbumById", albumId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取相册信息-验证框架使用
	 * @param albumId
	 * @param para
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
	public AlbumInfo getAlbumById(Long albumId,Map<String,Object> para){
		AlbumInfo album = null;
		if(para != null && para.get(CheckParam.TEMP_ALBUMINFO) != null){
			album = (AlbumInfo)para.get(CheckParam.TEMP_ALBUMINFO);
		}else{
			album = (AlbumInfo)this.selectOneObject("getAlbumById", albumId);
			if(album != null){
				para.put(CheckParam.TEMP_ALBUMINFO, album);
			}
		}
		return album;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 删除相册
	 * @param albumId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-10   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void deleteAlbum(Long albumId){
		this.update("deleteAlbum",albumId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 更新相册信息
	 * @param albumId
	 * @param name
	 * @param content
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-12   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void updateAlbum(Long albumId,String name,String content){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("albumId", albumId);
		para.put("name", name);
		para.put("content", content);
		this.update("updateAlbum",para);
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
	public PaginationDTO<PhotoInfo> findPhotoPage(long pageNum, long pageSize,Long albumId) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("albumId", albumId);
		PaginationDTO<PhotoInfo> pagination = new PaginationDTO<PhotoInfo>();
		pagination.setPara(para);
		pagination.setPerPageRecordNum(pageSize);
		pagination.setCurPageNum(pageNum);
		return this.selectPage("findPhotoPage", pagination);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 删除照片
	 * @param photoId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-12   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void deletePhoto(Long photoId){
		this.update("deletePhoto",photoId);
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
		return (PhotoInfo)this.selectOneObject("getPhotoInfoById", photoId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取照片信息-验证框架使用
	 * @param photoId
	 * @param para
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
	public PhotoInfo getPhotoInfoById(Long photoId,Map<String,Object> para){
		PhotoInfo photo = null;
		if(para != null && para.get(CheckParam.TEMP_PHOTOINFO) != null){
			photo = (PhotoInfo)para.get(CheckParam.TEMP_PHOTOINFO);
		}else{
			photo = (PhotoInfo)this.selectOneObject("getPhotoInfoById", photoId);
			if(photo != null){
				para.put(CheckParam.TEMP_PHOTOINFO, photo);
			}
		}
		return photo;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 更新照片信息
	 * @param photoId
	 * @param name
	 * @param content
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-13   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void updatePhotoInfo(Long photoId,String name,String content){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("photoId", photoId);
		para.put("name", name);
		para.put("content", content);
		this.update("updatePhotoInfo",para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 批量修改照片所属相册
	 * @param albumId
	 * @param photoIds
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-15   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void updatePhotoAlbumIdBatch(Long albumId,List<Long> photoIds){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("albumId", albumId);
		para.put("photoIds", photoIds);
		this.update("updatePhotoAlbumIdBatch", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : deletePhotoBatch
	 * @param photoIds
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-15   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void deletePhotoBatch(List<Long> photoIds){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("photoIds", photoIds);
		this.update("deletePhotoBatch", para);
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
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("photoId", photoId);
		para.put("albumId", albumId);
		return (PhotoInfo)this.selectOneObject("getNextPhotoById", para);
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
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("photoId", photoId);
		para.put("albumId", albumId);
		return (PhotoInfo)this.selectOneObject("getPrePhotoById", para);
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
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("photoId", photoId);
		para.put("groupId", groupId);
		return (PhotoInfo)this.selectOneObject("getGroupNextPhotoById", para);
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
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("photoId", photoId);
		para.put("groupId", groupId);
		return (PhotoInfo)this.selectOneObject("getGroupPrePhotoById", para);
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
	public PaginationDTO<PhotoInfo> findPhotoByUserIdPage(long pageNum, long pageSize,Long userId) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		PaginationDTO<PhotoInfo> pagination = new PaginationDTO<PhotoInfo>();
		pagination.setPara(para);
		pagination.setPerPageRecordNum(pageSize);
		pagination.setCurPageNum(pageNum);
		return this.selectPage("findPhotoByUserIdPage", pagination);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取集集组默认相册
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
	public AlbumInfo getGroupDefaultAlbum(Long groupId){
		return (AlbumInfo)this.selectOneObject("getGroupDefaultAlbum", groupId);
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
	public PaginationDTO<PhotoInfo> findPhotoByGroupIdPage(long pageNum, long pageSize,Long groupId) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("groupId", groupId);
		PaginationDTO<PhotoInfo> pagination = new PaginationDTO<PhotoInfo>();
		pagination.setPara(para);
		pagination.setPerPageRecordNum(pageSize);
		pagination.setCurPageNum(pageNum);
		return this.selectPage("findPhotoByGroupIdPage", pagination);
	}
	
}
