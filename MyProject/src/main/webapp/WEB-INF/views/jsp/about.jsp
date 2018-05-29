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
<!-- proverki -->
<c:if test="${sessionScope.user != null}">
    <!-- There is a user **attribute** in the session -->
    <jsp:include page="loggedHeader.jsp"/>
    <jsp:include page="loggedSidebar.jsp"/>
</c:if>

<c:if test="${sessionScope.user == null}">
    <!-- There is no user **attribute** in the session -->
    <jsp:include page="header.jsp"/>
    <jsp:include page="sidebar.jsp"/>
</c:if>
<!--// proverki -->
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <div class="show-top-grids">
        <div class="main-grids about-main-grids">
            <div class="recommended-info">
                <h3>About Videocean</h3>
                <p class="history-text">
                </p>
                <div class="about-grids">
                    <div class="about-bottom-grids">
                        <div class="col-sm-6 about-left">
                            <div class="about-left-grids">
                                <div class="col-md-2 about-left-img">
                                    <span class="glyphicon glyphicon-user glyphicon-facetime-video"
                                          aria-hidden="true"></span>
                                </div>
                                <div class="col-md-10 about-left-info">
                                    <a href="#">When We Started</a>
                                    <p>Launched in May 2018, Videocean allows people to discover, watch and
                                        share originally-created videos. Videocean provides a system which helps people
                                        to connect by subscribing for other people videos.</p>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                        <div class="col-sm-6 about-right">
                            <div class="about-left-grids">
                                <div class="col-md-2 about-left-img">
                                    <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                                </div>
                                <div class="col-md-10 about-left-info">
                                    <a href="#">Community Guidelines</a>
                                    <p>By using our web-site you can create your own account and share all your
                                        interests,
                                        and passions. Also you can enjoy to our rich collection of videos (you can like
                                        and dislike
                                        them, subscribes for them and even use information for your own web-site by
                                        using
                                        our API). </p>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <div class="about-bottom-grids">
                        <div class="col-sm-6 about-left">
                            <div class="about-left-grids">
                                <div class="col-md-2 about-left-img">
                                    <span class="glyphicon glyphicon-user glyphicon-book" aria-hidden="true"></span>
                                </div>
                                <div class="col-md-10 about-left-info">
                                    <a href="#">About Our Site</a>
                                    <p>Our web-site mission is to provide a space for expression and to open the world
                                        to everyone. We believe that everyone has the right to be heard and that the
                                        world
                                        is getting better when we listen, share and build a community through our
                                        stories.</p>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                        <div class="col-sm-6 about-right">
                            <div class="about-left-grids">
                                <div class="col-md-2 about-left-img">
                                    <span class="glyphicon glyphicon-user glyphicon-headphones"
                                          aria-hidden="true"></span>
                                </div>
                                <div class="col-md-10 about-left-info">
                                    <a href="#">New Update</a>
                                    <p>Our next version will be released in 2020 on the protection of the master
                                        degree.</p>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <div class="about-bottom-grids">
                        <div class="col-sm-6 about-left">
                            <div class="about-left-grids">
                                <div class="col-md-2 about-left-img">
                                    <span class="glyphicon glyphicon-user glyphicon-folder-open"
                                          aria-hidden="true"></span>
                                </div>
                                <div class="col-md-10 about-left-info">
                                    <a href="#">Careers</a>
                                    <p>The project was created by one full stack bachelor developer. </p>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                        <div class="col-sm-6 about-right">
                            <div class="about-left-grids">
                                <div class="col-md-2 about-left-img">
                                    <span class="glyphicon glyphicon-user glyphicon-phone" aria-hidden="true"></span>
                                </div>
                                <div class="col-md-10 about-left-info">
                                    <a href="#">Contact Us</a>
                                    <p>If you have any questions don't hesitate to contact us. Our email address is
                                        videocean.creators@gmail.com.</p>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- footer -->
    <jsp:include page="footer.jsp"/>
    <!-- //footer -->
</div>
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