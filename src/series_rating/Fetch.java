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
    	URL oracle = new URL("https://myanimelist.net/animelist/Rimusimp?status=2");
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    }
}
