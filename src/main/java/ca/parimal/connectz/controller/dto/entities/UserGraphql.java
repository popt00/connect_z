package ca.parimal.connectz.controller.dto.entities;

import ca.parimal.connectz.model.dao.entites.Entry;
import ca.parimal.connectz.model.dao.entites.User;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class UserGraphql {
    public static final String QUERY = "user {name,id}";
    private Integer anilistUserId;
    private String name;
    private String password;
    private Integer active;

    public UserGraphql(Integer anilistUserId, String name) {
        this.anilistUserId = anilistUserId;
        this.name = name;
    }

    public Integer getAnilistUserId() {
        return anilistUserId;
    }

    public void setAnilistUserId(Integer anilistUserId) {
        this.anilistUserId = anilistUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        setName(user.get("name").toString());
        setAnilistUserId(Integer.parseInt(user.get("id").toString()));
    }

}
