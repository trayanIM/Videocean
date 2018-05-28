<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="footer">
				<div class="footer-grids">
					<div class="footer-top">
						<div class="footer-top-nav">
							<ul>
								<li><a href="about"><spring:message code="about.name" /></a></li>
								<li><a href="creators"><spring:message code="creators.name" /></a></li>
								<li><a href="http://ittalents.bg/home"><spring:message code="advertise.name" /></a></li>
								<li><a href="developers-${clip.clipID}">API</a></li>
							</ul>
						</div>
						<div class="footer-bottom-nav">
							<ul>
								<li><a href="terms"><spring:message code="terms.privacy" /></a></li>
							</ul>
						</div>
					</div>
					<div class="footer-bottom">
						<ul>
							<li class="languages">
								<select class="form-control bfh-countries" data-country="US" onchange="location = this.value;">
									<option value=""><spring:message code="language.name" /></option>
									<option value="?language=en">English</option>
									<option value="?language=fr">Francais</option>
								</select>
							</li>
						<!--  <li class="languages">
								<select class="form-control bfh-countries">
									<option value=""><spring:message code="country.name" /></option>
									<option value="FR">France</option>
									<option value="GBR">Great Britain</option>
									<option value="BG">Bulgaria</option>
									<option value="GER">Germany</option>
									<option value="CH">China</option>
									<option value="RUS">Russia</option>
								</select>
							</li>
							-->
						</ul>
					</div>
				</div>
			</div>
</body>
</html>