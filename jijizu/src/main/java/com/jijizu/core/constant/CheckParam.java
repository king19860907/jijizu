package com.jijizu.core.constant;


/**   
 *******************************************************************************
 * @project : 集集组   
 * @type : CheckParam
 * @function : 验证参数类
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2012-12-20   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   以TEMP_为开头的表示用于各个check中对象的传递
 * 			如Check1中从数据库中查出了userDTO
 * 			此时check2也需要userDTO为了不再让check2从数据库中获取
 * 		          直接从临时变量中获取该对象即可
 *******************************************************************************
 */ 

public class CheckParam {
	
	public static final String LOGNAME = "logName";
	
	public static final String NAME = "name";
	
	public static final String NICKNAME = "nickName";
	
	public static final String PASSWORD = "password";
	
	public static final String STATUSID = "statusId";
	
	public static final String CONTENT = "content";
	
	public static final String SESSIONUSERINFO = "sessionUserInfo";
	
	public static final String COMMENTID = "commentId";
	
	public static final String FOLLOWUSERID = "followUserId";
	
	public static final String USERID = "userId";
	
	public static final String TARGET = "target";
	
	public static final String MAILSHOWID = "mailShowId";
	
	public static final String MAILDETAILID = "mailDetailId";
	
	public static final String COMPANYNAME = "companyName";
	
	public static final String JOBID = "jobId";
	
	public static final String USERSCHOOLID = "userSchoolId";
	
	public static final String BIRTHDAY = "birthday";
	
	public static final String BLOOD = "blood";
	
	public static final String HOMETOWNPROVINCE = "hometownProvince";
	
	public static final String HOMETOWNCITY = "hometownCity";
	
	public static final String HOMETOWNDISTRICT = "hometownDistrict";
	
	public static final String USERDESC = "userDesc";
	
	public static final String CHILDID = "childId";
	
	public static final String CHILDNAME = "childName";
	
	public static final String DEPARTMENT = "department";
	
	public static final String SCHOOLID = "schoolId";
	
	public static final String SCHOOLNAME = "schoolName";
	
	public static final String SCHOOLTYPE = "type";
	
	public static final String LIVINGCOMMUNITY = "livingCommunity";

	public static final String HOMETOWNLIVINGCOMMUNITY = "hometownLivingCommunity";
	
	public static final String STARTDAY = "startDay";
	
	public static final String STARTHOUR = "startHour";
	
	public static final String STARTMINUTE = "startMinute";
	
	public static final String STARTDATE = "startDate";
	
	public static final String ENDDAY = "endDay";
	
	public static final String ENDHOUR = "endHour";
	
	public static final String ENDMINUTE = "endMinute";
	
	public static final String ENDDATE = "endDate";
	
	public static final String APPLYENDDAY = "applyEndDay";
	
	public static final String APPLYENDHOUR = "applyEndHour";
	
	public static final String APPLYENDMINUTE = "applyEndMinute";
	
	public static final String APPLYENDDATE = "applyEndDate";
	
	public static final String MYFILE = "myFile";
	
	public static final String COST = "cost";
	
	public static final String FEE = "fee";
	
	public static final String APPLYNUM = "applyNum";
	
	public static final String PEOPLE = "people";
	
	public static final String ONLYONE = "onlyOne";
	
	public static final String PARENTSLIMIT = "parentsLimit";
	
	public static final String GROUPID = "groupId";
	
	public static final String EMAIL = "email";
	
	public static final String MOBILE = "mobile";
	
	public static final String ALBUMID = "albumId";
	
	public static final String PHOTOID = "photoId";
	
	public static final String POSTERIMGURL = "posterImgUrl";
	
	public static final String ENTERTYPEDETAIL = "enterTypeDetail";
	
	public static final String STARTAGE = "startAge";
	
	public static final String ENDAGE = "endAge";
	
	public static final String NOTICEID = "noticeId";
	
	public static final String CHILDIDS = "childIds";
	
	public static final String ADULTNUM = "adultNum";
	
	public static final String JOINSTATUS = "joinStatus";
	
	public static final String DIARYDATE = "diaryDate";
	
	public static final String CHILDMONTH = "childMonth";
	
	public static final String MEDIATYPE = "mediaType";
	
	public static final String MEDIAURL = "mediaUrl";
	
	public static final String SOURCETYPE = "sourceType";
	
	public static final String SOURCETYPEDETAIL = "sourceTypeDetail";
	
	public static final String IP = "ip";
	
	public static final String DIARYTYPE = "diaryType";
	
	public static final String HEIGHT = "height";
	
	public static final String TITLE = "title";
	
	public static final String DIARYID = "diaryId";
	
	public static final String NOTSHOWFLAG = "notShowFlag";
	
	public static final String SYMPTOMS = "symptoms";
	
	public static final String SICKNAME = "sickName";
	
	public static final String ICODE = "iCode";
	
	public static final String RANDOM = "random";
	
	//temp类型
	public static final String TMEP_USERINFO = "userInfo";
	
	public static final String TEMP_STATUSINFO = "statusInfo";
	
	public static final String TEMP_COMMENTINFO = "commentInfo";
	
	public static final String TEMP_MAILSHOW = "mailShow";
	
	public static final String TEMP_GROUPINFO = "groupInfo";
	
	public static final String TEMP_ALBUMINFO = "albumInfo";
	
	public static final String TEMP_PHOTOINFO = "photoInfo";
	
	public static final String TEMP_DIARYINFO = "diaryInfo";
	
	//为空检查
	public static final String IS_NEED_NULL_CHECK = "_IS_NEED_NULL_CHECK";

}
