package kr.or.hundbheroku.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.hundbheroku.dao.MusicDao;
import kr.or.hundbheroku.dto.MusicDto;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/searchResult.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//form으로부터 넘어온 값 저장
		String searchKeyword = request.getParameter("search")==null?"":request.getParameter("search");
		
		if(searchKeyword.equals("")) {
			searchKeyword = "없음";
		}
		
		//DAO생성
		MusicDao dao = new MusicDao();
					    
		//keyword가 포함되고, start로 시작하는 곡 목록 구하기
		List<MusicDto> list = dao.TenMusicsIncludingKeyword(searchKeyword);
		
		// 전체 곡 수 구하기 
		int count = dao.CountMusicsIncludingKeyword(searchKeyword);
		
		//request 넣어주기 
		request.setAttribute("searchKeyword", searchKeyword);
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		
		//페이지 이동
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/searchResult.jsp");
		requestDispatcher.forward(request, response);
		
	}

}
