package base.activitymeter;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
*/
@Entity
@Table(name = "activity")
public class Activity {

	private static final long AdminKey = 999999;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long key;
	private String text;
	private String title;
	private String category;
	private boolean valid;

	@OneToMany(mappedBy = "activity", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private Set<Tag> tags;

	public Activity() {

	}

	public Activity(long key, String text, String title, String category) {
		this.key = key;
		this.text = text;
		this.title = title;
		this.category = category;
		valid = false;
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
		return (testKey == getKey() || testKey == AdminKey);
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

	/*
	public String toString() {
		String info = "";
		try {
			JSONObject jsonInfo = new JSONObject();
			jsonInfo.put("key", this.key);
			jsonInfo.put("text", this.text);
			jsonInfo.put("title", this.title);
			jsonInfo.put("valid", this.valid);

			JSONArray tagArray = new JSONArray();
			if (this.tags != null) {
				this.tags.forEach(tag -> {
					JSONObject subJson = new JSONObject();
					try {
						subJson.put("keyword", tag.getKeyword());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					tagArray.put(subJson);
				});
			}
			jsonInfo.put("tags", tagArray);
			info = jsonInfo.toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return info;

	}
	*/
}