
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="springForm"
	uri="http://www.springframework.org/tags/form"%>

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
  <!-- proverki -->
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
				<div class="main-grids about-main-grids">
					<div class="recommended-info">
						<h3>About Videocean</h3>
						<p class="history-text">We started yesterday!
						</p>
						<div class="about-grids">
							<div class="about-bottom-grids">
								<div class="col-sm-6 about-left">
									<div class="about-left-grids">
										<div class="col-md-2 about-left-img">
											<span class="glyphicon glyphicon-user glyphicon-facetime-video" aria-hidden="true"></span>
										</div>
										<div class="col-md-10 about-left-info">
											<a href="#">When We Started</a>
											<p>Launched in May 2005, Videocean allows billions of people to discover, watch and share originally-created videos. YouTube provides a forum for people to connect, inform, and inspire others across the globe and acts as a distribution platform for original content creators and advertisers large and small.</p>
										</div>
										<div class="clearfix"> </div>
									</div>
								</div>
								<div class="col-sm-6 about-right">
									<div class="about-left-grids">
										<div class="col-md-2 about-left-img">
											<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
										</div>
										<div class="col-md-10 about-left-info">
											<a href="#">Community Guidelines</a>
											<p>Launched in May 2005, Videocean allows billions of people to discover, watch and share originally-created videos. YouTube provides a forum for people to connect, inform, and inspire others across the globe and acts as a distribution platform for original content creators and advertisers large and small.</p>
										</div>
										<div class="clearfix"> </div>
									</div>
								</div>
								<div class="clearfix"> </div>
							</div>
							<div class="about-bottom-grids">
								<div class="col-sm-6 about-left">
									<div class="about-left-grids">
										<div class="col-md-2 about-left-img">
											<span class="glyphicon glyphicon-user glyphicon-book" aria-hidden="true"></span>
										</div>
										<div class="col-md-10 about-left-info">
											<a href="#">About Our Site</a>
										<p>Launched in May 2005, Videocean allows billions of people to discover, watch and share originally-created videos. YouTube provides a forum for people to connect, inform, and inspire others across the globe and acts as a distribution platform for original content creators and advertisers large and small.</p>
										</div>
										<div class="clearfix"> </div>
									</div>
								</div>
								<div class="col-sm-6 about-right">
									<div class="about-left-grids">
										<div class="col-md-2 about-left-img">
											<span class="glyphicon glyphicon-user glyphicon-headphones" aria-hidden="true"></span>
										</div>
										<div class="col-md-10 about-left-info">
											<a href="#">New Update</a>
											<p>Launched in May 2005, Videocean allows billions of people to discover, watch and share originally-created videos. YouTube provides a forum for people to connect, inform, and inspire others across the globe and acts as a distribution platform for original content creators and advertisers large and small.</p>
											</div>
										<div class="clearfix"> </div>
									</div>
								</div>
								<div class="clearfix"> </div>
							</div>
							<div class="about-bottom-grids">
								<div class="col-sm-6 about-left">
									<div class="about-left-grids">
										<div class="col-md-2 about-left-img">
											<span class="glyphicon glyphicon-user glyphicon-folder-open" aria-hidden="true"></span>
										</div>
										<div class="col-md-10 about-left-info">
											<a href="#">Careers</a>
											<p>Launched in May 2005, YouTube allows billions of people to discover, watch and share originally-created videos. YouTube provides a forum for people to connect, inform, and inspire others across the globe and acts as a distribution platform for original content creators and advertisers large and small.</p>
											</div>
										<div class="clearfix"> </div>
									</div>
								</div>
								<div class="col-sm-6 about-right">
									<div class="about-left-grids">
										<div class="col-md-2 about-left-img">
											<span class="glyphicon glyphicon-user glyphicon-phone" aria-hidden="true"></span>
										</div>
										<div class="col-md-10 about-left-info">
											<a href="#">Contact Us</a>
											<p>Launched in May 2005, YouTube allows billions of people to discover, watch and share originally-created videos. YouTube provides a forum for people to connect, inform, and inspire others across the globe and acts as a distribution platform for original content creators and advertisers large and small.</p>
										</div>
										<div class="clearfix"> </div>
									</div>
								</div>
								<div class="clearfix"> </div>
							</div>
						</div>
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