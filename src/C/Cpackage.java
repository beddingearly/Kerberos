package C;

import java.util.Calendar;
import java.util.Random;



public class Cpackage {

	
	/**
	 * �����Ự�Գ�key
	 * @return
	 */
	public static String Createkey(){
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";   
		 Random random = new Random();   
		    StringBuffer sb = new StringBuffer();   
		    for (int i = 0; i < 8; i++) {   
		        int number = random.nextInt(base.length());   
		        sb.append(base.charAt(number));   
		    } 
		    return sb.toString(); 
	}
	
	/**
	 * ��UI����socket��ȡ
	 * @param str
	 * @return
	 */
	
	public static String GetCID(String str){
		return str;
	}
	
	/**
	 * ��UI����socket��ȡ
	 * @param str
	 * @return
	 */
	public static String GetVID(String str){
		return str;
	}
	
	/**
	 * TGS�̶�ID ��00000001��
	 * @return
	 */
	public static String GetTGSID(){
		String TGSID = "00000001";
		return TGSID;
	}
	/**
	 * lifetime �̶�Ϊ1800s
	 * @return
	 */
	public static String GetLifetime(){
		String time = "1800";
		return time;
	}
	
	/**
	 * TS
	 * @return
	 */
	public static String GetTS(){
		Calendar calendar = Calendar.getInstance();
		String timeStamp = String.valueOf(calendar.getTimeInMillis());
		return timeStamp;
	
	}

	
	/**
	 * ��ҪSocket�����  ADԴIP
	 * @return
	 */
	public static String GetAD(String str){
		int h = str.length();
		String temp=str.substring(1, h);
		String s="";
		String []a=temp.split("\\.");
		for(int i = 0; i<4 ;i++){
			if(a[i].length()==1){
				a[i]="00"+a[i];
			}
			if(a[i].length()==2){
				a[i]="0"+a[i];
			}
			s+=a[i];
		}
		return s;
	}
	
	
	public static void main(String[] args){
        String temp ="/192.168.1.12";
        temp=GetAD(temp)+" "+Createkey();
		System.out.println(temp);
	}
}
