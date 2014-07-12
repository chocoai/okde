<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		
    	<!-- 整体样式 -->
		<jc:plugin name="default" />
		
		<jc:plugin name="tab" />
		
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		
		<!--ajax 载入特效  -->
		<jc:plugin name="loading"/> 
		
    <title>基础设置</title>
    <script type="text/javascript">
    	var childId=0;
    	var parentNodes=0;
    	var names='';
    
    	//加载全部市场途径列表
    	function ajax_load_enrollment_way(data){ 
 			var list='';
 			var numbers=1;
 			var num=1;
			if(data.lstrltbool){
				if(data.basedictlst.length>0){
					numbers=1;
					$.each(data.basedictlst, function(){
						list+='<tr>';
						list+='<td align="center">'+numbers+'</td>';
						list+='<td align="center" width="30%">';
						list+='<div id="now'+this.id+'">';
						list+=this.name;
						list+='</div>';
						list+='<div id="click'+this.id+'" style="display:none;">';
						list+='<input id="name'+this.id+'" value="" maxlength="128"/>';
						list+='</div>';
						list+='</td>';
						
					    list+='<td align="center" width="30%">';
						list+='<div id="now'+this.id+'">';
						if(this.code==null||this.code==""){
							list+='';
						}else{
							list+=this.code;
						}
						list+='</div>';
						list+='<div id="click'+this.id+'" style="display:none;">';
						list+='<input id="code'+this.id+'" value="" disabled="disabled"/>';
						list+='</div>';
						list+='</td>';
						
						//排序列开始
						list+='<td align="center" width="50px">';
						list+='<div id="now'+this.id+'">';
						
						list+=this.orderNumber;
						
						list+='</div>';
						list+='<div id="click'+this.id+'" style="display:none;">';
						list+='<input style="width:25px;" id="order'+this.id+'" value=""/>';
						list+='</div>';
						list+='</td>';
						//排序结束
						
						
						
					    list+='<td align="center" width="30%">';
					    //if(this.id>INIT_BASE_DICT_ID && this.parentNode==0 ){
					   	if(true){
					    	if(this.baseDictList!=null && this.baseDictList.length>0)
					    	{
					    		list+='<a href="javascript:void(0)" onclick="openchild('+this.id+')" ><span id="dspan'+this.id+'">显示二级途径</span></a>&nbsp;';
						   
					    	}
						    list+='<a href="#" id="updateenrollmentway'+this.id+'" onclick="changeBtnValue('+this.id+')">修改</a>';
						    //list+='&nbsp;<a href="#" onclick="openShouQuan();">授权</a>';
						    //list+='&nbsp;<a href="#">查看授权</a>';
						   // list+='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" id="deleteenrollmentway'+this.id+'" onclick="deleteOrCancelEnrollmentWay('+this.id+')">删除</a>';
						}
						else if(this.baseDictList!=null && this.baseDictList.length>0)
						{
							list+='<a href="javascript:void(0)" onclick="openchild('+this.id+')" ><span id="dspan'+this.id+'">显示二级途径</span></a>';
						}
						else{
							list+='--';
						}
					    list+='</td>';
					    list+='</tr>';
					   	
					    if(this.baseDictList!=null && this.baseDictList.length>0)
					    {
					    	num=1;
					    	var parentId=this.id;
					    	$.each(this.baseDictList,function(){
					    		list+='<tr id="tr_'+parentId+'" class="tr_'+parentId+'" style="display:none" ><td align="center">'+numbers+'_'+num+'</td><td align="center" width="30%" style="font-style: italic">'+this.name+'</td><td align="center" width="30%">'+this.code+'</td><td align="center" width="50px">'+this.orderNumber+'</td><td align="center" width="30%"><a href="#"  onclick="updatechild('+this.id+','+this.parentNode+',\''+this.name+'\',\''+this.orderNumber+'\')" >修改</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>';//<a href="#" onclick="deleteOrCancelEnrollmentWay('+this.id+')">删除</a>
					    		num++;
					    	});
					    	
					    }
					    numbers++;
			 	});
			}else{
					list+='<tr class="error">';
					list+='<td colspan="3" align="center">';
					list+='<div>暂时没有相关信息</div>';
					list+='</td>';
					list+='</tr>';
			}
				
				$('#enrollmentwaytable > tbody').html(list);
				$('#add_enroll_way').show();
			}else{
				show('error_Msg','信息提示:',300,100);
			}
    	}
	    //接收添加方法回调函数，进行相关操作
	    function ajax_save_enrollment_way(data){ 
			if(!data.addrltbool){
				show('error_Msg','信息提示:',300,100);
    		}else if(data.errormsg){
    			cancelEnrollmentWay();
	  			show('success_Msg','信息提示:',250,100); 
	  		}else{
	  			show('add_error_Msg','信息提示:',300,100);
	  		}
			ajax_001_1();
			ajax_001_5();
		}
		//接收更新方法回调函数，进行相关操作
		function ajax_update_enrollment_way(data){
			if(!data.updaterltbool){
				show('error_Msg','信息提示:',300,100);
			}else if(data.sameinfobool){
				show('success_Msg','信息提示:',250,100);
	  		}else{
	  			show('add_error_Msg','信息提示:',300,100);
	  		}
	  		ajax_001_1();
	  		ajax_001_5();
		}
		//接收删除方法回调函数，进行相关操作
		function ajax_delete_enrollment_way(data){
			if(data.delrltbool){
	  			show('success_Msg','信息提示:',250,100); 
	  		}else{
	  			cancelEnrollmentWay();
	  			show('error_Msg','信息提示:',300,100);
	  		}
	  		ajax_001_1();
	  		ajax_001_5();
		}	
		
		function ajax_list_enrollment_way_parentnode(data)
		{
			var dlist='';
			$.each(data.basedictlst,function(){
				if(data.basedictlst!=null && data.basedictlst.length>0)
				{
					dlist+='<option value="'+this.id+'">'+this.name+'</option>';
				}else
				{
					dlist+='<option value="0">无数据</option>';
				}
				$('#selenrollway').html(dlist);
			});
		}
		
		function ajax_add_child_enrollment_way(data)
		{
			if(!data.addrltbool)
			{
				show('error_Msg','信息提示:',300,100);
				
			}
			if(!data.errormsg)
			{
				show('add_error_Msg','信息提示:',300,100);
				
			}else
			{
				show('success_Msg','信息提示:',300,100);		
				
			}
			ajax_001_1();
			ajax_001_5();
			closes('addChildDiv');
		}
		
		function openchild(id)
		{
			var dspan=$('#dspan'+id).html();
			if(dspan=="显示二级途径")
			{
				$('.tr_'+id).attr("style","display:");
				$('#dspan'+id).html("关闭二级途径");
			}else
			{
				$('.tr_'+id).attr("style","display:none");
				$('#dspan'+id).html("显示二级途径");
			}
			
		}
		
		
		function updatechild(id,parentNode,name,order)
		{
			 childId=id;
	    	 parentNodes=parentNode;
	    	 names=name;
	    	 $('#selenrollway').val(parentNode);
	    	 $('#name_child').val(name);
	    	 $('#order_child').val(order);
	    	 $('#save_submit').val('修改');
			 show('addChildDiv','添加二级途径',400,200);
			 ajax_001_1();
	    	 
	    	 
		}
		
		function ajax_update_child_enrollment_way(data)
		{
			if(!data.updaterltbool){
				show('error_Msg','信息提示:',300,100);
			}else if(data.sameinfobool){
				show('success_Msg','信息提示:',250,100);
	  		}else{
	  			show('add_error_Msg','信息提示:',300,100);
	  		}
	  		ajax_001_1();
	  		ajax_001_5();
	  		closes('addChildDiv');
		}
		
		
		
		
    </script>
    <a:ajax parameters="{basedicttype:type};{'basedict.name':$('#enrollmentwayname').val(),'basedict.orderNumber':$('#orderNumber').val(),'basedict.type':type};{basedictid:basedictid,'basedict.name':$('#name'+basedictid).val(),'basedict.orderNumber':$('#order'+basedictid).val()};{basedictid:basedictid};null;{'basedict.parentNode':$('#selenrollway').val(),'basedict.name':$('#name_child').val(),'basedict.type':BASEDICT_STYLE_ENROLLMENTWAY};{'basedict.id':childId,'basedict.parentNode':$('#selenrollway').val(),'basedict.name':$('#name_child').val(),'basedict.orderNumber':$('#order_child').val()}" 
    		successCallbackFunctions="ajax_load_enrollment_way;ajax_save_enrollment_way;ajax_update_enrollment_way;ajax_delete_enrollment_way;ajax_list_enrollment_way_parentnode;ajax_add_child_enrollment_way;ajax_update_child_enrollment_way;" 
    		urls="/basesetting/basedict/list_base_dict_flag;/basesetting/basedict/add_base_dict;/basesetting/basedict/update_base_dict;/basesetting/basedict/delete_base_dict;/basesetting/basedict/list_base_dict_parentnode;/basesetting/basedict/add_child_base_dict;/basesetting/basedict/update_child_base_dict" 
    		pluginCode="001"/>
    		
    <script type="text/javascript">
    	//全局变量 
    	var type=BASEDICT_STYLE_ENROLLMENTWAY;
    	var basedictid=0;
    	$(function(){
    		ajax_001_1();
    		ajax_001_5();
    		$('#shouquan_xuexizhongxin').dialog({
				autoOpen:false,
				modal:true,
				draggable:false,
				resizable:false,
				title:'市场途径授权',
				width: 500,
				height: 340,
				buttons: {
					'保存': function() { 
						alert("市场途径授权");
					}, 
					'取消': function() { 
						$(this).dialog("close"); 
					} 
				}
			});
    	});
    	
    	function openShouQuan(){
    		$('#shouquan_xuexizhongxin').dialog("open");
    	}
   
    	//增加一行输入框，用于填写市场途径信息
    	function addEnrollmentWay(){    		
   			$('#add_enroll_way').hide();
   			$('#enrollmentwaytable > tbody tr.error').remove();
    		var list='';
    		list+=$('#enrollmentwaytable > tbody').html();
			list+='<tr id="enrollmentwaytr">';
			list+='<td align="center">'; 
		    list+='';
		    list+='</td>'; 
		    list+='<td align="center">'; 
		    list+='<input id="enrollmentwayname" class="txt_box_150" maxlength="128"/>';
		    list+='</td>'; 
		    list+='<td align="center">'; 
		    list+='';
		    list+='</td>'; 
		    list+='<td align="center">'; 
		    list+='<input style="width:25px;" id="orderNumber" class="txt_box_150" maxlength="128"/>';
		    list+='</td>'; 
		    list+='<td align="center">';  
		    list+='<a href="#" onclick="saveEnrollmentWay()">保存</a>';	 
		    list+='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="cancelEnrollmentWay()">取消</a>'; 
		    list+='</td>';
		    list+='</tr>';
		    $('#enrollmentwaytable > tbody').html(list);
    	}
    	//取消一行尚未保存的输入框
    	function cancelEnrollmentWay(){
    		$("#enrollmentwaytr").remove();
    		$('#add_enroll_way').show();
    	}
    	//保存当前一行输入的市场途径信息
    	function saveEnrollmentWay(){
    		if($("#enrollmentwayname").val()==null||$("#enrollmentwayname").val()==""||$.trim($("#enrollmentwayname").val())==""||$("#orderNumber").val()==null||$("#orderNumber").val()==""||$.trim($("#orderNumber").val())==""){
    			show('fill_in_info_error','信息提示:',250,100); 
    		}else{
    			ajax_001_2();
    		}
    	}
    	
		//切换是否可以修改信息,保存修改信息
		function changeBtnValue(id){
			var updatebtn=$('#updateenrollmentway'+id).html();//获取保存/修改对象
			var deletebtn=$('#deleteenrollmentway'+id).html();//获取取消/删除对象
			
			if(updatebtn=="修改"){  	
				//nth(0)取id为"now"+id的第一个div的值，1为第二个div的值
				$('#name'+id).val($("div[id='now"+id+"']:nth(0)").html());
				$('#code'+id).val($("div[id='now"+id+"']:nth(1)").html());
				$('#order'+id).val($("div[id='now"+id+"']:nth(2)").html());
				$('#updateenrollmentway'+id).html("保存");
				$('#deleteenrollmentway'+id).html("取消");
				$("div[id='now"+id+"']").hide();
				$("div[id='click"+id+"']").show();
			}else{
				if($("#name"+id).val()==null||$("#name"+id).val()==""||$.trim($("#name"+id).val())==""||$("#order"+id).val()==null||$("#order"+id).val()==""||$.trim($("#order"+id).val())==""){
	    			show('fill_in_info_error','信息提示:',250,100); 
	    		}else{
				  	basedictid=id;
				  	ajax_001_3();
				$('#updateenrollmentway'+id).html("修改");
				$('#deleteenrollmentway'+id).html("删除");
				$("div[id='now"+id+"']").show();
				$("div[id='click"+id+"']").hide();
	    		}
			}
		}
		
		//删除当前选定的一条市场途径或是取消当前选定市场途径的可编辑状态
		function deleteOrCancelEnrollmentWay(id){
			var deletebtn=$('#deleteenrollmentway'+id).html();//获取取消/删除对象
			var updatebtn=$('#updateenrollmentway'+id).html();//获取保存/修改对象
			
			if(deletebtn=="取消"){
				$('#updateenrollmentway'+id).html("修改");
				$('#deleteenrollmentway'+id).html("删除");
				$("div[id='now"+id+"']").show();
				$("div[id='click"+id+"']").hide();
			}else{
				var isdel=confirm("您确定要删除吗？");
				if(isdel){
					basedictid=id;
					ajax_001_4();		
				}
			}
		}
		
		
		//弹出添加二级途径层
		function addChildEnrollmentWay()
		{
			$('#save_submit').val('保存');
			show('addChildDiv','添加二级途径',400,200);
		}
		
		function addChildEnrollWay()
		{
			
			var name=$('#name_child').val();
			var btnval=$('#save_submit').val();
			if($.trim(name)=="")
			{
				show('fill_in_info_error','温馨提示',150,100);
				return false;
			}else if(btnval=="保存"){
				ajax_001_6();
			}
			else
			{
				ajax_001_7();
			}
		}
		
		

    </script>

  </head>
  <body>
		<!--头部层开始 -->
		<head:head title="基础设置">
		</head:head>
		<!--主体层开始 -->
		<body:body>
					<!--Menu Begin-->
					<div>
						<s:include value="../common/menu.jsp" />
					</div>
					<!--Menu End-->
					
					<!--Left Begin-->
					
					<table class="gv_table_2">
				  		<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;">市场途径设置</th>
							<th style="text-align:right; font-weight:bold;"><div id="conmenu"><a id="add_enroll_way" href="javascript:void(0)" onclick="addEnrollmentWay()"><img src="<ui:img url="images/title_menu/icon_add.gif" />" />增加一级途径</a><a href="javascript:void(0)" onclick="addChildEnrollmentWay()"><img src="<ui:img url="images/title_menu/icon_add.gif" />" />增加二级途径</div></th>
						</tr>
					</table>
						
					<table class="gv_table_2" id="enrollmentwaytable" border="0" cellpadding="0" cellspacing="0">
						<thead>
							<tr>
								<th >序号</th>
								<th >市场途径名称</th>
								<th >编码</th>
								<th >排序</th>
								<th >操作</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<!--Left End-->	
					<div id="shouquan_xuexizhongxin" style="display:none">
						<form id="shouquan_xuexizhongxin_form">
							 
						</form> 
					</div>
	    </body:body>
			   <div id="fill_in_info_error" style="display:none;font-weight:bold" align="center"><br/>请填写完整数据！<br/></div>
			   <div id="success_Msg" style="display:none;font-weight:bold" align="center"><br/>操作成功！<br/></div>
			   <div id="add_error_Msg" style="display:none;font-weight:bold" align="center"><br/>数据库存在重复数据，请重新执行本次操作！<br/></div>
			   <div id="error_Msg" style="display:none;font-weight:bold" align="center"><br/>服务器繁忙，请您稍后尝试操作！<br/></div>
  				
  			   <div id="addChildDiv" style="display:none">
  			   
  			   		<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				
				<tr>
					<td class="lable_110">一级市场途径：</td>
					<td>
						<select  id="selenrollway" name="selenrollway">
					
						</select>
					</td>
				</tr>
				<tr>
					<td class="lable_110">二级市场途径名称：</td>
					<td>
						<input id="name_child"  name="name_child" class="txt_box_150" />
					</td>
				</tr>
				<tr>
					<td class="lable_110">排序编号：</td>
					<td>
						<input id="order_child"  name="order_child" class="txt_box_150" />
					</td>
				</tr>

				<tr>
					<td colspan="2" style="padding-top:7px !important; text-align:center !important;"  class="lable_110"><input type="button" value="保存" onclick="return addChildEnrollWay()" id="save_submit" class="btn_black_61"/></td>
				</tr>
			</table>
  			   	
  			   </div>	
  				
  </body>
</html>
