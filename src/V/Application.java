package V;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.application.java.Util;

public class Application implements Runnable{
	private Socket socket;
	private DataPacket dataPacket = new DataPacket();
	@Override
	public void run() {
		while(true){
			this.getMessage();//获取客户端发送过来的消息
			System.out.println("收到了客户端message:" + dataPacket.getMessage());
			//根据消息的包头判断包的类型
			if(dataPacket.getHead().charAt(4) == '0'){//为认证包
				//组包
				CombinePacket combinePacket = new CombinePacket();
				String message = combinePacket.combine(dataPacket);
				message = "000100010000"+message; 
				//将数据包转换为二进制字符串
				message = Util.toBinaryString(message);
				//发送数据包
				PrintWriter write;
				try {
					write = new PrintWriter(socket.getOutputStream());
					write.println(message);
					write.flush();
					write.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}else{
				if(dataPacket.getMessage().charAt(0) == '@'){
					return;
				}
				Tuling tuling = new Tuling();
				String result = tuling.getServer(dataPacket.getMessage());
				System.out.println("回复到客户机：" + result);
				//转换为二进制字符串
				result = Util.toBinaryString(result);
				try {
					PrintWriter write = new PrintWriter(socket.getOutputStream());
					write.println(result);
					write.flush();
					write.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	public void startServer(ServerSocket serverSocket){
		while(true){
			try {
				Application app = new Application();
				app.socket = serverSocket.accept();
				System.out.println("连接到客户端！");
				Thread thread = new Thread(app);
				thread.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void getMessage(){
		BufferedReader reader;
		String line;
		try {
			//从客户端读取数据
			reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			line = reader.readLine();
			//将受到的二进制字符串数据转换为字符串
			line = Util.binaryToString(line);
			dataPacket.parseMessage(line, dataPacket);
		} catch (IOException e) {
			System.err.println("socket为空");
			e.printStackTrace();
		}
	}
	
}
