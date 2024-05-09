package ca.parimal.connectz;

import ca.parimal.connectz.controller.GraphqlFetch;
import ca.parimal.connectz.controller.HomePage;
import ca.parimal.connectz.controller.MediaListController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConnectzApplication {

	public static void main(String[] args) {
		//MediaListController.getMediaList("popt");
		SpringApplication.run(ConnectzApplication.class, args);
	}

}
