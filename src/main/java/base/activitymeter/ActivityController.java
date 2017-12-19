package base.activitymeter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
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
	public List<Activity> listAll() {
		ArrayList<Activity> activities = new ArrayList<>();
		activityRepository.findAll().forEach(activity -> {
			if (activity.getValid())
				activities.add(activity);
		});
		return activities;
	}

	@RequestMapping(value = "/activity/find/{id}", method = RequestMethod.GET)
	public Activity find(@PathVariable Long id) {
		return activityRepository.findOne(id);
	}
	
	@RequestMapping(value= "/activity/findByKey/{id}/{key}", method = RequestMethod.GET)
	public Activity findByKey(@PathVariable Long id, @PathVariable Long key) {
		Activity acti = activityRepository.findOne(id);
		if(acti != null && acti.checkKey(key)) {
			return acti;
		}
		return null;
	}
	@RequestMapping(value = "/activity/listByTag/{tagSearch}", method = RequestMethod.GET)
	public List<Activity> listByTag(@PathVariable String tagSearch) {
		String[] tags = tagSearch.split(",");
		ArrayList<Activity> activities = new ArrayList<>();
		activityRepository.findAll().forEach(acti -> {
			for(String tag : tags) {
				if(acti.containsTag(tag)) {
					activities.add(acti);
					break;
				}
			}
		});
		return activities;
	}
	@RequestMapping(value = "/activity/listByCategory/{category}",method=RequestMethod.GET)
	public List<Activity> listByCategory(@PathVariable String category){
		ArrayList<Activity> activities = new ArrayList<>();
		activityRepository.findAll().forEach(acti -> {
			if(acti.getCategory().equals(category))
				activities.add(acti);
		});
		return activities;
	}

	@RequestMapping(value = "/activity/allValid/{valid}", method = RequestMethod.GET)
	public List<Activity> listAll(@PathVariable boolean valid) {
		ArrayList<Activity> activities = new ArrayList<>();
		activityRepository.findAll().forEach(activity -> {
			if (activity.getValid() == valid)
				activities.add(activity);
		});
		return activities;
	}
	
	@RequestMapping(value = "/activity/getCategories", method = RequestMethod.GET)
	public List<String> getCategories(){
		return Arrays.asList("Students","Teachers","Travelling","Business","Enterpreneurship");
	}

	@RequestMapping(value = "/activity", method = RequestMethod.POST)
	public HttpStatus create(@RequestBody Activity input) {

		boolean[] foundOne = new boolean[] {false};
		activityRepository.findAll().forEach(act ->{
			if(act.getKey() == input.getKey()) {
				act.setText(input.getText());
				act.setTitle(input.getTitle());
				act.setCategory(input.getCategory());
				act.setImgB64(input.getImgB64());
				if (input.getTags() != null) {
					act.setTags(input.getTags());
					for (Tag t : input.getTags()) {
						act.addTag(t);
						tagRepository.delete(t);
						tagRepository.save(new Tag(t.getKeyword(), act));
					}
				}
				act.setValid(true);
				foundOne[0] = true;
				activityRepository.save(act);
			}
		});
		activityRepository.findAll().forEach(System.out::println);
		return foundOne[0] ? HttpStatus.OK : HttpStatus.NOT_FOUND;

		/*
		Activity saved = input;
		input.setValid(true);
		saved = activityRepository.save(saved);
		
		if (input.getTags() != null) {
			for (Tag t : input.getTags()) {
				saved.addTag(t);
				tagRepository.delete(t);
				tagRepository.save(new Tag(t.getKeyword(), input));
			}
		}
		return HttpStatus.OK;
		*/
	}

	@RequestMapping(value = "/activity/{id}/{key}", method = RequestMethod.DELETE)
	public HttpStatus delete(@PathVariable Long id, @PathVariable Long key) {
		Activity activity = activityRepository.findOne(id);
		if (activity != null && activity.checkKey(key)) {
			activity.setValid(false);
			activityRepository.save(activity);
			return HttpStatus.OK;
		}
		return HttpStatus.NOT_FOUND;
	}

	@RequestMapping(value = "/activity", method = RequestMethod.PUT)
	public HttpStatus update(@RequestBody Activity input) {
		Activity activity = activityRepository.findOne(input.getId());
		if(activity != null && activity.checkKey(input.getKey())) {
			activity.setTitle(input.getTitle());
			activity.setText(input.getText());
			activity.setCategory(input.getCategory());
			activity.setImgB64(input.getImgB64());
			if(input.getTags() != null) {
				for(Tag t : activity.getTags())
					tagRepository.delete(t);
				activity.setTags(input.getTags());
				for (Tag t : input.getTags()) {
					activity.addTag(t);
					tagRepository.delete(t);
					tagRepository.save(new Tag(t.getKeyword(), activity));
				}
			}
			return HttpStatus.OK;
		}
		return HttpStatus.NOT_FOUND;
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

		activityRepository.save(new Activity(key, "", "","",""));

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");

		MailMail mm = (MailMail) context.getBean("mailMail");
		mm.sendMail("activitymeter1@gmail.com", mail.getAdress(), "ActivityMeter-Code",
				"Hallo, \nder angeforderte Code lautet: " + key);
		return HttpStatus.OK;

	}

}
