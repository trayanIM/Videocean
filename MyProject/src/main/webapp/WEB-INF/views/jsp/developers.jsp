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

    <c:if test="${sessionScope.user != null}">
   <!-- There is a user **attribute** in the session -->  
     <jsp:include page="loggedHeader.jsp" />
</c:if>

 <c:if test="${sessionScope.user == null}">
   <!-- There is a user **attribute** in the session -->  
    <jsp:include page="header.jsp" />
</c:if>
    
		<div class="developers">
			<div class="developers-banner">
				<!-- container -->
				<div class="container">
					<div class="developers-info">
						<h3>For Developers</h3>
						<p>We provide services for you</p>
					</div>
				</div>
				<!-- //container -->
			</div>
			<div class="developers-grids">
				<!-- container -->
				<div class="container">
					<div class="developers-grid">
						<a href="single">Demos</a>
						<p>Nunc vitae rutrum enim. Mauris at volutpat leo. Vivamus dapibus mi ut elit fermentum tincidunt. Vestibulum lobortis mauris maximus magna consectetur, non commodo velit vestibulum. Phasellus rutrum odio eu metus dignissim, nec feugiat nisi tempus. Nunc non nibh sagittis, rhoncus neque tempus, efficitur dolor. Suspendisse ornare sapien vitae mauris pharetra, sed eleifend ante luctus. Fusce mollis tortor sit amet ipsum suscipit semper.</p>
					</div>
					<div class="developers-grid">
						<a href="single">API Resources</a>
						<p>${json}</p>
					</div>
					<div class="developers-grid">
						<a href="single">Showcase</a>
						<p>Integer sapien enim, suscipit ac convallis et, feugiat ac est. Suspendisse felis quam, iaculis eget metus vel, semper tempus quam. Integer eu mauris ante. Nulla fringilla lectus ac metus sodales suscipit. Cras non purus tincidunt, semper elit vel, porttitor turpis. Sed imperdiet, ex quis consequat congue, justo urna aliquam diam, a pretium nulla arcu sed nisi. Nam mi turpis, imperdiet ornare sodales vitae, cursus eu sem.</p>
					</div>
					<div class="developers-grid">
						<a href="single">Developer Stories</a>
						<p>Mauris vehicula rutrum velit, a scelerisque eros volutpat quis. Curabitur vel molestie leo, et sodales diam. Donec sem nibh, mattis non cursus nec, elementum ac lacus. In hac habitasse platea dictumst. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aenean vitae ipsum fermentum, commodo lectus et, euismod justo. Praesent congue lorem a quam porttitor, a laoreet felis fringilla.</p>
					</div>
				</div>
				<!-- //container -->
			</div>
		</div>
			<!-- footer -->
			<jsp:include page="footer.jsp" />
			<!-- //footer -->
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