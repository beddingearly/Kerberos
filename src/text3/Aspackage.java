package text3;
import java.util.Calendar;
import java.util.Random;

import javax.swing.plaf.synth.SynthSeparatorUI;

import com.ticket.java.DataPacket;

public class Aspackage {
  /**
   * Kc,tgs 
   * @return  8位字符串
   */
	public static String Createkey(int length){
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
		 Random random = new Random();   
		    StringBuffer sb = new StringBuffer();   
		    for (int i = 0; i < length; i++) {   
		        int number = random.nextInt(base.length());   
		        sb.append(base.charAt(number));   
		    }   
		    return sb.toString(); 
	}
	/**
	 * 需要接收到C——AS的认证包
	 * @return
	 */
	public static String GetTGSID(){
		DataPacket a =new DataPacket();
		String TGSID = a.getDes_id();
		return TGSID;
	}
	
	
	
	/**
	 * TS
	 * 
	 * @return
	 */
	public static String Gettimestamp(){
		Calendar calendar = Calendar.getInstance();
		String timeStamp = String.valueOf(calendar.getTimeInMillis());
		return timeStamp;
	
	}
	
	
	public static String  Lifetime(){
		String lifetime = "1800";
		return lifetime;
	}
	/**
	 * 需要接收到C——AS的认证包
	 * @return
	 */
	public static String GetCID(){
		DataPacket a =new DataPacket();
		String CID = a.getSrc_id();
		return CID;
	}
	/**
	 * 需要Socket输出流  AD源IP
	 * @return
	 */
	public static String GetAD(){
		
		return null;
		
	}
	
	public static String AStoC(){
		String key = Createkey(8);
		String TGSID = GetTGSID();
		String TS = Gettimestamp();
		String Lifetime = Lifetime();
		String CID =GetCID();
		String AD = GetAD();
		String Head = "000100111011";
		String Tackettext  = key+CID+AD+TGSID+TS+Lifetime;
		//需要为Tacket加密
		String Packet = Head+TGSID+TS+Lifetime+key+Tackettext;
		//需要为Packet加密
		return Packet;
		
	}
	
	
	
	public static void main(String[] args){
	String key = Createkey(8);
	String timestamp = Gettimestamp();
	System.out.println(key+" "+timestamp);
	}
	
	
	
	public static String Createticket(){
		return null;
		
	}

}
