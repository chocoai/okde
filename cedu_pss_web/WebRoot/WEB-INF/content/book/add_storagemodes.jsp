<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
 	<title>入库方式</title>
  </head>
  
  <body>
  <form action="add_storagemode" method="post">
    <table border="1">
    	<tr><td>编&nbsp;&nbsp;&nbsp; 码</td><td><input name="storagemode.code"></td></tr>
    	<tr><td>类别名称</td><td><input name="storagemode.name"></td></tr>
    	<tr><td colspan="2"><input type="submit" value="保存"></td></tr>
    	<tr><td colspan="2" id="addtab">${resultiterate}</td></tr>   	
    </table>
  </form>
  </body>
</html>
