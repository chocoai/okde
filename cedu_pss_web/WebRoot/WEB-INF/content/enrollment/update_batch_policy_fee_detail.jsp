<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>批量修改院校收费政策</title>
		
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
				jQuery('#academyId').change();
				
				//初始化提示框
				$('#null_for_academy').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'信息提示',
					buttons: {
						'关闭': function() { 
							$(this).dialog("close"); 
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
			    	 		//$('#batchId').empty();
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
			//查询按钮
			function showpolicyfeedetail()
			{
				if(jQuery("#academyId").val()==0)
				{
					jQuery("#showDialog").html('<b>请选择院校！</b>');
					$('#null_for_academy').dialog("open");
				}
				else if(jQuery("#batchId").val()==0)
				{
					jQuery("#showDialog").html('<b>请选择批次！</b>');
					$('#null_for_academy').dialog("open");
				}
				else if(jQuery("#feesubjectId").val()==0)
				{
					jQuery("#showDialog").html('<b>请选择费用科目！</b>');
					$('#null_for_academy').dialog("open");
				}
				else
				{
					jQuery("#nextdiv").show();
					search601();
					search007();				
				}
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
					isPageOperating(id,601,"checkbox");
					return "审批通过"
				}
				else
				{
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
			/////////      /收费标准分页列表/    /////////////////			
			//列表自定义字段显示	
			function showname(name)
			{
				return name;
			}
			function adda(title,id)
			{
				return '<a href="view_policy_fee?id='+id+'" target="_blank">'+title+'</a>';
			}
			function operat(id)
			{
				return '<input type="radio" name="pfeeradio" value="'+id+'"/>';
			}
			
			//修改
			var ids="";
			var policyFeeId=0;
			function batchedit()
			{
				if(getCheckedValues601()=="" || getCheckedValues601()==null)
				{
					jQuery("#showDialog").html('<b>请选择要修改的政策！</b>');
					$('#null_for_academy').dialog("open");
				}
				else if(jQuery("input[name='pfeeradio']").length==0 || jQuery("input[name='pfeeradio']:checked").length==0)
				{
					jQuery("#showDialog").html('<b>请选择政策标准！</b>');
					$('#null_for_academy').dialog("open");
				}
				else
				{
					//ids=getCheckedValues601();
					policyFeeId=jQuery("input[name='pfeeradio']:checked").val();
					ajax_119_1();
				}
				 
			}
			//ajax回调函数
			function ajax_batch_edit(data){
				if(data.isfail)
				{
					jQuery("#showDialog").html('<b>修改成功！</b>');
					search601();
					$('#null_for_academy').dialog("open");
				}
				else
				{
					jQuery("#showDialog").html('<b>修改失败！</b>');
					$('#null_for_academy').dialog("open");
				}
			}
		</script>
		<!--ajax插件-->
		<a:ajax successCallbackFunctions="ajax_batch_edit" parameters="{ids:getCheckedValues601(),policyFeeId:jQuery(\"input[name='pfeeradio']:checked\").val()}" urls="/enrollment/update_batch_policy_fee_detail_ajax" pluginCode="119"/>
	</head>
  
  <body>

    <!-- 头开始 -->
		<head:head title="批量修改院校收费政策">
			<html:a text="返回" icon="return" href="/enrollment/list_policy_fee_detail"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">批量修改院校收费政策</th>
						
					</tr>
				</table>
	
				<table class="add_table">
					<tr>
		                <td><span>*</span>院校：</td>
		                <td align="left">
		                	<s:if test="%{academylist!=null && academylist.size()>0}">
								<s:select list="%{academylist}" listKey="id" theme="simple" listValue="name" headerKey="0" headerValue="--请选择--" name="academyId" id="academyId" cssClass="txt_box_150"/>
							</s:if>
		                	<s:else>
		                		<select name="academyId" id="academyId" class="txt_box_150">
									<option value="0">--请选择--</option>
								</select>
		                	</s:else>	
						</td>
		                <td><span>*</span>招生批次：</td>
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
		                <td><span>*</span>费用科目：</td>
		                <td align="left">
		                	<s:if test="%{feesubjectlist!=null && feesubjectlist.size()>0}">
		                		<s:select list="%{feesubjectlist}" listKey="id" theme="simple" headerKey="0" headerValue="--请选择--" listValue="name" name="feesubjectId" id="feesubjectId" cssClass="txt_box_150"/>
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
							<input type="button" class="btn_black_61"  onclick="showpolicyfeedetail();" value="查询"/>
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
						pageSize="5"
						isPage="false"
						isNumber="false"
						isChecked="true"
						checkboxValue="id"							
						params="'academyId':$('#academyId').val(),'batchId':$('#batchId').val(),'branchId':$('#branchId').val(),'levelId':$('#levelId').val(),'majorId':$('#majorId').val(),'feesubjectId':$('#feesubjectId').val(),'aduitStatus':$('#aduitStatus').val()"
						isonLoad="false"
					/>
				<div id="nextdiv" style="display:none">
					<table class="add_table">
						<tr>
							<td align="center">
								<input type="button" class="btn_black_61"  onclick="jQuery('#shownextdiv').show();" value="下一步"/>
							</td>
						</tr>
					</table>
				</div>
				<div id="shownextdiv" style="display:none">
					<page:plugin 
							pluginCode="007"
							il8nName="enrollment"
							searchListActionpath="list_policy_fee_ajax"
							searchCountActionpath="count_policy_fee_ajax"
							columnsStr="#operating;academyname;title;#feesubject;feestandardes"
							customColumnValue="0,operat(id);2,adda(title,id);3,feesubject(feeSubjectname);4,showname(feestandardes)"
							pageSize="5"
							isPage="false"
							isNumber="false"
							isChecked="false"								
							params="'academyId':$('#academyId').val(),'feesubjectId':$('#feesubjectId').val()"
							isonLoad="false"
						/>
				
					<table class="add_table">
						<tr>
							<td align="center">
								<input type="button" class="btn_black_61"  onclick="batchedit()" value="修改"/>
							</td>
						</tr>
					</table>
				</div>	
			</body:body>
	<div id="null_for_academy" style="display:none">
		<div align="center" id="showDialog">
		
		</div>
	</div>
  </body>
</html>
