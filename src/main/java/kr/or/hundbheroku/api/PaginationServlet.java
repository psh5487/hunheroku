package kr.or.hundbheroku.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.or.hundbheroku.dao.MusicDao;
import kr.or.hundbheroku.dto.MusicDto;

@WebServlet("/PaginationServlet")
public class PaginationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PaginationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		//pagination 변수 
		int begin = 1;
		int pageRange = 3;
		
		//ajax로 보낸 값 받기 
		String value = request.getParameter("sendString")==null?"":request.getParameter("sendString");
		
		if(!value.equals("")) {
		    System.out.println("value of 'sendString' is " + value); //test
		    
		    //json 문자열인 value 를 json 객체로 변환 (gson library)
		    JsonParser Parser = new JsonParser();
		    JsonObject jsonObj = (JsonObject) Parser.parse(value); 
		    
		    //press 받아오기 
		    String press = jsonObj.get("press").getAsString();
		    
		    if(press.equals("next"))
		    {
		    	//begin 받아오기 
			    String beginStr = jsonObj.get("begin").getAsString();
			    begin = Integer.parseInt(beginStr);
			    begin += pageRange;
			    
			    System.out.println("begin iss " + begin); //test
		    }
		}

		
//		String start = request.getParameter("start");
//		
//		if(start == null)
//			start = "0";
//		
//		int startInt = Integer.parseInt(start);
		
		// DAO생성
		MusicDao dao = new MusicDao();
						
		// id 정렬 
		dao.sortingId();
					    
//		// start로 시작하는 곡 목록 구하기
//		List<MusicDto> list = dao.getTenMusics(startInt);
		
		// 전체 곡 수 구하기 
		List<MusicDto> listAll = dao.getMusics();
		int count = listAll.size();
		
		// 전체 페이지수 구하기
		int pageCount = count / 10;
		if(count % 10 > 0)
			pageCount++;
		
		// 페이지네이션 블럭 만들기 
		List<Integer> pageList = new ArrayList<>();
		
		if(pageCount < begin+pageRange) {
			for(int i = begin; i <= pageCount; i++) {
				pageList.add(i);
			}
		}
		else {
			for(int i = begin; i < begin + pageRange; i++) {
				pageList.add(i);
			}
		}
		
		System.out.println(pageList);

		Map<String, Object> map = new HashMap<>();
		
		map.put("pageList", pageList);
//		map.put("count", count);
//		map.put("pageCount", pageCount);
		
		String json = new Gson().toJson(map);
		
		response.getWriter().write(json);
		
		
		
		//test - request안의 모든 key(parameter)와 value 확인하기 
//	    Enumeration params = request.getParameterNames();
//	    System.out.println("----------------------------");
//	    while (params.hasMoreElements()){
//	        String name = (String)params.nextElement();
//	        System.out.println(name + " : " + request.getParameter(name));
//	    }
//	    System.out.println("----------------------------");

	    //test
//	    System.out.println(memberArray);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
