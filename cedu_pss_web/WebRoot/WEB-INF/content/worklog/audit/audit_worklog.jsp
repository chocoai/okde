<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>日报评分</title>
    	
    	<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- jquery-loading -->
		<jc:plugin name="loading" />
		<!-- 表单验证 -->
		<jc:plugin name="validator" />
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
		$(document).ready(function(){
			//表单验证				
			$.formValidator.initConfig({formID:"form1",submitOnce:true,debug:false,
				onError:function(msg,obj,errorlist){
					alert(msg);
				},
				onSuccess:function(){
					
					return true;
				}
			});
        	$("#score").formValidator({onShow:"请输入评分（1-100之间）",onFocus:"只能输入1-100之间的数字",onCorrect:"评分成功"}).inputValidator({min:1,max:100,type:"value",onError:"评分必须在1-100之间，请确认"});
		 });
		</script>
	
  </head>
  
  <body>
<c:if test="${worklog.delete==false }">
 <c:if test="${worklog.pingFen==true }">
     <form id="form1" name="form1" action="audit_worklog" method="post">		
		<!--头部层开始 -->
		<head:head title="日报评分" >
		</head:head>
		<!--主体层开始 -->
		<body:body>		
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					<th style="text-align:left; font-weight:bold;">日报评分</th>
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
					<td class="lable_100">日报标题：</td>
					<td>
						${worklog.title }
					</td>
				</tr>
				<tr>
					<td class="lable_100">日报内容：</td>
					<td>
						<fieldset style="background-color: white;">
			         		<div>${worklog.content }</div>
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
				<tr>
					<td class="lable_100"><span>*</span>评分：</td>
					<td>
						<input type="text" class="txt_box_150" name="score" id="score" value=""/> 
						<div id="scoreTip" style="width:180px;float: right;padding-right:500px;height: 18px;"></div>
											
					</td>
				</tr>
				<tr>
					<td class="lable_100">意见：</td>
					<td>
			         	<textarea id="content" name="content"></textarea>
			         	<script type="text/javascript">CKEDITOR.replace( 'content' );</script>
					</td>
				</tr>
				<tr>
					<td><input type="hidden" name="id" value="${worklog.id }"/></td>
					<td><input class="btn_black_61" id="add_sub" type="submit" value="评价"/></td>
				</tr>
			</table>
		</body:body>
	</form>
</c:if>
<c:if test="${worklog.pingFen==false }">
	<center><h1>您已经评论了该日报！谢谢</h1><input class="btn_black_61" onclick="viewClose()" type="button" value="关闭"/></center>
</c:if>
</c:if>
<c:if test="${worklog.delete==true }">
	<center><h1>该日报不存在！</h1><input class="btn_black_61" onclick="viewClose()" type="button" value="关闭"/></center>
</c:if>
  </body>
</html>
