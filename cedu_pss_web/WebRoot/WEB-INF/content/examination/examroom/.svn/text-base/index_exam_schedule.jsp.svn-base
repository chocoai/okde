<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>院校收费政策标准设置</title>
		
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 选项卡 -->
		<jc:plugin name="tab" />
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		<!-- 操作等待插件 -->
		<jc:plugin name="loading" />
		<a:ajax parameters="{'examBatchId':$('#examBatchId').val(),'examAreaId':$('#examAreaId').val(),'inspector':$('#inspector').val(),'startTime':$('#startTime').val(),'endTime':$('#endTime').val(),'planedCost':$('#planedCost').val()};{'branchId':$('#branchId').val(),'code':$('#code').val(),'name':$('#name').val(),'address':$('#address').val()}"  
		successCallbackFunctions="ajax_add;ajax_add1" 
		urls="/examination/examroom/add_examschedule;/examination/examroom/add_exameara" 
		pluginCode="123" 
		/>
		
		<script type="text/javascript">
			//加载事件
			jQuery(function(){
				//信息提示
				jQuery('#createtest').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'信息提示'
				});	
				jQuery('#comment_save_dialog').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'信息提示'
				});	
				
				jQuery('#message_tips').dialog({
					autoOpen:false,
					draggable:false,
					resizable:false,
					title:'信息提示',
					width:200,
					height:150
				});		
				
				
			});
	function ajax_add(){
			show('添加成功','提示',200,100);
			 closes('comment_save_dialog');
		    search0909();
			}
				function del(id)
		{
		
			jQuery.post('<s:url value="delete_examschedule"/>',{"scheduleid":id},
			        function(data)
			    	{
			    		if(data.results)
			    		{
			    			show('showDialog','删除成功!',150,100);
			    			search0909();
			    		}
			    		else {show('showDialog','删除失败!',150,100);}	
			    	},
			 "json");	
		}
			
	function ajax_add1()
	{
		show('添加成功','提示',250,150);
	    closes('createtest');
		}
	
		function addComm1()
		{
		   var branchId=$('#branchId').val();
			var code=$('#code').val();
			var name=$('#name').val();
			var address=$('#address').val();
			alert(branchId+code+name+address);
			if($.trim(address)==""){
			alert('地址不能为空！');
			return;
			}
			if($.trim(code) =="")
			{
			   alert('编号不能为空');
                return;
			}
			if($.trim(name) =="")
			{
			alert('名称不能为空！');
				return;
				}
				
			ajax_123_2();
		}
		function addcomm()
		{
			var examBatchId=$('#examBatchId').val();
			var examAreaId=$('#examAreaId').val();
			var inspector=$('#inspector').val();
			var startTime=$('#startTime').val();
			var endTime=$('#endTime').val();
			var planedCost=$('#planedCost').val();
			alert(examBatchId+","+inspector);
			if(examBatchId==0){
			alert('考试批次不能为空');
			}
			if($.trim(startTime) =="")
			{
			alert('开始时间不能为空');
             return;
			}
			if($.trim(endTime) =="")
			{
			alert('结束时间不能为空');
			return;
	         }
	         if($.trim(planedCost) =="")
			{
			   alert('计划费用不能为空');
				return;
			}
			
			ajax_123_1();
	}	
		
	function  showtime(startTime,endTime){
	var  a=startTime+"--"+endTime;
    return a.substring(0,10)+"--"+a.substring(21,31);
	}	
	
	function showinspector(data){
	if(data==1){
	return "赵芬";
	}
	 if(data==2){
	return "安雅楠";
	}
	if(data==3){
	return "魏媛媛";
	}
	}
			
		</script>
		
	</head>
  
  <body>
	<!-- 头开始 -->
		<head:head title="巡考安排">
			<a href="javascript:show('createtest','新增巡考考点',450,350);" >
	 <img src="images/wait.gif" width="16" height="16" />新增巡考考点</a>
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="images/title_left.gif" />"/></th>
				 	<th style="text-align:left; font-weight:bold;">巡考安排表</th>
					<th style="text-align: right;">
				<img src="<ui:img url="images/icon_add.gif"/>" width="16" height="16" />
					<a href="javascript:show('comment_save_dialog','新增巡考',450,350);" >新增巡考</a></th>
				</tr>
			</table>
       <table class="add_table" cellpadding="0" cellspacing="0" border="0" width="100%">
		   <tr>
		   <td class="lable_100">考试批次：</td>
		   <td><s:select list="%{exambatchlist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="code" name="examBatchId" id="examBatchIds" cssClass="txt_box_130"/></td>
		   <td class="lable_100">考点:</td>
		   <td><s:select list="%{examarealist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="examAreaId" id="examAreaIds" cssClass="txt_box_130"/></td>
		   <td><input type="button" name="" id="" value="查询" class="btn_black_61"  onclick="search0909()"/>
		   </td>
		   </tr>
		</table>  
		 <table class="gv_table_2" id="resTable" border="0" cellpadding="0" cellspacing="0">
	    	<tbody>
			 <page:plugin 
				pluginCode="0909"
				il8nName="examschedule"
				searchListActionpath="findByconditions_examschedule"
				searchCountActionpath="count_examschedule"
				columnsStr="examBatchname;examAreaname;inspector;Time;planedCost"
				customColumnValue="2,showinspector(inspector);3,showtime(startTime,endTime)"
				view="http,examination/examroom/view_examschedule,id,id,_blank"
				update="http,examination/examroom/update_examschedule,id,id,_self"
				delete="json,del,id"
				pageSize="5"
				isPage="true"
				ontherOperatingWidth="80px"	
				params="'examBatchId':$('#examBatchIds').val(),'examAreaId':$('#examAreaIds').val()"
		     />  	 
	    	</tbody>
	    </table>			
			</body:body>
	
	<div id="comment_save_dialog" style="display:none">
			<form id="myform">
			<table class="add_table">
			<tr>
            	<td class="lable_100">考试批次：</td>
                <td><s:select list="%{exambatchlist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="code" name="examBatchId" id="examBatchId" cssClass="txt_box_130"/></td>
            </tr>
			<tr>
            	<td class="lable_100">考点：</td>
                <td> <s:select list="%{examarealist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="examAreaId" id="examAreaId" cssClass="txt_box_130"/></td>
            </tr>
			<tr>
            	<td class="lable_100">巡考老师：</td>
                <td><select name="inspector" id="inspector" class="txt_box_130">
				    <option value="0">--请选择--</option>
					<option value="1">赵芬</option>
					<option value="2">安雅楠</option>
					<option value="3">魏媛媛</option>
				</select></td>
            </tr>
			<tr>
            	<td class="lable_100">巡考时间：</td>
                <td><input name="startTime" id="startTime" type="text"  class="txt_box_130" onfocus="WdatePicker({skin:'whyGreen'})"  readonly="readonly" />
				  --<input name="endTime" id="endTime" type="text" class="txt_box_150" onfocus="WdatePicker({skin:'whyGreen'})"  readonly="readonly" /></td>
            </tr>
			<tr>
            	<td class="lable_100">计划费用：</td>
                <td><input type="text" name="planedCost" id="planedCost" class="txt_box_130"/></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                	<input type="button" class="btn_black_61"  value="添加"  onclick="addcomm()" />
                	<input type="button" class="btn_black_61" value="关闭" onclick="closes('comment_save_dialog');" />
                </td>
            </tr>
        </table>
			</form>
		</div>
