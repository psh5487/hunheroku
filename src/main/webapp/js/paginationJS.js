$(document).ready(function() { 
    var nextCount = 0;
    var previousCount = 0;
    
    $(".page").empty();
    
    var info = {};
    info.press = "first";
    info.begin = "1";
    
    for(var i = 1; i <= 3; i++)
	{
    	var start = (i-1)*10;
    	
    	$(".page").append("<li class='page-item'><a class='page-link' id='pageBtn' href='MusicList?start=" + start + "'>"+ i +"</a></li>");
	}
    
	
});

$("#page").click(function(){   //현재 페이지 누르면 파란 표시 
	$(this).addClass("active");
});

$("#next").click(function(){   
	var begin = $(".pagination li:nth-child(3)").text();
//	var pressed = $(this).attr("id");
	
	var info = {};
	info.press = "next";
	info.begin = begin;
	
	console.log(info);
	
	var start = (parseInt(begin)+3-1)*10;
	
	var url = "http://localhost:8080/hundb/MusicList?start="+String(start); 
	
	console.log(url);

	jsonSend(info, url);
});

function jsonSend(obj, url) {
	$.ajax({
	    url: 'http://localhost:8080/hundb/PaginationServlet',
	    type: 'GET',
	    data: {sendString: JSON.stringify(obj)}, //http 통신은 string으로만 가능함
	   
	    success: function(data, response) { //data는 서버로부터 받은 값임
	        console.log("버튼 누른 info 보내짐!");
	        window.location.replace(url); //페이지 redirect 하기 
	        makePagination(data);
	    },
	    error: function(request, status, error, data){
	        alert("code = "+ request.status + " message = " + request.responseText + " error = " + error);
	    }
	});
}

function makePagination(data) {
	
	console.log("makePagination Start!");
	console.log(data);
	
	console.log(typeof data);
	console.log(data.pageList);
	console.log(data.pageList.length);
	
	$(".page").empty();
	
}



