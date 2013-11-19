<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	boolean flag = false;
	if(session.getAttribute("userName") == null)
		flag = false;
	else
		flag = true;
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>网上订餐系统</title>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap-combined.min.css"/>
    <link rel="stylesheet" type="text/css" href="assets/css/layoutit.css"/>

	<!--<link rel="stylesheet" type="text/css" href="assets/css/style.css">-->
</head>