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
        <i data-fa-symbol="bars" class="fas fa-bars fa-fw"></i>
        <%----%>

        <nav class="navbar navbar-expand-md fixed-top">
            <button type="button" class="navbar-toggler ml-auto" data-toggle="collapse" data-target="#nav">
                <svg><use xlink:href="#bars"></use></svg>
            </button>

            <div class="collapse navbar-collapse justify-content-between" id="nav">
                <ul class="navbar-nav">
                    <li class="nav-item active"><a class="nav-link" href="/">Home</a></li>
                    <li class="nav-item"><a class="nav-link" href="/">Download</a></li>
                    <li class="nav-item"><a class="nav-link" href="/">Pricing</a></li>
                    <li class="nav-item"><a class="nav-link" href="/">Forum</a></li>
                    <li class="nav-item"><a class="nav-link" href="/">Wiki</a></li>
                    <li class="nav-item"><a class="nav-link" href="https://discord.gg/ZWtFt65">Discord</a></li>
                    <li class="nav-item"><a class="nav-link" href="/">Support</a></li>
                </ul>
            </div>
        </nav>

        <script src="${pageContext.request.contextPath}/resources/js/vendor/modernizr-3.7.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/vendor/jquery-3.4.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/vendor/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/vendor/all.min.js"></script>
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