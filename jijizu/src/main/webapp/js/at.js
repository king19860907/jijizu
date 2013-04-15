/**
 + ---------------------------------------- +
 + 微博发布功能 v1.0
 + Author: luzhichao
 + QQ: 190135180
 + Mail: luzhichao@shiqutech.com
 + ---------------------------------------- +
 + Date: 2012-09-06
 + ---------------------------------------- +
**/

/**
 * 静态方法集
 * @name _ATDOM
 * @ignore
*/
var _ATDOM = {
	/**
	 * 元素选择器
	 * @name _ATDOM#$lzc
	 * @param {string} sArg		#id/.clssName/tagName
	 * @param {object} context	可选，上下文
	 * @function
	 * @return element 为id时返回元素
	 * @return elements 为className|tagName时返回元素集
	 */	
	$lzc: function(sArg, context)
	{
		switch(sArg.charAt(0))
		{
			case "#":
				return document.getElementById(sArg.substring(1));
				break;
			case ".":
				var reg = new RegExp("(^|\\s)"+ sArg.substring(1) +"(\\s|$)"),
					arr = [],
					aEl = _ATDOM.$lzc("*", context),
					i;
				for(i = 0; i < aEl.length; i++) reg.test(aEl[i].className) && arr.push(aEl[i]);
				return arr;
				break;
			default:
				return (context || document).getElementsByTagName(sArg);
				break;
		}
	},
	/**
	 * 判断目标元素是否包含指定的className
	 * @name _ATDOM#hasClass
	 * @param {object} element		目标元素
	 * @param {string} className	要检测的className
	 * @function
	 * @return boolean
	 */	
	hasClass: function(element, className) {
		return new RegExp("(^|\\s)" + className + "(\\s|$)").test(element.className)
	},
	/**
	 * 给目标元素添加className
	 * @name Calendar#addClass
	 * @param {object} element		目标元素
	 * @param {string} className	要添加的className
	 * @function
	 */	
	addClass: function(element, className) {
		var arr = element.className.split(/\s+/);	
		this.hasClass(element, className) || arr.push(className);
		element.className = arr.join(" ").replace(/(^\s*)|(\s*$)/, "")
	},
	/**
	 * 删除目标元素className
	 * @name _ATDOM#removeClass
	 * @param {object} element		目标元素
	 * @param {string} className	要删除的className
	 * @function
	 */	
	removeClass: function(element, className) {
		element.className = element.className.replace(new RegExp("(^|\\s)" + className + "(\\s|$)", "g"), "").split(/\s+/).join(" ")	
	},	
	/**
	 * 给目标元素绑定事件
	 * @name _ATDOM#on
	 * @param {object} element		目标元素
	 * @param {string} type 		事件类型
	 * @param {function} handler	处理函数
	 * @function
	 */	
	on: function(element, type, handler) {
		element.addEventListener ? element.addEventListener(type, handler, false) : element.attachEvent("on" + type, handler)
	},
	/**
	 * 阻止事件冒泡和默认事件
	 * @name _ATDOM#halt
	 * @param {event} e	 
	 * @function
	 */	
	halt: function(e) {
		e = e || event;
		e.preventDefault  ? e.preventDefault()  : e.returnValue  = !1;
		e.stopPropagation ? e.stopPropagation() : e.cancelBubble = !0
	}
};

/**
 * 微博发布函数
 * @name Release
 * @param {object} property 配置参数
 */
function Release() {
	this._init.apply(this, arguments)
}

Object.extend = function(destination, source){
    for(var property in source)destination[property] = source[property];
    return destination;
};

