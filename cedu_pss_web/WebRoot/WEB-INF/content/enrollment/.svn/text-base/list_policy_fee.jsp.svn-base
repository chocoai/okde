<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>院校收费标准设置</title>
		
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
			
			
			/////////      /分页列表/    /////////////////
			
			//列表自定义字段显示
			function feesubject(op,indexcount,id)
			{
				if(indexcount>0)
				{
					isPageOperating(id,601,"update");
				}
				return op;
			}	
			
			function show(name)
			{
				return name;
			}
			
			//删除
			function deleteFun(id)
			{
				policyFeeId=id;
				if(confirm("确认删除吗？"))
				{
					ajax_100_1();
				}
			}
			
			//ajax回调函数  删除优惠标准
			var policyFeeId=0;//优惠标准Id
			function ajax_policyfee(data)
			{				
			    if(data.isfail)
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
		</script>
		<!--删除收费标准-->
		<a:ajax successCallbackFunctions="ajax_policyfee" parameters="{policeFeeId:policyFeeId}" urls="/enrollment/delete_policy_fee_ajax" pluginCode="100"/>
	</head>
  
  <body>

 
	<!-- 头开始 -->
		<head:head title="院校收费标准设置">
			<html:a text="新增院校收费标准" icon="add" href="/enrollment/add_policy_fee"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
				
				<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">院校收费标准设置</th>
						
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
						<td></td>
		                <td align="left">
							<input type="button" class="btn_black_61"  onclick="search601();" value="查询"/>
							<input type="button" class="btn_black_61"  onclick="refresh601();" value="刷新"/>
						</td>
	             	</tr>
				</table>
				
				<page:plugin 
						pluginCode="601"
						il8nName="enrollment"
						searchListActionpath="list_policy_fee_ajax"
						searchCountActionpath="count_policy_fee_ajax"
						columnsStr="academyname;title;modename;#feesubject;feestandardes"
						customColumnValue="3,feesubject(feeSubjectname,indexcount,id);4,show(feestandardes)"
						pageSize="10"
						isPage="true"
						isNumber="false"
						isChecked="false"
						update="http,/enrollment/modify_policy_fee,id,id,_blank"
						view="http,/enrollment/view_policy_fee,id,id,_blank"	
													
						params="'academyId':$('#academyId').val(),'feesubjectId':$('#feesubjectId').val(),'result.order':'title','result.sort':'desc'"
					/>
	
			</body:body>
	
	<!--弹出层-->
	<div id="message_returns_tips" style="display:none">
		<div align="center" id="showDialog">
		
		</div>
	</div>
  </body>
</html>
