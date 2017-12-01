package base.activitymeter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private final int id;
    private String text;
    private Tag[] tags;
    private String title;
    private boolean valid;

    public Activity (int id){
    	this.id = id;
    	valid = true;
    };


    public Activity(int id, String text, Tag[] tags, String title) {
    	this.id = id;
        this.text = text;
        this.tags = tags;
        this.title = title;
        valid = true;
    }

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

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
    
    public boolean checkId(int testId){
    	return (testId == getId());
    }
}