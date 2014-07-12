<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>添加合作方</title>
		
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 表单验证 -->
		<jc:plugin name="validator" />
		<!-- 分页 -->
		<jc:plugin name="page" />
		<jc:plugin name="loading" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		
		<script type="text/javascript">

		function addsub(number)
		{
			jQuery("#num").val(number);
			//if(jQuery('#type').val() == WEB_STU_ENROLLMENT_SOURCE || jQuery('#type').val()==WEB_STU_LAO_SHENG_XU_DU)
			//{
			//	if(jQuery('#studentId').val()<=0)
			//	{
			//		alert('学生异常！');
			//		return false;
			//	}
			//}
		}
		
		jQuery(document).ready(function(){
			//util.select.initOption('select[name=sertificateType]', get_sertificate());
			
			 if(${bol})
			 {
			 	alert('合作方已存在,请重新添加！！');
			 	// 取消此方法，会导致页面重新发送，造成死循环 edited by dongminghao
			 	//location.reload();
			 }
			 
			 
			  jQuery.formValidator.initConfig({formID:"form1",debug:false,submitOnce:true,
					onError:function(msg,obj,errorlist){
						alert(msg);
					}
				});
			 
			jQuery("#channelname").formValidator({onShow:"请输入合作方名称",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"合作方名称不能为空,请确认"});
			jQuery("#channelcontractNo").formValidator({onShow:"请输入合作编号",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"合作编号不能为空,请确认"});		
			jQuery("#type").formValidator({onShow:"请选择合作方类别",onFocus:"合作方类别必须选择",onCorrect:"输入正确",defaultvalue:"0"}).inputValidator({min:1,onError: "你是不是忘记选择合作方类别!"}).defaultPassed();  
			jQuery("#branchId").formValidator({onShow:"请选择学习中心",onFocus:"学习中心必须选择",onCorrect:"输入正确",defaultvalue:"0"}).inputValidator({min:1,onError: "你是不是忘记选择学习中心!"}).defaultPassed();  
			jQuery("#channelaccountBank").formValidator({onShow:"请输入开户行",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"开户行不能为空,请确认"}); 
			jQuery("#channelaccountName").formValidator({onShow:"请输入开户名",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"开户名不能为空,请确认"}); 
			jQuery("#channelaccount").formValidator({onShow:"请输入账号",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"账号不能为空,请确认"}); 
			jQuery("#channellinkman").formValidator({onShow:"请输入联系人",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"联系人不能为空,请确认"}); 
			jQuery("#channeltelephone").formValidator({onShow:"请输入联系电话",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"联系电话不能为空,请确认"}).regexValidator({regExp:"mobileortel",dataType:"enum",onError:"你输入的手机/座机号码格式不正确"}); 
			jQuery("#channelsertificateNo").formValidator({onShow:"请输入证件号码",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"证件号码不能为空,请确认"}); 
			
			
			jQuery('#student_div').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'学生列表',
					width: 700,
					height: 340,
					buttons: {
						'确认': function() { 
							var s_id="";
							var s_name="";
							jQuery("[name=stidentId_radio]").each(function(){
								if(this.checked){
									s_id=jQuery(this).val();
									s_name=jQuery.trim(jQuery(this).attr("id"));
									return false;
								}
							});
							if(s_id==null || s_id=="" || s_id==0)
							{
								alert('请先选择学生再确认！');
								return false;
							}
							jQuery('select').each(function(){
							jQuery(this).css("display","");
							});
							jQuery("#studentId").val(s_id);
							jQuery("#channelname").val(s_name+"_"+s_id);
							
							jQuery(this).dialog("close"); 
						}, 
						'取消': function() { 
							jQuery("#studentId").val(0);
							jQuery("#channelname").val("");
							jQuery('select').each(function(){
							jQuery(this).css("display","");
							});
							jQuery(this).dialog("close"); 
						} 
					}
				});
			$('a.ui-dialog-titlebar-close').hide();
			
			
			
			

		});	 
			
			//如果合作方类别为老带新或老生续读
			function typechange(obj)
			{
				jQuery('#hetongbianhao').html('');
				if(obj==WEB_STU_ENROLLMENT_SOURCE||obj==WEB_STU_LAO_SHENG_XU_DU)
				{
					jQuery("#studentId").val(0);
					jQuery("#channelcontractNo").formValidator(null);
					jQuery("#hezuofang").html("<span>*</span>学生：");
					jQuery("#selectStu").attr("style","display:");
					jQuery("#channelname").val("");
					jQuery("#channelname").attr("readonly","readonly");
				}else
				{
					jQuery("#studentId").val(0);
					jQuery("#channelname").val("");
					jQuery("#channelname").attr("readonly","");
					jQuery("#selectStu").attr("style","display:none");
					jQuery("#hezuofang").html("<span>*</span>合作方名称：");
					if(obj!=WEB_STU_DA_KE_HU)
					{
						jQuery('#hetongbianhao').html('*');
						jQuery("#channelcontractNo").formValidator({onShow:"请输入合作编号",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"合作编号不能为空,请确认"});		
					}else
					{
						jQuery("#channelcontractNo").formValidator(null);
					}
				}
			}
			
			//打开人员选择
			function openStudentDiv(){
				jQuery('#student_div').dialog("open");
				jQuery('select').each(function(){
					if(this.id!="sex1")
					{
						jQuery(this).css("display","none");
					}
				});
			}
			
			function operatingValue(id,name){
				return '<input type="radio" id="'+name+'" name="stidentId_radio" value="'+id+'" />';
			}
			
			
			
		</script>
	</head>
  
  <body>
    <form id="form1" name="form1" action="../enrollment/add_channel" method="post"  enctype="multipart/form-data">
		<input type="hidden" name="num" id="num"  />
		<input type="hidden" name="channel.studentId" id="studentId" value="0" />
		<!--头部层开始 -->
		<head:head title="添加合作方">
			<html:a text="返回" icon="return"  onclick="history.go(-1);"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
					<table class="gv_table_2">
				  	<tr>
						 	<th  style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif"/>" /></th>
						 	<th style="text-align:left; font-weight:bold;">基本资料</th>
					  </tr>
					</table>
						
						<table class="add_table">  
						     <tr>
								<td class="lable_100"><span>*</span>合作方类别：</td>
						        <td>
						        	<s:select onchange="typechange(this.value)" list="%{feelist}" listKey="id" headerKey="" headerValue="--请选择--" theme="simple" listValue="name" name="type" id="type" cssClass="txt_box_150"/>
						        </td>	 
						      	<td><div id="typeTip" style="width:250px"></div></td>  
						     </tr>
						    <tr>
						      	<td class="lable_100"><div id="hezuofang"><span>*</span>合作方名称：</div></td>
						        <td>
						        	<input type="text"  class="txt_box_150" name="channel.name" id="channelname" />
						        	&nbsp;<input style="display:none"  class="btn_black_61" type="button" id="selectStu" onclick="openStudentDiv();"  value="选择" />
						        </td>
						         <td><div id="channelnameTip" style="width:250px"></div></td>
						          </tr>
						      <tr>
							    <td class="lable_100"><span id="hetongbianhao"></span>合同编号：</td>
								<td><input type="text" name="channel.contractNo" id="channelcontractNo" class="txt_box_150"/></td>
								<td><div id="channelcontractNoTip" style="width:250px"></div></td>
				  			</tr>     
							
							 <tr>
							    <td class="lable_100"><span>*</span>所属中心：</td>
								<td>
									<s:select list="%{branchlist}" listKey="id" headerKey="" headerValue="--请选择--" theme="simple" listValue="name" name="branchId" id="branchId" cssClass="txt_box_150"/>	
								</td>
								<td><div id="branchIdTip" style="width:250px"></div></td>   
								  </tr>
							 <tr>
							    <td class="lable_100">办公地址：</td>
								<td colspan="3"><input type="text" name="channel.officeAddress" id="channelofficeAddress" class="txt_box_300" /></td>
								  
							 </tr>	  
								<tr>
		                        <td class="lable_100"><span>*</span>开户行：</td>
								<td><input id="channelaccountBank" name="channel.accountBank" class="txt_box_150"/></td>
								<td><div id="channelaccountBankTip" style="width:250px"></div></td>
							 </tr>
							 <tr>
							    <td class="lable_100"><span>*</span>开户名：</td>
								<td><input type="text" name="channel.accountName" id="channelaccountName" class="txt_box_150"/></td>
		                        <td><div id="channelaccountNameTip" style="width:250px"></div></td> 
		                          </tr>
							 <tr>
		                        <td class="lable_100"><span>*</span>账号：</td>
								<td><input id="channelaccount" name="channel.account" class="txt_box_150"/></td>
								<td><div id="channelaccountTip" style="width:250px"></div></td> 
							 </tr>
										  
							 <tr>
		                        <td class="lable_100"><span>*</span>联系人：</td>
								<td><input id="channellinkman" name="channel.linkman" class="txt_box_150"/></td>
							 	<td><div id="channellinkmanTip" style="width:250px"></div></td>
							 </tr>
							
							 <tr>
							    <td class="lable_100"><span>*</span>证件类型：</td>
								<td>
									<select id="" class="txt_box_70" name="student.certType">
										<option value="<%=Constants.CERTIFICATE_TYPE_ID %>" selected="selected">证件号</option>
										<option value="<%=Constants.CERTIFICATE_TYPE_DRIVER_ID %>">护照</option>
										<option value="<%=Constants.CERTIFICATE_TYPE_NCO_ID %>">士官证</option>
									</select>
								</td>
								  </tr>
							 <tr>
		                        <td class="lable_100"><span>*</span>证件号码：</td>
								<td><input id="channelsertificateNo" name="channel.sertificateNo" class="txt_box_150"/></td>
								<td><div id="channelsertificateNoTip" style="width:250px"></div></td> 
							</tr>
							 <tr>
							    <td class="lable_100"><span>*</span>联系电话：</td>
								<td><input type="text" name="channel.telephone" id="channeltelephone" class="txt_box_150"/></td>
		                    	<td><div id="channeltelephoneTip" style="width:250px"></div></td> 
		                      </tr>
							   
							 <tr>
								<td class="lable_100">附件标题：</td>
								<td><input type="text" name="channel.attachmentName" id="channelattachmentName" class="txt_box_150"/></td>
								
							 </tr>
							 <tr>
								<td class="lable_100">附件：</td>
								<td><input id="files" name="files" type="file" class="txt_box_150"/></td>
							 </tr>
							  <tr>
								<td class="lable_100"></td>
								<td>
								<input type="submit" onclick="addsub(1)" id="add_sub" name="add_sub" value="保存"  class="btn_black_61"/>
								<!-- input type="submit" onclick="addsub(2)" id="add_sub" name="add_sub" value="保存并申请政策"  class="btn_black_130"/> -->
								</td>
							 </tr>
						</table>
			
			<div id="addDiv" style="display:none">
			合作方已存在,请重新添加！！
			</div>
			
			
			<div id="student_div" style="display:none;overflow: hidden;">
						<table class="add_table">
							   <tr>
								   	 <td align="right">姓名：</td>
					                 <td align="right" style="width:100px;">
										<input type="text" class="txt_box_100" id="name1"/>
									 </td>
									 <td align="right">证件号：</td>
					                 <td align="right" style="width:100px;">
										<input type="text" class="txt_box_100" name="certNo" id="certNo"/>
									 </td>	
					                <td align="right">手机：</td>
					                 <td align="right" style="width:100px;">
										<input type="text" class="txt_box_100" name="phone" id="phone"/>
									 </td>
					                <td align="right">
										<input type="button" class="btn_black_61" value="查询" onclick="search_page_001()"/>
									</td>
								<td></td>
				              </tr>
		                </table>
									<page:plugin 
										pluginCode="_page_001"
										il8nName="crm"
										searchListActionpath="crm/crm_student_list"
										searchCountActionpath="crm/crm_student_count"
										columnsStr="#public.operating;certNo;name;schoolName;academyenrollbatchName;levelName;majorName"
										customColumnValue="0,operatingValue(id,name)"
										isPackage="false"
										pageSize="5"
										isPageSize="true"
										isonLoad="false"
										params="'student.callStatus':-1,'student.gender':-1,'student.certNo':jQuery('#certNo').val(),'student.name':jQuery('#name1').val(),'student.phone':jQuery('#phone').val()"
									/>		
					</div>
			
			
		        </body:body>
	</form>
  </body>
</html>
