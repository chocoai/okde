<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
		<title>用户详情</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
	
  </head>
  
  <body>
    <!--头部层开始 -->
		<head:head title="用户详情">
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<!--Search Begin-->
				<div>
					<table width="100%" class="add_table" border="0" cellpadding="2" cellspacing="0">
						<tr>
							<td class=""></td>
							<td align="left" rowspan="6">
								<s:if test="null==users.photoUrl">
									<img class="user_view_iamge" src="<ui:img url="${users.photoUrl}"/>"/>
								</s:if>
								<s:else>
									<img class="user_view_iamge" src="<ua:attachment url="${users.photoUrl}"/>"/>
								</s:else>
							</td>
						</tr>
						<tr>

							<td class="lable_100">用户名:</td>
							<td>${users.userName }</td>
						</tr>
						<tr>
							<td class="lable_100">姓名:</td>
							<td>${users.fullName }</td>
						</tr>
						<tr>

							<td class="lable_100">座机:</td>
							<td>${users.telephone }</td>
						</tr>
						<tr>
							<td class="lable_100">手机:</td>
							<td>${users.mobile }</td>
						</tr>
						<tr>

							<td class="lable_100">邮箱:</td>
							<td>${users.email }</td>
						</tr>
						<tr>
							<td class="lable_100">启用状态:</td>
							<td>
							<s:if test="0==users.status">
								启用
							</s:if>
							<s:if test="1==users.status">
								停用
							</s:if>
							</td>
						</tr>
						
						<tr>
							<td>&nbsp;</td>
							<td><input type="submit" value="关闭" onclick="javascript:self.close();" class="btn_black_61" /></td>
						</tr>
					</table>
				</div>
				<!--Search End-->
			</body:body>
  </body>
</html>
