/**
 * 
 */
package series_rating;

/**
 * @author popt
 * anime name, website , rating, username, 
 */
import series_rating.DatabaseCon;
import java.util.*;
import java.net.*;
import java.sql.ResultSet;
import java.io.*;
public class FetchUser {
	private String username;
	private ArrayList<String> animeName;
	private ArrayList<Integer> animeRating;
	private ArrayList<String> animeUrl;
	private ArrayList<String> animeStatus;
	private int len;
	FetchUser(){
		username = "";
		animeName = new ArrayList<>();
		animeRating = new ArrayList<>();
		animeUrl = new ArrayList<>();
		animeStatus = new ArrayList<>();
		len=0;
	}
    public FetchUser(String p_username) throws IOException{
    	
    	this.username = p_username;
    	animeName = new ArrayList<>();
		animeRating = new ArrayList<>();
		animeUrl = new ArrayList<>();
		animeStatus = new ArrayList<>();
		len=0;
    	
    	URL url_fetching = new URL("https://anilist.co/user/"+p_username+"/animelist");
        BufferedReader in = new BufferedReader(
        new InputStreamReader(url_fetching.openStream()));

        String inputLine;
        ArrayList<Character> ar_chr= new ArrayList<>();
        PrintWriter pr = new PrintWriter(username+".html");
        while ((inputLine = in.readLine()) != null) {
        	pr.print(inputLine);
        	for(int i=0;i<inputLine.length();i++) {
        		char x=inputLine.charAt(i);
//        		if(x=='<') {
//        			
//        		}
        		ar_chr.add(x);
        	}
//            System.out.println(inputLine);
//            len = inputLine.length();
//            total_new_line++;
        }
        pr.close();
        System.out.println("done fetching website");
        fetch_data(ar_chr);
        System.out.println("done fetching data");
//        System.out.println(" len:"+ar_chr.size());
        in.close();
        
    }
    void fetch_data(ArrayList<Character> ar) {
    	/**
    	 * Watching, Completed, Paused, Planning
    	 * */
    	int i=0,len = ar.size();
    	String split_string="section-name";
    	
    	while(i<len) {
//    		System.out.print(","+i);
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
        			else if(check(ar,i,"entry row")) {
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
    							this.animeName.add(u_animeName);
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
    boolean check(ArrayList<Character> ar,int i, String str) {
    	for(int j=0;j<str.length();j++) {
    		if(ar.get(i+j)!=str.charAt(j))return false;
    	}
    	return true;
    }
    
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
    
    void arPrint(ArrayList<Character> ar,int i, int j, int tab) {
    	while(tab-->0) {
    		System.out.print("\t");
    	}
    	for(;i<=j;i++) {
    		System.out.print(ar.get(i));
    	}
    	System.out.println("");
    }
    void print() {
    	if(this.len==0) {
    		System.out.println("none fetched");
    	}
    	for(int i=0;i<this.len;i++) {
    		System.out.println(i+" "+this.animeName.get(i) +"\t\t"+ this.animeRating.get(i)+"\t\t"
    				+ this.animeUrl.get(i)+"\t\t"+ this.animeStatus.get(i));
    	}
    }
    public boolean addUser() {
    	DatabaseCon con = new DatabaseCon();
    	int user_id = con.getUserId(this.username);
    	if(user_id==-1) {
    		user_id= con.getmaxUserId()+1;
    		
    	}
    	
    	return true;
    }
}
