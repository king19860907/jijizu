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

/**   
 *******************************************************************************
 * @project : 集集组
 * @type : AlbumExistCheck
 * @function : 相册存在检查-不存在则返回错误
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

public class AlbumExistCheck extends CheckCommonField
	implements CheckService{
	
	private AlbumDAO albumDAO;

	@Override
	public JsonResult check(Map<String, Object> para) {
		Object albumId = para.get(CheckParam.ALBUMID);
		if(!ObjectUtil.isEmptyObject(albumId)){
			AlbumInfo album = albumDAO.getAlbumById(Long.parseLong(albumId.toString()), para);
			if(album == null){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		return null;
	}

	public void setAlbumDAO(AlbumDAO albumDAO) {
		this.albumDAO = albumDAO;
	}
	
}
