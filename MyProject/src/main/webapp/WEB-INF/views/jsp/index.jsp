
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="springForm"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
           
           <%@ page errorPage="error.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Videocean</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="My Play Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- bootstrap -->
<link href="css/bootstrap.min.css" rel='stylesheet' type='text/css' media="all" />
<!-- //bootstrap -->
<link href="css/dashboard.css" rel="stylesheet">
<!-- Custom Theme files -->
<link href="css/style.css" rel='stylesheet' type='text/css' media="all" />
<script src="js/jquery-1.11.1.min.js"></script>
<!--start-smoth-scrolling-->
<!-- fonts -->
<link href='//fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
<!-- //fonts -->
</head>
  <body>
  <c:if test="${sessionScope.user != null}">
   <!-- There is a user **attribute** in the session -->  
    <jsp:include page="loggedHeader.jsp" />
      <jsp:include page="loggedSidebar.jsp" />
</c:if>

  <c:if test="${sessionScope.user == null}">
   <!-- There is no user **attribute** in the session -->  
    <jsp:include page="header.jsp" />
      <jsp:include page="sidebar.jsp" />
</c:if>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="main-grids">
				<div class="top-grids">
					<div class="recommended-info">
						<h3>Recent Videos</h3>
					</div>
					
	               	<c:forEach var="clip" items="${subClips}" step="1" begin="0" end="2">
					<div class="col-md-4 resent-grid recommended-grid slider-top-grids">
						<div class="resent-grid-img recommended-grid-img">
							<a href="single-${clip.clipID}">   <video width="360" height="200" ><source src="load-${clip.clipID}" ></source></video></a>
							<!--  <div class="time">
								<p>3:</p>
							</div>
							<div class="clck">
								<span class="glyphicon glyphicon-time" aria-hidden="true"></span>
							</div>-->
						</div>
						<div class="resent-grid-info recommended-grid-info">
							<h3><a href="single-${clip.clipID}" class="title title-info">${clip.name}</a></h3>
							<ul>
								<li><p class="author author-info"><a href="user-${clip.owner.userID}" class="author"> ${clip.owner.fullName}</a></p></li>
								<li class="right-list"><p class="views views-info"> ${clip.views} views</p></li>
							</ul>
						</div>
					</div>
					</c:forEach>
					
					<div class="clearfix"> </div>
				</div>
				<div class="recommended">
					<div class="recommended-grids">
						<div class="recommended-info">
							<h3></h3>
						</div>
						<script src="js/responsiveslides.min.js"></script>
						 <!--  <script>
							// You can also use "$(window).load(function() {"
							$(function () {
							  // Slideshow 4
							  $("#slider3").responsiveSlides({
								auto: true,
								pager: false,
								nav: true,
								speed: 500,
								namespace: "callbacks",
								before: function () {
								  $('.events').append("<li>before event fired.</li>");
								},
								after: function () {
								  $('.events').append("<li>after event fired.</li>");
								}
							  });
						
							});
						  </script>-->
						<div  id="top" class="callbacks_container">
							<ul class="rslides" id="slider3">
								<li>
									<div class="animated-grids">
									
										
						      <c:forEach var="cl" items="${clips}" step="1" begin="0" end="19">
										<div class="col-md-3 resent-grid recommended-grid slider-first">
											<div class="resent-grid-img recommended-grid-img">
												<a href="single-${cl.clipID}"> <video width="260" height="180"><source src="load-${cl.clipID}" ></source></video></a>
												<!-- <div class="time small-time slider-time">
													<p>4:34</p>
												</div>
												<div class="clck small-clck">
													<span class="glyphicon glyphicon-time" aria-hidden="true"></span>
												</div>-->
											</div>
											<div class="resent-grid-info recommended-grid-info">
												<h5><a href="single-${cl.clipID}" class="title">${cl.name}</a></h5>
												<div class="slid-bottom-grids">
													<div class="slid-bottom-grid">
														<p class="author author-info"><a href="user-${cl.owner.userID}" class="author">${cl.owner.fullName}</a></p>
													</div>
													<div class="slid-bottom-grid slid-bottom-right">
														<p class="views views-info">${cl.views} views</p>
													</div>
													<div class="clearfix"> </div>
												</div>
											</div>
										</div>
										</c:forEach>
										<div class="clearfix"> </div>
									</div>
								</li>
								<a href="loadprevious" >Previous Page</a>
							<a href="loadmore" >Next Page</a>
							</ul>
						</div>
					</div>
				</div>
				
				
				
				
				
				
				
				
				
				
		 	<div class="recommended">
					<div class="recommended-grids">
						<div class="recommended-info">
						</div>
						
						
					
						
						<div class="clearfix"> </div>
					</div>
					<div class="recommended-grids">
						
						
					
						
						<div class="clearfix"> </div>
					</div>
				</div>
			</div>
			<!-- footer -->
			<jsp:include page="footer.jsp" />
			<!-- //footer -->
		</div>
		<div class="clearfix"> </div>
	<div class="drop-menu">
		<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu4">
		  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Regular link</a></li>
		  <li role="presentation" class="disabled"><a role="menuitem" tabindex="-1" href="#">Disabled link</a></li>
		  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Another link</a></li>
		</ul>
	</div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
  </body>
</html>