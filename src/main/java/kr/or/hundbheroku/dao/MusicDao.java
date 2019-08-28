package kr.or.hundbheroku.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.hundbheroku.dto.MusicDto;

public class MusicDao {
//	private static String dburl = "jdbc:mysql://localhost:3306/musicdb?useUnicode=true&characterEncoding=utf8"
//	                            + "&verifyServerCertificate=false&useSSL=false&allowMultiQueries=true";
	
	private static String dburl = "jdbc:mysql://us-cdbr-iron-east-02.cleardb.net:3306/heroku_baea15322b4d46b?"
								+ "useUnicode=true&characterEncoding=utf8"
							    + "&reconnect=true&sslmode=require&verifyServerCertificate=false&useSSL=false&allowMultiQueries=true";
	
//	private static String dbUser = "hundb_user";
//	private static String dbpasswd = "hun2019";
	
	private static String dbUser = "b7df0b0d24409a";
	private static String dbpasswd = "25a26c2b";
	
	//id 정렬하기 
	public void sortingId() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		String multiQuerySql = "alter table music auto_increment=1;" + 
					 "set @count = 0;" + 
				     "update music set id = @count:=@count+1;";
		
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			PreparedStatement ps = conn.prepareStatement(multiQuerySql)) {
			
			boolean hasMoreResultSets = ps.execute(multiQuerySql);
			
			READING_QUERY_RESULTS:
			while (hasMoreResultSets || ps.getUpdateCount() != -1) {  
		        if (hasMoreResultSets) {  
		            ResultSet rs = ps.getResultSet();
		        }
		        else { // if has rs
		            int queryResult = ps.getUpdateCount();  
		            if ( queryResult == -1 ) { // no more queries processed  
		                break READING_QUERY_RESULTS;  
		            }
		            // handle success, failure, generated keys, etc here
		        }

		        hasMoreResultSets = ps.getMoreResults(); // check to continue in the loop  
		    }
			
		} catch (Exception ex) {
				ex.printStackTrace();
		}
	}
	
	//음반 리스트 파트 
	//SELECT barcode에 해당하는 곡 
	public List<MusicDto> getMusic(String barcodeNum) {
		List<MusicDto> list = new ArrayList<>();
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "SELECT title, artist, composer, category, track, label, barcode, regdate, importance, numOfDisc, id FROM music "
				   + "WHERE barcode= ?";
		
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			
			//?에 넣주기 
			ps.setString(1, barcodeNum);
			
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					String title = rs.getString("title");    //(쿼리문의) title 칼럼에서 string 꺼내옴 
					String artist = rs.getString("artist");
					String composer = rs.getString("composer");
					String category = rs.getString("category");
					String track = rs.getString("track");
					String label = rs.getString("label");
					String barcode = rs.getString("barcode");
					String regdate = rs.getString("regdate");
					int importance = rs.getInt("importance");
					String numOfDisc = rs.getString("numOfDisc");
					int id = rs.getInt("id");
					
					MusicDto music = new MusicDto(title, artist, composer, category, track, label, barcode, regdate, importance, numOfDisc, id);
					list.add(music);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	//SELECT 모든 곡 (try-with-resource 구문 : 알아서 close 해준다.)
	public List<MusicDto> getMusics() {
		List<MusicDto> list = new ArrayList<>();
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "SELECT title, artist, composer, category, track, label, barcode, regdate, importance, numOfDisc, id FROM music order by id DESC";
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
	
			try (ResultSet rs = ps.executeQuery()) {
	
				while (rs.next()) {
					String title = rs.getString("title");    //(쿼리문의) title 칼럼에서 string 꺼내옴 
					String artist = rs.getString("artist");
					String composer = rs.getString("composer");
					String category = rs.getString("category");
					String track = rs.getString("track");
					String label = rs.getString("label");
					String barcode = rs.getString("barcode");
					String regdate = rs.getString("regdate");
					int importance = rs.getInt("importance");
					String numOfDisc = rs.getString("numOfDisc");
					int id = rs.getInt("id");
					
					MusicDto music = new MusicDto(title, artist, composer, category, track, label, barcode, regdate, importance, numOfDisc, id);
					list.add(music); // list에 반복할때마다 music 인스턴스를 생성하여 list에 추가한다.
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	//SELECT start 부터 10개 곡
	public List<MusicDto> getTenMusics(int start) {
		List<MusicDto> list = new ArrayList<>();
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "SELECT title, artist, composer, category, track, label, barcode, regdate, importance, numOfDisc, id FROM music "
				   + "order by id DESC limit ?, 10";
		
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			
			//?에 넣주기 
			ps.setInt(1, start);
			
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					String title = rs.getString("title");    //(쿼리문의) title 칼럼에서 string 꺼내옴 
					String artist = rs.getString("artist");
					String composer = rs.getString("composer");
					String category = rs.getString("category");
					String track = rs.getString("track");
					String label = rs.getString("label");
					String barcode = rs.getString("barcode");
					String regdate = rs.getString("regdate");
					int importance = rs.getInt("importance");
					String numOfDisc = rs.getString("numOfDisc");
					int id = rs.getInt("id");
					
					MusicDto music = new MusicDto(title, artist, composer, category, track, label, barcode, regdate, importance, numOfDisc, id);
					list.add(music); // list에 반복할때마다 music 인스턴스를 생성하여 list에 추가한다.
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	//COUNT 특정 keyword를 포함하는 곡 전체 수 SELECT count(*) FROM product
	public int CountMusicsIncludingKeyword(String keyword) {
		int count = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "SELECT count(*) FROM music "
				   + "WHERE UPPER(title) LIKE ? OR UPPER(artist) LIKE ? OR UPPER(composer) LIKE ? OR UPPER(category) LIKE ? OR UPPER(track) LIKE ? OR UPPER(label) LIKE ?"; 
				   
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			
			//?에 넣주기 
			ps.setString(1, "%" + keyword.toUpperCase().trim() + "%");
			ps.setString(2, "%" + keyword.toUpperCase().trim() + "%");
			ps.setString(3, "%" + keyword.toUpperCase().trim() + "%");
			ps.setString(4, "%" + keyword.toUpperCase().trim() + "%");
			ps.setString(5, "%" + keyword.toUpperCase().trim() + "%");
			ps.setString(6, "%" + keyword.toUpperCase().trim() + "%");
			
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					
					count = rs.getInt(1);    //(쿼리문의) 첫번째 칼럼에서 값 꺼내옴 
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return count;
	}
	
	//SELECT 특정 keyword를 포함하는 곡 (검색)
	public List<MusicDto> TenMusicsIncludingKeyword(String keyword) {
		List<MusicDto> list = new ArrayList<>();
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "SELECT title, artist, composer, category, track, label, barcode, regdate, importance, numOfDisc, id FROM music "
				   + "WHERE UPPER(title) LIKE ? OR UPPER(artist) LIKE ? OR UPPER(composer) LIKE ? OR UPPER(category) LIKE ? OR UPPER(track) LIKE ? OR UPPER(label) LIKE ? " 
				   + "order by id DESC";
		
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			
			//?에 넣주기 
			ps.setString(1, "%" + keyword.toUpperCase().trim() + "%");
			ps.setString(2, "%" + keyword.toUpperCase().trim() + "%");
			ps.setString(3, "%" + keyword.toUpperCase().trim() + "%");
			ps.setString(4, "%" + keyword.toUpperCase().trim() + "%");
			ps.setString(5, "%" + keyword.toUpperCase().trim() + "%");
			ps.setString(6, "%" + keyword.toUpperCase().trim() + "%");
			
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					String title = rs.getString("title");    //(쿼리문의) title 칼럼에서 string 꺼내옴 
					String artist = rs.getString("artist");
					String composer = rs.getString("composer");
					String category = rs.getString("category");
					String track = rs.getString("track");
					String label = rs.getString("label");
					String barcode = rs.getString("barcode");
					String regdate = rs.getString("regdate");
					int importance = rs.getInt("importance");
					String numOfDisc = rs.getString("numOfDisc");
					int id = rs.getInt("id");
					
					MusicDto music = new MusicDto(title, artist, composer, category, track, label, barcode, regdate, importance, numOfDisc, id);
					list.add(music); // list에 반복할때마다 music 인스턴스를 생성하여 list에 추가한다.
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	//INSERT 곡 
	public int addMusic(MusicDto music) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1; //접속 실패  
		}
			
		String sql = "INSERT INTO music (title, artist, composer, category, track, label, barcode, importance, numOfDisc) "
				   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			 PreparedStatement ps = conn.prepareStatement(sql)) {
	
			ps.setString(1, music.getTitle());   //title을 첫번째 칼럼에 넣어주기 
			ps.setString(2, music.getArtist());
			ps.setString(3, music.getComposer());
			ps.setString(4, music.getCategory());
			ps.setString(5, music.getTrack());
			ps.setString(6, music.getLabel());
			ps.setString(7, music.getBarcode());
			ps.setInt(8, music.getImportance()); 
			ps.setString(9, music.getNumOfDisc());
			
			ps.executeUpdate();
			
			return 1; //저장 성공 
	
		} catch (Exception ex) {
			ex.printStackTrace();
			
			return 0; //저장 실패. 보통 데이터 중복 문제로 발생 
		}
	}
	
	//UPDATE 곡 
	public int updateMusic(MusicDto music) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1; //접속 실패  
		}
		
		String sql = "UPDATE music "
				   + "SET title = ?, artist = ?, composer = ?, category = ?, track = ?, label = ?, importance = ?, numOfDisc = ? "
				   + "WHERE barcode = ?";
				   
	
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			 PreparedStatement ps = conn.prepareStatement(sql)) {
	
			ps.setString(1, music.getTitle());   //title을 첫번째 칼럼에 넣어주기 
			ps.setString(2, music.getArtist());
			ps.setString(3, music.getComposer());
			ps.setString(4, music.getCategory());
			ps.setString(5, music.getTrack());
			ps.setString(6, music.getLabel());
			ps.setInt(7, music.getImportance()); 
			ps.setString(8, music.getNumOfDisc());
			ps.setString(9, music.getBarcode());
			
			ps.executeUpdate();
			
			return 1; //수정 성공 
	
		} catch (Exception ex) {
			ex.printStackTrace();
			
			return 0; //저장 실패. 보통 데이터 중복 문제로 발생 
		}
	}
	
	//DELETE 곡 
	public void deleteMusic(String barcode) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		String sql = "DELETE FROM music WHERE barcode = ?";
		
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ps.setString(1, barcode);  //barcode 를 첫번째 칼럼에 넣어주기  
					
			ps.executeUpdate();
	
		} catch (Exception ex) {
				ex.printStackTrace();
		}
	}
	
	//선곡표 만들기 파트 
	//뽑힌 id에 해당하는 곡 정보 가져오기 
	public List<MusicDto> choosedMusics(int arr[]) {
		List<MusicDto> list = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
//		String sql = "INSERT INTO choosedMusic (title, artist, composer, category, track, label, barcode, numOfDisc) "
//				   + "SELECT title, artist, composer, category, track, label, barcode, numOfDisc FROM music "
//				   + "WHERE id=? OR id=? OR id=? OR id=? OR id=? OR id=? OR id=? OR id=? OR id=? OR id=? OR id=? OR id=? OR id=? OR id=? OR id=?";
		
		String sql = "SELECT title, artist, composer, category, track, label, barcode, numOfDisc FROM music "
				   + "WHERE id=? OR id=? OR id=? OR id=? OR id=? OR id=? OR id=? OR id=? OR id=? OR id=? OR id=? OR id=? OR id=? OR id=? OR id=?";
		
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			
			//?에 넣주기 
			for(int i=0; i<15; i++)
			{
				ps.setInt(i+1, arr[i]);
			}
						
			try (ResultSet rs = ps.executeQuery()) {
	
				while (rs.next()) {
					String title = rs.getString("title");    //(쿼리문의) title 칼럼에서 string 꺼내옴 
					String artist = rs.getString("artist");
					String composer = rs.getString("composer");
					String category = rs.getString("category");
					String track = rs.getString("track");
					String label = rs.getString("label");
					String barcode = rs.getString("barcode");
					String numOfDisc = rs.getString("numOfDisc");
					String regdate = ""; //dto 형식을 맞추기 위한 것 뿐 
					int importance = 0;
					int id = 0;
					
					MusicDto music = new MusicDto(title, artist, composer, category, track, label, barcode, regdate, importance, numOfDisc, id);
					list.add(music); // list에 반복할때마다 music 인스턴스를 생성하여 list에 추가한다.
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

}
