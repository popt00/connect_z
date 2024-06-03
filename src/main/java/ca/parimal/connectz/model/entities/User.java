package ca.parimal.connectz.model.entities;

import jakarta.persistence.*;
import org.json.simple.JSONObject;

import java.util.Set;

@Entity
@Table(name="users")
public class User {
    public static final String QUERY = "user {name,id}";

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "data_id")
    private Long dataId;

    @Column(name="user_id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="password")
    private String password;

    @Column(name="active")
    private Integer active;

    @OneToMany(mappedBy = "user")
    Set<Entry> entries;

    public User(JSONObject user){
        this.name = user.get("name").toString();
        this.id = Integer.parseInt(user.get("id").toString());
    }

    public User(String name, int id){
        this.name = name;
        this.id = id;
        this.dataId = null;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
                ", id=" + id +
                '}';
    }
}
