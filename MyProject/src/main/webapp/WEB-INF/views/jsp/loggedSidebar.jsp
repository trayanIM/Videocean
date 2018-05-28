<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="col-sm-3 col-md-2 sidebar">
			<div class="top-navigation">
				<div class="t-menu">MENU</div>
				<div class="t-img">
					<img src="images/lines.png" alt="" />
				</div>
				<div class="clearfix"> </div>
			</div>
				<div class="drop-navigation drop-navigation">
				  <ul class="nav nav-sidebar">
				  
				  <br/>
				  <div class="user-panel">
            <div class="pull-left image">
            <!-- src="${user.picture}" -->
              <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image" style="position:absolute; left:32px;">
            </div>
            <div class="pull-left info">
            <br/>
             <br/>
              <br/>
              <br/>
             <br/>
              <br/>
               <br/>
             <br/>
              <br/>
              <!-- ${user.fullName} -->
              <p style="position:relative; left:70px;">${sessionScope.user.fullName}</p>
            </div>
          </div>
				 <br/>
				  <br/>
				   <br/>
				  <br/>
				  <br/>
				  <br/>
				  <br/>
				  <br/>
				  <br/>
				  <br/>
				  <br/>
			
					<li class="active"><a href="user" class="home-icon"><span class="glyphicon glyphicon-home" aria-hidden="true"></span><spring:message code="profile.name"/></a></li>
					<li><a href="playlist" class="user-icon"><span class="glyphicon glyphicon-home glyphicon-blackboard" aria-hidden="true"></span><spring:message code="library.name"/></a></li>
					<li><a href="history" class="sub-icon"><span class="glyphicon glyphicon-home glyphicon-hourglass" aria-hidden="true"></span><spring:message code="history.name"/></a></li>
					<li><a href="#" class="menu1"><span class="glyphicon glyphicon-film" aria-hidden="true"></span><spring:message code="categories.name"/><span class="glyphicon glyphicon-menu-down" aria-hidden="true"></span></a></li>
						<ul class="cl-effect-2">
							<li><a href="categories-1"><spring:message code="categories.trending"/></a></li>
							<li><a href="categories-2"><spring:message code="categories.sports"/></a></li>    
							<li><a href="categories-3"><spring:message code="categories.science"/></a></li>   
							<li><a href="categories-4"><spring:message code="categories.music"/></a></li>   
							<li><a href="categories-5"><spring:message code="categories.games"/></a></li>   
							<li><a href="categories-6"><spring:message code="categories.movies"/></a></li>   
							<li><a href="categories-7"><spring:message code="categories.other"/></a></li>                                           
						</ul>
						<!-- script-for-menu -->
						<script>
							$( "li a.menu1" ).click(function() {
							$( "ul.cl-effect-2" ).slideToggle( 300, function() {
							// Animation complete.
							});
							});
						</script>
						<!-- script-for-menu -->
						<script>
							$( "li a.menu" ).click(function() {
							$( "ul.cl-effect-1" ).slideToggle( 300, function() {
							// Animation complete.
							});
							});
						</script>
				  </ul>
				  <!-- script-for-menu -->
						<script>
							$( ".top-navigation" ).click(function() {
							$( ".drop-navigation" ).slideToggle( 300, function() {
							// Animation complete.
							});
							});
						</script>
						  <br/>
				 
				<div class="side-bottom">
						<div class="side-bottom-icons">
							<ul class="nav2">
							
							</ul>
						</div>
						
						<div class="copyright">
						
							<p>Copyright © 2015 My Play. All Rights Reserved | Design by <a href="http://w3layouts.com/">W3layouts</a></p>
						</div>
					</div>
				</div>
        </div>
</body>
</html>