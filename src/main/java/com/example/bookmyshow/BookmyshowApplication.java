package com.example.bookmyshow;

import com.example.bookmyshow.controllers.UserController;
import com.example.bookmyshow.dtos.SignUpRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing//helps to update few things in database automatically
@SpringBootApplication
public class BookmyshowApplication implements CommandLineRunner {
	@Autowired
	private UserController userController;

	public static void main(String[] args) {

		SpringApplication.run(BookmyshowApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		SignUpRequestDto request = new SignUpRequestDto();
		request.setEmail("abc@gmail.com");
		request.setPassword("password");
		userController.signUp(request);
	}
}
