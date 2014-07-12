<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>套打票据</title>
		<jc:plugin name="jquery" />
		<jc:plugin name="default" />
		<jc:plugin name="print" />
		<object id="LODOP"
			classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0 >
			<embed id="LODOP_EM" type="application/x-print-lodop" width=0
				height=0 pluginspage="<ua:attachment url="/upload/cedu_print/install_lodop.exe" />"></embed>
		</object>
	</head>
	<body>
		<a href="javascript:;" onclick="javascript:Design1();">模板设计</a>
		<br>
		<a href="javascript:;" onclick="javascript:Preview1();">模板的打印预览</a>
		<br>
		<a href="javascript:;" onclick="javascript:RealPrint();">模板的打印</a>
		<script language="javascript" type="text/javascript">
			var LODOP; //声明为全局变量   
			function Preview1() {
				CreateFullBill();
				LODOP.PREVIEW();
			};
			function Design1() {
				CreateFullBill();
				LODOP.PRINT_DESIGN();
			};
			function RealPrint() {		
				CreateFullBill();
				if (LODOP.PRINTA()) 
				   alert("已发出实际打印命令！"); 
				else 
				   alert("放弃打印！"); 
			};
		
			function CreateFullBill() {
				LODOP = getLodop(document.getElementById('LODOP'), document
						.getElementById('LODOP_EM'));
		
				
				LODOP.SET_PRINT_PAGESIZE(1,2400,920,"");
				LODOP.ADD_PRINT_TEXT(89,611,100,21,"132 8888 8888");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.ADD_PRINT_TEXT(89,213,65,21,"张三");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.ADD_PRINT_TEXT(115,212,139,21," 北京外国语大学");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.ADD_PRINT_TEXT(115,404,68,20,"20110903");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.ADD_PRINT_TEXT(115,675,89,20,"计算机专业");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.ADD_PRINT_TEXT(141,408,79,21,"60.00");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.ADD_PRINT_TEXT(141,213,80,21,"5000.00");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.ADD_PRINT_TEXT(168,413,90,20,"800.00");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.ADD_PRINT_TEXT(140,615,100,21,"500.00");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
				LODOP.ADD_PRINT_TEXT(167,211,100,20,"50.00");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.SET_PRINT_STYLEA(0,"Italic",1);
				LODOP.ADD_PRINT_TEXT(195,278,195,20,"陆仟陆佰元整");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.ADD_PRINT_TEXT(115,533,81,20,"高起专");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.ADD_PRINT_TEXT(278,657,33,21,"2011");
				LODOP.ADD_PRINT_TEXT(278,704,23,21,"11");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.ADD_PRINT_TEXT(278,742,23,21,"10");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.ADD_PRINT_TEXT(196,561,164,20,"6600.00");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.ADD_PRINT_TEXT(89,404,166,21,"142201 8888 8888 8888");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.ADD_PRINT_TEXT(169,625,100,20,"400.00");
				LODOP.ADD_PRINT_TEXT(234,406,100,20,"弘成学苑");
				LODOP.ADD_PRINT_TEXT(234,611,100,20,"晓佳");
		
			};
		</script>
		
	</body>
</html>