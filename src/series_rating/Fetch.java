/**
 * 
 */
package series_rating;

/**
 * @author popt
 *
 */
import java.util.*;
import java.net.*;
import java.io.*;
public class Fetch {
    public static void main(String[] args) throws IOException{
    	URL oracle = new URL("https://anilist.co/user/popt/animelist");
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));

        String inputLine;
        int total_new_line=0;
        while ((inputLine = in.readLine()) != null) {
        	for(int i=0;i<inputLine.length();i++) {
        		if(inputLine.charAt(i)=='\n')total_new_line++;
        	}
            System.out.println(inputLine);
        }
        System.out.println("new line:");
        System.out.println(total_new_line);
        in.close();
    }
}
