package C;


import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Calendar;

import text2.EncryMessage;

public class CombinePacket {
	
	/**
	 * �ͻ��˷��͸�TGS�����ݰ��ķ������
	 * @param dataPacket ����as���͸�c�����ݰ���
	 * @return
	 * @throws UnknownHostException
	 */
	public String combine(DataPacket dataPacket) throws UnknownHostException{
		String key = dataPacket.getKey();//���ڼ���au����Կ
		String idv = "00010001";//�û��ӽ������������ID��
		String idc = "00020002";//�û��ӽ�������
		String adc = ipToString(Inet4Address.getLocalHost().getHostAddress());
		Calendar calendar = Calendar.getInstance();
		String ts = String.valueOf(calendar.getTimeInMillis());
		String au = idc + adc + ts;
		au = EncryMessage.encryChatMessage(au, "aaaaaaaa");
		String ticket = dataPacket.getTicket();
		return idv + ticket + au;
	}
	
	//��ip��ַת��Ϊ�ַ���
	public static String ipToString(String ip){
		String result = new String();
		char[] ipArray = ip.toCharArray();
		String temp = new String();
		for(int i = 0; i < ipArray.length; i ++){
			if(ipArray[i] == '.'){
				int number = temp.length();
				if(number < 3){
					for(int j = 0; j < (3-number); j ++){
						temp = "0" + temp;
					}
				}
				result += temp;
				temp = "";
			}else{
				temp += ipArray[i];
			}
		}
		int number = temp.length();
		if(number < 3){
			for(int j = 0; j < (3-number); j ++){
				temp = "0" + temp;
			}
		}
		result += temp;
		return result;
	}
	
	public static void main(String[] args) throws UnknownHostException {
//		String msg = "";//as->c
//		DataPacket dataPacket = new DataPacket();
//		dataPacket.parseMessage(msg, dataPacket);
//		CombinePacket combinePacket = new CombinePacket();
//		combinePacket.combine(dataPacket);
	}
}
