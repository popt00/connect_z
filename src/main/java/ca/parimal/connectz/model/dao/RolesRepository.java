package ca.parimal.connectz.model.dao;

import ca.parimal.connectz.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface RolesRepository extends JpaRepository<Role, Long> {
}
