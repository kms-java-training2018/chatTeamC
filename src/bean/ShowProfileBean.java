package bean;

public class ShowProfileBean {

	private String name;
	private String profile;

	//SQLが取得できたか
	private int Unacquired = 1;

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

	public int getUnacquired() {
		return Unacquired;
	}

	public void setUnacquired(int Unacquired) {
		this.Unacquired = Unacquired;
	}

}
