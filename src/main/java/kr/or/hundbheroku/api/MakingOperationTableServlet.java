package kr.or.hundbheroku.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.hundbheroku.dao.classTimeDao;
import kr.or.hundbheroku.dto.classTimeDto;

@WebServlet("/MakingOperationTable")
public class MakingOperationTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MakingOperationTableServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		//학생들이 제출한 시간표 다 가져오기(제출한 사람 이름 가져오기 위함)
		classTimeDao dao = new classTimeDao();
		
		List<classTimeDto> list_classTime = dao.getClassTimes();
		int listLength_classTime = list_classTime.size();
		
		request.setAttribute("classTimeTableAll", list_classTime);
		request.setAttribute("classTimesLength", listLength_classTime);
		
		//운영시간표 가져오기 
		List<classTimeDto> list = dao.getOperationTables();
		int listLength = list.size();
		
		request.setAttribute("operationTableAll", list);
		request.setAttribute("operationTablesLength", listLength);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/makingOperationTable.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		//학생들이 제출한 시간표 다 가져오기
		classTimeDao dao = new classTimeDao();
		List<classTimeDto> list = dao.getClassTimes();
		
		int listLength = list.size();
		
		//String M1, M2, ..., F5 정해주기 
		String M1 = "X";  String M2 = "X";  String M3 = "X";  String M4 = "X";  String M5 = "X";
		String T1 = "X";  String T2 = "X";  String T3 = "X";  String T4 = "X";  String T5 = "X";
		String W1 = "X";  String W2 = "X";  String W3 = "X";  String W4 = "X";  String W5 = "X";
	    String TH1 = "X"; String TH2 = "X"; String TH3 = "X"; String TH4 = "X"; String TH5 = "X";
		String F1 = "X";  String F2 = "X";  String F3 = "X";  String F4 = "X";  String F5 = "X";
		
		//학생 당 몇 타임 배정되었는지 알려주는 맵  
		HashMap<String, String> hm = new HashMap<String, String>();
		
		//3타임 연속 되는 사람 찾기
		for(int i = 0; i < listLength; i++)
		{
			classTimeDto dto = list.get(i);
			String name = list.get(i).getName();
			
			if(dto.getM1().equals("0") && dto.getM2().equals("0") && dto.getM3().equals("0")) 
			{
				if(M1.equals("X") && M2.equals("X") && M3.equals("X"))
				{
					M1 = name;
					M2 = name;
					M3 = name;
					
					hm.put(name, "M1 3times"); 
					continue;
				}
			}
			else if(dto.getM2().equals("0") && dto.getM3().equals("0") && dto.getM4().equals("0"))
			{
				if(M2.equals("X") && M3.equals("X") && M4.equals("X"))
				{
					M2 = name;
					M3 = name;
					M4 = name;
					
					hm.put(name, "M2 3times");
					continue;
				}
			}
			else if(dto.getM3().equals("0") && dto.getM4().equals("0") && dto.getM5().equals("0"))
			{
				if(M3.equals("X") && M4.equals("X") && M5.equals("X"))
				{
					M3 = name;
					M4 = name;
					M5 = name;
					
					hm.put(name, "M3 3times");
					continue;
				}
			}
			
			if(dto.getT1().equals("0") && dto.getT2().equals("0") && dto.getT3().equals("0"))
			{
				if(T1.equals("X") && T2.equals("X") && T3.equals("X"))
				{
					T1 = name;
					T2 = name;
					T3 = name;
					
					hm.put(name, "T1 3times");
					continue;
				}
			}
			else if(dto.getT2().equals("0") && dto.getT3().equals("0") && dto.getT4().equals("0"))
			{
				if(T2.equals("X") && T3.equals("X") && T4.equals("X"))
				{
					T2 = name;
					T3 = name;
					T4 = name;
					
					hm.put(name, "T2 3times");
					continue;
				}
			}
			else if(dto.getT3().equals("0") && dto.getT4().equals("0") && dto.getT5().equals("0"))
			{
				if(T3.equals("X") && T4.equals("X") && T5.equals("X"))
				{
					T3 = name;
					T4 = name;
					T5 = name;
					
					hm.put(name, "T3 3times");
					continue;
				}
			}
			
			if(dto.getW1().equals("0") && dto.getW2().equals("0") && dto.getW3().equals("0"))
			{
				if(W1.equals("X") && W2.equals("X") && W3.equals("X"))
				{
					W1 = name;
					W2 = name;
					W3 = name;
					
					hm.put(name, "W1 3times");
					continue;
				}
			}
			else if(dto.getW2().equals("0") && dto.getW3().equals("0") && dto.getW4().equals("0"))
			{
				if(W2.equals("X") && W3.equals("X") && W4.equals("X"))
				{
					W2 = name;
					W3 = name;
					W4 = name;
					
					hm.put(name, "W2 3times");
					continue;
				}
			}
			else if(dto.getW3().equals("0") && dto.getW4().equals("0") && dto.getW5().equals("0"))
			{
				if(W3.equals("X") && W4.equals("X") && W5.equals("X"))
				{
					W3 = name;
					W4 = name;
					W5 = name;
					
					hm.put(name, "W3 3times");
					continue;
				}
			}
			
			if(dto.getTH1().equals("0") && dto.getTH2().equals("0") && dto.getTH3().equals("0"))
			{
				if(TH1.equals("X") && TH2.equals("X") && TH3.equals("X"))
				{
					TH1 = name;
					TH2 = name;
					TH3 = name;
					
					hm.put(name, "TH1 3times");
					continue;
				}
			}
			else if(dto.getTH2().equals("0") && dto.getTH3().equals("0") && dto.getTH4().equals("0"))
			{
				if(TH2.equals("X") && TH3.equals("X") && TH4.equals("X"))
				{
					TH2 = name;
					TH3 = name;
					TH4 = name;
					
					hm.put(name, "TH2 3times");
					continue;
				}
			}
			else if(dto.getTH3().equals("0") && dto.getTH4().equals("0") && dto.getTH5().equals("0"))
			{
				if(TH3.equals("X") && TH4.equals("X") && TH5.equals("X"))
				{
					TH3 = name;
					TH4 = name;
					TH5 = name;
					
					hm.put(name, "TH3 3times");
					continue;
				}
			}
			
			if(dto.getF1().equals("0") && dto.getF2().equals("0") && dto.getF3().equals("0"))
			{
				if(F1.equals("X") && F2.equals("X") && F3.equals("X"))
				{
					F1 = name;
					F2 = name;
					F3 = name;
					
					hm.put(name, "F1 3times");
					continue;
				}
			}
			else if(dto.getF2().equals("0") && dto.getF3().equals("0") && dto.getF4().equals("0"))
			{
				if(F2.equals("X") && F3.equals("X") && F4.equals("X"))
				{
					F2 = name;
					F3 = name;
					F4 = name;
					
					hm.put(name, "F2 3times");
					continue;
				}
			}
			else if(dto.getF3().equals("0") && dto.getF4().equals("0") && dto.getF5().equals("0"))
			{
				if(F3.equals("X") && F4.equals("X") && F5.equals("X"))
				{
					F3 = name;
					F4 = name;
					F5 = name;
					
					hm.put(name, "F3 3times");
					continue;
				}
			}
			
			//test
//			System.out.printf("after loop: "
//					+ "M1: %s, M2: %s, M3: %s, M4: %s, M5: %s, T1: %s, T2: %s, T3: %s, T4: %s, T5: %s, "
//					+ "W1: %s, W2: %s, W3: %s, W4: %s, W5: %s, TH1: %s, TH2: %s, TH3: %s, TH4: %s, TH5: %s, "
//					+ "F1: %s, F2: %s, F3: %s, F4: %s, F5: %s \n"
//					,M1, M2, M3, M4, M5, T1, T2, T3, T4, T5, W1, W2, W3, W4, W5, TH1, TH2, TH3, TH4, TH5, F1, F2, F3, F4, F5);
		}
		
		//2타임 연속 되는 사람 찾기
		for(int i = 0; i < listLength; i++)
		{
			classTimeDto dto = list.get(i);
			String name = list.get(i).getName();
			
			if(hm.containsKey(name) == false) //아직 배정 안 받은 사람 대상 
			{	
				if(M1.equals("X") && M2.equals("X"))
				{
					if(dto.getM1().equals("0") && dto.getM2().equals("0"))
					{
						M1 = name;
						M2 = name;
						
						hm.put(name, "M1 2times straight");
						continue;
					}
				}
				else if(M2.equals("X") && M3.equals("X"))
				{
					if(dto.getM2().equals("0") && dto.getM3().equals("0"))
					{
						M2 = name;
						M3 = name;
						
						hm.put(name, "M2 2times straight");
						continue;
					}
				}
				else if(M3.equals("X") && M4.equals("X"))
				{
					if(dto.getM3().equals("0") && dto.getM4().equals("0"))
					{
						M3 = name;
						M4 = name;
						
						hm.put(name, "M3 2times straight");
						continue;
					}
				}
				else if(M4.equals("X") && M5.equals("X"))
				{
					if(dto.getM4().equals("0") && dto.getM5().equals("0"))
					{
						M4 = name;
						M5 = name;
						
						hm.put(name, "M4 2times straight");
						continue;
					}
				}
				
				if(T1.equals("X") && T2.equals("X"))
				{
					if(dto.getT1().equals("0") && dto.getT2().equals("0"))
					{
						T1 = name;
						T2 = name;
						
						hm.put(name, "T1 2times straight");
						continue;
					}
				}
				else if(T2.equals("X") && T3.equals("X"))
				{
					if(dto.getT2().equals("0") && dto.getT3().equals("0"))
					{
						T2 = name;
						T3 = name;
						
						hm.put(name, "T2 2times straight");
						continue;
					}
				}
				else if(T3.equals("X") && T4.equals("X"))
				{
					if(dto.getT3().equals("0") && dto.getT4().equals("0"))
					{
						T3 = name;
						T4 = name;
						
						hm.put(name, "T3 2times straight");
						continue;
					}
				}
				else if(T4.equals("X") && T5.equals("X"))
				{
					if(dto.getT4().equals("0") && dto.getT5().equals("0"))
					{
						T4 = name;
						T5 = name;
						
						hm.put(name, "T4 2times straight");
						continue;
					}
				}
				
				if(W1.equals("X") && W2.equals("X"))
				{
					if(dto.getW1().equals("0") && dto.getW2().equals("0"))
					{
						W1 = name;
						W2 = name;
						
						hm.put(name, "W1 2times straight");
						continue;
					}
				}
				else if(W2.equals("X") && W3.equals("X"))
				{
					if(dto.getW2().equals("0") && dto.getW3().equals("0"))
					{
						W2 = name;
						W3 = name;
						
						hm.put(name, "W2 2times straight");
						continue;
					}
				}
				else if(W3.equals("X") && W4.equals("X"))
				{
					if(dto.getW3().equals("0") && dto.getW4().equals("0"))
					{
						W3 = name;
						W4 = name;
						
						hm.put(name, "W3 2times straight");
						continue;
					}
				}
				else if(W4.equals("X") && W5.equals("X"))
				{
					if(dto.getW4().equals("0") && dto.getW5().equals("0"))
					{
						W4 = name;
						W5 = name;
						
						hm.put(name, "W4 2times straight");
						continue;
					}
				}
				
				if(TH1.equals("X") && TH2.equals("X"))
				{
					if(dto.getTH1().equals("0") && dto.getTH2().equals("0"))
					{
						TH1 = name;
						TH2 = name;
						
						hm.put(name, "TH1 2times straight");
						continue;
					}
				}
				else if(TH2.equals("X") && TH3.equals("X"))
				{
					if(dto.getTH2().equals("0") && dto.getTH3().equals("0"))
					{
						TH2 = name;
						TH3 = name;
						
						hm.put(name, "TH2 2times straight");
						continue;
					}
				}
				else if(TH3.equals("X") && TH4.equals("X"))
				{
					if(dto.getTH3().equals("0") && dto.getTH4().equals("0"))
					{
						TH3 = name;
						TH4 = name;
						
						hm.put(name, "TH3 2times straight");
						continue;
					}
				}
				else if(TH4.equals("X") && TH5.equals("X"))
				{
					if(dto.getTH4().equals("0") && dto.getTH5().equals("0"))
					{
						TH4 = name;
						TH5 = name;
						
						hm.put(name, "TH4 2times straight");
						continue;
					}
				}
				
				if(F1.equals("X") && F2.equals("X"))
				{
					if(dto.getF1().equals("0") && dto.getF2().equals("0"))
					{
						F1 = name;
						F2 = name;
						
						hm.put(name, "F1 2times straight");
						continue;
					}
				}
				else if(F2.equals("X") && F3.equals("X"))
				{
					if(dto.getF2().equals("0") && dto.getF3().equals("0"))
					{
						F2 = name;
						F3 = name;
						
						hm.put(name, "F2 2times straight");
						continue;
					}
				}
				else if(F3.equals("X") && F4.equals("X"))
				{
					if(dto.getF3().equals("0") && dto.getF4().equals("0"))
					{
						F3 = name;
						F4 = name;
						
						hm.put(name, "F3 2times straight");
						continue;
					}
				}
				else if(F4.equals("X") && F5.equals("X"))
				{
					if(dto.getF4().equals("0") && dto.getF5().equals("0"))
					{
						F4 = name;
						F5 = name;
						
						hm.put(name, "F4 2times straight");
						continue;
					}
				}
				
			}
		}
		
		//한 타임 되는 사람 찾기 
		for(int i = 0; i < listLength; i++)
		{
			classTimeDto dto = list.get(i);
			String name = list.get(i).getName();
			
			if(hm.containsKey(name) == false) //아직 배정 안 받은 사람 대상 
			{	
				if(M1.equals("X") && dto.getM1().equals("0")) //1시간짜리니까 다른 요일에도 올 수 있게 해야함(최대 주 2회). 같은 요일에 여러 번 오게는 안 함. 
				{
					M1 = name;
					hm.put(name, "1time");
					
				}
				else if(M2.equals("X") && dto.getM2().equals("0"))
				{
					M2 = name;
					hm.put(name, "1time");
					
				}
				else if(M3.equals("X") && dto.getM3().equals("0"))
				{
					M3 = name;
					hm.put(name, "1time");
					
				}
				else if(M4.equals("X") && dto.getM4().equals("0"))
				{
					M4 = name;
					hm.put(name, "1time");
					
				}
				else if(M5.equals("X") && dto.getM5().equals("0"))
				{
					M5 = name;
					hm.put(name, "1time");
					
				}
				
				if(T1.equals("X") && dto.getT1().equals("0"))
				{
					T1 = name;
					
					if(hm.get(name) == "1time")
						hm.replace(name, "2time");
					else
						hm.put(name, "1time");
				}
				else if(T2.equals("X") && dto.getT2().equals("0"))
				{
					T2 = name;
					
					if(hm.get(name) == "1time")
						hm.replace(name, "2time");
					else
						hm.put(name, "1time");
				}
				else if(T3.equals("X") && dto.getT3().equals("0"))
				{
					T3 = name;
					
					if(hm.get(name) == "1time")
						hm.replace(name, "2time");
					else
						hm.put(name, "1time");
				}
				else if(T4.equals("X") && dto.getT4().equals("0"))
				{
					T4 = name;
					
					if(hm.get(name) == "1time")
						hm.replace(name, "2time");
					else
						hm.put(name, "1time");
				}
				else if(T5.equals("X") && dto.getT5().equals("0"))
				{
					T5 = name;
					
					if(hm.get(name) == "1time")
						hm.replace(name, "2time");
					else
						hm.put(name, "1time");
				}
				
				if(W1.equals("X") && dto.getW1().equals("0"))
				{
					if(hm.get(name) == "2time")
						continue;
					
					if(hm.get(name) == "1time")
					{
						W1 = name;
						hm.replace(name, "2time");
					}
					else
					{
						W1 = name;
						hm.put(name, "1time");
					}
				}
				else if(W2.equals("X") && dto.getW2().equals("0"))
				{
					if(hm.get(name) == "2time")
						continue;
					
					if(hm.get(name) == "1time")
					{
						W2 = name;
						hm.replace(name, "2time");
					}
					else
					{
						W2 = name;
						hm.put(name, "1time");
					}
				}
				else if(W3.equals("X") && dto.getW3().equals("0"))
				{
					if(hm.get(name) == "2time")
						continue;
					
					if(hm.get(name) == "1time")
					{
						W3 = name;
						hm.replace(name, "2time");
					}
					else
					{
						W3 = name;
						hm.put(name, "1time");
					}
				}
				else if(W4.equals("X") && dto.getW4().equals("0"))
				{
					if(hm.get(name) == "2time")
						continue;
					
					if(hm.get(name) == "1time")
					{
						W4 = name;
						hm.replace(name, "2time");
					}
					else
					{
						W4 = name;
						hm.put(name, "1time");
					}
				}
				else if(W5.equals("X") && dto.getW5().equals("0"))
				{
					if(hm.get(name) == "2time")
						continue;
					
					if(hm.get(name) == "1time")
					{
						W5 = name;
						hm.replace(name, "2time");
					}
					else
					{
						W5 = name;
						hm.put(name, "1time");
					}
				}
				
				if(TH1.equals("X") && dto.getTH1().equals("0"))
				{
					if(hm.get(name) == "2time")
						continue;
					
					if(hm.get(name) == "1time")
					{
						TH1 = name;
						hm.replace(name, "2time");
					}
					else
					{
						TH1 = name;
						hm.put(name, "1time");
					}
				}
				else if(TH2.equals("X") && dto.getTH2().equals("0"))
				{
					if(hm.get(name) == "2time")
						continue;
					
					if(hm.get(name) == "1time")
					{
						TH2 = name;
						hm.replace(name, "2time");
					}
					else
					{
						TH2 = name;
						hm.put(name, "1time");
					}
				}
				else if(TH3.equals("X") && dto.getTH3().equals("0"))
				{
					if(hm.get(name) == "2time")
						continue;
					
					if(hm.get(name) == "1time")
					{
						TH3 = name;
						hm.replace(name, "2time");
					}
					else
					{
						TH3 = name;
						hm.put(name, "1time");
					}
				}
				else if(TH4.equals("X") && dto.getTH4().equals("0"))
				{
					if(hm.get(name) == "2time")
						continue;
					
					if(hm.get(name) == "1time")
					{
						TH4 = name;
						hm.replace(name, "2time");
					}
					else
					{
						TH4 = name;
						hm.put(name, "1time");
					}
				}
				else if(TH5.equals("X") && dto.getTH5().equals("0"))
				{
					if(hm.get(name) == "2time")
						continue;
					
					if(hm.get(name) == "1time")
					{
						TH5 = name;
						hm.replace(name, "2time");
					}
					else
					{
						TH5 = name;
						hm.put(name, "1time");
					}
				}
				
				if(F1.equals("X") && dto.getF1().equals("0"))
				{
					if(hm.get(name) == "2time")
						continue;
					
					if(hm.get(name) == "1time")
					{
						F1 = name;
						hm.replace(name, "2time");
					}
					else
					{
						F1 = name;
						hm.put(name, "1time");
					}
				}
				else if(F2.equals("X") && dto.getF2().equals("0"))
				{
					if(hm.get(name) == "2time")
						continue;
					
					if(hm.get(name) == "1time")
					{
						F2 = name;
						hm.replace(name, "2time");
					}
					else
					{
						F2 = name;
						hm.put(name, "1time");
					}
				}
				else if(F3.equals("X") && dto.getF3().equals("0"))
				{
					if(hm.get(name) == "2time")
						continue;
					
					if(hm.get(name) == "1time")
					{
						F3 = name;
						hm.replace(name, "2time");
					}
					else
					{
						F3 = name;
						hm.put(name, "1time");
					}
				}
				else if(F4.equals("X") && dto.getF4().equals("0"))
				{
					if(hm.get(name) == "2time")
						continue;
					
					if(hm.get(name) == "1time")
					{
						F4 = name;
						hm.replace(name, "2time");
					}
					else
					{
						F4 = name;
						hm.put(name, "1time");
					}
				}
				else if(F5.equals("X") && dto.getF5().equals("0"))
				{
					if(hm.get(name) == "2time")
						continue;
					
					if(hm.get(name) == "1time")
					{
						F5 = name;
						hm.replace(name, "2time");
					}
					else
					{
						F5 = name;
						hm.put(name, "1time");
					}
				}
			}
			
			//test
//			System.out.printf("after loop: "
//					+ "M1: %s, M2: %s, M3: %s, M4: %s, M5: %s, T1: %s, T2: %s, T3: %s, T4: %s, T5: %s, "
//					+ "W1: %s, W2: %s, W3: %s, W4: %s, W5: %s, TH1: %s, TH2: %s, TH3: %s, TH4: %s, TH5: %s, "
//					+ "F1: %s, F2: %s, F3: %s, F4: %s, F5: %s \n"
//					,M1, M2, M3, M4, M5, T1, T2, T3, T4, T5, W1, W2, W3, W4, W5, TH1, TH2, TH3, TH4, TH5, F1, F2, F3, F4, F5);
		}
		
		//연속으로 두 번 오는 사람 중에, 아직 아무도 배정 안 된 타임에 추가해서 넣기 
		for(int i = 0; i < listLength; i++)
		{
			classTimeDto dto = list.get(i);
			String name = list.get(i).getName();
			String nameForTwoTimes = hm.get(name);
			
			if(nameForTwoTimes == "M1 2times straight" || nameForTwoTimes == "M2 2times straight" || nameForTwoTimes == "M3 2times straight" || nameForTwoTimes == "M4 2times straight" || nameForTwoTimes == "M5 2times straight"
			|| nameForTwoTimes == "T1 2times straight" || nameForTwoTimes == "T2 2times straight" || nameForTwoTimes == "T3 2times straight" || nameForTwoTimes == "T4 2times straight" || nameForTwoTimes == "T5 2times straight"
			|| nameForTwoTimes == "W1 2times straight" || nameForTwoTimes == "W2 2times straight" || nameForTwoTimes == "W3 2times straight" || nameForTwoTimes == "W4 2times straight" || nameForTwoTimes == "W5 2times straight"
			|| nameForTwoTimes == "TH1 2times straight" || nameForTwoTimes == "TH2 2times straight" || nameForTwoTimes == "TH3 2times straight" || nameForTwoTimes == "TH4 2times straight" || nameForTwoTimes == "TH5 2times straight"
			|| nameForTwoTimes == "F1 2times straight" || nameForTwoTimes == "F2 2times straight" || nameForTwoTimes == "F3 2times straight" || nameForTwoTimes == "F4 2times straight" || nameForTwoTimes == "F5 2times straight") 
			{	
				if(M1.equals("X") && dto.getM1().equals("0"))
				{
					M1 = name;
					hm.replace(name, "3time");
				}
				else if(M2.equals("X") && dto.getM2().equals("0"))
				{
					M2 = name;
					hm.replace(name, "3time");
				}
				else if(M3.equals("X") && dto.getM3().equals("0"))
				{
					M3 = name;
					hm.replace(name, "3time");
				}
				else if(M4.equals("X") && dto.getM4().equals("0"))
				{
					M4 = name;
					hm.replace(name, "3time");
				}
				else if(M5.equals("X") && dto.getM5().equals("0"))
				{
					M5 = name;
					hm.replace(name, "3time");
				}
				else if(T1.equals("X") && dto.getT1().equals("0"))
				{
					T1 = name;
					hm.replace(name, "3time");
				}
				else if(T2.equals("X") && dto.getT2().equals("0"))
				{
					T2 = name;
					hm.replace(name, "3time");
				}
				else if(T3.equals("X") && dto.getT3().equals("0"))
				{
					T3 = name;
					hm.replace(name, "3time");
				}
				else if(T4.equals("X") && dto.getT4().equals("0"))
				{
					T4 = name;
					hm.replace(name, "3time");
				}
				else if(T5.equals("X") && dto.getT5().equals("0"))
				{
					T5 = name;
					hm.replace(name, "3time");
				}
				else if(W1.equals("X") && dto.getW1().equals("0"))
				{
					W1 = name;
					hm.replace(name, "3time");
				}
				else if(W2.equals("X") && dto.getW2().equals("0"))
				{
					W2 = name;
					hm.replace(name, "3time");
				}
				else if(W3.equals("X") && dto.getW3().equals("0"))
				{
					W3 = name;
					hm.replace(name, "3time");
				}
				else if(W4.equals("X") && dto.getW4().equals("0"))
				{
					W4 = name;
					hm.replace(name, "3time");
				}
				else if(W5.equals("X") && dto.getW5().equals("0"))
				{
					W5 = name;
					hm.replace(name, "3time");
				}
				else if(TH1.equals("X") && dto.getTH1().equals("0"))
				{
					TH1 = name;
					hm.replace(name, "3time");
				}
				else if(TH2.equals("X") && dto.getTH2().equals("0"))
				{
					TH2 = name;
					hm.replace(name, "3time");
				}
				else if(TH3.equals("X") && dto.getTH3().equals("0"))
				{
					TH3 = name;
					hm.replace(name, "3time");
				}
				else if(TH4.equals("X") && dto.getTH4().equals("0"))
				{
					TH4 = name;
					hm.replace(name, "3time");
				}
				else if(TH5.equals("X") && dto.getTH5().equals("0"))
				{
					TH5 = name;
					hm.replace(name, "3time");
				}
				else if(F1.equals("X") && dto.getF1().equals("0"))
				{
					F1 = name;
					hm.replace(name, "3time");
				}
				else if(F2.equals("X") && dto.getF2().equals("0"))
				{
					F2 = name;
					hm.replace(name, "3time");
				}
				else if(F3.equals("X") && dto.getF3().equals("0"))
				{
					F3 = name;
					hm.replace(name, "3time");
				}
				else if(F4.equals("X") && dto.getF4().equals("0"))
				{
					F4 = name;
					hm.replace(name, "3time");
				}
				else if(F5.equals("X") && dto.getF5().equals("0"))
				{
					F5 = name;
					hm.replace(name, "3time");
				}
			}
		}
		
		//한 번도 배정을 받지 않은 사람 - 3타임 연속으로 배정된 시간을 골라서, 넣어주기 
		for(int i = 0; i < listLength; i++)
		{
			classTimeDto dto = list.get(i);
			String name = list.get(i).getName();
		
			if(hm.containsKey(name) == false) //아직 배정 안 받은 사람 대상 
			{	
				//Monday 
				if(hm.containsValue("M1 3times")) //3time 연속 배정 받은 사람이 존재 
				{
					if(dto.getM1().equals("0"))
					{
						M1 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "M1 3times"), "M2 2times straight");
					}
					else if(dto.getM3().equals("0"))
					{
						M3 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "M1 3times"), "M1 2times straight");
					}
				}
				else if(hm.containsValue("M2 3times")) 
				{
					if(dto.getM2().equals("0"))
					{
						M2 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "M2 3times"), "M3 2times straight");
					}
					else if(dto.getM4().equals("0"))
					{
						M4 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "M2 3times"), "M2 2times straight");
					}
				}
				else if(hm.containsValue("M3 3times")) 
				{
					if(dto.getM3().equals("0"))
					{
						M3 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "M3 3times"), "M4 2times straight");
					}
					else if(dto.getM5().equals("0"))
					{
						M5 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "M3 3times"), "M3 2times straight");
					}
				}
				
				//Tuesday
				if(hm.containsValue("T1 3times"))
				{
					if(dto.getT1().equals("0"))
					{
					    if(hm.get(name) == "1time")
					    {
					    	T1 = name;
							hm.replace(name, "2time");
							hm.replace((String) getKey(hm, "T1 3times"), "T2 2times straight");
					    }
						else
					    {
							T1 = name;
							hm.put(name, "1time");
							hm.replace((String) getKey(hm, "T1 3times"), "T2 2times straight");
					    }
					}
					else if(dto.getT3().equals("0"))
					{
						if(hm.get(name) == "1time")
					    {
					    	T3 = name;
							hm.replace(name, "2time");
							hm.replace((String) getKey(hm, "T1 3times"), "T1 2times straight");
					    }
						else
					    {
							T3 = name;
							hm.put(name, "1time");
							hm.replace((String) getKey(hm, "T1 3times"), "T1 2times straight");
					    }
					}
				}
				else if(hm.containsValue("T2 3times")) //3time 연속 배정 받은 사람이 존재 
				{
					if(dto.getT2().equals("0"))
					{
					    if(hm.get(name) == "1time")
					    {
					    	T2 = name;
							hm.replace(name, "2time");
							hm.replace((String) getKey(hm, "T2 3times"), "T3 2times straight");
					    }
						else
					    {
							T2 = name;
							hm.put(name, "1time");
							hm.replace((String) getKey(hm, "T2 3times"), "T3 2times straight");
					    }
					}
					else if(dto.getT4().equals("0"))
					{
						if(hm.get(name) == "1time")
					    {
					    	T4 = name;
							hm.replace(name, "2time");
							hm.replace((String) getKey(hm, "T2 3times"), "T2 2times straight");
					    }
						else
					    {
							T4 = name;
							hm.put(name, "1time");
							hm.replace((String) getKey(hm, "T2 3times"), "T2 2times straight");
					    }
					}
				}
				else if(hm.containsValue("T3 3times")) //3time 연속 배정 받은 사람이 존재 
				{
					if(dto.getT3().equals("0"))
					{
					    if(hm.get(name) == "1time")
					    {
					    	T3 = name;
							hm.replace(name, "2time");
							hm.replace((String) getKey(hm, "T3 3times"), "T4 2times straight");
					    }
						else
					    {
							T3 = name;
							hm.put(name, "1time");
							hm.replace((String) getKey(hm, "T3 3times"), "T4 2times straight");
					    }
					}
					else if(dto.getT5().equals("0"))
					{
						if(hm.get(name) == "1time")
					    {
					    	T5 = name;
							hm.replace(name, "2time");
							hm.replace((String) getKey(hm, "T3 3times"), "T3 2times straight");
					    }
						else
					    {
							T5 = name;
							hm.put(name, "1time");
							hm.replace((String) getKey(hm, "T3 3times"), "T3 2times straight");
					    }
					}
				}
				
				//Wednesday
				if(hm.containsValue("W1 3times"))
				{
					if(dto.getW1().equals("0"))
					{
						if(hm.get(name) == "2time")
							continue;
						
						if(hm.get(name) == "1time")
					    {
					    	W1 = name;
							hm.replace(name, "2time");
							hm.replace((String) getKey(hm, "W1 3times"), "W2 2times straight");
					    }
						else
					    {
							W1 = name;
							hm.put(name, "1time");
							hm.replace((String) getKey(hm, "W1 3times"), "W2 2times straight");
					    }
					}
					else if(dto.getW3().equals("0"))
					{
						if(hm.get(name) == "2time")
							continue;
						
						if(hm.get(name) == "1time")
					    {
					    	W3 = name;
							hm.replace(name, "2time");
							hm.replace((String) getKey(hm, "W1 3times"), "W1 2times straight");
					    }
						else
					    {
							W3 = name;
							hm.put(name, "1time");
							hm.replace((String) getKey(hm, "W1 3times"), "W1 2times straight");
					    }
					}
				}
				else if(hm.containsValue("W2 3times")) //3time 연속 배정 받은 사람이 존재 
				{
					if(dto.getW2().equals("0"))
					{
						if(hm.get(name) == "2time")
							continue;
						
						if(hm.get(name) == "1time")
					    {
					    	W2 = name;
							hm.replace(name, "2time");
							hm.replace((String) getKey(hm, "W2 3times"), "W3 2times straight");
					    }
						else
					    {
							W2 = name;
							hm.put(name, "1time");
							hm.replace((String) getKey(hm, "W2 3times"), "W3 2times straight");
					    }
					}
					else if(dto.getW4().equals("0"))
					{
						if(hm.get(name) == "2time")
							continue;
						
						if(hm.get(name) == "1time")
					    {
					    	W4 = name;
							hm.replace(name, "2time");
							hm.replace((String) getKey(hm, "W2 3times"), "W2 2times straight");
					    }
						else
					    {
							W4 = name;
							hm.put(name, "1time");
							hm.replace((String) getKey(hm, "W2 3times"), "W2 2times straight");
					    }
					}
				}
				else if(hm.containsValue("W3 3times")) //3time 연속 배정 받은 사람이 존재 
				{
					if(dto.getW3().equals("0"))
					{
						if(hm.get(name) == "2time")
							continue;
						
						if(hm.get(name) == "1time")
					    {
					    	W3 = name;
							hm.replace(name, "2time");
							hm.replace((String) getKey(hm, "W3 3times"), "W4 2times straight");
					    }
						else
					    {
							W3 = name;
							hm.put(name, "1time");
							hm.replace((String) getKey(hm, "W3 3times"), "W4 2times straight");
					    }
					}
					else if(dto.getW5().equals("0"))
					{
						if(hm.get(name) == "2time")
							continue;
						
						if(hm.get(name) == "1time")
					    {
					    	W5 = name;
							hm.replace(name, "2time");
							hm.replace((String) getKey(hm, "W3 3times"), "W3 2times straight");
					    }
						else
					    {
							W5 = name;
							hm.put(name, "1time");
							hm.replace((String) getKey(hm, "W3 3times"), "W3 2times straight");
					    }
					}
				}
				
				//Thursday
				if(hm.containsValue("TH1 3times"))
				{
					if(dto.getTH1().equals("0"))
					{
						if(hm.get(name) == "2time")
							continue;
						
						if(hm.get(name) == "1time")
					    {
					    	TH1 = name;
							hm.replace(name, "2time");
							hm.replace((String) getKey(hm, "TH1 3times"), "TH2 2times straight");
					    }
						else
					    {
							TH1 = name;
							hm.put(name, "1time");
							hm.replace((String) getKey(hm, "TH1 3times"), "TH2 2times straight");
					    }
					}
					else if(dto.getTH3().equals("0"))
					{
						if(hm.get(name) == "2time")
							continue;
						
						if(hm.get(name) == "1time")
					    {
					    	TH3 = name;
							hm.replace(name, "2time");
							hm.replace((String) getKey(hm, "TH1 3times"), "TH1 2times straight");
					    }
						else
					    {
							TH3 = name;
							hm.put(name, "1time");
							hm.replace((String) getKey(hm, "TH1 3times"), "TH1 2times straight");
					    }
					}
				}
				else if(hm.containsValue("TH2 3times")) //3time 연속 배정 받은 사람이 존재 
				{
					if(dto.getTH2().equals("0"))
					{
						if(hm.get(name) == "2time")
							continue;
						
						if(hm.get(name) == "1time")
					    {
					    	TH2 = name;
							hm.replace(name, "2time");
							hm.replace((String) getKey(hm, "TH2 3times"), "TH3 2times straight");
					    }
						else
					    {
							TH2 = name;
							hm.put(name, "1time");
							hm.replace((String) getKey(hm, "TH2 3times"), "TH3 2times straight");
					    }
					}
					else if(dto.getTH4().equals("0"))
					{
						if(hm.get(name) == "2time")
							continue;
						
						if(hm.get(name) == "1time")
					    {
					    	TH4 = name;
							hm.replace(name, "2time");
							hm.replace((String) getKey(hm, "TH2 3times"), "TH2 2times straight");
					    }
						else
					    {
							TH4 = name;
							hm.put(name, "1time");
							hm.replace((String) getKey(hm, "TH2 3times"), "TH2 2times straight");
					    }
					}
				}
				else if(hm.containsValue("TH3 3times")) //3time 연속 배정 받은 사람이 존재 
				{
					if(dto.getTH3().equals("0"))
					{
						if(hm.get(name) == "2time")
							continue;
						
						if(hm.get(name) == "1time")
					    {
					    	TH3 = name;
							hm.replace(name, "2time");
							hm.replace((String) getKey(hm, "TH3 3times"), "TH4 2times straight");
					    }
						else
					    {
							TH3 = name;
							hm.put(name, "1time");
							hm.replace((String) getKey(hm, "TH3 3times"), "TH4 2times straight");
					    }
					}
					else if(dto.getTH5().equals("0"))
					{
						if(hm.get(name) == "2time")
							continue;
						
						if(hm.get(name) == "1time")
					    {
					    	TH5 = name;
							hm.replace(name, "2time");
							hm.replace((String) getKey(hm, "TH3 3times"), "TH3 2times straight");
					    }
						else
					    {
							TH5 = name;
							hm.put(name, "1time");
							hm.replace((String) getKey(hm, "TH3 3times"), "TH3 2times straight");
					    }
					}
				}
				
				//Friday
				if(hm.containsValue("F1 3times"))
				{
					if(dto.getF1().equals("0"))
					{
						if(hm.get(name) == "2time")
							continue;
						
						if(hm.get(name) == "1time")
					    {
					    	F1 = name;
							hm.replace(name, "2time");
							hm.replace((String) getKey(hm, "F1 3times"), "F2 2times straight");
					    }
						else
					    {
							F1 = name;
							hm.put(name, "1time");
							hm.replace((String) getKey(hm, "F1 3times"), "F2 2times straight");
					    }
					}
					else if(dto.getF3().equals("0"))
					{
						if(hm.get(name) == "2time")
							continue;
						
						if(hm.get(name) == "1time")
					    {
					    	F3 = name;
							hm.replace(name, "2time");
							hm.replace((String) getKey(hm, "F1 3times"), "F1 2times straight");
					    }
						else
					    {
							F3 = name;
							hm.put(name, "1time");
							hm.replace((String) getKey(hm, "F1 3times"), "F1 2times straight");
					    }
					}
				}
				else if(hm.containsValue("F2 3times")) //3time 연속 배정 받은 사람이 존재 
				{
					if(dto.getF2().equals("0"))
					{
						if(hm.get(name) == "2time")
							continue;
						
						if(hm.get(name) == "1time")
					    {
					    	F2 = name;
							hm.replace(name, "2time");
							hm.replace((String) getKey(hm, "F2 3times"), "F3 2times straight");
					    }
						else
					    {
							F2 = name;
							hm.put(name, "1time");
							hm.replace((String) getKey(hm, "F2 3times"), "F3 2times straight");
					    }
					}
					else if(dto.getF4().equals("0"))
					{
						if(hm.get(name) == "2time")
							continue;
						
						if(hm.get(name) == "1time")
					    {
					    	F4 = name;
							hm.replace(name, "2time");
							hm.replace((String) getKey(hm, "F2 3times"), "F2 2times straight");
					    }
						else
					    {
							F4 = name;
							hm.put(name, "1time");
							hm.replace((String) getKey(hm, "F2 3times"), "F2 2times straight");
					    }
					}
				}
				else if(hm.containsValue("F3 3times")) //3time 연속 배정 받은 사람이 존재 
				{
					if(dto.getF3().equals("0"))
					{
						if(hm.get(name) == "2time")
							continue;
						
						if(hm.get(name) == "1time")
					    {
					    	F3 = name;
							hm.replace(name, "2time");
							hm.replace((String) getKey(hm, "F3 3times"), "F4 2times straight");
					    }
						else
					    {
							F3 = name;
							hm.put(name, "1time");
							hm.replace((String) getKey(hm, "F3 3times"), "F4 2times straight");
					    }
					}
					else if(dto.getF5().equals("0"))
					{
						if(hm.get(name) == "2time")
							continue;
						
						if(hm.get(name) == "1time")
					    {
					    	F5 = name;
							hm.replace(name, "2time");
							hm.replace((String) getKey(hm, "F3 3times"), "F3 2times straight");
					    }
						else
					    {
							F5 = name;
							hm.put(name, "1time");
							hm.replace((String) getKey(hm, "F3 3times"), "F3 2times straight");
					    }
					}
				}
				
				
			}
		}
		
		//아직도 한 번도 배정 받지 못한 사람 - 연속으로 두 타임 배정된 시간을 골라서 넣어주기 
		for(int i = 0; i < listLength; i++)
		{
			classTimeDto dto = list.get(i);
			String name = list.get(i).getName();
		
			if(hm.containsKey(name) == false) //아직 배정 안 받은 사람 대상 
			{	
				if(hm.containsValue("M1 2times straight")) //2time 연속 배정 받은 사람 찾기 
				{
					if(dto.getM1().equals("0"))
					{
						M1 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "M1 2times straight"), "1time");
						continue;
					}
					else if(dto.getM2().equals("0"))
					{
						M2 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "M1 2times straight"), "1time");
						continue;
					}
				}
				
				if(hm.containsValue("M2 2times straight")) 
				{
					if(dto.getM2().equals("0"))
					{
						M2 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "M2 2times straight"), "1time");
						continue;
					}
					else if(dto.getM3().equals("0"))
					{
						M3 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "M2 2times straight"), "1time");
						continue;
					}
				}
				
				if(hm.containsValue("M3 2times straight")) 
				{
					if(dto.getM3().equals("0"))
					{
						M3 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "M3 2times straight"), "1time");
						continue;
					}
					else if(dto.getM4().equals("0"))
					{
						M4 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "M3 2times straight"), "1time");
						continue;
					}
				}
				
				if(hm.containsValue("M4 2times straight")) 
				{
					if(dto.getM4().equals("0"))
					{
						M4 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "M4 2times straight"), "1time");
						continue;
					}
					else if(dto.getM5().equals("0"))
					{
						M5 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "M4 2times straight"), "1time");
						continue;
					}
				}
				
				if(hm.containsValue("T1 2times straight")) 
				{
					if(dto.getT1().equals("0"))
					{
						T1 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "T1 2times straight"), "1time");
						continue;
					}
					else if(dto.getT2().equals("0"))
					{
						T2 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "T1 2times straight"), "1time");
						continue;
					}
				}
				
				if(hm.containsValue("T2 2times straight")) 
				{
					if(dto.getT2().equals("0"))
					{
						T2 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "T2 2times straight"), "1time");
						continue;
					}
					else if(dto.getT3().equals("0"))
					{
						T3 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "T2 2times straight"), "1time");
						continue;
					}
				}
				
				if(hm.containsValue("T3 2times straight")) 
				{
					if(dto.getT3().equals("0"))
					{
						T3 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "T3 2times straight"), "1time");
						continue;
					}
					else if(dto.getT4().equals("0"))
					{
						T4 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "T3 2times straight"), "1time");
						continue;
					}
				}
				
				if(hm.containsValue("T4 2times straight")) 
				{
					if(dto.getT4().equals("0"))
					{
						T4 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "T4 2times straight"), "1time");
						continue;
					}
					else if(dto.getT5().equals("0"))
					{
						T5 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "T4 2times straight"), "1time");
						continue;
					}
				}
				
				if(hm.containsValue("W1 2times straight")) 
				{
					if(dto.getW1().equals("0"))
					{
						W1 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "W1 2times straight"), "1time");
						continue;
					}
					else if(dto.getW2().equals("0"))
					{
						W2 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "W1 2times straight"), "1time");
						continue;
					}
				}
				
				if(hm.containsValue("W2 2times straight")) 
				{
					if(dto.getW2().equals("0"))
					{
						W2 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "W2 2times straight"), "1time");
						continue;
					}
					else if(dto.getW3().equals("0"))
					{
						W3 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "W2 2times straight"), "1time");
						continue;
					}
				}
				
				if(hm.containsValue("W3 2times straight")) 
				{
					if(dto.getW3().equals("0"))
					{
						W3 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "W3 2times straight"), "1time");
						continue;
					}
					else if(dto.getW4().equals("0"))
					{
						W4 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "W3 2times straight"), "1time");
						continue;
					}
				}
				
				if(hm.containsValue("W4 2times straight")) 
				{
					if(dto.getW4().equals("0"))
					{
						W4 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "W4 2times straight"), "1time");
						continue;
					}
					else if(dto.getW5().equals("0"))
					{
						W5 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "W4 2times straight"), "1time");
						continue;
					}
				}
				
				if(hm.containsValue("TH1 2times straight")) 
				{
					if(dto.getTH1().equals("0"))
					{
						TH1 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "TH1 2times straight"), "1time");
						continue;
					}
					else if(dto.getTH2().equals("0"))
					{
						TH2 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "TH1 2times straight"), "1time");
						continue;
					}
				}
				
				if(hm.containsValue("TH2 2times straight")) 
				{
					if(dto.getTH2().equals("0"))
					{
						TH2 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "TH2 2times straight"), "1time");
						continue;
					}
					else if(dto.getTH3().equals("0"))
					{
						TH3 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "TH2 2times straight"), "1time");
						continue;
					}
				}
				
				if(hm.containsValue("TH3 2times straight")) 
				{
					if(dto.getTH3().equals("0"))
					{
						TH3 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "TH3 2times straight"), "1time");
						continue;
					}
					else if(dto.getTH4().equals("0"))
					{
						TH4 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "TH3 2times straight"), "1time");
						continue;
					}
				}
				
				if(hm.containsValue("TH4 2times straight")) 
				{
					if(dto.getTH4().equals("0"))
					{
						TH4 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "TH4 2times straight"), "1time");
						continue;
					}
					else if(dto.getTH5().equals("0"))
					{
						TH5 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "TH4 2times straight"), "1time");
						continue;
					}
				}
				
				if(hm.containsValue("F1 2times straight")) 
				{
					if(dto.getF1().equals("0"))
					{
						F1 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "F1 2times straight"), "1time");
						continue;
					}
					else if(dto.getF2().equals("0"))
					{
						F2 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "F1 2times straight"), "1time");
						continue;
					}
				}
				
				if(hm.containsValue("F2 2times straight")) 
				{
					if(dto.getF2().equals("0"))
					{
						F2 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "F2 2times straight"), "1time");
						continue;
					}
					else if(dto.getF3().equals("0"))
					{
						F3 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "F2 2times straight"), "1time");
						continue;
					}
				}
				
				if(hm.containsValue("F3 2times straight")) 
				{
					if(dto.getF3().equals("0"))
					{
						F3 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "F3 2times straight"), "1time");
						continue;
					}
					else if(dto.getF4().equals("0"))
					{
						F4 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "F3 2times straight"), "1time");
						continue;
					}
				}
				
				if(hm.containsValue("F4 2times straight")) 
				{
					if(dto.getF4().equals("0"))
					{
						F4 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "F4 2times straight"), "1time");
						continue;
					}
					else if(dto.getF5().equals("0"))
					{
						F5 = name;
						hm.put(name, "1time");
						hm.replace((String) getKey(hm, "F4 2times straight"), "1time");
						continue;
					}
				}
				
			}
		}
		
		//test
//		System.out.println(hm);
		
		//데이터를 DTO에 저장 
	    int id = 0;
	    String operationTableName = request.getParameter("operationTable_name");
	    
        classTimeDto dto_operationTable = new classTimeDto(id, operationTableName, M1, M2, M3, M4, M5, T1, T2, T3, T4, T5, 
					W1, W2, W3, W4, W5, TH1, TH2, TH3, TH4, TH5, F1, F2, F3, F4, F5);
        
        //DB 테이블에 저장
        dao.addOperationTable(dto_operationTable);
				
		// 컨텍스트 페스 경로가져오기
        String context = request.getContextPath();
        
		// 페이지 이동(리다이렉트) 
        response.sendRedirect(context + "/MakingOperationTable");
	}
	
	//value 값으로부터 키 찾기 함수 
	public static Object getKey(HashMap<String, String> map, Object value) 
	{ 
		for(Object o: map.keySet()) 
		{ 
			if(map.get(o).equals(value)) 
			{ 
				return o; 
			} 
		} 
		return null; 
	}

}
