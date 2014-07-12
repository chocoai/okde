<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../template/common/import.jsp"%>
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
		
    <title>专业设置</title>
    <script type="text/javascript">
    	<%--加载全部专业列表 --%>
    	function ajax_load_major(data){
    		var list='';
				if(data.lstrltbool){
					if(data.majorlst.length>0){
						$.each(data.majorlst, function(){
							list+='<tr>';
							list+='<td align="center">';
							list+='<div id="now'+this.id+'">';
							list+=this.name;
							list+='</div>';
							list+='<div id="click'+this.id+'" style="display:none;">';
							list+='<input id="name'+this.id+'" value="" maxlength="64"/>';
							list+='</div>';
							list+='</td>';
						    list+='<td align="center">';
							list+='<div id="now'+this.id+'">';
							if(this.code==null||this.code==""){
								list+='';
							}else{
								list+=this.code;
							}
							list+='</div>';
							list+='<div id="click'+this.id+'" style="display:none;">';
							list+='<input id="code'+this.id+'" value="" maxlength="16"/>';
							list+='</div>';
							list+='</td>';
						    list+='<td align="center">';
							list+='<div id="now'+this.id+'">';
							if(this.basemajorname!=null&&this.basemajorname!=""){
								list+=this.basemajorname;
							}
							list+='<input id="belongid'+this.id+'" type="hidden" value="'+this.belongBaseMajorId+'"/>'
							list+='</div>';
							list+='<div id="click'+this.id+'" style="display:none;">';
							list+='<select id="belongmajor'+this.id+'">';
							list+='</select>';
							list+='</div>';
							list+='</td>';
						    list+='<td align="center">';
						    list+='<a href="#" id="updatemajor'+this.id+'" onclick="changeMajorBtnValue('+this.id+')">修改</a>';
						    list+='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" id="deletemajor'+this.id+'" onclick="deleteOrCancelMajor('+this.id+')">删除</a>';
						    list+='</td>';
						    list+='</tr>';
				 });
					}else{
						list+='<tr class=error>';
						list+='<td colspan="4" align="center">';
						list+='<div>暂时没有相关信息</div>';
						list+='</td>';
						list+='</tr>';
					}
					$('#majortable > tbody').html(list);
					$('#add_major_div').show();
				}else{
					alert("数据异常，请稍后尝试操作");
				}
    	}
   	  <%--保存 --%>
    	function ajax_save_major(data){
    		if(!data.addrltbool){
				show('error_Msg','信息提示:',300,100);
    		}else if(data.errormsg){
	  			show('success_Msg','信息提示:',250,100); 
	  		}else{
	  			show('add_error_Msg','信息提示:',300,100);
	  		}
	  		ajax_001_1();
    	}
      <%-- 修改--%>
    	function ajax_update_major(data){
    		if(!data.updaterltbool){
				show('error_Msg','信息提示:',300,100);
			}else if(data.sameinfobool){
				show('success_Msg','信息提示:',250,100);
	  		}else{
	  			show('add_error_Msg','信息提示:',300,100);
	  		}
	  		ajax_001_1();
    	}
      <%--删除 --%>
      	function ajax_delete_major(data){
      		if(data.delrltbool){
  				show('success_Msg','信息提示:',250,100); 
	  		}else{
	  			show('error_Msg','信息提示:',300,100);
	  		}
	  		ajax_001_1();
      	}
    </script>
    <%--加载全部专业列表 --%>
    <a:ajax successCallbackFunctions="ajax_load_major" 
    		pluginCode="001" 
    		urls="/enrollment/list_major_by_deleteflag"
    		parameters="{academyId:'${id}'}"/>
    <a:ajax successCallbackFunctions="ajax_save_major" 
    		pluginCode="002" 
    		urls="/enrollment/add_major"
    		parameters="{'major.name':$('#majorname').val(),'major.code':$('#majorcode').val(),'major.academyId':'${id}','major.belongBaseMajorId':$('#majorbelong0').val()}"/>
    <a:ajax successCallbackFunctions="ajax_update_major" 
    		pluginCode="003" 
    		urls="/enrollment/update_major" 
    		parameters="{majorid:majorid,'major.name':$('#name'+majorid).val(),'major.code':$('#code'+majorid).val(),'major.belongBaseMajorId':$('#belongmajor'+majorid).val()}"/>
    <a:ajax successCallbackFunctions="ajax_delete_major" 
    		pluginCode="004" 
    		urls="/enrollment/delete_major"
    		parameters="{majorid:majorid}"/>
    <script type="text/javascript">
    	var majorid=0;
    	$(function(){
    		ajax_001_1();
    		initializeBaseMajorOnLoad();
    	});    	
    	//增加一行输入框，用于填写专业信息
    	function addMajor(){
    		$('#majortable > tbody tr.error').remove();
    		$('#add_major_div').hide();
    		var list='';
    		var selectlist=$('#belongs_to_major').html();
    		list=$('#majortable > tbody').html();
			list+='<tr id="majortr">';
		    list+='<td align="center">'; 
		    list+='<input id="majorname" class="txt_box_130" maxlength="64"/>';
		    list+='</td>'; 
		    list+='<td align="center">'; 
		    list+='<input id="majorcode" class="txt_box_130" maxlength="16"/>';
		    list+='</td>'; 
		    list+='<td align="center">'; 
		    list+='<select id="majorbelong0" class="txt_box_130" />';
		    list+='</select>';
		    list+='</td>'; 
		    list+='<td align="center">';  
		    list+='<a href="#" onclick="saveMajor()">保存</a>';	 
		    list+='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="cancelMajor()">取消</a>'; 
		    list+='</td>';
		    list+='</tr>';
		    $('#majortable > tbody').html(list);
		    $('#majorbelong0').html(selectlist);
    	}
    	//取消一行尚未保存的输入框
    	function cancelMajor(){
    		$("#majortr").remove();
    		$('#add_major_div').show();
    	}
    	//保存当前一行输入的专业信息
    	function saveMajor(){
    		if($("#majorname").val()==null||$("#majorname").val()==""||$.trim($("#majorname").val())==""){
    			show('fill_in_info_error','信息提示:',250,100); 
    		}else if($("#majorcode").val()==null||$("#majorcode").val()==""||$.trim($("#majorcode").val())==""){
    			show('fill_in_info_error','信息提示:',250,100); 
    		}else{
    			ajax_002_1();
    		}
    	}
    	
		//切换是否可以修改信息,保存修改信息
		function changeMajorBtnValue(id){
			var updatebtn=$('#updatemajor'+id).html();//获取保存/修改对象
			var deletebtn=$('#deletemajor'+id).html();//获取取消/删除对象
			
			if(updatebtn=="修改"){  	
				
				initializeBaseMajor('belongmajor',id);
				
				//nth(0)取id为"now"+id的第一个div的值，1为第二个div的值
				$('#name'+id).val($("div[id='now"+id+"']:nth(0)").html());
				$('#code'+id).val($("div[id='now"+id+"']:nth(1)").html());
				
				$('#updatemajor'+id).html("保存");
				$('#deletemajor'+id).html("取消");
				setTimeout("$(\"div[id='now"+id+"']\").hide();$(\"div[id='click"+id+"']\").show();",200);
			}else{
				if($("#name"+id).val()==null||$("#name"+id).val()==""||$.trim($("#name"+id).val())==""){
	    			show('fill_in_info_error','信息提示:',250,100); 
	    		}else if($("#code"+id).val()==null||$("#code"+id).val()==""||$.trim($("#code"+id).val())==""){
	    			show('fill_in_info_error','信息提示:',250,100); 
	    		}else{
	    			majorid=id;
				  	ajax_003_1();
				$('#updatemajor'+id).html("修改");
				$('#deletemajor'+id).html("删除");
				$("div[id='now"+id+"']").show();
				$("div[id='click"+id+"']").hide();
	    		}
			}
		}
	
		//删除当前选定的一条专业或是取消当前选定专业的可编辑状态
		function deleteOrCancelMajor(id){
			var deletebtn=$('#deletemajor'+id).html();//获取取消/删除对象
			var updatebtn=$('#updatemajor'+id).html();//获取保存/修改对象
			
			if(deletebtn=="取消"){
				$('#updatemajor'+id).html("修改");
				$('#deletemajor'+id).html("删除");
				$("div[id='now"+id+"']").show();
				$("div[id='click"+id+"']").hide();
			}else{
				var isdel=confirm("您确定要删除吗？");
				if(isdel){
					majorid=id;
					ajax_004_1();
				}
			}
		}
		//加载基础设置-专业列表
			function initializeBaseMajor(name,id){
				$.post('<uu:url url="/basesetting/basemajor/list_base_major_by_flag"/>',
					function(data) 
					  	{
					  		if(data.lstrltbool)
					  		{
					  			$("#"+name+id).empty();
					  			if(data.basemajorlist!=null&&data.basemajorlist.length>0){
					  				$.each(data.basemajorlist, function(){
										$("#"+name+id).append('<option value="'+this.id+'">'+this.name+'</option>'); 
									});
									$('#'+name+id).val($("#belongid"+id).val());
					  			}else{
					  				$("#"+name+id).append('<option value="0">暂无相关数据</option>'); 
					  			}
					  		}else{
					  			alert("数据异常，请稍后尝试重新操作");
					  		}
					  	},"json");  
			}
		
			//初始化加载基础设置-专业列表
			function initializeBaseMajorOnLoad(){
				$.post('<uu:url url="/basesetting/basemajor/list_base_major_by_flag"/>',
					function(data) 
					  	{
					  		if(data.lstrltbool)
					  		{
					  			$("#belongs_to_major").empty();
					  			if(data.basemajorlist!=null&&data.basemajorlist.length>0){
					  				$.each(data.basemajorlist, function(){
										$("#belongs_to_major").append('<option value="'+this.id+'">'+this.name+'</option>'); 
									});
									//$('#'+name+id).val($("#belongid"+id).val());
					  			}else{
					  				$("#belongs_to_major").append('<option value="0">暂无相关数据</option>'); 
					  			}
					  		}else{
					  			alert("数据异常，请稍后尝试重新操作");
					  		}
					  	},"json");  
			}
    </script>
  </head>
  <body>
  	<!-- 头开始 -->
		<head:head title="院校名称:${academyName}">
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<!--Menu Begin-->
				<%@ include file="../academy/_tab_title/academy_tab.jsp" %>
			<!--Menu End-->
			
			<!--Left Begin-->
			
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="../images/title_menu/title_left.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">专业设置</th>
					<th style="text-align:right; font-weight:bold;"><div id="conmenu"><a id="add_major_div" href="#" onclick="addMajor()"><img src="../images/title_menu/icon_add.gif" />增加</a></div></th>
				</tr>
			</table>
				
			<table class="gv_table_2" id="majortable" border="0" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th >专业名称</th>
						<th >编号</th>
						<th >所属基础专业</th>
						<th >操作</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
			<select id="belongs_to_major" style="display: none;" class="txt_box_130"></select>
			<!--Left End-->	
  		</body:body>
  		<div id="fill_in_info_error" style="display:none;font-weight:bold" align="center"><br/>请填写完整数据！<br/></div>
		   <div id="success_Msg" style="display:none;font-weight:bold" align="center"><br/>操作成功！<br/></div>
		   <div id="add_error_Msg" style="display:none;font-weight:bold" align="center"><br/>数据库存在重复数据，请重新执行本次操作！<br/></div>
		   <div id="error_Msg" style="display:none;font-weight:bold" align="center"><br/>服务器繁忙，请您稍后尝试操作！<br/></div>
  </body>
</html>