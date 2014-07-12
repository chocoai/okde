<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>院历订制</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<jc:plugin name="loading" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
	<script type="text/javascript">
		function findSuccessCallbackFunction(data){
			if(data.schoolCalendarSet!=null){
				var sids=data.schoolCalendarSet.schoolIds;
				if(sids!=null){
					var ss=sids.split(",");
					for(var i=0;i<ss.length;i++){
						$("#s_"+ss[i]).attr("checked",true);
						
					}
				}
				
			}
		}
		function updateSuccessCallbackFunction(data){
		
		}
		$("document").ready(function(){
		     $("#btn1").click(function(){
		    	 $("[name='ids']").attr("checked",'true');//全选
		     });
		     $("#btn2").click(function(){
		         $("[name='ids']").removeAttr("checked");//取消全选
		   	 });
		     $("#btn3").click(function(){
		    	 $("[name='ids']:even").attr("checked",'true');//选中所有奇数
		     });
		     $("#btn4").click(function(){
		         $("[name='ids']").each(function(){
					if($(this).attr("checked")){
		               $(this).removeAttr("checked");
		            }else{
		              $(this).attr("checked",'true');
		             }
		         });
		      
		    });
		 });
	</script>
	<a:ajax successCallbackFunctions="findSuccessCallbackFunction" pluginCode="001" urls="academy/find_school_calendar_set" isOnload="all"/>
	<a:ajax traditional="true" parameters="$('#myform').serializeObject()" successCallbackFunctions="updateSuccessCallbackFunction" pluginCode="002" urls="academy/update_school_calendar_set"/>
	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="院历订制">
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<form id="myform">
			<table class="add_table" border="0">	
				<c:if test="${request.academys!=null}" >
					<c:forEach var="ac" items="${request.academys}">
						<tr>
							<td  width="30px"><input type="checkbox" id="s_${ac.id }" name="ids" class="schids" value="${ac.id }" /></td>
							<td >${ac.name }</td>
						</tr>
					</c:forEach>
					<tr>
						<td align="center" colspan="2">
							<input type="button" id="btn1" value="全选">
							<input type="button" id="btn2" value="取消全选">
							<input type="button" id="btn3" value="选中所有奇数">
							<input type="button" id="btn4" value="反选">
							<input class="btn_black_61" type="button" id="save" onclick="ajax_002_1();"  value="保存" />
						</td>
					</tr>
				</c:if>
			</form>
			</table>
		</body:body>
	</body>

</html>