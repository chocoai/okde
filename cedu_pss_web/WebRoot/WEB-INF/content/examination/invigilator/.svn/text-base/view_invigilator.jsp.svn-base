<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
   <title>监考教师详情</title>
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
		<jc:plugin name="loading" />
		<a:ajax parameters="{'content':$('#content').val(),'invigilatorId':$('#invigilatorId').val()}"  
		successCallbackFunctions="ajax_add" 
		urls="/examination/invigilator/add_communicationrecord" 
		pluginCode="123" 
		/>
		<script type="text/javascript">
	       function isShow(obj,aObj){
	 			if($("#"+aObj).html()=="隐藏"){
	 				document.getElementById(obj).style.display="none";
	 				$("#"+aObj).html("显示");
	 			}else{
	 				document.getElementById(obj).style.display="";
	 				$("#"+aObj).html("隐藏");
	 			}
	 		}
	 		jQuery(function(){
				//信息提示
				jQuery('#createRemarkBox').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'信息提示'
				});	});
	function ajax_add(){}
	function addcomm()
		{
			var content=$('#content').val();
	         if($.trim(content) =="")
			{
			   alert('内容不能为空');
				return;
			}
			ajax_123_1();
	}	
	</script>
	</head>
	<!-- 添加备注 -->
	<html>
<body>
<div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a>监考老师:${invigilator.name}</a> </li>
		</ul>
	</div>
	<div id="conmenu">
	 <img src="../images/icon_title_return.jpg" />
	 <a href="#" onclick="history.go(-1);">返回</a>
	</div>

</div>
<div class="block">
	<div class="public_table_bg_766">
		<div class="tb_table_default_2">
			<%@include file="_cedu_invigilator_tab.jsp" %>
			<!--Left Begin-->

			<div style="float:left; width:700px;">
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="../images/title_left.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">基本信息</th>
				</tr>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">

				<tr>
					<td class="lable_100">姓名：</td>
					<td>${invigilator.name}</td>
					<td rowspan="6"><img class="img_teacher" src="temp/t002.jpg" /></td>
				</tr>
				<tr>
					<td class="lable_100">性别：</td>

					<td><s:if test="invigilator.gender==1">
					男
					</s:if>
					<s:elseif test="invigilator.gender==2">
					 女
					</s:elseif>
					 <s:else>
					  无
					 </s:else>
					</td>
				</tr>
				<tr>
					<td class="lable_100">电话：</td>
					<td>${invigilator.telephone}</td>
				</tr>
				<tr>

					<td class="lable_100">邮箱：</td>
					<td><a href="mailto:huangcan@126.com">${invigilator.email}</a></td>
				</tr>
				<tr>
					<td class="lable_100">最高学历：</td>
					<td><s:if test="invigilator.degree==1">
					博士
					</s:if>
					<s:elseif test="invigilator.degree==2">
					博士后
					</s:elseif>
					<s:elseif test="invigilator.degree==3">
					本科
					</s:elseif>
					<s:elseif test="invigilator.degree==4">
					   硕士
					</s:elseif>
					<s:else>
					 研究生
					</s:else>
					</td>
				</tr>
				<tr>
					<td class="lable_100">证件类型：</td>
					<td><s:if test="invigilator.certType==1">
					身份证
					</s:if>
					<s:elseif test="invigilator.certType==2">
					学生证
					</s:elseif>
					<s:elseif test="invigilator.certType==3">
					驾驶证
					</s:elseif>
					<s:else>
					教师资格证
					</s:else></td>
				</tr>
				<tr>
					<td class="lable_100">证件号码：</td>
					<td>${invigilator.certType}</td>

					<td></td>
				</tr>
				<tr>
					<td class="lable_100">工作原单位：</td>
					<td>${invigilator.workUnit}</td>
					<td></td>
				</tr>	
				<tr>

					<td class="lable_100">收费标准：</td>
					<td>${invigilator.feeStandard}元/<s:if test="invigilator.feeType==1">
					天
					</s:if>
					 <s:elseif test="invigilator.feeType==0">
					 场
					 </s:elseif>
					</td>
					<td></td>
				</tr>	
			</table>
		   <table class="gv_table_2">
				      <tr>

						<th style="width:20px;"><img src="../images/title_left.gif" /></th>
						<th style="text-align:left; font-weight:bold;">其他信息</th>
						<th style="text-align:left">
						<div id="conmenu">
							<a href="javascript:isShow('remarkdiv','remark');" id="remark">显示</a></div>
						</th>
					</tr>
			</table>

			<div id="remarkdiv" style="display: block">
		    <table class="add_table" border="0" cellpadding="2" cellspacing="2">
			    <tr>
					<td class="lable_100">监考经历：</td>
					<td>${invigilator.invigilationExperience}</td>
				</tr>	
				<tr>
					<td class="lable_100">备注：</td>
					<td>${invigilator.note}</td>
				</tr>
			</table>
			</div>
			</div>
			<!--Left End-->
			<!--Line Begin-->
			<div style="float:left;width:4px; height:500px; background-color:#3394C1; margin-left:2px; margin-right:2px;">
			</div>
			<!--Line End-->
			
			<!--Right Begin-->
			<div style="margin-left:708px;">
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="../images/title_left.gif" /></th>
					<th style="text-align:left; font-weight:bold;">弘成学苑</th>

				</tr>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td>
						<table class="gv_table_2" border="0" cellpadding="2" cellspacing="2">
							<tr><td colspan="2" 
							style="background-color:#F3F3F3;text-align:center;font-weight:bold;">学习中心</td></tr>
							<tr><td width="75">中心名称：</td><td>北京学习中心</td></tr>
							<tr><td>创建者：</td><td>${userinfo.fullName}</td></tr>
							<tr><td>电话：</td><td>${userinfo.telephone}</td></tr>
							<tr><td>邮件：</td><td><a href="mailto:gaosong@126.com">${userinfo.email}</a></td>
							</tr>
						</table>
					</td>

				</tr>
				<tr>
					<td>
						<table class="gv_table_2" border="0" cellpadding="2" cellspacing="2">
							<tr><td colspan="2" 
							style="background-color:#F3F3F3;text-align:center;font-weight:bold;">维护信息</td></tr>
							<tr><td width="75">创建日期：</td><td>${invigilator.createdTime}</td></tr>
							<tr><td>更新用户：</td><td>${updateuserinfo.fullName}</td></tr>

							<tr><td>更新日期：</td><td><fmt:formatDate value="${invigilator.updatedTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td></tr>
						</table>
					</td>
				</tr>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tr><td><input id="btn1" class="btn_gray_130" type="button" onclick="show('createRemarkBox','添加沟通记录',460,240);" value="沟通记录" /></td></tr>
			</table>
			</div>
			<!--Right End-->		
  		</div>
  	</div>
  </div>
  <!-- 添加沟通记录-->
<div id="createRemarkBox" style="display:none;">
<input type="hidden" name="invigilatorId" id="invigilatorId" value="${id}"  />
    <table class="add_table">
            <tr>
            	<td class="lable_100">沟通内容：</td>
                <td><textarea  id="content" name="content"  cols="24" rows="8" class="txt_box_300"></textarea></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                	<input type="button" class="btn_black_61" name="save" onclick="addcomm();" value="添加"  />
                	<input type="button" class="btn_black_61" value="关闭" onclick="closes('createRemarkBox');" />
                </td>
            </tr>

        </table>
</div>
</body>
</html>
