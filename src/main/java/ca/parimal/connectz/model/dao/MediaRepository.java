package ca.parimal.connectz.model.dao;

import ca.parimal.connectz.model.dao.entites.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
//    void save(Media media);
}
