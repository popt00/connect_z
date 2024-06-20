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
    private String authority;


    public Role() {
    }

    public Role(User userId) {
        this.user = userId;
    }

    public Role(User user, String authority) {
        this.user = user;
        this.authority = authority;
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

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String role) {
        this.authority = role;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "user=" + user +
                ", role='" + authority + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return Objects.equals(user, role1.user) && Objects.equals(authority, role1.authority);
    }
}
