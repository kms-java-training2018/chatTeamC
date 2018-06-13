package model;

public class CheckCharacter {



	/**
	 * 文字数チェック
	 * @param input 文字数チェックにかける文字列
	 * @param i 指定文字サイズ(byte)
	 * @return judgeByte 正否
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
	 * 半角チェック
	 * @param input チェックにかける文字列
	 * @return result 正否
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
	 * 空白チェック
	 * @param input チェックにかける文字
	 * @return result 正否
	 */
	public boolean spaceCheck(String input) {
		boolean result;

		String str = input.replaceAll(" ", "");
        str = str.replaceAll("　", "");

        result = !(str.isEmpty());

		return result;

	}



}


