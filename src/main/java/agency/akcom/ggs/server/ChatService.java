package agency.akcom.ggs.server;

import java.util.ArrayList;
import java.util.List;

public class ChatService {
	
	public static List<IChatListener> listeners = new ArrayList<>();
	
	public static void add(IChatListener listener) {
		listeners.add(listener);
	}
	
	public static void notifyListeners() {
		for (IChatListener listener : listeners){
			listener.update();
		}
	}
}
