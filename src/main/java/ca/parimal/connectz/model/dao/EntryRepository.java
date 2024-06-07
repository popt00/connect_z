package ca.parimal.connectz.model.dao;

import ca.parimal.connectz.model.entities.dao.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface EntryRepository extends JpaRepository<Entry, Long> {
}
