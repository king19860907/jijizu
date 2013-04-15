/**
* DD_belatedPNG: Adds IE6 support: PNG images for CSS background-image and HTML <IMG/>.
* Author: Drew Diller
* Email: drew.diller@gmail.com
* URL: http://www.dillerdesign.com/experiment/DD_belatedPNG/
* Version: 0.0.8a
* Licensed under the MIT License: http://dillerdesign.com/experiment/DD_belatedPNG/#license
*
* Example usage:
* DD_belatedPNG.fix('.png_bg'); // argument is a CSS selector
* DD_belatedPNG.fixPng( someNode ); // argument is an HTMLDomElement
**/

/*
PLEASE READ:
Absolutely everything in this script is SILLY.  I know this.  IE's rendering of certain pixels doesn't make sense, so neither does this code!
*/

var DD_belatedPNG = {
	ns: 'DD_belatedPNG',
	imgSize: {},
	delay: 10,
	nodesFixed: 0,
	createVmlNameSpace: function () { /* enable VML */
		if (document.namespaces && !document.namespaces[this.ns]) {
			document.namespaces.add(this.ns, 'urn:schemas-microsoft-com:vml');
		}
	},
	createVmlStyleSheet: function () { /* style VML, enable behaviors */
		/*
			Just in case lots of other developers have added
			lots of other stylesheets using document.createStyleSheet
			and hit the 31-limit mark, let's not use that method!
			further reading: http://msdn.microsoft.com/en-us/library/ms531194(VS.85).aspx
		*/
		var screenStyleSheet, printStyleSheet;
		screenStyleSheet = document.createElement('style');
		screenStyleSheet.setAttribute('media', 'screen');
		document.documentElement.firstChild.insertBefore(screenStyleSheet, document.documentElement.firstChild.firstChild);
		if (screenStyleSheet.styleSheet) {
			screenStyleSheet = screenStyleSheet.styleSheet;
			screenStyleSheet.addRule(this.ns + '\\:*', '{behavior:url(#default#VML)}');
			screenStyleSheet.addRule(this.ns + '\\:shape', 'position:absolute;');
			screenStyleSheet.addRule('img.' + this.ns + '_sizeFinder', 'behavior:none; border:none; position:absolute; z-index:-1; top:-10000px; visibility:hidden;'); /* large negative top value for avoiding vertical scrollbars for large images, suggested by James O'Brien, http://www.thanatopsic.org/hendrik/ */
			this.screenStyleSheet = screenStyleSheet;
			
			/* Add a print-media stylesheet, for preventing VML artifacts from showing up in print (including preview). */
			/* Thanks to Rémi Prévost for automating this! */
			printStyleSheet = document.createElement('style');
			printStyleSheet.setAttribute('media', 'print');
			document.documentElement.firstChild.insertBefore(printStyleSheet, document.documentElement.firstChild.firstChild);
			printStyleSheet = printStyleSheet.styleSheet;
			printStyleSheet.addRule(this.ns + '\\:*', '{display: none !important;}');
			printStyleSheet.addRule('img.' + this.ns + '_sizeFinder', '{display: none !important;}');
		}
	},
	readPropertyChange: function () {
		var el, display, v;
		el = event.srcElement;
		if (!el.vmlInitiated) {
			return;
		}
		if (event.propertyName.search('background') != -1 || event.propertyName.search('border') != -1) {
			DD_belatedPNG.applyVML(el);
		}
		if (event.propertyName == 'style.display') {
			display = (el.currentStyle.display == 'none') ? 'none' : 'block';
			for (v in el.vml) {
				if (el.vml.hasOwnProperty(v)) {
					el.vml[v].shape.style.display = display;
				}
			}
		}
		if (event.propertyName.search('filter') != -1) {
			DD_belatedPNG.vmlOpacity(el);
		}
	},
	vmlOpacity: function (el) {
		if (el.currentStyle.filter.search('lpha') != -1) {
			var trans = el.currentStyle.filter;
			trans = parseInt(trans.substring(trans.lastIndexOf('=')+1, trans.lastIndexOf(')')), 10)/100;
			el.vml.color.shape.style.filter = el.currentStyle.filter; /* complete guesswork */
			el.vml.image.fill.opacity = trans; /* complete guesswork */
		}
	},
	handlePseudoHover: function (el) {
		setTimeout(function () { /* wouldn't work as intended without setTimeout */
			DD_belatedPNG.applyVML(el);
		}, 1);
	},
	/**
	* This is the method to use in a document.
	* @param {String} selector - REQUIRED - a CSS selector, such as '#doc .container'
	**/
	fix: function (selector) {
		if (this.screenStyleSheet) {
			var selectors, i;
			selectors = selector.split(','); /* multiple selectors supported, no need for multiple calls to this anymore */
			for (i=0; i<selectors.length; i++) {
				this.screenStyleSheet.addRule(selectors[i], 'behavior:expression(DD_belatedPNG.fixPng(this))'); /* seems to execute the function without adding it to the stylesheet - interesting... */
			}
		}
	},
	applyVML: function (el) {
		el.runtimeStyle.cssText = '';
		this.vmlFill(el);
		this.vmlOffsets(el);
		this.vmlOpacity(el);
		if (el.isImg) {
			this.copyImageBorders(el);
		}
	},
	attachHandlers: function (el) {
		var self, handlers, handler, moreForAs, a, h;
		self = this;
		handlers = {resize: 'vmlOffsets', move: 'vmlOffsets'};
		if (el.nodeName == 'A') {
			moreForAs = {mouseleave: 'handlePseudoHover', mouseenter: 'handlePseudoHover', focus: 'handlePseudoHover', blur: 'handlePseudoHover'};
			for (a in moreForAs) {			
				if (moreForAs.hasOwnProperty(a)) {
					handlers[a] = moreForAs[a];
				}
			}
		}
		for (h in handlers) {
			if (handlers.hasOwnProperty(h)) {
				handler = function () {
					self[handlers[h]](el);
				};
				el.attachEvent('on' + h, handler);
			}
		}
		el.attachEvent('onpropertychange', this.readPropertyChange);
	},
	giveLayout: function (el) {
		el.style.zoom = 1;
		if (el.currentStyle.position == 'static') {
			el.style.position = 'relative';
		}
	},
	copyImageBorders: function (el) {
		var styles, s;
		styles = {'borderStyle':true, 'borderWidth':true, 'borderColor':true};
		for (s in styles) {
			if (styles.hasOwnProperty(s)) {
				el.vml.color.shape.style[s] = el.currentStyle[s];
			}
		}
	},
	vmlFill: function (el) {
		if (!el.currentStyle) {
			return;
		} else {
			var elStyle, noImg, lib, v, img, imgLoaded;
			elStyle = el.currentStyle;
		}
		for (v in el.vml) {
			if (el.vml.hasOwnProperty(v)) {
				el.vml[v].shape.style.zIndex = elStyle.zIndex;
			}
		}
		el.runtimeStyle.backgroundColor = '';
		el.runtimeStyle.backgroundImage = '';
		noImg = true;
		if (elStyle.backgroundImage != 'none' || el.isImg) {
			if (!el.isImg) {
				el.vmlBg = elStyle.backgroundImage;
				el.vmlBg = el.vmlBg.substr(5, el.vmlBg.lastIndexOf('")')-5);
			}
			else {
				el.vmlBg = el.src;
			}
			lib = this;
			if (!lib.imgSize[el.vmlBg]) { /* determine size of loaded image */
				img = document.createElement('img');
				lib.imgSize[el.vmlBg] = img;
				img.className = lib.ns + '_sizeFinder';
				img.runtimeStyle.cssText = 'behavior:none; position:absolute; left:-10000px; top:-10000px; border:none; margin:0; padding:0;'; /* make sure to set behavior to none to prevent accidental matching of the helper elements! */
				imgLoaded = function () {
					this.width = this.offsetWidth; /* weird cache-busting requirement! */
					this.height = this.offsetHeight;
					lib.vmlOffsets(el);
				};
				img.attachEvent('onload', imgLoaded);
				img.src = el.vmlBg;
				img.removeAttribute('width');
				img.removeAttribute('height');
				document.body.insertBefore(img, document.body.firstChild);
			}
			el.vml.image.fill.src = el.vmlBg;
			noImg = false;
		}
		el.vml.image.fill.on = !noImg;
		el.vml.image.fill.color = 'none';
		el.vml.color.shape.style.backgroundColor = elStyle.backgroundColor;
		el.runtimeStyle.backgroundImage = 'none';
		el.runtimeStyle.backgroundColor = 'transparent';
	},
	/* IE can't figure out what do when the offsetLeft and the clientLeft add up to 1, and the VML ends up getting fuzzy... so we have to push/enlarge things by 1 pixel and then clip off the excess */
	vmlOffsets: function (el) {
		var thisStyle, size, fudge, makeVisible, bg, bgR, dC, altC, b, c, v;
		thisStyle = el.currentStyle;
		size = {'W':el.clientWidth+1, 'H':el.clientHeight+1, 'w':this.imgSize[el.vmlBg].width, 'h':this.imgSize[el.vmlBg].height, 'L':el.offsetLeft, 'T':el.offsetTop, 'bLW':el.clientLeft, 'bTW':el.clientTop};
		fudge = (size.L + size.bLW == 1) ? 1 : 0;
		/* vml shape, left, top, width, height, origin */
		makeVisible = function (vml, l, t, w, h, o) {
			vml.coordsize = w+','+h;
			vml.coordorigin = o+','+o;
			vml.path = 'm0,0l'+w+',0l'+w+','+h+'l0,'+h+' xe';
			vml.style.width = w + 'px';
			vml.style.height = h + 'px';
			vml.style.left = l + 'px';
			vml.style.top = t + 'px';
		};
		makeVisible(el.vml.color.shape, (size.L + (el.isImg ? 0 : size.bLW)), (size.T + (el.isImg ? 0 : size.bTW)), (size.W-1), (size.H-1), 0);
		makeVisible(el.vml.image.shape, (size.L + size.bLW), (size.T + size.bTW), (size.W), (size.H), 1 );
		bg = {'X':0, 'Y':0};
		if (el.isImg) {
			bg.X = parseInt(thisStyle.paddingLeft, 10) + 1;
			bg.Y = parseInt(thisStyle.paddingTop, 10) + 1;
		}
		else {
			for (b in bg) {
				if (bg.hasOwnProperty(b)) {
					this.figurePercentage(bg, size, b, thisStyle['backgroundPosition'+b]);
				}
			}
		}
		el.vml.image.fill.position = (bg.X/size.W) + ',' + (bg.Y/size.H);
		bgR = thisStyle.backgroundRepeat;
		dC = {'T':1, 'R':size.W+fudge, 'B':size.H, 'L':1+fudge}; /* these are defaults for repeat of any kind */
		altC = { 'X': {'b1': 'L', 'b2': 'R', 'd': 'W'}, 'Y': {'b1': 'T', 'b2': 'B', 'd': 'H'} };
		if (bgR != 'repeat' || el.isImg) {
			c = {'T':(bg.Y), 'R':(bg.X+size.w), 'B':(bg.Y+size.h), 'L':(bg.X)}; /* these are defaults for no-repeat - clips down to the image location */
			if (bgR.search('repeat-') != -1) { /* now let's revert to dC for repeat-x or repeat-y */
				v = bgR.split('repeat-')[1].toUpperCase();
				c[altC[v].b1] = 1;
				c[altC[v].b2] = size[altC[v].d];
			}
			if (c.B > size.H) {
				c.B = size.H;
			}
			el.vml.image.shape.style.clip = 'rect('+c.T+'px '+(c.R+fudge)+'px '+c.B+'px '+(c.L+fudge)+'px)';
		}
		else {
			el.vml.image.shape.style.clip = 'rect('+dC.T+'px '+dC.R+'px '+dC.B+'px '+dC.L+'px)';
		}
	},
	figurePercentage: function (bg, size, axis, position) {
		var horizontal, fraction;
		fraction = true;
		horizontal = (axis == 'X');
		switch(position) {
			case 'left':
			case 'top':
				bg[axis] = 0;
				break;
			case 'center':
				bg[axis] = 0.5;
				break;
			case 'right':
			case 'bottom':
				bg[axis] = 1;
				break;
			default:
				if (position.search('%') != -1) {
					bg[axis] = parseInt(position, 10) / 100;
				}
				else {
					fraction = false;
				}
		}
		bg[axis] = Math.ceil(  fraction ? ( (size[horizontal?'W': 'H'] * bg[axis]) - (size[horizontal?'w': 'h'] * bg[axis]) ) : parseInt(position, 10)  );
		if (bg[axis] % 2 === 0) {
			bg[axis]++;
		}
		return bg[axis];
	},
	fixPng: function (el) {
		el.style.behavior = 'none';
		var lib, els, nodeStr, v, e;
		if (el.nodeName == 'BODY' || el.nodeName == 'TD' || el.nodeName == 'TR') { /* elements not supported yet */
			return;
		}
		el.isImg = false;
		if (el.nodeName == 'IMG') {
			if(el.src.toLowerCase().search(/\.png$/) != -1) {
				el.isImg = true;
				el.style.visibility = 'hidden';
			}
			else {
				return;
			}
		}
		else if (el.currentStyle.backgroundImage.toLowerCase().search('.png') == -1) {
			return;
		}
		lib = DD_belatedPNG;
		el.vml = {color: {}, image: {}};
		els = {shape: {}, fill: {}};
		for (v in el.vml) {
			if (el.vml.hasOwnProperty(v)) {
				for (e in els) {
					if (els.hasOwnProperty(e)) {
						nodeStr = lib.ns + ':' + e;
						el.vml[v][e] = document.createElement(nodeStr);
					}
				}
				el.vml[v].shape.stroked = false;
				el.vml[v].shape.appendChild(el.vml[v].fill);
				el.parentNode.insertBefore(el.vml[v].shape, el);
			}
		}
		el.vml.image.shape.fillcolor = 'none'; /* Don't show blank white shapeangle when waiting for image to load. */
		el.vml.image.fill.type = 'tile'; /* Makes image show up. */
		el.vml.color.fill.on = false; /* Actually going to apply vml element's style.backgroundColor, so hide the whiteness. */
		lib.attachHandlers(el);
		lib.giveLayout(el);
		lib.giveLayout(el.offsetParent);
		el.vmlInitiated = true;
		lib.applyVML(el); /* Render! */
	}
};
try {
	document.execCommand("BackgroundImageCache", false, true); /* TredoSoft Multiple IE doesn't like this, so try{} it */
} catch(r) {}
DD_belatedPNG.createVmlNameSpace();
DD_belatedPNG.createVmlStyleSheet();


