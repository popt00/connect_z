package ca.parimal.connectz.model.dao.entites;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
public class User {
//    @EmbeddedId
//    UserKey id;

    @Id
    @Column(name="user_id")
//    @MapsId(name = "userId")
    private Integer userId;

    @Column(name="username")
    private String name;

    @Column(name="password")
    private String password;

    @Column(name="active")
    private Integer active=1;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Entry> entries;


    public User(int userId, String name){
        this.name = name;
        this.userId = userId;
    }

    public User() {
    }

//    public UserKey getId() {
//        return id;
//    }
//
//    public void setId(UserKey id) {
//        this.id = id;
//    }


    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer anilstUserId) {
        this.userId = anilstUserId;
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
                ", id=" + userId +
                '}';
    }
}
