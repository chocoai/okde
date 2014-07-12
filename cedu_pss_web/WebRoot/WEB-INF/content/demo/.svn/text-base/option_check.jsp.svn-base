<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<jc:plugin name="jquery" />
		<jc:plugin name="default" />
		
		<!-- 下拉复选框插件 -->
		<jc:plugin name="option_check" />
		 <script>
            $(document).ready(function () {
            	Jselect($("#optioncheck"),{//显示内容的文本框id
					bindid:'btn',   //可绑定到按钮上，此处为点击文本框显示下拉框
					hoverclass:'oc_hover',   //样式(不用修改)
					optionsbind:function(){
						return hqhtml();
					}
				});
            });
            
            function hqhtml(){//此处可在数据库中取值后拼接html，注意：预选项加上 lang='checked'属性
				var maxRow = 10;//下拉框最多显示条数
				var rowIndex = 0;//获取数据条数
				var str = "";
				// 获取集合列表（依情况自行修改）
				<c:if test="${channeltypelist!=null}">
					<c:forEach items="${channeltypelist}" var="channeltype">
						str+="<tr><td style='width:5px'><input type=\"checkbox\" name=\"optioncheck\" value='${channeltype.id}' /></td><td>${channeltype.name}</td></tr>";
						rowIndex++;
					</c:forEach>
				</c:if>
				if(rowIndex>maxRow){
					rowIndex = maxRow;
				}
				var optionshtml="<div style=\"height:"+((rowIndex*15)+18)+"px;overflow-X:auto;overflow:scroll;width:100px;\"><table style=\"width:100%; background-color: #E8E8E8;font-size:12px;\" cellpadding=\"0\" cellspacing=\"0\" >"+str+"</table></div>";  
	            return optionshtml;
			}
			
			function ds(){//获取值（依情况自行修改）
				var str = "";
				//for(var i=0;i<12;i++){
				//	if(document.getElementsByName("optioncheck")[i].checked == true){
				//		alert(document.getElementsByName("optioncheck")[i].value);
				//	}
				//}
			}
        </script>
	</head>
	<body>
		<form>
			<input id="optioncheck" type="text" class="oc_dt"/><input id="btn" type="button" value="选择"/>
			<input type="button" onClick="ds()" value=" alert "/>
		</form>
	</body>
</html>