<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>修改收费政策</title>
		
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

			//加载事件
			jQuery(function(){
			
				////////////////////////赋值\\\\\\\\\\\\\\\\\\\\\
				
				//收费标准赋值
				search007();
				
				
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
			/////////      /ajax提交/    /////////////////		
			function adddetail()
			{
				
				if(jQuery("input[name='pfeeradio']").length==0 || jQuery("input[name='pfeeradio']:checked").length==0)
				{
					jQuery("#showDialog").html('<b>请选择政策标准！</b>');
					$('#null_for_academy').dialog("open");
				}
				else
				{
					var policyFeeId=jQuery("input[name='pfeeradio']:checked").val();//收费标准id
					
					 //修改政策
			    	$.ajax({
			    		url:'<uu:url url="/enrollment/update_polify_fee_detail_ajax" />',
			    		data:{policyFeeId:policyFeeId,policyFeeDetailId:jQuery('#policyFeeDetailId').val()},
			    		dataType:'json',
			    	 	type:'post',
			    	 	success:function(data){
			    	 		
				    	 	if(data.backtracks)
				    	 	{
				    	 		jQuery("#showDialog").html('<b>修改政策成功！</b>');
								$('#null_for_academy').dialog("open");	   	 		
				    	 	}	
				    	 	else
				    	 	{
				    	 		jQuery("#showDialog").html('<b>修改政策失败！</b>');
								$('#null_for_academy').dialog("open");
				    	 	}
			    	 	
				    	 }	
			    	});
		
				}
			}	
		    	
		    /////////      /收费标准分页列表/    /////////////////			
			//列表自定义字段显示
			function feesubject(op)
			{
				return op;
			}	
			
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
				if(id==parseInt(jQuery("#policyFeeId").val()))
				{
					return '<input type="radio"  checked="checked" name="pfeeradio" value="'+id+'"/>';
				}
				else
				{
					return '<input type="radio" name="pfeeradio" value="'+id+'"/>';
				}
			}
		</script>
	</head>
  
  <body>
<!-- 头开始 -->
		<head:head title="修改收费政策">
			<html:a text="返回" icon="return" onclick="history.go(-1);"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
			   <form name="myform" action="update_policy_fee_detail" method="post">
				<input type="hidden" name="policyFeeId" id="policyFeeId" value="${policyFeeDetail.policyFeeId}"/>
				<input type="hidden" name="policyFeeDetailId" id="policyFeeDetailId" value="${policyFeeDetail.id}"/>
				<input type="hidden" name="academyId" id="academyId" value="${policyFeeDetail.academyId}"/>
				<input type="hidden" name="feesubjectId" id="feesubjectId" value="${policyFeeDetail.feeSubjectId}"/>
				 <table class="gv_table_2">
				  	<tr>
						 <th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 <th style="text-align:left; font-weight:bold;">基本信息</th>							
					</tr>
				  </table>
				  <table class="add_table" id="brach" class="brach" border="0" cellpadding="2" cellspacing="2">
				  	<tr>
		                <td class="lable_100">院校：</td>
		                <td align="left">
		                	${policyFeeDetail.academyname}
						</td>
					</tr>
					<tr>
		                <td class="lable_100">学习中心：</td>
		                <td align="left">
		                	${policyFeeDetail.branchname}
						</td>
					</tr>
					<tr>
		                <td class="lable_100">批次：</td>
		                <td align="left">
		                	${policyFeeDetail.batchname}
						</td>
					</tr>	
					<tr>
		                <td class="lable_100">层次：</td>
		                <td align="left">
		                	${policyFeeDetail.levelname}
						</td>
					</tr>
					<tr>
		                <td class="lable_100">专业：</td>
		                <td align="left">
		                	${policyFeeDetail.majorname}
						</td>
					</tr>
					<tr>
		                <td class="lable_100">费用科目：</td>
		                <td align="left">
		                	${policyFeeDetail.feeSubjectname}
						</td>
					</tr>
				  </table>
				  
				   <table class="gv_table_2">
				  	<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;" class="feehtml">政策标准
					 	   </th>
				    </tr>
				  </table>
				  
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
				 
				  <table class="add_table"><tr><td align="center"><input class="btn_black_61" type="button" onclick="adddetail()" value="修改" /></td></tr></table>
				</form>
			</body:body>
	
	<!--div id="null_for_academy"  style="display:none;font-weight:bold" align="center"></div-->
	<div id="null_for_academy" style="display:none">
		<div align="center" id="showDialog">
		
		</div>
	</div>
	<div id="show_for_academy" style="display:none">
		<table class="gv_table_2" id="showcover">
			<tbody></tbody>
			<tfoot></tfoot>
		</table>
	</div>
 	<div id="message_confirm" style="display:none">
		<div align="center">确认执行该操作！</div>
	</div>
  </body>
</html>
