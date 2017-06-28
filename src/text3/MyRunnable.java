package text3;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JTextArea;
import com.ticket.java.DataPacket;

import text2.EncryMessage;

public class MyRunnable implements Runnable{
	private int i=0;
	Socket client;
	String str="";
	JTextArea text=null;
	public MyRunnable(){
		
	}
	public MyRunnable(Socket client,JTextArea text)
	{
		this.client=client;
		this.text = text;
	}
	@Override
	public void run() {
		BufferedReader cin = null;
		PrintWriter cout;
		try{
			//等待接受客户端的连接请求，连接成功后返回一个已连接的Socket对象
			//创建缓冲区对象，用于读取客户端发送的信息
			cin=new BufferedReader(new InputStreamReader(client.getInputStream()));
			//创建缓冲区对象，用于向客户端发送信息
			cout=new PrintWriter(client.getOutputStream());
			String s =null;
			s = cin.readLine();
			DataPacket dataPacket = new DataPacket();
			dataPacket.parseMessage(s);
			linksql z=new linksql(); 
			int num= z.Authen(dataPacket.getSrc_id());
			if(num==1){
				str = dataPacket.getSrc_id();
				text.append(str+"认证成功！\n");	
				String Finaltext=AStoC(dataPacket);
				System.out.println( Finaltext);
				cout.println(Finaltext);
				cout.flush();
			}else{
				str = dataPacket.getSrc_id();
				text.append(str+"认证失败！\n");
			}					
		}catch(Exception e){
		System.out.println("服务器端出错，信息如下：\n"+e.getMessage());
		}		
	}
	/**
	 * 打包AS to C
	 * @param dataPacket
	 * @return
	 */
	public String AStoC(DataPacket dataPacket){
		Aspackage pa= new Aspackage();
		String ktgs = "aaaaaaaa";
		EncryMessage em = new EncryMessage();
		String Keyctgs=pa.Createkey(8);
		String TGSID = dataPacket.getDes_id();
		String TS2 = pa.Gettimestamp();
		String Lifetime2 =pa.Lifetime();
		String CID = dataPacket.getSrc_id();
		InetAddress address = client.getInetAddress();
		String AD=address.toString();
		//System.out.println( AD);
		String tickettgs = Keyctgs + CID + AD + TGSID + TS2 + Lifetime2;//需要加密
		tickettgs = EncryMessage.encryChatMessage(tickettgs, ktgs);
		System.out.println(tickettgs);
		String head = "000100111110";
		String text = TGSID + TS2 + Lifetime2 +tickettgs + Keyctgs;//需要加密
		
		String kc = "ssssssss";
		text = EncryMessage.encryChatMessage(text, kc);
		
		String Finaltext = head + text ;
		System.out.println("finalText:" + Finaltext);
		
		
		System.out.println(Finaltext);
		return Finaltext ;
	}

}
