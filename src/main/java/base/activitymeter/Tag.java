package base.activitymeter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tag {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private long activityId;
	private String keyword;
	
	public Tag() {
		
	}
	
	public Tag(String keyword){
		this.keyword = keyword;
		activityId = -1;
	}
	
	public String getKeyword(){
		return keyword;
	}
	
	public void setKeyword(String keyword){
		this.keyword = keyword;
	}
	
	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}
	
	public long getActivityId() {
		return activityId;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
    public boolean checkId(long testId){
    	return (testId == getId());
    }
	
	public boolean checkKeyword(String keyword){
		return (keyword.equals(getKeyword()));
	}
}
