package ca.parimal.connectz.model.dao.entites;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "roles",
        uniqueConstraints={@UniqueConstraint(columnNames ={"user_id","role"})})
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "role_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "role")
    private String role;


    public Role() {
    }

    public Role(User userId) {
        this.user = userId;
    }

    public Role(User user, String role) {
        this.user = user;
        this.role = role;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUserId() {
        return user;
    }

    public void setUserId(User userId) {
        this.user = userId;
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
                "user=" + user +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return Objects.equals(user, role1.user) && Objects.equals(role, role1.role);
    }
}
