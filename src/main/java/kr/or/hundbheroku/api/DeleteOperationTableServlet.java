package kr.or.hundbheroku.api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.hundbheroku.dao.classTimeDao;

@WebServlet("/DeleteOperationTableServlet")
public class DeleteOperationTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteOperationTableServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
        //form에서 해당 id 받아오기
        int id = Integer.parseInt(request.getParameter("item_id"));
        
        // DAO생성
        classTimeDao dao = new classTimeDao();
        
        // 테이블에 저장
        dao.deleteOperationTable(id);
        
        // 컨텍스트 페스 경로가져오기
        String context = request.getContextPath();
        
        // 페이지 이동(리다이렉트) 
        response.sendRedirect(context + "/MakingOperationTable");
	}

}
