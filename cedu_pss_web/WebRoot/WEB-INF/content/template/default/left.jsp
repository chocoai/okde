<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>菜单栏</title>
		<jc:plugin name="layout" />
		<jc:plugin name="jquery" />
		<script type=text/javascript>
//等待dom元素加载完毕.
$(document).ready(function() {
	getTree();
});

function inits() {
	$(".has_children").siblings().addClass("highlight").children("div").hide();
	$(".node_1").children("a").hide();
	$(".has_node").click(function() {
		if ($(this).parent().children("div").is(":hidden")) {
			$(this).parent().addClass("highlight") //为当前元素增加highlight类
					.children("div").show().end() //将子节点的a元素显示出来并重新定位到上次操作的元素
					.siblings().removeClass("highlight") //获取元素的兄弟元素，并去掉他们的highlight类
					.children("div").hide(); //将兄弟元素下的a元素隐藏
		} else {
			$(".has_children").addClass("highlight").children("div").hide();
		}

		var aMenus = $(window.top.head.document.getElementById("tdMenu"))
				.children("a");
		for ( var i = 0; i < aMenus.length; i++) {

			if (this.id == aMenus[i].id) {
				$(window.top.head.document.getElementById(aMenus[i].id)).css( {
					"color" : "white"
				});
			} else {
				$(window.top.head.document.getElementById(aMenus[i].id)).css( {
					"color" : "black"
				});
			}
		}

	});
	$(".left_children").click(function() {

		if ($(this).parent().children("a").is(":hidden")) {
			$(this).parent().addClass("highlight") //为当前元素增加highlight类
					.children("a").show(); //将兄弟元素下的a元素隐藏
			$(this).removeClass("left_round").addClass("show_node");
		} else {
			$(this).parent().addClass("highlight") //为当前元素增加highlight类
					.children("a").hide();
			$(this).removeClass("show_node").addClass("left_round");
		}
	});
	$(".has_node_1").click(function() {

		if ($(this).parent().children("a").is(":hidden")) {
			$(this).parent().addClass("highlight") //为当前元素增加highlight类
					.children("a").show(); //将兄弟元素下的a元素隐藏
			$(this).parent().children("#left_j").removeClass("left_round")
					.addClass("show_node");
		} else {
			$(this).parent().addClass("highlight") //为当前元素增加highlight类
					.children("a").hide();
			$(this).parent().children("#left_j").removeClass("show_node")
					.addClass("left_round");
		}
	});
	$("a").click(function() {
		$(".nochosea").removeClass("chosea");
		$(this).addClass("chosea");
	});
	setMeun();
}

function getTree() {
	jQuery
			.post(
					'<s:url value="tree_left"/>',
					function(data) {
						if (null != data.trslist) {
							var sublists = "";

							$
									.each(
											data.trslist,
											function() {
												//子系统
												sublists += '<input value="' + this.subSystem.orders + '" type="hidden">';
												sublists += "<div id=\'tab"
														+ this.subSystem.orders
														+ "\' class='leftcss' onClick='setTab(\"tab\","
														+ this.subSystem.orders
														+ ")'></div>";
												var con_tab_id = "con_tab"
														+ this.subSystem.orders;
												var subdiv = "<div style='display: none' id='"
														+ con_tab_id
														+ "' style='border:0px red solid'></div>";

												jQuery('#menu').html(
														jQuery('#menu').html()
																+ subdiv);
												var ml = this.mlist;
												$
														.each(
																ml,
																function() {
																	//模块
																	var modulardivid = this.modular.id;
																	var modularhtml = "<div id='"
																			+ modulardivid
																			+ "' class=has_children>";
																	var photourl = WEB_PLUGINS
																			+ "/plugin/base_css/images/menu/"
																			+ this.modular.imageUrl;
																	modularhtml += "<span id='"
																			+ modulardivid
																			+ "_a' class='has_node'><img border=0 src='"
																			+ photourl
																			+ "'></span>";
																	modularhtml += "</div>";
																	jQuery(
																			'#' + con_tab_id)
																			.html(
																					jQuery(
																							"#"
																									+ con_tab_id)
																							.html()
																							+ modularhtml);

																	var psl = this.pslist;
																	$
																			.each(
																					psl,
																					function() {
																						//权限
																						var pl = this.plist;
																						var pshtml = "<div class=node_"
																								+ this.privilegeSet.id
																								+ ">";
																						//有子项
																						if (pl.length > 1) {
																							//权限集
																							pshtml += "<div id='left_j' class='left_round left_children'>&nbsp;</div>";
																							pshtml += "<div id='left_bg' class='right_word has_node_1'>";
																							pshtml += '<img src=\''
																									+ WEB_IMAGES
																									+ '/images/arrow_01.gif\' alt="" />&nbsp;'
																									+ this.privilegeSet.name;
																							pshtml += "</div>";
																							$
																									.each(
																											pl,
																											function() {
																												pshtml += "<a class='nochosea' style='margin-left:30px;' name='"
																														+ this.fullPath
																														+ "' href='"
																														+ '<s:url value="/" />'
																														+ this.fullPath
																																.substring(1)
																														+ "' target='main' >"
																														+ this.name
																														+ "</a>";
																											});
																						}
																						//无子项
																						if (pl.length == 1) {
																							//权限集
																							pshtml += "<div class='left_noadd'>&nbsp;</div>";
																							pshtml += "<div class='right_word'>";
																							pshtml += "<a class='nochosea' href='"
																									+ WEB_PATH
																									+ "/"
																									+ this.plist[0].fullPath
																											.substring(1)
																									+ "' name='"
																									+ this.plist[0].fullPath
																									+ "' target='main'>"
																									+ this.plist[0].name
																									+ "</a>";
																							pshtml += "</div>";
																						}
																						pshtml += "</div>";
																						jQuery(
																								'#' + modulardivid)
																								.html(
																										jQuery(
																												'#' + modulardivid)
																												.html()
																												+ pshtml);
																					});
																});
											});
							jQuery('#leftbar_left').html(sublists);
							inits();
						}
					}, "json");
}

