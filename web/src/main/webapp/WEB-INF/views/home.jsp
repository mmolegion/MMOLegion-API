<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html class="no-js">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="manifest" href="${pageContext.request.contextPath}/resources/site.webmanifest">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/normalize.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/global.css">

        <title>MMOLegion</title>
    </head>
    <body class="ml-bg">
        <!--[if IE]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade your browser</a> to improve your experience and security.</p>
        <![endif]-->

        <nav class="navbar sticky-top ml-bg">
            <ul class="list-inline">
                <li class="list-inline-item">
                    <a class="nav-link active" href="/">Home</a>
                </li>
                <li class="list-inline-item">
                    <a class="nav-link" href="/">Download</a>
                </li>
                <li class="list-inline-item">
                    <a class="nav-link" href="/">Pricing</a>
                </li>
                <li class="list-inline-item">
                    <a class="nav-link" href="/">Forum</a>
                </li>
                <li class="list-inline-item">
                    <a class="nav-link" href="/">Wiki</a>
                </li>
                <li class="list-inline-item">
                    <a class="nav-link" href="https://discord.gg/ZWtFt65">Discord</a>
                </li>
                <li class="list-inline-item">
                    <a class="nav-link" href="/">Support</a>
                </li>
                <li class="list-inline-item">
                    <a class="nav-link" href="/">Hi Tina!</a>
                </li>
            </ul>
        </nav>


        <script src="${pageContext.request.contextPath}/resources/js/vendor/modernizr-3.7.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/vendor/jquery-3.4.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/vendor/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/plugins.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/global.js"></script>
        <script async src="https://www.googletagmanager.com/gtag/js?id=UA-141039073-1"></script>
        <script>
            window.dataLayer = window.dataLayer || [];
            function gtag(){dataLayer.push(arguments);}
            gtag('js', new Date());

            gtag('config', 'UA-141039073-1');
        </script>
    </body>
</html>