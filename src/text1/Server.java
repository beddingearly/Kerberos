import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
	public static final int PORT=8989;
	public Server(){	
	}
	public void run()
	{
		ServerSocket serverSocket;
		Socket client;
		Vector<Socket>socketList=new<String>Vector();
		try {
			//创建ServerSocket对象，监听本机的PORT端口
			serverSocket=new ServerSocket(PORT);
			while(true){
				//创建socket套接字，处理客户端连接请求
				client=serverSocket.accept();
				socketList.add(client);
				new Thread(new MyRunnable(client)).start();;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args)
	{
		Server server=new Server();
		server.run();
	}
}