<!-- 添加考点-->
<div id="createtest" style="display:none;">
    	<table class="add_table">
			<tr>
            	<td class="lable_100">所在学习中心：</td>
                <td>  <select name="branchId" id="branchId" class="txt_box_130">
				           <option value="0">--全部--</option>
						   <option value="1">北京学习中心</option>
						   <option value="2">上海学习中心</option>
						   <option value="3">台州学习中心</option>
						   <option value="4">九江学习中心</option>
						   <option value="5">宁波学习中心</option>
				   </select></td>
            </tr>
			<tr>
            	<td class="lable_100">考点编号：</td>
                <td><input type="text" class="txt_box_130" name="code" id="code"/></td>
            </tr>
			<tr>
            	<td class="lable_100">考点名称：</td>
                <td><input type="text" name="name" id="name" class="txt_box_130"/></td>
            </tr>
			<tr>
            	<td class="lable_100">考点地址：</td>
                <td><input type="text" name="address" id="address" class="txt_box_130"/></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                	<input type="button" class="btn_black_61"   value="确定" onclick="addComm1()"  />
                	<input type="button" class="btn_black_61" value="关闭" onclick="closes('createtest');"  />
                </td>
            </tr>
        </table>
       
</div>
<div id="message_tips" style="display:none">
<div id="message_returns_tips" align="center">
</div>
</div>
<div id="showDialog" style="display:none">
 
 </div>
  </body>
</html>
