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
  <a class="navbar-brand" href="<%=request.getContextPath()%>/">음취헌</a>
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

<div class="container">
<br><h2>곡 수정하기 </h2>

<form name="music_form" method="POST" action="updateMusic" accept-charset="utf-8">
	<br><h5>바코드 넘버</h5>
	${music.get(0).barcode}<br><br>
	<input type="hidden" name="barcode" value="${music.get(0).barcode}" />
	
	<h5>장르</h5>
	<select name="category" class="form-control" required>
	  <option value="" disabled>장르 선택</option>
	  <option value="독주곡" <c:if test="${music.get(0).category=='독주곡'}">selected</c:if>>독주곡(Solo)</option>
	  <option value="교향곡" <c:if test="${music.get(0).category=='교향곡'}">selected</c:if>>교향곡(Symphony)</option>
	  <option value="콘체르토" <c:if test="${music.get(0).category=='콘체르토'}">selected</c:if>>콘체르토(Concerto)</option>
	  <option value="실내악" <c:if test="${music.get(0).category=='실내악'}">selected</c:if>>실내악(Chamber)</option>
	  <option value="오페라" <c:if test="${music.get(0).category=='오페라'}">selected</c:if>>오페라(Opera)</option>
	  <option value="기타" <c:if test="${music.get(0).category=='기타'}">selected</c:if>>기타</option>
	</select>
	<br><br>
	
	<h5>제목</h5>
	<input type = "text" name = "title" value="${music.get(0).title}" class="form-control" required/><br><br>
	
	<h5>아티스트</h5>
	<textarea rows="5" name="artist" class="form-control" required>${music.get(0).artist}</textarea><br><br>
	
	<h5>트랙 리스트</h5>
	<textarea rows="10" name="track" id="track" class="form-control">${music.get(0).track}</textarea><br><br>
	
	<h5>음반사</h5>
	<input type = "text" name = "label" value="${music.get(0).label}" class="form-control" /><br><br>
	
	<h5>디스크 수</h5>
	<input type = "text" name = "numOfDisc" value="${music.get(0).numOfDisc}" class="form-control" pattern="\d*" title="숫자로만 입력하세요."/><br><br>
	
	<h5>작곡가</h5>
	<textarea rows="5" name = "composer" class="form-control">${music.get(0).composer}</textarea><br><br>
			   
	<h5>선호도 (선택사항)</h5>
	<input type = "radio" name = "importance" value = "3" <c:if test="${music.get(0).importance == 3}">checked="checked"</c:if>/>&nbsp;상&ensp;&ensp; 
	<input type = "radio" name = "importance" value = "2" <c:if test="${music.get(0).importance == 2}">checked="checked"</c:if>/>&nbsp;중&ensp;&ensp;
	<input type = "radio" name = "importance" value = "1" <c:if test="${music.get(0).importance == 1}">checked="checked"</c:if>/>&nbsp;하<br><br><br>
	
	<a href="<%=request.getContextPath()%>/MusicList" class="btn btn-outline-info"> ← 이전 </a> &ensp;
	<input type = "submit" value = "제출하기" id = "submit_button" class="btn btn-info"/>
</form>
<br><br>
</div>
</body>
</html>