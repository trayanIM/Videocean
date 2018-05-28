<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="springForm"
	uri="http://www.springframework.org/tags/form"%>
<%@ page errorPage="error.jsp" %>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Videocean</title>
<!-- added -->
 <!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<!-- Graph CSS -->
<!-- added -->



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
   <!-- There is a user **attribute** in the session -->  
    <jsp:include page="loggedHeader.jsp" />
      <jsp:include page="loggedSidebar.jsp" />


 
     <br/>
      <br/>
       <br/>
       <!-- added -->
       <div class="outter-wp">
       <div class="custom-widgets">
												   <div class="row-one">
														
														<div class="col-md-3 widget states-mdl">
															<div class="stats-left">
																<h5>Alltime</h5>
																<h4>Views</h4>
															</div>
															<div class="stats-right">
																<label> ${views}</label>
															</div>
															<div class="clearfix"> </div>	
														</div>
														<div class="col-md-3 widget states-thrd">
															<div class="stats-left">
																<h5>All your</h5>
																<h4>Followers</h4>
															</div>
															<div class="stats-right">
																<label id="follow-count">${follow}</label>
															</div>
															<div class="clearfix"> </div>	
														</div>
														<div class="col-md-3 widget states-last">
															<div class="stats-left">
																 <h5>Alltime</h5>
																<h4>Likes</h4>
															</div>
															<div class="stats-right">
																<label>${likesOfClip}</label>
															</div>
															<div class="clearfix"> </div>	
														</div>
														<div class="clearfix"> </div>	
													</div>
												</div>	
												</div>		
													<div class="recommended-info">
								<div class="heading">
									<h3>${category.name}</h3>
								</div>
								<div class="heading-right">
									<a  href="#small-dialog8" class="play-icon popup-with-zoom-anim">Subscribe</a>
								</div>
								<div class="clearfix"> </div>
							</div>		
       
     <!-- added -->
      <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <!-- added subscribe-->
 
     
			<div class="show-top-grids">
				<div class="col-sm-10 show-grid-left main-grids">
					<div class="recommended">
						<div class="recommended-grids english-grid">
							<div class="recommended-info">
								<div class="heading">
									<h3>My Last Clips</h3>
								</div>
								 <!-- added subscribe-->
								     <div class="recommended-info">
								<div class="heading-right">
					
									<a  href="#small-dialog8" class="play-icon popup-with-zoom-anim" onclick="return saveFollowers();">Subscribe</a>
								</div>
								<div class="clearfix"> </div>
							</div>	
								<!-- added subscribe-->
								
									<script>
									function saveFollowers() {
										jQuery.ajax({
											  type: "POST",
											  url: "${currentURL}/follow",
											  success: function(result) {
												  console.log(result);
													var p =  JSON.parse(result);
													jQuery("#follow-count").html(p.follow);
						
											  }
											});
										return false;
									}
								</script>
								<div class="clearfix"> </div>
							</div>
							<c:forEach var="clip" items="${clips}" step="1" begin="0" end="2">
					<div class="col-md-4 resent-grid recommended-grid slider-top-grids">
						<div class="resent-grid-img recommended-grid-img">
							<a href="single-${clip.clipID}"><video width="292" height="250" ><source src="load-${clip.clipID}" ></source></video></a>
							<!-- <div class="time">
								<p>3:04</p>
							</div>
							<div class="clck">
								<span class="glyphicon glyphicon-time" aria-hidden="true"></span>
							</div>-->
						</div>
						<div class="resent-grid-info recommended-grid-info">
							<h3><a href="single.html" class="title title-info">${clip.name}</a></h3>
							<ul>
								<li><p class="author author-info"><a href="#" class="author"> ${clip.owner.fullName}</a></p></li>
								<li class="right-list"><p class="views views-info"> ${clip.views} views</p></li>
							</ul>
						</div>
					</div>
					</c:forEach>
							
							
							
							
							<div class="clearfix"> </div>
						</div>
					</div>
					<div class="recommended">
						<div class="recommended-grids">
							<div class="recommended-info">
								
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
							
								<div class="clearfix"> </div>
							</div>
						
						
					
						
							<div class="clearfix"> </div>
						</div>
					</div>
					<div class="recommended">
						<div class="recommended-grids">
							<div class="recommended-info">
								<div class="clearfix"> </div>
							</div>
						
						
							
						
							<div class="clearfix"> </div>
						</div>
					</div>
				</div>
				<div class="clearfix"> </div>
			</div>
			<!-- footer -->
			<jsp:include page="footer.jsp" />
			<!-- //footer -->
		</div>
		<div class="clearfix"> </div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
  </body>
</html>