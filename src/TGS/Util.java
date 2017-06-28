package TGS;

public class Util {
	public static String toBinaryString(String message){
		String result = new String();
		for(int i = 0 ; i < message.length(); i ++){
			int num = Integer.valueOf(message.charAt(i));
			String temp = Integer.toBinaryString(num);
			if(temp.length() < 8){
				int length = temp.length();
				for(int j = 0; j < 8-length; j ++){
					temp = "0" + temp;
				}
			}
			result += temp;
		}
		return result;
	}
	
	public static String binaryToString(String message){
		String result = new String();
		for(int i = 0; i < message.length();){
			String temp = new String();
			for(int j = 0; j < 8; j ++){
				temp += message.charAt(i);
				i ++;
			}
			int num = Integer.parseInt(temp, 2);
			result += (char)(num);
		}
		return result;
	}
}
