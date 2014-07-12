<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<html>
		<title>历史数据导入</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!--  分页 -->
		<jc:plugin name="page" />
		
		<jc:plugin name="loading" />
		
		<jc:plugin name="ajax_upload" />		
		
		<script type="text/javascript">
			//学生信息导入ID
			var studentImportRecord_id=-1;
			//学生信息导入状态
			var studentImportRecord_status=-1;

			
			var sex=-1;
			
			var code="";
			$(function(){
				$('#create1').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'上传公服数据',
					width: 800,
					height: 270,
					buttons: {
						'保存': function() { 
							code="1";
							
							if($("#file1").val()==null||$("#file1").val()==""){
								alert("请选择上传文件");
								return false;
							}
							if($("#file1").val().indexOf(".xls")==-1){
								alert("请上传.xls文件");
								return false;
							}
							if(code!=null&&code!=""){
								ajaxFileUpload(code);
							}
						}, 
						'取消': function() { 
							$(this).dialog("close"); 
						} 
					}
				});
				
				$('#create2').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'上传预报名数据',
					width: 800,
					height: 270,
					buttons: {
						'保存': function() { 
							code="2";
							
							if($("#file2").val()==null||$("#file2").val()==""){
								alert("请选择上传文件");
								return false;
							}
							if($("#file2").val().indexOf(".xls")==-1){
								alert("请上传.xls文件");
								return false;
							}
							if(code!=null&&code!=""){
								ajaxFileUpload(code);
							}
						}, 
						'取消': function() { 
							$(this).dialog("close"); 
						} 
					}
				});				
			});
			
			function createStudentImportRecord1(){
				$('#create1').dialog('open');
			}
			
			function createStudentImportRecord2(){
				$('#create2').dialog('open');
			}
			
			function duibi(){
				$('#create2').dialog('open');
			}
			
			//上传excel
			function ajaxFileUpload(id)
		    {
		        $.ajaxFileUpload
		        (
		            {
		                url:'<uu:url url="/persontool/stu_duibi_upload" />?id='+id,//用于文件上传的服务器端请求地址
		                secureuri:false,//一般设置为false
		                fileElementId:'file'+id,//文件上传空间的id属性  <input type="file" id="file" name="file" />
		                dataType: 'json',//返回值类型 一般设置为json
		                success: function (data, status)  //服务器成功响应处理函数
		                {
		                    alert(data.message);//从服务器返回的json中取出message中的数据,其中message为在struts2中action中定义的成员变量
		                    
		                    if(typeof(data.error) != 'undefined')
		                    {
		                        if(data.error != '')
		                        {
		                            alert(data.error);
		                        }else
		                        {
		                            alert(data.message);
		                        }
		                    }
		                    if(data.id==1)
		                    {
		                    	$('#create1').dialog("close");
		                    	$('#filePath1').val(data.path);
		                    }
		                    if(data.id==2)
		                    {
		                    	$('#create2').dialog("close");
		                    	$('#filePath2').val(data.path);
		                    }
		                },
		                error: function (data, status, e)//服务器响应失败处理函数
		                {
		                	$('#create').dialog("close");
		                    alert(e);		                    
		                }
		            }
		        )
		        
		        return false;

		    }	
		    
		    function create_studentImportRecord_callback(data)
		    {
		    	alert("对比结束");
		    }
		    
		    function exportresult_callback(data)
		    {
		    	alert("导出结束");
		    }		
		    
		    function duibiresult_callback(data)
		    {
		    	var lists="";
		    	$(data.lst).each(function(){
		    			lists+="<tr>";
						lists+="<td>"+this.name+"</td>";
						lists+="<td>"+this.shenFengZhengHaoMa+"</td>";
						lists+="<td>"+this.yuanXiaoMingCheng+"</td>";
						lists+="<td>"+this.zhuanYe+"</td>";
						lists+="<td>"+this.cengCi+"</td>";
						lists+="<td>"+this.zhaoShengPiCi+"</td>";
						lists+="<td>"+this.xueShengZhuangTai+"</td>";
						lists+="<td>"+this.jianKongZhuangTai+"</td>";
						lists+="<td>"+this.xueXiZhongXing+"</td>";
						lists+="<td>"+this.shuJuLaiYuan+"</td>";
						lists+="<td>"+this.zhaoShengTuJing+"</td>";
						lists+="<td>"+this.shiChangTuJing+"</td>";
						lists+="<td>"+this.shouJiHaoma+"</td>";
						lists+="<td>"+this.zuoJiHaoMa+"</td>";
						lists+="<td>"+this.genJinRen+"</td>";
						lists+="<td>"+this.yiJiaoNaBaoMingFei+"</td>";
						lists+="<td>"+this.yiJiaoNaCeShiFei+"</td>"	;						
						lists+="<td>"+this.yiJiaoNaXueFei+"</td>";
						lists+="<td>"+this.yiJiaoNaJiaoCaiFei+"</td>";
						lists+="<td>"+this.jianKongJieGuo+"</td>";
						lists+="<td>"+this.huiFangNeiRong+"</td>";
						lists+="<td>"+this.riQi+"</td>";
						lists+="<td>"+this.huJiaoZhongXinFrom+"</td>";
						lists+="</tr>";
					});
		    	jQuery('#listduibiresult > tbody').html(lists);
		    }
			
		</script>
		<a:ajax parameters="$('#duibi_form').serializeObject()" successCallbackFunctions="create_studentImportRecord_callback" pluginCode="0001" urls="/persontool/stu_duibi"/>
		<a:ajax successCallbackFunctions="duibiresult_callback" pluginCode="0002" urls="/persontool/stu_duibi_result"/>
		<a:ajax successCallbackFunctions="exportresult_callback" pluginCode="0003" urls="/persontool/excel_export_cc_student"/>
	</head>
	<body>
	<!--头部层开始 -->
		<head:head title="公服数据与预报名数据对比" il8nName="crm">
			<input type="button" value="上传公服数据" onclick="createStudentImportRecord1();"/>
			<input type="button" value="上传预报名数据" onclick="createStudentImportRecord2();"/>

		</head:head>
		
		<!--主体层开始 -->
		<body:body>
				<div>
					<form id="duibi_form" >
					<table width="100%"class="add_table" border="0" cellpadding="2" cellspacing="0">
						<tr>
							<td class="lable_100">对比条件:</td>
							<td>
								<input type="hidden" id="filePath1" name="filePath1" />
								<input type="hidden" id="filePath2" name="filePath2" />
								<input type="checkbox" id="search1" name="fullname" value="1" />姓名&nbsp;
								<input type="checkbox" id="search2" name="mobile" value="2" />手机号码&nbsp;
								<input type="checkbox" id="search3" name="tel" value="3" />座机号码&nbsp;
								<input type="checkbox" id="search4" name="idcard" value="4" />身份号码&nbsp;
							</td>
							<td>
								<input type="button" value="对比" onclick="ajax_0001_1();"/>
								<input type="button" value="查看对比结果" onclick="ajax_0002_1();"/>
								<input type="button" value="导出对比结果" onclick="ajax_0003_1();"/>
							</td>
						</tr>
					</table>
					</form>
				</div>
					<table class="gv_table_2">
						<tr>
							<th style="width:20px;"><img src="../images/title_menu/title_left.gif" /></th>
							<th style="text-align:left; font-weight:bold;">对比结果：</th>
						</tr>
					</table>
					<table id="listduibiresult" class="gv_table_2" border="0" cellpadding="0" cellspacing="0" width="100%">
						<thead>
						<tr>
							<th>姓名</th>
							<th>身份证号码</th>
							<th>院校名称</th>
							<th>专业</th>
							<th>层次</th>
							<th>招生批次</th>
							<th>学生状态</th>
							<th>监控状态</th>
							<th>学习中心</th>
							<th>数据来源</th>
							<th>招生途径</th>
							<th>市场途径</th>
							<th>手机号码</th>
							<th>座机号码</th>
							<th>当前跟进人</th>
							<th>报名费缴纳金额</th>
							<th>测试费缴纳金额</th>							
							<th>学费缴纳金额</th>
							<th>教材费缴纳金额</th>
							<th>监控结果</th>
							<th>回访内容</th>
							<th>日期</th>
							<th>呼叫中心来源</th>
						</tr>
						</thead>
						<tbody>
						</tbody>
					</table>					
					<div id="create1" style="display:none">
						<form id="create_studentImportRecord_form1">
							<input type="hidden" class="txt_box_200"  name="studentImportRecord.type" value="1"/>
							 <table class="add_table" border=0>
							 	<tr>
							 		<td align="right"><span>*</span>上传公服文件：</td>
							 		<td width="220px">
							 			<input class="txt_box_200" type="file" name="file" id="file1">
							 			
							 		</td>
							 		<td align="left">
										<a href="<ua:attachment url="/upload/persontool_import_xls/temp/gongfu.xls" />">下载模板</a>
									</td>
									
									<td>
							 			<span>注:请上传.xls文件.</span>
							 		</td>
							 	</tr>
							 	
							 </table>
						</form> 
					</div>
					<div id="create2" style="display:none">
						<form id="create_studentImportRecord_form2">
							<input type="hidden" class="txt_box_200"  name="studentImportRecord.type" value="2"/>
							 <table class="add_table" border=0>
							 	
							 	<tr>
							 		<td align="right"><span>*</span>上传预报名文件：</td>
							 		<td width="220px">
							 			<input class="txt_box_200" type="file" name="file" id="file2">
							 			
							 		</td>
							 		<td align="left">
										<a href="<ua:attachment url="/upload/persontool_import_xls/temp/yubaoming.xls" />">下载模板</a>
									</td>
									
									<td>
							 			<span>注:请上传.xls文件.</span>
							 		</td>
							 	</tr>
							 	
							 </table>
						</form> 
					</div>
						
					</body:body>
	</body>

</html>