
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
<meta name="keywords" content="" />
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
      <!--// proverki -->
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="show-top-grids">
				<div class="col-sm-10 show-grid-left main-grids">
					<div class="recommended">
						<div class="recommended-grids english-grid">
							<div class="recommended-info">
								<div class="heading">
									<h3>${category.name}</h3>
								</div>
								<!--  <div class="heading-right">
									<a  href="#small-dialog8" class="play-icon popup-with-zoom-anim">Subscribe</a>
								</div>-->
								<div class="clearfix"> </div>
							</div>
						
					<c:forEach var="clip" varStatus="status" items="${clips}" step="1" begin="0" end="7">
				
							<div class="col-md-3 resent-grid recommended-grid movie-video-grid">
								<div class="resent-grid-img recommended-grid-img">
									<a href="single-${clip.clipID}"> <video width="210" height="140" ><source src="load-${clip.clipID}" ></a>
									<!-- <div class="time small-time show-time movie-time">
										<p>2:06</p>
									</div>
									<div class="clck movie-clock">
										<span class="glyphicon glyphicon-time" aria-hidden="true"></span>
									</div>-->
								</div>
								<div class="resent-grid-info recommended-grid-info recommended-grid-movie-info">
									<h5><a href="single-${clip.clipID}" class="title">${clip.name}</a></h5>
									<ul>
										<li><p class="author author-info"><a href="user-${clip.owner.userID}" class="author">${clip.owner.fullName}</a></p></li>
										<li class="right-list"><p class="views views-info">${clip.views} views</p></li>
									</ul>
								</div>
							</div>
								 <c:if test="${status.count%4==0}" > 
					 <br>
					 <br>
					 <br>
					 <br>
					 </c:if>
							</c:forEach>
							 <c:if test="${not empty errorMessage}">
       <p style="color:red"> 
   Error: ${errorMessage}
   </p>
</c:if>
							<div class="clearfix"> </div>
						</div>
					</div>
					<div class="recommended">
						<div class="recommended-grids">
							<div class="recommended-info">
								<div class="heading">
									<h3></h3>
								</div>
								<div class="heading-right">
									<!-- <a  href="#small-dialog8" class="play-icon popup-with-zoom-anim">Subscribe</a>-->
								</div>
								<div class="clearfix"> </div>
							</div>
							
						
							<div class="clearfix"> </div>
						</div>
					</div>
					<div class="recommended">
						<div class="recommended-grids">
							<div class="recommended-info">
								<div class="heading">
									<h3></h3>
								</div>
								<div class="heading-right">
									<!-- <a  href="#small-dialog8" class="play-icon popup-with-zoom-anim">Subscribe</a>-->
								</div>
								<div class="clearfix"> </div>
							</div>
						
						
							
						
							<div class="clearfix"> </div>
						</div>
					</div>
					<div class="recommended">
						<div class="recommended-grids">
							<div class="recommended-info">
								<div class="heading">
									<h3></h3>
								</div>
								<div class="heading-right">
									<!-- ><a  href="#small-dialog8" class="play-icon popup-with-zoom-anim">Subscribe</a>-->
								</div>
								<div class="clearfix"> </div>
							</div>
						
						
						
						
							<div class="clearfix"> </div>
						</div>
					</div>
				</div>
				<div class="col-md-2 show-grid-right">
					<h3>Categories</h3>
					<div class="show-right-grids">
						<ul>
							<li class="tv-img"><a href="categories-1"><img src="images/mv.png" alt="" /></a></li>
							<li><a href="categories-1">Trending</a></li>
						</ul>
					</div>
					<div class="show-right-grids">
						<ul>
							<li class="tv-img"><a href="categories-2"><img src="images/mv.png" alt="" /></a></li>
							<li><a href="categories-2">Sports</a></li>
						</ul>
					</div>
					<div class="show-right-grids">
						<ul>
							<li class="tv-img"><a href="categories-3"><img src="images/mv.png" alt="" /></a></li>
							<li><a href="categories-3">Science</a></li>
						</ul>
					</div>
					<div class="show-right-grids">
						<ul>
							<li class="tv-img"><a href="categories-4"><img src="images/mv.png" alt="" /></a></li>
							<li><a href="categories-4">Music</a></li>
						</ul>
					</div>
					<div class="show-right-grids">
						<ul>
							<li class="tv-img"><a href="categories-5"><img src="images/mv.png" alt="" /></a></li>
							<li><a href="categories-5">Games</a></li>
						</ul>
					</div>
					<div class="show-right-grids">
						<ul>
							<li class="tv-img"><a href="categories-6"><img src="images/mv.png" alt="" /></a></li>
							<li><a href="categories-6">Movies</a></li>
						</ul>
					</div>
					<div class="show-right-grids">
						<ul>
							<li class="tv-img"><a href="categories-7"><img src="images/mv.png" alt="" /></a></li>
							<li><a href="categories-7">Other</a></li>
						</ul>
					</div>
				</div>
				<div class="clearfix"> </div>
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