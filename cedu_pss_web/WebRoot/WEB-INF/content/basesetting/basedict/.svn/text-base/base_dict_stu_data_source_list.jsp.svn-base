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
    	//加载全部数据来源列表
    	function ajax_load_stu_data_source(data){
    		var list='';
			if(data.lstrltbool){
				if(data.basedictlst.length>0){
					$.each(data.basedictlst, function(){
						list+='<tr>';
						list+='<td align="center" width="35%">';
						list+='<div id="now'+this.id+'">';
						list+=this.name;
						list+='</div>';
						list+='<div id="click'+this.id+'" style="display:none;">';
						list+='<input id="name'+this.id+'" value="" maxlength="128"/>';
						list+='</div>';
						list+='</td>';
					    list+='<td align="center" width="35%">';
						list+='<div id="now'+this.id+'">';
						list+=this.code;
						list+='</div>';
						list+='<div id="click'+this.id+'" style="display:none;">';
						list+='<input id="code'+this.id+'" value="" disabled="disabled"/>';
						list+='</div>';
						list+='</td>';
					    list+='<td align="center" width="30%">';
					    if(this.id>INIT_BASE_DICT_ID){
					    	//list+='<a href="#" id="updatestudentdatasource'+this.id+'" onclick="changeBtnValue('+this.id+')">修改</a>';
					    	//list+='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" id="deletestudentdatasource'+this.id+'" onclick="deleteOrCancelStudentDataSource('+this.id+')">删除</a>';
					    }else{
					    	list+='--';
					    }
					    list+='</td>';
					    list+='</tr>';
			 });
				}else{
						list+='<tr class=error>';
						list+='<td colspan="3" align="center">';
						list+='<div>暂时没有相关信息</div>';
						list+='</td>';
						list+='</tr>';
				}
				$('#studentdatasourcetable > tbody').html(list);
				$('#add_data_source').show();
			}else{
				show('error_Msg','信息提示:',300,100);
			}				
    	}
    	//接收添加方法回调函数，进行相关操作
		 function ajax_save_stu_data_source(data){
	  		if(!data.addrltbool){
				show('error_Msg','信息提示:',300,100);
    		}else if(data.errormsg){
	  			show('success_Msg','信息提示:',250,100); 
	  		}else{
	  			show('add_error_Msg','信息提示:',300,100);
	  		}
	  		ajax_001_1();
		}
		//接收更新方法回调函数，进行相关操作
		function  ajax_update_stu_data_source(data){
			if(!data.updaterltbool){
				show('error_Msg','信息提示:',300,100);
			}else if(data.sameinfobool){
				show('success_Msg','信息提示:',250,100);
	  		}else{
	  			show('add_error_Msg','信息提示:',300,100);
	  		}
	  		ajax_001_1();
		}	
		
		//接收删除方法回调函数，进行相关操作
		function ajax_delete_stu_data_source(data){
			if(data.delrltbool){
	  			show('success_Msg','信息提示:',400,130); 
	  		}else{
	  			show('error_Msg','信息提示:',400,130);
	  		}
	  		ajax_001_1();
		}	
    </script>
    <a:ajax parameters="{basedicttype:type};{'basedict.name':$('#studentdatasourcename').val(),'basedict.code':$('#studentdatasourcecode').val(),'basedict.type':type};{basedictid:basedictid,'basedict.name':$('#name'+basedictid).val(),'basedict.code':$('#code'+basedictid).val()};{basedictid:basedictid}" 
    		successCallbackFunctions="ajax_load_stu_data_source;ajax_save_stu_data_source;ajax_update_stu_data_source;ajax_delete_stu_data_source" 
    		urls="/basesetting/basedict/list_base_dict_flag;/basesetting/basedict/add_base_dict;/basesetting/basedict/update_base_dict;/basesetting/basedict/delete_base_dict" 
    		pluginCode="001"/>
    		
    <script type="text/javascript">
    	//全局变量 
    	var type=BASEDICT_STYLE_STUDATASOURCE;
    	var basedictid=0;
    	
    	$(function(){
    		ajax_001_1();
    	});
	
    	//增加一行输入框，用于填写数据来源信息
    	function addStudentDataSource(){
    		$('#studentdatasourcetable > tbody tr.error').remove();
    		$('#add_data_source').hide();
    		var list='';
    		list+=$('#studentdatasourcetable > tbody').html();
			list+='<tr id="studentdatasourcetr">';
		    list+='<td align="center">'; 
		    list+='<input id="studentdatasourcename" class="txt_box_150" maxlength="128"/>';
		    list+='</td>'; 
		    list+='<td align="center">'; 
		    list+='';
		    list+='</td>'; 
		    list+='<td align="center">';  
		    list+='<a href="#" onclick="saveStudentDataSource()">保存</a>';	 
		    list+='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="cancelStudentDataSource()">取消</a>'; 
		    list+='</td>';
		    list+='</tr>';
		    $('#studentdatasourcetable > tbody').html(list);
    	}
    	//取消一行尚未保存的输入框
    	function cancelStudentDataSource(){
    		$('#add_data_source').show();
    		$("#studentdatasourcetr").remove();
    	}
    	//保存当前一行输入的数据来源信息
    	function saveStudentDataSource(){
    		if($("#studentdatasourcename").val()==null||$("#studentdatasourcename").val()==""||$.trim($("#studentdatasourcename").val())==""){
    			show('fill_in_info_error','信息提示:',250,100); 
    		}else{
    			ajax_001_2();
    		}
    	}
    	
	
	//切换是否可以修改信息,保存修改信息
	function changeBtnValue(id){
		var updatebtn=$('#updatestudentdatasource'+id).html();//获取保存/修改对象
		var deletebtn=$('#deletestudentdatasource'+id).html();//获取取消/删除对象
		
		if(updatebtn=="修改"){  	
			//nth(0)取id为"now"+id的第一个div的值，1为第二个div的值
			$('#name'+id).val($("div[id='now"+id+"']:nth(0)").html());
			$('#code'+id).val($("div[id='now"+id+"']:nth(1)").html());
			$('#updatestudentdatasource'+id).html("保存");
			$('#deletestudentdatasource'+id).html("取消");
			$("div[id='now"+id+"']").hide();
			$("div[id='click"+id+"']").show();
		}else{
			if($("#name"+id).val()==null||$("#name"+id).val()==""||$.trim($("#name"+id).val())==""){
    			show('fill_in_info_error','信息提示:',250,100); 
    		}else{
			  	basedictid=id;
			  	ajax_001_3();
				$('#updatestudentdatasource'+id).html("修改");
				$('#deletestudentdatasource'+id).html("删除");
				$("div[id='now"+id+"']").show();
				$("div[id='click"+id+"']").hide();
    		}
		}
	}
	
	//删除当前选定的一条数据来源或是取消当前选定数据来源的可编辑状态
	function deleteOrCancelStudentDataSource(id){
		var deletebtn=$('#deletestudentdatasource'+id).html();//获取取消/删除对象
		var updatebtn=$('#updatestudentdatasource'+id).html();//获取保存/修改对象
		
		if(deletebtn=="取消"){
			$('#updatestudentdatasource'+id).html("修改");
			$('#deletestudentdatasource'+id).html("删除");
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

    </script>

  </head>
  
  <body>
		<!--头部层开始 -->
		<head:head title="数据来源">
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
						 	<th style="text-align:left; font-weight:bold;"> 数据来源</th>
							<th style="text-align:right; font-weight:bold;"><div id="conmenu"><a id="add_data_source" href="javascript:void(0)" onclick="addStudentDataSource()"><img src="<ui:img url="images/title_menu/icon_add.gif" />" />增加</a></div></th>
						</tr>
					</table>
						
					<table class="gv_table_2" id="studentdatasourcetable" border="0" cellpadding="0" cellspacing="0">
						<thead>
							<tr>
								<th >数据来源名称</th>
								<th >编号</th>
								<th >操作</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<!--Left End-->	
		  		</body:body>
		   <div id="fill_in_info_error" style="display:none;font-weight:bold" align="center"><br/>请填写完整数据！<br/></div>
		   <div id="success_Msg" style="display:none;font-weight:bold" align="center"><br/>操作成功！<br/></div>
		   <div id="add_error_Msg" style="display:none;font-weight:bold" align="center"><br/>数据库存在重复数据，请重新执行本次操作！<br/></div>
		   <div id="error_Msg" style="display:none;font-weight:bold" align="center"><br/>服务器繁忙，请您稍后尝试操作！<br/></div>
  </body>
</html>
