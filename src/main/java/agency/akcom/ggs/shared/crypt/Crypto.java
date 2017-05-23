package agency.akcom.ggs.shared.crypt;

public class Crypto implements ICrypto{
	private String text;
	
	
	public static int[] digitize(String word) {
		int[] code = new int[word.length()];
		for (int i = 0; i < code.length; i++){
			char letter = word.charAt(i);
			code[i] = (int)letter;
		}
		return code;
	}
	public static String redigitize(int[] code) {
		String word = "";
		for (int i = 0; i < code.length; i++){
			word += Character.toString((char)code[i]);
		}
		return word;
	}
	
	public static String crypt(String word, double skey) {
		
		int[] code = digitize(word);
		int[] newCode = new int[code.length];
		
		for (int i = 0; i < newCode.length; i++){
			newCode[i] = code[i] + (int)skey + 1;
		}
		String newWord = redigitize(newCode);
		
		return newWord;
	}

	
	public static String decrypt(String word, double skey) {
		
		int[] code = digitize(word);
		int[] newCode = new int[code.length];
		
		for (int i = 0; i < newCode.length; i++){
			newCode[i] = code[i] - (int)skey - 1;
		}
		String newWord = redigitize(newCode);
		
		return newWord;
	}
	public static double getSahredSecretKey(double openKey, int secretKey, int p) {
		return Math.pow(openKey, secretKey) % p;
	}
	public static double getOpenKey(int secret, int p, int g) {
		return Math.pow(g, secret) % p;
	}
	/*
	 * Do a random
	 */
	public static int randomSecretKey() {
		//return 6;
		int Min = 5;
		int Max = 20;
		int rand = Min + (int)(Math.random() * ((Max - Min) + 1));
		return rand;
	}
	
	public static double cryptPass(String pass, double key){
		int[] code = digitize(pass);
		int[] newCode = new int[code.length];
		return 0;
	}
}
