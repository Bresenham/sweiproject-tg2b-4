package base.activitymeter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private int key;
    private String text;
    private Tag[] tags;
    private String title;
    private boolean valid;
    
    public Activity() {
    	
    }
    
    public Activity (int key){
    	this.key = key;
    	valid = true;
    };

    public Activity(int key, String text, Tag[] tags, String title) {
    	this.key = key;
        this.text = text;
        this.tags = tags;
        this.title = title;
        valid = true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
    	this.id = id;
    }
    
    public int getKey() {
    	return key;
    }
    
    public void setKey(int key) {
    	this.key = key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public Tag[] getTags() {
      return tags;
    }

    public void setTags(Tag[] tags) {
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
    
    public boolean checkId(long testId){
    	return (testId == getId());
    }
}