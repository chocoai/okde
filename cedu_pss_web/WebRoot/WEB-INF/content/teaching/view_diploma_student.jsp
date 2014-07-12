<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>学生详情</title>
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
		
			$(document).ready(function(){
			//性别
			if(${student.gender}==0)
			{$('#sexSp').html('女');}
			else if(${student.gender}==1)
			{$('#sexSp').html('男');}
			else
			{$('#sexSp').html('未知');}
			//证件类型
			if(${student.certType}==CERTIFICATE_TYPE_ID)
			{$('#zhengjianSp').html('身份证');}
			else if(${student.certType}==CERTIFICATE_TYPE_DRIVER_ID)
			{$('#zhengjianSp').html('驾照');}
			else if(${student.certType}==CERTIFICATE_TYPE_NCO_ID)
			{$('#zhengjianSp').html('士官证');}
			else
			{$('#zhengjianSp').html('未知');}
			
			
			if('${isback}'=='true')
			{
			  alert('上传成功！');
			}
		})
		function shangchuanimg()
		{
			if(jQuery("#img").val()=="")
			{
				alert("请选择图片!");
			}
			else
			{
				jQuery("#myform").submit();
			}
		}
		
		</script>
		
  </head>
  
  <body>
		<!--头部层开始 -->
		<head:head title="学生详情">
	
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<!--Menu Begin-->
			<%@ include file="_tab_title/diploma_student_tab.jsp" %>
			<!--Menu End-->
			
			<!--Left Begin-->
			<div style="float:left; width:800px;">
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">基本信息</th>
				</tr>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				
				<tr>
					<td class="lable_100">姓名：</td>
					<td>
					<c:if test="${student.name!=null&&student.name!=''}">
											<span style="color:black;">${student.name}</span>
					</c:if>
					<c:if test="${student.name==null||student.name==''}">
											<span style="color:black;">无</span>
					</c:if>
					</td>
					<td class="lable_100">性别：</td>
					<td>
					<span style="color:black;" id="sexSp" ></span>
					</td>
					<td class="lable_100">民族：</td>
					<td><span style="color:black;">${studentSlave.ethnicGroup}</span></td>
				</tr>
				
				<tr>
					<td class="lable_100">别名：</td>
					<td><c:if test="${studentSlave.alias!=null&&studentSlave.alias!=''}">
											<span style="color:black;">${studentSlave.alias}</span>
					</c:if>
					<c:if test="${studentSlave.alias==null||studentSlave.alias==''}">
											<span style="color:black;">无</span>
					</c:if></td>
					<td class="lable_100">笔名：</td>
					<td><c:if test="${studentSlave.penName!=null&&studentSlave.penName!=''}">
											<span style="color:black;">${studentSlave.penName}</span>
					</c:if>
					<c:if test="${studentSlave.penName==null||studentSlave.penName==''}">
											<span style="color:black;">无</span>
					</c:if></td>
					<td class="lable_100">曾用名：</td>
					<td><c:if test="${studentSlave.formerName!=null&&studentSlave.formerName!=''}">
						<span style="color:black;">${studentSlave.formerName}</span>
					</c:if>
					<c:if test="${studentSlave.formerName==null||studentSlave.formerName==''}">
					<span style="color:black;">无</span>
					</c:if></td>
				</tr>		  
				<tr>
					<td class="lable_100">出生地：</td>
					<td>
		               <c:if test="${studentSlave.homeplace!=null&&studentSlave.homeplace!=''}">
											<span style="color:black;">${studentSlave.homeplace}</span>
					</c:if>
					<c:if test="${studentSlave.homeplace==null||studentSlave.homeplace==''}">
											<span style="color:black;">无</span>
					</c:if>
					</td>
					<td class="lable_100">政治面貌：</td>
					<td>${studentSlave.politicalStatus}</td>
					<td class="lable_100">现居地:</td>
					<td><span style="color:black;">${student.address}</span></td>
				</tr>
				<tr>
					<td class="lable_100">证件类型：</td>
					<td><span style="color:black;" id="zhengjianSp"></span></td>
					<td class="lable_100">证件号码：</td>
					<td>${student.certNo}</td>
					<td class="lable_100">电话号码:</td>
					<td><span style="color:black;" >${student.mobile}</span></td>
				</tr>
				<tr>
					<td class="lable_100">招生途径：</td>
					<td><span style="color:black;" id="source" >${student.enrollmentSourceName}</span></td>
					<td class="lable_100">学历：</td>
					<td>${student.degree}</td>
					<td class="lable_100">招生来源:</td>
					<td><span style="color:black;" id="source" >${student.studentDataSourceName}</span></td>
				</tr>
				<tr>
					<td class="lable_100">邮箱：</td>
					<td>${student.email}</td>
					<td class="lable_100">邮编：</td>
					<td>${student.zipcode}</td>
					<td class="lable_100" >座机:</td>
					<td><span style="color:black;" id="source" >${student.phone}</span></td>
				</tr>
			</table>
			<table class="gv_table_2">
				<tr>
						<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif"/>" /></th>
						<th style="text-align:left; font-weight:bold; width:50px;">补充信息</th>
					</tr>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td class="lable_100">高考号：</td>
					<td><c:if test="${studentSlave.ueeNo!=null&&studentSlave.ueeNo!=''}">
											<span style="color:black;">${studentSlave.ueeNo}</span>
					</c:if>
					<c:if test="${studentSlave.ueeNo==null||studentSlave.ueeNo==''}">
											<span style="color:black;">无</span>
					</c:if></td>
					<td class="lable_100">入学时间：</td>
					<td>${studentSlave.admissionTime}</td>
					<td>入学方式:</td>
					<td>${studentSlave.admissionWay}</td>
				</tr>
				<tr>
					<td class="lable_100">意向：</td>
					<td><c:if test="${student.preferAcademy!=null&&student.preferAcademy!=''}">
											<span style="color:black;">${student.preferAcademy}</span>
					</c:if>
					<c:if test="${student.preferAcademy==null||student.preferAcademy==''}">
					<span style="color:black;">无</span>
					</c:if></td>
					<td class="lable_100">状态：</td>
					<td>${student.status}</td>
					<td>培训方式:</td>
					<td><c:if test="${studentSlave.trainingForm!=null&&studentSlave.trainingForm!=''}">
					<span style="color:black;">${studentSlave.trainingForm}</span>
					</c:if>
					<c:if test="${studentSlave.trainingForm==null||studentSlave.trainingForm==''}">
					<span style="color:black;">无</span>
					</c:if></td>
				</tr>
			</table>
			<table class="gv_table_2">
				<tr>
						<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif"/>" /></th>
						<th style="text-align:left; font-weight:bold; width:50px;">其他信息</th>
				</tr>
			</table>
			<table  class="add_table" border="0" cellpadding="2" cellspacing="2">
			<tr>
				<td class="lable_100">备注信息：</td>
				<td><c:if test="${student.remark!=null&&student.remark!=''}">
											<span style="color:black;">${student.remark}</span>
					</c:if>
					<c:if test="${student.remark==null||student.remark==''}">
											<span style="color:black;">无</span>
					</c:if></td>
	
				</tr>
			</table>
				<input class="btn_black_61" type="button"  onclick="window.location.href='list_diploma';" value="返回" />
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
					<th style="text-align:left; font-weight:bold;">学籍信息</th>
				</tr>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td class="lable_100">学习中心：</td>
					<td><span style="color:black;" id="branchSp">${student.branchName }</span></td>
				</tr>
				<tr>
					<td class="lable_100">院校：</td>
					<td><span style="color:black;" id="academyIdSp">${student.schoolName }</span></td>
				</tr>
				<tr>
					<td class="lable_100">学号：</td>
					<td><span style="color:black;" id="levelIdSp">${student.number }</span></td>
				</tr>
				<tr>
					<td class="lable_100">层次：</td>
					<td><span style="color:black;" id="levelIdSp">${student.levelName }</span></td>
				</tr>
				<tr>
					<td class="lable_100">批次：</td>
					<td><span id="batchName" name="batch" style="color: black">${student.enrollmentBatchName }</span></td>
				</tr>
				<tr>
					<td class="lable_100">专业：</td>
					<td><span style="color:black;" id="majorIdSp">${student.majorName }</span></td>
				</tr>
			</table>
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif"/>" /></th>
					<th style="text-align:left; font-weight:bold;">添加毕业证书</th>
				</tr>
			</table>
			<form  action="add_diploma_img" id="myform" method="post" enctype="multipart/form-data">
			
			<input type="hidden"   name="id" value="${id}" />	
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td class="lable_100">毕业证书：</td>
					<td>
					
					<c:if test="${diploma!=null}">
						<input name="img" id="img" type="file" />
						<input class="btn_black_61"  type="button" value="上传" onclick="shangchuanimg()" id="uploadpicture" />
					</c:if>
					<c:if test="${diploma==null}">
						<div>对不起，你的证书还没颁发呢！</div>
					</c:if></td>
					
				</tr>
			</table>
			</form>
			</div>
			<!--Right End-->		
			
  		</body:body>
  </body>
</html>
