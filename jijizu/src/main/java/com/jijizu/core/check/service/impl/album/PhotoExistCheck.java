package com.jijizu.core.check.service.impl.album;

import java.util.Map;

import com.jijizu.base.util.ObjectUtil;
import com.jijizu.core.album.dao.AlbumDAO;
import com.jijizu.core.album.dto.PhotoInfo;
import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;

/**   
 *******************************************************************************
 * @project : 集集组   
 * @type : PhotoExistCheck
 * @function : 照片存在验证-不存在则返回错误
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

public class PhotoExistCheck extends CheckCommonField
	implements CheckService{
	
	private AlbumDAO albumDAO;

	@Override
	public JsonResult check(Map<String, Object> para) {
		Object photoId = para.get(CheckParam.PHOTOID);
		if(!ObjectUtil.isEmptyObject(photoId)){
			PhotoInfo photo = albumDAO.getPhotoInfoById(Long.parseLong(photoId.toString()), para);
			if(photo == null){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		return null;
	}

	public void setAlbumDAO(AlbumDAO albumDAO) {
		this.albumDAO = albumDAO;
	}
	
}
