<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>院校列表</title>
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
		
		<script type="text/javascript">
		$(function(){
			$('[name=batch]').click(function(){
				var index = this.value;
				$('[id^=added_branch_]').hide();
				var count=index.split("_");
				$('#added_branch_'+count[0]).show();
				if(parseInt(count[0])==2)
				{
					showFeeBatch();
				}
			});	
		});
		
		var changeindex=0;//全局变量  
		//显示自定义缴费次数
		function showFeeBatch()
		{
			var count = $("input[name='batch']:checked").val();
			var index=count.split("_");
			var batchId=index[1];
			//alert(batchId);
			$.post('<s:url value="branchfeebatchlist"/>',{batchId:batchId,academyId:$("#academyId").val()},
			function(data){
				var lists='';
				changeindex=1;
				if(data.feelist!=null)
				{
					
					$.each(data.feelist, function(){
	    				lists+='<tr id="'+changeindex+'">';
						lists+='<td>'+changeindex+'</td>';
						lists+='<td>'+this.name+'</td>';
						lists+='<td>'+this.startTime+'</td>';
		    			lists+='<td>'+this.endTime+'</td>';
						lists+='<td><input type="button" value="删除" onclick="deleteFeeBatch('+changeindex+','+this.id+')" /></td>';
						lists+='</tr>';
						changeindex++;
					});
	    		}
	    		
	    			lists+='<tr id="'+changeindex+'">';
					lists+='<td>'+changeindex+'</td>';
					lists+='<td><input type="text" id="feename'+changeindex+'" name="feename'+changeindex+'"/></td>'
					lists+='<td><input class="Wdate" type="text" name="starttime'+changeindex+'" id="starttime'+changeindex+'" onclick="WdatePicker()"/></td>';
	    			lists+='<td><input class="Wdate" type="text" name="endtime'+changeindex+'" id="endtime'+changeindex+'" onclick="WdatePicker()"/></td>';
					lists+='<td><input type="button" value="添加" onclick="addFeeBatch('+changeindex+')" /></td>';
					lists+='</tr>';
					changeindex++;
	    		
   				$('#added_branch_2 > tfoot').html(lists);
			},"json");
		}
		
		//添加自定义缴费次数
		function addFeeBatch(id)
			{
				var feename=$("#feename"+id).val();
				var starttime=$("#starttime"+id).val();
				var endtime=$("#endtime"+id).val();
				if(feename==null||feename=="")
				{	
					alert("请输入缴费次数名称");
				}
				else if(starttime==null||starttime=="")
				{
					alert("请输入开始时间");
				}
				else if(endtime==null||endtime=="")
				{
					alert("请输入结束时间");
				}
				else
				{
					var count = $("input[name='batch']:checked").val();
					var index=count.split("_");
					var batchId=index[1];
					
					//var lists='';
					//lists=$('#added_branch_2 > tfoot').html();
					 $.post('branchfeebatchadd', {feename:feename,starttime:starttime,endtime:endtime,batchId:batchId,academyId:$('#academyId').val()},
				        function(data)
				    	{
				    		showFeeBatch();
				    		//lists+='<tr id="'+changeindex+'">';
							//lists+='<td>'+changeindex+'</td>';
							//lists+='<td><input type="text" id="feename'+changeindex+'" name="feename'+changeindex+'"/></td>'
							//lists+='<td><input class="Wdate" type="text" name="starttime'+changeindex+'" id="starttime'+changeindex+'" onclick="WdatePicker({dateFmt:\'MM-dd\'})"/></td>';
			    		//	lists+='<td><input class="Wdate" type="text" name="endtime'+changeindex+'" id="endtime'+changeindex+'" onclick="WdatePicker({dateFmt:\'MM-dd\'})"/></td>';
							//lists+='<td><input type="button" value="添加" onclick="addFeeBatch('+changeindex+')" /></td>';
							////lists+='</tr>';
							//changeindex++;
							//$('#added_branch_2 > tfoot').html(lists);
				    	},"json");
				 }
			}
		//删除缴费次数
		function deleteFeeBatch(changeindex,id)
		{
			if(confirm("确认删除吗？"))
			{
				$('#'+changeindex).remove();
				$.post('branchfeebatchdelete', {id:id},
				     function(data)
				    	{
				    		showFeeBatch();
				    	},"json");
			}
		}
		</script>
	
  </head>
  
  <body>
  <!-- 头开始 -->
		<head:head title="院校名称：${academy.name}">
			<html:a text="返回" icon="return" onclick="history.go(-1);"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<!--Menu Begin-->
			<%@ include file="_tab/academy_enroll_tab.jsp" %>
			<!--Menu End-->
			
			<!--Left Begin-->
			<div style="float:left; width:300px;">
			<input type="hidden" value="${academy.id}" id="academyId" name="academyId"/>
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif" />" /></th>
				 	<th style="text-align:left; font-weight:bold;">院校招生批次&nbsp;</th>
				</tr>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2" id="way_tab">
				<tbody id="year_2010">
					<s:iterator id="items" value="studyList" status="sta"> 
						<tr>
							<s:if test="#sta.count==1">
								<td><input type="radio" name="batch" value="1_${items.id}"  checked="checked"/>默认 <input type="radio" name="batch" value="2_${items.id}"/>自定义</td>
								<td ><s:property value="enrollmentName"/></td>
							</s:if>
							<s:else>
								<td><input type="radio" name="batch" value="1_${items.id}"  />默认 <input type="radio" name="batch" value="2_${items.id}"/>自定义</td>
								<td ><s:property value="enrollmentName"/></td>
							</s:else>
						</tr>
					</s:iterator>
					
				</tbody>
				
			</table>
			
			</div>
			<!--Left End-->
			
			<!--Line Begin-->
			<div style="float:left;width:4px; height:500px; margin-left:2px; margin-right:2px;">
			</div>
			<!--Line End-->
			
			<!-- Center BEGIN -->
			<div style="float:left; width:600px;">
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif" />" /></th>
				 	<th style="text-align:left; font-weight:bold;">缴费次数</th>

				</tr>
			</table>
			<table id="added_branch_1" class="add_table" border="0" cellpadding="2" cellspacing="2" style="display:block;">
					<tr>
						<td width="30">编号</td>
						<td>缴费次数名称</td>
						<td></td>
						<td>开始时间</td>
						<td>结束时间</td>
					</tr>
				<s:iterator id="item" value="feeList" status="st"> 
					<tr>
						<td width="30"><s:property value="#st.count"/></td>
						<td ><s:property value="name"/></td>
						<td width="30"></td>
						<td ><s:date name="startTime" format="MM-dd" /></td>
						<td ><s:date name="endTime" format="MM-dd" /></td>
					</tr>
				</s:iterator>
				<!--tr>
					<td colspan="4" align="center"><input id="btn_remove" class="btn_gray_130" type="button" value="确定"/></td>
				</tr-->
			</table>
			<table id="added_branch_2" class="add_table" border="0" cellpadding="2" cellspacing="2" style="display:none;">
				<tbody>
					<tr>
						<td width="30">编号</td>
						<td>缴费次数名称</td>
						<td>开始时间</td>
						<td>结束时间</td>
						<td>操作</td>
					</tr>
				</tbody>
				<tfoot>
					
				</tfoot>
				
			</table>
			
			</table>
			<!-- table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<tr><td><input id="btn_remove" class="btn_gray_130" type="button" value="确定"/></td></tr>
				</tr>
			</table> -->
			</div>
			
			<div style="float:left;width:4px; height:300px; margin-left:2px; margin-right:2px;">
			</div>
			
			<!-- Center END -->
			
			<!--Right Begin-->
			
			<!--Right End-->		
			
  		</body:body>
  </body>
</html>
