package oop_1;

import java.net.*;
import java.sql.Timestamp;
import java.util.Scanner;

import javax.swing.table.DefaultTableModel;

import java.io.*;

public class Client {
	private Socket client;
	private ObjectOutputStream output; 
	private ObjectInputStream input;
	private String message = ""; // message from server
	private String chatServer; // host server for this application
	
	public Client(String host) {
		chatServer = host;
		runClient();
	}
	
	public void runClient() 
	   {
	      try // connect to server, get streams, process connection
	      {
	         connectToServer(); // create a Socket to make connection
	         getStreams(); // get the input and output streams
	         //processConnection(); // process connection
	      } // end try
	      catch ( EOFException eofException ) 
	      {
	         displayMessage( "\nClient terminated connection" );
	      } // end catch
	      catch ( IOException ioException ) 
	      {
	         ioException.printStackTrace();
	      } // end catch
	      finally 
	      {
	         //closeConnection(); // close connection
	      } // end finally
	   } // end method runClient
	
	
	public void connectToServer() throws UnknownHostException, IOException {
		System.out.println("Attempting connection\n");
		client = new Socket(InetAddress.getByName( chatServer ), 13131);
		System.out.println("Connected to: " + client.getInetAddress().getHostName());
	}
	
	public void getStreams() throws IOException {
		output=new ObjectOutputStream(client.getOutputStream());
		output.flush();
		input=new ObjectInputStream(client.getInputStream());
	}
	
	public String processConnection() throws IOException {//接收服务器端反馈的信息
		
		try {
			message = ( String ) input.readObject();
			System.out.println(message);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return message;
		
	}
	
	public User receiveUser() throws ClassNotFoundException, IOException {//接收用户实例
		User user=(User)input.readObject();
		return user;
	}
	
	public DefaultTableModel receiveT() throws ClassNotFoundException, IOException {//接收表格模型
		DefaultTableModel t=(DefaultTableModel)input.readObject();
		return t;
	}
	
	public byte[] receiveS() throws ClassNotFoundException, IOException {//接收文件
		byte[] file=(byte[])input.readObject();
		return file;
	}
	
	public void sendMessage( String message )//发送操作请求
	   {
	      try 
	      {
	         //output.writeObject( "CLIENT>>> " + message );
	    	 output.writeObject(message);//output到客户端，内容为：server+文本框输入的字符串，将会再客户端展示
	         output.flush(); // flush data to output
	         System.out.println( "\nCLIENT>>> " + message );
	      } 
	      catch ( IOException ioException )
	      {
	    	  System.out.println( "\nError writing object" );
	      } 
	   } 
	
	public void sendsingleData(String d) {//删除用户、删除文件、下载文件
		try {
			output.writeObject(d);
			output.flush();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendUserData(String name,String password) {//登录传输
		String[] buf= {name,password};
		try {
			output.writeObject(buf);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendUserData(String name,String password,String role) {//添加用户、修改用户、用户修改密码
		String[] buf= {name,password,role};
		try {
			output.writeObject(buf);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendByte(byte[] file) {//发送文件信息
		try {
			output.writeObject(file);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendDocData(String ID, String creator, String description, String filename) {//上传文件
		String[] buf= {ID,creator,description,filename};
		try {
			output.writeObject(buf);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void displayMessage(String message) {
		System.out.println(message);
	}
	
	public void closeConnection()  {
		System.out.println("\nClosing connection" );
		try 
	      {
	         output.close(); 
	         input.close(); 
	         client.close(); 
	      } 
	      catch ( IOException ioException ) 
	      {
	         ioException.printStackTrace();
	      } 
	}
}
