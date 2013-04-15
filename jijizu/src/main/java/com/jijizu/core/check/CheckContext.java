package com.jijizu.core.check;

import java.util.List;
import java.util.Map;

import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.dto.JsonResult;

public class CheckContext {
	
	private List<CheckService> checkList;

	/**   
	 *******************************************************************************
	 * @function : 验证方法<br>
	 * 	约定：1）将需要验证的参数放入map中<br>
	 * 		  2）如果从key中取得的value为空，则默认验证通过<br>
	 * 		  3）如果需验证key中的value不能为空，则可在配置时最前面加一层ParamsNotNullCheck<br>
	 * 		  4）如果key中的value可以为空，则需要在map中增加（key+CheckParam.IS_NEED_NULL_CHECK）为建,false为值即可<br>
	 * @param para
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-28   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult check(Map<String, Object> para) {

		if (checkList != null) {
			for (CheckService service : checkList) {
				JsonResult result = service.check(para);
				if (result != null) {
					return result;
				}
			}
		}
		return null;
	}

	public void setCheckList(List<CheckService> checkList) {
		this.checkList = checkList;
	}
	
}
