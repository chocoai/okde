<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>个人领用</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!--  tree控件 -->
		<jc:plugin name="tree" />
		<!-- 操作等待插件 -->

		<script type="text/javascript">
 


		function caozuo(detail,id)
		{			 
			if(detail==null)
			{					 
				var s='<s:url value="/meterial/murdetail/find_murdetail?meterid=" />';
				return '<a href="'+s+id+'">明细</a>'
			//return "<a href='/meterial/meterialapplicationdetail/find_meterialapplicationdetail'>明细</a>"
			}					
		}	
		
		function shijian(useTime)
		{
			return useTime.substring(0,10);
		}
						 
		</script>


	</head>

	<body>
		<!--头部层开始 -->
		<head:head title="物料管理 &gt;&gt; 个人领用">
			<html:a text="添加领用单" icon="add"
				href="/meterial/murdetail/add_murdetail" />
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<div class="block">
				<div class="public_table_bg_766">
					<div class="tb_table_default_2">
						<table class="add_table">
							<tr>
								<td align="left">
									领用单编号:
								</td>
								<td>
									<input type="text" id="code" maxlength="60" />
								</td>
								<td align="left">
									领用人:
								</td>
								<td>
									<input type="text" id="applicantname" maxlength="60" />
								</td>
								<td align="left">
									<input name="" type="button" class="btn_black_61" value="查询"
										onclick="searchpage();" />
								</td>
							</tr>
						</table>
						<div id="finddiv" style="display: block;">
							<page:plugin pluginCode="page" il8nName="meterialusagerecord"
								searchListActionpath="list_meterialusagerecord"
								searchCountActionpath="count_meterialusagerecord"
								columnsStr="code;applicantname;amount;useTime;detail;"
								customColumnValue="3,shijian(useTime);4,caozuo(detail,id)" 
								pageSize="3"
								ontherOperatingWidth="80px" isPageSize="3"
								params="'code':$('#code').val(),'applicantname':$('#applicantname').val()" />
						</div>
					</div>
				</div>
			</div>
		</body:body>
	</body>
</html>