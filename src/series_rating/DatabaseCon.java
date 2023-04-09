/**
 * 
 */
package series_rating;

import java.sql.*;

/**
 * @author popt
 * 1) user_id, username, name, age, email
 * 2) animes, id, anme name, average rating, url,
 * 3) table_main : user_id, anime_id, rating, status
 * 4) keeper_user_id
 * 5) keeper_anime_id
 */
public class DatabaseCon {

	/**
	 * @param args
	 */
	static final String DB_URL = "jdbc:mysql://localhost:3306/sys";
	static final String USER = "root";
	static final String PASS = "popt1289";
	static final String QUERY = "SELECT * FROM employee where id = 92";

	public static void checkcon(String[] args) {
		// TODO Auto-generated method stub
		// System.out.println("hello world");
		runQuery(QUERY);
	}
	
	public static boolean runQuery(String str) {
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(str);) {
			// Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				System.out.print("ID: " + rs.getInt("id"));
				System.out.println(", Last: " + rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
