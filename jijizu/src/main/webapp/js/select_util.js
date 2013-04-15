 /***************************************************************************
	 * 文 件 名：selectListTools.js 文件描述：关于list列表框的一些工具方法 主要方法： 1，
	 * moveUp(oSelect,isToTop) －－－－－－－－－－－－ 向上移动一个list列表框的选中项目，
	 * 可以支持多选移动，可以设置是否移动到顶层 2， moveDown(oSelect,isToBottom)－－－－－－－－－－
	 * 向下移动一个list列表框的选中项目， 可以支持多选移动，可以设置是否移动到底层 3，
	 * moveSelected(oSourceSel,oTargetSel) －－－－－－ 在两个列表框之间转移数据 4，
	 * moveAll(oSourceSel,oTargetSel)－－－－－－－－－ 转移两个列表框之间的全部数据 5，
	 * deleteSelectItem(oSelect) －－－－－－－－－－－ 删除所选的项目
	 * 
	 **************************************************************************/
  
 /**
	 * 使选中的项目上移
	 * 
	 * oSelect: 源列表框 isToTop: 是否移至选择项到顶端，其它依次下移， true为移动到顶端，false反之，默认为false
	 */
 var SelectUtil=new Object();

 SelectUtil.moveUp=function(oSelect,isToTop)
 {
     // 默认状态不是移动到顶端
     if(isToTop == null)
         var isToTop = false;
         
     // 如果是多选------------------------------------------------------------------
     if(oSelect.multiple)
     {
         for(var selIndex=0; selIndex<oSelect.options.length; selIndex++)
         {
             // 如果设置了移动到顶端标志
             if(isToTop)
             {
                 if(oSelect.options[selIndex].selected)
                 {
                     var transferIndex = selIndex;
                     while(transferIndex > 0 && !oSelect.options[transferIndex - 1].selected)
                     {
                         oSelect.options[transferIndex].swapNode(oSelect.options[transferIndex - 1]);
                         transferIndex --;
                     }
                 }
             }
             // 没有设置移动到顶端标志
             else
             {
                 if(oSelect.options[selIndex].selected)
                 {
                     if(selIndex > 0)
                     {
                         if(!oSelect.options[selIndex - 1].selected)
                             oSelect.options[selIndex].swapNode(oSelect.options[selIndex - 1]);
                     }
                 }
             }
         }
     }
     // 如果是单选--------------------------------------------------------------------
     else
     {
         var selIndex = oSelect.selectedIndex;
         if(selIndex <= 0)
             return;
         // 如果设置了移动到顶端标志
         if(isToTop)
         {
             while(selIndex > 0)
             {
                 oSelect.options[selIndex].swapNode(oSelect.options[selIndex - 1]);
                 selIndex --;
             }
         }
         // 没有设置移动到顶端标志
         else        
             oSelect.options[selIndex].swapNode(oSelect.options[selIndex - 1]);
     }
 }

/**
 * 使选中的项目下移
 * 
 * oSelect: 源列表框 isToTop: 是否移至选择项到底端，其它依次上移， true为移动到底端，false反之，默认为false
 */
 SelectUtil.moveDown=function(oSelect,isToBottom)
 {
     // 默认状态不是移动到顶端
     if(isToBottom == null)
         var isToBottom = false;
         
     var selLength = oSelect.options.length - 1;
     
     // 如果是多选------------------------------------------------------------------
     if(oSelect.multiple)
     {
         for(var selIndex=oSelect.options.length - 1; selIndex>= 0; selIndex--)
         {
             // 如果设置了移动到顶端标志
             if(isToBottom)
             {
                 if(oSelect.options[selIndex].selected)
                 {
                     var transferIndex = selIndex;
                     while(transferIndex < selLength && !oSelect.options[transferIndex + 1].selected)
                     {
                         oSelect.options[transferIndex].swapNode(oSelect.options[transferIndex + 1]);
                         transferIndex ++;
                     }
                 }
             }
             // 没有设置移动到顶端标志
             else
             {
                 if(oSelect.options[selIndex].selected)
                 {
                     if(selIndex < selLength)
                     {
                         if(!oSelect.options[selIndex + 1].selected)
                             oSelect.options[selIndex].swapNode(oSelect.options[selIndex + 1]);
                     }
                 }
             }
         }
     }
     // 如果是单选--------------------------------------------------------------------
     else
     {
         var selIndex = oSelect.selectedIndex;
         if(selIndex >= selLength - 1)
             return;
         // 如果设置了移动到顶端标志
         if(isToBottom)
         {
             while(selIndex < selLength - 1)
             {
                 oSelect.options[selIndex].swapNode(oSelect.options[selIndex + 1]);
                 selIndex ++;
             }
         }
         // 没有设置移动到顶端标志
         else        
             oSelect.options[selIndex].swapNode(oSelect.options[selIndex + 1]);
     }
 }

