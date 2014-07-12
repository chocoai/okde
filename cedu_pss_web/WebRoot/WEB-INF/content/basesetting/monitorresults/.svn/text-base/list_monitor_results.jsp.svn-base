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
		<!--  分页 -->
		<jc:plugin name="page" />
		
    <title>基础设置</title>
      <script type="text/javascript">  
      //加载全部层次列表
	    function ajax_load_result(data){
	   		var list='';
			if(data.lstrltbool){
				if(data.resultlist.length>0){
					$.each(data.resultlist, function(){
						list+='<tr>';
						list+='<td align="center" width="35%">';
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
						list+='<td align="center" width="35%">';
						list+='<div id="now'+this.id+'">';
						list+=this.name;
						list+='</div>';
						list+='<div id="click'+this.id+'" style="display:none;">';
						list+='<input id="name'+this.id+'" value="" maxlength="128"/>';
						list+='</div>';
						list+='</td>';
					    list+='<td align="center" width="30%">';
					    list+='<a href="#" id="updateresult'+this.id+'" onclick="changeResultBtnValue('+this.id+')">修改</a>';
					    list+='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" id="deleteresult'+this.id+'" onclick="deleteOrCancelResult('+this.id+')">删除</a>';
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
				$('#resultable > tbody').html(list);
				$('#conmenadd_monitor_rltu').show();
			}else{
				show('error_Msg','信息提示:',400,130);
			}				
	    }
      	
      	//接收添加方法回调函数，进行相关操作
	    function ajax_save_result(data){
	    	if(!data.addrltbool){
				show('error_Msg','信息提示:',300,100);
    		}else if(data.errormsg){
	  			show('success_Msg','信息提示:',250,100); 
	  		}else{
	  			show('add_error_Msg','信息提示:',300,100);
	  		}
	  		
			ajax_007_1();
	    }
	    //接收删除方法回调函数，进行相关操作
		function ajax_delete_result(data){
	    	if(data.delrltbool){
	  			show('success_Msg','信息提示:',400,130); 
	  		}else{
	  			show('error_Msg','信息提示:',400,130);
	  		}
	  		ajax_007_1();
	    }
	    //接收更新方法回调函数，进行相关操作
		function ajax_update_result(data){
	    	if(!data.updaterltbool){
				show('error_Msg','信息提示:',300,100);
			}else if(data.sameinfobool){
				show('success_Msg','信息提示:',250,100);
	  		}else{
	  			show('add_error_Msg','信息提示:',300,100);
	  		}
	  		ajax_007_1();
	    }
      </script>
      <a:ajax parameters="null" 
	    	  successCallbackFunctions="ajax_load_result" 
	    	  pluginCode="007" 
	    	  urls="/basesetting/monitorresults/list_monitor_results_by_flag"/>
      <a:ajax parameters="{'monitorResults.name':$('#resultname').val()}"
      		  successCallbackFunctions="ajax_save_result" 
      		  pluginCode="001" 
      		  urls="/basesetting/monitorresults/add_monitor_Results"/>
      <a:ajax parameters="{resultid:resultid}"
      		  successCallbackFunctions="ajax_delete_result" 
      		  pluginCode="002" 
      		  urls="/basesetting/monitorresults/delete_monitor_results_by_config"/>
	  <a:ajax parameters="{resultid:resultid,'monitorResults.name':$('#name'+resultid).val()}" 
			  successCallbackFunctions="ajax_update_result" 
			  pluginCode="003" 
			  urls="/basesetting/monitorresults/update_monitor_results"/>      		  
	  <script type="text/javascript">
	  	//全局变量 
    	var resultid=0;
	    $(function(){
	    	ajax_007_1();
    	});
	
		//增加一行输入框，用于填写信息
    	function addMonitorResult(){
    		$('#resultable > tbody tr.error').remove();
    		$('#add_monitor_rlt').hide();
    		var list='';
    		list+= $('#resultable > tbody').html();
			list+='<tr id="resulttr">';
		    list+='<td align="center">'; 
		    list+='';
		    list+='</td>'; 
		    list+='<td align="center">'; 
		    list+='<input id="resultname" class="txt_box_150" maxlength="128" />';
		    list+='</td>'; 
		    list+='<td align="center">';  
		    list+='<a href="#" onclick="saveMonitorResult()">保存</a>';	 
		    list+='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="cancelResult()">取消</a>'; 
		    list+='</td>';
		    list+='</tr>';
		    $('#resultable > tbody').html(list);
    	}
    	
    	//保存当前一行输入的信息
    	function saveMonitorResult(){
    		if($("#resultname").val()==null||$("#resultname").val()==""||$.trim($("#resultname").val())==""){
    			show('fill_in_info_error','信息提示:',250,100); 
    		}else{
    			ajax_001_1();
    		}
    	}
    	//取消一行尚未保存的输入框
    	function cancelResult (){
    		$("#resulttr").remove();
    		$('#add_monitor_rlt').show();
    	}
    	
    	//切换是否可以修改信息,保存修改信息
		function changeResultBtnValue(id){
			var updatebtn=$('#updateresult'+id).html();//获取保存/修改对象
			var deletebtn=$('#deleteresult'+id).html();//获取取消/删除对象
	
			if(updatebtn=="修改"){  	
				//nth(0)取id为"now"+id的第一个div的值，1为第二个div的值
				$('#name'+id).val($("div[id='now"+id+"']:nth(1)").html());
				$('#code'+id).val($("div[id='now"+id+"']:nth(0)").html());
				$('#updateresult'+id).html("保存");
				$('#deleteresult'+id).html("取消");
				$("div[id='now"+id+"']").hide();
				$("div[id='click"+id+"']").show();
			}else{
    			resultid=id;
				ajax_003_1();  	
				$('#updateresult'+id).html("修改");
				$('#deleteresult'+id).html("删除");
				$("div[id='now"+id+"']:nth(1)").show();
				$("div[id='click"+id+"']").hide();
			}
		}
		//删除当前选定的一条层次或是取消当前选定层次的可编辑状态
		function deleteOrCancelResult(id){
			var deletebtn=$('#deleteresult'+id).html();//获取取消/删除对象
			var updatebtn=$('#updateresult'+id).html();//获取保存/修改对象
			
			if(deletebtn=="取消"){
				$('#updateresult'+id).html("修改");
				$('#deleteresult'+id).html("删除");
				$("div[id='now"+id+"']").show();
				$("div[id='click"+id+"']").hide();
			}else{
				var isdel=confirm("您确定要删除吗？");
				if(isdel){
				 	resultid=id;
				 	ajax_002_1();
				}
			}
		}
      </script>

  </head>
  
  <body>
  		<!--头部层开始 -->
		<head:head title="基础设置">
		</head:head>
		<body:body>
			<div>
				<s:include value="../common/menu.jsp" />
			</div>
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
				 	<th style="text-align:left; font-weight:bold;">监控结果设置</th>
					<th style="text-align:right; font-weight:bold;"><div id="conmenu"><a id="add_monitor_rlt" href="javascript:void(0)" onclick="addMonitorResult()"><img src="<ui:img url="images/title_menu/icon_add.gif" />" />增加</a></div></th>
				</tr>
			</table>
			<table class="gv_table_2" id="resultable" border="0" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th >编号</th>
						<th >监控结果名称</th>
						<th >操作</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
  		</body:body>
 	   <div id="fill_in_info_error" style="display:none;font-weight:bold" align="center"><br/>请填写完整数据！<br/></div>
	   <div id="success_Msg" style="display:none;font-weight:bold" align="center"><br/>操作成功！<br/></div>
	   <div id="add_error_Msg" style="display:none;font-weight:bold" align="center"><br/>数据库存在重复数据，请重新执行本次操作！<br/></div>
	   <div id="error_Msg" style="display:none;font-weight:bold" align="center"><br/>服务器繁忙，请您稍后尝试操作！<br/></div>
  </body>
</html>
