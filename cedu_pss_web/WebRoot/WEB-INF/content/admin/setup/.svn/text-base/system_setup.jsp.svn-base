<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>系统设置</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 表单验证 -->
		<jc:plugin name="validator" />
		<jc:plugin name="loading" />
		<script type="text/javascript">
			function successCallbackFunction(data){
				var res=data.result.split(",");
				if(res[0]=="true"){
					$("#m_s_span").css({"display":""});
					$("#m_e_span").css({"display":"none"});
				}else{
					$("#m_s_span").css({"display":"none"});
					$("#m_e_span").css({"display":""});
				}
				if(res[1]=="true"){
					$("#s_s_span").css({"display":""});
					$("#s_e_span").css({"display":"none"});
				}else{
					$("#s_s_span").css({"display":"none"});
					$("#s_e_span").css({"display":""});
				}
				if(res[2]=="true"){
					$("#mem_s_span").css({"display":""});
					$("#mem_e_span").css({"display":"none"});
				}else{
					$("#mem_s_span").css({"display":"none"});
					$("#mem_e_span").css({"display":""});
				}
				
			}
			function reloadCallback(data){
				alert("OK");
			}
			function xiufuCallback(data){
				alert("修复成功!");
			}
			function xiufuJiaofeidanCallback(data){
				alert("修复成功!");
			}
			function xiufuSexCallback(data){
				alert("修复成功!");
			}
			
			function xiufufpdallaccountCallback(data){
				alert("修复成功!");
			}
			
			function xiufufpdallmoneyCallback(data){
				alert("修复成功!");
			}
			
			function xiuFuStuAccDanChunCallBack(data){
				alert("修复成功!");
			}
			
			function xiuFufpdposebankacademyChunCallBack(data){
				alert("修复成功!");
			}
			
			function xiuFuxufufpdiscountamountmoneyCallBack(data){
				alert("修复成功!");
			}
			
		</script>
		<a:ajax parameters="$('#sys_form').serializeObject()" successCallbackFunctions="successCallbackFunction" pluginCode="sys" urls="/admin/setup/check_connection"/>
		
		<a:ajax successCallbackFunctions="reloadCallback" pluginCode="reload" urls="/admin/setup/reload_project"/>
		
		<a:ajax successCallbackFunctions="xiufuCallback" pluginCode="xiufu" urls="/admin/setup/repair_professional_students"/>
		
		<a:ajax successCallbackFunctions="xiufuJiaofeidanCallback" pluginCode="jiaofei" urls="/admin/setup/repair_fee_payment_common_batch"/>
		
		<a:ajax successCallbackFunctions="xiufuSexCallback" pluginCode="xingbie" urls="/admin/setup/repair_students_sex"/>
		
		<a:ajax successCallbackFunctions="xiufuSexCallback" pluginCode="genjinren" urls="/admin/setup/repair_students_user_id"/>
		
		
		<a:ajax successCallbackFunctions="xiufufpdallaccountCallback" pluginCode="fpdallaccount" urls="/admin/setup/repair_fee_payment_detail_all_account_ajax"/>
		
		<a:ajax successCallbackFunctions="xiufufpdallmoneyCallback" pluginCode="fpdallmoney" urls="/admin/setup/repair_fee_payment_total_recharge_account_ajax"/>
		
		<a:ajax successCallbackFunctions="xiuFuStuAccDanChunCallBack" pluginCode="stuaccdanchun" urls="/admin/setup/repair_student_account_dan_chun_chong_zhi_ajax"/>
		
		<a:ajax successCallbackFunctions="xiuFufpdposebankacademyChunCallBack" pluginCode="fpdposebankacademy" urls="/admin/setup/revert_fpd_status_account_for_pos_ebank_academy_ajax"/>
		
		<a:ajax successCallbackFunctions="xiuFuxufufpdiscountamountmoneyCallBack" pluginCode="fpdiscountamountmoney" urls="/admin/setup/repair_fee_payment_total_discount_account_ajax"/>
		
		
		<script type="text/javascript">
			$(document).ready(function(){
				$.formValidator.initConfig({formID:"sys_form",submitOnce:false,debug:true,
					onError:function(msg,obj,errorlist){
						alert(msg);
					},
					onSuccess:function(){
						ajax_sys_1();
						return false;
					},
					submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
				});
				$("#M_URL").formValidator({onShow:"请输入主库地址",onFocus:"如:127.0.0.1:3306/cedu",onCorrect:"验证通过"}).inputValidator({min:5,onError:"验证不通过"}).defaultPassed();
				$("#M_USER").formValidator({onShow:"请输入主库用户名",onFocus:"请输入用户名，最少2位！",onCorrect:"验证通过"}).inputValidator({min:2,onError:"验证不通过"}).defaultPassed();
				$("#M_PASS").formValidator({onShow:"请输入主库密码",onFocus:"请输入密码，最少5位！",onCorrect:"验证通过"}).inputValidator({min:5,onError:"验证不通过"}).defaultPassed();
				$("#S_URL").formValidator({onShow:"请输入从库地址",onFocus:"如:127.0.0.1:3306/cedu",onCorrect:"验证通过"}).inputValidator({min:5,onError:"验证不通过"}).defaultPassed();
				$("#S_USER").formValidator({onShow:"请输入从库用户名",onFocus:"请输入用户名，最少2位！",onCorrect:"验证通过"}).inputValidator({min:2,onError:"验证不通过"}).defaultPassed();
				$("#S_PASS").formValidator({onShow:"请输入从库密码",onFocus:"请输入密码，最少5位！",onCorrect:"验证通过"}).inputValidator({min:5,onError:"验证不通过"}).defaultPassed();
				$("#MEMCACHED_URL").formValidator({onShow:"请输入Memcache连接地址",onFocus:"如:127.0.0.1:11211",onCorrect:"验证通过"}).inputValidator({min:5,onError:"验证不通过"}).defaultPassed();
			});
			// 重启tomcat
			function rerloadProject(){
				if(confirm("确认重启服务吗？10秒之后跳转到登录页面！")){
					ajax_reload_1();
					//显示加载图表
					showAjaxLoad();
					setTimeout('rLoad()','10000');
				}
			}
			//修复学生
			function xiufuStudent(){
				if(confirm("确定要修复学生专业信息？")){
					ajax_xiufu_1();
				}
				
			}
			//修复学生跟进人
			function xufuxueshenggenjinren(){
				if(confirm("修复学生跟进人？")){
					ajax_genjinren_1();
				}
			}
			//修复缴费全局批次
			function xiufuJiaofeidan(){
				if(confirm("确定要修复缴费单信息？")){
					ajax_jiaofei_1();
				}
			}
			//修复学生性别
			function xiufuSex(){
				if(confirm("确定要修复学生性别？")){
					ajax_xingbie_1();
				}
			}
			//修复缴费单明细各个账户值（只能是完成缴费还没进行其他缴费流程才能修复）
			function xiufufpdallaccount(){
				if(confirm("确定修复缴费单明细各个账户值？")){
					ajax_fpdallaccount_1();
				}
			}
			//修复缴费单充值金额和总金额（明细的使用充值金额，在缴费单里的充值金额应为负数
			function xufufpdallmoney(){
				if(confirm("确定修复缴费单充值金额和总金额？")){
					ajax_fpdallmoney_1();
				}
			}
			//修复学生账户单纯充值情况（单纯充值都生成预缴费单）
			function xufustuaccdanchun(){
				if(confirm("确定修复学生账户单纯充值，生成预缴费单么？")){
					ajax_stuaccdanchun_1();
				}
			}
			//修复学生pos、网银院校时由于需求变更  前期历史数据倒退一个状态
			function xufufpdposebankacademy()
			{
				alert("已经操作过，现在不能操作。");
				//if(confirm("确定还原pos、网银院校历史数据？已经操作过，现在不能操作。")){
				//	ajax_fpdposebankacademy_1();
				//}
			}
			//修复缴费单优惠金额、满足条件是总金额+优惠金额=缴费金额+充值金额
			function xufufpdiscountamountmoney()
			{
				if(confirm("确定修复学生缴费单充值金额么？")){
					ajax_fpdiscountamountmoney_1();
				}
			}
			
			
			function rLoad(){
				parent.location.href='<s:url value="/template/login" />';
			}
		</script>
	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="系统设置">
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<form id="sys_form" name="sys_form">
				<fieldset>
				<table class="gv_table_2" >
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif"/>" /></th>
					 	<th style="text-align:left; font-weight:bold;">MySql主库连接信息<!--<input type="checkbox" disabled="disabled" checked="checked"/></th>-->
					</tr>
				</table>
				 <table class="add_table" border="0">	
				 	
				 	<tr>
						<td width="200px" align="right">主库地址：</td>
						<td width="330px" align="left">
							<input type="text" class="txt_box_300" id="M_URL" name="M_URL" value="${requestScope.M_URL}"/>
						</td>
						<td align="left">
							<div id="M_URLTip" width="200px;"></div>
						</td>
					</tr>
					<tr>
						<td width="200px" align="right">主库用户名：</td>
						<td align="left"><input type="text" class="txt_box_150" id="M_USER"  name="M_USER" value="${requestScope.M_USER}"/></td>
						<td align="left">
							<div id="M_USERTip" width="200px;"></div>
						</td>
					</tr>
					<tr>
						<td width="200px" align="right">主库密码：</td>
						<td align="left"><input type="password" class="txt_box_150" id="M_PASS" name="M_PASS" value="${requestScope.M_PASS}"/></td>
						<td align="left">
							<div id="M_PASSTip" width="200px;"></div>
						</td>
					</tr>
					<tr>
						<td width="200px" align="right"></td>
						<td align="left">
							&nbsp;
							<span id="m_s_span" style="color: green;display: none;">连接成功！</span><span id="m_e_span" style="color: color;display: none;">连接失败！</span>
						</td>
					</tr>
					
				</table>
				<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif"/>" /></th>
					 	<th style="text-align:left; font-weight:bold;">MySql从库连接信息<!--<input type="checkbox" checked="checked"/></th>-->
						
					</tr>
				</table>
				<table class="add_table" border="0">	
				 	
				 	<tr>
						<td width="200px" align="right">从库地址：</td>
						<td width="330px" align="left"><input type="text" class="txt_box_300" name="S_URL" id="S_URL" value="${requestScope.S_URL}"/></td>
						<td align="left">
							<div id="S_URLTip" width="200px;"></div>
						</td>
					</tr>
					<tr>
						<td width="200px" align="right">从库用户名：</td>
						<td align="left"><input type="text" class="txt_box_150" id="S_USER" name="S_USER" value="${requestScope.S_USER}"/></td>
						<td align="left">
							<div id="S_USERTip" width="200px;"></div>
						</td>
					</tr>
					<tr>
						<td width="200px" align="right">从库密码：</td>
						<td align="left"><input type="password" class="txt_box_150" id="S_PASS" name="S_PASS" value="${requestScope.S_PASS}"/></td>
						<td align="left">
							<div id="S_PASSTip" width="200px;"></div>
						</td>
					</tr>
					<tr>
						<td width="200px" align="right"></td>
						<td align="left">
							&nbsp;
							<span id="s_s_span" style="color: green;display: none;">连接成功！</span><span id="s_e_span" style="color: color;display: none;">连接失败！</span>
						</td>
					</tr>
				</table>
				<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif"/>" /></th>
					 	<th style="text-align:left; font-weight:bold;">Memcache连接信息<!--<input type="checkbox" checked="checked"/></th>-->
						
					</tr>
				</table>
				<table class="add_table" border="0">	
				 	<tr>
						<td width="200px" align="right">连接地址：</td>
						<td  width="330px" align="left"><input type="text" class="txt_box_300" id="MEMCACHED_URL" name="MEMCACHED_URL" value="${requestScope.MEMCACHED_URL}"/></td>
						<td align="left">
							<div id="MEMCACHED_URLTip" width="200px;"></div>
						</td>
					</tr>
					<tr>
						<td width="200px" align="right"></td>
						<td align="left">
							&nbsp;
							<span id="mem_s_span" style="color: green;display: none;">连接成功！</span><span id="mem_e_span" style="color: color;display: none;">连接失败！</span>
						</td>
					</tr>
				</table>
				<center>
				<input class="btn_black_61" type="submit" id="save" onclick=""  value="保存" />
				&nbsp;&nbsp;<input class="btn_black_61" type="button" id="reload" onclick="rerloadProject();"  value="重启服务" />
				&nbsp;&nbsp;<input class="btn_black_130" type="button" onclick="xiufuStudent();"  value="修复学生院校专业" />
				
				&nbsp;&nbsp;<input class="btn_black_130" type="button" onclick="xiufuJiaofeidan();"  value="修复缴费单全局批次" />
				&nbsp;&nbsp;<input class="btn_black_130" type="button" onclick="xiufuSex();"  value="修复学生性别" />
				&nbsp;&nbsp;<input class="btn_black_130" type="button" onclick="xiufufpdallaccount();"  value="修复缴费单账户金额" />
				&nbsp;&nbsp;<input class="btn_black_130" type="button" onclick="xufuxueshenggenjinren();"  value="修复学生跟进人" />
				&nbsp;&nbsp;<input class="btn_black_130" type="button" onclick="xufufpdallmoney();"  value="修复缴费单总金额" />
				</center>
				<br/>
				<center>
					<input class="btn_black_130" type="button" onclick="xufustuaccdanchun();"  value="修复学生单纯充值" />
					&nbsp;&nbsp;<input class="btn_black_130" type="button" onclick="xufufpdposebankacademy();"  value="还原pos网银院校数据" />
					&nbsp;&nbsp;<input class="btn_black_130" type="button" onclick="xufufpdiscountamountmoney();"  value="修复缴费单优惠金额" />
				</center>
			  </fieldset>
			 </form>
		</body:body>
	</body>

</html>