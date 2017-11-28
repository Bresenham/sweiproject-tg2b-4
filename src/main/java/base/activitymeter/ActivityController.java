package base.activitymeter;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activity")
public class ActivityController {
  
  @Autowired
  private static ActivityRepository activityRepository;
  
  
  public static ActivityRepository getActivityRepository() {
	return activityRepository;
}

@GetMapping
  public ArrayList<Activity> listAll() {
      ArrayList<Activity> activities = new ArrayList<>();
      activityRepository.findAll().forEach(activity -> 
      {if(activity.isValid())
    	  activities.add(activity);
      });
      return activities;
  }

  @GetMapping("{id}")
  public Activity find(@PathVariable Long id) {
      return activityRepository.findOne(id);
  }
  
  @GetMapping("{valid}")
  public ArrayList<Activity> listAll(@PathVariable boolean valid)
  {
	  ArrayList<Activity> activities = new ArrayList<>();
      activityRepository.findAll().forEach(activity -> 
      {if(activity.isValid() == valid)
    	  activities.add(activity);
      });
      return activities;
  }

  @PostMapping
  public Activity create(@RequestBody Activity input) {
      return activityRepository.save(new Activity(input.getId(), input.getText(), input.getTags(), input.getTitle()));
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable Long id) {
      Activity activity = activityRepository.findOne(id);
      if(activity != null)
      {
    	  activity.setValid(false);
      }
  }

  @PutMapping("{id}")
  public Activity update(@PathVariable Long id, @RequestBody Activity input) {
      Activity activity = activityRepository.findOne(id);
      if (activity == null) {
          return null;
      } else {
          activity.setText(input.getText());
          activity.setTags(input.getTags());
          activity.setTitle(input.getTitle());
          return activityRepository.save(activity);
      }
  }

}
