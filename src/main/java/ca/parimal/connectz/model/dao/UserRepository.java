package ca.parimal.connectz.model.dao;

import ca.parimal.connectz.model.entities.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserRepository extends JpaRepository<User, Long> {
}
