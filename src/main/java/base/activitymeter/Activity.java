package base.activitymeter;

import java.util.Set;
import java.util.Date;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.GetMapping;

@Entity
@Table(name = "activity")
public class Activity {

	private static final long ADMIN_KEY = 999999;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long key;
	private String text;
	private String title;
	private String category;
	private boolean valid;
	private Date dateOfCreation; 
	@Column(length=1000000000)
	private String imgB64;
	private String ytbLink;

	public String getImgB64() {
		return imgB64;
	}

	public void setImgB64(String imgB64) {
		this.imgB64 = imgB64;
	}

	@OneToMany(mappedBy = "activity", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private Set<Tag> tags;

	public Activity() {

	}

	public Activity(long key, String text, String title, String category, String imgB64, String ytbLink) {
		this.key = key;
		this.text = text;
		this.title = title;
		this.category = category;
		this.imgB64 = imgB64;
		this.ytbLink = ytbLink;
		valid = false;
		dateOfCreation = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getKey() {
		return key;
	}

	public void setKey(long key) {
		this.key = key;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}

	public boolean getValid() {
		if (checkIfOutdated()){
			setValid(false);
		}
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public boolean checkId(long testId) {
		return (testId == getId());
	}
	
	public boolean checkKey(long testKey) {
		// Hier auch Admin-Key akzeptieren.
		return (testKey == getKey() || testKey == ADMIN_KEY);
	}

	public void addTag(Tag t) {
		tags.add(t);
	}
	
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Set<Tag> getTags() {
		return this.tags;
	}
	
	public boolean containsTag(String keyword) {
		boolean containsTag = false;
		for(Tag t : getTags()) {
			if(t.getKeyword().equals(keyword)) {
				containsTag = true;
				break;
			}
		}
		return containsTag;
	}

	public String getYtbLink() {
		return ytbLink;
	}

	public void setYtbLink(String ytbLink) {
		this.ytbLink = ytbLink;
	}
	public Date getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(Date newDate) {
		this.dateOfCreation = newDate;
	}
	public boolean checkIfOutdated(){
		int yearsUntilOutdated = 1;
		boolean isOutdated;
		if(getDateOfCreation() != null){
		Calendar post = Calendar.getInstance();
		post.setTime(getDateOfCreation());
		Calendar dDay = Calendar.getInstance();
		dDay.add(Calendar.YEAR, -yearsUntilOutdated);
		isOutdated = post.before(dDay);
		} else { isOutdated = false;} 			//possible Exception for Posts that should stay longer
		return isOutdated;
	} 
}