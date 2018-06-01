package bean;


public class ShowProfileBean {

	private String name;
	private String profile;

	//SQLが取得できたか
	private int sql;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;

	}
	public int getSql() {
		return sql;
	}
	public void setSql(int sql) {
		this.sql = sql;
	}

}
