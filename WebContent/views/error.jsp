<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>User Error.</title>
<script type="text/javascript">
$.post('getRest',{"s":s}).success(function(data){
	$('#doc-res').val(data);
	var swfMap = document.getElementById("Map");
	swfMap.getData(data);
	}).error(function(data){
	alert("系统出错");
}); 
</script>
</head>
<body>
<h2>User Error.</h2>
</body>
</html>