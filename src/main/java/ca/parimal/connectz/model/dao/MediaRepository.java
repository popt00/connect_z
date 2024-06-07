package ca.parimal.connectz.model.dao;

import ca.parimal.connectz.model.entities.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface MediaRepository extends JpaRepository<Media, Long> {
//    void save(Media media);
}
