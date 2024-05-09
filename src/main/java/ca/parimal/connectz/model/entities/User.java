package ca.parimal.connectz.model.entities;

import org.json.simple.JSONObject;

public class User {
    public static final String QUERY = "user {name,id}";
    private String name;
    private Integer id;

    public User(JSONObject user){
        this.name = user.get("name").toString();
        this.id = Integer.parseInt(user.get("id").toString());
    }
    public User(String name, int id){
        this.name = name;
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
