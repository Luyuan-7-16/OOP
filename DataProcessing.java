package oop_1;

import java.util.Enumeration;
import java.util.Hashtable;
import java.io.File;
import java.io.Serializable;
import java.sql.*;

public  class DataProcessing implements Serializable{

	private static boolean connectToDB=false;
	
	static Hashtable<String, User> users;//----------------------------用户列表
	static Hashtable<String, Doc> docs;//------------------------------文件列表
	static String upload="D:\\Build\\JavaTest\\upload\\";
	private static String driverName="com.mysql.cj.jdbc.Driver";                                                       // 加载数据库驱动类
	private static String URL="jdbc:mysql://localhost:3306/document?serverTimezone=UTC&characterEncoding=utf-8";       // 声明数据库的URL
	private static String USER="root";                                                                                 // 数据库用户
	private static String PASSWORD="123456";
	//private static Connection con;      //-----连接
	//private static PreparedStatement ps;//-----sql执行用的东西
	//private static ResultSet rs;        //-----结果集
	private static String selectUserSql="select * from user_info";
	private static String selectUserbynameSql="select * from user_info where username=?";
	private static String insertUserSql ="insert into user_info values (?,?,?)";
	private static String updateUserSql ="update user_info set role=?,password=? where username=?";
	private static String deleteUserSql ="delete from user_info where username=?";
	private static String selectDocSql="select * from doc_info";
	private static String selectDocbyIDSql="select * from doc_info where ID=?";
	private static String insertDocSql="insert into doc_info values (?,?,?,?,?)";
	//private static String deleteDocSql="delete from doc_info where ID=?";

	static {
		users = new Hashtable<String, User>();
		//users.put("jack", new Operator("jack","123","operator"));
		//users.put("rose", new Browser("rose","123","browser"));
		//users.put("kate", new Administrator("kate","123","administrator"));
		Init();
		
		//Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
		docs = new Hashtable<String,Doc>();
		//docs.put("0001",new Doc("0001","jack",timestamp,"Doc Source Java","Doc.java"));
				
	}
	
	public static  void Init(){
	}
	
