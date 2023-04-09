/**
 * 
 */
package series_rating;
import series_rating.FetchUser;

import java.io.IOException;

import series_rating.DatabaseCon;
/**
 * @author popt
 *
 */
public class ProjectDemo {
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Starting project");
		FetchUser fetched_user = new FetchUser("popt");
		System.out.println("Over");
		fetched_user.print();
		
		boolean flag=false;
		while(flag) {
			System.out.println("1) Do you want to fetch dataset from anilist and add it to database?");
			System.out.println("2) Search your dataset by username");
			System.out.println("3) Seee suggested series by username");
			System.out.println("4) Exit");
			flag=false;
		}
	}
	
}
