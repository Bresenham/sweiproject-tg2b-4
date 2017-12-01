package base.activitymeter;

public class Tag {
	
	private String keyword;
	
	public Tag(String keyword){
	
		this.keyword = keyword;
	}
	
	public String getKeyword(){
		return keyword;
	}
	
	public void setKeyword(String keyword){
		this.keyword = keyword;
	}
	
	public boolean checkKeyword(String keyword){
		return (keyword == getKeyword());
	}
}
