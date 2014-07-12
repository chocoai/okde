<%@ page language="java" pageEncoding="UTF-8" import="net.cedu.common.*" %>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<title>招生返款标准设置</title>
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
	
	<script type="text/javascript">
	function parseStd(standards){
		var stdInfo = [];
		var len = standards ? standards.length : 0;
		for(var i=0; i<len; i++){
			var std = standards[i];
			
			var form = '';
			if(std.rebatesId==MONEY_FORM_RATIO)
				form = '%';
			else if(std.rebatesId == MONEY_FORM_AMOUNT)
				form = '元';
			else if(std.rebatesId == MONEY_FORM_SCORE)
				form = '分';
			
			stdInfo.push(std.enrollmentFloor + '&nbsp;人&nbsp;&nbsp;&mdash;&nbsp;&nbsp;' + std.enrollmentCeil+ '&nbsp;人&nbsp;&nbsp;&nbsp;'+ std.value+'&nbsp;&nbsp;&nbsp;'+form);
		}
		
		return stdInfo.join('<br/>');
	}
	
		function getbranchName(branchName,indexcount,id)
		{
			if(indexcount>0)
			{
				isPageOperating(id,"001","update");
			}
			if(branchName!=null && branchName.length>15)
			{
				return '<span title='+branchName+'>'+branchName.substring(0,12)+"..."+'</span>';
			}else
			{
				return '<span title='+branchName+'>'+branchName+'</span>';
			}
		}
	
	jQuery(function(){
		//gridView.render(1);
		$('#btnDoSearch').click(function(){
			//gridView.render(1);
			search001();
		});
	});
	</script>
  </head>
  
  <body>
		<!-- 头开始 -->
		<head:head title="招生返款标准设置">
			<html:a text="添加招生返款标准" icon="add" href="/enrollment/chnlplcystd/add_chnl_plcy_std" target="_blank" />
		</head:head>
		<!--主体层开始 -->
		<body:body>
		   <table class="gv_table_2">
		  	<tr>
				<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
				<th style="text-align:left; font-weight:bold;">招生返款标准</th>
		    </tr>
		  </table>
		<form id="searchparam">
		  	<table class="add_table">
              <tr>
              	<td align="right">学习中心：</td>
				<td align="left">
					<s:select list="branchList" listKey="id" listValue="name"
						headerKey="0" headerValue="-----请选择-----"
						name="branchId" id="branchId" cssClass="txt_box_150">
					</s:select>
				</td>
				<td align="right">院校：</td>
				<td align="left">
					<s:select list="academies" listKey="id" listValue="name"
						headerKey="-1" headerValue="-----请选择-----"
						name="academyId" id="academyId" cssClass="txt_box_150">
					</s:select>
				</td>
				<td align="right">费用项目：</td>
				<td align="left">
					<s:select list="feeSubjects" listKey="id" listValue="name"
						headerKey="-1" headerValue="----请选择----"
						name="feeSubjectId" id="feeSubjectId" cssClass="txt_box_150">
					</s:select>
				</td>
				<td class="lable_100">合作方:</td>					
				<td >
		  			<s:select list="channelTypes" listKey="id" listValue="name"
						headerKey="-1" headerValue="所有合作方"
						name="channelTypeId" id="channelTypeId" cssClass="txt_box_150">
					</s:select>
				</td>
				<td width="5%"></td>
             <td align="left"></td>
			  	<td colspan="" align="left">
					<input type="button" id="btnDoSearch" class="btn_black_61" value="查询"/>
				</td>
              </tr>
			 </table>
		</form>
		 
			<page:plugin 
				pluginCode="001"
				il8nName="enrollment"
				subStringLength="23"
				searchListActionpath="/enrollment/chnlplcystd/list_chnl_plcy_std"
				searchCountActionpath="/enrollment/chnlplcystd/count_chnl_plcy_std"
				columnsStr="branchName;academyName;title;feeSubjectName;channelTypeName;#rebatestd"
				customColumnValue="0,getbranchName(branchName,indexcount,id);5,parseStd(standards)"
				pageSize="10"
				isPage="true"
				update="http,/enrollment/chnlplcystd/edit_chnl_plcy_std,id,id,_blank"
				view="http,/enrollment/chnlplcystd/view_chnl_plcy_std,id,id,_blank"
				searchFormId="searchparam"
				isonLoad="true"
				isPackage="false"
				isOrder="true"
				isPageSize="true"
			/>

		</body:body>
</body>
</html>
