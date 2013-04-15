<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link type="image/x-icon" href="/favicon.ico" rel="shortcut icon"/>
<%@ include file="/common/common-js-inc.jsp" %>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.Jcrop.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.form.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/user.js${JS_EDITION}"></script>
<!--[if IE 6]>
<script type="text/javascript" src="../js/DD_belatedPNG_0.0.8a.js"></script>
<![endif]-->
</head>

<body>
<!--头部文件-->
<%@include file="/common/header.jsp" %>
<!--主体部分-->
<div class="W_main bg3">
  <div class="W_main_bg clearfix"> 
    <!--菜单部分-->
    <%@include file="/user/set/inc/left-inc.jsp" %>
    <!--内容部分-->
    <div class="W_main_r3">
      <div class="MIB_setup">
        <div class="setup_main">
          <div class="index_title">
            <h3>修改头像</h3>
            <h4>上传真人头像，有助于你的好友找到你哟。</h4>
          </div>
          <%@include file="/user/set/inc/data-integrity-inc.jsp" %>
          <div class="setting_box">
       	  <div class="setting_btn">
            	选择图片: <span>仅支持JPG、GIF、PNG，且文件小于2M</span>
            	<form id="FileUploadAction" method="post" action="/status/fileUploadStatusAjax.jspa" enctype="multipart/form-data">
     				<input type="file" name="myFile" value="" id="FileUploadAction_myFile" onchange="updateImage();" />
            		<input type="hidden" name="rate" value="1" />
            	</form>
                
          </div>
          <div class="setting_layer">
                <div class="box-163css">
                	<form id="head-img-form" action="/user/updateHeadImage.jspa" method="post">
                    <div class="part01-jscrop">
                    	
                        <img id="bPic" src="<s:property value="originalImgUrl"/>" class="setting_img" />
                        
                        <span id="preview_box" class="crop_preview"><img id="crop_preview" src="<s:property value="img"/>" /></span>	
                        <span id="preview_box2" class="crop_preview2"><img id="crop_preview2" src="<s:property value="img"/>" /></span>
                        <span id="preview_box3" class="crop_preview3"><img id="crop_preview3" src="<s:property value="img"/>" /></span>
                    </div>
                     <div style="display:none">
						 距离顶部： <input name="y" type="text" value="" id="txt_top" /><br />
						 距离左边： <input name="x" type="text" value="" id="txt_left" /><br />
						 截取框的宽： <input name="w" type="text" value="" id="txt_DropWidth" /><br />
						 截取框的高： <input name="h" type="text" value="" id="txt_DropHeight" /><br />
						 <input name="img" type="text" value="<s:property value="originalImgUrl"/>"/>
						 <input id="t" name="t" type="text" value="1"/>
		         	  </div> 
		         	  <input type="hidden" name="target" id="targetHidden" value="" />
                    </form>
          		</div>
                <div class="setting_txt">您上传的头像会自动生成三种尺寸， <br />请注意中小尺寸的头像是否清晰</div>
                <div class="setting_txt_a">大尺寸头像</div>
                <div class="setting_txt_b">中尺寸头像</div>
                <div class="setting_txt_c">小尺寸头像</div>
            </div>
          </div>
           <div class="save_btn"><a href="javascript:void(0);" id="head_img_btn" class="W_btn_d">保存</a><a href="javascript:void(0);" class="W_btn_d">取消</a></div>
        </div>
      </div>
    </div>
  </div>
</div>
<!--尾部文件-->
<%@include file="/common/footer.jsp" %>
<script type="text/javascript"><!--

$(document).ready(function(){
	var $pic = $("#bPic");
	var height = $pic.height();
	setTimeout(processPic,100);
	$("#head_img_btn").click(function(){
		$("#head-img-form").submit();
	});
});

function processPic(){
	$("#targetHidden").val(getQueryString('target'));
	var $pic = $("#bPic");
	var height = $pic.height();
	if(height <= 30){
		setTimeout(processPic,500);
		return;
	}
	var width = $pic.width();
	if((height>=width)&&(height>300)){
		$pic.height(300);
		$("#t").val(300/height);
	}else if((height<width)&&(width>300)){
		$pic.width(300);
		$("#t").val(300/width);
	}

	jQuery("#bPic").Jcrop({
		onChange:showPreview,
		onSelect:showPreview,
		aspectRatio:1,
		setSelect:[0,0,100,100]
	});	

	//简单的事件处理程序，响应自onChange,onSelect事件，按照上面的Jcrop调用
	function showPreview(coords){
		if(parseInt(coords.w) > 0){
			//计算预览区域图片缩放的比例，通过计算显示区域的宽度(与高度)与剪裁的宽度(与高度)之比得到
			var rx = jQuery("#preview_box").width() / coords.w; 
			var ry = jQuery("#preview_box").height() / coords.h;
			//通过比例值控制图片的样式与显示
			jQuery("#crop_preview").css({
				width:Math.round(rx * jQuery("#bPic").width()) + "px",	//预览图片宽度为计算比例值与原图片宽度的乘积
				height:Math.round(rx * jQuery("#bPic").height()) + "px",	//预览图片高度为计算比例值与原图片高度的乘积
				marginLeft:"-" + Math.round(rx * coords.x) + "px",
				marginTop:"-" + Math.round(ry * coords.y) + "px"
			});
			jQuery("#crop_preview2").css({
				width:Math.round(rx * jQuery("#bPic").width()) * 0.52 + "px",	//预览图片宽度为计算比例值与原图片宽度的乘积
				height:Math.round(rx * jQuery("#bPic").height()) * 0.52 + "px",	//预览图片高度为计算比例值与原图片高度的乘积
				marginLeft:"-" + Math.round(rx * coords.x) * 0.52 + "px",
				marginTop:"-" + Math.round(ry * coords.y) * 0.52 + "px"
			});
			jQuery("#crop_preview3").css({
				width:Math.round(rx * jQuery("#bPic").width()) * 0.35 + "px",	//预览图片宽度为计算比例值与原图片宽度的乘积
				height:Math.round(rx * jQuery("#bPic").height()) * 0.35 + "px",	//预览图片高度为计算比例值与原图片高度的乘积
				marginLeft:"-" + Math.round(rx * coords.x) * 0.35 + "px",
				marginTop:"-" + Math.round(ry * coords.y) * 0.35 + "px"
			});
		}
		$("#txt_left").val(coords.x); //得到选中区域左上角横坐标 
		$("#txt_top").val(coords.y); //得到选中区域左上角纵坐标 
		$("#txt_DropWidth").val(coords.w); //得到选中区域的宽度 
		$("#txt_DropHeight").val(coords.h); //得到选中区域的高度 
	}
}

</script>

</body>
</html>
