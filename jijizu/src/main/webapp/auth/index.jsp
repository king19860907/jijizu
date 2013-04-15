<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>认证首页-集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/photo_wall.css" rel="stylesheet" type="text/css" />
<%@ include file="/common/common-js-inc.jsp" %>
<script type="text/javascript" src="${JS_PATH}/jquery-1.4.2.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/hl_all.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/at.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/common_layer.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/photo_wall.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery-1.2.6.pack.js${JS_EDITION}"></script> 
<script type="text/javascript" src="${JS_PATH}/jquery.tools.min.js${JS_EDITION}"></script> 
<script type="text/javascript" src="${JS_PATH}/jquery.rotate.js${JS_EDITION}"></script> 
<script type="text/javascript" src="${JS_PATH}/wineFriends.js${JS_EDITION}"></script>
<!--[if IE 6]>
<script type="text/javascript" src="../js/DD_belatedPNG_0.0.8a.js"></script>
<![endif]-->
</head>
<body class="sign">
<%@include file="/common/header.jsp" %>
<div class="verified_header">
	<div class="wrap2 verified_bg"><a href="/auth/authPersonal.jspa"></a></div>
</div>
<div class="W_signbg">
	<div class="wrap2">
        <div class="verified_title">集集组达人认证说明</div>
        <div class="verified_content">
        	<div class="txt">
            	<strong>什么是集集组达人？</strong>
                <span>集集组认证达人，是集集组中的活跃分子，是集集组推出的一个真实户外达人用户的舞台。成为认证达人，你可以拥有专属的身份标识，炫酷的达人勋章，还可以结识更多跟你有共同兴趣爱好的户外朋友，获得更多粉丝，拥有更多知己，更可优先享受集集组各种线上线下达人活动带来的户外生活便利和优惠。</span>
            </div>
            <div class="txt">
            	<strong>认证达人有什么特权？</strong>
                <span><b>· 专属身份标识：</b>认证达人即可点亮专属金色V字表识。它将会出现在去野吧的每个能彰显你身份的地方。<br />
<b>· 活动发布特权：</b>认证达人可在集集组活动栏任意发布高质量活动，呼朋唤友一起去参与活动！<br />
<b>· 网站推荐特权：</b>认证达人尊享被推荐至集集组网站首页的权利！让更多的人认识你，和志同道合的朋友分享你的育儿经验！</span>
            </div>
            <div class="txt">
            	<strong>如何申请认证达人？</strong>
                <div class="personal_title">个人认证</div>
                <dl>
                	<dt><i class="personal"></i></dt>
                    <dd>认证范围：支持娱乐、体育、传媒、财经、科技、文学出版、政府官员、人文艺术、游戏、军事航空、动漫、旅游、时尚等领域知名人士的认证申请。<br /><em class="red">基本条件：</em>有头像</dd>
                    <dd class="btn"><a href="/auth/authPersonal.jspa">立即申请</a>	</dd>
                </dl>
            </div>
            <div class="txt">
            	<div class="agency_title_box"><div class="agency_title">企业认证</div><span class="tips">（政府、媒体、校园、企业、网站、应用等官方帐号均可申请机构认证）</span></div>
                <dl>
                	<dt class="height480"><i class="agency"></i></dt>
                    <dd>有营业执照和公章的盈利型企业官方帐户均可申请企业认证。<br />
<em class="red">企业认证条件：</em><br />
1、头像应为企业商标/标识或品牌Logo<br />
2、昵称应为企业/品牌的全称或无歧义简称；若昵称为代理品牌，需体现代理区域<br />
3、昵称不能仅包含一个通用性描述词语，且不可使用过度修饰性词语<br />
4、企业提供完成有效年检的《企业法人营业执照》/《个体工商户营业执照》等资料<br />
<em class="red">企业认证条件：</em><br />
1、营业执照副本：加盖企业彩色公章并已通过最新年检的营业执照副本，并将此副本拍摄成清晰彩色照片的形式；<br />
2、营业执照登记的名称与加盖公章的文字应一致<br />
3、自有品牌：商标注册证、软件著作权证等<br />
4、代理品牌：代理授权书、代理授权合同等<br />
5、加盟品牌：品牌加盟证<br />
6、企业网站：网站备案信息<br />
7、企业实体店：实体店属于企业的文件证明资料，如：餐饮服务许可证等<br />
<em class="red">认证流程：</em><br />
1、申请准备：准备各项申请材料<br />
2、在线提交认证申请：填写企业信息、上传相关认证材料<br />
3、审核：等待工作人员审核（五个工作日内）<br />
4、通过审核，申请帐号可以登录申请认证的集集组账户，在消息--通知中收到认证通过的通知，同时获得认证标识<br />
5、未通过审核，申请帐号可以登录申请认证的集集组账户，在消息-通知中查看未通过审核的原因</dd>
                    <dd class="btn"><a href="/auth/authEnterprise.jspa">立即申请</a></dd>
                </dl>
            </div>
        </div>
    </div>
</div>
<%@include file="/common/footer.jsp" %>
</body>
</html>