<%@ page language="java" pageEncoding="UTF-8" import="net.cedu.common.*" %>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<title>院校返款标准设置</title>
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
				if(std.valueForm==MONEY_FORM_RATIO)
					form = '%';
				else if(std.valueForm == MONEY_FORM_AMOUNT)
					form = '元';
				else if(std.valueForm == MONEY_FORM_SCORE)
					form = '分';
				
				stdInfo.push(std.floor + '&nbsp;人&nbsp;&nbsp;&mdash;&nbsp;&nbsp;' + std.ceil+ '&nbsp;人&nbsp;&nbsp;&nbsp;'+ std.value+'&nbsp;&nbsp;&nbsp;'+form);
			}
			
			return stdInfo.join('<br/>');
		}
		function showname(feeSubjectName,indexcount,id)
		{
			if(indexcount>0)
			{
				isPageOperating(id,"001","update");
			}
			return feeSubjectName;
		}
		
		jQuery(function(){
			$('#btnDoSearch').click(function(){
				search001();
			});
		});
	</script>
  </head>
  
  <body>
		<!--头部层开始 -->
		<head:head title="院校返款标准设置">
			<html:a text="添加返款标准" icon="add" href="/enrollment/academyrebatestd/add_acdm_rbt_std" target="_blank"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
		<table class="gv_table_2">
			<tr>
				<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
				<th style="text-align:left; font-weight:bold;">院校返款标准</th>
		    </tr>
		  </table>
		<form id="searchForm">
		  	<table class="add_table">
              <tr>
			  	<td align="right">院校：</td>
                <td align="left">
					<s:select list="academies" listKey="id" listValue="name"
						headerKey="%{@net.cedu.entity.enrollment.AcademyRebatePolicy@ACADEMY_ID_ALL}" headerValue="-----请选择-----"
						name="academyId" id="academyId" cssClass="txt_box_130">
					</s:select>
				</td>
					<td align="right">费用项目：</td>
                <td align="left">
					<s:select list="feeSubjects" listKey="id" listValue="name"
						headerKey="0" headerValue="----请选择----"
						name="feeSubjectId" id="feeSubjectId" cssClass="txt_box_130">
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
			searchListActionpath="/enrollment/academyrebatestd/list_data_academy_rebate_policy"
			searchCountActionpath="/enrollment/academyrebatestd/list_count_academy_rebate_policy"
			columnsStr="academyName;title;feeSubjectName;#standardlist"
			customColumnValue="2,showname(feeSubjectName,indexcount,id);3,parseStd(standards)"
			pageSize="30"
			isPage="true"
			update="http,/enrollment/academyrebatestd/edit_academy_rbt_std,policyId,id,_blank"
			view="http,/enrollment/academyrebatestd/view_academy_rbt_std,policyId,id,_blank"
			searchFormId="searchForm"
			isonLoad="true"
			titleBar="title"
			isPackage="false"
			isOrder="false"
			isNumber="true"
		/>

		</body:body>
</body>
</html>
