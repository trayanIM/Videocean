<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="springForm"
           uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Videocean</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!-- bootstrap -->
    <link href="css/bootstrap.min.css" rel='stylesheet' type='text/css' media="all"/>
    <!-- //bootstrap -->
    <link href="css/dashboard.css" rel="stylesheet">
    <!-- Custom Theme files -->
    <link href="css/style.css" rel='stylesheet' type='text/css' media="all"/>
    <script src="js/jquery-1.11.1.min.js"></script>
    <!--start-smoth-scrolling-->
    <!-- fonts -->
    <link href='//fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
          rel='stylesheet' type='text/css'>
    <link href='//fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
    <!-- //fonts -->
</head>
<body>


<c:if test="${sessionScope.user != null}">
    <!-- There is a user **attribute** in the session -->
    <jsp:include page="loggedHeader.jsp"/>
</c:if>

<c:if test="${sessionScope.user == null}">
    <!-- There is a user **attribute** in the session -->
    <jsp:include page="header.jsp"/>
</c:if>


<div class="developers">
    <!--<div class="developers-banner creators-banner">-->
    <div class="developers-banner">
        <!-- container -->
        <div class="container">
            <div class="developers-info">
                <br/>
                <br/>
                <br/>
                <h3>For Creators</h3>
                <p>.</p>
            </div>
        </div>
        <!-- //container -->
    </div>
    <div class="creators-grids">
        <!-- container -->
        <div class="container">
            <div class="col-md-4 creators-top-grid">
                <div class="creators-grid">
                    <a>Trayan Muchev</a>
                    <p>Age : 22 </p>
                    <br/>
                    <p>Born in Sliven in 1995 </p>
                    <br/>
                    <p>F.Number: 121214078 </p>
                    <br/>
                    <p>Education: Software Engineering TU Sofia </p>
                    <br/>
                </div>
            </div>
            <div class="col-md-4 creators-top-grid">
                <div class="creators-grid">
                    <a>Contacts</a>
                    <p>Email : videocean.creators@gmail.com </p>
                    <br/>
                    <p>Skype: videocean.creators</p>
                    <br/>
                    <p>Cellphone: 0883200555 </p>
                    <br/>
                    <p>Address: Jk. Studentski Grad block 3 </p>
                    <br/>
                </div>
            </div>
            <div class="col-md-4 creators-top-grid">
                <div class="creators-grid">
                    <a>Accomplishments</a>
                    <p>Education: Finished 5th season of IT Talents </p>
                    <br/>
                    <p>Job: Java Regular Developer in EPAM Systems </p>
                    <br/>
                    <p>Job: More than 2 years working experience </p>
                    <br/>
                    <p>Sports: First racket of TU tennis team </p>
                    <br/>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
        <!-- //container -->
    </div>
    <div class="creators-bottom">
        <!-- container -->
        <div class="container">
            <div class="creators-bottom-grids-info">
                <h3>The video platform you will love</h3>
            </div>
            <div class="creators-bottom-grids">
                <div class="col-md-4 creators-bottom-grid">
                    <div class="services-icon">
                        <span class="glyphicon glyphicon-globe" aria-hidden="true"></span>
                    </div>
                    <h4>Global</h4>
                    <p>We are trying to reach as many people all over the world!</p>
                </div>
                <div class="col-md-4 creators-bottom-grid green">
                    <div class="services-icon">
                        <span class="glyphicon glyphicon-magnet" aria-hidden="true"></span>
                    </div>
                    <h4>Engaging</h4>
                    <p> And all we are doing is for your pleasure!</p>
                </div>
                <div class="col-md-4 creators-bottom-grid red">
                    <div class="services-icon">
                        <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                    </div>
                    <h4>Helpful</h4>
                    <p>We are trying to help you spend your time better!</p>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        <!-- //container -->
    </div>
</div>
<!-- footer -->
<jsp:include page="footer.jsp"/>
<!-- //footer -->
<div class="clearfix"></div>
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