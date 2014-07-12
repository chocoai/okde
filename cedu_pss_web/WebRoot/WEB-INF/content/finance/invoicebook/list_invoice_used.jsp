<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>已使用收据</title>
    	<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 选项卡 -->
		<jc:plugin name="tab" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />

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
		function getstatus()
		{
			return '已开票'
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

		</script>
	
  </head>
  
  <body>
  		<!--头部层开始 -->
		<head:head title="已使用收据">
		</head:head>
		<!--主体层开始 -->
		<body:body>
		<input type="hidden" id="branchlst" name="branchlst" ></input>
			
			
			
			<page:plugin 
					pluginCode="123"
					il8nName="finance"
					searchListActionpath="listreceipt"
					searchCountActionpath="countreceipt"
					columnsStr="code;studentName;certNo;batchName;levelName;majorName;status;isCanceled;"
					customColumnValue="0,linkcode(feePaymentId,code);1,linkstudent(studentId,studentName);6,getstatus();7,iscanceled(isCanceled);"
					pageSize="10"
					ontherOperatingWidth="80px"	
					params="'id':'${param.id}','status':Fee_STATUS_TRUE,'isCancel':PAYMENT_STATUS_ZUO_FEI"
			/>
   			</body:body>
	
	
  </body>
</html>
