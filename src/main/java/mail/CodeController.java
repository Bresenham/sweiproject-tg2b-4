package mail;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import base.activitymeter.Activity;
import base.activitymeter.ActivityController;


@RestController
@RequestMapping("/RequestID")

public class CodeController {
	@PostMapping
	public void send(@RequestBody String mail) {
		long min = 100000;
		long max = 999999;
		long id = -1;
		boolean unique = false;

		while (!unique) {
			id = (long) (Math.random() * (max - min)) + min;
			unique = !ActivityController.getActivityRepository().exists((long) id);
		}

		ActivityController.getActivityRepository().save(new Activity(id, "", "", ""));
		
		
		ApplicationContext context =
	             new ClassPathXmlApplicationContext("Spring-Mail.xml");

	    	MailMail mm = (MailMail) context.getBean("mailMail");
	        mm.sendMail("activitymeter1@gmail.com",
	    		   mail,
	    		   "Testing123",
	    		   "Testing only \n\n Hello Spring Email Sender");
		
		

	}

	

}
