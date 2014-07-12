<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>招生指标查询</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<jc:plugin name="page" />
		<jc:plugin name="loading" />
		<!--全局批次(学习中心)-->
		<a:ajax  isZebraCrossing="false" isOnload="all" successCallbackFunctions="ajax_global_batch" parameters="{branchId:jQuery('#branchId').val()}" urls="/enrollment/cascade_global_batch_branch_ajax" pluginCode="100"/>
		<!--招生指标-->
		<a:ajax isZebraCrossing="false" parameters="jQuery('#myform').serializeObject()" successCallbackFunctions="find_user_enroll_quota_callback" pluginCode="crm" urls="/crm/find_user_enroll_quota"/>
		<script type="text/javascript">
			function ajax_global_batch(data)
			{		
				$('#globalBatchId').empty();
			    $('#globalBatchId').append('<option value="0">--请选择--</option>');
			    if(data.globalBatchList!=null && data.globalBatchList.length>0)
			    {
			    	$.each(data.globalBatchList,function(){	
			    		$('#globalBatchId').append('<option value="'+this.id+'">'+this.batch+'</option>'); 
			    	});
			   	}
			   	$("#user_zhibiao >tbody").html('<tr align="center" ><td colspan="5">没有找到符合条件的记录</td></tr>');
			}
			
			function find_user_enroll_quota_callback(data){
				if(data.users[0]==null){
					$("#user_zhibiao >tbody").html('<tr align="center" ><td colspan="5">没有找到符合条件的记录</td></tr>');
					return;
				}
				var str="";
					$.each(data.users[0].academylst,function(){
						str+='<tr>'
							+'<td align="center">${session.userTicket.fullName}</td>'			
							+'<td align="center">'+this.name+'</td>'
							+'<td align="center">'+this.expectTarget+'</td>'
							+'<td align="center"><a href="'+WEB_PATH+'/crm/students_follow_view?enrollmentBatchId='+$('#globalBatchId').val()+'&academyId='+this.id+'">'+this.complete+'</a></td>'
						+'</tr>';
					});
					
				$("#user_zhibiao >tbody").html(str);
				mergeCells();
			}
			function mergeCells(){
				if(!checktable('user_zhibiao')){
					return false;
				}else{
					coltogather($('#user_zhibiao tr'),0);
				}
			}
			
			function loadUser(){
				if($('#globalBatchId').val()==0||$('#globalBatchId').val()=="0"){
					return ;
				}
				//加载招生指标
				ajax_crm_1();
			}
		</script>
	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="招生指标查询">
		</head:head>
		<!--主体层开始 -->
		<body:body>
					<table class="gv_table_2">
						<tr>
							<th style="width:20px;"><img src="<%=Constants.WEB_IMAGES %>/plugin/base_css/images/operating/title_left.gif" /></th>
							<th style="text-align:left; font-weight:bold;">个人招生指标</th>					
						</tr>
					</table>
								
								<table class="add_table" border="0">
									<tr>
										<td align="right">学习中心：</td>
						                <td align="left">
											${branch.name}
											<input type="hidden" id="branchId" name="student.branchId" value="${branch.id}"/>
										</td>
						                <td align="right">全局批次：</td>
						                <td align="left">
						                 <form id="myform">
											<select name="batchId" id="globalBatchId" class="txt_box_150" onchange="loadUser();">
												<option value="0">--请选择--</option>
											</select>
										</form>
										</td>
					              	</tr>
								</table>
				<table class="gv_table_2" id="user_zhibiao">
					<thead>
						<tr>
							<th>负责人</th>
							<th>院校</th>
							<th>指标数</th>
							<th>完成数</th>					
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
								
				
		</body:body>
	</body>

</html>