package com.jijizu.core.user.dto;

import java.io.Serializable;

import com.jijizu.base.util.JijizuUtil;
import com.jijizu.core.constant.CommonConstant;
import com.jijizu.core.constant.InitData;

public class RecommendUser implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1924532894382671367L;

	private Long userId;
	
	private String headImgUrl;
	
	private String name;
	
	private String recommendType;
	
	private Long vFlag;
	
	private Long enterpriseFlag;
	
	public RecommendUser(){}
	
	public RecommendUser(Long userId, String headImgUrl, String name,
			String recommendType) {
		super();
		this.userId = userId;
		this.headImgUrl = headImgUrl;
		this.name = name;
		this.recommendType = recommendType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getHeadImgUrl() {
		return getHeadImgUrl(CommonConstant.HEAD_IMG_SIZE_50);
	}
	
	public String getHeadImgUrl(String imgSize){
		return JijizuUtil.getHeadImgUrl(headImgUrl, imgSize);
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRecommendType() {
		return recommendType;
	}
	
	public String getRecommendTypeStr(){
		return InitData.sysDictCodeAndNameRelationShip.get(recommendType);
	}

	public void setRecommendType(String recommendType) {
		this.recommendType = recommendType;
	}
	
	public Long getEnterpriseFlag() {
		return enterpriseFlag;
	}

	public void setEnterpriseFlag(Long enterpriseFlag) {
		this.enterpriseFlag = enterpriseFlag;
	}

	public Long getVFlag() {
		return vFlag;
	}

	public void setVFlag(Long vFlag) {
		this.vFlag = vFlag;
	}
}
