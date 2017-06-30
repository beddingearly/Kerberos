package C;


import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Calendar;

import text2.EncryMessage;

public class CombinePacket {
	
	/**
	 * 客户端发送给TGS的数据包的封包函数
	 * @param dataPacket 解析as发送给c的数据包类
	 * @return
	 * @throws UnknownHostException
	 */
	public String combine(DataPacket dataPacket) throws UnknownHostException{
		String key = dataPacket.getKey();//用于加密au的密钥
		String idv = "00010001";//用户从界面输入服务器ID。
		String idc = "00020002";//用户从界面输入
		String adc = ipToString(Inet4Address.getLocalHost().getHostAddress());
		Calendar calendar = Calendar.getInstance();
		String ts = String.valueOf(calendar.getTimeInMillis());
		String au = idc + adc + ts;
		au = EncryMessage.encryChatMessage(au, "aaaaaaaa");
		String ticket = dataPacket.getTicket();
		return idv + ticket + au;
	}
	
	//将ip地址转换为字符串
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
