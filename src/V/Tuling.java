package V;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Tuling {
	
	public String getServer(String message){
		try {
			message = URLEncoder.encode(message,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String result = new String();
		String info = "http://www.tuling123.com/openapi/api?key=9d683e3abf2a42508ceaa36d0e7c0ea1&info=" + message + "&userid=1580325417";
		try {
			URL url = new URL(info);
			URLConnection connection = url.openConnection();
			HttpURLConnection conn = (HttpURLConnection)connection;
			conn.setRequestMethod("POST");
			connection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
			String line = reader.readLine();
			while(line != null){
				result += line;
				line = reader.readLine();
			}
			reader.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
