<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>User Error.</title>
<script src="assets/js/jquery.js"></script>
<script type="text/javascript">
$(document).ready(
function(){
$.post('getRestaurant',{"location":"北京"}).success(function(data){
	var obj = eval ("(" + data + ")");
	var i = 0;
	var strHtml = "";
	while(i < obj.total){
		strHtml += '<li class="span3"><div class="thumbnail"><img data-src="holder.js/300x200" src="';
		strHtml += obj.rows[i].restaurantImg;
		strHtml += '"><h2>';
		strHtml += obj.rows[i].restaurantName;
		strHtml += '</h2></div></li>';
		i ++;
	}

	alert(strHtml);
	}).error(function(data){
	alert("系统出错");
});
}
);
</script>
</head>
<body>
<h2>User Error.</h2>
</body>
</html>