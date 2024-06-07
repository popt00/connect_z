package ca.parimal.connectz;

import ca.parimal.connectz.model.dao.EntryRepository;
import ca.parimal.connectz.model.dao.MediaRepository;
//import ca.parimal.connectz.model.dao.RolesRepository;
import ca.parimal.connectz.model.dao.RolesRepository;
import ca.parimal.connectz.model.dao.UserRepository;
import ca.parimal.connectz.model.entities.Entry;
import ca.parimal.connectz.model.entities.Media;
//import ca.parimal.connectz.model.entities.Role;
import ca.parimal.connectz.model.entities.Role;
import ca.parimal.connectz.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConnectzApplication {

	public static void main(String[] args) {
		//MediaListController.getMediaList("popt");
		SpringApplication.run(ConnectzApplication.class, args);
	}

	@Bean
	@Autowired
	public CommandLineRunner commandLineRunner(UserRepository userRepository, MediaRepository mediaRepository, RolesRepository rolesRepository, EntryRepository entryRepository) {

		return runner -> {
//			createOneUser(userRepository);
//			createOneMedia(mediaRepository);
//			createOneRole(userRepository, rolesRepository);
			createEntry(userRepository,mediaRepository,entryRepository);
		};
	}

	private void createEntry(UserRepository userRepository, MediaRepository mediaRepository, EntryRepository entryRepository) {
		if(userRepository.findAll().size()==0){
			createOneUser(userRepository);
		}
		User user = userRepository.findAll().get(0);
		if(mediaRepository.findAll().size()==0){
			createOneMedia(mediaRepository);
		}
		Media media = mediaRepository.findAll().get(0);
		Entry entry = new Entry(user,media);
		entry.setScore(90);
		entry.setStatus("COMPLETED");
		entryRepository.save(entry);
	}

	private void createOneRole(UserRepository userRepository, RolesRepository rolesRepository) {
		if(userRepository.findAll().size()==0){
			createOneUser(userRepository);
		}
		User user = userRepository.findAll().get(0);
		Role role = new Role(user,"newone");
		rolesRepository.save(role);
	}

	private void createOneUser(UserRepository userRepository) {
		User newUser = new User(1233,"popt");
		userRepository.save(newUser);
	}

	private void createOneMedia(MediaRepository mediaRepository) {
		Media newMedia = new Media(2900,"One Piece rocks");
		mediaRepository.save(newMedia);
	}

}
