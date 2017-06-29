package V;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
	public static void main(String[] args) {
		//VUserInterface vInterface = new VUserInterface();
		try {
			ServerSocket serverSocket = new ServerSocket(8989);
			Application app = new Application();
			app.startServer(serverSocket);
		} catch (IOException e) {
			System.err.println("服务器端口已经被占用！！！");
		}
	}
}
