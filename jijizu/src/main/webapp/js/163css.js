/*163css*/
phjQuery = {
	bindEvt : function() {
		var o = {};
		o.btn_sell_l = jQuery("#pageLeft");
		if (o.btn_sell_l.length > 0) {
			o.btn_sell_l.bind("click", function() {
				phjQuery.scrollLveSell('right');
			});
		}
		o.btn_sell_r = jQuery("#pageRight");
		if (o.btn_sell_r.length > 0) {
			o.btn_sell_r.bind("click", function() {
				phjQuery.scrollLveSell('left');
			});
		}

	},
	scrollLveSell : function(o) {
		if (this.cfg.lv_flag == 0)
			return false;
		var tar = jQuery("#sell_order");
		var tar2 = jQuery("#sell_order_t");
		tar.stop();
		tar2.stop();
		var max_num = jQuery("#sell_order>li").length;
		var width = 196;
		var left = 0;
		var pos = tar.position();
		//alert(pos.left + "|" + width);
		if (o == "left") {
			if (Math.abs(pos.left)+width > width*(max_num-5)) {
				return false;
			}
			left = pos.left - width;
		} else if (o == "right") {
			if (pos.left >= 0) {
				return false;
			}
			left = pos.left + width;
		}
		left = left + "px";
		//alert(left);
		phjQuery.cfg.lv_flag = 0;
		tar.animate({
			left: left
		}, 600);
		tar2.animate({
			left: left
		}, 600, function() {
			phjQuery.cfg.lv_flag = 1;
			//alert(pos.left);
		});
	},

	cfg : {

	},
	init : function() {
		this.bindEvt();
	}
};

jQuery(function(jQuery) {
	phjQuery.init();
});