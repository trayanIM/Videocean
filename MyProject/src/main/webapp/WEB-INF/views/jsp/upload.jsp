<%@page import="com.example.model.TYPE"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Videocean</title>

<script type="application/x-javascript">
	
	
	
	
	
 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 





</script>
<!-- bootstrap -->
<link href="css/bootstrap.min.css" rel='stylesheet' type='text/css'
	media="all" />
<!-- //bootstrap -->
<link href="css/dashboard.css" rel="stylesheet">
<!-- Custom Theme files -->
<link href="css/style.css" rel='stylesheet' type='text/css' media="all" />
<script src="js/jquery-1.11.1.min.js"></script>
<!--start-smoth-scrolling-->
<!-- fonts -->
<link
	href='//fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Poiret+One'
	rel='stylesheet' type='text/css'>
<!-- //fonts -->

<!--<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet"> -->
<link href="css/fileinput.min.css" media="all" rel="stylesheet"
	type="text/css" />
<!--<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> -->
<script src="js/fileinput.min.js" type="text/javascript"></script>
<!--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js" type="text/javascript"></script>-->

</head>
<body>
	 <jsp:include page="loggedHeader.jsp" />
	 
	<div class="module upload-form-module">
		<!-- upload -->
		
		
		
		
		
		<div class="form" style="display: block;">
			<form:form method="POST" modelAttribute="fileBucket"
				enctype="multipart/form-data" class="form-horizontal">

				<h2>Upload a file</h2>
				<div class="form-group">
					<form:input type="file" accept="video/*" path="file" id="file"
						class="form-control input-sm" placeholder="Upload video file" />
					<script>
						$(document).on('ready', function() {
							$("#file").fileinput();
						});
					</script>
					<div class="has-error">
						<form:errors path="file" class="help-inline" />
					</div>
				</div>
				<div class="form-group">

					<form:input type="text" path="name" id="name"
						class="form-control input-sm" placeholder="Video Title" />
				</div>
				<div class="form-group">

					<form:textarea path="description" id="description" rows="5"
						cols="30" class="form-control input-sm"
						placeholder="Video Description" />
				</div>
				<select name="category">
					<c:forEach var="category" items="${categories}">
						<option value="${category.getCategoryID()}">${category.getName()}</option>
					</c:forEach>

				</select>
				<br>
				<br>
				<br>
				<br>
				<div class="form-actions floatRight">
					<input type="submit" value="Upload" class="btn btn-primary">
					<button type="reset" class="btn btn-default btn-reset">Reset</button>
				</div>


			</form:form>
			<c:if test="${not empty error}">
				<p style="color: red">Error: ${error}</p>
			</c:if>
			<div class="cta">
				<a href="<c:url value='/index' />">Home</a>
			</div>



		</div>

	</div>


</body>
</html>