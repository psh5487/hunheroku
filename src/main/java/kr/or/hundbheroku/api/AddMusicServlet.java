package kr.or.hundbheroku.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.hundbheroku.dao.MusicDao;
import kr.or.hundbheroku.dto.MusicDto;

//for crwaling 
import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@WebServlet("/addMusic")
public class AddMusicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddMusicServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/musicForm.jsp");
		requestDispatcher.forward(request, response);
	}
	
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String context = request.getContextPath();
	
	    // form tag로부터 넘어온 값 저장 - 바코드, 선호도, category, 저장 시간(""로 보내면 db에서 알아서~)
	    String barcode = request.getParameter("barcode");
	    String category = request.getParameter("category");
	    
	    String composer = request.getParameter("composerFromSelect")==null?"":request.getParameter("composerFromSelect");
	    if(composer.equals("기타") || composer.equals(""))
	    {
	    	composer = request.getParameter("composerFromInput")==null?"":request.getParameter("composerFromInput");
	    	
	    	if(composer.equals(""))
	    	{
	    		composer = "기타";
	    	}
	    }
	    
	    int importance;
	    String importanceFromForm = request.getParameter("importance");
	    if(importanceFromForm == null) {
	    	importance = 2;
	    }
	    else {
	    	importance = Integer.parseInt(request.getParameter("importance")); 
	    }
	    
	    String regdate = "";
	    int id = 0;
	    
	    //크롤링
	    String title = "";
	    String artist = "";
	    String track = "";
	    String label = "";
	    String numOfDisc = "1";
        

        //핫트랙스 페이지 url 
        String url = "https://www.hottracks.co.kr/ht/record/detail/" + barcode;
        System.out.println(url + " 접속하기");
       
        //링크 대상 페이지에 접근하기
	    Document doc = Jsoup.connect(url).userAgent("Jsoup Scraper").get();
	    
	    //title
	    title = doc.select(".tit.mgt30").text();  
	    
	    if(title.equals("")) //핫트랙스 크롤링 실패, 아마존 크롤링 시도 
	    {
	    	//아마존 페이지 url 가져오기
		    String amazonUrl = "https://www.amazon.com/s?k=" + barcode;
		    System.out.println(amazonUrl + " 접속하기");
		    
		    // GET 요청을 보내고 Document 객체를 변수 doc에 저장하기
		    Document amazonDoc = Jsoup.connect(amazonUrl).userAgent("Jsoup Scraper").get();
		
		    // CSS 선택자를 사용해 링크 추출하기
		    Elements titleLine = amazonDoc.select(".a-link-normal.a-text-normal");
		    
		 	// 링크의 URL 추출하기 (절대경로)
		    String nextUrl = "";
		 	nextUrl = titleLine.attr("abs:href");
		 	
		 	if(nextUrl.equals(""))
		 	{
		 		System.out.println("크롤링 실패\n");
		 		
		 		PrintWriter out = response.getWriter();
		    	out.println("<script>alert('현재 판매되지 않는 음반으로, 곡을 직접 추가해야 합니다.'); location.href='"+context+"/MusicList';</script>");
		    	out.flush();
		 	}
		 	else
		 	{
		     	System.out.println(nextUrl + " 접속 성공");
		     	
		     	// 링크 대상 페이지에 접근하기
		     	Document nextDoc = Jsoup.connect(nextUrl).userAgent("Jsoup Scraper").get();
		     	     	
		     	// title, artist, composer, track, label, numOfDisc 값 추출하여 저장 
		     	//title
		     	title = nextDoc.select("#productTitle").text();  
		     	System.out.println(title);
		     	
		     	//artist
		     	Elements artists = nextDoc.select(".author > .a-link-normal"); 
		        
		        for(Element element: artists) {  //Elements 길이만큼 반복된다. 
		        	String artistRaw = element.text();
		        	
		        	if(artistRaw.indexOf(",") != -1) //artist 명에서 ',' 발견 ex) Gould, Glenn
	        		{
	        			String[] arr = artistRaw.split(",");
	        			artistRaw = arr[1].trim() + " " + arr[0].trim();
	        		}
		        	
		        	artistRaw = artistRaw.replace("-", " ");
	        		
		        	String artistLow = artistRaw.toLowerCase();
		        	
		        	if(artistLow.indexOf("bach") >= 0 || artistLow.indexOf("bartok") >= 0 || artistLow.indexOf("beethoven") >= 0 || artistLow.indexOf("bernstein") >= 0 ||
		        	   artistLow.indexOf("bizet") >= 0 || artistLow.indexOf("brahms") >= 0 || artistLow.indexOf("britten") >= 0 || artistLow.indexOf("chopin") >= 0 || artistLow.indexOf("debussy") >= 0 ||
		        	   artistLow.indexOf("dvorak") >= 0 || artistLow.indexOf("elgar") >= 0 || artistLow.indexOf("faure") >= 0 || artistLow.indexOf("franck") >= 0 || artistLow.indexOf("gershwin") >= 0 ||
		        	   artistLow.indexOf("grieg") >= 0 || artistLow.indexOf("haydn") >= 0 || artistLow.indexOf("handel") >= 0 || artistLow.indexOf("johann strauss") >= 0 || artistLow.indexOf("liszt") >= 0 ||
		        	   artistLow.indexOf("mahler") >= 0 || artistLow.indexOf("mendelssohn") >= 0 || artistLow.indexOf("messiaen") >= 0 || artistLow.indexOf("mozart") >= 0 || artistLow.indexOf("mussorgsky") >= 0 ||
		        	   artistLow.indexOf("paganini") >= 0 || artistLow.indexOf("prokofiev") >= 0 || artistLow.indexOf("puccini") >= 0 || artistLow.indexOf("ravel") >= 0 || artistLow.indexOf("rachmanino") >= 0 ||
		        	   artistLow.indexOf("richard strauss") >= 0 || artistLow.indexOf("rimsky") >= 0 || artistLow.indexOf("rodrigo") >= 0 || artistLow.indexOf("rosssini") >= 0 || artistLow.indexOf("rubinstein") >= 0 ||
		        	   artistLow.indexOf("saens") >= 0 || artistLow.indexOf("saëns") >= 0 || artistLow.indexOf("salieri") >= 0 || artistLow.indexOf("schoenberg") >= 0 || artistLow.indexOf("schubert") >= 0 ||
		        	   artistLow.indexOf("schumann") >= 0 || artistLow.indexOf("shostakovich") >= 0 || artistLow.indexOf("sibelius") >= 0 || artistLow.indexOf("stravinsky") >= 0 || artistLow.indexOf("tchaikovsky") >= 0 ||
		        	   artistLow.indexOf("telemann") >= 0 || artistLow.indexOf("verdi") >= 0 || artistLow.indexOf("vivaldi") >= 0 || artistLow.indexOf("wagner") >= 0 || artistLow.indexOf("weber") >= 0 
		        	   )
		        	{
		        		String composerLow = composer.toLowerCase();
		        		String artistLastname = artistRaw.substring(artistRaw.lastIndexOf(" ")+1); //마지막 문자열만 가져오기(이름의 성만 가져오기)
		        		
		        		if(composerLow.indexOf(artistLastname.toLowerCase()) == -1) 
		        		{
		        			composer += "\n";
		        			composer += artistLastname.substring(0, 1).toUpperCase() + artistLastname.substring(1).toLowerCase(); //첫 글자만 대문자화
		        		}
		        	}
		        	else //aritst가 작곡가가 아닐 경우 
		        	{
			    		//artist 첫글자만 대문자 처리 
					    String[] arr = artistRaw.trim().split(" ");
					    StringBuffer sb = new StringBuffer();

					    for (int j = 0; j < arr.length; j++) {
					        sb.append(Character.toUpperCase(arr[j].charAt(0)))
					          .append(arr[j].substring(1).toLowerCase())
					          .append(" ");
					    }
					    
					    artist = sb.toString().trim();	
					    artist += "\n";
		        	}
		        }
		        
		        System.out.println(composer);
		        System.out.println(artist);
		        
		        //track
		        Elements tracks = nextDoc.select(".a-section > .TitleLink"); 
		        
		        for(Element element: tracks) {  //Elements 길이만큼 반복된다. 
		        	track += element.text();
		        	track += "\n";
		        }
		        System.out.println(track);
		        
		        //label, numOfDisc 
		        Elements NodeList = nextDoc.select(".content li");
		        
		        for(int i = 0; i < NodeList.size(); i++)
		        {
		        	String innerText = NodeList.get(i).text();
		        	
		        	if(innerText.indexOf("Number of Discs:") != -1)
		        	{
		        		numOfDisc = innerText.substring(17);
		        		System.out.println(numOfDisc);
		        	}
		        	
		        	if(innerText.indexOf("Label:") != -1)
		        	{
		        		label = innerText.substring(7);
		        		System.out.println(label);
		        	}
		        }
	        
		        //DB 작업
		        // 폼에서 입력 받은 데이터를 DTO에 저장
		        MusicDto dto = new MusicDto(title, artist, composer, category, track, label, barcode, regdate, importance, numOfDisc, id);
		        
		        // DAO생성
		        MusicDao dao = new MusicDao();
		        
		        // 테이블에 저장
		        int result = dao.addMusic(dto);
		        
		        // 저장결과 alert 창 띄우기 
		        if(result == 1)
		        {
		        	PrintWriter out = response.getWriter();
		        	out.println("<script>alert('저장되었습니다!'); location.href='"+context+"/MusicList';</script>");
		        	out.flush();
		        } 
		        else if(result == 0)
		        {
		        	PrintWriter out = response.getWriter();
		        	out.println("<script>alert('이미 저장된 곡입니다!'); location.href='"+context+"/addMusic';</script>");
		        	out.flush();
		        }
		        else if(result == -1)
		        {
		        	PrintWriter out = response.getWriter();
		        	out.println("<script>alert('DB 접속에 문제가 있습니다.'); location.href='"+context+"/addMusic';</script>");
		        	out.flush();
		        }
		 	}
	    	
	    }
	    else //핫트랙스 크롤링 성공
	    {
	    	System.out.println("핫트랙스 크롤링 시작\n");
	    	
	    	// title, artist, composer, track, label, numOfDisc 값 추출하여 저장 
	    	//title
	    	System.out.println(title);
	    	
		    //artist, label
		    Elements NodeList = doc.select(".cover li");
		    
		    for(int i = 0; i < NodeList.size(); i++)
		    {
		    	String innerText = NodeList.get(i).text();
		    	
		    	if(innerText.indexOf("연주자 :") != -1)
		    	{
		    		artist = innerText.substring(6).replace("-", " ");
		    		
		    		//artist 첫글자만 대문자 처리 
				    String[] arr = artist.trim().split(" ");
				    StringBuffer sb = new StringBuffer();

				    for (int j = 0; j < arr.length; j++) {
				        sb.append(Character.toUpperCase(arr[j].charAt(0)))
				          .append(arr[j].substring(1).toLowerCase())
				          .append(" ");
				    }
				    
				    artist = sb.toString().trim().replace(", ", "\n");	
				    artist += "\n";
		    	}
		    	else if(innerText.indexOf("지휘자 :") != -1)
		    	{
		    		String conductor = innerText.substring(6).replace("-", " ");
		    		
		    		//conductor 첫글자만 대문자 처리 
				    String[] arr = conductor.trim().split(" ");
				    StringBuffer sb = new StringBuffer();

				    for (int j = 0; j < arr.length; j++) {
				        sb.append(Character.toUpperCase(arr[j].charAt(0)))
				          .append(arr[j].substring(1).toLowerCase())
				          .append(" ");
				    }
				    
				    conductor = sb.toString().trim().replace(", ", "\n");
				    
				    //artist에 붙여주기 
				    artist += conductor;
				    artist += "\n";
		    	}
		    	else if(innerText.indexOf("오케스트라 :") != -1)
		    	{
		    		String orchestra = innerText.substring(8).replace("-", " ");
		    		
		    		//orchestra 첫글자만 대문자 처리 
				    String[] arr = orchestra.trim().split(" ");
				    StringBuffer sb = new StringBuffer();

				    for (int j = 0; j < arr.length; j++) {
				        sb.append(Character.toUpperCase(arr[j].charAt(0)))
				          .append(arr[j].substring(1).toLowerCase())
				          .append(" ");
				    }
				    
				    orchestra = sb.toString().trim().replace(", ", "\n");
				    
				    //artist에 붙여주기 
				    artist += orchestra;
				    artist += "\n";
		    	}
		    	else if(innerText.indexOf("레이블 :") != -1)
		    	{
		    		label = innerText.substring(6);
		    		System.out.println(label);
		    	}
		    }
		    
		    //artist 문자열에서 괄호 및 괄호 내용 제거하기 
		    Pattern PATTERN_BRACKET = Pattern.compile("\\([^\\(\\)]+\\)");
		    Matcher matcher = PATTERN_BRACKET.matcher(artist);
	        
	        String removeTextArea = new String();
	        
	        while(matcher.find()) {
	            int startIndex = matcher.start();
	            int endIndex = matcher.end();
	            
	            removeTextArea = artist.substring(startIndex, endIndex);
	            artist = artist.replace(removeTextArea, "");
	            matcher = PATTERN_BRACKET.matcher(artist);
	        }
	        
		    System.out.println(artist);
		    
		    //track
		    Elements tracks = doc.select(".t_left"); 
		    
		    for(Element element: tracks) {  //Elements 길이만큼 반복된다. 
		    	track += element.text();
		    	track += "\n";
		    }
		    
		    System.out.println(track);
		    
		    //numOfDisc 
		    Elements NodeList2 = doc.select(".album_option li");
		
		    String raw_numOfDisc = NodeList2.get(7).text();
		    numOfDisc = raw_numOfDisc.substring(8, 9); //디스크 수 : 4 DISC | 에서 4 추출 
		    
		    System.out.println(numOfDisc);
		    
		    //DB 작업
		    // 폼에서 입력 받은 데이터를 DTO에 저장
		    MusicDto dto = new MusicDto(title, artist.trim(), composer.trim(), category, track.trim(), label, barcode, regdate, importance, numOfDisc, id);
		    
		    // DAO생성
		    MusicDao dao = new MusicDao();
		    
		    // 테이블에 저장
		    int result = dao.addMusic(dto);
		    
		    // 저장결과 alert 창 띄우기 
		    if(result == 1)
		    {
		    	PrintWriter out = response.getWriter();
		    	out.println("<script>alert('저장되었습니다!'); location.href='"+context+"/MusicList';</script>");
		    	out.flush();
		    } 
		    else if(result == 0)
		    {
		    	PrintWriter out = response.getWriter();
		    	out.println("<script>alert('이미 저장된 곡입니다!'); location.href='"+context+"/addMusic';</script>");
		    	out.flush();
		    }
		    else if(result == -1)
		    {
		    	PrintWriter out = response.getWriter();
		    	out.println("<script>alert('DB 접속에 문제가 있습니다.'); location.href='"+context+"/addMusic';</script>");
		    	out.flush();
		    }
		}
	    
	}
}
