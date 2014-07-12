<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>区域管理 学习中心设置</title>
<!--  jquery库 -->
<jc:plugin name="jquery" />
<!-- jquery-UI -->
<jc:plugin name="jquery_ui" />
<!-- 整体样式 -->
<jc:plugin name="default" />
<!-- 选项卡 -->
<jc:plugin name="tab" />

<script type="text/javascript">
function remove_branch(index)
{
	var branches = new Array();
	jQuery('#added_branch_' + index + ' :checkbox[name="branch"]').each(function(){
		if(this.checked)
		{
			branches.push(this.value);
			var parentRow = $(this).parent().parent();
			parentRow.appendTo('#candidate_branch');
			this.checked = false;
			//$('td',parentRow).attr('class','');
			$('td',parentRow).addClass('removed_recently');
		}
	});
	
	if(branches.length>0)
		;
		//alert('移除成功');
	else
		alert('没有待移除授权的学习中心');
	//alert(branches.join('\n'));
}

function add_branch(index)
{
	var branches = new Array();
	jQuery('#candidate_branch :checkbox[name="branch"]').each(function(){
		if(this.checked)
		{
			branches.push(this.value);
			var parentRow = $(this).parent().parent();
			parentRow.appendTo('#added_branch_'+index);
			this.checked = false;
			$('td',parentRow).attr('class','');
			$('td',parentRow).addClass('added_recently');
		}
	});
	
	if(branches.length>0)
		;
		//alert('添加成功');
	else
		alert('没有待授权的学习中心');
	//alert(branches.join('\n'));
}

$(function(){
	$('[name=area]').click(function(){
		var index = this.value;
		document.getElementById('btn_remove').onclick=function(){ remove_branch(index); };
		document.getElementById('btn_add').onclick=function(){
			add_branch(index);
		};
		$('[id^=added_branch_]').hide();
		$('[id^=added_branch_] :checkbox[name=branch]').attr('checked', false);
		$('[id=candidate_branch] :checkbox[name=branch]').attr('checked', false);
		$('#added_branch_'+index).show();
	});
	$($('[name=area]').get(0)).click();
});
</script>
</head>  
<body>
		<!-- 头开始 -->
		<head:head title="区域管理 &gt;&gt; 学习中心设置">
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<!--Menu Begin-->
			<div>
				<ul id="menu">
				<li><a href="area_management?tab=1" title="">区域经理设置</a></li>
				<li><a href="area_management?tab=2" title="" class="current">学习中心设置</a></li>
				</ul>
			</div>
			<!--Menu End-->
			
			<!--Left Begin-->
			<div style="float:left; width:300px;">
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="../images/title_left.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">区域经理</th>
				</tr>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tbody>
					<tr>
						<td width="30"><input type="radio" name="area" value="1"/></td>
						<td>杨兵兵</td>
					</tr>
					<tr>
						<td width="30"><input type="radio" name="area" value="2"/></td>
						<td>王莹</td>
					</tr>
					<tr>
						<td width="30"><input type="radio" name="area" value="3"/></td>
						<td>郑丽群</td>
					</tr>
					<tr>
						<td width="30"><input type="radio" name="area" value="4"/></td>
						<td>魏媛媛</td>
					</tr>
				</tbody>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<tr><td><input id="btn1" class="btn_gray_130" type="button" value="保存更改" /></td></tr>
				</tr>
			</table>
			</div>
			<!--Left End-->
			
			<!--Line Begin-->
			<div style="float:left;width:4px; height:500px; margin-left:2px; margin-right:2px;">
			</div>
			<!--Line End-->
			
			<!-- Center BEGIN -->
			<div style="float:left; width:392px;">
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="../images/title_left.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">已分配学习中心</th>
				</tr>
			</table>
			<table id="added_branch_1" class="add_table" border="0" cellpadding="2" cellspacing="2" style="display:none;">
				<tr>
					<tr><td width="30"><input type="checkbox" name="branch" value="b100299122"/></td><td>阜阳学习中心</td></tr>
					<tr><td width="30"><input type="checkbox" name="branch" value="b20939912398"/></td><td>南京学习中心</td></tr>
					<tr><td width="30"><input type="checkbox" name="branch" value="b20999209383"/></td><td>徐州学习中心</td></tr>
					<tr><td width="30"><input type="checkbox" name="branch" value="b03942029929"/></td><td>菏泽学习中心</td></tr>
				</tr>
			</table>
			<table id="added_branch_2" class="add_table" border="0" cellpadding="2" cellspacing="2" style="display:none;">
				<tr>
					<tr><td width="30"><input type="checkbox" name="branch" value="b20999209383"/></td><td>云南学习中心</td></tr>
					<tr><td width="30"><input type="checkbox" name="branch" value="b03942029929"/></td><td>西藏学习中心</td></tr>
				</tr>
			</table>
			<table id="added_branch_3" class="add_table" border="0" cellpadding="2" cellspacing="2" style="display:none;">
				<tr>
					<tr><td width="30"><input type="checkbox" name="branch" value="b20939912398"/></td><td>河南学习中心</td></tr>
					<tr><td width="30"><input type="checkbox" name="branch" value="b20999209383"/></td><td>亳州学习中心</td></tr>
					<tr><td width="30"><input type="checkbox" name="branch" value="b03942029929"/></td><td>漉浥学习中心</td></tr>
				</tr>
			</table>
			<table id="added_branch_4" class="add_table" border="0" cellpadding="2" cellspacing="2" style="display:none;">
				<tr>
					<tr><td width="30"><input type="checkbox" name="branch" value="b03942029929"/></td><td>邯郸学习中心</td></tr>
				</tr>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<tr><td><input id="btn_remove" class="btn_gray_130" type="button" value="移除选中中心"/></td></tr>
				</tr>
			</table>
			</div>
			
			<div style="float:left;width:4px; height:300px; margin-left:2px; margin-right:2px;">
			</div>
			
			<!-- Center END -->
			
			<!--Right Begin-->
			<div style="margin-left:708px;">
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="../images/title_left.gif" /></th>
					<th style="text-align:left; font-weight:bold;">可选学习中心</th>
				</tr>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td>
						<table id="candidate_branch" class="gv_table_2" border="0" cellpadding="2" cellspacing="2">
							<tr><td width="30"><input type="checkbox" name="branch"/></td><td>九江学习中心</td></tr>
							<tr><td width="30"><input type="checkbox" name="branch"/></td><td>常州学习中心</td></tr>
							<tr><td width="30"><input type="checkbox" name="branch"/></td><td>合肥学习中心</td></tr>
							<tr><td width="30"><input type="checkbox" name="branch"/></td><td>安庆学习中心</td></tr>
							<tr><td width="30"><input type="checkbox" name="branch"/></td><td>杭州学习中心</td></tr>
							<tr><td width="30"><input type="checkbox" name="branch"/></td><td>长沙学习中心</td></tr>
							<tr><td width="30"><input type="checkbox" name="branch"/></td><td>上海学习中心</td></tr>
						</table>
					</td>
				</tr>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tr><td><input id="btn_add" class="btn_gray_130" type="button" value="添加选中中心"/></td></tr>
			</table>
			</div>
			<!--Right End-->		
			
  		</body:body>
</body>
</html>
