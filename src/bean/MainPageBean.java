package bean;

import java.util.ArrayList;

public class MainPageBean {
	/** メンバー一覧 + 会話 */
	private ArrayList<LatestMenberMessageBean> latestMenberMessageBeanList = new ArrayList<LatestMenberMessageBean>();

	/** グループ一覧 */
	private ArrayList<LatestGroupMessageBean> latestGroupMessageBeanList = new ArrayList<LatestGroupMessageBean>();

	/**
	 * ダイレクトメッセージの最新メッセージ一覧を返す
	 */
	public ArrayList<LatestMenberMessageBean> getLatestMenberMessageBeanList() {
		return latestMenberMessageBeanList;
	}

	/**
	 * ダイレクトメッセージの最新メッセージ一覧を設定
	 */
	public void setLatestMenberMessageBeanList(LatestMenberMessageBean latestMenberMessageBeanList) {
		this.latestMenberMessageBeanList.add(latestMenberMessageBeanList);
	}

	/**
	 * グループメッセージの最新メッセージ一覧を返す
	 */
	public ArrayList<LatestGroupMessageBean> getLatestGroupMessageBeanList() {
		return latestGroupMessageBeanList;
	}

	/**
	 * グループメッセージの最新メッセージ一覧を設定
	 */
	public void setLatestGroupMessageBeanList(LatestGroupMessageBean latestGroupMessageBeanList) {
		this.latestGroupMessageBeanList.add(latestGroupMessageBeanList);
	}
}
