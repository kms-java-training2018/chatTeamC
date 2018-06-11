package bean;

public class MyPageBean {
	// マイページに表示される名前
	private String name;
	// マイページに表示されるプロフィール
	private String myProfile;

	/*** 名前を返す */
	public String getName() {
		return name;
	}
	/*** 名前を設定する */
	public void setName(String name) {
		this.name = name;
	}
	/*** プロフィールを返す */
	public String getMyProfile() {
		return myProfile;
	}
	/*** プロフィールを設定する */
	public void setMyProfile(String myProfile) {
		this.myProfile = myProfile;
	}

}
