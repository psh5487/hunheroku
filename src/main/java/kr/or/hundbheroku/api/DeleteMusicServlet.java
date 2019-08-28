package kr.or.hundbheroku.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.hundbheroku.dao.MusicDao;
import kr.or.hundbheroku.dto.MusicDto;

@WebServlet("/DeleteMusicServlet")
public class DeleteMusicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteMusicServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
        //form에서 해당 바코드 받아오기
        String barcode = request.getParameter("item_barcode");
        
        // DAO생성
        MusicDao dao = new MusicDao();
        
        // 바코드에 해당하는 음악 지우기 
        dao.deleteMusic(barcode);
        
        // 컨텍스트 페스 경로가져오기
        String context = request.getContextPath();
        
        // 페이지 이동(리다이렉트) 
        response.sendRedirect(context + "/MusicList");
	}

}
