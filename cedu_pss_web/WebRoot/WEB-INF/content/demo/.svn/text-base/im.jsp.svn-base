<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<jc:plugin name="jquery" />
		<jc:plugin name="default" />
		 <script>
            $(document).ready(
                function () {
                   requestMessage_();
                }
            );
            function requestMessage_(){
            	 $.ajax({
                        url: "http://_www.hcxy.cn:8080/cedu/im/request_message",
                        timeout: 10000,
                        error: function (xmlHttpRequest, error) {
                           requestMessage_();
                        },
                        success: function (data, textStatus) {
                           $("#message").html(data.count);
                           requestMessage_();
                           
                        },
                    });
            }
        </script>
	</head>
	<body>
		<font style="color" id="message"></font>
	</body>
</html>