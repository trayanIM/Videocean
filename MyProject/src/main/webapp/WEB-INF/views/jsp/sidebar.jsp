<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<br/>
<div class="col-sm-3 col-md-2 sidebar">
			<div class="top-navigation">
				<div class="t-menu">MENU</div>
				<div class="t-img">
					<img src="images/lines.png" alt="" />
				</div>
				
				<div class="clearfix"> </div>
			</div>
			<br/>
				<div class="drop-navigation drop-navigation">
				  <ul class="nav nav-sidebar">
					<li class="active">
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
					<div class="side-bottom">
						<div class="copyright">
							<p>Copyright © 2015 Videocean. All Rights Reserved | Design by <a href="http://videocean.com/">VOTeam</a></p>
						</div>
					</div>
				</div>
        </div>
</body>
</html>