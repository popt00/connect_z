package ca.parimal.connectz.model.dao;

import ca.parimal.connectz.model.dao.entites.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {
//    List<Role> findAllByUserId(Long userId);
}
