<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="springForm"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
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
				<div class="main-grids news-main-grids">
					<div class="recommended-info">
						<h3>Data Security and Privacy Policy	</h3>
						<p class="history-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus efficitur, eros 
							sed suscipit porttitor, diam felis tempus odio, eget sollicitudin purus sem sit amet dolor. Integer euismod 
							non mauris commodo rutrum. Nulla risus felis, rhoncus vel est sed, consequat efficitur ante. Phasellus mi 
							sapien, accumsan vitae lobortis vitae, laoreet dapibus metus. Pellentesque id ipsum vel nibh imperdiet 
							imperdiet ac ac mauris. Suspendisse ac leo augue. Nullam venenatis massa ut pulvinar scelerisque. Duis vel 
							vehicula urna. Quisque semper vitae lectus a feugiat. Sed dignissim egestas nunc, nec suscipit mauris 
							interdum lobortis.
						</p>
						<div class="terms-info">
							<div class="terms-info-grid">
								<h3>1. Personal information</h3>
								<p>Pellentesque consequat ligula dui, in aliquam libero pellentesque sit amet. Ut viverra, urna vitae 
									volutpat rhoncus, lectus sem tincidunt mauris, eu pretium neque urna nec lorem. Mauris cursus rhoncus 
									feugiat. Proin quis nisl augue. Nulla eleifend velit neque, id cursus erat interdum sit amet.
								</p>
								<ol>
									<li><p class="terms-info-text">Pellentesque consequat ligula dui, in aliquam libero pellentesque sit amet. Ut viverra, urna vitae 
											volutpat rhoncus, lectus sem tincidunt mauris, eu pretium neque urna nec lorem. Mauris cursus rhoncus 
											feugiat. Proin quis nisl augue. Nulla eleifend velit neque, id cursus erat interdum sit amet.
										</p>
									</li>
									<li><p class="terms-info-text">Pellentesque consequat ligula dui, in aliquam libero pellentesque sit amet. Ut viverra, urna vitae 
											volutpat rhoncus, lectus sem tincidunt mauris, eu pretium neque urna nec lorem. Mauris cursus rhoncus 
											feugiat. Proin quis nisl augue. Nulla eleifend velit neque, id cursus erat interdum sit amet.
										</p>
									</li>
									<li><p class="terms-info-text">Pellentesque consequat ligula dui, in aliquam libero pellentesque sit amet. Ut viverra, urna vitae 
											volutpat rhoncus, lectus sem tincidunt mauris, eu pretium neque urna nec lorem. Mauris cursus rhoncus 
											feugiat. Proin quis nisl augue. Nulla eleifend velit neque, id cursus erat interdum sit amet.
										</p>
									</li>
								</ol>
							</div>
							<div class="terms-info-grid">
								<h3>2. Other information</h3>
								<p>Pellentesque consequat ligula dui, in aliquam libero pellentesque sit amet. Ut viverra, urna vitae 
									volutpat rhoncus, lectus sem tincidunt mauris, eu pretium neque urna nec lorem. Mauris cursus rhoncus 
									feugiat. Proin quis nisl augue. Nulla eleifend velit neque, id cursus erat interdum sit amet.Mauris 
									cursus rhoncus feugiat. Proin quis nisl augue. Nulla eleifend velit neque, id cursus erat interdum 
									sit amet. Curabitur vel neque vel sapien pulvinar placerat. Cras eget leo id risus vestibulum 
									dapibus. Class aptent taciti sociosqu 
								</p>
								<ol>
									<li><p class="terms-info-text">Pellentesque consequat ligula dui, in aliquam libero pellentesque sit amet. Ut viverra, urna vitae 
											volutpat rhoncus, lectus sem tincidunt mauris, eu pretium neque urna nec lorem. Mauris cursus rhoncus 
											feugiat. Proin quis nisl augue. Nulla eleifend velit neque, id cursus erat interdum sit amet.
										</p>
									</li>
									<li><p class="terms-info-text">Pellentesque consequat ligula dui, in aliquam libero pellentesque sit amet. Ut viverra, urna vitae 
											volutpat rhoncus, lectus sem tincidunt mauris, eu pretium neque urna nec lorem. Mauris cursus rhoncus 
											feugiat. Proin quis nisl augue. Nulla eleifend velit neque, id cursus erat interdum sit amet.
										</p>
									</li>
									<li><p class="terms-info-text">Pellentesque consequat ligula dui, in aliquam libero pellentesque sit amet. Ut viverra, urna vitae 
											volutpat rhoncus, lectus sem tincidunt mauris, eu pretium neque urna nec lorem. Mauris cursus rhoncus 
											feugiat. Proin quis nisl augue. Nulla eleifend velit neque, id cursus erat interdum sit amet.
										</p>
									</li>
									<li><p class="terms-info-text">Pellentesque consequat ligula dui, in aliquam libero pellentesque sit amet. Ut viverra, urna vitae 
											volutpat rhoncus, lectus sem tincidunt mauris, eu pretium neque urna nec lorem. Mauris cursus rhoncus 
											feugiat. Proin quis nisl augue. Nulla eleifend velit neque, id cursus erat interdum sit amet.
										</p>
									</li>
									<li><p class="terms-info-text">Pellentesque consequat ligula dui, in aliquam libero pellentesque sit amet. Ut viverra, urna vitae 
											volutpat rhoncus, lectus sem tincidunt mauris, eu pretium neque urna nec lorem. Mauris cursus rhoncus 
											feugiat. Proin quis nisl augue. Nulla eleifend velit neque, id cursus erat interdum sit amet.
										</p>
									</li>
								</ol>
							</div>
							<div class="terms-info-grid">
								<h3>3. Use of information</h3>
								<p>Pellentesque consequat ligula dui, in aliquam libero pellentesque sit amet. Ut viverra, urna vitae 
									volutpat rhoncus, lectus sem tincidunt mauris, Mauris cursus rhoncus feugiat. Proin quis 
									nisl augue. Nulla eleifend velit neque, id cursus erat interdum sit amet. Curabitur vel neque vel 
									sapien pulvinar placerat. Cras eget leo id risus vestibulum dapibus. Class aptent taciti sociosqu eu 
									pretium neque urna nec lorem. Mauris cursus rhoncus Nulla eleifend velit neque, id cursus erat interdum sit amet.
								</p>
								<ol>
									<li><p class="terms-info-text">Pellentesque consequat ligula dui, in aliquam libero pellentesque sit amet. Ut viverra, urna vitae 
											volutpat rhoncus, lectus sem tincidunt mauris, eu pretium neque urna nec lorem. Mauris cursus rhoncus 
											feugiat. Proin quis nisl augue. Nulla eleifend velit neque, id cursus erat interdum sit amet.
										</p>
									</li>
									<li><p class="terms-info-text">Pellentesque consequat ligula dui, in aliquam libero pellentesque sit amet. Ut viverra, urna vitae 
											volutpat rhoncus, lectus sem tincidunt mauris, eu pretium neque urna nec lorem. Mauris cursus rhoncus 
											feugiat. Proin quis nisl augue. Nulla eleifend velit neque, id cursus erat interdum sit amet.
										</p>
									</li>
								</ol>
							</div>
							<div class="terms-info-grid">
								<h3>4. Cookies</h3>
								<p>Pellentesque consequat ligula dui, in aliquam libero pellentesque sit amet. Ut viverra, urna vitae 
									volutpat rhoncus, lectus sem tincidunt mauris, eu pretium neque urna nec lorem. Mauris cursus rhoncus 
									feugiat. Proin quis nisl augue. Nulla eleifend velit neque, id cursus erat interdum sit amet.lectus 
									sem tincidunt mauris, Mauris cursus rhoncus feugiat.
								</p>
								<ol>
									<li><p class="terms-info-text">Pellentesque consequat ligula dui, in aliquam libero pellentesque sit amet. Ut viverra, urna vitae 
											volutpat rhoncus, lectus sem tincidunt mauris, eu pretium neque urna nec lorem. Mauris cursus rhoncus 
											feugiat. Proin quis nisl augue. Nulla eleifend velit neque, id cursus erat interdum sit amet.
										</p>
									</li>
									<li><p class="terms-info-text">Pellentesque consequat ligula dui, in aliquam libero pellentesque sit amet. Ut viverra, urna vitae 
											volutpat rhoncus, lectus sem tincidunt mauris, eu pretium neque urna nec lorem. Mauris cursus rhoncus 
											feugiat. Proin quis nisl augue. Nulla eleifend velit neque, id cursus erat interdum sit amet.
										</p>
									</li>
									<li><p class="terms-info-text">Pellentesque consequat ligula dui, in aliquam libero pellentesque sit amet. Ut viverra, urna vitae 
											volutpat rhoncus, lectus sem tincidunt mauris, eu pretium neque urna nec lorem. Mauris cursus rhoncus 
											feugiat. Proin quis nisl augue. Nulla eleifend velit neque, id cursus erat interdum sit amet.
										</p>
									</li>
									<li><p class="terms-info-text">Pellentesque consequat ligula dui, in aliquam libero pellentesque sit amet. Ut viverra, urna vitae 
											volutpat rhoncus, lectus sem tincidunt mauris, eu pretium neque urna nec lorem. Mauris cursus rhoncus 
											feugiat. Proin quis nisl augue. Nulla eleifend velit neque, id cursus erat interdum sit amet.
										</p>
									</li>
									<li><p class="terms-info-text">Pellentesque consequat ligula dui, in aliquam libero pellentesque sit amet. Ut viverra, urna vitae 
											volutpat rhoncus, lectus sem tincidunt mauris, eu pretium neque urna nec lorem. Mauris cursus rhoncus 
											feugiat. Proin quis nisl augue. Nulla eleifend velit neque, id cursus erat interdum sit amet.
										</p>
									</li>
								</ol>
							</div>
							<div class="terms-info-grid">
								<h3>5. Vivamus pharetra ante convallis</h3>
								<p>Pellentesque consequat ligula dui, in aliquam libero pellentesque sit amet. Ut viverra, urna vitae 
									volutpat rhoncus, lectus sem tincidunt mauris, eu pretium neque urna nec lorem. Mauris cursus rhoncus 
									feugiat. Proin quis nisl augue. Nulla eleifend velit neque, Vivamus pharetra, ante convallis aliquam sodales, 
									arcu dolor consectetur eros, a consequat nisl risus sit amet nuncVivamus pharetra, ante convallis aliquam sodales, 
									arcu dolor consectetur eros, a consequat nisl risus sit amet nuncVivamus pharetra, ante convallis aliquam sodales, 
									arcu dolor consectetur eros, a consequat nisl risus sit amet nuncid cursus erat interdum sit amet.
								</p>
							</div>
							<div class="terms-info-grid">
								<h3>6. Security</h3>
								<ol>
									<li><p class="terms-info-text">Pellentesque consequat ligula dui, in aliquam libero pellentesque sit amet. Ut viverra, urna vitae 
											volutpat rhoncus, lectus sem tincidunt mauris, eu pretium neque urna nec lorem. Mauris cursus rhoncus 
											feugiat. Proin quis nisl augue. Nulla eleifend velit neque, id cursus erat interdum sit amet.
										</p>
									</li>
									<li><p class="terms-info-text">Pellentesque consequat ligula dui, in aliquam libero pellentesque sit amet. Ut viverra, urna vitae 
											volutpat rhoncus, lectus sem tincidunt mauris, eu pretium neque urna nec lorem. Mauris cursus rhoncus 
											feugiat. Proin quis nisl augue. Nulla eleifend velit neque, id cursus erat interdum sit amet.
										</p>
									</li>
									<li><p class="terms-info-text">Pellentesque consequat ligula dui, in aliquam libero pellentesque sit amet. Ut viverra, urna vitae 
											volutpat rhoncus, lectus sem tincidunt mauris, eu pretium neque urna nec lorem. Mauris cursus rhoncus 
											feugiat. Proin quis nisl augue. Nulla eleifend velit neque, id cursus erat interdum sit amet.
										</p>
									</li>
								</ol>
							</div>
							<div class="terms-info-grid">
								<h3>7. Access and alteration</h3>
								<p>Pellentesque consequat ligula dui, in aliquam libero pellentesque sit amet. Ut viverra, urna vitae 
									volutpat rhoncus, lectus sem tincidunt mauris, eu pretium neque urna nec lorem. Mauris cursus rhoncus 
									feugiat. Proin quis nisl augue. Vivamus pharetra, ante convallis aliquam sodales, arcu dolor consectetur 
									eros, a consequat nisl risus sit amet nuncNulla eleifend velit neque, id cursus erat interdum sit amet.
								</p>
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