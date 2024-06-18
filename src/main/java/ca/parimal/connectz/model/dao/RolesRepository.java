package ca.parimal.connectz.model.dao;

import ca.parimal.connectz.model.dao.entites.Role;
import ca.parimal.connectz.model.dao.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface RolesRepository extends JpaRepository<Role, Integer> {
//    List<Role> findAllByUserId(Long userId);
//    List<Role> findAllByUserId(User userId);
//    boolean existsByUserAndRole(User userId, String role);
//    Role findByUserAndRole(User userId, String role);
}
