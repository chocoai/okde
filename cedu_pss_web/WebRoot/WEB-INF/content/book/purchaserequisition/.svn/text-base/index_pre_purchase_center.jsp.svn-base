<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>中心预申购</title>			
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
		<script type="text/javascript">
		
		//学院，批次，层次，专业的级联
		function clearBatch()
		{
			$('#batchId').empty().append('<option value="0" selected="selected">---请选择院校---</option>');
			clearLevel();
			 
		}
		
		function batchCallback(data){
			$('#batchId').empty();
			$(data.batches).each(function(index){
				var batchOpt = $('<option value="'+this.id+'">'+(this.enrollmentName ? this.enrollmentName : '')+'</option>');
				batchOpt.appendTo('#batchId');
			});
			if($('#batchId option').size()>0){
				$('#batchId').prepend('<option value="0" selected="selected">---请选择---</option>');
			} else {
				$('#batchId').append('<option value="0" selected="selected">---无数据---</option>');
			}
		}
		BATCH_PARAM = null;
		function listBatch(academyId)
		{
			clearBatch();		
			if(academyId <= 0) return;			
			$('#batchId').html('<option value="0" selected="selected">====请稍等====</option>');
			BATCH_PARAM = { academyId: academyId };
			ajax_batch_1();
		}
			 
		function clearLevel()
		{
			$('#levelId').html('<option value="0" selected="selected">===请选择批次===</option>');
			$('#majorId').html('<option value="0" selected="selected">==请选择层次==</option>');
		}
		
		function levelCallback(data){
			$('#levelId').empty();
			
			if(!data || !data.list || data.list.length<=0){
				$('#levelId').append('<option value="0" selected="selected">====无数据====</option>');
				return;
			}
			
			$('#levelId').append('<option value="0" selected="selected">====请选择====</option>');
			
			$(data.list).each(function(index){
				$('<option  value="'+this.level.id+'" al="'+this.id+'">'+(this.level ? this.level.name : '')+'</option>').appendTo('#levelId');
			});
		}
	
		LEVEL_PARAM = null;
		function listLevel(batchId)
		{
			clearLevel();
			
			if(batchId==0) return;
			LEVEL_PARAM = {batchId: batchId};
			
			$('#levelId').html('<option value="0" selected="selected">====请稍等====</option>');
			ajax_level_1();
		}	
		function majorCallback(data){
			$('#majorId').empty();
			
			if(!data || !data.list || data.list.length<=0)
			{
				$('#majorId').append('<option value="0" selected="selected">====无数据====</option>');
				return;
			}
			
			$('#majorId').append('<option value="0" selected="selected">====请选择====</option>');
			
			$(data.list).each(function(index){
				$('#majorId').append('<option value="'+this.id+'">'+this.name+'</option>');
			});
		}
		
		MAJOR_PARAM = null;
		function listMajor(levelId)
		{
			if(!levelId)
			{
				$('#majorId').html('<option value="0" selected="selected">===请选择层次=====</option>');
				return;
			}
			MAJOR_PARAM = {academyLevelId: levelId};
			$('#majorId').html('<option value="0" selected="selected">===请稍等=====</option>');
			ajax_major_1();
		}
		
		DEL_PARAM = null;
		jQuery(function(){
			$('#batchId').change(function(){
				var batch = this.value;
				 
				listLevel(batch);
			});
			
			$('#levelId').change(function(){
				var levelId = this.options[this.selectedIndex].getAttribute('al');
				listMajor(levelId);
			});
			
			$('#academyId').change(function(){
				listBatch(this.value);
			});
		});
		//报名阶段全选/反选
 		function SelectAll() {
 			var checkboxs=document.getElementsByName("status");
 			for (var i=0;i<checkboxs.length;i++) {
 			 var e=checkboxs[i];
 			 e.checked=!e.checked;
 			}
 			batch();
		}
		 	
		//被选中的checkbox
		var　array="";
		function batch()
		{
		     var rusult=[];
		    
           var check_array=document.getElementsByName("status");
           for(var i=0;i<check_array.length;i++)
           {
               if(check_array[i].checked==true)
               {         
                  rusult.push(check_array[i].value);
               }
           }
           array=rusult.join(",");    
           $("#status").val(array);     
		}
			
		//删除行
		function del(id)
		{
			$('#tr'+id).remove();			
		}
 		
 		//测试方法
		function asd() {
		var a=[]; 
		var index=$("intpu[name=student]").text();
			//代改进
			for(var i=1;i<10;i++)
			{
			   	 a.push($("#studentids"+i+"").attr("value"));
			}
		alert(a);
		alert(index);				
		}
 		 	 
 		//增加Action
	 	function addPurchase(){
		 	var academyId=$('#academyId').val() ;
			var batchId=$('#batchId').val();
			var levelId=$('#levelId').val() ;
			var majorId=$('#majorId').val() ;
			var stuids=[]; 
			var studentids="";
			for(var i=1;i<10+1;i++)
			{
			   	 stuids.push($("#studentids"+i+"").attr("value"));
			}
			studentids=stuids.join(",");
			//通过表单实现js post提交
			document.write("<form action='../purchaserequisition/pre_purchase_center_book?academyId="+academyId+"&batchId="+batchId+"&levelId="+levelId+"&majorId="+majorId+"&stuids="+studentids+"' method='post' name='addForm' style='display:none'>");
			document.write("</form>");
			document.addForm.submit();
		}	 				 		 		
	</script>	
	<a:ajax successCallbackFunctions="levelCallback" parameters="LEVEL_PARAM" pluginCode="level" urls="/enrollment/academylevel/list_academy_level_for_batch"/>
	<a:ajax successCallbackFunctions="majorCallback" parameters="MAJOR_PARAM" pluginCode="major" urls="/enrollment/academymajor/list_academy_major_for_academy_level"/>
	<a:ajax successCallbackFunctions="batchCallback" parameters="BATCH_PARAM" pluginCode="batch" urls="/enrollment/list_enroll_batch_for_academy"/>
	</head>
  <body>
  <div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a class="icon">中心预申购</a> </li>
		</ul>
	</div>
	
