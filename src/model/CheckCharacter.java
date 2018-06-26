package model;

public class CheckCharacter {

	/**
	 * ○シングルクォーテーション表示
	 * 文字列中のシングルクォーテーションを表示させる為に
	 * 文字列中のシングルクォーテーションを2つに置換する。
	 * @param input 置換元の文字列
	 * @return result 置換後の文字列
	 */
	public String singleQuotation(String input) {
		String result = input.replaceAll("'", "''");
		return result;
	}

	/**
	 * ○サイズチェック
	 * @param input 文字数チェックにかける文字列
	 * @param i 指定文字サイズ(byte)
	 * @return judgeByte 正否falseの場合、サイズオーバー。trueの場合、規定値内。
	 */
	public boolean stringLengthCheck(String input, int i) {

		//結果変数
		boolean judgeByte;

		//指定文字数
		int max;

		// 何バイト分の長さであるかを取得
		int length = input.getBytes().length;
		// 最大バイト数の設定
		max = i;

		judgeByte = (length <= max);

		return judgeByte;
	}

	/**
	 * ○文字数チェック
	 * @param input 文字数チェックにかける文字列
	 * @param i 指定文字サイズ(文字数)
	 * @return judgeSize 正否falseの場合、文字数オーバー。trueの場合、規定値内。
	 */
	public boolean stringSizeCheck(String input, int i) {

		//結果変数
		boolean judgeSize;

		//指定文字数
		int max;

		// 文字数の長さを取得
		int length = input.toCharArray().length;
		// 最大文字数の設定
		max = i;

		judgeSize = (length <= max);

		return judgeSize;
	}

	/**
	 * ○半角チェック
	 * @param input チェックにかける文字列
	 * @return result 正否falseの場合、全角あり。trueの場合、半角のみ。
	 */
	public boolean halfSizeCheck(String input) {
		//結果変数
		boolean result;

		int len = input.length();
		byte[] bytes = input.getBytes();

		result = (len == bytes.length);

		return result;
	}

	/**
	 * ○空白チェック
	 * @param input チェックにかける文字
	 * @return result falseの場合、strが空白のみ。trueの場合、値がある。
	 */
	public boolean spaceCheck(String input) {
		boolean result;

		String str = input.replaceAll(" ", "");
		str = str.replaceAll("　", "");

		result = !(str.isEmpty());

		return result;

	}
}