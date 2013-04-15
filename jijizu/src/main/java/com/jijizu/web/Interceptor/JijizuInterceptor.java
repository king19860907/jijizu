package com.jijizu.web.Interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class JijizuInterceptor implements Interceptor {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1231837517470167768L;

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
	/*	try{
			MicroBlogActionBase action = (MicroBlogActionBase) invocation.getProxy().getAction();
			String actionName = invocation.getProxy().getActionName();
			Map<String,Object> ajaxMap = new HashMap<String,Object>();
			ajaxMap.put("post-status", "post-status");
			ajaxMap.put("post-comment", "post-comment");
			ajaxMap.put("forward-status", "forward-status");
			ajaxMap.put("savePromotion", "savePromotion");
			ajaxMap.put("submit_senior_pingjian", "submit_senior_pingjian");
			ajaxMap.put("saveFavoriteGoods", "saveFavoriteGoods");
			ajaxMap.put("submit_pingjian", "submit_pingjian");
			ajaxMap.put("sendPrivateMessage", "sendPrivateMessage");
			
			Map<String,Object> groupMap = new HashMap<String,Object>();
			groupMap.put("saveComment", "saveComment");
			groupMap.put("saveTopic", "saveTopic");
			
			SnsUserInfo userInfo = action.getUserInfo();
			
			if(userInfo != null && ajaxMap.get(actionName) != null){
				ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(ServletActionContext.getServletContext());
				MicroBlogService microBlogService = (MicroBlogService) ac.getBean("microBlogService", MicroBlogService.class);
				
				if(microBlogService.isInBlackHouse(userInfo.getUserId())){
					
					action.outObjectToJson(new JsonResult("-1", "对不起，您已经被禁言！", null));
					
					return "none";
				}
				
			}else if(userInfo != null && groupMap.get(actionName) != null){
				ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(ServletActionContext.getServletContext());
				MicroBlogService microBlogService = (MicroBlogService) ac.getBean("microBlogService", MicroBlogService.class);
				
				if(microBlogService.isInBlackHouse(userInfo.getUserId())){
								
					action.setExceptionMsg("对不起，您已经被禁言！");
					
					return "exception";
				}
				
			}
		}catch(Exception e){
			
		}*/
		
		
		return invocation.invoke();
	}
}
