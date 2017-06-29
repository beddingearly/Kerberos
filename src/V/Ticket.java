package V;

import text2.EncryMessage;

public class Ticket {
	private String key;
	private String src_id;
	private String ad;
	private String des_id;
	private String timeStamp;
	private String lifeTime;

	private final static int ID_LENGTH = 8;
	private final static int TIMESTAMP_LENGTH = 13;
	private final static int LIFETIME_LENGTH = 4;
	private final static int KEY_LENGTH = 8;
	private final static int AD_LENGTH = 12;
	
	private String ticketDecryption(String ticket){
		//v和tgs共同维护的密钥
		String key="aaaaaaaa";
		return EncryMessage.decryMessage(ticket, key);
	}
	
	public void divTicketTGS(String TTGS){
		
		//DES() to ttgs;
		String ttgs = ticketDecryption(TTGS);;

		String ss = "";
		ss = ttgs.substring(0, KEY_LENGTH);//8 Kc,tgs
		ttgs = ttgs.substring(KEY_LENGTH, ttgs.length());
		setKey(ss);
		
		ss = ttgs.substring(0, ID_LENGTH);//8 IDc
		ttgs = ttgs.substring(ID_LENGTH, ttgs.length());
		setSrc_id(ss);
		
		
		ss = ttgs.substring(0, AD_LENGTH);//29 ADc
		ttgs = ttgs.substring(AD_LENGTH, ttgs.length());
		setAuthenticator(ss);
		
		ss = ttgs.substring(0, ID_LENGTH);//8 IDtgs
		ttgs = ttgs.substring(ID_LENGTH, ttgs.length());
		setDes_id(ss);
		
		ss = ttgs.substring(0, TIMESTAMP_LENGTH);//6 TS2
		ttgs = ttgs.substring(TIMESTAMP_LENGTH, ttgs.length());
		setTimeStamp(ss);
		
		ss = ttgs.substring(0, LIFETIME_LENGTH);//4 Lifetime2
		ttgs = ttgs.substring(LIFETIME_LENGTH, ttgs.length());
		setLifeTime(ss);

	}


	private void setAuthenticator(String ad) {
		this.ad = ad;
	}


	public String getSrc_id() {
		return src_id;
	}


	public void setSrc_id(String src_id) {
		this.src_id = src_id;
	}


	public String getDes_id() {
		return des_id;
	}


	public void setDes_id(String des_id) {
		this.des_id = des_id;
	}


	public String getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}


	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}

	public String getLifeTime() {
		return lifeTime;
	}


	public void setLifeTime(String lifeTime) {
		this.lifeTime = lifeTime;
	}

}
