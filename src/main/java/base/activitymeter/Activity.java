package base.activitymeter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Activity {

    @Id
    private Long id;
    private String text;
    private String tags;
    private String title;
    private boolean valid;

    public Activity (){};

    public Activity(Long id, String text, String tags, String title) {
    	this.id = id;
        this.text = text;
        this.tags = tags;
        this.title = title;
        valid = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public String getTags() {
      return tags;
    }

    public void setTags(String tags) {
      this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public boolean isValid()
    {
    	return valid;
    }
    
    public void setValid(boolean valid)
    {
    	this.valid = valid;
    }
}