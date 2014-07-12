<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
   <title>监考审批</title>
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
		<!-- 选项卡 -->
		<jc:plugin name="tab" />
		<jc:plugin name="loading" />
		
		
		<script>
		//操作列头
		function replay(id,status)
		{
			if(status==0)
			{
		    var image='<a href="kaochang_anpai?id='+id+'">考场安排</a>';
			return image;
			}
			if(status==1)
			{
				return '已安排';
			}
			
		}
		function ap(op)
		{
			jQuery("#status").val(op);
			search123();
		}
		function showTitle(id,plan){
		var detial='<a href="view_examplan?id='+id+'">"'+plan+'"</a>';
		return detial;
		}
		
		function showTime(examBeginTime,examEndTime){
		return examBeginTime+"--"+examEndTime;
		}
		
		$(document).ready(function(){
				$("#menu>li>a").click(function(){
						$(this).attr("class","current");
						var selObj=this;
							$("#menu>li>a").each(function(){
									if(this!=selObj){
									$(this).attr("class","");
									}
							});
					});
			});
		</script>
	
</head>
  <body>
  
  <div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a>考场安排</a> </li>
		</ul>
	</div>
</div>
<div class="block">
<br/>
<div>
	<ul id="menu">
		<li><a href="javascript:ap(0)" title="未安排" class="current">未安排</a></li>
		<li><a href="javascript:ap(1)" title="已安排" >已安排</a></li>
	</ul>
</div>
</div>
<div class="block">
	<div class="public_table_bg_766">
		<div class="tb_table_default_2">
 	  	<input type="hidden" name="status" id="status" value="<%=Constants. AUDIT_STATUS_FALSE %>"/>
		<table class="add_table" cellpadding="2" cellspacing="0" border="0" width="100%">
			    <tr>
				   <td class="lable_100">层次：</td>
				   <td>
				     <s:select list="%{levelist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="levelId" id="levelId" cssClass="txt_box_130"/>
				     </td>
				   <td class="lable_100">专业：</td>
				   <td><select name="majorId" id="majorId" class="txt_box_130">
				           <option value="0">--全部--</option>
						   <option value="1">会计学</option>
						   <option value="2">物流管理</option>
						   <option value="3">工商管理</option>
						   <option value="4">工程管理</option>
						   <option value="5">计算机应用技术</option>
						   <option value="6">城市轨道交通</option>
						   <option value="7">计算机科学与技术</option>
				   </select></td>
				   <td class="lable_100">批次：</td>
						<td>
						<select class="txt_box_150" name="batchId" id="batchId">
						   <option value="0">--全部--</option>
						   <option value="1">1003</option>
						   <option value="2">1009</option>
						   <option value="3">1109</option>
						</select>
						</td>
				   <td><input type="button" value="查询" class="btn_black_61" onclick="search123();"></td>
				</tr>
			 </table>
		     <table class="gv_table_2" id="resTable" border="0" cellpadding="0" cellspacing="0">
	    	<tbody>
			 <page:plugin 
				pluginCode="123"
				il8nName="examination"
			    searchListActionpath="findByconditions_examplan"
				searchCountActionpath="count_examplan"
				columnsStr="plan;levelname;majorname;batchname;#times;planCount;invigilatorIds;#preparing"
				customColumnValue="0,showTitle(id,plan);4,showTime(examBeginTime,examEndTime);7,replay(id,status)"
				pageSize="5"
				ontherOperatingWidth="80px"	
				params="'levelId':$('#levelId').val(),'majorId':$('#majorId').val(),'batchId':$('#batchId').val(),'examplan.status':$('#status').val()"
		     />  	 
	    	</tbody>
	    </table>    
   </div>
  </div>
 </div>
</body>
</html>
