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

<body>

<div id="colorBar"></div>

<div class="container wrapper wrapperWhite">
<div class="container">

    <div class="row" id="row-logo">
    	<div class="span12 navbar bufferBottom">
    		<img src="assets/img/logo.png" class="imgLogo" />
            
            

                <div class="nav-collapse collapse">
                  <ul class="nav" role="navigation">
                    <li class="dropdown cityChoice">
                      <a id="drop1" href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">北京 <b class="caret"></b></a>
                      <ul class="dropdown-menu" role="menu" aria-labelledby="drop1">
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="http://google.com">上海</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#anotherAction">广州</a></li>
                      </ul>
                    </li>
                  </ul>
                </div>

            
            
            
    		<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            	<span class="icon-bar"></span>
            	<span class="icon-bar"></span>
            	<span class="icon-bar"></span>
            </a>
            <%
            	if(flag == false){
            %>
            <div id="navList" class="nav-collapse collapse">
            	<div><a href="#logInModal" data-toggle="modal">LOG IN</a></div>
                <div>|</div>
                <div><a href="#signUpModal" data-toggle="modal">SIGN UP</a></div>
            </div>
            <%
            	}else{
            %>
            <div id="userInfo" class="nav-collapse collapse">
            	<div>
                	<a id="dropInfo" href="#" data-toggle="dropdown">
                    	<i class="icon-user"></i> &nbsp;<%=session.getAttribute("userName") %>
                    </a>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="drop1">
                    	<li role="presentation"><a role="menuitem" tabindex="-1" href="user/UserLogout">查看订单</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="UserLogout">退出登录</a></li>
                	</ul>
                </div>
            </div>
            <%
            	}
            %>
        </div>
	</div>

	<div class="row"><div class="span12 topBorder bufferBottom20"></div></div>

	<div class="row-fluid">
		<div class="span12">
			<div class="carousel slide" id="carousel-276302"  data-ride="carousel">
				<ol class="carousel-indicators">
					<li class="active" data-slide-to="0" data-target="#carousel-276302"></li>
					<li data-slide-to="1" data-target="#carousel-276302"></li>
					<li data-slide-to="2" data-target="#carousel-276302"></li>
				</ol>
				<div class="carousel-inner">
					<div class="item active">
						<img alt="" src="assets/img/1.jpg" />
						<div class="carousel-caption">
							<h4>棒球</h4>
							<p>棒球运动是一种以棒打球为主要特点，集体性、对抗性很强的球类运动项目，在美国、日本尤为盛行。</p>
						</div>
					</div>
					<div class="item">
						<img alt="" src="assets/img/2.jpg" />
						<div class="carousel-caption">
							<h4>冲浪</h4>
							<p>冲浪是以海浪为动力，利用自身的高超技巧和平衡能力，搏击海浪的一项运动。运动员站立在冲浪板上，或利用腹板、跪板、充气的橡皮垫、划艇、皮艇等驾驭海浪的一项水上运动。</p>
						</div>
					</div>
					<div class="item">
						<img alt="" src="assets/img/3.jpg" />
						<div class="carousel-caption">
							<h4>自行车</h4>
							<p>以自行车为工具比赛骑行速度的体育运动。1896年第一届奥林匹克运动会上被列为正式比赛项目。环法赛为最著名的世界自行车锦标赛。</p>
						</div>
					</div>
				</div>
                <a data-slide="prev" href="#carousel-276302" class="left carousel-control">‹</a>
                <a data-slide="next" href="#carousel-276302" class="right carousel-control">›</a>
			</div>
		</div>
	</div>
    
</div>
</div>

<div class="container wrapper wrapperBrown">
	<div class="container">
    	<div class="container">
    	<ul class="thumbnails" id="rstList">
    		
        </ul>
    </div>
    </div>
</div>

<div class="container wrapper" id="wrapperFooter">
	<div class="container" id="footerContent">
		<div class="row">
			<div class="span12" id="footerCopyright">Copyright 2013&nbsp;&nbsp; Chen Fengjiao &amp; Liu Tianpeng &amp; Yin Ruiping</div>
		</div>
    </div>
</div>


<!-- Modal -->
<div id="logInModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="logInModalLabel" aria-hidden="true">
	<div class="modal-header">
    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    	<h3 id="logInModalLabel">LOG IN</h3>
	</div>
	<form class="form-horizontal loginDialog" action="UserLogin" method="post" acceptcharset="UTF-8">
		<div class="modal-body">
			<div class="control-group">
    			<label class="control-label" for="inputEmail">Email</label>
    			<div class="controls">
					<input type="text" id="inputEmail" placeholder="Email" name="userName">
				</div>
			</div>
  			<div class="control-group">
    			<label class="control-label" for="inputPassword">Password</label>
    			<div class="controls">
      				<input type="password" id="inputPassword" placeholder="Password" name="userPwd">
    			</div>
			</div>
			<button class="btn btn-info" data-dismiss="modal" aria-hidden="true" type="submit">Log In</button>
	</div>
		<div class="modal-footer">
    	<button class="btn btn-info" data-dismiss="modal" aria-hidden="true" type="submit">Log In</button>
    	<button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
	</div>
	</form>
</div>

<div id="signUpModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="signUpModalLabel" aria-hidden="true">
	<div class="modal-header">
    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    	<h3 id="signUpModalLabel">SIGN UP</h3>
	</div>
    <form class="form-horizontal loginDialog" action="/User/Signin/" method="post" acceptcharset="UTF-8">
		<div class="modal-body">
			<div class="control-group">
    			<label class="control-label" for="inputEmail">Email</label>
    			<div class="controls">
					<input type="text" id="inputEmail" placeholder="Email">
				</div>
			</div>
  			<div class="control-group">
    			<label class="control-label" for="inputPassword">Password</label>
    			<div class="controls">
      				<input type="password" id="inputPassword" placeholder="Password">
    			</div>
			</div>
            <div class="control-group">
    			<label class="control-label" for="inputPassword">Confirm</label>
    			<div class="controls">
      				<input type="password" id="inputPassword" placeholder="Confirm">
    			</div>
			</div>
	</div>
		<div class="modal-footer">
    	<button class="btn btn-info" data-dismiss="modal" aria-hidden="true">Sign Up</button>
    	<button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
	</div>
	</form>
</div>


	<form class="form-horizontal loginDialog" action="UserLogin" method="post" acceptcharset="UTF-8">
		<input type="text" id="inputEmail" placeholder="Email" name="userName">
		<input type="password" id="inputPassword" placeholder="Password" name="userPwd">
	   	<button class="btn btn-info" data-dismiss="modal" aria-hidden="true" type="submit">Log In</button>
    	<button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
	</form>



<script src="assets/js/jquery.js"></script>
<script src="assets/js/bootstrap.min.js"></script>

<script type="text/javascript">
$(document).ready(
		function(){
		$.post('getRestaurant',{"location":"北京"}).success(function(data){
			var obj = eval ("(" + data + ")");
			var i = 0;
			var strHtml = "";
			while(i < obj.total){
				strHtml += '<li class="span3"><div class="thumbnail"><img width="300" height="200" data-src="holder.js/300x200" src="';
				strHtml += obj.rows[i].restaurantImg;
				strHtml += '"><h2>';
				strHtml += obj.rows[i].restaurantName;
				strHtml += '</h2></div></li>';
				i ++;
			}
			var $rstList = $("#rstList"); 
			$rstList.html(strHtml);
			}).error(function(data){
			alert("系统出错");
		});
		}
		);
</script>

</body>
</html>
