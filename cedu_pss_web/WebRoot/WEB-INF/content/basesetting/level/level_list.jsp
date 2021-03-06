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
		
		<!-- ajax 载入页面特效 -->
		<jc:plugin name="loading"/> 
		
    <title>基础设置</title>
	  <script type="text/javascript">
	    //加载全部层次列表
	    function ajax_load_level(data){
	   		var list='';
			if(data.lstrltbool){
				if(data.levellst.length>0){
					$.each(data.levellst, function(){
						list+='<tr>';
						list+='<td align="center" width="35%">';
						list+='<div id="now'+this.id+'">';
						list+=this.name;
						list+='</div>';
						list+='<div id="click'+this.id+'" style="display:none;">';
						list+='<input id="name'+this.id+'" value="" maxlength="64"/>';
						list+='</div>';
						list+='</td>';
					    list+='<td align="center" width="35%">';
						list+='<div id="now'+this.id+'">';
						list+=this.code;
						list+='</div>';
						list+='<div id="click'+this.id+'" style="display:none;">';
						list+='<input id="code'+this.id+'" value="" maxlength="16"/>';
						list+='</div>';
						list+='</td>';
					    list+='<td align="center" width="30%">';
					    list+='<a href="#" id="updatelevel'+this.id+'" onclick="changeLevelBtnValue('+this.id+')">修改</a>';
					    list+='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" id="deletelevel'+this.id+'" onclick="deleteOrCancelLevel('+this.id+')">删除</a>';
					    list+='</td>';
					    list+='</tr>';
			 });
				}else{
					if($('#leveltable > tfoor >tr').size()<=0){
						list+='<tr class=error>';
						list+='<td colspan="3" align="center">';
						list+='<div>暂时没有相关信息</div>';
						list+='</td>';
						list+='</tr>';
					}
				}
				$('#leveltable > tbody').html(list);
				$('#add_level').show();
			}else{
				show('error_Msg','信息提示:',400,130);
			}				
	    }
	    //接收添加方法回调函数，进行相关操作
	    function ajax_save_level(data){
	    	if(!data.addrltbool){
	    		show('error_Msg','信息提示:',320,100);
    		}else if(data.errormsg){
    			cancelLevel();
	  			show('success_Msg','信息提示:',250,130); 
	  		}else{
	  				show('add_error_Msg','信息提示:',320,100);
	  		}
			ajax_001_1();
	    }
		//接收更新方法回调函数，进行相关操作
		function ajax_update_level(data){
	    	if(!data.updaterltbool){
				show('error_Msg','信息提示:',320,100);
			}else if(data.sameinfobool){
				show('success_Msg','信息提示:',250,100);
	  		}else{
	  			show('add_error_Msg','信息提示:',320,100);
	  		}
	  		ajax_001_1();
	    }
		//接收删除方法回调函数，进行相关操作
		function ajax_delete_level(data){
	    	if(data.delrltbool){
	  			show('success_Msg','信息提示:',250,100); 
	  		}else{
	  			show('error_Msg','信息提示:',300,100);
	  		}
	  		ajax_001_1();
	    }
	  </script>
	  <a:ajax parameters="null" 
	    	  successCallbackFunctions="ajax_load_level" 
	    	  pluginCode="001" 
	    	  urls="/basesetting/level/list_level_by_deleteflag"/>
	  <a:ajax parameters="{'level.name':$('#levelname').val(),'level.code':$('#levelcode').val()}" 
			  successCallbackFunctions="ajax_save_level" 
		      pluginCode="002" 
			  urls="/basesetting/level/add_level"/>
	  <a:ajax parameters="{levelid:levelid,'level.name':$('#name'+levelid).val(),'level.code':$('#code'+levelid).val()}" 
			  successCallbackFunctions="ajax_update_level" 
			  pluginCode="003" 
			  urls="/basesetting/level/update_level"/>
	  <a:ajax parameters="{levelid:levelid}" 
			  successCallbackFunctions="ajax_delete_level" 
			  pluginCode="004" 
			  urls="/basesetting/level/delete_level"/>
    <script type="text/javascript">
    	//全局变量 
    	var levelid=0;
    	$(function(){
    		ajax_001_1();
    	});
    	
    	//增加一行输入框，用于填写层次信息
    	function addLevel(){
    		$('#leveltable > tbody tr.error').remove();
    		$('#add_level').hide();
    		var list='';
    		list+=$('#leveltable > tbody').html();
			list+='<tr id="leveltr">';
		    list+='<td align="center">'; 
		    list+='<input id="levelname" class="txt_box_150" maxlength="64"/>';
		    list+='</td>'; 
		    list+='<td align="center">'; 
		    list+='<input id="levelcode" class="txt_box_150" maxlength="16"/>';
		    list+='</td>'; 
		    list+='<td align="center">';  
		    list+='<a href="#" onclick="saveLevel()">保存</a>';	 
		    list+='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="cancelLevel()">取消</a>'; 
		    list+='</td>';
		    list+='</tr>';
		    $('#leveltable > tbody').html(list);
    	}
    	//取消一行尚未保存的输入框
    	function cancelLevel(){
    		$("#leveltr").remove();
    		$('#add_level').show();
    	}
    	//保存当前一行输入的层次信息
    	function saveLevel(){
    		if($("#levelname").val()==null||$("#levelname").val()==""||$.trim($("#levelname").val())==""){
    			show('fill_in_info_error','信息提示:',250,100); 
    		}else if($("#levelcode").val()==null||$("#levelcode").val()==""||$.trim($("#levelcode").val())==""){
    			show('fill_in_info_error','信息提示:',250,100); 
    		}else{
    			ajax_002_1();
    		}
    	}
	
	//切换是否可以修改信息,保存修改信息
	function changeLevelBtnValue(id){
		var updatebtn=$('#updatelevel'+id).html();//获取保存/修改对象
		var deletebtn=$('#deletelevel'+id).html();//获取取消/删除对象
		
		if(updatebtn=="修改"){  	
			//nth(0)取id为"now"+id的第一个div的值，1为第二个div的值
			$('#name'+id).val($("div[id='now"+id+"']:nth(0)").html());
			$('#code'+id).val($("div[id='now"+id+"']:nth(1)").html());
			$('#updatelevel'+id).html("保存");
			$('#deletelevel'+id).html("取消");
			$("div[id='now"+id+"']").hide();
			$("div[id='click"+id+"']").show();
		}else{
			if($("#name"+id).val()==null||$("#name"+id).val()==""||$.trim($("#name"+id).val())==""){
    			show('fill_in_info_error','信息提示:',250,100); 
    		}else if($("#code"+id).val()==null||$("#code"+id).val()==""||$.trim($("#code"+id).val())==""){
    			show('fill_in_info_error','信息提示:',250,100); 
    		}else{
    			levelid=id;
				ajax_003_1();  	
				$('#updatelevel'+id).html("修改");
				$('#deletelevel'+id).html("删除");
				$("div[id='now"+id+"']").show();
				$("div[id='click"+id+"']").hide();
    		}
		}
	}
	
	//删除当前选定的一条层次或是取消当前选定层次的可编辑状态
	function deleteOrCancelLevel(id){
		var deletebtn=$('#deletelevel'+id).html();//获取取消/删除对象
		var updatebtn=$('#updatelevel'+id).html();//获取保存/修改对象
		
		if(deletebtn=="取消"){
			$('#updatelevel'+id).html("修改");
			$('#deletelevel'+id).html("删除");
			$("div[id='now"+id+"']").show();
			$("div[id='click"+id+"']").hide();
		}else{
			var isdel=confirm("您确定要删除吗？");
			if(isdel){
			 	levelid=id;
			 	ajax_004_1();
			}
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
				 	<th style="text-align:left; font-weight:bold;">层次设置</th>
					<th style="text-align:right; font-weight:bold;"><div id="conmenu" style="display:block;"><a id="add_level" href="javascript:void(0)" onclick="addLevel()"><img src="<ui:img url="images/title_menu/icon_add.gif" />" />增加</a></div></th>
				</tr>
			</table>
				
			<table class="gv_table_2" id="leveltable" border="0" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th >层次名称</th>
						<th >层次编号</th>
						<th >操作</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
			<!--Left End-->	
  		</body:body>
   <div id="fill_in_info_error" style="display:none;font-weight:bold" align="center"><br/>请填写完整数据！<br/></div>
   <div id="success_Msg" style="display:none;font-weight:bold" align="center"><br/>操作成功！<br/></div>
   <div id="add_error_Msg" style="display:none;font-weight:bold" align="center"><br/>数据库存在重复数据，请重新执行本次操作<br/></div>
   <div id="error_Msg" style="display:none;font-weight:bold" align="center"><br/>服务器繁忙，请您稍后尝试操作！<br/></div>
  </body>
</html>
