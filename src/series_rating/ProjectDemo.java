/**
 * 
 */
package series_rating;
import series_rating.FetchUser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthGraphicsUtils;

import series_rating.DatabaseCon;
/**
 * @author popt
 *
 */
public class ProjectDemo {
	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub
		System.out.println("Starting project connect_z");
		Scanner s = new Scanner(System.in);
		DatabaseCon con = new DatabaseCon();
		con.runQuery("SELECT max_user_id FROM z_id_keeper");
		System.out.println("done");
//		con.runQuery("UPDATE z_id_keeper SET max_user_id = "+ Integer.toString(2));
		boolean flag=false;
		while(flag) {
			System.out.println("1) Do you want to fetch dataset from anilist and add it to database?");
			System.out.println("2) Search user series list");
			System.out.println("3) See suggested series by username");
			System.out.println("other keys for Exit");
			String user_input = s.nextLine();
			if(user_input.equals("1")) {
				System.out.print("\t\tProvide user name:");
				String username= s.nextLine();
				FetchUser fetched_user = new FetchUser(username);
				System.out.println("\tUser Data Fetched");
				fetched_user.print();
//				System.out.println("\t\tProvide user name");
				fetched_user.addUser();
				//appeding data in sql
			}
			else if(user_input.equals("2")) {
				System.out.print("\t\tProvide user name:");
				String username= s.nextLine();
				int user_id = con.getUserId(username);
				if(user_id==-1) {
					System.out.println("\t\t\t provided username does not exist, Kindly check again");
					continue;
				}
				//TOdo
			}
			else if(user_input.equals("3")) {
				System.out.print("\t\tProvide user name for that user's suggestion:");
				String username= s.nextLine();
				int user_id = con.getUserId(username);
				if(user_id==-1) {
					System.out.println("\t\t\t provided username does not exist, Kindly check again");
					continue;
				}
				
//				Suggestion user_sugg = new Suggestion(id);
			}
			else {
				flag = false;
			}
		}
	}
	
}
