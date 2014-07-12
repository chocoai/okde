<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../template/common/import.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>考场备案详情</title>
<!-- jquery库 -->
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
		<a:ajax parameters="{'fee':$('#feeStandard').val(),'capacity':$('#capacity').val(),'name':$('#name').val(),'examroomId':$('#examroomId').val(),'feetype':$('.feeType:checked').val()}"  
		successCallbackFunctions="ajax_add" 
		urls="/examination/examroom/add_classroom" 
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
	 		
	 	function ajax_add(){
		 show('添加成功','提示',200,100);
	     closes('createclassroom');
	    
		}

			//加载事件
			jQuery(function(){
				//信息提示
				jQuery('#createclassroom').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'信息提示'
				});		
			});
			
		function addclass()
		{
		   var examroomId=$('#examroomId').val();
			var name=$('#name').val();
			var count=$('#capacity').val();
			var fee=$('#feeStandard').val();
			var feetype=$(".feeType:checked").val();
			
			alert(name+","+count+"类型"+feetype);
			if($.trim(name)==""){
			alert('名称不能为空！');
			return;
			}
			if($.trim(count) =="")
			{
			   alert('数量不能为空！');
				//jQuery("#message_
			}
			if($.trim(fee)==""){
			alert('费用标准不能为空！');
			}
			if($.trim(feetype)==""){
			alert('收费类型不能为空');
			}
			ajax_123_1();
		}
		
	</script>
</head>  
<body>
<div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a>考场名称:${examroom.name }</a> </li>
		</ul>
	</div>
	<div id="conmenu">
	 <img src="<ui:img url="images/icon_title_return.jpg"/>" />
	 <a href="#" onclick="history.go(-1);">返回</a>
	</div>
</div>
<div class="block">
	<div class="public_table_bg_766">
		<div class="tb_table_default_2">
			<!--Menu Begin-->
			<div>
				<%@include file="_cedu_confirm_tab.jsp" %>
			</div>
			<!--Menu End-->
			
			<!--Left Begin-->
			<div style="float:left; width:700px;">
			<input type="hidden" name="id" id="id" value="${id}"/>
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="images/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">基本信息</th>
				</tr>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td class="lable_150">考场名称：</td>
					<td>${examroom.name}</td>
				</tr>
				<tr>
					<td class="lable_150">考场地址：</td>
					<td>${examroom.address}</td>
				</tr>
				<tr>
					<td class="lable_150">电话：</td>
					<td>${examroom.phone}</td>
				</tr>
				<tr>
					<td class="lable_150">邮箱：</td>
					<td><a href="lisi@126.com">${examroom.email }</a></td>
				</tr>
				<tr>
					<td class="lable_150">联系人：</td>
					<td>${examroom.linkman }</td>
				</tr>
			</table>
		   <table class="gv_table_2">
				      <tr>
						<th style="width:20px;"><img src="<ui:img url="images/title_left.gif"/>" /></th>
						<th style="text-align:left; font-weight:bold;">其他信息</th>
						<th style="text-align:left">
						<div id="conmenu">
							<a href="javascript:isShow('remarkdiv','remark');" id="remark">显示</a></div>
						</th>
					</tr>
			</table>
			<div id="remarkdiv" style="display: block;">
		    <table class="add_table" border="0" cellpadding="2" cellspacing="2">
			    <tr>
					<td class="lable_150">能否签订协议：</td>
					<td><s:if test="examroom.isSignable==0">
					是
					</s:if>
					<s:elseif test="examroom.isSignable==1">
					否 <br/> 原因：${examroom.signableSeason }
					</s:elseif>
					</td>
				</tr>	
				<tr>
					<td class="lable_150">能否长期租用：</td>
					<td><s:if test="examroom.isLongTerm==0">
					是
					</s:if>
					<s:elseif test="examroom.isLongTerm==1">
					否 <br/> 原因：${examroom.longTermSeason}
					</s:elseif></td>
				</tr>
				
				<tr>
					<td class="lable_150">能否提供监考老师：</td>
					<td><s:if test="examroom.hasInvigilator==0">
					是
					</s:if>
					<s:elseif test="examroom.hasInvigilator==1">
					否 <br/> 原因：${examroom.hasInvigilatorSeason}
					</s:elseif></td>
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
					<th style="width:20px;"><img src="<ui:img url="images/title_left.gif"/>" /></th>
					<th style="text-align:left; font-weight:bold;">弘成学苑</th>
				</tr>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td>
						<table class="gv_table_2" border="0" cellpadding="2" cellspacing="2">
							<tr><td colspan="2" 
							style="background-color:#F3F3F3;text-align:center;font-weight:bold;">学习中心</td></tr>
							<tr><td width="75">中心名称：</td><td></td></tr>
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
							<tr><td width="75">创建日期：</td><td>${examroom.createdTime}</td></tr>
							<tr><td>更新用户：</td><td>${updateuserinfo.fullName}</td></tr>
							<tr><td>更新日期：</td><td><fmt:formatDate value="${examroom.updatedTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td></tr>
						</table>
					</td>
				</tr>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tr><td><input id="btn1" class="btn_gray_130" type="button" onclick="show('createclassroom','添加教室信息',600,220);" value="添加教室" /></td></tr>
			</table>
			</div>
			
			<!--Right End-->		
			
  		</div>
  	</div>
  </div>
  <!-- 添加教室-->
		<div id="createclassroom" style="display:none;">
    	<table class="add_table">
            <tr>
            	<th class="lable_100">所在的考场：</th>
                <td><s:select list="%{examroomlist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="examroomId" id="examroomId" cssClass="txt_box_130"/>
				</td>
            </tr>
			<tr>
			    <td class="lable_100">教室名称：</td>

				<td><input type="text" name="name" id="name" class="txt_box_150"/></td>
			</tr>
			<tr>
			    <td class="lable_100">教室容量：</td>
				<td><input type="text" name="capacity" id="capacity" class="txt_box_150" value=""/>&nbsp;&nbsp;【按教室的间隔计算】</td>
			</tr>
			<tr>
			    <td class="lable_100">费用状况：</td>

				<td><input id="feeStandard" name="feeStandard" class="txt_box_150"/>&nbsp;元 &nbsp;/ &nbsp;
					<input type="radio" name="feeType" class="feeType" checked="checked" value="0"/>场 &nbsp;&nbsp;
					<input type="radio" name="feeType" class="feeType" value="1"/> 天
				</td>
			</tr>
            <tr>
                <td colspan="2" align="center">
                	<input type="button" class="btn_black_61" name="save" onclick="addclass();" value="添加"  />
                	<input type="button" class="btn_black_61" value="关闭" onclick="closes('createclassroom');" />
                </td>
            </tr>
        </table>
</div></body>
</html>

