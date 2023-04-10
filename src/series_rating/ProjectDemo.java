package series_rating;
import series_rating.FetchUser;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


import series_rating.DatabaseCon;
/**
 * @author connect_z team
 *
 */
public class ProjectDemo {
	/**
	 * Project Demo Class for running of connect_z
	 * The series_rating package contains classes that are used 
	 * for suggesting new series to the users based on their previous watching history.
	 * @param args
	 * @throws IOException when an input or output exception occurs 
	 * @throws SQLException when an error occurs while interacting with the database
	 */
	public static Scanner s ; //Declaring scanner outside
	public static void main(String[] args) throws IOException, SQLException {
		System.out.println("Starting project connect_z");
		s = new Scanner(System.in);
		DatabaseCon con = new DatabaseCon();
//		con.checkcon();
//		System.out.println("done");
		var flag=true;var  flag_input_4=true;
		int user_id=-1;
		//System.out.println(con.getOtherUser(2, 1).length);
//		flag = false;
		String user_input="1";
		String horiLine ="------------------------------------------------------------------------";
		outer : while(flag) {
			
			if(flag_input_4) {
				System.out.println("1) Do you want to fetch dataset from anilist and add it to database?");
				System.out.println("2) Search user series list");
				System.out.println("3) See suggested series by username");
				System.out.println("4) Edit user");
				System.out.println("5) Truncate all table data");
				System.out.print("   Other keys for Exit :");
				user_input= s.nextLine();
			}
			
			if(user_input.equals("1")) { //"1) Do you want to fetch dataset from anilist and add it to database?"
				System.out.print("\tProvide user name:");
				String username= s.nextLine();
				FetchUser fetched_user = new FetchUser(username);
				System.out.println("\tUser Data Fetched");
				fetched_user.print();
				//appeding data in sql
				user_id=  con.addfetchedUser(fetched_user);
				System.out.println(username+" added");
				System.out.println("\tDo you want to edit default user details?\n\t\tY/y for yes any other input for no");
				String yes = s.nextLine();
				if(yes.toUpperCase().equals("Y")) {
					user_input = "4";
					flag_input_4=false;
				}
				
			}
			else if(user_input.equals("2")) {//"2) Search user series list"
				user_id= CalltUserId();
				if(user_id==-1)continue;
				FetchUser u_user = con.getUser(user_id);
				u_user.print();
			}
			else if(user_input.equals("3")) {//"3) See suggested series by username"
				user_id= CalltUserId();
				if(user_id==-1)continue;
				//TODO suggestions & print
				Suggestion sugg = new Suggestion();
				if(sugg.run()==false) {
					System.out.println("No more suggestions");
					continue outer;
				}
				System.out.println("user_id: "+sugg.getMaxAffUser()+", matching probability: "+ sugg.getMaxUserResonace());
				ArrayList<Integer> ser_id= sugg.getSerIds();
				ArrayList<Float> prob = sugg.getProb();
				System.out.println("Suggested Series: (Total "+ser_id.size()+") ");
				for(int i=0;i<ser_id.size();i++) {
					System.out.println(ser_id.get(i)+"\t"+prob.get(i)+"\t\t"+con.getSeriesName(ser_id.get(i)));
				}
				System.out.println(horiLine);
			}
			else if(user_input.equals("4")) {//"4) Edit User
				if(flag_input_4) {
					user_id= CalltUserId();
					if(user_id==-1)continue;
				}
				else {
					flag_input_4=true;
				}
				//user edit
				System.out.print("\t\tEnter name of user:");
				String u_name = s.nextLine();
				System.out.print("\t\tEnter email of user:");
				String u_email = s.nextLine();
				System.out.print("\t\tEnter age of user:");
				String u_age_str = s.nextLine();
				int u_age=21;
				try {
					u_age= Integer.parseInt(u_age_str);
				}
				catch(NumberFormatException e) {
					System.out.println("\t\tprovided age is not integer, try again");
					continue outer;
				}
				con.editUser(user_id, u_name, u_email, u_age);
				System.out.println("\t\tUser Updated");
				
			}
			else if(user_input.equals("5")) {//"5) Truncate all table data"
				//calling drop all method
				con.dropAll();
			}
			else {
				flag = false;
			}
		}
		s.close();
	}
	public static int CalltUserId() throws SQLException {
//		Scanner s = new Scanner(System.in);
		DatabaseCon con = new DatabaseCon();
		System.out.print("\tDo you want to search by \n\t\t1) Username\n\t\t   Any other key for Userid :");
		String user_search = s.nextLine();
		int user_id=-1;
		if(user_search.equals("1")) {
			System.out.print("\tProvide user name for that user's suggestion:");
			String username= s.nextLine();
			user_id = con.getUserId(username);
			if(user_id==-1) {
				System.out.println("\t\tprovided username does not exist, Kindly check again");
				user_id= -1;
			}
		}
		else {
			System.out.print("\tProvide user id in string for that user's suggestion:");
			String userid_str= s.nextLine();
			try {
				user_id = Integer.parseInt(userid_str);
				
			}
			catch(NumberFormatException e) {
				System.out.println("\t\tprovided userid is not integer, try again");
				user_id=-1;
			}
			if(user_id>con.getmaxUserId() || user_id<=0) {
				user_id=-1;
			}
		}
//		s.close();
		
		return user_id;
	}
	
}
