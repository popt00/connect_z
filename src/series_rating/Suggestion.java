package series_rating;

import series_rating.DatabaseCon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * @author connect_z team
 * This class represents a suggestion engine that uses resonance 
 * between users to recommend TV series to a given user.
 */
public class Suggestion {
	/**
	 * It has the following instance variables:
	 * ser_Ids: an ArrayList of integers that stores 
	 * 		the series IDs of the recommended TV series.
	 * prob: an ArrayList of floats that stores the probability 
	 * 		of the recommended TV series being liked by the user.
	 * user_id: an integer that stores the ID of the 
	 * 		user for whom the recommendations are being generated.
	 * max_aff_user: an integer that stores the ID 
	 * 		of the user with the highest resonance with the given user.
	 * max_user_resonance: a float that stores the resonance value 
	 * 		between the given user and the user with the highest resonance.
	 * */
	private ArrayList<Integer> ser_Ids;
	private ArrayList<Float> prob;
	private int user_id; 
	private int max_aff_user;
	private float max_user_resonace;
	
	/**
	 * initializes user_id to 1, and initializes ser_Ids and prob to empty ArrayLists.
	 * */
	public Suggestion() {
		user_id=1;
		ser_Ids=new ArrayList<>();
		prob=new ArrayList<>();
	}
	public Suggestion(int u_id) {
		user_id=u_id;
		ser_Ids=new ArrayList<>();
		prob=new ArrayList<>();
	}
	/**
	 * generates recommendations for the given user. 
	 * It fetches the TV series and ratings of the given user, and 
	 * calculates the resonance values between the given user and all other users.
	 * Then it sorts the other users based on their resonance values, 
	 * and selects the user with the highest resonance. 
	 * Finally, it fetches the TV series watched by the selected user, and 
	 * calculates the probability of the recommended TV series being liked by the given 
	 * user based on the resonance value. Returns true if recommendations were generated, false otherwise.
	 * */
	public boolean run() throws SQLException{
		DatabaseCon con= new DatabaseCon();
		int [][]u_series= con.getUserData(user_id, "series_id","rating");
		for(int i=0;i<u_series.length;i++)System.out.print(u_series[i][0]+",");
		System.out.println("fetched main series");
//		System.out.println(u_ser_id.toString());
//		System.out.println(u_rating.toString());
		int max_user = con.getmaxUserId()+1;
		int [][] resonance= new int [max_user][2];
		//res[user][0]= formula & res[user][0]= +10 everytime
		//Formula : if Diff of rating is {0,10},{1,9},{2,7},{3,5},{4,3},{>=5,0}
		int formula[] = {10,9,7,5,3,1,0,0,0,0,0};
		for(int i=0;i<u_series.length;i++) {
			int i_ser_id = u_series[i][0];
			int i_ser_rating = u_series[i][1];
			int [][] users = con.getSeriesData(i_ser_id, "user_id", "rating");
			System.out.print(i+"("+users.length+"),");//debugging
			for(int j=0;j<users.length;j++) {
				int diff = Math.abs(i_ser_rating - users[j][1]);
				resonance[users[j][0]][0]+= formula[diff];
				resonance[users[j][0]][1]+= 10;
			}
		}
		System.out.println("fetched other series");
		float prob[][]=new float[max_user][2];
		for(int i=0;i<max_user;i++) {
//			System.out.print("{"+resonance[i][0]+","+resonance[i][1]+"}");
			prob[i][0]=i;
			if(resonance[i][1]==0)prob[i][1]=0;
			else prob[i][1]= (1f*resonance[i][0])/resonance[i][1];
		}
		//some debugging		
		for(int i=0;i<max_user;i++)System.out.print(prob[i][1]+",");System.out.println("");
		prob[user_id][1]=0;
		
		Arrays.sort(prob, new Comparator<float[]>() {
			public int compare(float []a, float []b) {
				return Float.compare(b[1], a[1]);
			}
		});		
		//some debugging
		//for(int i=0;i<max_user;i++)System.out.print("{"+prob[i][0]+","+prob[i][1]+"}");System.out.println("");
		int index=0,size=0;
		while(size==0 && index<max_user) {
			this.max_aff_user = (int)prob[index][0];
			this.max_user_resonace = prob[index][1];
			int [][] other_user= con.getOtherUser(this.max_aff_user, this.user_id);
			System.out.print(this.max_aff_user+"("+other_user.length+"),");
			//System.out.println(other_user.length +",,,,,,");
			for(int i=0;i<other_user.length;i++) {
				this.ser_Ids.add(other_user[i][0]);
				this.prob.add(other_user[i][1]*this.max_user_resonace);
			}
			size= ser_Ids.size();
			index++;
		}
		if(index==max_user)return false;
		return true;
	}
	//Getter method for ser_Ids
	public ArrayList<Integer> getSerIds() {
        return ser_Ids;
    }
	
	// Getter methods for prob
    public ArrayList<Float> getProb() {
        return prob;
    }
    
    // Getter and setter methods for user_id
    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
    
    // 	Getter method for max_aff_user
    public int getMaxAffUser() {
        return max_aff_user;
    }

    // Getter method for max_user_resonace
    public float getMaxUserResonace() {
        return max_user_resonace;
    }
    
}
