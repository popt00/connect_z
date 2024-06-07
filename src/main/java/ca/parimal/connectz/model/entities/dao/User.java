package ca.parimal.connectz.model.entities.dao;

import jakarta.persistence.*;
import org.json.simple.JSONObject;

@Entity
@Table(name="users")
public class User {
    public static final String QUERY = "user {name,id}";
    public User(JSONObject user){
        this.name = user.get("name").toString();
        this.anilistUserId = Integer.parseInt(user.get("id").toString());
    }


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name="anilist_user_id")
    private Integer anilistUserId;

    @Column(name="username")
    private String name;

    @Column(name="password")
    private String password;

    @Column(name="active")
    private Integer active;

//    @OneToMany(mappedBy = "user")
//    Set<Entry> entries;


    public User(int anilistUserId, String name){
        this.name = name;
        this.anilistUserId = anilistUserId;
    }

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getAnilistUserId() {
        return anilistUserId;
    }

    public void setAnilistUserId(Integer anilstUserId) {
        this.anilistUserId = anilstUserId;
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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + anilistUserId +
                '}';
    }
}
