package kr.or.hundbheroku.api;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/updateMusic")
public class UpdateMusicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateMusicServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		String barcode = request.getParameter("barcode");
		
		//DAO생성
		MusicDao dao = new MusicDao();
						
		// 전체 곡 수 구하기 
		List<MusicDto> music = dao.getMusic(barcode);
		
		System.out.println(music);
		
		request.setAttribute("music", music);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/updateMusicForm.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//form으로부터 넘어온 값 저장
		String barcode = request.getParameter("barcode");
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String artist = request.getParameter("artist")==null?"":request.getParameter("artist");
		String track = request.getParameter("track")==null?"":request.getParameter("track");
		String label = request.getParameter("label")==null?"":request.getParameter("label");
		
		//디스크 수 
		String numOfDisc = request.getParameter("numOfDisc")==null?"":request.getParameter("numOfDisc");
		
		if(numOfDisc.equals("")) 
			numOfDisc = "1";
		    
		//작곡가 
		String composer = request.getParameter("composer")==null?"":request.getParameter("composer");
		
		if(composer.equals(""))
		{
			composer = "기타";
		}
		else
		{
			while(composer.indexOf(", ") >= 0)
				composer = composer.replace(", ", "\n");
			
			while(composer.indexOf(",") >= 0)
				composer = composer.replace(",", "\n");
		}
		
		//아티스트 
		if(!artist.equals(""))
		{
			while(artist.indexOf(", ") >= 0)
				artist = artist.replace(", ", "\n");
			
			while(artist.indexOf(",") >= 0)
				artist = artist.replace(",", "\n");
		}
			
		//선호도 
		int importance;
		String importanceFromForm = request.getParameter("importance");
		if(importanceFromForm == null) {
			importance = 2;
		}
		else {
			importance = Integer.parseInt(request.getParameter("importance")); 
		}
		   
        //dto 형식 맞추기 위함 
        String regdate = "";
        int id = 0;
		
        //데이터를 DTO에 저장
        MusicDto dto = new MusicDto(title, artist, composer, category, track, label, barcode, regdate, importance, numOfDisc, id);
        
        //DAO생성
        MusicDao dao = new MusicDao();
        
        //디비에 저장
        int result = dao.updateMusic(dto);
        
        // 저장결과 alert 창 띄우기 
        String context = request.getContextPath();
        
        if(result == 1)
        {
        	PrintWriter out = response.getWriter();
        	out.println("<script>alert('수정되었습니다!'); location.href='"+context+"/MusicList';</script>");
        	out.flush();
        } 
        else if(result == 0)
        {
        	PrintWriter out = response.getWriter();
        	out.println("<script>alert('같은 바코드 넘버로 이미 저장된 곡이 있습니다!'); location.href='"+context+"/updateMusic';</script>");
        	out.flush();
        }
        else if(result == -1)
        {
        	PrintWriter out = response.getWriter();
        	out.println("<script>alert('DB 접속에 문제가 있습니다.'); location.href='"+context+"/updateMusic';</script>");
        	out.flush();
        }
	}

}
