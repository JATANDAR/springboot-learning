package au.com.acttab.model;


public class ToDoDatumModel {
	private String description;
	private String targetDate;
	private boolean isItDone;
	private Integer position;
	
	public ToDoDatumModel() {
		super();
	}

	public ToDoDatumModel(String description, String targetDate, boolean isItDone, Integer pos) {
		this.description = description;
		this.targetDate = targetDate;
		this.isItDone = isItDone;
		this.position = pos;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}
	public boolean isItDone() {
		return isItDone;
	}
	
	public void setItDone(boolean isItDone) {
		this.isItDone = isItDone;
	}
	
	public boolean getIsItDone() {
		return this.isItDone;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "ToDoDatumModel [description=" + description + ", targetDate=" + targetDate + ", isItDone=" + isItDone
				+ ", position=" + position + "]";
	}
	
}
