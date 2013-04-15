// JavaScript Document
$(function(){
	 //相册照片
	$(".mark_box .z_left2").click(function(){
	var $this = $(this);
	//alert($this.parent().parent().siblings().children().next().next().html())
	$this.parent().parent().siblings().children().next().next().find("*").rotateLeft(90);
	var width = $this.parent().parent().siblings().children().next().next().find("*").width();
	if(width>534){
	$this.parent().parent().siblings().children().next().next().find("*").width(534);
	}
	})
	$(".mark_box .z_right2").click(function(){
	var $this = $(this);
	$this.parent().parent().siblings().children().next().next().find("*").rotateRight(90);
	var width = $this.parent().parent().siblings().children().next().next().find("*").width();
	if(width>534){
	$this.parent().parent().siblings().children().next().next().find("*").width(534);
	}
	}) 
	
}); 