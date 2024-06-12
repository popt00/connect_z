package ca.parimal.connectz.model.dao.entites;

import jakarta.persistence.*;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Default;

@Entity(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "role_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "role")
    private String role="ROLE_USER";


    public Role() {
    }

    public Role(User userId) {
        this.userId = userId;
    }

    public Role(User userId, String role) {
        this.userId = userId;
        this.role = role;
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
