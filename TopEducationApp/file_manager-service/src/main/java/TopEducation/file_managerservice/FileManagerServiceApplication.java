package TopEducation.file_managerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FileManagerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileManagerServiceApplication.class, args);
	}

}
