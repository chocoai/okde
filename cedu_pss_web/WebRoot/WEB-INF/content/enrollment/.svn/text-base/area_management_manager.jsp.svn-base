<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>区域管理 区域经理设置</title>
<!--  jquery库 -->
<jc:plugin name="jquery" />
<!-- jquery-UI -->
<jc:plugin name="jquery_ui" />
<!-- 整体样式 -->
<jc:plugin name="default" />
<!-- 选项卡 -->
<jc:plugin name="tab" />

	<script type="text/javascript">
	function prepare_add_area()
	{
		var ui = $('#add_area_dialog');
		ui.dialog('option', 'title', '新增区域经理');
		ui.dialog('open');
	}
	
	function modify_area(lnk)
	{
		var tds = $($(lnk).parents('td').get(0)).siblings();
		
		$('#area_code').html($(tds[0]).html());
		$('#area_name').val($(tds[1]).html());
		$('#area_manager').val($(tds[2]).html());
		
		var ui = $('#add_area_dialog');
		ui.dialog('option', 'title', '修改区域经理');
		ui.dialog('open');
	}
	
	$(function(){
		var ui = $('#add_area_dialog');
		ui.dialog({
			title: '新增区域经理',
			autoOpen: false,
			modal: true,
			beforeClose: function(){
				$('form',ui).get(0).reset();
			}
		});
	});
	
	</script>
</head>
<body>
		<!--头部层开始 -->
		<head:head title="区域管理 &gt;&gt; 区域经理">
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<div>
				<ul id="menu">
				<li><a href="area_management?tab=1" title="" class="current">区域经理设置</a></li>
				<li><a href="area_management?tab=2" title="">学习中心设置</a></li>
				</ul>
			</div>

			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="../images/title_left.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">区域经理设置</th>
					<th>
						<div style="text-align:right;">
							<img src="../images/cedu/icon/icon_add.gif" class="img_icon"/>
							<a href="javascript:void(0);" onclick="prepare_add_area()">新增区域经理</a>
						</div>
					</th>
				</tr>
			</table>
			<table class="gv_table_2" width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tbody>
				<tr>
					<th>操作</th>
					<th>区域经理</th>
			    </tr>
				<tr>
				  <td align="center">
					<a href="javascript:void(0);" onclick="modify_area(this)"><img class="img_icon" src="../images/cedu/icon/icon_edit.gif" title="修改"/></a>
				  </td>
				  <td align="center">杨兵兵</td>
			    </tr>
				<tr>
				  <td align="center">
					<a href="javascript:void(0);" onclick="modify_area(this)"><img class="img_icon" src="../images/cedu/icon/icon_edit.gif" title="修改"/></a>
				  </td>
				  <td align="center">杨兵兵</td>
			    </tr>
				<tr>
				  <td align="center">
					<a href="javascript:void(0);" onclick="modify_area(this)"><img class="img_icon" src="../images/cedu/icon/icon_edit.gif" title="修改"/></a>
				  </td>
				  <td align="center">张三</td>
			    </tr>
				<tr>
				  <td align="center">
					<a href="javascript:void(0);" onclick="modify_area(this)"><img class="img_icon" src="../images/cedu/icon/icon_edit.gif" title="修改"/></a>
				  </td>
				  <td align="center">李兴</td>
			    </tr>
				<tr>
				  <td align="center">
					<a href="javascript:void(0);" onclick="modify_area(this)"><img class="img_icon" src="../images/cedu/icon/icon_edit.gif" title="修改"/></a>
				  </td>
				  <td align="center">王五</td>
			    </tr>
				<tr>
				  <td align="center">
					<a href="javascript:void(0);" onclick="modify_area(this)"><img class="img_icon" src="../images/cedu/icon/icon_edit.gif" title="修改"/></a>
				  </td>
				  <td align="center">张三</td>
			    </tr>
			  </tbody>
			</table>
			<div id="candidaes_pager" class="pager2">
				<tr>
					<td colspan="8"><div class="imgalign" align="right">
						<span class="disabled"><label>当前1页/总1页</label></span>    	
						<img border='0' src='../images/navLeft.gif' />
						<img border='0' src='../images/page_left.gif' />
						<span class="csspage" >1</span>
						<img border='0' src='../images/page_right.gif' />
						<img border='0' src='../images/navRight.gif' />
						<span class="disabled"><input type="text" class="topage" value="1" /><input class="btnto" type="button" value="GO" /></span>
						</div>
					</td>
				</tr>
			</div>

		</body:body>

<div id="add_area_dialog" style="display: none;">
<form>
	<table class="add_table">  
		<tr>
			<td class="lable_100">区域经理：</td>
			<td>
				<select class="txt_box_100" name="" id="area_manager" >
					<option>请选择</option>
					<option>丁向东</option>
					<option>朱宏亮</option>
					<option>杨兵兵</option>
					<option>曾衍南</option>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input class="btn_black_61" type="button" value="保存"/></td>
		</tr>
	</table>
</form>
</div>

</body>
</html>
