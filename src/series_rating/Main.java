/**
 * 
 */
package series_rating;

import java.sql.*;

/**
 * @author popt
 *
 */
public class Main {

	/**
	 * @param args
	 */
	static final String DB_URL = "jdbc:mysql://localhost:3306/sys";
	static final String USER = "root";
	static final String PASS = "popt1289";
	static final String QUERY = "SELECT * FROM employee where id = 92";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("hello world");
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(QUERY);) {
			// Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				System.out.print("ID: " + rs.getInt("id"));
				System.out.println(", Last: " + rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
