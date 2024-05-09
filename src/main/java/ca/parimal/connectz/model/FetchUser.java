package ca.parimal.connectz.model;

/**
 * @author popt
 * fetching anime name, website , rating by username,
 */
import java.util.*;
import java.net.*;
import java.sql.ResultSet;
import java.io.*;
/**
 * fetch user from fetchuser
 * starting of class
 * @author popt
 *
 */
public class FetchUser {
    // Fields declaration
    private String username;
    private ArrayList<UserSeriesRating> seriesRatingData;
    private int len;
    // Default constructor
    FetchUser(){
        username = "";
        seriesRatingData = new ArrayList<>();
        len=0;
    }

    // Parameterized constructor that fetches data from a website
    /**
     * Fetches username from website
     * @param p_username
     * @throws IOException
     */
    public FetchUser(String p_username){

        this.username = p_username;
        seriesRatingData = new ArrayList<>();
        len=0;
        // Fetching data from a website

    }

    public void scrape() throws IOException {
        URL url_fetching = new URL("https://anilist.co/user/"+this.username+"/animelist");

        HttpURLConnection con = (HttpURLConnection) url_fetching.openConnection();
        con.setRequestMethod("GET");

        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));



//        BufferedReader in = new BufferedReader(new InputStreamReader(url_fetching.openStream()));
        String inputLine;
        ArrayList<Character> ar_chr= new ArrayList<>();
        while ((inputLine = in.readLine()) != null) {
            for(int i=0;i<inputLine.length();i++) {
                char x=inputLine.charAt(i);
                ar_chr.add(x);
            }
        }
        savetoFile(ar_chr);
        fetchData(ar_chr);
        in.close();
    }
    void savetoFile( ArrayList<Character> aList) throws IOException {
        File file = new File("./Storage.html");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 0; i < aList.size(); i++) {
            bw.write(aList.get(i).toString());
        }
        bw.flush();
        bw.close();
    }
    // Method that parses the website data and stores relevant data in the object's fields
    void fetchData(ArrayList<Character> ar) {
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
                                u_score = ((int)ar.get(i+"iv score=1".length())-48)*10;
                                if(ar.get(i+"iv score=11".length())!='"'){
                                    if(ar.get(i+"iv score=11".length())=='.'){
                                        u_score+= (int)ar.get(i+"iv score=111".length())-48;
                                    }
                                    else{
                                        u_score=u_score*10+(int)ar.get(i+"iv score=11".length())-48;
                                    }
                                }

                                if(u_score==0){u_score=6;
                                    System.out.println("this one");}

                                arPrint(ar,i,i+10,0);
                                UserSeriesRating tempSeriesRating = new UserSeriesRating();
                                tempSeriesRating.setName(u_animeName.replace("'", ""));
                                tempSeriesRating.setRating(u_score);
                                tempSeriesRating.setUrl(u_animeWebsite);
                                tempSeriesRating.setStatus(u_status);
//                                System.out.println(tempSeriesRating);
                                seriesRatingData.add(tempSeriesRating);
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
            System.out.println("\t"+i+" "+seriesRatingData.get(i).toString());
        }
        System.out.println(horiLine);
    }

    @Override
    public String toString() {
        return "FetchUser{" +
                "username='" + username + '\'' +
                ", seriesRatingData=" + seriesRatingData +
                ", len=" + len +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<UserSeriesRating> getSeriesRatingData() {
        return seriesRatingData;
    }

    public void setSeriesRatingData(ArrayList<UserSeriesRating> seriesRatingData) {
        this.seriesRatingData = seriesRatingData;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }
}