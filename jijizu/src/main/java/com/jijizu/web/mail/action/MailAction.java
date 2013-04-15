package com.jijizu.web.mail.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jijizu.core.constant.CacheConstant;
import com.jijizu.core.constant.SysDataDicConstant;
import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.mail.dto.MailDetail;
import com.jijizu.core.mail.dto.MailShow;
import com.jijizu.core.mail.service.MailService;
import com.jijizu.core.service.MessageService;
import com.jijizu.core.status.dto.StatusFace;
import com.jijizu.core.status.service.StatusService;
import com.jijizu.core.user.dto.UserInfo;
import com.jijizu.core.user.service.UserInfoService;
import com.jijizu.web.action.BaseAction;

@Controller
@Scope("prototype")
public class MailAction extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8681368903353911656L;
	
	private MailService mailService;
	
	private PaginationDTO<MailShow> mailPage;
	
	private PaginationDTO<MailDetail> mailDetailPage;
	
	private Long mailShowId;
	
	private int page;
	
	private MailShow mailShow;
	
	private List<StatusFace> faces;
	
	private StatusService statusService;
	
	private UserInfo userInfo;
	
	private UserInfoService userInfoService;
	
	private MessageService messageService;
	
	public String mail(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		if(sessionUserInfo == null){
			return NOT_LOGIN;
		}
		
		mailPage = mailService.findMailShowPage(page, 10, sessionUserInfo.getUserId());
		userInfo = userInfoService.getUserInfoById(sessionUserInfo.getUserId());
		messageService.clearNewMessage(CacheConstant.MESSAGE_MAIL_NEW_COUNT, userInfo.getUserId());
		
		return SUCCESS;
	}
	
	public String mailDetail(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		if(sessionUserInfo == null){
			return NOT_LOGIN;
		}
		if(mailShowId == null){
			return INDEX;
		}
		mailShow = mailService.getMailShowById(mailShowId);
		if(mailShow==null){
			return INDEX;
		}
		
		mailDetailPage = mailService.findMailDetailPage(mailShowId, page, 10l);
		faces = statusService.findStatusFace(SysDataDicConstant.FACE_TYPE_STATUS); 
		userInfo = userInfoService.getUserInfoById(sessionUserInfo.getUserId());
		
		return SUCCESS;
	}

	public PaginationDTO<MailShow> getMailPage() {
		return mailPage;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setMailShowId(Long mailShowId) {
		this.mailShowId = mailShowId;
	}

	public PaginationDTO<MailDetail> getMailDetailPage() {
		return mailDetailPage;
	}

	public MailShow getMailShow() {
		return mailShow;
	}

	public List<StatusFace> getFaces() {
		return faces;
	}

	public void setStatusService(StatusService statusService) {
		this.statusService = statusService;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
}
