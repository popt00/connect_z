package ca.parimal.connectz.model.entities;

import jakarta.persistence.*;

//@Entity(name = "roles")
public class Roles {

    @Id
    @ManyToOne
    @JoinColumn(name = "data_id")
    User userDataId;

    @Column(name = "role")
    String role;

    public Roles(User userId, String role) {
        this.userDataId = userId;
        this.role = role;
    }

    public Roles() {

    }

    public User getUserId() {
        return userDataId;
    }

    public void setUserId(User userId) {
        this.userDataId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "userId=" + userDataId +
                ", role='" + role + '\'' +
                '}';
    }
}
