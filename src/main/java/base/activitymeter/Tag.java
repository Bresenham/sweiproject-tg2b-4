package base.activitymeter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
import org.json.JSONObject;
*/

@Entity
@Table(name = "tag")
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String keyword;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "activity_id")
	private Activity activity;

	public Tag() {
	}
	
	public Tag(String keyword) {
	this.keyword = keyword;
	}

	public Tag(String keyword, Activity activity) {
		this.keyword = keyword;
		this.activity = activity;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Activity getActivity() {
		return this.activity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean checkId(long testId) {
		return (testId == getId());
	}

	public boolean checkKeyword(String keyword) {
		return (keyword.equals(getKeyword()));
	}

	/*
	public String toString() {
		String info = "";
		try {
			JSONObject jsonInfo = new JSONObject();
			jsonInfo.put("keyword", this.keyword);
			try {
				JSONObject activityObj = new JSONObject();
				activityObj.put("key", this.activity.getKey());
				activityObj.put("text", this.activity.getText());
				activityObj.put("title", this.activity.getTitle());
				activityObj.put("valid", this.activity.getValid());
				jsonInfo.put("activity", activityObj);
			} catch (Exception e) {
				// TODO: handle exception
			}
			

			info = jsonInfo.toString();
		} catch (Exception e) {
		}

		return info;
	}
	*/
}
