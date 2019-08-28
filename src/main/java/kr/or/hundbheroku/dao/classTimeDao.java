package kr.or.hundbheroku.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.hundbheroku.dto.MusicDto;
import kr.or.hundbheroku.dto.classTimeDto;

public class classTimeDao {
//	private static String dburl = "jdbc:mysql://localhost:3306/musicdb?useUnicode=true&characterEncoding=utf8"
//            + "&verifyServerCertificate=false&useSSL=false";
//	private static String dbUser = "hundb_user";
//	private static String dbpasswd = "hun2019";
	
	private static String dburl = "jdbc:mysql://us-cdbr-iron-east-02.cleardb.net:3306/heroku_baea15322b4d46b?"
			+ "useUnicode=true&characterEncoding=utf8"
		    + "&reconnect=true&sslmode=require&verifyServerCertificate=false&useSSL=false&allowMultiQueries=true";
	
	private static String dbUser = "b7df0b0d24409a";
	private static String dbpasswd = "25a26c2b";
	
	//SELECT * 학생 시간표들 -> try-with-resource 구문 : 알아서 close 해준다. 
	public List<classTimeDto> getClassTimes() {
		List<classTimeDto> list = new ArrayList<>();
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "SELECT id, name, M1, M2, M3, M4, M5, T1,  T2,  T3,  T4,  T5, " + 
									  "W1,  W2,  W3,  W4,  W5, TH1, TH2, TH3, TH4, TH5, " + 
								      "F1,  F2,  F3,  F4,  F5 FROM classTime order by id";
		
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
	
			try (ResultSet rs = ps.executeQuery()) {
	
				while (rs.next()) {	
					int id = rs.getInt(1);
					String name = rs.getString(2);
					
					String M1 = rs.getString(3);
					String M2 = rs.getString(4);
					String M3 = rs.getString(5);
					String M4 = rs.getString(6);
					String M5 = rs.getString(7);
					          
					String T1 = rs.getString(8);
					String T2 = rs.getString(9);
					String T3 = rs.getString(10);
					String T4 = rs.getString(11);
					String T5 = rs.getString(12);
					          
					String W1 = rs.getString(13);
					String W2 = rs.getString(14);
					String W3 = rs.getString(15);
					String W4 = rs.getString(16);
					String W5 = rs.getString(17);
					
					String TH1 = rs.getString(18);
					String TH2 = rs.getString(19);
					String TH3 = rs.getString(20);
					String TH4 = rs.getString(21);
					String TH5 = rs.getString(22);
					
					String F1 = rs.getString(23);
					String F2 = rs.getString(24);
					String F3 = rs.getString(25);
					String F4 = rs.getString(26);
					String F5 = rs.getString(27);
					
					classTimeDto classTime = new classTimeDto(id, name, M1, M2, M3, M4, M5, T1, T2, T3, T4, T5, 
							  											W1, W2, W3, W4, W5, TH1, TH2, TH3, TH4, TH5, 
							  											F1, F2, F3, F4, F5);
					
					list.add(classTime); // list에 반복할때마다 classTime 인스턴스를 생성하여 list에 추가한다.
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	//SELECT * 운영 시간표
	public List<classTimeDto> getOperationTables() {
		List<classTimeDto> list = new ArrayList<>();
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "SELECT id, name, M1, M2, M3, M4, M5, T1,  T2,  T3,  T4,  T5, " + 
									  "W1,  W2,  W3,  W4,  W5, TH1, TH2, TH3, TH4, TH5, " + 
								      "F1,  F2,  F3,  F4,  F5 FROM operationTable order by id";
		
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
	
			try (ResultSet rs = ps.executeQuery()) {
	
				while (rs.next()) {	
					int id = rs.getInt(1);
					String name = rs.getString(2);
					
					String M1 = rs.getString(3);
					String M2 = rs.getString(4);
					String M3 = rs.getString(5);
					String M4 = rs.getString(6);
					String M5 = rs.getString(7);
					          
					String T1 = rs.getString(8);
					String T2 = rs.getString(9);
					String T3 = rs.getString(10);
					String T4 = rs.getString(11);
					String T5 = rs.getString(12);
					          
					String W1 = rs.getString(13);
					String W2 = rs.getString(14);
					String W3 = rs.getString(15);
					String W4 = rs.getString(16);
					String W5 = rs.getString(17);
					
					String TH1 = rs.getString(18);
					String TH2 = rs.getString(19);
					String TH3 = rs.getString(20);
					String TH4 = rs.getString(21);
					String TH5 = rs.getString(22);
					
					String F1 = rs.getString(23);
					String F2 = rs.getString(24);
					String F3 = rs.getString(25);
					String F4 = rs.getString(26);
					String F5 = rs.getString(27);
					
					classTimeDto classTime = new classTimeDto(id, name, M1, M2, M3, M4, M5, T1, T2, T3, T4, T5, 
							  											W1, W2, W3, W4, W5, TH1, TH2, TH3, TH4, TH5, 
							  											F1, F2, F3, F4, F5);
					
					list.add(classTime); // list에 반복할때마다 classTime 인스턴스를 생성하여 list에 추가한다.
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	//INSERT 학생시간표 
	public void addClassTime(classTimeDto classTime) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
			
		String sql = "INSERT INTO classTime (name, M1, M2, M3, M4, M5, T1, T2, T3, T4, T5, " + 
												  "W1, W2, W3, W4, W5, TH1, TH2, TH3, TH4, TH5, " + 
				                                  "F1, F2, F3, F4, F5) "
				   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			 PreparedStatement ps = conn.prepareStatement(sql)) {
	
			ps.setString(1, classTime.getName());   //name을 첫번째 칼럼에 넣어주기 
			
			ps.setString(2, classTime.getM1());
			ps.setString(3, classTime.getM2());
			ps.setString(4, classTime.getM3());
			ps.setString(5, classTime.getM4());
			ps.setString(6, classTime.getM5());
			
			ps.setString(7, classTime.getT1());
			ps.setString(8, classTime.getT2());
			ps.setString(9, classTime.getT3());
			ps.setString(10, classTime.getT4());
			ps.setString(11, classTime.getT5());
			
			ps.setString(12, classTime.getW1());
			ps.setString(13, classTime.getW2());
			ps.setString(14, classTime.getW3());
			ps.setString(15, classTime.getW4());
			ps.setString(16, classTime.getW5());
			
			ps.setString(17, classTime.getTH1());
			ps.setString(18, classTime.getTH2());
			ps.setString(19, classTime.getTH3());
			ps.setString(20, classTime.getTH4());
			ps.setString(21, classTime.getTH5());
			
			ps.setString(22, classTime.getF1());
			ps.setString(23, classTime.getF2());
			ps.setString(24, classTime.getF3());
			ps.setString(25, classTime.getF4());
			ps.setString(26, classTime.getF5());
			
			ps.executeUpdate();
	
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//INSERT 운영시간표
	public void addOperationTable(classTimeDto operationTable) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
			
		String sql = "INSERT INTO operationTable (name, M1, M2, M3, M4, M5, T1, T2, T3, T4, T5, " + 
												  "W1, W2, W3, W4, W5, TH1, TH2, TH3, TH4, TH5, " + 
				                                  "F1, F2, F3, F4, F5) "
				   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			 PreparedStatement ps = conn.prepareStatement(sql)) {
	
			ps.setString(1, operationTable.getName());   //name을 첫번째 칼럼에 넣어주기 
			
			ps.setString(2, operationTable.getM1());
			ps.setString(3, operationTable.getM2());
			ps.setString(4, operationTable.getM3());
			ps.setString(5, operationTable.getM4());
			ps.setString(6, operationTable.getM5());

			ps.setString(7, operationTable.getT1());
			ps.setString(8, operationTable.getT2());
			ps.setString(9, operationTable.getT3());
			ps.setString(10, operationTable.getT4());
			ps.setString(11, operationTable.getT5());
			  
			ps.setString(12, operationTable.getW1());
			ps.setString(13, operationTable.getW2());
			ps.setString(14, operationTable.getW3());
			ps.setString(15, operationTable.getW4());
			ps.setString(16, operationTable.getW5());
			               
			ps.setString(17, operationTable.getTH1());
			ps.setString(18, operationTable.getTH2());
			ps.setString(19, operationTable.getTH3());
			ps.setString(20, operationTable.getTH4());
			ps.setString(21, operationTable.getTH5());
			              
			ps.setString(22, operationTable.getF1());
			ps.setString(23, operationTable.getF2());
			ps.setString(24, operationTable.getF3());
			ps.setString(25, operationTable.getF4());
			ps.setString(26, operationTable.getF5());
			
			ps.executeUpdate();
	
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//DELETE 학생 시간표 
	public void deleteClassTime(int id) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		String sql = "DELETE FROM classTime WHERE id = ?";
		
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ps.setInt(1, id);  
			
			ps.executeUpdate();
	
		} catch (Exception ex) {
				ex.printStackTrace();
		}
	}
	
	//DELETE 운영 시간표 
	public void deleteOperationTable(int id) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		String sql = "DELETE FROM operationTable WHERE id = ?";
		
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ps.setInt(1, id);  
			
			ps.executeUpdate();
	
		} catch (Exception ex) {
				ex.printStackTrace();
		}
	}
	
}





