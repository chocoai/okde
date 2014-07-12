<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>院校收费政策</title>
		
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

			//加载事件
			jQuery(function(){
							
				//审核下拉框
				showaduitStatus();
				
				//院校相关级联(批次、学习中心-层次-专业(防止刷新时其他不刷新))
				jQuery('#academyId').change(function(){
					selectbatch();
					selectbranch();
					//selectlevel();ajax延时放到批次里执行
					//selectmajor();
				});	
				//招生批次相关级联(学习中心、层次、专业(防止刷新层次，专业不刷新))
				jQuery('#batchId').change(function(){
					selectbranch();
					selectlevel();
					//selectmajor();
				});	
				//层次专业级联
				jQuery('#levelId').change(function(){
					selectmajor();
				});	
				
				//信息提示
				jQuery('#message_returns_tips').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'信息提示',
					buttons: {
						'关闭': function() { 
							jQuery(this).dialog("close"); 
						} 
					}
				});	
			});
			//审核下拉框
			function showaduitStatus()
			{
				$('#aduitStatus').empty();
			    $('#aduitStatus').append('<option value="3">--请选择--</option>');
			    $('#aduitStatus').append('<option value="'+AUDIT_STATUS_FALSE+'">未审批</option>');
			    $('#aduitStatus').append('<option value="'+AUDIT_STATUS_TRUE+'">审批通过</option>');
			    $('#aduitStatus').append('<option value="'+AUDIT_STATUS_FAIL+'">审批不通过</option>');
			}
			/////////      /级联/    /////////////////
			
			 //读取批次
		    function selectbatch()
		    {
		    	$.ajax({
		    		url:'<s:url value="cascade_academy_batch_all_ajax" />',
		    		data:{academyId:jQuery('#academyId').val()},
		    		dataType:'json',
		    	 	type:'post',
		    	 	success:function(data){
		    	 		$('#batchId').empty();
			    	 	$('#batchId').append('<option value="0">--请选择--</option>');	
			    	 	if(data.batchlist!=null&&data.batchlist.length>0)
			    	 	{
				    	 	$.each(data.batchlist,function(){	
				    	 		$('#batchId').append('<option value="'+this.id+'">'+this.enrollmentName+'</option>'); 
				    	 	});
			    	 	}
			    	 	selectlevel();//读取层次  	
			    	 }	
		    	});
		    }
			 //读取学习中心
		    function selectbranch()
		    {
		   		var batchId=$('#batchId').val();
		    	$.ajax({
		    		url:'<s:url value="cascade_batch_branch_ajax" />',
		    		data:{batchId:batchId,academyId:jQuery('#academyId').val()},
		    		dataType:'json',
		    	 	type:'post',
		    	 	success:function(data){
		    	 		$('#branchId').empty();
			    	 	$('#branchId').append('<option value="0">--请选择--</option>');	
			    	 	if(data.branchlist!=null&&data.branchlist.length>0)
			    	 	{
				    	 	$.each(data.branchlist,function(){	
				    	 		$('#branchId').append('<option value="'+this.id+'">'+this.name+'</option>'); 
				    	 	});
			    	 	}	
			    	 }	
		    	});
		    }
			 //读取层次
		    function selectlevel()
		    {
		   		var batchId=$('#batchId').val();
		    	$.ajax({
		    		url:'<s:url value="cascade_batch_level_ajax" />',
		    		data:{batchId:batchId},
		    		dataType:'json',
		    	 	type:'post',
		    	 	success:function(data){
		    	 		$('#levelId').empty();
				    	$('#levelId').append('<option value="0">--请选择--</option>');
			    	 	if(data.levellist!=null&&data.levellist.length>0)
			    	 	{	
				    	 	$.each(data.levellist,function(){	
				    	 		$('#levelId').append('<option value="'+this.id+'">'+this.level.name+'</option>'); 
				    	 	});
				    	 }	
				    	 selectmajor();//读取专业
			    	 }	
		    	});
		    	
		    }
			 //读取专业
		    function selectmajor()
		    {
		   		var levelId=$('#levelId').val();
		    	$.ajax({
		    		url:'<s:url value="cascade_level_major_ajax" />',
		    		data:{levelId:levelId},
		    		dataType:'json',
		    	 	type:'post',
		    	 	success:function(data){
		    	 		$('#majorId').empty();
			    	 	$('#majorId').append('<option value="0">--请选择--</option>');
			    	 	if(data.majorlist!=null&&data.majorlist.length>0)
			    	 	{
			    	 		$.each(data.majorlist,function(){	
			    	 			$('#majorId').append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	 		});
			    	 	}	
			    	 }	
		    	});
		    }
			
			/////////      /分页列表/    /////////////////
			
			//列表自定义字段显示
			function feesubject(op)
			{
				return op;
			}	
			function aduitstatus(aduitStatus,id)
			{
				if(parseInt(aduitStatus)==AUDIT_STATUS_FALSE)
				{
					return "未审批";
				}
				else if(parseInt(aduitStatus)==AUDIT_STATUS_TRUE)
				{
					isPageOperating(id,601,"update");
					isPageOperating(id,601,"delete");
					return "审批通过"
				}
				else
				{
					isPageOperating(id,601,"delete");
					//isPageOperating(id,601,"update");
					return "审批不通过";
				}
				
			}	
			function isusing(isUsing)
			{
				if(parseInt(isUsing)==STATUS_ENABLED)
				{
					return "启用";
				}
				return "停用";
			}
			function showfee(feestandardes)
			{
				return feestandardes;
			}
			
			function changeShow(check){
				if(check==false){
	
					$('#table601 tr').find('td:eq(1)').hide(); 
					$(".academyname601").hide();
				}else{
					$('#table601 tr').find('td:eq(1)').show(); 
					$(".academyname601").show();
				}
			}
			
			function changeShow1(check){
				//alert(check);
				if(check==false){

					$('#table601 tr').find('td:eq(3)').hide(); 
					$(".branchname601").hide();
				}else{
					$('#table601 tr').find('td:eq(3)').show(); 
					$(".branchname601").show();
				}
			}
			//删除
			function deleteFun(id)
			{
				jQuery('#message_confirm').dialog({
					title:'确认操作',
					buttons: {
					'确认': function() { 						
							pfdId=id;
							ajax_100_1();//删除
							jQuery(this).dialog("close"); 
						}, 
					'取消': function() { 
							jQuery(this).dialog("close"); 
						} 
					}
				});
				jQuery('#message_confirm').dialog("open"); 
			}
			//ajax回调函数  删除
			var pfdId=0;//院校收费政策Id
			function ajax_pfd(data)
			{				
			    if(data.isback)
			    {
			    	refresh601();
			    	jQuery("#showDialog").html('<b>删除成功！</b>');
					jQuery('#message_returns_tips').dialog("open");
			    }
			    else
			    {
			    	jQuery("#showDialog").html('<b>删除失败！</b>');
					jQuery('#message_returns_tips').dialog("open");
			    }			  
			}
			$(document).ready(function(){
				//changeShow1(true);
				//changeShow(true);
			});
			function list_b601(data){
				changeShow($("#academyname").attr("checked"));
				changeShow1($("#branchname").attr("checked"));
			}
		</script>
		<!--删除收费标准-->
		<a:ajax 
			successCallbackFunctions="ajax_pfd"
			parameters="{pfdId:pfdId}" 
			urls="/enrollment/del_policy_fee_detail_ajax" 
			pluginCode="100"
		/>
	</head>
	
    <body>
	
		<!-- 头开始 -->
		<head:head title="院校收费政策设置">
			<html:a text="新增院校收费政策" icon="add" href="/enrollment/add_policy_fee_detail"/>
			<html:a text="批量修改" icon="update" href="/enrollment/update_batch_policy_fee_detail"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
				
				<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">院校收费政策</th>
						
					</tr>
				</table>
	
				<table class="add_table">
					<tr>
		                <td>院校：</td>
		                <td align="left">
		                	
		                	<s:if test="%{academylist!=null}">
								<s:select list="%{academylist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="academyId" id="academyId" cssClass="txt_box_150"/>
							</s:if>
		                	<s:else>
		                		<select name="academyId" id="academyId" class="txt_box_150">
									<option value="0">--请选择--</option>
								</select>
		                	</s:else>	
		                	<label><input id="academyname" onclick="changeShow(this.checked);" type="checkbox" checked="checked"/>显示</label>
		                	
						</td>
		                <td>招生批次：</td>
		                <td align="left">
							<select name="batchId" id="batchId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
						<td>学习中心：</td>
		                <td align="left">
							<select name="branchId" id="branchId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
							<label><input id="branchname" onclick="changeShow1(this.checked);" type="checkbox" checked="checked"/>显示</label>
						</td>
		                <td>层次：</td>
		                <td>
							<select name="levelId" id="levelId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
	              	</tr>
				  	<tr>
		                <td>专业：</td>
		                <td align="left">
							<select name="majorId" id="majorId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
		                <td>费用科目：</td>
		                <td align="left">
		                	<s:if test="%{feesubjectlist!=null}">
		                		<s:select list="%{feesubjectlist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="feesubjectId" id="feesubjectId" cssClass="txt_box_150"/>
		                	</s:if>
		                	<s:else>
		                		<select name="feesubjectId" id="feesubjectId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
		                	</s:else>							
						</td>
						<td>审批：</td>
		                <td align="left">
		                	<select name="aduitStatus" id="aduitStatus" class="txt_box_150">
								<option value="3">--请选择--</option>
							</select>						
						</td>
						<td></td>
		                <td align="left">
							<input type="button" class="btn_black_61"  onclick="search601();" value="查询"/>
						</td>
	             	</tr>
				</table>
				
				<page:plugin 
						pluginCode="601"
						il8nName="enrollment"
						searchListActionpath="list_policy_fee_detail_ajax"
						searchCountActionpath="count_policy_fee_detail_ajax"
						columnsStr="academyname;batchname;branchname;levelname;majorname;#feesubject;feestandardes;#aduitstatus;#isusing"
						customColumnValue="5,feesubject(feeSubjectname);6,showfee(feestandardes);7,aduitstatus(aduitStatus,id);8,isusing(isUsing)"
						pageSize="10"
						isNumber="false"
						isChecked="false"
						listCallback="list_b"
						delete="json,deleteFun,id"
						update="http,/enrollment/update_policy_fee_detail,id,id,_self"
						view="http,/enrollment/view_policy_fee_detail,id,id,_blank"								
						params="'academyId':$('#academyId').val(),'batchId':$('#batchId').val(),'branchId':$('#branchId').val(),'levelId':$('#levelId').val(),'majorId':$('#majorId').val(),'feesubjectId':$('#feesubjectId').val(),'aduitStatus':$('#aduitStatus').val()"
					/>
	
			</body:body>
	<!--弹出层-->
	<div id="message_returns_tips" style="display:none">
		<div align="center" id="showDialog">
		
		</div>
	</div>
	<!--弹出层    确认操作-->
	<div id="message_confirm" style="display:none">
		<div align="center" >
			<b>确认删除院校收费政策么？</b>
		</div>
	</div>
  </body>
 
</html>
