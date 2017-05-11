package agency.akcom.ggs.shared.crypt;

public interface ICrypto {
	String crypt(String text);
	String decrypt(String text);
}
