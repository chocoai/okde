<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>所有收据</title>
    	<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- jquery-loading -->
		<jc:plugin name="loading" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 选项卡 -->
		<jc:plugin name="tab" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />



		
		<script type="text/javascript">
		var ids=0;
		
		//作废
		function ajax_update(data)
		{
			show('updateDiv','提示',250,150);
			//search123();
			refresh123();
		}
		
		
		</script>
		
		<a:ajax parameters="{'id':ids,'status':AUDIT_STATUS_INIT}"
				successCallbackFunctions="ajax_update"
				urls="/finance/invoicebook/updatereceipt"
		 pluginCode="123" />








		<script type="text/javascript">
	
		
		
		/*
		收据明细
		*/
		function linkcode(feePaymentId,code)
		{
			if(feePaymentId>0)
			{
				return '<a href="'+WEB_PATH+'/finance/payment/payment_view?feePaymentId='+feePaymentId+'" target="_blank">'+code+'</a>';
			}
			return code;
		}
	
		/*
		学生明细
		*/
		function linkstudent(studentId,name)
		{
			if(studentId>0)
			{
				return '<a href="'+WEB_PATH+'/crm/view_home?studentId='+studentId+'" target="_blank">'+name+'</a>';
			}
			return name;
		}
		
		
		
		/*
		授权状态
		*/
		function getstatus(status)
		{
			if(status==AUDIT_STATUS_INIT)
			{
				return '已作废';
			}else
			{
				return status==Fee_STATUS_FALSE?"未开票":"已开票";
			}
			
		}

		/*
		是否核销
		*/
		function iscanceled(cancel)
		{
			if(cancel==STATUS_AUTHOR_FALSE)
			{
				return '未核销';
			}else
			{
				return '已核销';
			}
		}
		
		
		/*
		声明作废
		*/
		function updatestatus(id,status)
		{
			if(status==AUDIT_STATUS_INIT)
			{
				return "";
			}
			return '<a href="javascript:void(0)"  onclick="update('+id+')">核销</a>';
		}


		/*
		作废
		*/
		function update(id)
		{
			$('#message_confirm').dialog({
				title:'确认操作',
				buttons: {
					'确认': function() { 
							$(this).dialog("close"); 
							ids=id;
							ajax_123_1();
						}, 
					'取消': function() { 
							$(this).dialog("close"); 
						} 
				}
			});
			$('#message_confirm').dialog("open"); 
		}
		</script>
	
  </head>
  
  <body>
   		<!--头部层开始 -->
		<head:head title="所有收据">
		</head:head>
		<!--主体层开始 -->
		<body:body>
		<input type="hidden" id="branchlst" name="branchlst" ></input>
			
			
			
			<page:plugin 
					pluginCode="123"
					il8nName="finance"
					searchListActionpath="listreceipt"
					searchCountActionpath="countreceipt"
					columnsStr="code;studentName;certNo;batchName;levelName;majorName;status;isCanceled;#operation;"
					customColumnValue="0,linkcode(feePaymentId,code);1,linkstudent(studentId,studentName);6,getstatus(status);7,iscanceled(isCanceled);8,updatestatus(id,status)"
					pageSize="10"
					ontherOperatingWidth="80px"	
					params="'id':'${param.id}','status':-2,'isCancel':-2"
			/>
			
			
			<div id="updateDiv" style="display:none">
			修改成功！！
			</div>	
			
			
			
			
   		</body:body>
		<!--弹出层    确认操作-->
		<div id="message_confirm" style="display:none">
			<div align="center" >
				<b>确认核销吗？</b>
			</div>
		</div>
	
  </body>
</html>
