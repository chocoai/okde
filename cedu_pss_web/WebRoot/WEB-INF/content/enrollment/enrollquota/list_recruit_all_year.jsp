<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>总部招生指标</title>
    	<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!--  jquery-loading-->
		<jc:plugin name="loading" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 选项卡 -->
		<jc:plugin name="tab" />
		<script type="text/javascript">
		var quotaId=0;
		var quotaYear=0;
		
		
		/*
		查询所有年度
		*/
		function ajax_listenrollquotaall(data)
		{
			
			
		}
		
		/*
		修改总指标
		*/
		function ajax_updateenrollquota(data)
		{
			closes('updateDiv');
			show('updateFinishDiv','提示',250,150);
			//search123();
			refresh123();
		}
		
		function ajax_addenrollquotabatch(data)
		{
			if(data.bol)
			{
				show('addDiv','提示',250,150);
			}else
			{
				show('errorDiv','提示',250,150);
			}
		}
		
		function ajax_addenrollquota(data)
		{
			
			$('#target').html("("+data.target+")");
			$('#quota').val(data.totaltarget);
			
		}
		
		function ajax_add(data)
		{
			if(data.bol)
			{
				show('addDiv','提示',250,150);
					
			}else
			{
				show('errorDiv','提示',250,150);
			}
			init();
			search123();
		
		
		
		}
		
		</script>
		
		
	
		<a:ajax parameters="null;{'id':$('#id').val(),'quota':$('#uquota').val()};{'quotaId':quotaId,'year':quotaYear};{'year':$('#year').val(),'branch':$('#branch').val(),'opeaing':$('#opeaing').val(),'major':$('#major').val(),'quota':$('#number').val()};{'year':$('#year').val(),'quota':$('#quota').val()}"
		successCallbackFunctions="ajax_listenrollquotaall;ajax_updateenrollquota;ajax_addenrollquotabatch;ajax_addenrollquota;ajax_add" 
		urls="/enrollment/enrollquota/listenrollquotaall;/enrollment/enrollquota/updateenrollquota;/enrollment/enrollquota/addenrollquotabatch;/enrollment/enrollquota/addenrollquota;/enrollment/enrollquota/addquota"
		pluginCode="123" 
		/>
		<a:ajax successCallbackFunctions="batch_enroll_quota_target_callback" parameters="{'id':$('#id').val()}" pluginCode="0001" urls="/enrollment/enrollquota/findbatchenrollquotatarget"/>
		<script type="text/javascript">
		
		
		function selNumType()
		{
			
			var num=$('#major').val();
			if(num==2)
			{
				$('#bili').html('(%)');
			}else
			{
				$('#bili').html('');
			}
		}
	
		function linkquota(id,quota)
		{
			return '<a href="'+WEB_PATH+'/enrollment/enrollquota/list_recruit_all?id='+id+'&tab=2"  >'+quota+'</a>';
		}
		
		function operating(id,enrollYear,quota)
		{
			
			return '<a href="javascript:void(0)" onclick="updatequota('+id+','+enrollYear+','+quota+')" >修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)"  onclick="addbatchquota('+id+','+enrollYear+')"   >创建批次指标</a>';
		}
		function addbatchquota(id,enrollYear)
		{
			quotaId=id;
			quotaYear=enrollYear;
			ajax_123_3();
			
		
		}
		
		
		function addenrollquota()
		{
			var num =parseInt($('#number').val());
			
			if(isNaN(num))
			{
				show('numDiv','提示',250,150);
				return;
			}
			ajax_123_4();
			
		}
		
		
		
		
	
		function updatequota(id,enrollYear,quota)
		{
			$('#id').val(id);
			$('#uyear').html(enrollYear);
			$('#uquota').val(quota);
			show('updateDiv','提示',250,300);
		}
		
		function updateenrollquota()
		{	
			var quota=$('#uquota').val();
			if($.trim(quota)=="")
			{
				$('#valquota').html('总指标不能为空！！');
				return;
			}
			if(isNaN(quota))
			{
				$('#valquota').html('总指标只能为数字！！');
				return;
			}
			ajax_0001_1();
		}
		function batch_enroll_quota_target_callback(data)
		{
			var quota=$('#uquota').val();
			if(data.batchtotaltarget>quota)
			{
				$('#valquota').html('年度总指标不能小于对应的批次总指标！！');
				return;
			}
			$('#valquota').html('');
			ajax_123_2();
			init();
		}
		
		
		function typesValue(types)
		{
			return types==STATUS_AUTHOR_FALSE?"未分配":"已分配";
		}
		
		function add()
		{
			
			var qouta=parseInt($('#quota').val());
			
			if(isNaN(qouta))
			{
				show('numDiv','提示',250,150);
				return;
			}
			ajax_123_5();
			init();
		} 
		
		function init()
		{
			var myDate = new Date();
			var nowDate=myDate.getFullYear();
			//var nowDate=myDate.toLocaleDateString();
			//var OneYear = nowDate.substring(0,nowDate.indexOf ('年'));  
			var OneYear = nowDate;
			var lists='';
			var count=2006;
			while(true)
			{
				var year=++count;
				lists+='<option value="'+year+'">'+year+'</option>';
				if(count==(parseInt(OneYear)+2))
				{
					break;
				}
			}
			$('#year').html("");
			
			$('#year').html(lists);
		}
		
		/*
		  页面首次加载
		*/
		$(function(){
			init();
	
	});
	
	
	
	
	
		</script>
	
  </head>
  
  <body>
   <!--头部层开始 -->
		<head:head title="总部招生指标">
		</head:head>
		<!--主体层开始 -->
		<body:body> 
		
			<!--Menu Begin-->
			<%@ include file="../_tab/enroll_quota_tab.jsp" %>
			<!--Menu End-->
		
		 <table class="add_table" cellpadding="0" cellspacing="0" border="0" width="100%">
		   <tr>
		   <td class="lable_50">年度：</td>
		   <td>
		   <select name="year" id="year" class="txt_box_100">
				   </select>
		   </td>
		 
		   <td><select name="level" id="branch" class="txt_box_130">
				          
						   <option value="1">上年计划招生指标</option>
						   <option value="2">上年完成招生指标</option>
						  
				   </select><span id="target"></span></td>
		   <td class="lable_50">操作符：</td>
		   <td>
			<select name="level" id="opeaing" class="txt_box_50">
						   <option value="1">加</option>
						   <option value="2">减</option>
						   <option value="3">乘</option>
						   <option value="4">除</option>
						  
				   </select>
			</td>
			<td><select name="major" id="major" onchange="selNumType()" class="txt_box_130">
						   <option value="1">数量</option>
						   <option value="2">比例</option>
				   </select></td>	   
				   
			<td><input type="text" id="number" class="txt_box_100" />
			<span id="bili"></span>
			等于<input type="text" id="quota" class="txt_box_100" />
			</td>
			
			
		    <td colspan="2" align="center"><input type="button" name="" id="" value="计算"  onclick="addenrollquota()" class="btn_black_61" />
		    <input type="button" name="" id="" value="保存"  onclick="add()" class="btn_black_61" />
		    </td>
			</tr>
 
		</table>  	

		<page:plugin 
				pluginCode="123"
				il8nName="enrollment"
				searchListActionpath="listenrollquota"
				searchCountActionpath="countenrollquota"
				columnsStr="enrollYear;quota;types;#operating"
				customColumnValue="1,linkquota(id,quota);2,typesValue(types);3,operating(id,enrollYear,quota)"
				pageSize="10"
				ontherOperatingWidth="80px"	
				params=""
				isOrder="false"
				
	/>

		<div id="updateDiv" style="display:none;">
		<form>
		<input type="hidden" name="id" id="id"  />
			<table class="add_table">  
				<tr>
					<td class="lable_100">年度：</td>
					<td>
						<span id="uyear"></span>
					</td>
				</tr>
				<tr>
					<td class="lable_100">总指标</td>
					<td>
					<input type="text" class="txt_box_130" name="uquota" id="uquota" /><br/>
					<span id="valquota"></span>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input onclick="updateenrollquota()" class="btn_black_61" type="button"  value="保存"/></td>
				</tr>
			</table>
		</form>
		</div>
		
		
		<div id="addDiv" style="display:none">
		创建成功！！
		</div>
		
		<div id="errorDiv" style="display:none">
		创建失败！！该年度指标已创建！！
		</div>
		
		
		<div id="numDiv" style="display:none">
			指标数只能为数字！！
		</div>
		
		<div id="updateFinishDiv" style="display:none">
			修改完成！！<br/><span style="color: red;">注：请核对对应的批次总指标</span>
		</div>	
		
		
		
		
		
		
				
		


   		</body:body>
  </body>
</html>
