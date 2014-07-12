<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 选项卡 -->
		<jc:plugin name="tab" />
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		<!-- 操作等待插件 -->
		<jc:plugin name="loading" />
		
	<script type="text/javascript">
	function  showtime(startTime,endTime){
	var  a=startTime+"--"+endTime;
  return a.substring(0,10)+"--"+a.substring(21,31);}
 
 	$(document).ready(function(){
 	search0909();
 	});
		function showsummary(){
		return '<a href="index_exam_schedule_summary">巡考总结</a>';
		}
		
	function teacherping(id){
	return '<a href="lists_invigilator?id='+id+'">监考老师评价</a>';
	}
		function showbao(){
		return '<a>监控报告</a>';
		}
		function actualcost(id,planedCost,actualCost){
		var lists='';
	     lists+='<span id="sp'+id+'" >'+actualCost+'</span>';
		if(actualCost>planedCost){
		$('#sp'+id).css({'color':'red'});
		//$('#sp'+id).css({'fontSize':'12px'});
		}
		return lists;
		}	
		
		function showinspector(data){
	if(data==1){
	return "赵芬";
	}
	 if(data==2){
	return "安雅楠";
	}
	if(data==3){
	return "魏媛媛";
	}
	}
		</script>

  </head>
  
  <body>
  <head:head title="监控报告">
		</head:head>
  <body:body>
  
          <table class="add_table" cellpadding="0" cellspacing="0" border="0" width="100%">
		   <tr>
		   <td class="lable_100">考试批次：</td><td><s:select list="%{exambatchlist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="code" name="examBatchId" id="examBatchIds" cssClass="txt_box_130"/></td>
		   <td class="lable_100">考点:</td><td><s:select list="%{examarealist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="examAreaId" id="examAreaIds" cssClass="txt_box_130"/></td>
		   <td><input type="button" name="" id="" value="查询" class="btn_black_61"  onclick="search0909()" />
		   </td>
		   </tr>
		</table>  		
		      <table class="gv_table_2" id="resTable" border="0" cellpadding="0" cellspacing="0">
	    	<tbody>
			 <page:plugin 
				pluginCode="0909"
				il8nName="examschedule"
				searchListActionpath="findByconditions_examschedule"
				searchCountActionpath="count_examschedule"
				columnsStr="examBatchname;plan;examAreaname;inspector;Time;planedCost;actualCost;#summary;#jiankong;#teacher"
				customColumnValue="3,showinspector(inspector);4,showtime(startTime,endTime);6,actualcost(id,planedCost,actualCost);7,showsummary();8,showbao();9,teacherping(id)"
				pageSize="5"
				ontherOperatingWidth="80px"	
				params="'examBatchId':$('#examBatchIds').val(),'examAreaId':$('#examAreaIds').val()"
		     />  	 
	    	</tbody>
	    </table>			
  </body:body>
  </body>
</html>

