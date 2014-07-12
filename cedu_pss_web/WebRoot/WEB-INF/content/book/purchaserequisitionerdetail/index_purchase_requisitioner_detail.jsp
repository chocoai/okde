<%@ page language="java" pageEncoding="UTF-8" import="net.cedu.common.*" %>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>学生领用</title>
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
	<!-- 基础JS -->
	<jc:plugin name="base_js" />

	<jc:plugin name="loading" />
	
	<script type="text/javascript">
	function clearBatch()
	{
		$('#batchId').empty().append('<option value="0" selected="selected">---请选择---</option>');
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
	
	 
	function branchCallback(data){
		$('#branchId').empty();
		
		if(!data || !data.grantedList || data.grantedList.length<=0){
			$('#branchId').append('<option value="0" selected="selected">====无数据====</option>');
			return;
		}
		
		$('#branchId').append('<option value="0" selected="selected">====请选择====</option>');
		$(data.grantedList).each(function(index){
			$('#branchId').append('<option value="'+this.id+'">'+this.name+'</option>');
		});
	}
	BRANCH_PARAM = null;
	function listGrantedBranch(batchId)
	{
		if(batchId < 1) return;
		
		$('#branchId').html('<option value="0" selected="selected">===请稍等===</option>');
		BRANCH_PARAM = {batchId: batchId};
		ajax_branch_1();
	}
	
	function clearLevel()
	{
		$('#levelId').html('<option value="0" selected="selected">===请选择===</option>');
		$('#majorId').html('<option value="0" selected="selected">==请选择==</option>');
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
			$('#majorId').html('<option value="0" selected="selected">===请选择=====</option>');
			return;
		}
		MAJOR_PARAM = {academyLevelId: levelId};
		$('#majorId').html('<option value="0" selected="selected">===请稍等=====</option>');
		ajax_major_1();
	}
	
	DEL_PARAM = null;
	jQuery(function(){
		util.select.initOption('#auditStatus', get_policy_audit_status());
		$('#batchId').change(function(){
			var batch = this.value;
			listGrantedBranch(batch);
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
	
	
	function zhuangtai(status)
	{
		if(status==0)
		{
		return "未领用";
		}else
		{
		return "已领用";
		}
	}
	
	function shuaxin(bool)
	{	
		if(bool==1)
		 {
			var x= $("#status").val("1");
			$('#second').addClass('current');
			$('#first').removeClass('current');		 
			searchpage();
		 }else if(bool==0)
		 {
			 var y= $("#status").val("0");
			 $('#first').addClass('current');
			 $('#second').removeClass('current');	 
			 searchpage();
		 }
	}
	 
	
	
	
	
	function caozuo(id,status)
	{		
			if(status==0)
			{
			var n='<s:url value="/book/purchaserequisitionerdetail/update_purchase_requisitioner_detail?purchaseid=" />'; 
		 	return '<a href="'+n+id+'"><input type="button" class="btn_black_61" value="领用"/></a>'	
		 	 searchpage();
		 	 }else
		 	 return "";
	}
	</script>
	
	<a:ajax successCallbackFunctions="levelCallback" parameters="LEVEL_PARAM" pluginCode="level" urls="/enrollment/academylevel/list_academy_level_for_batch"/>
	<a:ajax successCallbackFunctions="majorCallback" parameters="MAJOR_PARAM" pluginCode="major" urls="/enrollment/academymajor/list_academy_major_for_academy_level"/>
	<a:ajax successCallbackFunctions="branchCallback" parameters="BRANCH_PARAM" pluginCode="branch" urls="/enrollment/list_academy_branch"/>
	<a:ajax successCallbackFunctions="batchCallback" parameters="BATCH_PARAM" pluginCode="batch" urls="/enrollment/list_enroll_batch_for_academy"/>
	
	<a:ajax successCallbackFunctions="deleteCallback" parameters="DEL_PARAM" pluginCode="delete" urls="/enrollment/academyrebatepolicy/delete_acdm_rbt_plc_dtl"/>
</head>
<body>
		<div id="title_new">

	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a>学生领用</a> </li>
		</ul>
	</div>
	
</div>
<div class="block">
	<div class="public_table_bg_766">

		<div class="tb_table_default_2">
			<!--Menu Begin-->
			<div>
				<ul id="menu">
				<li><a href="javascript:void(0)" id="first" title="" class="current" onclick="shuaxin(0);">待领用</a></li>
				<li><a href="javascript:void(0)" id="second" title=""   onclick="shuaxin(1);">已领用</a></li>
				
				</ul>
			</div>
			<div style="height:5px;"></div>
			<body:body>
		<form id="purchase">
			<table class="add_table">
              <tr>
              	<td class="lable_80">院校：</td>
              	<td><s:select cssClass="txt_box_150" list="academies" listKey="id" listValue="name" id="academyId" name="academyId" headerKey="0" headerValue="===请选择===="></s:select></td>
					<td class="lable_80">招生批次：</td>
                <td><select class="txt_box_150" id="batchId" name="batchId"><option value="0">===请选择===</option></select></td>
                
					<td class="lable_80">层次：</td>
                <td align="left">
					<select class="txt_box_150" name="levelId" id="levelId"><option value="0">===请选择===</option></select>
					</td>
                <td class="lable_80">专业：</td>
                <td align="left">
					<select name="majorId" id="majorId" class="txt_box_150"><option value="0">===请选择===</option></select>
					</td>
			</tr>
			 <tr> 
			 <td align="right">学生姓名：</td>
						<td align="left"><input name="studentname" id="studentname" type="text" class="txt_box_100" /></td>	
						<td align="right">教材名称：</td>	
						<td align="left"><input name="bookname" id="bookname" type="text" class="txt_box_100" /></td>	
						<td align="right">版次：</td>	
						<td align="left"><input name="bookedition" id="bookedition" type="text" class="txt_box_100" /></td>	
						<td>&nbsp;<input type="hidden" id="status" name="status" value="1"/></td>
						
                <td align="left"><input name="button" id="dosearch" type="button" class="btn_black_61" onclick="searchpage()" value="查询"/></td>
			</tr>
			
			  
            </table>
		</form>
       <table class="gv_table_2">
					 
							<page:plugin pluginCode="page" il8nName="bookpurchaserequisitionerdetail"
								searchListActionpath="page_list_purchaserequisitionerdetail"
								searchCountActionpath="page_count_purchaserequisitionerdetail"
								columnsStr="studentname;academyname;levelname;majorname;batchname;bookname;bookedition;requiredAmount;bookprice;avg;status;handle"
								customColumnValue="10,zhuangtai(status);11,caozuo(id,status)"
								pageSize="3" ontherOperatingWidth="80px" isPageSize="3"
								params="'fromId':$('#fromId').val(),'fromname':$('#fromname').val()" 
								searchFormId="purchase"
								/>
			</table>
			
			<div id="msgDiv" style="display:none">
		已扣费！！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br /><br />

	</div>

		</body:body>
		</div>
		</div>
		 </div>
</body>
</html>
