package ca.parimal.connectz.model.dao.entites;

import jakarta.persistence.*;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return Objects.equals(userId, role1.userId) && Objects.equals(role, role1.role);
    }
}
