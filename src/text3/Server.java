package text3;
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
		as_welcome a =new as_welcome();
		a.ini();
		ServerSocket serverSocket;
		Socket client;
		Vector<Socket>socketList=new<String>Vector();
		try {
			//����ServerSocket���󣬼���������PORT�˿�
			serverSocket=new ServerSocket(PORT);
			while(true){
				//����socket�׽��֣�����ͻ�����������
				client=serverSocket.accept();
				socketList.add(client);
				MyRunnable my = new MyRunnable(client,a.ta);
				Thread thread = new Thread(my);
				thread.start();	
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