Release.prototype = {
	_init : function (obj, id, fnEnd)
	{
		if(typeof obj == 'string')
		{
			var _this = this;
			this.iNow = 1;
			this.val  = 0;
			this.id   = id;
			this.property(fnEnd);
			this.box  = this.fnEnd.box;
			this.list = this.fnEnd.list;
			this.mousekey();
			this.mouseclick();
			//$("#text").focus();
		}
		/*jQuery("#text").bind("focus",function(){
			_this.character('text', 11111111280, 'num140');
		});*/
	},
	property : function (fnEnd)
	{
		this.fnEnd = {};
		Object.extend(this.fnEnd, fnEnd || {});
	},
	
	/**
	 * 获取元素
	 * @name _DOM
	 * @function
	 */
	_DOM : function ()
	{
		/** 获取元素ID **/
		this.List = _ATDOM.$lzc('#' + this.list);
		this.aLi  = _ATDOM.$lzc('li',this.List);
		this.Box  = _ATDOM.$lzc('#' + this.box);
		this.Id   = _ATDOM.$lzc('#' + this.id);
		
		/** 获取元素CLASS **/
		this.Btn   = _ATDOM.$lzc('.btn', document)[0];
		
		//限制文字
		this.num140 = _ATDOM.$lzc('#num140');
	},
	
	/**
	 * 控制@关联菜单位置
	 * @name listSite 
	 * @function
	 */
	listSite : function ()
	{
		this._DOM();
		
		this.replaceHTML        = this.getPosition(this.Id).replace(/\</g,'&lt;').replace(/\>/g,'&gt;').replace(/\n/g,'<br>').replace(/\s/g,'&nbsp;');
		this.replaceAt          = this.replaceHTML.match(/@[^@]+|@/g);
		this.replaceAt          = this.replaceAt ? this.replaceAt[this.replaceAt.length-1]+'$' : '';
		this.replaceAt          = new RegExp(this.replaceAt);
		
		/** 创建插入隐藏DIV获取光标位置 **/
		this.oDiv               = document.createElement('div');
		this.oDiv.id            = 'textDisplay';
		this.oDiv.innerHTML     = this.replaceHTML.replace(this.replaceAt,'') + '<cite>#</cite>';
		this.Box.appendChild(this.oDiv);
		
		/** 列表位置 **/
		this.oTextDisplay       = _ATDOM.$lzc('#textDisplay');
		this.oCite              = _ATDOM.$lzc('cite',this.oTextDisplay)[0];
		
		this.List.style.top     = this.oCite.offsetTop  + this.oTextDisplay.parentNode.offsetTop - this.Id.scrollTop + 20 + 'px';
		this.List.style.left    = this.oCite.offsetLeft + this.oTextDisplay.parentNode.offsetLeft + 2  + 'px';
		this.Box.removeChild(this.oDiv);
	},
	
	/**
	 * 判断光标位置\获取光标前文字
	 * @name getPosition 
	 * @function
	 */
	getPosition : function ()
	{
		this.text;
		this._DOM();
		
		if(typeof(this.Id.selectionStart)=="number")
		{
			this.text = this.Id.value.substring(0,this.Id.selectionStart);//获取文本框value
		}
		else
		{
			this.rng;
			this.rng  = document.selection.createRange();
			this.rng.moveStart("character",-event.srcElement.value.length);
			this.text = this.rng.text;
		}
		return this.text;
	},
	
	/**
	 * 微博名称插入位置
	 * @name insertAdd 
	 * @function
	 */
	insertAdd : function (txt,lenv)
	{
		this._DOM();
		this.txt = txt + ' ';
		/** 隐藏下拉列表 **/
		this.List.style.display = 'none';
		
		/** 浏览器判断 **/
		if(document.selection)
		{
			this.range = document.selection.createRange();
			this.range.moveStart("character",-event.srcElement.value.length);
			
			//插入文本&&判断IE鼠标点击
			lenv && lenv > 0 ? this.start = lenv : this.start = this.range.text.length;
			
			this.atReplace(this.start);
			this.setFocus();
			
		}
		else if(window.getSelection&&this.Id.selectionStart>-1)
		{
			this.start = this.Id.selectionStart;
			this.end   = this.Id.selectionEnd;
			this.atReplace(this.start);
			this.setFocus();
		}
	},
	
	/**
	 * 替换@符之后文字
	 * @name insertAdd 
	 * @function
	 */
	atReplace : function (start)
	{
		this._DOM();
		
		this.aReplace = this.Id.value.substring(0,start).match(/@[^@]+|@/g);
		this.aReplace = this.aReplace ? this.aReplace[this.aReplace.length-1]+'$' : '';
			
		this.aReplace = new RegExp(this.aReplace);
		this.aReplace = this.Id.value.substring(0,start).replace(this.aReplace,'@');
	},
	
	/**
	 * 插入文本设置光标位置
	 * @name setFocus 
	 * @function
	 */
	setFocus : function ()
	{
		/** 浏览器判断 **/
		if(document.selection)
		{
			this.Id.value = this.aReplace + this.txt + this.Id.value.substring(this.start,this.Id.value.length);
			this.ofocus = this.Id.createTextRange();   
			this.ofocus.moveStart('character',(this.aReplace.length+this.txt.length));
			this.ofocus.collapse(true);
			this.ofocus.select();
		}
		else if(window.getSelection&&this.Id.selectionStart>-1)
		{
			this.Id.value = this.aReplace + this.txt + this.Id.value.substring(this.start,this.end) + this.Id.value.slice(this.end);
			/** 设置光标位置 **/
			this.Id.setSelectionRange(this.aReplace.length + this.txt.length,this.aReplace.length + this.txt.length);
		}
		this.character('text', 11111111280, 'num140');
	},
	
	/**
	 * 显示隐藏下拉列表
	 * @name disList 
	 * @function
	 */
	disList : function ()
	{
		/** @符号个数 **/
		var len = this.getPosition(this.Id).match(/@[^@]+|@/g);
		
		//if(len)len[len.length-1] != '@' ? this.aLi[0].innerHTML = '选择昵称或敲空格完成输入' : this.aLi[0].innerHTML = '选择最近@的人或直接输入';
		//len ? (/\s/g.test(len[len.length-1]) ? this.List.style.display = 'none':this.List.style.display = 'block') : this.List.style.display = 'none';
		/*len ? (/[^a-zA-Z0-9\u4e00-\u9fa5@]/g.test(len[len.length-1]) ? this.List.style.display = 'none':this.List.style.display = 'block') : this.List.style.display = 'none';
		if(len){
			var attr = this.id;
			if(!$("#publisherTop_list").is(":hidden")){
				$.ajax({
					type:'POST',
					url:'/follow/findAtFirends.jspa',
					data:({"attr":attr
				      }),
					success:function(data){
						$("#publisherTop_list").html(data);
					}
				});
			}
		}*/
		if(len){
			var attr = this.id;
			if(!$("#publisherTop_list").is(":hidden")){
				$.ajax({
					type:'POST',
					url:'/follow/findAtFirends.jspa',
					data:({"attr":attr
				      }),
					success:function(data){
						$("#publisherTop_list").html(data);
					}
				});
			}
		}
	},
	
	/**
	 * 菜单选择
	 * @name opt
	 * @function
	 */
	opt : function ()
	{
		for(var len=this.aLi.length,i=1;i<len;i++)_ATDOM.removeClass(this.aLi[i],'hove');
		_ATDOM.addClass(this.aLi[this.iNow],'hove');
	},
	
	/**
	 * 拆分字符
	 * @name limit
	 * @function
	 */
	limit : function (id)
	{
		this.e        = _ATDOM.$lzc('#' + id).value;
        this.e_length = 0;

        if(this.e.replace(/\n*\s*/,'')=='')
		{
			this.e_length = 0
		}
		else
		{
			this.e_length = this.e.match(/[^ -~]/g) == null ? this.e.length : this.e.length + this.e.match(/[^ -~]/g).length;
        }
        return this.e_length
	},
	
	/**
	 * 字符限制
	 * @name character
	 * @function
	 */
	character : function (id, size, tit)
	{
		this.e_length = this.limit(id);
		this.font_count = Math.floor((size - this.e_length) / 2);
		if(this.font_count >= 0)
		{
			_ATDOM.$lzc('#' + tit).innerHTML = "发言请遵守社区公约，委员会名单，还可以输入<em>"+this.font_count+"</em>字";
			_ATDOM.addClass(this.Btn,'btnh');
			return true
        }else{
			_ATDOM.$lzc('#' + tit).innerHTML = "发言请遵守社区公约，委员会名单，已经超过<em style='color:red'>"+Math.abs(this.font_count)+"</em>字";
			_ATDOM.removeClass(this.Btn,'btnh');
			return false
        }
	},
	
	/**
	 * 错误闪红
	 * @name Error
	 * @function
	 */
	Error : function (id)
	{
		var _this  = this;
		this.Timer = null;
		this.i     = 0;
		
		this.Timer = setInterval(function(){
			_this.i ++;
			_this.i == 6 ? (clearInterval(_this.Timer),_this.Id.focus()) : (_this.i%2==0 ? _ATDOM.addClass(_ATDOM.$lzc('#' + id),'back') : _ATDOM.removeClass(_ATDOM.$lzc('#' + id),'back'));
		},120);
	},
	
	/**
	 * 鼠标键盘事件监听
	 * @name mousekey 
	 * @function
	 */
	mousekey : function ()
	{
		var _this = this;
		this._DOM();
		
		/** 鼠标抬起 **/
		_ATDOM.on(this.Id, 'mouseup', function()
		{
			_this.listSite();
			if($("#publisherTop_list").is(":hidden")){
				_this.disList();
			}
		});
		
		/** 键盘抬起 **/
		_ATDOM.on(this.Id, 'keyup', function()
		{
			_this.listSite();
			if($("#publisherTop_list").is(":hidden")){
				_this.disList();
			}
			_this.character('text', 11111111280, 'num140');
			if(this.value == '')_ATDOM.removeClass(_this.Btn,'btnh');
			
			/** IE鼠标文字存储 **/
			if(document.selection)
			{
				var range = document.selection.createRange();
				range.moveStart("character",-event.srcElement.value.length);
				_this.val = range.text.length;
			}
		});
		
		/** 键盘按下 **/
		this.Id.onkeydown = function (e)
		{
			var oEvent = e || event;
			
			if(_this.List.style.display == 'block' && oEvent.keyCode == 38)
			{
				_this.iNow == 1 ? _this.iNow = _this.aLi.length - 1 : _this.iNow --;
				_this.opt();
				return false;
			}
			else if(_this.List.style.display == 'block' && oEvent.keyCode == 40)
			{
				_this.iNow == _this.aLi.length - 1 ? _this.iNow = 1 : _this.iNow ++;
				_this.opt();
				return false;
			}			
			
			if(_this.List.style.display == 'block' && oEvent.keyCode == 13)
			{
				/** 插入列表内容 **/
				_this.insertAdd(_this.aLi[_this.iNow].innerHTML);
				return false;
			}
		};
		
		/** 光标选中 **/
		$("#text").bind("focus",function(){
			_ATDOM.addClass(this,'hove');
			
			/** 光标选中改变输入文字 **/
			_ATDOM.addClass(_this.num140,'color');
			_this.character('text', 11111111280, 'num140');
			
			/** 控制发布按钮变色 **/
			//this.value != '' && _this.e_length <= 11111111280 ? _ATDOM.addClass(_this.Btn,'btnh') : _ATDOM.removeClass(_this.Btn,'btnh');
			this.value != '' ? _ATDOM.addClass(_this.Btn,'btnh') : _ATDOM.removeClass(_this.Btn,'btnh');
		});
		/*_ATDOM.on(this.Id, 'focus', function()
		{
			
		});*/
		
		/** 光标离开 **/
		$("#text").bind("blur",function(){
			_ATDOM.removeClass(this,'hove');
			
			/** 光标离开改变输入文字 **/
			if(this.value == '')
			{
				_ATDOM.removeClass(_this.num140,'color');
				_this.num140.innerHTML = '前任现任人手一个，李晨泡妞神器石头心走红&nbsp;&nbsp;24小时热博';
				_ATDOM.removeClass(_this.Btn,'btnh');
			}
		});
		/*_ATDOM.on(this.Id, 'blur', function()
		{
			_ATDOM.removeClass(this,'hove');
			
			*//** 光标离开改变输入文字 **//*
			if(this.value == '')
			{
				_ATDOM.removeClass(_this.num140,'color');
				_this.num140.innerHTML = '前任现任人手一个，李晨泡妞神器石头心走红&nbsp;&nbsp;24小时热博';
				_ATDOM.removeClass(_this.Btn,'btnh');
			}
		});*/
	},
	
	/**
	 * 鼠标点击列表
	 * @name mouseclick 
	 * @function
	 */
	mouseclick : function ()
	{
		var _this = this;
		
		for(var len=this.aLi.length,i=1;i<len;i++)
		{
			this.aLi[i].index = i;
			
			/** 鼠标经过微博名称 **/
			_ATDOM.on(this.aLi[i], 'mouseover', function()
			{
				for(var len=_this.aLi.length,i=1;i<len;i++)_ATDOM.removeClass(_this.aLi[i],'hove');
				_ATDOM.addClass(this,'hove');
				_this.iNow = this.index;
			});
			
			/** 鼠标点击微博名称 **/
			_ATDOM.on(this.aLi[i], 'click', function()
			{
				/** 插入列表内容 **/
				_this.insertAdd(this.children[1].innerHTML,_this.val);
				
			});
		}
		
		/** 点击发布按钮 **/
		_ATDOM.on(this.Btn, 'click', function ()
		{
		
			if($("#post_status_btn").attr("class") == 'btn btnh')
			{
				var options = { 
						cache : false,
						dataType:"json",	
						type :"post",
						async:false,
						success: function(data) { 
						  if(data.flag == 0){
							  closeImgUploadLayer();
							  _this.Id.value = '';
							  _this.character('text', 11111111280, 'num140');
						      _ATDOM.removeClass(_this.Btn,'btnh');
						      _ATDOM.addClass(_ATDOM.$lzc('.fbcg',document)[0],'dis');
						  	/** 判断发布成功 **/
								setTimeout(function(){
									_ATDOM.removeClass(_ATDOM.$lzc('.fbcg',document)[0],'dis');
									_this.Id.focus();
								},500);
								
								$("#post_status_media_type").val(null);
								$("#post_status_media_url").val(null);
								
							/** 加载最新的一条微薄 **/
								getNewestStatus(data.data);
						  }else{
						  	alert(data.msg);
						  }
						}
					};
					var $form = $("#post_status_form");
					$form.attr('action','/status/postStatus.jspa');
			        $form.ajaxSubmit(options);
			}
			else
			{
				_this.Error('text');
			}
		});
	}
}

/**
 * new发布函数
 * @name atRelease
 * @obj, id, fnEnd 参数
 */
function atRelease(obj, id, fnEnd){
	return new Release(obj, id, fnEnd);
}
