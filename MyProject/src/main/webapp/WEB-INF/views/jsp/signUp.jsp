<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="springForm"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Videocean Register</title>


    <link rel="stylesheet" href="css/reset.css">

    <link rel='stylesheet prefetch'
          href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900|RobotoDraft:400,100,300,500,700,900'>
    <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>

    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/styleLogin.css">


</head>

<body>
<%String number1 = "<source src='load-96' type='video/mp4'>"; %>
<%String number2 = "<source src='load-95' type='video/mp4'>"; %>
<%String number3 = "<source src='load-96' type='video/mp4'>"; %>
<video id="Video1" class="bgvid back" autoplay="false" muted="muted" preload="auto" loop>
    <%
        double p = Math.random();
        if (p < 0.3) {
            out.println(number1);
        }
        if (p < 0.6 && p >= 0.3) {
            out.println(number2);
        }
        if (p >= 0.6) {
            out.println(number3);
        }
    %>
</video>

<!-- Form Mixin-->
<!-- Input Mixin-->
<!-- Button Mixin-->
<!-- Pen Title-->
<div class="pen-title">
    <h1></h1><span> </span>
</div>
<!-- Form Module-->
<div class="module form-module">
    <div class="toggle"><i class="fa fa-times fa-pencil"></i>
        <div class="tooltip">Login/Register</div>
    </div>
    <div class="form">
        <h2>Create an account</h2>
        <springForm:form action="signUp" method="post" commandName="user">
            <springForm:input type="email" placeholder="Email Address" path="username"
                              pattern="([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?" required="required"
                              title="Валиден имейл адрес!"/>
            <springForm:input name="Password" type="password" placeholder="Password" required="required" path="password"
                              pattern=".{6,}" title="Букви/цифри/специални символи като минималната дължина е 6 символа!"/>
            <springForm:input type="text" placeholder="Full Name" path="fullName" required="required" pattern="[A-Za-z0-9]{1,20}"/>
            <button>Register</button>
        </springForm:form>
        <c:if test="${not empty error}">
            <p style="color:red">
                <br/>
                Error: ${error}
            </p>
        </c:if>
    </div>
    <div class="form">
        <h2>Login to your account</h2>
        <springForm:form action="signIn" method="post" commandName="user">
            <springForm:input type="text" path="username" placeholder="Email Address"/>
            <springForm:input type="password" path="password" placeholder="Password"/>
            <button>Login</button>
        </springForm:form>
        <c:if test="${not empty error}">
            <p style="color:red; position:center;">
                <br/>
                Error: ${error}
            </p>
        </c:if>
    </div>

    <div class="cta"><a href="index">Back to home page</a></div>
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

<script src="js/index.js"></script>


</body>
</html>
