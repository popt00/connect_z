package ca.parimal.connectz.model.entities.dao;

import jakarta.persistence.*;

@Entity(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "role_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User userId;

    @Column(name = "role")
    String role;

    public Role(User userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    public Role() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
                "userId=" + userId +
                ", role='" + role + '\'' +
                '}';
    }
}
