package com.jijizu.core.check.service.impl.album;

import java.util.Map;

import com.jijizu.base.util.ObjectUtil;
import com.jijizu.core.album.dao.AlbumDAO;
import com.jijizu.core.album.dto.AlbumInfo;
import com.jijizu.core.album.dto.PhotoInfo;
import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.user.dto.UserInfo;

/**   
 *******************************************************************************
 * @project : 集集组   
 * @type : PhotoBelongToUserCheck
 * @function : 照片属于当前用户验证-不属于则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2013-2-12   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class PhotoBelongToUserCheck extends CheckCommonField
	implements CheckService{
	
	private AlbumDAO albumDAO;

	@Override
	public JsonResult check(Map<String, Object> para) {
		Object photoId = para.get(CheckParam.PHOTOID);
		UserInfo sessionUserInfo = (UserInfo)para.get(CheckParam.SESSIONUSERINFO);
		if(!ObjectUtil.isEmptyObject(photoId) && sessionUserInfo != null){
			PhotoInfo photo = albumDAO.getPhotoInfoById(Long.parseLong(photoId.toString()), para);
			if(photo != null){
				AlbumInfo albumInfo = albumDAO.getAlbumById(photo.getAlbumId(), para);
				if(sessionUserInfo.getUserId().longValue() != photo.getCreateUserId().longValue()
						&& albumInfo != null && albumInfo.getUserId().longValue() != sessionUserInfo.getUserId().longValue()){
					return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
				}
			}
		}
		return null;
	}

	public void setAlbumDAO(AlbumDAO albumDAO) {
		this.albumDAO = albumDAO;
	}
	
}
