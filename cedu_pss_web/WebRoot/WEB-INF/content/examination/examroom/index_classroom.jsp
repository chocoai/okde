<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
  <title>教室信息</title>
  <!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 分页 -->
		<jc:plugin name="page" />
		<jc:plugin name="loading" />
		<!-- 选项卡 -->
		<jc:plugin name="tab" />
		<!-- 表单验证 -->
		<jc:plugin name="validator" />
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />	
		<a:ajax parameters="{'fee':$('#feeStandard').val(),'capacity':$('#capacity').val(),'name':$('#name').val(),'examroomId':$('#examroomId').val(),'feetype':$('.feeType:checked').val()}"  
		successCallbackFunctions="ajax_add" 
		urls="/examination/examroom/add_classroom" 
		pluginCode="123" 
		/>
		<script type="text/javascript">
		function del(id)
		{
			jQuery.post('<s:url value="delete_classroom"/>',{"id":id},
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
		
		function ajax_add(){
		 show('添加成功','提示',200,100);
	     closes('createclassroom');
	     search0909();
		}

			//加载事件
			jQuery(function(){
				//信息提示
				jQuery('#createclassroom').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'信息提示'
				});		
			});
			
		function addclass()
		{
		   var examroomId=$('#examroomId').val();
			var name=$('#name').val();
			var count=$('#capacity').val();
			var fee=$('#feeStandard').val();
			var feetype=$(".feeType:checked").val();
			alert(examroomId);
			if($.trim(name)==""){
			alert('名称不能为空！');
			return;
			}
			if($.trim(count) =="")
			{
			   alert('数量不能为空！');
				//jQuery("#message_
			}
			if($.trim(fee)==""){
			alert('费用标准不能为空！');
			}
			if($.trim(feetype)==""){
			alert('收费类型不能为空');
			}
			ajax_123_1();
		}
		
		
		
		function showFee(feeStandard,feeType){
		if(feeType==0){
		return feeStandard+"元/场";
		}
		if(feeType==1){
		return feeStandard+"元/天";
		}
		
		
		}
		</script>
  </head>
  
  <body>
  <!--头部层开始 -->
  <head:head title="考场名称:${examroom.name }">
  </head:head>
		<%@include file="_cedu_confirm_tab.jsp" %>
		<!--主体层开始 -->
		<body:body>
		<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="images/title_left.gif"/>" /></th>
					<th style="text-align: right;">
						<img src="<ui:img url="images/icon_add.gif"/>" width="16" height="16" />
					<a href="javascript:show('createclassroom','添加教室信息',450,350);" >添加教室信息</a></th>
				</tr>
			</table>
		  <table class="gv_table_2" id="resTable" border="0" cellpadding="0" cellspacing="0">
	    	<tbody>
			 <page:plugin 
				pluginCode="0909"
				il8nName="examschedule"
				searchListActionpath="find_classroom"
				searchCountActionpath="count_classroom"
				columnsStr="name;capacity;fee"
				customColumnValue="2,showFee(feeStandard,feeType)"
				update="http,examination/examroom/update_classroom,id,id,_self"
				delete="json,del,id"
				pageSize="5"
				ontherOperatingWidth="80px"	
		     />  	 
	    	</tbody>
	    </table>
		
		</body:body>
		<div id="createclassroom" style="display:none;">
    	<table class="add_table">
            <tr>
            	<th class="lable_100">所在的考场：</th>
                <td> <s:select list="%{examroomlist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="examroomId" id="examroomId" cssClass="txt_box_130"/>
				</td>
            </tr>
			<tr>
			    <td class="lable_100">教室名称：</td>

				<td><input type="text" name="name" id="name" class="txt_box_150"/></td>
			</tr>
			<tr>
			    <td class="lable_100">教室容量：</td>
				<td><input type="text" name="capacity" id="capacity" class="txt_box_150" value=""/>&nbsp;&nbsp;【按教室的间隔计算】</td>
			</tr>
			<tr>
			    <td class="lable_100">费用状况：</td>

				<td><input id="feeStandard" name="feeStandard" class="txt_box_150"/>&nbsp;元 &nbsp;/ &nbsp;
					<input type="radio" name="feeType" class="feeType" checked="checked" value="0"/>场 &nbsp;&nbsp;
					<input type="radio" name="feeType" class="feeType" value="1"/> 天
				</td>
			</tr>
            <tr>
                <td colspan="2" align="center">
                	<input type="button" class="btn_black_61" name="save" onclick="addclass();" value="添加"  />
                	<input type="button" class="btn_black_61" value="关闭" onclick="closes('createclassroom');" />
                </td>
            </tr>
        </table>
</div>
<div id="showDialog" style="display:none">

</div>
  </body>
</html>
