package com.java.packet.java;

public class DataPacket {
	// 各个字段的长度
	private final int HEAD_LENGTH = 7;
	private final int ID_LENGTH = 8;
	private final int TIMESTAMP_LENGTH = 13;
	private final int LIFETIME_LENGTH = 4;
	private final int KEY_LENGTH = 64;
	private final int AUTHENTICATOR_LENGTH = 46;

	// 包头具有的字段，按照定义的顺序拼接
	private int src_id_mark;
	// 源ID标志位。如果为0，表示数据包中不包含源ID；如果为1，表示数据包中包含源ID。
	private int des_id_mark;
	// 目的ID标志位。如果为0，表示数据包中不包含目的ID；如果为1，表示数据包中包含目的ID。
	private int timeStamp_mark;
	// 时间戳标志位。如果为0，表示数据包中不包含时间戳；如果为1，表示数据报中包含时间戳。
	private int lifeTime_mark;
	// 生命周期标志位。如果为0，表示数据包中不包含生命周期；如果为1，表示数据报中包含生命周期。
	private int authenticator_mark;
	// authenticator标志位。如果为0，表示数据包中不包含authenticator；如果为1，表示数据报中包含authenticator。
	private int key_mark;
	// 对称密钥标志位。如果为0，表示数据包中不包含密钥；如果为1，表示数据报中包含密钥。
	private int ticket_mark;
	// 票据标志位。如果为0，表示数据包中不包含票据；如果为1，表示数据报中包含票据。

	// 数据部分具有的字段，按照定义的顺寻拼接
	private String src_id = new String();
	// 源ID。
	private String des_id = new String();
	// 目的ID。
	private String timeStamp = new String();
	// 时间戳。
	private String lifeTime = new String();
	// 生命周期
	private String authenticator = new String();
	// authenticator
	private String key = new String();
	// 对称密钥
	private String ticket = new String();
	// 票据

	public void parseMessage(String msg) {
		String head = msg.substring(0, HEAD_LENGTH);
		String data = msg.substring(HEAD_LENGTH, msg.length());

		// 如果数据包中包含源ID，解析出源ID。
		if (head.charAt(0) == '1') {
			src_id = data.substring(0, ID_LENGTH);
			data = data.substring(ID_LENGTH, data.length());
		}
		// 如果数据包中包含目的ID，解析出目的ID。
		if (head.charAt(1) == '1') {
			des_id = data.substring(0, ID_LENGTH);
			data = data.substring(ID_LENGTH, data.length());
		}
		// 如果数据包中包含时间戳，解析出时间戳。
		if (head.charAt(2) == '1') {
			timeStamp = data.substring(0, TIMESTAMP_LENGTH);
			data = data.substring(TIMESTAMP_LENGTH, data.length());
		}
		// 如果数据包中包含生命周期，解析出生命周期。
		if (head.charAt(3) == '1') {
			lifeTime = data.substring(0, LIFETIME_LENGTH);
			data = data.substring(LIFETIME_LENGTH, data.length());
		}
		// 如果数据包中包含包含authenticator，解析出authenticator。
		if (head.charAt(4) == '1') {
			authenticator = data.substring(0, AUTHENTICATOR_LENGTH);
			data = data.substring(AUTHENTICATOR_LENGTH, data.length());
		}
		// 如果数据包中包含密钥，解析出密钥
		if (head.charAt(5) == '1') {
			key = data.substring(0, KEY_LENGTH);
			data = data.substring(KEY_LENGTH, data.length());
		}
		// 如果数据包中包含票据，解析出票据。
		if (head.charAt(6) == '1') {
			ticket = data;
		}

	}

	public int getSrc_id_mark() {
		return src_id_mark;
	}

	public void setSrc_id_mark(int src_id_mark) {
		this.src_id_mark = src_id_mark;
	}

	public int getDes_id_mark() {
		return des_id_mark;
	}

	public void setDes_id_mark(int des_id_mark) {
		this.des_id_mark = des_id_mark;
	}

	public int getTimeStamp_mark() {
		return timeStamp_mark;
	}

	public void setTimeStamp_mark(int timeStamp_mark) {
		this.timeStamp_mark = timeStamp_mark;
	}

	public int getLifeTime_mark() {
		return lifeTime_mark;
	}

	public void setLifeTime_mark(int lifeTime_mark) {
		this.lifeTime_mark = lifeTime_mark;
	}

	public int getAuthenticator_mark() {
		return authenticator_mark;
	}

	public void setAuthenticator_mark(int authenticator_mark) {
		this.authenticator_mark = authenticator_mark;
	}

	public int getKey_mark() {
		return key_mark;
	}

	public void setKey_mark(int key_mark) {
		this.key_mark = key_mark;
	}

	public int getTicket_mark() {
		return ticket_mark;
	}

	public void setTicket_mark(int ticket_mark) {
		this.ticket_mark = ticket_mark;
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

	public String getLifeTime() {
		return lifeTime;
	}

	public void setLifeTime(String lifeTime) {
		this.lifeTime = lifeTime;
	}

	public String getAuthenticator() {
		return authenticator;
	}

	public void setAuthenticator(String authenticator) {
		this.authenticator = authenticator;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public int getHEAD_LENGTH() {
		return HEAD_LENGTH;
	}

	public int getID_LENGTH() {
		return ID_LENGTH;
	}

	public int getTIMESTAMP_LENGTH() {
		return TIMESTAMP_LENGTH;
	}

	public int getLIFETIME_LENGTH() {
		return LIFETIME_LENGTH;
	}

	public int getKEY_LENGTH() {
		return KEY_LENGTH;
	}

	public int getAUTHENTICATOR_LENGTH() {
		return AUTHENTICATOR_LENGTH;
	}
}
