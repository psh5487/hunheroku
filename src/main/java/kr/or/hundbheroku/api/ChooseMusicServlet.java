package kr.or.hundbheroku.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.hundbheroku.dao.MusicDao;
import kr.or.hundbheroku.dto.MusicDto;


@WebServlet("/chooseMusic")
public class ChooseMusicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChooseMusicServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/chooseMusic.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//DAO생성
		MusicDao dao = new MusicDao();
		
		//id 정렬 
        dao.sortingId();
        
        //list 길이 구하기 
     	List<MusicDto> list = dao.getMusics();
     	int listLength = list.size();
     	
     	//list가 충분하지 않을 때 
     	if(listLength < 15)
     	{
     		String context = request.getContextPath();
     		
     		PrintWriter out = response.getWriter();
        	out.println("<script>alert('곡을 15개 이상 입력하세요!'); location.href='"+context+"/MusicList';</script>");
        	out.flush();
     	}
		
     	//랜덤하게 id 15개 뽑기
		int a[] = new int[15];    
        Random r = new Random(); 
        
        for(int i = 0; i < 15; i++) 
        {
            a[i] = r.nextInt(listLength)+1; //id 1~10 중 랜덤으로 하나를 뽑아 a[i]에 저장
            
            for(int j = 0; j < i; j++)      //중복제거를 위한 for문 
            {
                /*현재 a[i]에 저장된 랜덤숫자와 이전의 a[]에 들어간 숫자 비교
                 ※예를 들어
                 배열 a[3]에 숫자 6이 들어갔을때 이전에 완성된 배열 a[0],a[1],a[2]와 비교하여
                 숫자 6이 중복되지 않을시 다음 a[4]으로 넘어가고, 중복된다면 다시 a[3]에 중복되지   
                 않는 숫자를 넣기 위하여 i를 한번 감소한후 처음 for문으로 돌아가 다시 a[3]을 채운다
                 */
                if(a[i]==a[j])  
                {
                    i--;
                }
            }
        }
        
        String name = request.getParameter("chooseMusicName"); 
    	
        // sql 결과를 담기 
     	List<MusicDto> listOfChoosedMusics = dao.choosedMusics(a);
    	
     	request.setAttribute("choosedMusicAll", listOfChoosedMusics);
     	request.setAttribute("chossedMusicLength", 15);
        
        // 페이지 이동 
//        String context = request.getContextPath();
//        response.sendRedirect(context + "/chooseMusic");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/chooseMusic.jsp");
		requestDispatcher.forward(request, response);
        
	}
}
