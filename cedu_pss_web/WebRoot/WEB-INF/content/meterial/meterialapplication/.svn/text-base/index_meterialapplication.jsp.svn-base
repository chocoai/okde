<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>中心申请</title>
    	<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- jquery-loading -->
		<jc:plugin name="loading" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		
		<script type="text/javascript">
		function replay(status) {
		if (status == STATUS_HAS_DISTRIBUTED) {
			return "<img src='../../images/title_menu/icon_up.gif'/>派发完";
			} else {
			return "派发中";
			}
		}

		function caozuo(id)
		{
			var n='<s:url value="/meterial/meterialapplicationdetail/find_meterialapplicationdetail?meterid=" />'; 
			return '<a href="'+n+id+'">明细</a>'		
		}
		
		function shijian(submitTime)
		{
			return submitTime.substring(0,10);
		}
	 
</script>


	</head>

	<body>
		<!--头部层开始 -->
		<head:head title="物料管理 &gt;&gt; 中心申请">
			<html:a text="添加申请单" icon="add"
				href="/meterial/meterialapplicationdetail/add_meterial_application_deta" />
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<div class="block">
				<div class="public_table_bg_766">
					<div class="tb_table_default_2">
						<table class="add_table">
							<tr>
								<td align="left">
									申请单编号:
									</td>
									<td>
										<input type="text" id="code"  maxlength="60" />
									</td>
									<td align="left">
										申请人:
										</td>
										<td>
											<input type="text" id="applicantname"  maxlength="60" />
										</td>
										<td align="left">
											<input name="" type="button" class="btn_black_61" value="查询"
												onclick="searchpage();" />
										</td>
									 
							</tr>
						</table>
						<div id="finddiv" style="display: block;">
							<page:plugin pluginCode="page" il8nName="meterialapplication"
								searchListActionpath="list_meterialapplication"
								searchCountActionpath="count_meterialapplication"
								columnsStr="code;applicantname;amount;submitTime;status;detail;"
								customColumnValue="3,shijian(submitTime);4,replay(status);5,caozuo(id)"
								pageSize="3" ontherOperatingWidth="80px" isPageSize="3"
								params="'code':$('#code').val(),'applicantname':$('#applicantname').val()" 							
								/>
					</div>
				</div>
			</div>
		</div>
		</body:body>




	</body>
</html>