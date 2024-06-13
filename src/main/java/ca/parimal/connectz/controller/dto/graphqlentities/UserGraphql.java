package ca.parimal.connectz.controller.dto.graphqlentities;

import org.json.simple.JSONObject;

public class UserGraphql {
    public static final String QUERY = "user {name,id}";
    private Integer userId;
    private String username;
    private String password;
    private Integer active;

    public UserGraphql(Integer userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public UserGraphql() {
        super();
    }
    public UserGraphql(JSONObject user){
        setUsername(user.get("name").toString());
        setUserId(Integer.parseInt(user.get("id").toString()));
    }

}
