package series_rating;
import java.sql.*;
import java.util.ArrayList;

import series_rating.FetchUser;

/**
 * @author connect_z team
 * 
 */
public class DatabaseCon {
	//private Connection conn;
	/**
	 * @param args
	 * @throws SQLException 
	 */
	public DatabaseCon() throws SQLException {
		//Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	}

	static final String DB_URL = "jdbc:mysql://localhost:3306/connect_z";
	static final String USER = "root";
	static final String PASS = "popt1289";
	//checks connectivity
	public void checkcon() {
		// TODO Auto-generated method stub
		// System.out.println("hello world");
		String QUERY = "SELECT * FROM z_id_keeper";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(QUERY);) {
			while (rs.next()) {
				System.out.print("user id: " + rs.getInt("max_user_id") + ",series id: " 
								+ rs.getInt("max_series_id") + "\n");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// retrives userid by username
	public int getUserId(String u_name) {
		String query = "SELECT user_id FROM z_user WHERE username = '" + u_name + "';";
		int user_id=-1;
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);) {
			while (rs.next()) {
				user_id = rs.getInt("user_id");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		return user_id;
	}

	public int getSeriesId(String u_series_name) {
		//u_series_name= u_series_name.replace("'", "");
		String query = "SELECT series_id FROM z_series WHERE series_name = '" + u_series_name + "'";
		int series_id=-1;
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);) {
			while (rs.next()) {
				series_id =  rs.getInt("series_id");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		return series_id;
	}
	public int getmaxUserId() {
		String query = "SELECT max_user_id FROM z_id_keeper";
		int max_user_id=0;
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);) {
			while (rs.next()) {
				max_user_id = rs.getInt("max_user_id");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return max_user_id;
	}
	public int getmaxSeriesId() {
		String query = "SELECT max_series_id FROM z_id_keeper";
		int max_series_id=0;
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);) {
			while (rs.next()) {
				 max_series_id =  rs.getInt("max_series_id");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return max_series_id;
	}
	public boolean setmaxUserId(int max_user_id) throws SQLException {
		String query = "UPDATE z_id_keeper SET max_user_id = " + Integer.toString(max_user_id);
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			conn.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public boolean setmaxSeriesId(int max_series_id) throws SQLException {
		String query = "UPDATE z_id_keeper SET max_series_id = " + Integer.toString(max_series_id);
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			conn.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	//returns userid
	public int addUser(String username, String email) {
		String sql = "INSERT INTO z_user (user_id, username, email, name, age) " +
                "VALUES (?, ?, ?, ?, ?)";
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			int user_id = getmaxUserId()+1;
			pstmt.setInt(1, user_id);
	        pstmt.setString(2, username);
	        pstmt.setString(3, email);
	        pstmt.setString(4, "parimal");
	        pstmt.setInt(5, 21);
	        pstmt.executeUpdate();
	        pstmt.close();
			conn.close();
			setmaxUserId(user_id);
			//System.out.println("log "+user_id);
			return getmaxUserId();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	public int addSeries(String seriesName, String seriesUrl) {
		String sql = "INSERT INTO z_series (series_id, series_name, url) " +
                "VALUES (?, ?, ?)";
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, getmaxSeriesId()+1);
			setmaxSeriesId(getmaxSeriesId()+1);
	        pstmt.setString(2, seriesName);
	        pstmt.setString(3, seriesUrl);
	        pstmt.executeUpdate();
	        pstmt.close();
			conn.close();
			return getmaxSeriesId();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	public int addfetchedUser(FetchUser u_user) throws SQLException {
		DatabaseCon con = new DatabaseCon();
		int user_id = con.getUserId(u_user.getUsername());
		if (user_id == -1) {
			user_id = addUser(u_user.getUsername(), "temp1234@email.com");
			System.out.println("\tuser does not exist, user "+ u_user.getUsername()+" with id " +user_id+" created" );
		}
		ArrayList<String> animeName = u_user.getAnimeName();
		ArrayList<Integer> animeRating = u_user.getAnimeRating();
		ArrayList<String> animeUrl = u_user.getAnimeUrl();
		ArrayList<String> animeStatus= u_user.getAnimeStatus();
		try {
			System.out.println("\tAdding "+u_user.getUsername()+" to database ");
			String sql = "INSERT INTO z_main (user_id, series_id, rating, series_status)"
					+ " VALUES (?, ?, ?, ?)";
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement pstmt = conn.prepareStatement(sql);
	        
	        // ArrayLists, and add a new row to the z_main table for each one
	        for (int i = 0; i < animeName.size(); i++) {
	        	pstmt.setInt(1, user_id);
	        	int seriesId = getSeriesId(animeName.get(i));
	        	if(seriesId==-1) {
	        		seriesId = addSeries(animeName.get(i), animeUrl.get(i));
	        	}
	            pstmt.setInt(2, seriesId);
	            pstmt.setInt(3, animeRating.get(i));
	            pstmt.setString(4, animeStatus.get(i));
	            pstmt.executeUpdate();
	        }
	        System.out.println("\t"+u_user.getUsername()+" added");
	        pstmt.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
		return user_id;
	}
	public String getSeriesName(int series_id) {
		String query_get_series_name= "SELECT series_name FROM z_series "
				+ "WHERE series_id = "+ Integer.toString(series_id);
		return getQueryRes(query_get_series_name, "series_name");
	}
	public String getQueryRes(String query, String ColumnName) {
		String result="";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);) {
			while (rs.next()) {
				result = rs.getString(ColumnName);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		return result;
	}
	public FetchUser getUser(int u_id) {
		FetchUser u_user = new FetchUser();
		u_user.setUsername(getQueryRes("SELECT username FROM z_user WHERE user_id = "+ Integer.toString(u_id), "username"));
		ArrayList<String> animeName = u_user.getAnimeName();
		ArrayList<Integer> animeRating = u_user.getAnimeRating();
		ArrayList<String> animeUrl = u_user.getAnimeUrl();
		ArrayList<String> animeStatus= u_user.getAnimeStatus();
		String query = "SELECT * FROM z_main WHERE user_id = "+Integer.toString(u_id);
		
		System.out.println("");
		
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);) {
			while (rs.next()) {
				int series_id = rs.getInt("series_id");
				String series_name= getQueryRes("SELECT series_name FROM z_series WHERE series_id = "
				+ Integer.toString(series_id), "series_name");
				String series_url= getQueryRes("SELECT url FROM z_series WHERE series_id = "
						+ Integer.toString(series_id), "url");
				//System.out.println(series_id+", "+series_name+", "+series_url);
				
				animeName.add(series_name);
				animeRating.add(rs.getInt("rating"));
				animeUrl.add(series_url);
				animeStatus.add(rs.getString("series_status"));
			}
			rs.close();
			stmt.close();
			conn.close();
			u_user.setLen(animeName.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return u_user;
		}
		return u_user;
	}
	
	public boolean editUser(int user_id, String u_name, String u_email, int u_age) {
		//TODO edit function
		String sql = "UPDATE z_user SET email = ? , name = ?, age = ? WHERE user_id = ?";
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
	        pstmt.setString(1, u_email);
	        pstmt.setString(2, u_name);
	        pstmt.setInt(3, u_age);
	        pstmt.setInt(4, user_id);
	        pstmt.executeUpdate();
	        pstmt.close();
			conn.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean dropAll() {
		String query = "TRUNCATE TABLE ?";
		Connection conn;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String[] tableNames = {"z_user", "z_series", "z_main", "z_id_keeper"};

	        for (String tableName : tableNames) {
	            String sql = "TRUNCATE TABLE " + tableName;
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	            pstmt.executeUpdate();
	            pstmt.close();
	        }
	        query = "INSERT INTO  z_id_keeper(max_user_id, max_series_id) VALUES (0,0);";
	        Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			conn.close();
	        return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public int[] getMainColumn(int user_id, String columnName) {
		//TODO edit function
		String query= "SELECT "+columnName+" FROM z_main WHERE user_id = "
					+Integer.toString(user_id)+" ";
		ArrayList<Integer> arr=new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				arr.add(rs.getInt(columnName));
			}
			rs.close();
			stmt.close();
			conn.close();
			int []ans = new int[arr.size()];
			for(int i=0;i<arr.size();i++) {
				ans[i]= arr.get(i);
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public int[][] getUserData(int user_id, String columnName, String columnName2) {
		//TODO edit function
		String query= "SELECT "+columnName+" , "+columnName2+" FROM z_main WHERE user_id = "
					+Integer.toString(user_id)+" ";
		ArrayList<Integer> arr=new ArrayList<>();
		ArrayList<Integer> arr2=new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				arr.add(rs.getInt(columnName));
				arr2.add(rs.getInt(columnName2));
			}
			rs.close();
			stmt.close();
			conn.close();
			int [][] ans = new int[arr.size()][2];
			for(int i=0;i<arr.size();i++) {
				ans[i][0]= arr.get(i);
				ans[i][1]= arr2.get(i);
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public int[][] getSeriesData(int series_id, String columnName, String columnName2) {
		//TODO edit function
		String query= "SELECT "+columnName+" , "+columnName2+" FROM z_main WHERE series_id = "
					+Integer.toString(series_id)+" ";
		ArrayList<Integer> arr=new ArrayList<>();
		ArrayList<Integer> arr2=new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				arr.add(rs.getInt(columnName));
				arr2.add(rs.getInt(columnName2));
			}
			rs.close();
			stmt.close();
			conn.close();
			int [][] ans = new int[arr.size()][2];
			for(int i=0;i<arr.size();i++) {
				ans[i][0]= arr.get(i);
				ans[i][1]= arr2.get(i);
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int[][] getOtherUser(int user_id_in, int user_id_not_in) {
		String query = "SELECT series_id,rating FROM z_main WHERE user_id = " + Integer.toString(user_id_in)
				+ " and series_id not in (SELECT series_id from z_main "
				+ "where user_id = " + Integer.toString(user_id_not_in)+" );";
		ArrayList<Integer> arr=new ArrayList<>();
		ArrayList<Integer> arr2=new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				arr.add(rs.getInt("series_id"));
				arr2.add(rs.getInt("rating"));
			}
			rs.close();
			stmt.close();
			conn.close();
			int [][] ans = new int[arr.size()][2];
			for(int i=0;i<arr.size();i++) {
				ans[i][0]= arr.get(i);
				ans[i][1]= arr2.get(i);
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
