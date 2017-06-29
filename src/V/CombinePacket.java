package V;

import java.math.BigInteger;

import text2.EncryMessage;

public class CombinePacket {
	public String combine(DataPacket dataPacket){
		String keyCV = dataPacket.getTicket().getKey();
		String timeStamp = dataPacket.getAuthenticator().getTimeStamp();
		BigInteger bigInteger = new BigInteger(timeStamp).add(new BigInteger("1"));
		timeStamp = bigInteger.toString();
		return EncryMessage.encryChatMessage(timeStamp, keyCV);
	}
}
