<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>毕业证书</title>
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
		<!-- 选项卡 -->
		<jc:plugin name="tab" />
		
		<script type="text/javascript">
		
		
		
		</script>
  </head>
  
  <body>
		<!--头部层开始 -->
		<head:head title="毕业证书">
	
		</head:head>
		<%@ include file="_tab_title/diploma_student_tab.jsp" %>
		<!--主体层开始 -->
		<body:body>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td class="lable_100">毕业证书：</td>
					<td>
					<c:if test="${diploma.diplomaUrl!=null&&diploma.diplomaUrl!=''}">
										<img src="<ui:img url="${diploma.diplomaUrl}" />" height="300px" border="0" />
					</c:if>
					<c:if test="${diploma.diplomaUrl==null||diploma.diplomaUrl==''}">
											<span style="color:black;">无</span>
					</c:if>
					</td>
				</tr>
			</table>
			<input class="btn_black_61" type="button"  onclick="window.location.href='list_diploma';" value="返回" />
  		</body:body>
  </body>
</html>
