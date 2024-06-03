package ca.parimal.connectz;

import ca.parimal.connectz.controller.GraphqlFetch;
import ca.parimal.connectz.controller.HomePage;
import ca.parimal.connectz.controller.MediaListController;
import ca.parimal.connectz.model.dao.MediaRepository;
import ca.parimal.connectz.model.dao.UserDao;
import ca.parimal.connectz.model.dao.UserRepository;
import ca.parimal.connectz.model.entities.Media;
import ca.parimal.connectz.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ConnectzApplication {

	public static void main(String[] args) {
		//MediaListController.getMediaList("popt");
		SpringApplication.run(ConnectzApplication.class, args);
	}

	@Bean
	@Autowired
	public CommandLineRunner commandLineRunner( MediaRepository mediaRepository) {

		return runner -> {
			createOneUser( mediaRepository);
		};
	}

	private void createOneUser(MediaRepository mediaRepository) {
//		List<User> users = userDAO.findAll();
//		System.out.println(users);
//		User newUser = new User("Parimal",29);
//		userDAO.save(newUser);

		Media newMedia = new Media();
		newMedia.setTitle("One Piece");
		mediaRepository.save(newMedia);
	}

}