DD_belatedPNG.fix('.gn_logo, .gn_nav .home i, .gn_nav .jjz i, .gn_nav .friend i, .gn_nav .badge i, .gn_search_btn, .gn_person .gn_setting .gn_mymail i, .gn_person .gn_setting .gn_myset i, .W_main_l .W_left_nav .level_1_Box .lev a i, .pl_publisherTop .boxf .arrow, .btnf .icon_detail a, .pl_publisherTop .hover span.arrow, .pl_publisherTop .box .publishTitle, .pl_publisherTop .btn, .W_border_content .btn, .pl_publisherTop .btnh, .W_border_content .btnh, .fbcg, .pl_advertisement .close, .pl_group .title a, .pl_group_main, .WB_detail .WB_friend dl dd.btn a.addFriend, .WB_detail .WB_arrow, .WB_media_expand .action .ico_faces, .WB_media_expand p.btn a, .comment_lists dl.comment_list dd .repeat .WB_arrow, .rot_box p a, .thumbnail , .rotImg,.canvas, .W_person_info .nameBox a i, .W_person_info .nameBox .icon_bed i, .W_person_info .nameBox .icon_bed .tips, .W_person_info .nameBox .icon_bed .tips .txt del a, .site_badge, .WB_right_module fieldset .btns a.W_btn_round, .WB_right_module fieldset .btns a.W_btn_round_ico, .WB_right_module .person_list dd.btn a.addFriend, .old_friend_btn, .WB_right_module .recommend dl dd.btn a, .WB_right_module .activity_list dl dd.btn a, .WB_right_module ul.friend_list li a.add_group_btn, .backToTop, .W_layer, .tips_01 .pic, .tips_01 .pic a, .tips_02 .pic, .tips_02 .pic a, .tips_03 .pic, .tips_03 .pic a, .tips_04 .pic, .tips_04 .pic a, .W_layer_people, .name_card .people dd p a.addFriend, .name_card .people dd span.cancelFriend, .name_card .people dd p i, .arrow, .webox, .webox #inside h1 a, .layer_point .point dt span.icon_warnM, .layer_point2 .point dt span.icon_warnOK, .layer_point3 .point dt span.icon_warnM, .layer_point .btn a, .layer_point3 .btn a, .letter_btn a, .W_private_letter dl dd .M_notice_del span.icon_del, .W_private_letter .btn a, .W_layer_del-1, .W_layer_del-2, .W_layer_del-1 .content_del span.icon_ask, .W_layer_del-2 .content_del span.icon_ask, .W_layer_del-1 .content_del p.btn a, .W_layer_del-2 .content_del p.btn a, .layoutContent, .layoutContent .content .W_close, .layoutContent .content .W_close:hover, .layoutContent .content .arrow, .layer_send_box, .layer_send_box .content .W_close, .layer_send_box .content .W_close:hover, .layer_send_box .content .arrow, .message_wrap .search_title .operation_btn a.W_btn_b, .message_wrap .search_title .operation_btn a.W_btn_a, .send_private_msg dl.private_SRLr .icon_mes, .private_dialogue .private_SRLl .content .arrow, .private_dialogue .private_SRLr .content .arrow, .private_dialogue .private_SRLr .content .close a, .private_dialogue .private_SRLl .content .close a, .info_tab01 .add_btn a, .Progress_bar .tips, .setting_box fieldset .btns a.W_btn_round_ico, .roommate_list dl dd.btn a.addFriend, .schSearchLayer .sch_list li, .schSearchLayer .btn a, .setting_box .setting_btn a, .lay_box, .lay_box h1 a, .Album_lay .Album_btn, .m_user_albumlist dl dt, .m_user_albumlist dl dt .new, .flash_up .flash_title span.btn, .flash_up .flash_content .upload_btn, .flash_up .flash_content .upload_content ul li .upload_name, .flash_up .flash_content .upload_content ul li .close, .m_photo_list_a dl dt a span.M_ico, .m_path_tit p.ok em, .m_add_tag .btn, .P_uppict_edit_save .M_btn_a, .L_sendweibo .m_selectpic_list li.selected .M_ico, .face_cmn .action .ico_faces, .m_commentBox .cmnt_area .forward .M_ico, .m_commentBox .cmnt_area .submit .M_btn_c, .jcarousel-skin-tango .jcarousel-next-horizontal, .jcarousel-skin-tango .jcarousel-prev-horizontal, .tab_normal .bar_left a.W_btn_c_disable, .tab_normal .bar_left a.on, .tab_normal .bar_left a.W_btn_c_disable span, .tab_normal .bar_left a.W_btn_c_disable span.on, .mylistBox .myfollow_list .info li a.set_group span.W_arrow, .mylistBox .mylihover .info li a.set_group span.W_arrow, .mylistBox .myfollow_list .info li a.set_group.show span.W_arrow, .mylistBox .mylihover .info li a.set_group.show , span.W_arrow, .layer_point .point dt span.icon_warnM, .myfollow_select .tips_succ p.icon .icon_succS, .myfollow_select .ms_opt a.new, .myfollow_select .ms_opt a.cancel, .myfollow_select .ms_opt .error i.icon, .find_big_tab ul.find_nav li.on i, .conResult_sam dl dd.btn a.addFriend, .find_big_tab .tab_layer ul li i, .find_big_tab .tab_layer .find_content_box table td .friend_search_btn, .find_big_tab .tab_layer .conResult_sam_sub dl dd.btn a.addFriend, .event_details dl dd.btn a.addEvent, .W_main_sub_r .event_details dl dd.btn a.addEvent, .album_data .upbtn_small, .ev_album .a_item, .ev_album_title .title_word .icon, .jjz_left_box .M_notice_del span.icon_del, .jjz_left_box .M_notice_succ .icon_succ, .jjz_left_box .M_notice_warn .icon_warn, .jjz_left_box .ev_notice_area dl dd a.W_btn_a, .jjz_left_box .ev_next a.W_btn_d, .W_main_sub_r .ev_infoMbox, .W_main_sub_b .A_head_con .ev_detail_top .ev_detail_cont .ev_detail_btn a.W_btn_d, .W_main_sub_b .pl_event_publisher .btnf .btn, .W_main_sub_b .pl_event_publisher .btnf .btnh, .layer_event_uppic .up_des, .layer_event_uppic .up_con a, .layer_point_upload_img .up_mainContainer ul.grp_list li .album_cover_bg, .layer_point_upload_img .up_mainContainer ul.grp_list li .album_cover_bg .album_grp_con.empty, .layer_point_upload_img .mainContainer ul.photo_list li .selected .chked, .layer_point_upload_img .sl_opt_area a, .layer_event_upimg .up_modify .del , .layer_event_upimg .btn_album_small, .layer_event_upimg .up_modify a.send, .ev_album_single .ic_original, .ev_album_single .ic_pic, .A_2col_left .pl_event_publisher .btnf .btn, .A_2col_left .pl_event_publisher .btnf .btnh, .plc_profile_header .pl_profile_hisInfo .pf_tags .tags em, .plc_profile_header .pl_profile_hisInfo .pf_do .btn_bed .editInformation, .plc_profile_header .pl_profile_hisInfo .pf_do .btn_bed .addFriend, .plc_profile_header .pl_profile_hisInfo .pf_do .focusLink, .pf_badge a, .send_weibo .btnf .btn_weibo, .slide_panel .slide_panel_pager i, .pl_profile_nav ul, .tab_friend a.on, .W_main_sub_b .conResult_sam dl dd .focusLink, .pl_content_account .infoblock .info_title fieldset .btns a.W_btn_round, .pl_content_account .pf_item .btn a, .pl_content_account .pf_item .action .icon_edit, .pl_content_account .pf_item .action .icon_close, .invite_register .nologin .img_lang, .WB_detail_Single .WB_arrow, .WB_detail_Single .WB_arrow3, .layer_point .icon_del, .layer_point .layer1_commit a, .layer_point .p_btn a, .W_signbg table td .tips .icon, .photo_wall_header, .photo_wall_header .photo_header_bg, .photo_wall_header .photo_header_bg ul li a, .photo_wall_header .photo_header_bg ul li a.on, .p_content dl dd.difficulty i.star, .p_content dl div.btn, .W_medal .btn a, .W_main_sub_b .p_content dl .description_btn a, .W_main_sub_r .event_details dl dd.btn a.addEvent, .W_main_sub_r .ev_infoMbox, .ul_medLs_3 li .img_show .ico_closeZ a, .btn_szshow a.btn_greenLg, .photo_wall_top, .photo_wall_center, .photo_wall_bottom, .photo_wall_center .photo_content .group .title, .photo_wall_center .photo_content .group .title .btn, .photo_wall_center .photo_content .group .badge_list .left_bg, .photo_wall_center .photo_content .group .badge_list ul, .photo_wall_center .photo_content .group .pic_list ul li, .photo_wall_center .photo_content .group .pic_list ul li.empty .upload_tips .btn, .photo_wall_center .photo_content .group .badge_list .right_bg, .search_input_b .searchBtn, .search_logo, .list_person .person_addr i, .person_adbtn a.addFriend, .person_adbtn .focusLink, .more_title1 .close_btn, .search_adv_detail dl.p_btn a, .W_signbg table td .tips .icon, body.sign .sign_form .tips .icon, #v_tip1, #v_err1 em, .return_btn, .return_btn:hover, .p_content div.btn_badge, .WB_right_module ul.badge_list li img, .p_content dl dt.badge img, .btnf .icon_detail a i, .little_book_main .top, .little_book_main .center, .little_book_main .bottom, .little_book_nav dl.lable li, .little_book_nav dl.lable li span, .little_book_nav dl.lable li span a, .littlebook_topnav .left, .littlebook_topnav .right, .littlebook_topnav ul, .littlebook_topnav ul li a:hover');