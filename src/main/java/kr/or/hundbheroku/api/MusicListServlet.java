package kr.or.hundbheroku.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
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


//@WebServlet(urlPatterns = {"/MusicList"},
//             initParams = {@WebInitParam(name = "start", value = "0")})
@WebServlet("/MusicList")
public class MusicListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MusicListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		//pagination 변수 
		int pageRange = 3;
		
		String start = request.getParameter("start");
		
		if(start == null)
			start = "0";
		
		int startInt = Integer.parseInt(start);
		
		
		// DAO생성
		MusicDao dao = new MusicDao();
						
		// id 정렬 
		dao.sortingId();
					    
		// start로 시작하는 곡 목록
		List<MusicDto> list = dao.getTenMusics(startInt);
		
		// 전체 곡 수
		List<MusicDto> listAll = dao.getMusics();
		int count = listAll.size();
		
		// 전체 페이지수
		int pageCount = count / 10;
		if(count % 10 > 0)
			pageCount++;
		
		//페이지네이션 
		//현재 페이지 
		int cur = startInt/10 + 1;
		
		//몇 번째 블락인지 
		int block = (int)cur/pageRange;
		
		if(cur % pageRange == 0)
			block -= 1;
		
		//블락의 begin 값 구하기 
		int begin = block * pageRange + 1;
		
		//마지막 블락이 몇 번째 블락인지 
		int lastBlock = (int)pageCount/pageRange;
		
		if(pageCount % pageRange == 0)
			lastBlock -= 1;
		
		//마지막 블락의 begin 값 
		int lastBegin = lastBlock * pageRange + 1;
		
		request.setAttribute("list", list);
		request.setAttribute("pageRange", pageRange);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("cur", cur);
		request.setAttribute("begin", begin);
		request.setAttribute("lastBegin", lastBegin);
		request.setAttribute("count", count);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/musicList.jsp");
		requestDispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
