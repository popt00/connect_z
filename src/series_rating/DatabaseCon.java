/**
 * 
 */
package series_rating;

import java.sql.*;

/**
 * @author popt
 * 1) user_id, username, name, age, email
 * 2) animes, id, anme name, url,
 * 3) table_main : user_id, anime_id, rating, status
 * 4) keeper_user_id
 * 5) keeper_anime_id
 */
public class DatabaseCon {

	/**
	 * @param args
	 */
	public DatabaseCon() {
	}
	static final String DB_URL = "jdbc:mysql://localhost:3306/sys";
	static final String USER = "root";
	static final String PASS = "popt1289";
	static final String QUERY = "SELECT * FROM employee where id = 92";

	public static void checkcon(String[] args) {
		// TODO Auto-generated method stub
		// System.out.println("hello world");
		runQuery(QUERY);
	}
	
	public static ResultSet runQuery(String str) {
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(str);)
		{
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public boolean runStatement(String str) throws SQLException {
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		    Statement stmt = conn.createStatement();
		    stmt.executeUpdate(str);
		    return true;
	    }
		catch (SQLException e) {
			return false;
		}
	  }
	
	// retrives userid by username
	public int getUserId(String u_name) {
    	String query = "SELECT user_id FROM z_user WHERE username = '"+u_name+"';";
    	ResultSet rs = runQuery(query);
    	try {
			while(rs.next()) {
				return rs.getInt("user_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
    	return -1;
	}
	public int getSeriesId(String u_name) {
    	String query = "SELECT user_id FROM z_user WHERE username = '"+u_name+"'";
    	ResultSet rs = runQuery(query);
    	try {
			while(rs.next()) {
				return rs.getInt("user_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
    	return -1;
	}
	public int getmaxUserId() {
    	String query = "SELECT max_user_id FROM z_id_keeper";
    	ResultSet rs = runQuery(query);
    	try {
			while(rs.next()) {
				return rs.getInt("max_user_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
		}
    	return 1;
	}
	public boolean setmaxUserId(int max_user_id) throws SQLException {
    	String query = "UPDATE z_id_keeper SET max_user_id = "+ Integer.toString(max_user_id);
    	runStatement(query);
//    	System.out.println("run");
		return true;
	}
	public int getmaxSeriesId() {
    	String query = "SELECT max_series_id FROM z_id_keeper";
    	ResultSet rs = runQuery(query);
    	try {
			while(rs.next()) {
				return rs.getInt(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
		}
    	return 1;
	}
	
}
