package agency.akcom.ggs.shared.crypt;

public class Crypto implements ICrypto{
	private String text;
	
	
	public int[] digitize(String word) {
		int[] code = new int[text.length()];
		for (int i = 0; i < code.length; i++){
			//char letter = word[i];
			//code[i] = word[i];
		}
		return null;
	}
	public String redigitize(int[] code) {
		return null;
	}
	@Override
	public String crypt(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String decrypt(String text) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
