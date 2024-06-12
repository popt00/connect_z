package ca.parimal.connectz.model.dao.entites;

import jakarta.persistence.*;

import java.util.List;

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
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="active")
    private Integer active=1;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Entry> entries;

//    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
//    List<Role> roles;
//    public List<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<Role> roles) {
//        this.roles = roles;
//    }

    public User(int userId, String username){
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
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
                "name='" + username + '\'' +
                ", id=" + userId +
                '}';
    }
}
