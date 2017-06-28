package text3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class linksql {	
	/**
	 * AS连接数据库并注册用户
	 * @param username   新用户名
	 * @param cipher     密码
	 */
	public void Linksql(String username,String cipher){
		String driver = "com.mysql.jdbc.Driver";
		//URL指向要访问的数据库名word
		String url = "jdbc:mysql://127.0.0.1:3306/word?";
		String user = "root";
		String password = "love523023";  
		Connection conn = null;
		int h;
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,password);
			if(!conn.isClosed())
				 h=0;
			     Statement statement = conn.createStatement();
			     String sql =  "insert into persons(username,cipher) values('"+username+"','"+cipher+"')";
			     int rs = statement.executeUpdate(sql);      
		}catch(ClassNotFoundException e){
			
			e.printStackTrace();  
		}catch(SQLException e){
			e.printStackTrace();  
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	
	}
	
	/**
	 * AS认证函数
	 * @param username   用户名
	 * @param cipher     密码
	 * @return 1 为认证成功     0为认证失败
	 */
	public int Authen(String username){
		String driver = "com.mysql.jdbc.Driver";
		//URL指向要访问的数据库名word
		String url = "jdbc:mysql://127.0.0.1:3306/word?";
		String user = "root";
		String password = "love523023";  
		Connection conn = null;
		String name = null;
		String mi =null;
		int h;
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,password);
			if(!conn.isClosed())
				 h=0;
			     Statement statement = conn.createStatement();
			     String sql =  "select * from persons where username ='"+username+"'";
			     ResultSet rs = statement.executeQuery(sql);
			     while(rs.next())  {
			    	 name= rs.getString("username");
			    	 System.out.println(name);
			    	 if(name.equals(username)){
			    		//System.out.println(username + "认证成功！");
			    		return 1;
			    	 }else{
			    		 //System.out.println(username + "认证失败！");
			    		 return 0;
			    	 }
			    	 
			     }
			     rs.close();       
			      
		}catch(ClassNotFoundException e){
			
			e.printStackTrace();  
		}catch(SQLException e){
			e.printStackTrace();  
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		return 0;
	
	}

}
