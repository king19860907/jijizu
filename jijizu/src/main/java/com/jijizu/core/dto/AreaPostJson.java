package com.jijizu.core.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.jijizu.core.constant.InitData;
import com.jijizu.core.user.dto.PostArea;

/**
 ******************************************************************************* 
 * @project : 也买网
 * @type : AreaPostJson
 * @function : 用户构成地址初始化json字符串
 ******************************************************************************* 
 * @version ：1.1.0
 * @creator ：majun
 * @date ：2012-12-19
 ******************************************************************************* 
 * @revision ：
 * @revisor ：
 * @date ：
 * @memo ：
 ******************************************************************************* 
 */

public class AreaPostJson implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1217341858024794363L;

	public AreaPostJson() {
		for (Object obj : InitData.areaProvince.keySet()) {
			this.PROVINCE.add((PostArea) InitData.areaProvince.get(obj));
		}
		for (Object obj : InitData.areaCity.keySet()) {
			this.CITY.add((PostArea) InitData.areaCity.get(obj));
		}
		for (Object obj : InitData.areaDistrict.keySet()) {
			this.DISTRICT.add((PostArea) InitData.areaDistrict.get(obj));
		}
	}

	private List<PostArea> PROVINCE = new ArrayList<PostArea>();
	private List<PostArea> CITY = new ArrayList<PostArea>();
	private List<PostArea> DISTRICT = new ArrayList<PostArea>();

	public List<PostArea> getPROVINCE() {
		return PROVINCE;
	}

	public void setPROVINCE(List<PostArea> pROVINCE) {
		PROVINCE = pROVINCE;
	}

	public List<PostArea> getCITY() {
		return CITY;
	}

	public void setCITY(List<PostArea> cITY) {
		CITY = cITY;
	}

	public List<PostArea> getDISTRICT() {
		return DISTRICT;
	}

	public void setDISTRICT(List<PostArea> dISTRICT) {
		DISTRICT = dISTRICT;
	}

}
