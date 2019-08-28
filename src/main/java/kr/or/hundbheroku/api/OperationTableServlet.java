package kr.or.hundbheroku.api;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.lang.String;

import com.google.gson.*;

import kr.or.hundbheroku.dao.classTimeDao;
import kr.or.hundbheroku.dto.classTimeDto;

@WebServlet("/operationTable")
public class OperationTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OperationTableServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		classTimeDao dao = new classTimeDao();

		List<classTimeDto> list = dao.getClassTimes();
		int listLength = list.size();
		
		request.setAttribute("classTimeTableAll", list);
		request.setAttribute("classTimesLength", listLength);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/operation.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html;charset=utf-8");
//	    response.setContentType("application/json; charset=utf-8");
	    
	    //test - request안의 모든 key(parameter)와 value 확인하기 
//	    Enumeration params = request.getParameterNames();
//	    System.out.println("----------------------------");
//	    while (params.hasMoreElements()){
//	        String name = (String)params.nextElement();
//	        System.out.println(name + " : " + request.getParameter(name));
//	    }
//	    System.out.println("----------------------------");

	    //ajax 통신 결과를 보내기 
//	    PrintWriter out = response.getWriter(); 
//	    out.print((String)"서버에서 값을 받음!");
	    
	    String value = request.getParameter("sendString");
//	    System.out.println("value of 'sendString' is " + value); //test
	    
	    //json 문자열인 value 를 json 객체로 변환 (gson library)
	    JsonParser Parser = new JsonParser();
	    JsonObject jsonObj = (JsonObject) Parser.parse(value); 
	    
	    //name 받아오기 
	    String name = jsonObj.get("name").getAsString();
	    
	    //시간표 받아오기
	    JsonArray memberArray = (JsonArray) jsonObj.get("classTime");
	    
	    //test
//	    System.out.println(memberArray);
	    
	    //String M1, M2, ..., F5 정해주기 
	    String M1 = "0";  String M2 = "0";  String M3 = "0";  String M4 = "0";  String M5 = "0";
		String T1 = "0";  String T2 = "0";  String T3 = "0";  String T4 = "0";  String T5 = "0";
		String W1 = "0";  String W2 = "0";  String W3 = "0";  String W4 = "0";  String W5 = "0";
	    String TH1 = "0"; String TH2 = "0"; String TH3 = "0"; String TH4 = "0"; String TH5 = "0";
		String F1 = "0";  String F2 = "0";  String F3 = "0";  String F4 = "0";  String F5 = "0";
		
	    for (int i = 0; i < memberArray.size(); i++) {
	    	String str = memberArray.get(i).getAsString();
	    	
	    	if(str.equals("M1")) M1 = "1";
	    	else if(str.equals("M2")) M2 = "1";
	    	else if(str.equals("M3")) 
	    	{
	    		M2 = "1"; //3시 수업인 사람은 2시 45분에 떠나야하므로ㅠㅠ M2도 스케줄 있는 거로 간주. 
	    		M3 = "1";
	    	}
	    	else if(str.equals("M4")) M4 = "1";
	    	else if(str.equals("M5")) M5 = "1";
	    	else if(str.equals("M6")) 
	    	{
	    		M5 = "1";
	    	}
	    	else if(str.equals("T1")) T1 = "1";
	    	else if(str.equals("T2")) T2 = "1";
	    	else if(str.equals("T3")) 
	    	{
	    		T2 = "1";
	    		T3 = "1";
	    	}
	    	else if(str.equals("T4")) T4 = "1";
	    	else if(str.equals("T5")) T5 = "1";
	    	else if(str.equals("T6")) 
	    	{
	    		T5 = "1";
	    	}
	    	else if(str.equals("W1")) W1 = "1";
	    	else if(str.equals("W2")) W2 = "1";
	    	else if(str.equals("W3")) 
	    	{
	    		W2 = "1";
	    		W3 = "1";
	    	}
	    	else if(str.equals("W4")) W4 = "1";
	    	else if(str.equals("W5")) W5 = "1";
	    	else if(str.equals("W6")) 
	    	{
	    		W5 = "1";
	    	}
	    	else if(str.equals("TH1")) TH1 = "1";
	    	else if(str.equals("TH2")) TH2 = "1";
	    	else if(str.equals("TH3")) 
	    	{
	    		TH2 = "1";
	    		TH3 = "1";
	    	}
	    	else if(str.equals("TH4")) TH4 = "1";
	    	else if(str.equals("TH5")) TH5 = "1";
	    	else if(str.equals("TH6")) 
	    	{
	    		TH5 = "1";
	    	}
	    	else if(str.equals("F1")) F1 = "1";
	    	else if(str.equals("F2")) F2 = "1";
	    	else if(str.equals("F3")) 
	    	{
	    		F2 = "1";
	    		F3 = "1";
	    	}
	    	else if(str.equals("F4")) F4 = "1";
	    	else if(str.equals("F5")) F5 = "1";
	    	else if(str.equals("F6")) 
	    	{
	    		F5 = "1";
	    	}
	    }
	    
	    //데이터를 DTO에 저장 
	    int id = 0;
	    
        classTimeDto dto = new classTimeDto(id, name, M1, M2, M3, M4, M5, T1, T2, T3, T4, T5, 
					W1, W2, W3, W4, W5, TH1, TH2, TH3, TH4, TH5, F1, F2, F3, F4, F5);
        
        //DAO생성
        classTimeDao dao = new classTimeDao();
        
        //테이블에 저장
        dao.addClassTime(dto);
	}

}
