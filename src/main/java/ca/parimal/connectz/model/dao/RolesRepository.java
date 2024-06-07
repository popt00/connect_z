package ca.parimal.connectz.model.dao;

import ca.parimal.connectz.model.dao.entites.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {
}
