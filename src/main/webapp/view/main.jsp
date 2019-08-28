<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>음취헌</title>
<!-- favicon 적용  -->
<link rel="shortcut icon" href="img/favicon.ico" style="user-select: auto;">
<link rel="icon" href="img/favicon.ico" style="user-select: auto;">
	
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/hundbStyle.css">

<!-- bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<body>

<!-- 네브바 -->
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
  <a class="navbar-brand" href="<%=request.getContextPath()%>/main">음취헌</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/MusicList">음반 리스트</a>
      </li>  
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/chooseMusic">선곡표 만들기</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/operationTable">운영시간표</a>
      </li>  
    </ul>
  </div>  
</nav>

<!-- 슬라이드쇼 -->
<div id="demo" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ul class="carousel-indicators">
    <li data-target="#demo" data-slide-to="0" class="active"></li>
    <li data-target="#demo" data-slide-to="1"></li>
  </ul>

  <!-- The slideshow -->
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img class = "slideImg" src="img/slideshow1.jpg" alt="소개">
    </div>
    <div class="carousel-item">
      <img class = "slideImg" src="img/slideshow0.jpg" alt="소개">
    </div>
  </div>

  <!-- Left and right controls -->
  <a class="carousel-control-prev" href="#demo" data-slide="prev">
    <span class="carousel-control-prev-icon"></span>
  </a>
  <a class="carousel-control-next" href="#demo" data-slide="next">
    <span class="carousel-control-next-icon"></span>
  </a>
</div>

<!-- 활동 소개 파트 -->
<img class = "introductionImg" src="img/introduction.jpg" alt="소개">

<div class = "introLogo">
	<a href = "https://www.facebook.com/skku.musicholic/" target = "_blank"><img class = "facebook" src="img/facebook.png" alt="페이스북"></a> &nbsp;&nbsp;
	<a href = "https://place.map.kakao.com/17568205" target = "_blank"><img class = "map" src="img/map.png" alt="지도"></a>
</div>

<footer>
음취헌 | 성균관대학교 자연과학캠퍼스 복지회관 3층 | Developed by Sohyun
</footer>

</body>
</html>