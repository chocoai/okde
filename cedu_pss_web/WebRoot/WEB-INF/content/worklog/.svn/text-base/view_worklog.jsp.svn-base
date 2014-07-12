<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>个人日报详情</title>
    	
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
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		<jc:plugin name="ckeditor"/>		
		
		<script type="text/javascript">

			function viewClose() {
				 window.opener = null;
				 window.open('','_self');
				 window.close();
			}
		</script>
	
  </head>
  
  <body>
     <form id="form1" name="form1" action="add_worklog" method="post">		
		<!--头部层开始 -->
		<head:head title="worklog" il8nName="admin">
			<html:a text="关闭" onclick="viewClose();" icon="return" target="_self"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>		
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					<th style="text-align:left; font-weight:bold;">个人日报</th>
				</tr>
			</table>
			
			<table class="add_table">  
				<tr>
					<td class="lable_100">姓名：</td>
					<td>
						${worklog.createUser.fullName }
					</td>
				</tr>
				<tr>
					<td class="lable_100"><span>*</span>日报标题：</td>
					<td>
						${worklog.title } 
					</td>
				</tr>
				<tr>
					<td class="lable_100">日报内容：</td>
					<td>
						<fieldset style="background-color: white;">
			         		<div >${worklog.content }</div>
			         	</fieldset>
			         	
					</td>
				</tr>
				<tr>
					<td class="lable_100">日报表：</td>
					<td>
						<fieldset>
							<iframe width="100%" height="110px"  frameborder="0" id="home_sch" name="home_sch" marginheight="0" marginwidth="0" src="<%=Constants.WEB_PATH %>/report/individual_daily_interface?userId=${worklog.createBy}&date=<fmt:formatDate value="${worklog.createOn}"  pattern="yyyy-MM-dd" />" target="_self" allowtransparency="true"></iframe>
						</fieldset>
					</td>
				</tr>
			</table>
		</body:body>
	</form>
  </body>
</html>
