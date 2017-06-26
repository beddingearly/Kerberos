package text1;

public class ticket {
	
	private final int HEAD_LENGTH = 7;
	private String src_id;
	private String des_id;
	private String timeStamp;
	private String key;
	private String authenticator;
	private String lifeTime;

	private final static int ID_LENGTH = 8;
	private final static int TIMESTAMP_LENGTH = 13;
	private final static int LIFETIME_LENGTH = 4;
	private final static int KEY_LENGTH = 64;
	private final static int AUTHENTICATOR_LENGTH = 46;

	
	public void divTicketTGS(String TTGS){
		//DES() to ttgs;
		String ttgs = "";

		String ss = "";
		ss = ttgs.substring(0, KEY_LENGTH);//8 Kc,tgs
		ttgs = ttgs.substring(KEY_LENGTH, ttgs.length());
		setKey(ss);
		
		ss = ttgs.substring(0, ID_LENGTH);//8 IDc
		ttgs = ttgs.substring(ID_LENGTH, ttgs.length());
		setSrc_id(ss);
		
		
		ss = ttgs.substring(0, AUTHENTICATOR_LENGTH);//29 ADc
		ttgs = ttgs.substring(AUTHENTICATOR_LENGTH, ttgs.length());
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


	private void setAuthenticator(String authenticator) {
		this.authenticator = authenticator;
		
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
