package com.jijizu.core.check.service.impl.album;

import java.util.Map;

import com.jijizu.base.util.ObjectUtil;
import com.jijizu.core.album.dao.AlbumDAO;
import com.jijizu.core.album.dto.AlbumInfo;
import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.user.dto.UserInfo;

/**   
 *******************************************************************************
 * @project : 集集组-相册属于某个用户验证，不属于则返回错误   
 * @type : AlbumBelongToUserCheck
 * @function : 
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2013-2-10   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class AlbumBelongToUserCheck extends CheckCommonField
	implements CheckService{

	private AlbumDAO albumDAO;
	
	@Override
	public JsonResult check(Map<String, Object> para) {

		UserInfo sessionUserInfo = (UserInfo)para.get(CheckParam.SESSIONUSERINFO);
		Object albumId = para.get(CheckParam.ALBUMID);
		if(sessionUserInfo != null && !ObjectUtil.isEmptyObject(albumId)){
			AlbumInfo album = albumDAO.getAlbumById(Long.parseLong(albumId.toString()), para);
			if(sessionUserInfo.getUserId().longValue() != album.getUserId().longValue()
					&& sessionUserInfo.getUserId().longValue() != album.getCreateUserId().longValue()){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		
		return null;
	}

	public void setAlbumDAO(AlbumDAO albumDAO) {
		this.albumDAO = albumDAO;
	}
	
}
