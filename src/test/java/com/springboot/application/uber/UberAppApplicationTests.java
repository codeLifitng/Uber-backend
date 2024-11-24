package com.springboot.application.uber;

import com.springboot.application.uber.services.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UberAppApplicationTests {

	@Autowired
	private EmailSenderService emailSenderService;

	@Test
	void contextLoads() {
		emailSenderService.sendEmail("nilayrs0803@gmail.com",
				"Test email from Taxi Service",
				"Hi this is a testing email from the taxi service, Don't take it seriously");
	}

	@Test
	void sendMultipleMultipleEmails() {
		String emails[] = {
				"nilayrs0803@gmail.com",
				"rajnilaysharma@gmail.com",
				"nilay.fly@gmail.com"
		};
		emailSenderService.sendEmail(emails,
				"Test email from Taxi Service",
				"Hi this is a testing email from the taxi service, Don't take it seriously");
	}

}
