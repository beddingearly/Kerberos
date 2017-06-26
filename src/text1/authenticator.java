package text1;

public class authenticator {
	private String src_id;
	private String timeStamp;
	private String authenticator;

	private final static int ID_LENGTH = 8;
	private final static int TIMESTAMP_LENGTH = 13;
	private final static int AUTHENTICATOR_LENGTH = 46;
	
	public void divTicketTGS(String TTGS){

		String ttgs = "";//DES() to ttgs;

		String ss = "";
		
		ss = ttgs.substring(0, ID_LENGTH);//8 IDc
		ttgs = ttgs.substring(ID_LENGTH, ttgs.length());
		setSrc_id(ss);
		
		
		ss = ttgs.substring(0, AUTHENTICATOR_LENGTH);//29 ADc
		ttgs = ttgs.substring(AUTHENTICATOR_LENGTH, ttgs.length());
		setAuthenticator(ss);
		
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

	public String getAuthenticator() {
		return authenticator;
	}

	public void setAuthenticator(String authenticator) {
		this.authenticator = authenticator;
	}
}
