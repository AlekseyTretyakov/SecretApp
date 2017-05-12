package agency.akcom.ggs.shared.crypt;

public class Crypto implements ICrypto{
	private String text;
	
	
	public int[] digitize(String word) {
		int[] code = new int[word.length()];
		for (int i = 0; i < code.length; i++){
			char letter = word.charAt(i);
			code[i] = (int)letter;
		}
		return code;
	}
	public String redigitize(int[] code) {
		String word = "";
		for (int i = 0; i < code.length; i++){
			word += Character.toString((char)code[i]);
		}
		return word;
	}
	@Override
	public String crypt(String word, double okey, int skey) {
		return "";
	}

	@Override
	public String decrypt(String text) {
		// TODO Auto-generated method stub
		return null;
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
		return 6;
	}
	
}
