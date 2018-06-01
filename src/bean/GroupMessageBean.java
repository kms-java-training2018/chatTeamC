package bean;

public class GroupMessageBean {
	/** 自分のグループ番号 */
	private String my_group_no;

	/** 移動先グループ番号 */
	private String de_group_no;

	public String getMy_group_no() {
		return my_group_no;
	}

	public void setMy_group_no(String my_group_no) {
		this.my_group_no = my_group_no;
	}

	public String getDe_group_no() {
		return de_group_no;
	}

	public void setDe_group_no(String de_group_no) {
		this.de_group_no = de_group_no;
	}

	/** 自分の会員番号 */
	private String my_user_no;

	/** 移動先会員番号 */
	private String de_user_no;

	public String getMy_user_no() {
		return my_user_no;
	}

	public void setMy_user_no(String my_user_no) {
		this.my_user_no = my_user_no;
	}

	public String getDe_user_no() {
		return de_user_no;
	}

	public void setDe_user_no(String de_user_no) {
		this.de_user_no = de_user_no;
	}
}