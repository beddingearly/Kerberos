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
			this.getMessage();//��ȡ�ͻ��˷��͹�������Ϣ
			System.out.println("�յ��˿ͻ���message:" + dataPacket.getMessage());
			//������Ϣ�İ�ͷ�жϰ�������
			if(dataPacket.getHead().charAt(4) == '0'){//Ϊ��֤��
				//���
				CombinePacket combinePacket = new CombinePacket();
				String message = combinePacket.combine(dataPacket);
				message = "000100010000"+message; 
				//�����ݰ�ת��Ϊ�������ַ���
				message = Util.toBinaryString(message);
				//�������ݰ�
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
				System.out.println("�ظ����ͻ�����" + result);
				//ת��Ϊ�������ַ���
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
				System.out.println("���ӵ��ͻ��ˣ�");
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
			//�ӿͻ��˶�ȡ����
			reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			line = reader.readLine();
			//���ܵ��Ķ������ַ�������ת��Ϊ�ַ���
			line = Util.binaryToString(line);
			dataPacket.parseMessage(line, dataPacket);
		} catch (IOException e) {
			System.err.println("socketΪ��");
			e.printStackTrace();
		}
	}
	
}
