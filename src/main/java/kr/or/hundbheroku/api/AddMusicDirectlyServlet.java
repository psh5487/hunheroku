package kr.or.hundbheroku.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.hundbheroku.dao.MusicDao;
import kr.or.hundbheroku.dto.MusicDto;

@WebServlet("/addMusicDirectly")
public class AddMusicDirectlyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddMusicDirectlyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/musicComplexForm.jsp");
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
        String composer = request.getParameter("composerFromSelect")==null?"":request.getParameter("composerFromSelect");
	    if(composer.equals("기타") || composer.equals(""))
	    {
	    	composer = request.getParameter("composerFromInput")==null?"":request.getParameter("composerFromInput");
	    	
	    	if(composer.equals(""))
	    	{
	    		composer = "기타";
	    	}
	    	else
	    	{
	    		//composer 첫글자만 대문자 처리 
	    	    String[] arr = composer.trim().split(" ");
	    	    StringBuffer sb = new StringBuffer();

	    	    for (int j = 0; j < arr.length; j++) {
	    	        sb.append(Character.toUpperCase(arr[j].charAt(0)))
	    	          .append(arr[j].substring(1).toLowerCase())
	    	          .append(" ");
	    	    }
	    	    	
	    	    composer = sb.toString().trim().replace(", ", "\n");
	    	}
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
        
        //artist 형식 맞추기 
        artist = artist.replace("-", " ");
		
		//artist 첫글자만 대문자 처리 
	    String[] arr = artist.trim().split(" ");
	    StringBuffer sb = new StringBuffer();

	    for (int j = 0; j < arr.length; j++) {
	        sb.append(Character.toUpperCase(arr[j].charAt(0)))
	          .append(arr[j].substring(1).toLowerCase())
	          .append(" ");
	    }
	    
	    artist = sb.toString().trim().replace(", ", "\n");
		
        //dto 형식 맞추기 위함 
        String regdate = "";
        int id = 0;
		
        //데이터를 DTO에 저장
        MusicDto dto = new MusicDto(title, artist.trim(), composer.trim(), category, track, label, barcode, regdate, importance, numOfDisc, id);
        
        //DAO생성
        MusicDao dao = new MusicDao();
        
        //디비에 저장
        int result = dao.addMusic(dto);
        
        // 저장결과 alert 창 띄우기 
        String context = request.getContextPath();
        
        if(result == 1)
        {
        	PrintWriter out = response.getWriter();
        	out.println("<script>alert('저장되었습니다!'); location.href='"+context+"/MusicList';</script>");
        	out.flush();
        } 
        else if(result == 0)
        {
        	PrintWriter out = response.getWriter();
        	out.println("<script>alert('이미 저장된 곡입니다!'); location.href='"+context+"/addMusicDirectly';</script>");
        	out.flush();
        }
        else if(result == -1)
        {
        	PrintWriter out = response.getWriter();
        	out.println("<script>alert('DB 접속에 문제가 있습니다.'); location.href='"+context+"/addMusicDirectly';</script>");
        	out.flush();
        }
		
//		  //컨텍스트 페스 경로가져오기
//        String context = request.getContextPath();
//        
//        // 페이지 이동(리다이렉트) 
//        response.sendRedirect(context + "/MusicList");
        
	}

}
