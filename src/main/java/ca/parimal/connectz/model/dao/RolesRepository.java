package ca.parimal.connectz.model.dao;

import ca.parimal.connectz.model.dao.entites.Role;
import ca.parimal.connectz.model.dao.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface RolesRepository extends JpaRepository<Role, Integer> {

    @Query("SELECT r.authority FROM Role r WHERE r.user = ?1")
    List<String> findAuthorities(User user);

    @Modifying
    @Query("DELETE FROM Role WHERE user = ?1")
    void deleteAuthorities(User user);

}
