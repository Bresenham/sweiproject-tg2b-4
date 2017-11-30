package base.mail;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import base.activitymeter.Activity;
import base.activitymeter.ActivityController;

@RestController
@RequestMapping("/SendMail")

public class CodeController {

	
	
	
	@PostMapping
	public HttpStatus send(@RequestBody Mail mail) {
		long min = 100000;
		long max = 999999;
		long id = -1;
		boolean unique = false;

		// while (!unique) {
		id = (long) (Math.random() * (max - min)) + min;
		// unique = !ActivityController.getActivityRepository().exists((long) id);
		// }

		// ActivityController.getActivityRepository().save(new Activity(id, "", "",
		// ""));

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");

		MailMail mm = (MailMail) context.getBean("mailMail");
		mm.sendMail("activitymeter1@gmail.com", mail.getAdress(), "ActivityMeter-Code",
				"Hallo, \nder angeforderte Code lautet: " + id);
		return HttpStatus.OK;

	}

}
