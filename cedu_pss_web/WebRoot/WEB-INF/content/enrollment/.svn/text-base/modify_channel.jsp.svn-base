<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>修改合作方</title>
		
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 表单验证 -->
		<jc:plugin name="validator" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		
		<script type="text/javascript">
			$(document).ready(function(){
				 util.select.initOption('select[name=sertificateType]', get_sertificate());
			
				$("#type").val('${channel.type}');	
				$("#branchId").val('${channel.branchId}');
				$("#sertificateType").val('${channel.sertificateType}');
				
				if(${channel.isAll}==STATUS_AUTHOR_FALSE)
				{
					$("#all").attr("checked",true);
				}else
				{
					$("#notall").attr("checked",true);
				}
				
				//$(document).ready(function(){
			    util.select.initOption('select[name=sertificateType]', get_sertificate());
			 	
			 	var bol='${bol}';
			 if(bol)
			 {
			 	show('addDiv','提示',200,150);
			 }
			 
			 
			  $.formValidator.initConfig({formID:"form1",debug:false,submitOnce:true,
					onError:function(msg,obj,errorlist){
						//$.map(errorlist,function(msg1){alert(msg1)});
						alert(msg);
					}
				});
			 
			$("#name").formValidator({onShow:"请输入合作方名称",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"合作方名称不能为空,请确认"});
			$("#contractNo").formValidator({onShow:"请输入合作编号",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"合作编号不能为空,请确认"});
			$("#type").formValidator({onShow:"请选择合作方类别",onFocus:"合作方类别必须选择",onCorrect:"输入正确",defaultvalue:"0"}).inputValidator({min:1,onError: "你是不是忘记选择合作方类别!"}).defaultPassed();  
			$("#branchId").formValidator({onShow:"请选择学习中心",onFocus:"学习中心必须选择",onCorrect:"输入正确",defaultvalue:"0"}).inputValidator({min:1,onError: "你是不是忘记选择学习中心!"}).defaultPassed();  
			$("#accountBank").formValidator({onShow:"请输入开户行",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"开户行不能为空,请确认"}); 
			$("#accountName").formValidator({onShow:"请输入开户名",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"开户名不能为空,请确认"}); 
			$("#account").formValidator({onShow:"请输入账号",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"账号不能为空,请确认"}); 
			$("#linkman").formValidator({onShow:"请输入联系人",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"联系人不能为空,请确认"}); 
			$("#telephone").formValidator({onShow:"请输入联系电话",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"联系电话不能为空,请确认"}); 
			$("#sertificateNo").formValidator({onShow:"请输入证件号码",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"证件号码不能为空,请确认"}); 
			

		//});	
				//如果是老带新或老生续读禁止修改名称和类型
				if(${channel.type}==WEB_STU_ENROLLMENT_SOURCE || ${channel.type}==WEB_STU_LAO_SHENG_XU_DU)
				{
					$('#name').attr({'disabled':'disabled'});
					$('#type').attr({'disabled':'disabled'});
				}
				
				
				
			});
			
			//验证下拉列表框
			function types()
			{
				if(${channel.type}==WEB_STU_ENROLLMENT_SOURCE || ${channel.type}==WEB_STU_LAO_SHENG_XU_DU)
				{
					return true;
				}
				else
				{
					if($('#type').val()==WEB_STU_ENROLLMENT_SOURCE || $('#type').val()==WEB_STU_LAO_SHENG_XU_DU)
					{
						alert('合作方不合法');
						return false;
					}
					else
					{
						return true;
					}
				}
			}
			
		</script>
	</head>
  
  <body>
    <form id="form1" name="form1" action="modify_channel" method="post" enctype="multipart/form-data" >
		
		<!-- 头开始 -->
		<head:head title="修改合作方">
			<html:a text="返回" icon="return" href="${url}"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
					<table class="gv_table_2">
				  	<tr>
						 	<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;">基本资料</th>
					  </tr>
					</table>
					<input type="hidden" class="txt_box_150" name="id" id="id" value="${channel.id}"/>
					<input type="hidden" class="txt_box_150" name="actionResult" id="actionResult" value="${actionResult}"/>
					
						<table class="add_table">  
						        <tr>
							      	<td class="lable_100"><span>*</span>合作方名称：</td>
							        <td>
							        	<input type="text" class="txt_box_300" name="name" id="name" value="${channel.name}"/>
							        </td>
									 <td><div id="nameTip" style="width:250px"></div></td>
								</tr>
								<tr>
								    <td class="lable_100"><span>*</span>合同编号：</td>
									<td><input type="text" name="contractNo" id="contractNo" class="txt_box_150"  value="${channel.contractNo}"/></td>
									 <td><div id="contractNoTip" style="width:250px"></div></td>
								</tr>
								<tr>
									<td class="lable_100"><span>*</span>合作方类别：</td>
							        <td>
							        	<s:select list="%{feelist}" listKey="id" headerKey="" disabled="%{channel.aduitStatus==1?true:false}" headerValue="--请选择--" theme="simple" listValue="name" name="type" id="type" cssClass="txt_box_150"/>
							        </td>	 
						        	 <td><div id="typeTip" style="width:250px"></div></td>
						        </tr>
							    <tr>
								    <td class="lable_100"><span>*</span>学习中心：</td>
									<td>
										<s:select list="%{branchlist}" listKey="id" headerKey="" disabled="%{channel.aduitStatus==1?true:false}" headerValue="--请选择--" theme="simple" listValue="name" name="branchId" id="branchId" cssClass="txt_box"/>
									</td>
		                       		 <td><div id="branchIdTip" style="width:250px"></div></td>
		                       </tr>
		                       <tr>
							    <td class="lable_100">办公地址：</td>
								<td colspan="3"><input type="text" name="officeAddress" id="officeAddress" class="txt_box_300" value="${channel.officeAddress}"/></td>
							  	 <td><div id="officeAddressTip" style="width:250px"></div></td>
							   </tr>
							   <tr>
		                        <td class="lable_100"><span>*</span>开户行：</td>
								<td><input id="accountBank" name="accountBank" class="txt_box_150" value="${channel.accountBank}"/></td>
							 	<td><div id="accountBankTip" style="width:250px"></div></td>
							 </tr>
							 <tr>
							    <td class="lable_100"><span>*</span>开户名：</td>
								<td><input type="text" name="accountName" id="accountName" class="txt_box_150" value="${channel.accountName}"/></td>
		                      	<td><div id="accountNameTip" style="width:250px"></div></td>
		                        </tr>
								<tr>
		                        <td class="lable_100"><span>*</span>账号：</td>
								<td><input id="account" name="account" class="txt_box_150" value="${channel.account}"/></td>
							 	<td><div id="accountTip" style="width:250px"></div></td>
							 </tr>
								<tr>
			                        <td class="lable_100"><span>*</span>联系人：</td>
									<td><input id="linkman" name="linkman" class="txt_box_150" value="${channel.linkman}"/></td>
									<td><div id="linkmanTip" style="width:250px"></div></td>
							 </tr>
							
							 <tr>
							    <td class="lable_100"><span>*</span>证件类型：</td>
								<td><select name="sertificateType" id="sertificateType" class="txt_box_150"  value="${channel.sertificateType}">
								    <option selected="selected" value="">--请选择--</option>
									
								</select></td>
		                      	<td><div id="sertificateTypeTip" style="width:250px"></div></td>
		                       </tr>
								<tr>
		                        <td class="lable_100"><span>*</span>证件号码：</td>
								<td><input id="sertificateNo" name="sertificateNo" class="txt_box_150" value="${channel.sertificateNo}"/></td>
								<td><div id="sertificateNoTip" style="width:250px"></div></td>
							 </tr>
							 <tr>
							    <td class="lable_100"><span>*</span>联系电话：</td>
								<td><input type="text" name="telephone" id="telephone" class="txt_box_150" value="${channel.telephone}"/></td>
		                        <td><div id="telephoneTip" style="width:250px"></div></td>
		                        </tr> 
							 <tr>
								<td class="lable_100">附件标题：</td>
								<td><input type="text" name="attachmentName" id="attachmentName" value="${channel.attachmentName}" class="txt_box_150"/></td>
							 </tr>
							 <tr>
								<td class="lable_100">附件：</td>
								<td><input id="files" name="files" type="file" class="txt_box_150"/></td>
							 </tr>	
							
							 <tr>
							    <td class="lable_100"></td>
								<td align="center" colspan="3"><input type="submit" id="add_sub" name="add_sub" value="保存" class="btn_black_61" onclick="return types()"/>
								         
								</td>
							 </tr>
						</table>	
		       </body:body>
	</form>
  </body>
</html>
