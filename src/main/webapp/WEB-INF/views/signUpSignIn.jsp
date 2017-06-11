<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<c:url value="/resources/css/signUpSignIn.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/jquery.1.10.2.min.js" />"></script>
<script src="<c:url value="/resources/js/signUpSignIn.js" />"></script>
</head>
<body>
<div class="form">
    <ul class="tab-group">
        <li class="tab active"><a href="#signup">Sign Up</a></li>
        <li class="tab"><a href="#login">Log In</a></li>
    </ul>
    <div class="tab-content">
        <div id="signup">
            <h3>Sign Up for Free</h3>
            <form action="./signUpCheck.jsp" method="post">
                <div class="field-wrap">
                    <label>
                        nick Name<span class="req">*</span>
                    </label>
                    <input type="text" required autocomplete="off" name="signUpNickName"/>
                </div>

                <div class="field-wrap">
                    <label>
                        Set A Password<span class="req">*</span>
                    </label>
                    <input type="password"required autocomplete="off" name="signUpPassword"/>
                </div>
                <button class="button button-block">Get Start</button>
            </form>
        </div>

        <div id="login">
            <h3>Welcome Back!</h3>
            <form action="./signInCheck.jsp" method="post">
                <div class="field-wrap">
                    <label>
                        nick Name<span class="req">*</span>
                    </label>
                    <input type="text" required autocomplete="off" name="signInNickName"/>
                </div>
                <div class="field-wrap">
                    <label>
                        Password<span class="req">*</span>
                    </label>
                    <input type="password"required autocomplete="off" name="signInPassword"/>
                </div>
                <%--<p class="forgot"><a href="#">Forgot Password?</a></p>--%>
                <button class="button button-block"/>Log In</button>
            </form>
        </div>
    </div><!-- tab-content -->
</div> <!-- /form -->
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="js/signUpIn.js"></script>
</body>
</html>