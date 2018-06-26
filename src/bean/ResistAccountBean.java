package bean;

public class ResistAccountBean {
	/** 会員ID */
	private String userId;

	/** パスワード */
	private String password;

	/** id,passエラーメッセージ */
	private String errorMessage;

	/** name,profileエラーメッセージ */
	private String profileErrorMessage;

	/** sqlエラーメッセージ */
	private String sqlErrorMessage;

	/** id被りエラーメッセージ */
	private String idErrorMessage;


	/** check判定*/
	private boolean checkJudge;

	/** 表示名 */
	private String userName;

	/** 会員番号 */
	private String userProfile;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProfile() {
		return userProfile;
	}

	public void setProfile(String profile) {
		this.userProfile = profile;
	}

	public String getProfileErrorMessage() {
		return profileErrorMessage;
	}

	public void setProfileErrorMessage(String profileErrorMessage) {
		this.profileErrorMessage = profileErrorMessage;
	}

	public String getSqlErrorMessage() {
		return sqlErrorMessage;
	}

	public void setSqlErrorMessage(String sqlErrorMessage) {
		this.sqlErrorMessage = sqlErrorMessage;
	}

	public boolean isCheckJudge() {
		return checkJudge;
	}

	public void setCheckJudge(boolean checkJudge) {
		this.checkJudge = checkJudge;
	}

	public String getIdErrorMessage() {
		return idErrorMessage;
	}

	public void setIdErrorMessage(String idErrorMessage) {
		this.idErrorMessage = idErrorMessage;
	}

}
