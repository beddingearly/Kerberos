package TGS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JTextArea;

import com.application.java.Util;

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
			s=Util.binaryToString(s);
			System.out.println("C->TGS:������"+s);
			DataPacket dataPacket = new DataPacket();
			dataPacket.parseMessage(s, dataPacket);
			linksql z=new linksql(); 
			int num= z.Authen(dataPacket.getDes_id());
			if(num==1){	
				String Finaltext=TGStoC(dataPacket);
				cout.println(Finaltext);
				cout.flush();
			}else{
				text.append("��Ʊʧ�ܣ�\n");
			}					
		}catch(Exception e){
			e.printStackTrace();//System.out.println("�������˳�����Ϣ���£�\n"+e.getMessage());
		}		
	}
	/**
	 * ���TGS to C
	 * @param dataPacket
	 * @return
	 */
	public String TGStoC(DataPacket datapacket){
		
		String mess="";
		String VID = datapacket.getDes_id();
		Ticket Tickettgs= datapacket.getTicket();
		Authenticator Authen = datapacket.getAuthenticator();
		String Key = Tickettgs.getKey();
		String VIDt = Tickettgs.getDes_id();
		String CIDt = Tickettgs.getSrc_id();
		String ADt  = Tickettgs.getAd();
		Cpackage a = new Cpackage();
		String TS4 = a.GetTS();
		String AD = Authen.getAd();
		String CID = Authen.getSrc_id();
		String TS3 = Authen.getTimeStamp();
		if( CIDt.equals(CID) ){                 //***********AD������
			String head ="000100110110";
			String Keycv = a.Createkey();
			String LifeTime4 = a.GetLifetime();
			String ticketv =Keycv + CID + AD + VIDt + TS4 +LifeTime4 ;
			linksql kk = new linksql(); 
			String Keyv =kk.Getkey1(VIDt);          //TGS��Vά������Կ
			ticketv = EncryMessage.encryChatMessage(ticketv,Keyv);
		    mess = VIDt + TS4 + ticketv + Keycv;
		    System.out.println("TGS->C:δ����"+mess);
			mess = head + EncryMessage.encryChatMessage(mess, Key);
			 System.out.println("TGS->C:����"+mess);
			mess = Util.toBinaryString(mess);  //ת������
			 System.out.println("TGS->C:������"+mess);
			str = CIDt;
			text.append(str+"��Ʊ�ɹ���\n");
		}else{
			System.out.println(CID + "Ʊ����Ч����");
		}
		
		return mess;
	}
}
