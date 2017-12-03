package base.activitymeter;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import base.mail.Mail;
import base.mail.MailMail;

@RestController
public class ActivityController {
	@Autowired
	private ActivityRepository activityRepository;

	@Autowired
	private TagRepository tagRepository;

	public TagRepository getTagRepository() {
		return tagRepository;
	}

	public ActivityRepository getActivityRepository() {
		return activityRepository;
	}

	@RequestMapping(value = "/activity", method = RequestMethod.GET)
	public ArrayList<Activity> listAll() {
		ArrayList<Activity> activities = new ArrayList<>();
		activityRepository.findAll().forEach(activity -> {
			if (activity.getValid())
				activities.add(activity);
		});
		return activities;
	}

	@RequestMapping(value = "/activity/{id}", method = RequestMethod.GET)
	public Activity find(@PathVariable Long id) {
		return activityRepository.findOne(id);
	}

	@RequestMapping(value = "/activity/{valid}", method = RequestMethod.GET)
	public ArrayList<Activity> listAll(@PathVariable boolean valid) {
		ArrayList<Activity> activities = new ArrayList<>();
		activityRepository.findAll().forEach(activity -> {
			if (activity.getValid() == valid)
				activities.add(activity);
		});
		return activities;
	}

	@RequestMapping(value = "/activity", method = RequestMethod.POST)
	public HttpStatus create(@RequestBody Activity input) {

		Activity saved = input;
		saved = activityRepository.save(saved);

		if (input.getTags() != null) {
			for (Tag t : input.getTags()) {
				saved.addTag(t);
				tagRepository.delete(t);
				tagRepository.save(new Tag(t.getKeyword(), input));
			}
		}
		activityRepository.findAll().forEach(System.out::println);
		return HttpStatus.OK;
	}

	@RequestMapping(value = "/activity/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		Activity activity = activityRepository.findOne(id);
		if (activity != null) {
			activity.setValid(false);
		}
	}

	@RequestMapping(value = "/activity/{id}", method = RequestMethod.PUT)
	public Activity update(@PathVariable Long id, @RequestBody Activity input) {
		Activity activity = activityRepository.findOne(id);
		if (activity == null) {
			return null;
		} else {
			activity.setText(input.getText());
			// activity.setTags(input.getTags());
			activity.setTitle(input.getTitle());
			return activityRepository.save(activity);
		}
	}

	@RequestMapping(value = "/SendMail", method = RequestMethod.POST)
	public HttpStatus send(@RequestBody Mail mail) {
		long min = 100000;
		long max = 999999;
		long key = -1;
		boolean unique = false;

		while (!unique) {
			key = (long) (Math.random() * (max - min)) + min;
			unique = (activityRepository.findIdByKey(key).isEmpty());
		}

		activityRepository.save(new Activity(key, "", ""));

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");

		MailMail mm = (MailMail) context.getBean("mailMail");
		mm.sendMail("activitymeter1@gmail.com", mail.getAdress(), "ActivityMeter-Code",
				"Hallo, \nder angeforderte Code lautet: " + key);
		return HttpStatus.OK;

	}

}
