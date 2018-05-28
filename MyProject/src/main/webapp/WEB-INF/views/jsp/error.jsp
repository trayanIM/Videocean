<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>404 error page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <link href="css/styleError.css" rel="stylesheet" type="text/css" media="all"/>
</head>
<body>
<!-----start-wrap--------->
<div class="wrap">
    <!-----start-content--------->
    <div class="content">
        <!-----start-logo--------->
        <div class="logo">
            <h1><a href="index"><img src="imagesError/logo.png"/></a></h1>
            <span><img src="imagesError/signal.png"/>Oops! The Page you requested was not found!</span>
        </div>
        <!-----end-logo--------->
        <!-----start-search-bar-section--------->
        <div class="buttom">
            <div class="seach_bar">
                <p>You can go to <span><a href="index">home</a></span> page!</p>
                <!-----start-sear-box--------->
                <div class="search_box">
                    <%--<form>--%>
                    <%--<!-- Rado tuka da vkluchi nashiq search -->--%>
                    <%--<input type="text" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search';}"><input type="submit" value="">--%>
                    <%--</form>--%>
                </div>
            </div>
        </div>
        <!-----end-sear-bar--------->
    </div>
    <!----copy-right-------------->
</div>

<!---------end-wrap---------->
</body>
</html>