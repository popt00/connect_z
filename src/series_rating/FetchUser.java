package series_rating;

/**
 * @author connect_z team
 * fetching anime name, website , rating by username, 
 */
import series_rating.DatabaseCon;
import java.util.*;
import java.net.*;
import java.sql.ResultSet;
import java.io.*;
public class FetchUser {
	// Fields declaration
	private String username;
	private ArrayList<String> animeName;
	private ArrayList<Integer> animeRating;
	private ArrayList<String> animeUrl;
	private ArrayList<String> animeStatus;
	private int len;
	// Default constructor
	FetchUser(){
		username = "";
		animeName = new ArrayList<>();
		animeRating = new ArrayList<>();
		animeUrl = new ArrayList<>();
		animeStatus = new ArrayList<>();
		len=0;
	}
	
	// Parameterized constructor that fetches data from a website
    public FetchUser(String p_username) throws IOException{
    	
    	this.username = p_username;
    	animeName = new ArrayList<>();
		animeRating = new ArrayList<>();
		animeUrl = new ArrayList<>();
		animeStatus = new ArrayList<>();
		len=0;
		// Fetching data from a website
    	URL url_fetching = new URL("https://anilist.co/user/"+p_username+"/animelist");
        BufferedReader in = new BufferedReader(
        new InputStreamReader(url_fetching.openStream()));

        String inputLine;
        ArrayList<Character> ar_chr= new ArrayList<>();
        PrintWriter pr = new PrintWriter("src/files/"+username+".html");
        while ((inputLine = in.readLine()) != null) {
        	pr.print(inputLine);
        	for(int i=0;i<inputLine.length();i++) {
        		char x=inputLine.charAt(i);
        		ar_chr.add(x);
        	}
//            System.out.println(inputLine);//debugging
//            len = inputLine.length();
//            total_new_line++;
        }
        // Closing the PrintWriter and BufferedReader
        pr.close();
        //System.out.println("done fetching website");
        // Parsing the website data
        fetch_data(ar_chr);
        //System.out.println("done fetching data");
//        System.out.println(" len:"+ar_chr.size()); // for debugging
        in.close();
        
    }
    // Method that parses the website data and stores relevant data in the object's fields
    void fetch_data(ArrayList<Character> ar) {
    	/**
    	 * Watching, Completed, Paused, Planning
    	 * */
    	int i=0,len = ar.size();
    	String split_string="section-name";
    	// Iterating over the characters in the ArrayList
    	while(i<len) {
    		if(check(ar,i,split_string)) {
//    			arPrint(ar,i,i+split_string.length()+2,0);
//    			System.out.println("in checked if statement");
    			i=i+split_string.length()+2;
    			String u_status = getStatus(ar,i);
//    			System.out.println("\t\t"+u_status);
    			int divCounter = 0;
    			while(divCounter>=0) {
//    				System.out.println("inside greater than 0 loop");
//    				System.out.println(divCounter);
    				if(check(ar,i,"<div")) {divCounter++;}
        			else if(check(ar,i,"</div>")) {divCounter--;}
        			else if(check(ar,i,"entry row")||check(ar,i,"entry-card") ) {
//        				arPrint(ar, i, i+10, 2);
    					String u_animeName="";
    					String u_animeWebsite="";
    					int u_score=6;
    					String u_website="";
    					div2loop : while(divCounter>=2) {
//    						System.out.println("inside 2 loop");
    						if(check(ar,i,"<div")) {divCounter++;}
                			else if(check(ar,i,"</div>")) {divCounter--;}
    						if(check(ar,i,"class=\"title\"")) {
    							i+= "class=\"title\"<a hre".length();
    							while(ar.get(i)!='"')i++;
    							i++;
    							String temp_web="https://anilist.co";
    							while(ar.get(i)!='"') {
    								temp_web= temp_web + Character.toString(ar.get(i));
    								i++;
    							}
    							i++;
    							while(ar.get(i)!='>')i++;
    							i++;
    							String temp_animeName = "";
    							while(ar.get(i)!='<') {
    								temp_animeName= temp_animeName + Character.toString(ar.get(i));
    								i++;
    							}
    							u_animeName=temp_animeName;
    							u_animeWebsite= temp_web;
    						}
    						else if(check(ar,i,"iv score=")) {
    							u_score = (int)ar.get(i+"iv score=1".length())-48;
    							if(u_score==0)u_score=6;
    							this.animeName.add(u_animeName.replace("'", ""));
        						this.animeRating.add(u_score);
        						this.animeUrl.add(u_animeWebsite);
        						this.animeStatus.add(u_status);
        						this.len++;
        						i++;
        						break div2loop;
    						}
    						i++;
    					}//while loop of >=2 end
    				}// end of else if statement
    				
    				i++;
    			}//end of while >=0
    		}// end of if section-name
    		i++;
    	}//end of while
    	
    }
    /**
     *  This method checks if the sub-list of characters in the ArrayList ar starting at index i
     *  matches the String str. If all characters match, it returns true, otherwise it returns false.
     * */
    boolean check(ArrayList<Character> ar,int i, String str) {
    	for(int j=0;j<str.length();j++) {
    		if(ar.get(i+j)!=str.charAt(j))return false;
    	}
    	return true;
    }
    /**
     * This method takes an ArrayList of characters and an index i as input, and returns a String
     * that corresponds to the character at index i. The returned String represents the status of
     * an anime. If the character is 'W', the anime is "Watching". If it is 'C', it is "Completed".
     * If it is 'R', it is "Rewatching". If it is 'D', it is "Dropped". If it is 'p', it may be
     * either "Plan to Watch" or "Paused" depending on the next character in the ArrayList.
     * If none of these characters are found, the method returns "None".
     * */
    String getStatus(ArrayList<Character> ar,int i) {
    	char chr= ar.get(i);
    	if(chr=='W')return "Watching";
    	if(chr=='C')return "Completed";
    	if(chr=='R')return "Rewatching";
    	if(chr=='D')return "Dropped";
    	if(chr=='p') {
    		if(ar.get(i+1)=='l')return "Plan to Watch";
    		return "Paused";
    	}
    	return "None";
    }
    //prints ar for debugging from i to j with tab times tab
    void arPrint(ArrayList<Character> ar,int i, int j, int tab) {
    	while(tab-->0) {
    		System.out.print("\t");
    	}
    	for(;i<=j;i++) {
    		System.out.print(ar.get(i));
    	}
    	System.out.println("");
    }
    //this method prints the anime list. If the anime list is empty, it prints "none fetched".
    void print() {
    	if(this.len==0) {
    		System.out.println("none fetched");
    		return;
    	}
    	String horiLine ="------------------------------------------------------------------------";
    	System.out.println(horiLine);
    	System.out.println("Username: "+this.username);
    	for(int i=0;i<this.len;i++) {
    		System.out.println(i+" "+this.animeName.get(i) +"\t\t"+ this.animeRating.get(i)+"\t\t"
    				+ this.animeUrl.get(i)+"\t\t"+ this.animeStatus.get(i));
    	}
    	System.out.println(horiLine);
    }
    //method returns the value of the username field.
    public String getUsername() {
        return username;
    }
    //method sets the value of the username field to the provided username parameter.
    public void setUsername(String username) {
        this.username = username;
    }
    //method returns the animeName ArrayList.
    public ArrayList<String> getAnimeName() {
        return animeName;
    }
    //method sets the animeName ArrayList to the provided animeName parameter.
    public void setAnimeName(ArrayList<String> animeName) {
        this.animeName = animeName;
    }
    //method returns the animeRating ArrayList.
    public ArrayList<Integer> getAnimeRating() {
        return animeRating;
    }
    //method sets the animeRating ArrayList to the provided animeRating parameter.
    public void setAnimeRating(ArrayList<Integer> animeRating) {
        this.animeRating = animeRating;
    }
    //method returns the animeUrl ArrayList.
    public ArrayList<String> getAnimeUrl() {
        return animeUrl;
    }
    //method sets the animeUrl ArrayList to the provided animeUrl parameter.
    public void setAnimeUrl(ArrayList<String> animeUrl) {
        this.animeUrl = animeUrl;
    }
    //method returns the animeStatus ArrayList.
    public ArrayList<String> getAnimeStatus() {
        return animeStatus;
    }
    //method sets the animeStatus ArrayList to the provided animeStatus parameter.
    public void setAnimeStatus(ArrayList<String> animeStatus) {
        this.animeStatus = animeStatus;
    }
    //method returns the value of the len field.
    public int getLen() {
        return len;
    }
    //method sets the value of the len field to the provided len parameter.
    public void setLen(int len) {
        this.len = len;
    }
}
