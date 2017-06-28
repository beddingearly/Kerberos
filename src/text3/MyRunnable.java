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
			//�ȴ����ܿͻ��˵������������ӳɹ��󷵻�һ�������ӵ�Socket����
			//�����������������ڶ�ȡ�ͻ��˷��͵���Ϣ
			cin=new BufferedReader(new InputStreamReader(client.getInputStream()));
			//��������������������ͻ��˷�����Ϣ
			cout=new PrintWriter(client.getOutputStream());
			String s =null;
			s = cin.readLine();
			DataPacket dataPacket = new DataPacket();
			dataPacket.parseMessage(s);
			linksql z=new linksql(); 
			int num= z.Authen(dataPacket.getSrc_id());
			if(num==1){
				str = dataPacket.getSrc_id();
				text.append(str+"��֤�ɹ���\n");	
				String Finaltext=AStoC(dataPacket);
				System.out.println( Finaltext);
				cout.println(Finaltext);
				cout.flush();
			}else{
				str = dataPacket.getSrc_id();
				text.append(str+"��֤ʧ�ܣ�\n");
			}					
		}catch(Exception e){
		System.out.println("�������˳�����Ϣ���£�\n"+e.getMessage());
		}		
	}
	/**
	 * ���AS to C
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
		String tickettgs = Keyctgs + CID + AD + TGSID + TS2 + Lifetime2;//��Ҫ����
		tickettgs = EncryMessage.encryChatMessage(tickettgs, ktgs);
		System.out.println(tickettgs);
		String head = "000100111110";
		String text = TGSID + TS2 + Lifetime2 +tickettgs + Keyctgs;//��Ҫ����
		
		String kc = "ssssssss";
		text = EncryMessage.encryChatMessage(text, kc);
		
		String Finaltext = head + text ;
		System.out.println("finalText:" + Finaltext);
		
		
		System.out.println(Finaltext);
		return Finaltext ;
	}

}
