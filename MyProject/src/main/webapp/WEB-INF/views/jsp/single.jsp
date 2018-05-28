
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
<script  src="http://code.jquery.com/jquery-2.2.2.js"  
integrity="sha256-4/zUCqiq0kqxhZIyp4G0Gk+AOtCJsY1TA00k5ClsZYE="   
crossorigin="anonymous"></script>
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
  
  <!--  facebook js sdk -->
	<div id="fb-root"></div>
	<script>
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/bg_BG/sdk.js#xfbml=1&version=v2.5";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>
	<!--  end of facebook js sdk -->
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
				<div class="col-sm-8 single-left">
					<div class="song">
						<div class="song-info">
							<h3>${clip.name}</h3>	
					</div>
						<div class="video-grid">
							<video width="550" height="300" controls>
                    <source src="load-${clip.clipID}" type="video/mp4">
                     <source src="load-${clip.clipID}" type="video/ogg">
Your browser does not support the video tag.
</video>
						</div>
					</div>
					<div class="song-grid-right">
						<div class="share">
							<h5>Share</h5>
							<ul>							
								<li><!--  <a target="_blank"
								href="https://www.facebook.com/sharer/sharer.php?u=single-${currentID}"
								class="icon fb-icon">Facebook</a>-->
								<a href="#"
								class="icon like" onclick="return saveLike();">Like <span id="like-count">${likes}</span></a>
								<script>
									function saveLike() {
										jQuery.ajax({
											  type: "POST",
											  url: "${currentUrl}/like",
											  success: function(result) {
												  console.log(result);
													var p =  JSON.parse(result);
													jQuery("#like-count").html(p.likes);
													jQuery("#dislike-count").html(p.dislikes);
											  }
											});
										return false;
									}
								</script>
	                          <a href="#"
								class="icon twitter-icon" onclick="return saveDislike();">Like <span id="dislike-count">${dislikes}</span></a>
								<script>
									function saveDislike() {
										jQuery.ajax({
											  type: "POST",
											  url: "${currentUrl}/dislike",
											  success: function(result) {
												  console.log(result);
													var p =  JSON.parse(result);
													jQuery("#dislike-count").html(p.dislikes);
													jQuery("#like-count").html(p.likes);
											  }
											});
										return false;
									}
								</script>
							</li>
								<li class="view">${clip.views }</li>								
							</ul>
						</div>
					</div>
					<div class="clearfix"> </div>
					<div class="published">
						<script src="../jquery.min.js"></script>
							<script>
								$(document).ready(function () {
									size_li = $("#myList li").size();
									x=1;
									$('#myList li:lt('+x+')').show();
									$('#loadMore').click(function () {
										x= (x+1 <= size_li) ? x+1 : size_li;
										$('#myList li:lt('+x+')').show();
									});
									$('#showLess').click(function () {
										x=(x-1<0) ? 1 : x-1;
										$('#myList li').not(':lt('+x+')').hide();
									});
								});
							</script>
							<div class="load_more">	
								<ul id="myList">
									<li>
										<h4>${clip.datePublished}</h4>
										<p>${clip.description}</p>
									</li>
								</ul>
							</div>
					</div>
					<div class="all-comments">
						<!-- <div class="all-comments-info">
							<a href="#">All Comments (8,657)</a>
							<div class="box">
								<form>
									<input type="text" placeholder="Comment" required=" ">
									<input onclick="addComment()" type="submit" value="SEND">
									<div class="clearfix"> </div>
								</form>
							</div>
							<div class="all-comments-buttons">
								<ul>
									<li><a href="#" class="top">Top Comments</a></li>

								</ul>
							</div>
						</div>-->
						<div class="media-grids">
						
							<!-- <div class="media">
								
								<div class="media-left">
									<a href="#">
										
									</a>
								</div>
								<div class="media-body">
								
								</div>
							</div>
							<div class="media">
								<div class="media-left">
									<a href="#">
										
									</a>
								</div>
								<div class="media-body">
									
								</div>
							</div>-->
							
						</div>
					</div>
				</div>
				<div class="col-md-4 single-right">
					<h3>Up Next</h3>
					<div class="single-grid-right">
					<c:forEach var="cl" items="${clips}" step="1" begin="0" end="6">
						<div class="single-right-grids">
							<div class="col-md-4 single-right-grid-left">
								<a href="single-${cl.clipID}"> <video width="120" height="80" ><source src="load-${cl.clipID}" ></source></video></a>
							</div>
							<div class="col-md-8 single-right-grid-right">
								<a href="single-${cl.clipID}" class="title"> ${cl.name}</a>
								<p class="author"><a href="user-${cl.owner.userID}" class="author">${cl.owner.fullName}</a></p>
								<p class="views">${cl.views} views</p>
							</div>
							<div class="clearfix"> </div>
						</div>
					</c:forEach>
					</div>
				</div>
				<div class="clearfix"> </div>
			</div>
			<!-- footer -->
			<jsp:include page="footerForSingle.jsp" />
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