/**
 * 移动select的部分内容,必须存在value，此函数以value为标准进行移动
 * 
 * oSourceSel: 源列表框对象 oTargetSel: 目的列表框对象
 */
 SelectUtil.moveSelected=function(oSourceSel,oTargetSel)
 {
     // 建立存储value和text的缓存数组
     var arrSelValue = new Array();
     var arrSelText = new Array();
     // 此数组存贮选中的options，以value来对应
     var arrValueTextRelation = new Array();
     var index = 0;// 用来辅助建立缓存数组
     
     // 存储源列表框中所有的数据到缓存中，并建立value和选中option的对应关系
     for(var i=0; i<oSourceSel.options.length; i++)
     {
         if(oSourceSel.options[i].selected)
         {
             // 存储
             arrSelValue[index] = oSourceSel.options[i].value;
             arrSelText[index] = oSourceSel.options[i].text;
             // 建立value和选中option的对应关系
             arrValueTextRelation[arrSelValue[index]] = oSourceSel.options[i];
             index ++;
         }
     }
     
     // 增加缓存的数据到目的列表框中，并删除源列表框中的对应项
     for(var i=0; i<arrSelText.length; i++)  
     {
         // 增加
         var oOption = document.createElement("option");
         oOption.text = arrSelText[i];
         oOption.value = arrSelValue[i];
         oTargetSel.options[oTargetSel.options.length]=new Option(arrSelText[i],arrSelValue[i]);
         
         // 删除源列表框中的对应项
         oSourceSel.removeChild(arrValueTextRelation[arrSelValue[i]]);
     }
 }

/**
 * 移动select的整块内容
 * 
 * oSourceSel: 源列表框对象 oTargetSel: 目的列表框对象
 */
 SelectUtil.moveAll=function (oSourceSel,oTargetSel)
 {
     // 建立存储value和text的缓存数组
     var arrSelValue = new Array();
     var arrSelText = new Array();
     
     // 存储所有源列表框数据到缓存数组
     for(var i=0; i<oSourceSel.options.length; i++)
     {
         arrSelValue[i] = oSourceSel.options[i].value;
         arrSelText[i] = oSourceSel.options[i].text;
     }
     
     // 将缓存数组的数据增加到目的select中
     for(var i=0; i<arrSelText.length; i++)  
     {
         var oOption = document.createElement("option");
         oOption.text = arrSelText[i];
         oOption.value = arrSelValue[i];
         oTargetSel.add(oOption);
     }
     
     // 清空源列表框数据，完成移动
     oSourceSel.innerHTML = "";
 }

/**
 * 删除所有项目
 * 
 * oSelect: 源列表框对象
 */
 SelectUtil.deleteAll=function(oSelect)
 {
     for(var i=0; i<oSelect.options.length; i++)
     {
         if(i>=0 && i<=oSelect.options.length-1)
         {

             oSelect.options[i] = null;

             i --;
         }
     }
 }

/**
 * 删除选定项目
 * 
 * oSelect: 源列表框对象
 */
 SelectUtil.deleteSelectItem=function(oSelect)
 {
     for(var i=0; i<oSelect.options.length; i++)
     {
         if(i>=0 && i<=oSelect.options.length-1 && oSelect.options[i].selected)
         {
             oSelect.options[i] = null;
             i --;
         }
     }
 }
 SelectUtil.SelectAll=function(oSelect){
 	for(var i=0;i<oSelect.options.length;i++){
 		oSelect.options[i].selected=true;
 	}
 }

 
 SelectUtil.SelectValue=function(oSelect,value){
	 if(oSelect == undefined){
	 	 return;
	 }
  
 	for(var i=0;i<oSelect.options.length;i++){
 		if(oSelect.options[i].value==value){
	 		oSelect.options[i].setAttribute("selected",true);//selected=true;	 			
	 		// oSelect.selectedIndex = i;
	 	}
 	}
 }
 SelectUtil.GetSelectValue=function(oSelect){
 if(oSelect ==null){
	 	 return;
	 }
 	if(oSelect.selectedIndex>=0){
 		return oSelect.options[oSelect.selectedIndex].value;
 	}
 	return "";
 }
 
 
 

