package cn.buaa.myweixin;

public class ActivityEntity {
	private static final String TAG = ActivityEntity.class.getSimpleName();

	private String actName;

	private String actDate;

	private String actClass;

	private String actPlace;

	public ActivityEntity(String actName, String actDate, String actClass,
			String actPlace) {
		super();
		this.actName = actName;
		this.actDate = actDate;
		this.actClass = actClass;
		this.actPlace = actPlace;
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public String getActDate() {
		return actDate;
	}

	public void setActDate(String actDate) {
		this.actDate = actDate;
	}

	public String getActClass() {
		return actClass;
	}

	public void setActClass(String actClass) {
		this.actClass = actClass;
	}

	public String getActPlace() {
		return actPlace;
	}

	public void setActPlace(String actPlace) {
		this.actPlace = actPlace;
	}

}
