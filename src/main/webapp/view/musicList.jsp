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
<link rel="icon" href="img/favicon.ico" style="user-select: auto;">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- css file -->
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
        <a class="nav-link" href="<%=request.getContextPath()%>/MakingOperationTable">운영시간표</a>
      </li>  
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/operationTable">개인시간표 입력</a>
      </li>  
    </ul>
  </div>  
</nav>
<br><br>

<div class="title">
	<!-- 제목 -->
	<div style="width: 250px">
		<a class="nav-link" href="<%=request.getContextPath()%>/MusicList"><h2 style="color: black">헌 음반 리스트</h2></a>
	</div>
</div>

<div class="content">
	<div class = "before_list">
	    <!-- 검색 상자&버튼 -->
	    <div>
			<form name="search_form" id = "search_form" method="POST" action="search" accept-charset="utf-8" class="form-inline">
				<div class="form-group" style="margin-right: 10px">
			 		<input type="text" placeholder="검색" name="search" class="form-control">
			 	</div>
			 	<div class="form-group" style="margin-right: 20px">
			 		<input type="image" src="img/search.png" name="search" id="searchButton" width="20px" height="auto"/>
			 	</div>
			</form>
		</div>
		<div class = "musicAdd_button">
		  	<a href="<%=request.getContextPath()%>/addMusic">
				<button type="button" class="btn" id="easy_button">+ 곡 간편 추가</button>
			</a>  
			<a href="<%=request.getContextPath()%>/addMusicDirectly">
				<button type="button" class="btn btn-info" id="hard_button">+ 곡 직접 추가</button>
			</a>
		</div>
	</div><br>
    
    <div style="font-size: 20px; margin-bottom: 10px">Total <span class="text-danger">${count}</span>곡, ${cur}/${pageCount}</div>
  
  <div class="table-responsive">  
  <table class="table">
    <thead class="thead-light">
      <tr>
        <th>No</th>
        <th>Title</th>
        <th>Artist</th>
        <th>Composer</th>
        <th>Category</th>
        <th>NumOfDisc</th>
        <th>Label</th>
        <th>Barcode</th>
        <th></th>
        <th></th>
      </tr>
    </thead>
    
    <tbody>
	<c:forEach items="${list}" var="item" begin="0" end="10" varStatus="vs">
		<tr>
			<td>${item.id}</td>
			<td>${item.title} <br>
			    <!-- Button to Open the Modal -->
	  			<%-- <div data-toggle="modal" id="modalBtn" data-target="#myModal${vs.index}">
	   				<div style="cursor: pointer; color: gray; margin-top: 10px">▼Track</div>
	  			</div> --%>
	  			<a href="#" data-toggle="modal" id="modalBtn" data-target="#myModal${vs.index}">
	   				<div style="cursor: pointer; color: gray; margin-top: 10px">▼Track</div>
	  			</a>
				
				<!-- The Modal -->
				<div class="modal" id="myModal${vs.index}">
				  <div class="modal-dialog modal-dialog-centered modal-xl">
				    <div class="modal-content">
				    
				      <!-- Modal Header -->
				      <div class="modal-header">
				        <h5 class="modal-title">${item.title}</h5>
				        <button type="button" class="close" data-dismiss="modal">×</button>
				      </div>
				      
				      <!-- Modal body -->
				      <div class="modal-body">
				        <p style="white-space:pre-line;">${item.track}</p>
				      </div>
				    </div>
				  </div>
				</div>
			</td>
			<td><p style="white-space:pre;">${item.artist}</p></td>
			<td><p style="white-space:pre-line;">${item.composer}</p></td>
			<td>${item.category}</td>
			<td>${item.numOfDisc}</td>
			<td>${item.label}</td>
			<td>${item.barcode}</td>
			<td>
				<form name="edit_form" id = "edit_form" method="GET" action="updateMusic" accept-charset="utf-8">
					<input type="image" src="img/edit.png" name ="edit" id="editButton" width="20px" height="auto"/>
					<input type="hidden" name="barcode" value="${item.barcode}" />
				</form>
			</td>
			<td>
				<form name="trash_form" id = "trash_form" method="POST" action="DeleteMusicServlet" accept-charset="utf-8">
					<input type="image" src="img/remove.png" name ="trash" id="trashButton" width="21px" height="auto"/>
					<input type="hidden" name="item_barcode" value="${item.barcode}" />
				</form>
			</td>
		</tr>
	</c:forEach>
	<tr>
		<td></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td>
	</tr>
    </tbody>
  </table>
  </div>
  
  <!-- pagination -->
  <ul class="pagination justify-content-center">
  	<li class="page-item <c:if test="${begin==1}">disabled</c:if>"><a class="page-link" id="gotoHead" href="MusicList?start=0">처음</a></li>
    <li class="page-item <c:if test="${begin==1}">disabled</c:if>"><a class="page-link" id="previous" href="MusicList?start=${(begin - pageRange -1) * 10}"><</a></li>
    
    <%-- <c:forEach items="${pageStartList}" var="pageIndex" varStatus="status">
    	<li class="page-item" id="page"><a class="page-link" href="MusicList?start=${pageIndex}">${status.index +1}</a></li>
    </c:forEach> --%>
    
    <%-- <c:forEach items="${pageList}" var="pageIndex">
    	<li class="page-item"><a class="page-link" id="page" href="MusicList?start=${pageIndex * 10}">${pageIndex + 1}</a></li>
    </c:forEach> --%>
    
    <%-- <c:forEach items="${pageList}" varStatus="status">
    	<li class="page-item"><a class="page-link" id="pageBtn" href="MusicList?start=${(pageList[status.index]-1) * 10}">${pageList[status.index]}</a></li>
    </c:forEach> --%>
    
    <c:choose>
    <c:when test="${pageCount < begin+pageRange}">
    	<c:forEach var="i" begin="${begin}" end="${pageCount}">
    		<li class="page-item <c:if test="${i==cur}">active</c:if>"> <a class="page-link" id="pageBtn" href="MusicList?start=${(i-1) * 10}">${i}</a></li>
    	</c:forEach>
    </c:when>
    
    <c:otherwise>
    	<c:forEach var="i" begin="${begin}" end="${begin+pageRange -1}">
    		<li class="page-item <c:if test="${i==cur}">active</c:if>"> <a class="page-link" id="pageBtn" href="MusicList?start=${(i-1) * 10}">${i}</a></li>
    	</c:forEach>
    </c:otherwise>
	</c:choose>

    <li class="page-item <c:if test="${begin==lastBegin}">disabled</c:if>"><a class="page-link" id="next" href="MusicList?start=${(begin + pageRange -1) * 10}">></a></li>
    <li class="page-item <c:if test="${begin==lastBegin}">disabled</c:if>"><a class="page-link" id="gotoTail" href="MusicList?start=${(pageCount -1) * 10}">끝</a></li>
  </ul>
  
  <br><br>
</div>

</body>
	<!-- <script type="text/javascript" src="js/paginationJS.js"></script> -->
</html>


