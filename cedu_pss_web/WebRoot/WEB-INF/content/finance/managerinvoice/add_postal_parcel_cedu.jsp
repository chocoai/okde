<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>新增邮寄包</title>
    	<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!--  Ajax操作等待 -->
		<jc:plugin name="loading" />
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
			
		function findinvoice()
		{
			$('#managementinvoicelistDiv').attr('style','display:block');
			search123();
		}
	   
	   	//验证
	    function ids()
	    {
	    	$('#invoiceids').val(getCheckedValues123());
	    	var postSerialNo=$('#postSerialNo').val();
	    	var code=$('#code').val();
	    	var forwarder=$('#forwarder').val();
	    	if($.trim(postSerialNo) =="")
	    	{
	    		show('postalDiv','提示',250,150);
	    		return false;
	    	}
	    	if($.trim(code) =="")
	    	{
	    		show('codeDiv','提示',250,150);
	    		return false;
	    	}
	    	if($.trim(forwarder) =="")
	    	{
	    		show('forwarderDiv','提示',250,150);
	    		return false;
	    	}
	    	if(getCheckedValues123()=="")
	    	{
	    		show('errorDiv','提示',250,150);
	    		return false;
	    	}
	    	return true;
	    }
	  
	   function listCallback123(data){
			if(data.result.list.length==0)
			{
				$('#subDiv').attr('style','display:none');
			}else
			{
				$('#subDiv').attr('style','display:block');
			}
				
		}
		</script>
	
  </head>
  
  <body>
  
   <form id="form" onsubmit="return ids()" action="<uu:url url="/finance/managerinvoice/add_postal_parcel_cedu" />" method="post" enctype="multipart/form-data">	
   <input type="hidden" id="invoiceids" name="invoiceids"  />
   
   <!--头部层开始 -->
		<head:head title="新增邮寄包">
			<html:a text="返回" href="#" onclick="javascript:history.go(-1);" icon="return"  />
		</head:head>
		<!--主体层开始 -->
		<body:body>
			
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">发票列表</th>			
				</tr>
			</table>
			<fieldset>
				<font color="red">提示：请通过学习中心查询需要邮寄的发票！</font>
			</fieldset>
	 
	 		<table class="add_table" cellpadding="0" cellspacing="0" border="0" width="100%">
				   <tr>
					   <td class="lable_100">学习中心：</td>
					     <td>
					     	<s:select list="%{branchlst}" listKey="id"  theme="simple" listValue="name" name="branchId" id="branchId" cssClass="txt_box_150"/>
					     	<input type="button"  name="" id="" onclick="findinvoice()"  value="查询" class="btn_black_61" />
					     </td>	
						
					 </tr>
			</table>
				
		  <div id="managementinvoicelistDiv" style="display:none">
			<table class="add_table" border="0">
			  <tr>

				<td  align="right"><span>*</span>邮寄单号：</td>
                <td align="center">
					<input type="text" id="postSerialNo" name="postSerialNo"  class="txt_box_130" />
				</td>
   
				<td  align="right"><span>*</span>邮包号：</td>
                <td align="center">

					<input type="text" id="code" name="code"  class="txt_box_130" />
				</td>
				<td  align="right"><span>*</span>货运公司：</td>
                <td align="center">
					<input type="text" id="forwarder" name="forwarder"   class="txt_box_130"/>
				</td>  
				   
              </tr>
         </table>
		
			<page:plugin 
					pluginCode="123"
					il8nName="finance"
					searchListActionpath="listmanagementinvoicecedufalse"
					columnsStr="issuedTime;invoiceCode;studentName;academyenrollbatchName;levelName;majorName;amountPaied"
					customColumnValue=""
					isPage="false"
					listCallback="listCallback"
					isChecked="true"
					ontherOperatingWidth="80px"	
					params="'branchId':$('#branchId').val(),'isPost':0"
			/>
			
			<div id="subDiv">
			<table class="add_table">
		  	<tr>
				<td align="center" colspan="2"><input type="checkbox" name="ispost" id="ispost" />是否邮寄
					<input class="btn_black_130" type="submit" value="保存" id="btn1" name="btn1"/>
					
					<input class="btn_black_61" type="reset" value="取消"/>
				</td>
			</tr>
		  </table>
		  </div>
		  
		  
		  
		  
		  </div>
		  
		  <div  id="errorDiv" class="update_error"  style="display:none">
			  请选择要邮寄的发票！！
		  </div>
		  
		   <div  id="postalDiv" class="update_error"  style="display:none">
			邮寄单号不能为空！！
		  </div>
		  
		   <div  id="codeDiv" class="update_error"  style="display:none">
			邮包号不能为空！！
		  </div>
		  
		   <div  id="forwarderDiv" class="update_error"  style="display:none">
			发货公司不能为空！！
		  </div>
		  
		  
		  
		 

		 

		   </body:body>
	
	</form>
  </body>
</html>
