<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>学生详情</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />

		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		<jc:plugin name="loading" />
		<!-- 表单验证 -->
		<jc:plugin name="validator" />
		<!-- 分页 -->
		<jc:plugin name="page" />
		<script type="text/javascript">
		$(document).ready(function(){
			
			
		
		})
		</script>
		
	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="学生详情">
		</head:head>
		<!--主体层开始 -->
		<body:body>
							
								<table class="gv_table_2">
										<tr>
											<th style="width:20px;"><img src="<%=Constants.WEB_IMAGES %>/plugin/base_css/images/operating/title_left.gif" /></th>
											<th style="text-align:left; font-weight:bold;">基本资料</th>					
										</tr>
									</table>

								<table class="add_table"   border="0">
									<tr>

										<td align="right" width="100px;">
											标题：
										</td>
										<td align="left" valign="middle">
											<c:if test="${teachingNotice.title!=null&&teachingNotice.title!=''}">
											<span style="color:black;">${teachingNotice.title}</span>
											</c:if>
											<c:if test="${teachingNotice.title==null||teachingNotice.title==''}">
											<span style="color:black;">无</span>
											</c:if>
										</td>
										
									</tr>
									<tr>
										<td align="right" width="100px;">
											发布者：
										</td>
										<td align="left">
											<c:if test="${teachingNotice.publisher!=null&&teachingNotice.publisher!=''}">
											<span style="color:black;">${teachingNotice.publisher}</span>
											</c:if>
											<c:if test="${teachingNotice.publisher==null||teachingNotice.publisher==''}">
											<span style="color:black;">无</span>
											</c:if>
										</td>
									</tr>
									<tr>
									<td align="right" width="100px;">
											时间：
										</td>
										<td align="left">
											<c:if test="${teachingNotice.publishTime!=null&&teachingNotice.publishTime!=''}">
											<span style="color:black;">${teachingNotice.publishTime}</span>
											</c:if>
											<c:if test="${teachingNotice.publishTime==null||teachingNotice.publishTime==''}">
											<span style="color:black;">无</span>
											</c:if>
										</td>
									</tr>
									<tr>
										<!--<td align="right" width="100px;">
											内容：
										</td>
										  <td align="left">
											<textarea rows="5" cols="40"   style="color:black; border: none"  disabled="disabled" >${teachingNotice.content}</textarea>
										</td>
										-->
										<td class="lable_100">内容：</td>
					                    <td>${teachingNotice.content}</td>
									</tr>
								
								</table>
								<br/>
								<input class="btn_black_61" type="button"
													onclick="window.location.href='teaching_notice';"
													value="返回" />
							 
		</body:body>
	</body>

</html>