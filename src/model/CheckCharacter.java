package model;

public class CheckCharacter {

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

	public boolean halfSizeCheck(String input) {
		//結果変数
		boolean result;

		int len = input.length();
		byte[] bytes = input.getBytes();

		result = (len == bytes.length);

		return result;
	}

}