function setMeun() {
	var n = $('#leftbar_left').find('input[type="hidden"]');
	var maxval = 19;
	for ( var i = 0; i < n.size(); i++) {
		var hidval = $('#leftbar_left').find('input[type="hidden"]').eq(i)
				.val();
		maxval = maxval > hidval ? hidval : maxval;
		var menu = document.getElementById("tab" + hidval);
		var img = "img_dark" + hidval;
		menu.innerHTML = "<img src='" + WEB_PLUGINS
				+ "/plugin/base_css/images/menu/" + img + ".gif' />";
		var con = document.getElementById("con_tab" + hidval);
		con.style.display = "none";
	}
	if ("" != "" && "0" != "") {
		var menu = document.getElementById("tab");
		var con = document.getElementById("con_tab");
		var img = "img_light";
		menu.innerHTML = "<img src='" + WEB_PLUGINS
				+ "/plugin/base_css/images/menu/" + img + ".gif' />";
		con.style.display = "";
		$('#').addClass("highlight").children("div").show();
	} else {
		var menu = document.getElementById("tab" + maxval);
		var con = document.getElementById("con_tab" + maxval);
		var img = "img_light" + maxval;
		menu.innerHTML = "<img src='" + WEB_PLUGINS
				+ "/plugin/base_css/images/menu/" + img + ".gif' />";
		con.style.display = "";
	}
}
function setTab(name, cursel) {
	var n = $('#leftbar_left').find('input[type="hidden"]');
	for ( var i = 0; i < n.size(); i++) {
		var hidval = $('#leftbar_left').find('input[type="hidden"]').eq(i)
				.val();
		var menu = document.getElementById(name + hidval);
		var img = "img_dark" + hidval;
		menu.innerHTML = "<img src='" + WEB_PLUGINS
				+ "/plugin/base_css/images/menu/" + img + ".gif' />";
		var con = document.getElementById("con_" + name + hidval);/* con_two_1 */
		con.style.display = "none";
	}
	var menu = document.getElementById(name + cursel);/* two1 */
	var con = document.getElementById("con_" + name + cursel);/* con_two_1 */
	var img = "img_light" + cursel;
	menu.innerHTML = "<img src='" + WEB_PLUGINS
			+ "/plugin/base_css/images/menu/" + img + ".gif' />";
	con.style.display = "";
}
</script>
	</head>
	<body>
		<div id=leftbar>
			<!-- 子系统 -->
			<div id=leftbar_left style="border: 0px gray solid">

			</div>
			<!-- 子系统容器 -->
			<div id=leftbar_right style="border: 0px yellow solid">
				<!-- 模块 -->
				<div id="menu" style="border: 0px yellow solid">

				</div>
			</div>
		</div>

 
	
	</body>
</html>
