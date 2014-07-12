<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>院校列表</title>
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
		
		<script type="text/javascript">
		var ids=0;
		
		//删除联系人
		function ajax_delete(data)
		{
			show('delDiv','提示',250,150);
			closes('isdeleteDiv');
			window.location.reload();
		}
		
		
		//修改联系人
		function ajax_update(data)
		{
			show('updateDiv','提示',250,150);
			closes('linkman_save_dialog');
			window.location.reload();
		}
		
		
		</script>
		
		<a:ajax parameters="{'linkmanId':ids};{'linkmanId':ids,'academylinkman.name':$('#lmname').val(),'academylinkman.position':$('#lmposition').val(),'academylinkman.duty':$('#lmduty').val(),'academylinkman.telephone':$('#lmtelephone').val(),'academylinkman.mobile':$('#lmmobile').val(),'academylinkman.email':$('#lmemail').val()}"
				successCallbackFunctions="ajax_delete;ajax_update"
				urls="/academy/deleteacademylinkman;/academy/updateacademylinkman"
		 pluginCode="123"
		  />
		
		
		
		
		
		
		
		<script type="text/javascript">
		
		
		/*
		删除院校联系人
		*/
		function deletelinkman(id)
		{
			ids=id;
			show('isdeleteDiv','提示',250,150);
		}
		
		/*
		取消删除
		*/
		function cdiv()
		{
			closes('isdeleteDiv');
		}		
		
		
		/*
		弹出修改联系人
		*/
		function updatelinkman(id,name,position,duty,telephone,mobile,email)
		{
			
		 	var ui = jQuery('#linkman_save_dialog');
			jQuery('form', ui)[0].reset();
			ids=id;
			$('#lmname').val(name);
			$('#lmposition').val(position);
			$('#lmduty').val(duty);
			$('#lmtelephone').val(telephone);
			$('#lmmobile').val(mobile);
			$('#lmemail').val(email);
			jQuery('#save_submit')[0].onclick=updatelinkmans;
			show('linkman_save_dialog','新增沟通记录',450,350);
		}
	
	
		function updatelinkmans()
		{
			
			var lmname=$('#lmname').val();
			var lmmobile=$('#lmmobile').val();
			
			
			if($.trim(lmname)=="")
			{
				show('nameDiv','提示',250,150);
				return ;
			}
			if($.trim(lmmobile)=="")
			{
				show('mobDiv','提示',250,150);
				return ;
			}
			ajax_123_2();
		}
		
	
		
		
		</script>
	
  </head>
  
  <body>
		<!--头部层开始 -->
		<head:head title="院校名称：${academy.name}">
	
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<!--Menu Begin-->
			<%@ include file="_tab_title/academy_tab.jsp" %>
			<!--Menu End-->
			
			<!--Left Begin-->
			<div style="float:left; width:800px;">
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">基本信息</th>
				 	<th style="text-align:right;"><a href="<uu:url url="/academy/update_academy_logo"/>?id=${param.id}" target="_blank">设置院校LoGo</a></th>
				</tr>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				
				<tr>
					<td class="lable_100">LoGo：</td>
					<td><img src="<ui:img url="${academy.pictureUrl}" />" height="50px" border="0" /></td>
				</tr>
				<tr>
					<td class="lable_100">院校名：</td>
					<td>${academy.name}</td>
				</tr>
				<tr>
					<td class="lable_100">院校简称：</td>
					<td>${academy.shortName}</td>
				</tr>
				<tr>
					<td class="lable_100">成立年度：</td>
					<td>${academy.foundedYear}</td>
				</tr>
				<tr>
					<td class="lable_100">电话：</td>
					<td>${academy.telephone}</td>
				</tr>
				<tr>
					<td class="lable_100">网址：</td>
					<td><a href="${academy.website}" target="_blank">${academy.website}</a></td>
				</tr>		  
				<tr>
					<td class="lable_100">院校规模：</td>
					<td>
					<s:if test="academy.scale==1" >
					1000人以下
					</s:if>
					<s:elseif test="academy.scale==2">
					1000-3000人
					</s:elseif>
					<s:elseif test="academy.scale==3">
					3000-5000人
					</s:elseif>
					<s:else>
					5000人以上
					</s:else>
					</td>
				</tr>
				<tr>
					<td class="lable_100">院校简介：</td>
					<td>${academy.introduction}</td>
				</tr>
			</table>
			<table class="gv_table_2">
				<tr>
						<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif"/>" /></th>
						<th style="text-align:left; font-weight:bold; width:50px;">联系人</th>
						<th style="text-align:right;"><a href="<uu:url url="/academy/add_academy_link_man" />?id=${param.id}" target="_blank"><img class="img_icon" src="<ui:img url="images/icon_add.gif" />" align="absmiddle"/>新增院校联系人</a></th>
					</tr>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<th width="15%">姓名</th>
					<th width="15%">职务</th>
					<th width="15%">职责</th>
					<th width="15%">座机</th>
					<th width="15%">手机</th>
					<th width="15%">邮箱</th>
					<th width="15%">操作</th>
					
				</tr>
				<s:iterator var="alm" value="academylinkman">
				<tr>
					<td width="15%"><s:property value="#alm.name" /></td>
					<td width="15%"><s:property value="#alm.position" /></td>
					<td width="15%"><s:property value="#alm.duty" /></td>
					<td width="15%"><s:property value="#alm.telephone" /></td>
					<td width="15%"><s:property value="#alm.mobile" /></td>
					<td width="15%"><a href="mailto:<s:property value="#alm.email" />"><s:property value="#alm.email" /></a></td>
					<td width="15%"><a href="javascript:void(0)" onclick="updatelinkman(<s:property value="#alm.id" />,'<s:property value="#alm.name" />','<s:property value="#alm.position" />','<s:property value="#alm.duty" />','<s:property value="#alm.telephone" />','<s:property value="#alm.mobile" />','<s:property value="#alm.email" />')"  >修改</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="deletelinkman(<s:property value="#alm.id" />)"  >删除</a></td>
				</tr>
				</s:iterator>
				
				
				
			</table>
			</div>
			<!--Left End-->
			
			<!--Line Begin-->
			<div style="float:left;width:4px; height:500px; background-color:#3394C1; margin-left:2px; margin-right:2px;">
			</div>
			<!--Line End-->
			
			<!--Right Begin-->
			<div style="margin-left:808px;">
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif"/>" /></th>
					<th style="text-align:left; font-weight:bold;">弘成学苑</th>
				</tr>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td>
						<table class="gv_table_2" border="0" cellpadding="2" cellspacing="2">
							<tr><td colspan="2" 
							style="background-color:#F3F3F3;text-align:center;font-weight:bold;">执行团队</td></tr>
							<tr><td width="75">项目经理：</td><td>${userinfo.fullName}</td></tr>
							<tr><td>电话：</td><td>${userinfo.telephone}</td></tr>
							<tr><td>邮件：</td><td><a href="mailto:${userinfo.email}">${userinfo.email}</a></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="gv_table_2" border="0" cellpadding="2" cellspacing="2">
							<tr><td colspan="2" 
							style="background-color:#F3F3F3;text-align:center;font-weight:bold;">维护信息</td></tr>
							<tr><td width="75">创建日期：</td><td><fmt:formatDate value="${academy.createdTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td></tr>
							<tr><td>更新用户：</td><td>${updateuserinfo.fullName}</td></tr>
							<tr><td>更新日期：</td><td><fmt:formatDate value="${academy.updatedTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td></tr>
						</table>
					</td>
				</tr>
			</table>
			
			</div>
			<!--Right End-->		
			
			
			<div id="linkman_save_dialog" style="display:none">
			<form>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				
				<tr>
					<td class="lable_110">姓名：</td>
					<td><input type="text" id="lmname" name="lmname" class="txt_box_100"/></td>
				</tr>
				
				<tr>
					<td class="lable_110">职务：</td>
					<td><input type="text" id="lmposition" name="lmposition" class="txt_box_100"/></td>
				</tr>
				
				<tr>
					<td class="lable_110">职责：</td>
					<td><input type="text" id="lmduty" name="lmduty" class="txt_box_100"/></td>
				</tr>
				
				<tr>
					<td class="lable_110">电话：</td>
					<td><input type="text" id="lmtelephone" name="lmtelephone" class="txt_box_100"/></td>
				</tr>
				
				<tr>
					<td class="lable_110">手机：</td>
					<td><input type="text" id="lmmobile" name="lmmobile" class="txt_box_100"/></td>
				</tr>
				
				<tr>
					<td class="lable_110">邮箱：</td>
					<td><input type="text" id="lmemail" name="lmemail" class="txt_box_100"/></td>
				</tr>
				
				
				
				<tr>
					<td colspan="2" style="padding-top:7px !important; text-align:center !important;"  class="lable_110"><input type="button" value="提交" id="save_submit" class="btn_black_61"/></td>
				</tr>
			</table>
			<input type="hidden" id="comment_code"/>
			<input type="hidden" id="academy_code" code="A009882993"/>
			</form>
		</div>
			
			
		<div id="isdeleteDiv" style="display:none">
		<div style="float:inherit">确定要删除吗？</div><br/>
		<input type="button" value="确定" id="btnok" name="btnok" onclick="ajax_123_1()" class="btn_black_61"/> 
		<input type="button" value="取消" id="tt" name="tt" onclick="cdiv()" class="btn_black_61"/> 
		
		</div>
			
		<div id="updateDiv" style="display:none">
			修改成功！！
		</div>	
			
			
		<div id="delDiv" style="display:none">
			删除成功！！
		</div>	
		
		
		<div id="nameDiv" style="display:none">
			姓名不能为空！！
		</div>	
		
		
		<div id="mobDiv" style="display:none">
			手机不能为空！！
		</div>		
			
	
  		</body:body>
  </body>
</html>