</div>
<div class="block">
	<div class="public_table_bg_766">
		<div class="tb_table_default_2"> 	    
			<div style="height:5px;"></div>
			<body:body>
		<form name="index_pre_purchase_center" method="post" >
			<table class="add_table">
              <tr>
              	<td class="lable_80">院校：</td>
              	<td><s:select cssClass="txt_box_150" list="academies" listKey="id" listValue="name" id="academyId" name="student.academyId" headerKey="0" headerValue="===请选择===="></s:select></td>
					<td class="lable_80">招生批次：</td>
                <td><select class="txt_box_150" id="batchId" name="student.batchId"><option value="0">===请选择===</option></select></td>              
					<td class="lable_80">层次：</td>
                <td align="left">
					<select class="txt_box_150" name="student.levelId" id="levelId"><option value="0">===请选择===</option></select>
					</td>
                <td class="lable_80">专业：</td>
                <td align="left">
					<select name="student.majorId" id="majorId" class="txt_box_150"><option value="0">===请选择===</option></select>
					</td>
			</tr>		 
			<tr>
			<td colspan="3"  align="left"><a href="javascript:void(0)" onclick="SelectAll();">全选/反选</a></td>
			</tr>
			<tr>
				<td align="right">报名阶段:</td>
				<td colspan="3"  align="left">
				<input type="checkbox" checked="checked" name="status" value="<%=Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI %>" />已报名
				<input type="checkbox" checked="checked" name="status"  value="<%=Constants.STU_CALL_STATUS_YI_CE_SHI %>"  />已测试
				<input type="checkbox" checked="checked" name="status"  value="<%=Constants.STU_CALL_STATUS_YI_LU_QU %>" />已录取
				</td>					
				</tr>
				<tr>
				<td align="right">在籍在读阶段:</td>
				<td > 
				<input type="checkbox" checked="checked" name="status" value="<%=Constants.STU_CALL_STATUS_QU_XIAO_XUE_JI %>" />已取消学籍
				<input type="checkbox" checked="checked" name="status" value="<%=Constants.STU_CALL_STATUS_KE_CHENG_JING_XIU %>" />课程进修
				<input type="checkbox" checked="checked" name="status"  value="<%=Constants.STU_CALL_STATUS_YI_FU_XUE %>"/>已复学
				<input type="hidden" name="student.prePurchaseStatus" id="status"/>
				</td>
						<td align="right"></td>
						<td align="left"><input name="" type="submit" class="btn_black_61" value="查询" /></td>
				</tr>								  
            </table>
		</form>		
		</body:body>				 		
			<table class="gv_table_2">			
					<tr>
					<th>编号</th>
					<th>姓名</th>
		    		<th>学号</th>
					<th>批次</th>
					<th>层次</th>
					<th>专业</th>
					<th>学生状态</th>
					<th>学费余额</th>
					<th>教材费余额</th>
					<th>操作</th>	
					</tr>
				 <s:iterator var="stu" value="stulist" status="x">
					<tr id="tr<s:property value="#stu.id"/>">
					<td align="center"><s:property value="#x.index"/></td>
						<td align="center"><s:property value="#stu.name"/></td>						
						<td align="center"><s:property value="#stu.number"/></td>
						<td align="center"><s:property value="#stu.batchId"/></td>
						<td align="center"><s:property value="#stu.levelId"/></td>
						<td align="center"><s:property value="#stu.majorId"/></td>
						<td align="center"><s:property value="#stu.status"/></td>
						<td align="center"><s:property value="#stu.status"/></td>
						<td align="center"><s:property value="#stu.status"/>
						 <input type="hidden" id="studentids${stu.id}" name="student" value="${ stu.id}" /> 
						 </td>						
						<td align="center"><a href="#" onclick="del(<s:property value="#stu.id"/>)">取消</a></td>				  
					</tr>
 				</s:iterator> 				 
			</table>			
			<table class="add_table">			
			<tr>
				<td align="center"><input name="" onclick="addPurchase()" type="button" class="btn_black_61" value="下一步" /> </td>
			</tr>			
			</table>   	 
    </div>     
   </div>
  </div> 		
	<div id="msgDiv" style="display:none">
		操作成功！！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br /><br />
	</div>		
</body>
</html>