	public static Doc searchDoc(String ID) throws SQLException { //-----------------------在数据库里面按ID找文件
		String tempID=null;
		String tempc=null;
		Timestamp tempt=null;
		String tempd=null;
		String tempf=null;
		Doc temp=null;
		if(connectToDB) {
			try {
				Connection con;      //-----连接
				PreparedStatement ps;//-----sql执行用的东西
				ResultSet rs;        //-----结果集
			    Class.forName(driverName);		// 加载数据库驱动类
			    con=DriverManager.getConnection(URL,USER,PASSWORD);   // 建立数据库连接
				ps=con.prepareStatement(selectDocbyIDSql);
				ps.setString(1, ID);
			    rs=ps.executeQuery();
				while (rs.next()){
					tempID=rs.getString(1);
					tempc=rs.getString(2);
					tempt=rs.getTimestamp(3);
					tempd=rs.getString(4);
					tempf=rs.getString(5);
				}			
				rs.close();
				ps.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			temp=new Doc(tempID,tempc,tempt,tempd,tempf);
			return temp;
		}
		else return null;
	}
	
	public static Enumeration<Doc> getAllDocs() throws SQLException{//-----------------------
		docs.clear();
		String tempID=null;
		String tempc=null;
		Timestamp tempt=null;
		String tempd=null;
		String tempf=null;
		if(connectToDB) {
			try {
				Connection con;
				PreparedStatement ps;
				ResultSet rs;
				Class.forName(driverName);
				con=DriverManager.getConnection(URL, USER, PASSWORD);
				ps=con.prepareStatement(selectDocSql);
				rs=ps.executeQuery();
				while(rs.next()) {
					tempID=rs.getString(1);
					tempc=rs.getString(2);
					tempt=rs.getTimestamp(3);
					tempd=rs.getString(4);
					tempf=rs.getString(5);
					docs.put(tempID, new Doc(tempID,tempc,tempt,tempd,tempf));
				}
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
		Enumeration<Doc> e  = docs.elements();
		return e;
	} 
	
	public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename) throws SQLException{ //在哈希表里面加入文件
		if(connectToDB) {
			try {
				Connection con;
				PreparedStatement ps;
				Class.forName(driverName);
				con=DriverManager.getConnection(URL,USER,PASSWORD);
				ps=con.prepareStatement(insertDocSql);
				ps.setString(1, ID);
				ps.setString(2, creator);
				ps.setTimestamp(3, timestamp);
				ps.setString(4, description);
				ps.setString(5, filename);
				ps.executeUpdate();
				ps.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}			
			return true;
		}else {
			return false;
		}
	} 
	
	public static boolean hadname(String name) {
		String tempn=null;
		if(connectToDB) {
			try {
				Connection con;      //-----连接
				PreparedStatement ps;//-----sql执行用的东西
				ResultSet rs;        //-----结果集
			    Class.forName(driverName);		// 加载数据库驱动类
			    con=DriverManager.getConnection(URL,USER,PASSWORD);   // 建立数据库连接
				ps=con.prepareStatement(selectUserbynameSql);
				ps.setString(1, name);
			    rs=ps.executeQuery();
				while (rs.next()){
					tempn=rs.getString(1);//如果数据库里面有这个人，tempn不为null
				}			
				rs.close();
				ps.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			if(tempn!=null) return true;
			else return false;
		}else return false;		
	}
	
	public static User searchUser(String name) throws SQLException{  //--------------------------------------按名字搜索用户		
		String tempn=null;
		String tempp=null;
		String tempr=null;
		User temp=null;
		if(connectToDB) {
			try {
				Connection con;      //-----连接
				PreparedStatement ps;//-----sql执行用的东西
				ResultSet rs;        //-----结果集
			    Class.forName(driverName);		// 加载数据库驱动类
			    con=DriverManager.getConnection(URL,USER,PASSWORD);   // 建立数据库连接
				ps=con.prepareStatement(selectUserbynameSql);
				ps.setString(1, name);
			    rs=ps.executeQuery();
				while (rs.next()){
					tempn=rs.getString(1);
					tempp=rs.getString(2);
					tempr=rs.getString(3);
				}			
				rs.close();
				ps.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			if(tempn==null) return null;
			if(tempr.equalsIgnoreCase("administrator"))
				temp=new Administrator(tempn,tempp,tempr);
			else if(tempr.equalsIgnoreCase("operator"))
				temp=new Operator(tempn,tempp,tempr);
			else if(tempr.equalsIgnoreCase("browser"))
				temp=new Browser(tempn,tempp,tempr);
			return temp;
		}
		else return null;
	}
	
	public static User searchUser(String name, String password) throws SQLException {  //--------------------------验证用户名和密码
		String tempn=null;
		String tempp=null;
		String tempr=null;
		User temp=null;
		if(connectToDB) {
			try {
				Connection con;      //-----连接
				PreparedStatement ps;//-----sql执行用的东西
				ResultSet rs;        //-----结果集
			    Class.forName(driverName);		// 加载数据库驱动类
			    con=DriverManager.getConnection(URL,USER,PASSWORD);   // 建立数据库连接
				ps=con.prepareStatement(selectUserbynameSql);
				ps.setString(1, name);
			    rs=ps.executeQuery();
				while (rs.next()){
					tempn=rs.getString(1);
					tempp=rs.getString(2);
					tempr=rs.getString(3);
				}			
				rs.close();
				ps.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
		if(tempn==null) return null;
		if(password.equals(tempp)) {
			if(tempr.equalsIgnoreCase("administrator"))
				temp=new Administrator(tempn,tempp,tempr);
			else if(tempr.equalsIgnoreCase("operator"))
				temp=new Operator(tempn,tempp,tempr);
			else if(tempr.equalsIgnoreCase("browser"))
				temp=new Browser(tempn,tempp,tempr);
			return temp;
		}else return null;
	}
	
	public static Enumeration<User> getAllUser() throws SQLException{         //-------------------------------列出用户列表
		users.clear();
		String tempn=null;
		String tempp=null;
		String tempr=null;
		if(connectToDB) {
			try {
				Connection con;
				PreparedStatement ps;
				ResultSet rs;
				Class.forName(driverName);
				con=DriverManager.getConnection(URL, USER, PASSWORD);
				ps=con.prepareStatement(selectUserSql);
				rs=ps.executeQuery();
				while(rs.next()) {
					tempn=rs.getString(1);
					tempp=rs.getString(2);
					tempr=rs.getString(3);
					//System.out.println(rs.getString(1)+":"+rs.getString(2)+":"+rs.getString(3));
					if(tempr.equalsIgnoreCase("administrator"))
						users.put(tempn, new Administrator(tempn,tempp,tempr));
					else if(tempr.equalsIgnoreCase("operator"))
						users.put(tempn, new Operator(tempn,tempp,tempr));
					else if(tempr.equalsIgnoreCase("browser"))
						users.put(tempn, new Browser(tempn,tempp,tempr));
				}
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
		Enumeration<User> e  = users.elements();//返回一个Enumeration类型的东西，它指向users的第一个元素，利用它提供的方法可以遍历users这个哈希表
		return e;
	}
	
	
	
	public static boolean updateUser(String name, String password, String role) throws SQLException{//-----------------更新用户信息(角色，密码)
		if(connectToDB) {
			try {
				Connection con;
				PreparedStatement ps;
				Class.forName(driverName);
				con=DriverManager.getConnection(URL,USER,PASSWORD);
				ps=con.prepareStatement(updateUserSql);
				ps.setString(1, role);
				ps.setString(2, password);
				ps.setString(3, name);
				ps.executeUpdate();
				ps.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}			
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean insertUser(String name, String password, String role) throws SQLException{//----------增加用户
		if(connectToDB) {
			try {
				Connection con;
				PreparedStatement ps;
				Class.forName(driverName);
				con=DriverManager.getConnection(URL,USER,PASSWORD);
				ps=con.prepareStatement(insertUserSql);
				ps.setString(1, name);
				ps.setString(2, password);
				ps.setString(3, role);
				ps.executeUpdate();
				ps.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}			
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean deleteUser(String name) throws SQLException{//---------------删除用户
		if(connectToDB) {
			try {
				Connection con;
				PreparedStatement ps;
				Class.forName(driverName);
				con=DriverManager.getConnection(URL,USER,PASSWORD);
				ps=con.prepareStatement(deleteUserSql);
				ps.setString(1, name);
				ps.executeUpdate();
				ps.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}			
			return true;
		}else {
			return false;
		}
	}	
	public static boolean deleteDoc(String ID) throws SQLException{//---------------删除文件	
		if (docs.containsKey(ID)){
			Doc dgz=DataProcessing.searchDoc(ID);
			File f=new File(upload+dgz.getFilename());
			f.delete();
			docs.remove(ID);
			return true;
		}else
			return false;
		
	}
            
	public static void disconnectFromDB() {//----------------------------------------是否连接到数据库
		try {
			Connection con;                                        //-----连接
			PreparedStatement ps;                                  //-----sql执行用的东西
			ResultSet rs;                                          //-----结果集
			Class.forName("com.mysql.cj.jdbc.Driver");		       // 加载数据库驱动类
			con=DriverManager.getConnection(URL,USER,PASSWORD);    // 建立数据库连接
			con.close();
			connectToDB=true;
		}catch(ClassNotFoundException e ){
	    	System.out.println("数据驱动错误");
	    	e.printStackTrace();
	    }catch(SQLException e){
	    	System.out.println("数据库错误");
	    	e.printStackTrace();
	    }
   }           

	
	/*public static void main(String[] args) throws SQLException {		
		DataProcessing.disconnectFromDB();
		User dgz;
		for(Enumeration<User> e  = DataProcessing.getAllUser();e.hasMoreElements();) {
			dgz=e.nextElement();
			System.out.println(dgz.getName()+"\t"+dgz.getPassword()+"\t"+dgz.getRole());
		    }
		System.out.println("5");
		Doc DGZ;
		for(Enumeration<Doc> e  = DataProcessing.getAllDocs();e.hasMoreElements();) {
			DGZ=e.nextElement();
			System.out.println(DGZ.getID()+"\t"+DGZ.getCreator()+"\t"+DGZ.getTimestamp().toString());
		    }
	}*/
	
}
