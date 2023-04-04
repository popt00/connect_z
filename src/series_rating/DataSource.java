/**
 * 
 */
package series_rating;
import java.util.*;
/**
 * @author popt
 *
 */
import java.util.HashMap;
import java.util.Map;

import java.util.HashMap;
import java.util.Map;

class User {
    private String username;
    private String avatar;

    public User(String username, String avatar) {
        this.username = username;
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }
}

public class DataSource {
    public static User getUserById(int id) {
        // Make a request to the AniList API to fetch the user data
        // and parse the response to create a User object
        // For example, using a library like Retrofit or OkHttp
        String username = "example_username";
        String avatar = "https://example.com/avatar.png";
        return new User(username, avatar);
    }
}
