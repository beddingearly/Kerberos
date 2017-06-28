package TGS;

import text2.EncryMessage;

public class Authenticator {
	private String src_id;
	private String ad;
	private String timeStamp;
	
	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	private final static int ID_LENGTH = 8;
	private final static int TIMESTAMP_LENGTH = 13;
	private final static int AD_LENGTH = 12;
	
	private String auDecryption(String au, DataPacket dataPacket){
		
		String key  = dataPacket.getTicket().getKey();
		return EncryMessage.decryMessage(au, key);
	}
	
	public void divTicketTGS(String TTGS, DataPacket dataPacket){

		String ttgs = auDecryption(TTGS, dataPacket);

		String ss = "";
		
		ss = ttgs.substring(0, ID_LENGTH);//8 IDc
		ttgs = ttgs.substring(ID_LENGTH, ttgs.length());
		setSrc_id(ss);
		
		
		ss = ttgs.substring(0, AD_LENGTH);//29 ADc
		ttgs = ttgs.substring(AD_LENGTH, ttgs.length());
		setAd(ss);
		
		ss = ttgs.substring(0, TIMESTAMP_LENGTH);//6 TS2
		ttgs = ttgs.substring(TIMESTAMP_LENGTH, ttgs.length());
		setTimeStamp(ss);

	}

	public String getSrc_id() {
		return src_id;
	}

	public void setSrc_id(String src_id) {
		this.src_id = src_id;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
}
