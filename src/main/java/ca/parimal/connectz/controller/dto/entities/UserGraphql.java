package ca.parimal.connectz.controller.dto.entities;

import org.json.simple.JSONObject;

public class UserGraphql {
    public static final String QUERY = "user {name,id}";
    private Integer anilistUserId;
    private String username;
    private String password;
    private Integer active;

    public UserGraphql(Integer anilistUserId, String username) {
        this.anilistUserId = anilistUserId;
        this.username = username;
    }

    public Integer getAnilistUserId() {
        return anilistUserId;
    }

    public void setAnilistUserId(Integer anilistUserId) {
        this.anilistUserId = anilistUserId;
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
        setAnilistUserId(Integer.parseInt(user.get("id").toString()));
    }

}
