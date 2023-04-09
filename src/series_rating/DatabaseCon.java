/**
 * 
 */
package series_rating;

import java.sql.*;
import java.util.ArrayList;

import series_rating.FetchUser;

/**
 * @author popt 1) user_id, username, name, age, email 2) animes, id, anme name,
 *         url, 3) table_main : user_id, anime_id, rating, status 4)
 *         keeper_user_id 5) keeper_anime_id
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

	public static void checkcon() {
		// TODO Auto-generated method stub
		// System.out.println("hello world");
		String QUERY = "SELECT * FROM z_id_keeper";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(QUERY);) {
			while (rs.next()) {
				System.out.print(
						"user id: " + rs.getInt("max_user_id") + ",series id: " + rs.getInt("max_series_id") + "\n");
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
		int max_user_id=1;
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
			return 1;
		}
		return max_user_id;
	}
	public int getmaxSeriesId() {
		String query = "SELECT max_series_id FROM z_id_keeper";
		int max_series_id=1;
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
			return 1;
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
			
			pstmt.setInt(1, getmaxUserId()+1);
	        pstmt.setString(2, username);
	        pstmt.setString(3, email);
	        pstmt.setString(4, "parimal");
	        pstmt.setInt(5, 21);
	        pstmt.executeUpdate();
	        pstmt.close();
			conn.close();
			setmaxUserId(getmaxSeriesId()+1);
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
	        pstmt.setString(2, seriesName);
	        pstmt.setString(3, seriesUrl);
	        pstmt.executeUpdate();
	        pstmt.close();
			conn.close();
			setmaxSeriesId(getmaxSeriesId()+1);
			return getmaxSeriesId();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	public boolean addfetchedUser(FetchUser u_user) throws SQLException {
		DatabaseCon con = new DatabaseCon();
		int user_id = con.getUserId(u_user.getUsername());
		if (user_id == -1) {
			user_id = addUser(u_user.getUsername(), "temp1234@email.com");
		}
		ArrayList<String> animeName = u_user.getAnimeName();
		ArrayList<Integer> animeRating = u_user.getAnimeRating();
		ArrayList<String> animeUrl = u_user.getAnimeUrl();
		ArrayList<String> animeStatus= u_user.getAnimeStatus();
		try {
			
			String sql = "INSERT INTO z_main (user_id, series_id, rating, series_status) VALUES (?, ?, ?, ?)";
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
	        
	        pstmt.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	public FetchUser getUser(int u_id) {
		
	}
}
