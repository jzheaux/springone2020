package io.jzheaux.springsecurity.patterns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class PatternsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatternsApplication.class, args);
	}

	@RestController
	static class PatternsController {
		private final LocationService locationService;

		public PatternsController(LocationService locationService) {
			this.locationService = locationService;
		}

		@GetMapping("/")
		public String hello() {
			return "Hello!";
		}
	}

}
