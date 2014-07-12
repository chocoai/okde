<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>弘成学苑 -- 网络学历教育预报名</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="description" content="弘成学苑是经教育部批准，与远程教育试点高校联合开展现代远程教育公共服务体系建设试点单位，是构建面向全民终身学习的数字化学习支持服务体系。" />
<meta name="keywords" content="弘成学苑, 弘成教育, 专升本, 高起专, 高起本, 专业学历, 本科学历, 南京大学, 北京交通大学, 厦门大学, 东北财经大学, 华南师范大学, 西南交通大学, 重庆大学, 北京语言大学, 福建师范大学, 电子科技大学, 东北师范大学, 吉林大学, 网上农大, 北京外国语大学, 网络学院, 文凭, 远程教育, 网络教育, 中华学习网" />
<link href="http://baoming.xueyuan.chinaedu.net/images/all.css" rel="stylesheet" type="text/css">
<link href="http://baoming.xueyuan.chinaedu.net/images/base.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="http://xueyuan.chinaedu.net/favicon.ico" type="image/x-icon">
<style>
.txtinput{border:#ff9b22 1px solid; color:#7D7D7D;height:16px;line-height:20px; padding:2px;}
</style>


<!--  jquery库 -->
<jc:plugin name="jquery" />
<!--  表单验证 -->
<jc:plugin name="cedu_check_baoming_form" />
<!-- 省市级联 -->
<jc:plugin name="provinces" />
<!-- 加载等待 -->
<jc:plugin name="loading" />



<!--<script language="javascript" src="http://baoming.xueyuan.chinaedu.net/js/CheckSignForm.js"></script>
--><link href="http://baoming.xueyuan.chinaedu.net/images/portal.css" rel="stylesheet" type="text/css">
<script language="javascript" src="http://baoming.xueyuan.chinaedu.net/images/prototype.js"></script>
<script language="javascript" src="http://baoming.xueyuan.chinaedu.net/images/main.htm"></script>
<script language="javascript" src="http://baoming.xueyuan.chinaedu.net/images/ProvinceCombo.js"></script>
<script language="javascript">this.focus();</script>
<script type="text/javascript">
	function successFunctionCallBack(data){
		if(data.exist){
			alert("您的报名信息已经存在，稍后将有客服人员与您联系，谢谢！");
		}else{
			alert("预报名成功，稍后将有客服人员与您联系，谢谢！");
		}
	}
	/**
	 * 把form元素转换为json
	 * @memberOf {TypeName} 
	 * @return {TypeName} 
	 */
	jQuery.fn.serializeObject = function() {
	    var o = {};
	    var a = this.serializeArray();
	    jQuery.each(a, function() {
	        if (o[this.name]) {
	            if (!o[this.name].push) {
	                o[this.name] = [ o[this.name] ];
	            }
	            o[this.name].push(this.value || '');
	        } else {
	        	 
	            o[this.name] = this.value || '';
	           
	        }
	    });
	   
	    return o;
	}
	function all_branch_list_call_back(doc){
			if(doc.branchlist==null||doc.branchlist.length==0){
				return;
			}
			jQuery("#branch").html("");
            jQuery("<option value='" + 0 + "'>选择学习中心</option>").appendTo(jQuery("#branch"));
			jQuery(doc.branchlist).each(function(){
						jQuery("<option value='" + this.id + "'>" + this.name + "</option>").appendTo(jQuery("#branch"));
			});
			
	}
</script>
<script type="text/javascript">

			//加载事件
			jQuery(function(){
				
				jQuery('#branch').change(function(){
					//全局招生批次
					ajax_100_1();				
				});	
				//全局批次级联
				jQuery('#globalBatchId').change(function(){
					ajax_110_1();//院校					
				});			
				//院校相关级联
				jQuery('#academyId').change(function(){
					ajax_140_1();//招生批次						
				});					
				//层次专业级联
				jQuery('#levelId').change(function(){
					ajax_130_1();//专业	
				});	
				
			});
		</script>
<script type="text/javascript">
			var batchId=0;
			//ajax回调函数   全局批次(学习中心)
			function ajax_global_batch(data)
			{		
				jQuery('#globalBatchId').empty();
			    jQuery('#globalBatchId').append('<option value="0">选择批次</option>');
			    if(data.globalBatchList!=null && data.globalBatchList.length>0)
			    {
			    	jQuery.each(data.globalBatchList,function(){	
			    		jQuery('#globalBatchId').append('<option value="'+this.id+'">'+this.batch+'</option>'); 
			    	});
			   	}
			   	ajax_110_1();//院校
			}
			//ajax回调函数   院校(学习中心、全局批次)
			function ajax_academy(data)
			{				
				jQuery('#academyId').empty();
			    jQuery('#academyId').append('<option value="0">选择院校</option>');
			    if(data.academyList!=null && data.academyList.length>0)
			    {
			    	jQuery.each(data.academyList,function(){	
			    		jQuery('#academyId').append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	});
			   	}
			   	jQuery('#batch').html('');
			   	jQuery('#batchId').val(0);
			   	//batchId=parseInt(jQuery('#batchId').val());
			   	ajax_120_1();//层次	
			}
			//ajax回调函数  层次(招生批次)
			function ajax_level(data)
			{				
				jQuery('#levelId').empty();
			    jQuery('#levelId').append('<option value="0">选择层次</option>');
			    if(data.levellist!=null && data.levellist.length>0)
			    {
			    	jQuery.each(data.levellist,function(){	
			    		jQuery('#levelId').append('<option value="'+this.id+'">'+this.level.name+'</option>'); 
			    	});
			   	}	
			   	ajax_130_1();//专业	
			}
			//ajax回调函数  专业(层次)
			function ajax_major(data)
			{				
				jQuery('#majorId').empty();
			    jQuery('#majorId').append('<option value="0">选择专业</option>');
			    if(data.majorlist!=null && data.majorlist.length>0)
			    {
			    	jQuery.each(data.majorlist,function(){	
			    		jQuery('#majorId').append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	});
			    }	
			}
			//ajax回调函数  招生批次(院校、全局批次)
			function ajax_batch(data)
			{				
			    if(data.batch!=null)
			    {
			    	jQuery('#batch').html(data.batch.enrollmentName);
			   		jQuery('#batchId').val(data.batch.id);
			    }	
			    else
			    {
			    	jQuery('#batch').html('');
			   		jQuery('#batchId').val(0);
			    }
			    //batchId=parseInt(jQuery('#batchId').val());
			    ajax_120_1();//层次	
			}
		</script>
<a:ajax traditional="true" parameters="jQuery('#signupForm').serializeObject()" successCallbackFunctions="successFunctionCallBack" pluginCode="001" urls="/crm/add_student_np"/>

		<a:ajax successCallbackFunctions="all_branch_list_call_back" pluginCode="002" urls="/crm/all_branch_list" isOnload="all"/>

		<!--全局批次(学习中心)-->
		<a:ajax successCallbackFunctions="ajax_global_batch" parameters="{branchId:jQuery('#branch').val()}" urls="/enrollment/cascade_global_batch_branch_ajax" pluginCode="100"/>
		
		<!--院校(学习中心、全局批次)-->
		<a:ajax successCallbackFunctions="ajax_academy" parameters="{branchId:jQuery('#branch').val(),globalBatchId:jQuery('#globalBatchId').val()}" urls="/enrollment/cascade_branch_global_batch_academy_ajax" pluginCode="110"/>
		
		<!--层次(招生批次)-->
		<a:ajax successCallbackFunctions="ajax_level" parameters="{batchId:jQuery('#batchId').val()}" urls="/enrollment/cascade_batch_level_ajax" pluginCode="120"/>
		
		<!--专业(层次)-->
		<a:ajax successCallbackFunctions="ajax_major" parameters="{levelId:jQuery('#levelId').val()}" urls="/enrollment/cascade_level_major_ajax" pluginCode="130"/>
		
		<!--招生批次(院校、全局批次)-->
		<a:ajax successCallbackFunctions="ajax_batch" parameters="{academyId:jQuery('#academyId').val(),globalBatchId:jQuery('#globalBatchId').val()}" urls="/enrollment/cascade_global_batch_academy_batch_ajax" pluginCode="140"/>

<script>
function submitForm(){
	if(checkSignForm()){
		ajax_001_1();
	}
}
function initPage() {
	//initProvinces(document.signupForm.province, document.signupForm.city);
	
} 
jQuery(document).ready(function(){
	//选中下拉菜单
	pcas=new jQuery.PCAS({province:'select[name=student.livingPlace]',provinceV:""});
});
</script>
</head><body onload="initPage();">
<div id="header">
	<div class="topInfo clearfix"><span class="left"><a 
href="http://info.chinaedu.net/" target="_blank">弘成教育</a>旗下网站</span><span
 class="right">国家级现代远程教育公共服务体系试点单位</span></div>
    <div class="topLB">
    	<p><a href="http://xueyuan.chinaedu.net/" class="logo" title="弘成学苑">弘
成学苑</a></p>
      <p class="headpic"><object 
classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" 
codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0"
 width="600" height="60">
<param name="movie" value="http://baoming.xueyuan.chinaedu.net/images/top_banner60060.swf">
<param name="quality" value="high"> 
<param name="wmode" value="transparent">
<param name="menu" value="false">
<embed src="http://baoming.xueyuan.chinaedu.net/images/top_banner60060.swf" quality="high" 
pluginspage="http://www.macromedia.com/go/getflashplayer" 
type="application/x-shockwave-flash" width="600" height="60"> 
</object> </p>
        <p class="hotLine">
        	<img src="http://baoming.xueyuan.chinaedu.net/images/xy_hotline.gif" alt="热线电话">

            手机座机皆可拨打，免长途<br>
            <a href="http://xueyuan.chinaedu.net/html/492faf5785b68/"><img
 src="http://baoming.xueyuan.chinaedu.net/images/xy_online.gif" alt="在线咨询"></a>
        </p>
        <p class="clear"></p>
    </div>
    <div class="nov clearfix">
   	  <p class="novLeft"></p>
        <ul>

       	  <li><span><a href="http://xueyuan.chinaedu.net/">弘成学苑首页</a></span></li>
            <li class="liLine"></li>
          <li><span><a 
href="http://xueyuan.chinaedu.net/html/492faf56169c2/recruit/">报名专区</a>[<a
 href="http://xueyuan.chinaedu.net/html/zy/school_wrap_Profession_2.html" class="smallnormal">高起
本</a> <a href="http://xueyuan.chinaedu.net/html/zy/school_wrap_Profession_3.html" 
class="smallnormal">专升本</a> <a 
href="http://xueyuan.chinaedu.net/html/zy/school_wrap_Profession_1.html" class="smallnormal">高起专</a>]</span></li>
            <li class="liLine"></li>

          <li><span><a 
href="http://xueyuan.chinaedu.net/html/492faf543acae/">招生院校</a></span></li>
            <li class="liLine"></li>
          <li><span><a 
href="http://xueyuan.chinaedu.net/html/492faf5635e11/">学习中心</a></span></li>
            <li class="liLine"></li>
          <li><span><a href="http://xueyuan.prcedu.com/" target="_blank">开心校园</a></span></li>
        </ul>      
        <p class="novRight"></p>

        <div class="search">

		  <form method="post" action="http://baoming.xueyuan.chinaedu.net/search.aspx" 
name="formsearch">
        	<span class="left"><input name="key" id="key" 
class="searchInput" value="请输入期望的学历和专业" 
onclick="this.value='';this.style.color='#444444'" autocomplete="off" 
type="text"></span><span class="right"><input name="" value=" " 
class="searchBn" type="submit"></span>
          </form>
		</div>
    </div>
    <p class="clear"></p>
</div>
<div class="stucen stucenCorner">

	<span class="tl"></span><span class="tr"></span>
    <span class="bl"></span><span class="br"></span> 
	<strong>学习中心：</strong><a href='http://xueyuan.chinaedu.net/html/492faf5635e11/492faf56c95a7/'>北京</a><a href='http://xueyuan.chinaedu.net/html/492faf5635e11/492faf56cc3d9/'>上海</a><a href='http://xueyuan.chinaedu.net/html/492faf5635e11/492faf56cf2bb/'>南京</a><a href='http://xueyuan.chinaedu.net/html/492faf5635e11/492faf56dae3b/'>杭州</a><a href='http://xueyuan.chinaedu.net/html/492faf5635e11/wx/'>无锡</a><a href='http://xueyuan.chinaedu.net/html/492faf5635e11/492faf56d222c/'>苏州</a><a href='http://xueyuan.chinaedu.net/html/492faf5635e11/492faf56d514b/'>常州</a><a href='http://xueyuan.chinaedu.net/html/492faf5635e11/492faf56d7f5a/'>徐州</a><a href='http://xueyuan.chinaedu.net/html/492faf5635e11/ha/'>淮安</a><a href='http://xueyuan.chinaedu.net/html/492faf5635e11/yz/'>扬州</a><a href='http://xueyuan.chinaedu.net/html/492faf5635e11/sq/'>宿迁</a><a href='http://xueyuan.chinaedu.net/html/492faf5635e11/yc/'>盐城</a><a href='http://xueyuan.chinaedu.net/html/492faf5635e11/taizh/'>泰州</a><a href='http://xueyuan.chinaedu.net/html/492faf5635e11/nt/'>南通</a><a href='http://xueyuan.chinaedu.net/html/492faf5635e11/lyg/'>连云港</a><a href='http://xueyuan.chinaedu.net/html/492faf5635e11/492faf56ddda7/'>宁波</a><a href='http://xueyuan.chinaedu.net/html/492faf5635e11/492faf56e3f48/'>温州</a><a href='http://xueyuan.chinaedu.net/html/492faf5635e11/492faf56e0ce7/'>台州</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>

<p class="clear"></p>
<table style="margin: 0pt auto;" border="0" cellpadding="0" 
cellspacing="0" width="950">
	<tbody><tr>
		<td align="left" height="30">
			<img src="http://baoming.xueyuan.chinaedu.net/images/main_bar_icon_01.gif" align="absmiddle" 
width="18" height="18"> <a href="http://xueyuan.chinaedu.net/">弘成学苑</a> 
&gt; 网络学历教育预报名
	  </td>
	</tr>
</tbody></table>

<table style="margin: 0pt auto;" border="0" cellpadding="0" 
cellspacing="0" width="950">
	<tbody><tr> 
		<td align="left" valign="top" width="250"> 
          <table border="0" cellpadding="0" cellspacing="0" width="100%">
            <tbody><tr>
              <td>
			<table style="background-repeat: repeat-x; background-position: left 
top;" background="http://baoming.xueyuan.chinaedu.net/images/tb_tl_a_bk_01.gif" bgcolor="#fefdf9" 
border="0" cellpadding="0" cellspacing="0" width="240" height="100%">
				<tbody><tr> 
					<td valign="top" height="7"> 
						<table background="http://baoming.xueyuan.chinaedu.net/images/tb_tl_a_03.gif" border="0" 
cellpadding="0" cellspacing="0" width="100%">
							<tbody><tr> 
								<td width="20" height="7"><img 
src="http://baoming.xueyuan.chinaedu.net/images/tb_tl_a_01.gif" width="6" height="7"></td>

								<td align="right"><img src="http://baoming.xueyuan.chinaedu.net/images/tb_tl_a_02.gif" 
width="6" height="7"></td>
							</tr>
						</tbody></table>
					</td>
				</tr>
				<tr> 
					<td style="border-left: 1px solid rgb(219, 219, 219); border-right:
 1px solid rgb(219, 219, 219);" align="center" valign="top" height="*">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tbody><tr> 
								<td height="5"></td>

							</tr>
						</tbody></table>
						<p style="padding: 20px 0pt;"><img 
src="http://baoming.xueyuan.chinaedu.net/images/xueyuan_logo.gif"> </p>
						<table border="0" cellpadding="5" cellspacing="0" width="220">
							<tbody><tr> 
								<td align="left"> 
									<div style="line-height: 20px; text-align: left;"><font 
color="#666666">　　2007年2月，教育部颁发了《教育部办公厅关于同意弘成科技发展有限公司和中国人民大学等有关高校联合开展现代远
程教育公共服务体系建设试点项目的通知》教育部办公厅发文高教厅函【2007】12号文件，正式授权弘成学苑为国家级现代远程教育公共服务体系试点项目实
施单位。<br>
　　弘成学苑自实施公共服务体系试点项目以来，陆续在北京、上海、浙江、江苏四个地区开展现代远程教育公共服务体系建设工作，目前，已在北京、上海、南
京、苏州、常州、扬州、无锡、淮安、宿迁、徐州、盐城、连云港、泰州、南通、杭州、宁波、温州、台州等城市开设弘成学苑数字化学习中心，依托高校和各地教
育资源，不断创新网络学历教育服务模式，在广州、南昌、长春、郑州、海南等省会市设立弘成学苑提数字化示范学习中心；
弘成学苑始终致力于为更多的求学者提供专业、实用的教育服务，为学习者提供学校选择、专业选择、课程咨询、导学督学、就业指导等全方位一站式教育支持服
务，逐渐打造终身学习型服务平台。目前，弘成学苑已与近百家企事业单位建立紧密的合作关系,为电力、交通、金融、外贸、建筑、水利、教育、电子、石化、商
务等行业的培养输送了大批的优秀人才。<br>
　　弘成学苑依托弘成教育集团强大的互联网技术服务优势，与众多 
“211工程”的高校建立合作关系，充分利用优质教育资源，打破传统学历教育学习模式，满足不同群体的学习需求，为社会提供包括网络高等学历教育、行业职
业培训、技能培训、大众素质教育等在内的教育服务，实现快乐学习、终身学习的目标。<br>

　　弘成教育集团成立于1999年，是集高等学历教育、基础教育、国际教育、101远程教育于一体的综合教育服务机构，自1999年与中国人民大学合作开
办现代远程教育以来，紧密合作高校已达十余所，十年来，弘成现代远程网络教育已为社会各行业输送具有大专以上高等学历的大学生20余万人，在读大学生30
余万人；2007年12月11日弘成教育集团在美国纳斯达克正式挂牌上市（股票代码【NasdaqGM:CEDU】），成为国内首个成功登陆海外资本市场
的网络教育全面服务提供商。多年来，弘成教育集团本着“弘扬教育成就人生”的宗旨，高度重视对社会的贡献价值，设立并开展“弘成风采奖学金”等社会助学活
动，在人生导向、劳动就业和职业规划等方面关注用户需求，为社会、为合作伙伴、为学生提供精细专业化的网络教育服务。<br>
<p class="blank8"></p>
										<strong><font color="#676767">免费招生电话：</font></strong>
										<strong><font color="#f66a2d">400-680-4299</font></strong><br>
										<strong><font color="#676767">咨询邮箱：</font></strong>
										<a href="mailto:callcenter@chinaedu.net"><font color="#096fd4">callcenter@chinaedu.net</font></a>
 
										<br>

										</font>
									</div>
								</td>
							</tr>
						</tbody></table>
					</td>
				</tr>
				<tr> 
					<td valign="top" height="8"> 
						<table background="http://baoming.xueyuan.chinaedu.net/images/tb_tl_a_06.gif" border="0" 
cellpadding="0" cellspacing="0" width="100%">

							<tbody><tr> 
								<td width="20" height="7"><img 
src="http://baoming.xueyuan.chinaedu.net/images/tb_tl_a_04.gif" width="10" height="8"></td>
								<td align="right"><img src="http://baoming.xueyuan.chinaedu.net/images/tb_tl_a_05.gif" 
width="10" height="8"></td>
							</tr>
						</tbody></table>
					</td>
				</tr>
			</tbody></table></td>
            </tr>

            <tr>
              <td style="padding-top: 8px;">
			<table style="background-repeat: repeat-x; background-position: left 
top;" background="http://baoming.xueyuan.chinaedu.net/images/tb_tl_a_bk_02.gif" bgcolor="#fefdf9" 
border="0" cellpadding="0" cellspacing="0" width="240" height="100%">
				<tbody><tr> 
					<td valign="top" height="7"> 
						<table background="http://baoming.xueyuan.chinaedu.net/images/tb_tl_a_03.gif" border="0" 
cellpadding="0" cellspacing="0" width="100%">
							<tbody><tr> 
								<td align="left" width="20" height="7"><img 
src="http://baoming.xueyuan.chinaedu.net/images/tb_tl_a_01.gif" width="6" height="7"></td>
							  <td align="right"><img src="http://baoming.xueyuan.chinaedu.net/images/tb_tl_a_02.gif" 
width="6" height="7"></td>
							</tr>

						</tbody></table>
					</td>
				</tr>
				<tr> 
					<td style="border-left: 1px solid rgb(219, 219, 219); border-right:
 1px solid rgb(219, 219, 219);" align="center" valign="top" height="*">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tbody><tr> 
								<td height="5"></td>
							</tr>
						</tbody></table>

						<table border="0" cellpadding="5" cellspacing="0" width="220">
							<tbody><tr> 
								<td align="left"> 
									<div style="line-height: 20px; text-align: left;"> 
										<font color="#666666"><strong><font color="#676767">友情提示：</font></strong><br><br>
											1.对符合要求的学生实行免试入学政策 <br>
											2.部分高校不收取报名和入学测试费</font> 
									</div>
								</td>

							</tr>
						</tbody></table>
					</td>
				</tr>
				<tr> 
					<td valign="top" height="8"> 
						<table background="http://baoming.xueyuan.chinaedu.net/images/tb_tl_a_06.gif" border="0" 
cellpadding="0" cellspacing="0" width="100%">
					  <tbody><tr> 
								<td align="left" width="20" height="7"><img 
src="http://baoming.xueyuan.chinaedu.net/images/tb_tl_a_04.gif" width="10" height="8"></td>
							  <td align="right"><img src="http://baoming.xueyuan.chinaedu.net/images/tb_tl_a_05.gif" 
width="10" height="8"></td>

							</tr>
						</tbody></table>
				  </td>
				</tr>
			</tbody></table></td>
            </tr>
          </tbody></table>		</td>
		<td align="left" valign="top"> 
			<form name="signupForm" id="signupForm" method="post" onsubmit="return false;">

			<table style="background-repeat: repeat-x; background-position: left 
top;" background="http://baoming.xueyuan.chinaedu.net/images/tb_tl_a_bk_02.gif" bgcolor="#fefdf9" 
border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
				<tbody><tr> 
					<td valign="top" height="7"> 
						<table background="http://baoming.xueyuan.chinaedu.net/images/tb_tl_a_03.gif" border="0" 

cellpadding="0" cellspacing="0" width="100%">
							<tbody><tr> 
								<td width="20" height="7"><img 
src="http://baoming.xueyuan.chinaedu.net/images/tb_tl_a_01.gif" width="6" height="7"></td>
								<td align="right"><img src="http://baoming.xueyuan.chinaedu.net/images/tb_tl_a_02.gif" 
width="6" height="7"></td>
							</tr>
						</tbody></table>
					</td>

				</tr>
				<tr> 
					<td style="border-left: 1px solid rgb(219, 219, 219); border-right:
 1px solid rgb(219, 219, 219);" align="center" valign="top" height="*">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tbody><tr> 
								<td height="5"></td>
							</tr>
						</tbody></table>
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tbody><tr> 
								<td align="center" height="40"><strong><font color="#f66a2f" 
size="3">网络学历教育预报名</font></strong></td>

							</tr>
						</tbody></table> 
			
						<table border="0" cellpadding="5" cellspacing="0" width="90%">
							<tbody><tr> 
								<td class="maindesc_td" align="left">　　您好，如果您希望进一步了解网络学历教育的相关信息与
具体的学习细节，请仔细填写以下表格，我们的咨询教师将在您指定的时间拨打您的电话并提供咨询服务——</td>
            </tr>
							<tr> 
								<td class="maindesc_td" align="left">　　<font color="#ff0000"><strong>声
明：</strong>我们尊重并将严格保密您的个人隐私信息，承诺绝不出租或出售您的个人隐私信息。</font></td>

						  </tr>
						
						</tbody></table>
						<table border="0" cellpadding="5" cellspacing="0" width="90%">
			  <tbody><tr> 
								<td class="maintable_td" align="left">1、请填写您的<strong>姓名</strong>，并选择<strong>称谓</strong> （*）：<br>
									<input name="student.name" id="userName" class="text_field" size="20" type="text">							
									<input name="student.gender" id="sex" type="radio" checked="checked" value="1"> 男
									<input name="student.gender" id="sex" type="radio"  value="0"> 女
　								</td>
						  </tr>
							<tr> 
								<td class="maintable_td" align="left">2、请选择您当前工作或生活的<strong>所在地</strong>（*）：
 
<table border="0" cellpadding="0" cellspacing="0" width="90">
										<tbody><tr> 
											<td>

												
													<div class="pretty_select_box2"> 
														<select name="student.livingPlace" id="cityName" hidefocus="" class="pretty_select">
															
														</select>
													</div>
										  </td>
											<td style="visibility: hidden;">
												
																																	</td>
										</tr>
								  </tbody></table>								</td>
						  </tr>

							<tr> 
								<td class="maintable_td" align="left">3、请填写您的<strong>联系电话</strong>（手机号码或座机号码至少填写一项）（*）： 
									<table class="maindesc" border="0" cellspacing="0" width="90%">
								  <tbody><tr> 
											<td nowarp="" align="left" width="211" height="25">手机号码：
										  <input name="student.mobile" id="mobile" class="text_field" size="20" type="text"></td>
											<td align="left" width="352"><font color="#a1a1a1">参考格式：13912345678</font></td>
									  </tr>
										<tr> 
											<td nowarp="" align="left" height="25">座机号码：
										  <input name="student.phone" id="phone" class="text_field" size="20" type="text"></td>

											<td align="left"><font color="#a1a1a1">参考格式：010-66666666-1234</font></td>
										</tr>
								  </tbody></table>								</td>
				      </tr>
					  <tr> 
								<td class="maintable_td" align="left">4、请填写您的<strong>证件号码</strong>，该号码将与您的整个求学过程紧密相连，请慎重填写：<strong></strong><br> 
									
								 <table width="100%" border="0" cellspacing="0" cellpadding="0">

<tr> 
											<td nowarp="" align="left" width="60" height="25">证件类型：</td>
								    <td align="left" width="130"><p class="pretty_select_box" style="text-align: left;">
								   <select name="student.certType" id="certificateType" hidefocus="" 
class="pretty_select">
<option value="">选择证件类型</option>

<option value="<%=Constants.CERTIFICATE_TYPE_ID %>" selected="selected">证件号</option>
<option value="<%=Constants.CERTIFICATE_TYPE_DRIVER_ID %>">驾照</option>
<option value="<%=Constants.CERTIFICATE_TYPE_NCO_ID %>">士官证</option>

</select></p>
							</td>
										  <td align="left">&nbsp;</td>
									  </tr>
										<tr> 
											<td nowarp="" align="left" height="25">证件号码： </td>
										  <td align="left"><input name="student.certNo"  id="certificateCode" class="text_field" size="20" type="text"></td>
										  <td align="left">&nbsp;</td>
									  </tr>

</table>

																	</td>
						  </tr>
							<tr> 
								<td class="maintable_td" align="left">5、请您选择当前的<strong>学历程度</strong>（*）：<br> 
									
									
<input name="student.degree" id="educational" type="radio" value="4"> 高中以下

<input name="student.degree" id="educational" type="radio" value="3"> 高中或中专

<input name="student.degree" id="educational" type="radio" value="2"> 大学专科


<input name="student.degree" id="educational" type="radio" value="1"> 大学本科及以上

																	</td>
						  </tr>
						  
							<tr> 
								<td class="maintable_td" align="left">6、请选择可以为您提供电话咨询的<strong>服务时间</strong>：<br> 
									
									<table class="maindesc" border="0" cellspacing="0" width="99%">
								  <tbody><tr> 
											<td nowarp="" align="left" width="160" height="20"> 
											 <input name="serviceTime" id="serviceTime" value="工作时间（10～17时）" type="checkbox">工作时间（10～17时）&nbsp;	 </td>

										  <td align="left" width="160"> 
												 <input name="serviceTime" id="serviceTime" value="下班时间（18～20时）" type="checkbox">下班时间（18～20时）&nbsp;	 										</td>
										  <td align="left">
											 <input name="serviceTime" id="serviceTime" value="周末或假日（10～17时）" type="checkbox">周末或假日（10～17时）&nbsp;	 										</td>
									  </tr><tr> 
											<td nowarp="" align="left" width="160" height="20"> 
											 <input name="serviceTime" id="serviceTime" value="日常皆可（10～20时）" type="checkbox">日常皆可（10～20时）&nbsp;	 </td>

										  <td align="left" width="160"> 
												&nbsp;	 										</td>
										  <td align="left">
											&nbsp;	 										</td>
									  </tr>
										<tr> 
										 
									  <td colspan="3" align="left">
										<input name="serviceTimeBoolean" id="specialServiceTimeFlag" value="true"  type="checkbox"> 指定时间：
										<input name="serviceTimeText" id="specialServiceTime" class="text_field" size="20" type="text"></td>

									  </tr>
									</tbody></table>							  </td>
						  </tr>
							<tr> 
								<td class="maintable_td" align="left">
									<strong>如果您希望我们为您提供更好更深入的服务，建议填写以下信息——</strong>
									<br>
									<br>									
							  7、请填写您感兴趣的<strong>学习中心</strong>、<strong>批次</strong>、<strong>院校</strong>、<strong>层次</strong>与<strong>专业</strong>（学习中心必须选）（*）：<br>

									
							  <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                <tbody>
                                		<tr>
                                	
                                  <td style="padding: 3px;" nowarp="" align="left" width="60" height="25">学习中心：</td>
                                  <td style="padding: 3px;" align="left" width="140">
                                 	<span id="batch" name="batch" style="color: black;display:none"></span>
									<input id="batchId" type="hidden" value="0" name="student.enrollmentBatchId">
                                 	 <p class="pretty_select_box" style="text-align: left;">
                                  		<select id="branch" name="student.branchId"  hidefocus="" class="pretty_select">
											<option value="0" selected="selected">选择学习中心</option>
										</select>
									</p>
									</td>
                                </tr>
                                 <tr>
                                  <td style="padding: 3px;" nowarp="" align="left" height="25">批　　次：</td>
                                  <td style="padding: 3px;" align="left" width="140">
                                  	<p class="pretty_select_box" style="text-align: left;">
                                  		<select name=""  hidefocus="" id="globalBatchId" class="pretty_select">
											<option value="0" selected="selected">选择批次</option>
										</select>
									</p>
                                  </td>
                                  <td style="padding-left: 5px;" nowarp="" align="left"></td>
                                </tr>
                                <tr>
                                  <td style="padding: 3px;" nowarp="" align="left" height="25">院　　校：</td>
                                  <td style="padding: 3px;" align="left" width="140">
                                  	<p class="pretty_select_box" style="text-align: left;">
                                  		<select name="student.academyId"  hidefocus="" id="academyId" class="pretty_select">
											<option value="0" selected="selected">选择院校</option>
										</select>
									</p>
                                  </td>
                                </tr>
                                 <tr>
                                  <td style="padding: 3px;" nowarp="" align="left" height="25">层　　次：</td>
                                  <td style="padding: 3px;" align="left" width="140">
                                  	<p class="pretty_select_box" style="text-align: left;">
                                  		<select name="student.levelId"  hidefocus="" class="pretty_select" id="levelId">
											<option value="0" selected="selected">选择层次</option>
										</select>
									</p>
                                  </td>
                                
                                </tr>
                                <tr>
                                  <td style="padding: 3px;" nowarp="" align="left" height="25">专　　业：</td>
                                  <td style="padding: 3px;" align="left" width="140">
                                  	<p class="pretty_select_box" style="text-align: left;">
                                  		<select name="student.majorId" id="majorId" hidefocus="" class="pretty_select" id="majorId">
											<option value="0" selected="selected">选择专业</option>
										</select>
									</p>
                                  </td>
                                 
                                </tr>
								</tbody></table> 
							  </td>
				</tr>
					  </tbody></table>
					  <table border="0" cellpadding="5" cellspacing="0" width="90%">
							<tbody><tr> 
								<td class="maindesc_td" align="left">8、请完善您的个人信息：<br>
									<table class="maindesc" border="0" cellspacing="0" width="600">
						  <tbody>
										<tr> 
											<td nowarp="" align="left" height="25">邮件地址： </td>

										  <td align="left"><input name="student.email" id="email" class="text_field"  size="20" type="text"></td>
										  <td style="padding-left: 5px;" align="left"><font 
color="#a1a1a1">参考格式：callcenter@chinaedu.net</font></td>
									  </tr>
										<tr> 
											<td nowarp="" align="left" height="25">联系地址： </td>
										  <td colspan="2" align="left"><input name="student.address" id="addressInfo"  class="text_field" size="40" type="text"></td>
									  </tr>
										<tr> 
											<td nowarp="" align="left" height="25">邮政编码： </td>

										  <td align="left"><input name="student.zipcode" id="postalCode" class="text_field"  size="20" type="text"></td>
										  <td style="padding-left: 5px;" align="left"><font 
color="#a1a1a1">应为6位数字，如：100007</font></td>
									  </tr>
									</tbody></table> 
							  </td>
				</tr>
					  </tbody></table>
						<table border="0" cellpadding="0" cellspacing="0" width="90%">
							<tbody><tr> 
								<td align="center" height="40">

									<input onclick="submitForm();" type="image" src="http://baoming.xueyuan.chinaedu.net/images/btn_submit_signup.gif" />
									
									<input name="refererUrl" 
value="http://www.google.cn/search?hl=zh-CN&amp;source=hp&amp;q=弘成学苑&
amp;btnG=Google 搜索&amp;aq=1&amp;oq=弘成" type="hidden">
									<input name="refererKey" value="102" type="hidden">
									<input name="action" type="hidden" value="signFormEx">
								</td>
							</tr>
						</tbody></table>
						<table border="0" cellpadding="5" cellspacing="0" width="90%">
							<tbody><tr> 
								<td class="maindesc_td" align="left">说明：标记为 （*）的项目均为必填或必选。</td>

						  </tr>
						</tbody></table>
					</td>
				</tr>
				<tr> 
					<td valign="top" height="8"> 
						<table background="http://baoming.xueyuan.chinaedu.net/images/tb_tl_a_06.gif" border="0" 
cellpadding="0" cellspacing="0" width="100%">
							<tbody><tr> 
								<td width="20" height="7"><img 
src="http://baoming.xueyuan.chinaedu.net/images/tb_tl_a_04.gif" width="10" height="8"></td>
								<td align="right"><img src="http://baoming.xueyuan.chinaedu.net/images/tb_tl_a_05.gif" 
width="10" height="8"></td>

							</tr>
						</tbody></table>
					</td>
				</tr>
			</tbody></table>
			</form>
		</td>
    </tr>	
</tbody></table>

 <div id="footer" style="margin-top:10px;">
    <p class="footInfo"><a href="http://xueyuan.chinaedu.net/html/sitemap.html" target="_blank">网站地图</a> <span>|</span> <a href="http://xueyuan.chinaedu.net/html/about.html" target="_blank">关于我们</a> <span>|</span> <a href="http://xueyuan.chinaedu.net/special/adviser/index.html" target="_blank">招聘信息</a> <span>|</span> <a href="http://xueyuan.chinaedu.net/html/492faf5785b68/index_xueyuan.html" target="_blank">客服中心</a> <span>|</span> <a href="mailto:fuwu@chinaedu.net">投诉邮箱</a></p>

    <p class="footInfo" style="color:#666666">经营许可证编号：<a href="http://www.miibeian.gov.cn/">京ICP证041171号</a>   网站注册标号010202003111100006</p>
    <p class="footInfo" style="color:#666666">&copy;2001-2009 北京现代兴业网络技术有限公司 版权所有。保留所有权利。</p>  
    <p class="chineduLogo"><a href="http://xueyuan.chinaedu.net/"><img 
src="http://baoming.xueyuan.chinaedu.net/images/xueyuan_logo.gif" alt="Chinaedu"></a></p>
    <p class="icpPic"><a  href="http://www.hd315.gov.cn/beian/view.asp?bianhao=010202003111100006"  target="_blank"><img src="http://baoming.xueyuan.chinaedu.net/images/icp_pic.jpg" alt="ICP"></a></p>
  
</div>
<script language="JavaScript">
	nwr_wmas_siteid = '7dbc792adf374ffa89cc3ae472c200fd';
	nwr_wmas_track='';
</script>

<script language="JavaScript" src="http://js.lov9.net/wmas.js"></script>

<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-19704567-5']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
<script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F4e4a7c19e824f44ee5d6a1e0c5ad0338' type='text/javascript'%3E%3C/script%3E"));
</script>
 
 <script language="javascript">
 	
	var selo='';
	if(selo!=''){
		for(var i=0;i<document.getElementById('eduLevel').options.length;i++){
			if(document.getElementById('eduLevel').options[i].value==selo)document.getElementById('eduLevel').options[i].selected=true;
		}
	}
	
 </script>

</body></html>