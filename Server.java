package oop_1;

import java.awt.HeadlessException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
//import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Server {
	   private ServerSocket server; // server socket
	   private Socket connection; // connection to client            Socket即client sockets
	   private int count=0;	   
	   public Server() throws IOException {
		   runSever();
	   }
	   
	   public void runSever() throws IOException {
		   server = new ServerSocket( 13131, 100 ); // create ServerSocket
		   while ( true ) 
		   {
			   System.out.println("Wating for connection\n");
			   connection = server.accept();
			   count++;
			   new CreateServerThread(connection,"Thread"+count);
		   }
	   }
	public static void main(String[] args) {
		DataProcessing.disconnectFromDB();
		try {
			Server sever=new Server();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class CreateServerThread extends Thread{
	private String uploadpath="D:\\Build\\JavaTest\\upload\\";
	private Socket client;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private String Thread;
	public CreateServerThread(Socket c,String s) throws IOException{
	    this.client=c;
	    Thread=s;
	    input=new ObjectInputStream(client.getInputStream());
	    output=new ObjectOutputStream(client.getOutputStream());
	    System.out.println( Thread+":" + Thread + " received from: " + client.getInetAddress().getHostName() );
	    System.out.println( Thread+":" + Thread + "Got I/O streams\n");
	    start();
	}
	
	public void run() {
				String message=null;     
			      do // process messages sent from client 循环展示（接收）客户端的信息，
			      { 
			         try // read message and display it
			         {
			            message = ( String ) input.readObject(); // read new message input与客户端socket连接
			            System.out.print("\n");
			            displayMessage( message ); // display message
			            
			           if(message.equals("CLIENT_LOGIN")) {//------------------------------------------登录
			        	   String[] User=(String[])input.readObject();
			        	   if (DataProcessing.hadname(User[0])) {                           //----验证用户名
			        		   sendMessage("YES");
								if (DataProcessing.searchUser(User[0], User[1])!=null) { 
									User u=DataProcessing.searchUser(User[0], User[1]);
									displayMessage(User[0]);
									sendMessage("SERVER_LOGIN");
									sendUser(u);						
								}
								else {
									sendMessage("LOGIN_FAILURE");
								}
			        	   }
			        	   else sendMessage("NULL");
			        	   
			           }else if(message.equals("CLIENT_USER_ADD")) {//------------------------------添加用户
			        	   String[] User=(String[])input.readObject();
			        	   if(DataProcessing.hadname(User[0])) {
			        		   sendMessage("Name had exists");
			        	   }else {
			        		   sendMessage("Allow");
			        		   if(DataProcessing.insertUser(User[0], User[1],User[2])) {	        		   
				        		   sendMessage("SERVER_USER_ADD");
								}
								else sendMessage("ADD_USER_FAILURE");
			        	   }

			           }else if(message.equals("CLIENT_USER_MOD")) {//------------------------------修改用户
			        	   String[] User=(String[])input.readObject();
			        	   
			        	   if(DataProcessing.updateUser(User[0], User[1],User[2])) {	        		   
			        		   sendMessage("SERVER_USER_MOD");
							}
							else sendMessage("MOD_USER_FAILURE");
			        	   
			           }else if(message.equals("CLIENT_USER_DEL")){//-------------------------------删除用户
			        	   String name=(String)input.readObject();
			        	   
			        	   if(DataProcessing.deleteUser(name)) {	        		   
			        		   sendMessage("SERVER_USER_DEL");
							}
							else sendMessage("DEL_USER_FAILURE");
			        	   
			           }else if(message.equals("CLIENT_DOC_UP")) {//--------------------------------上传文件
			        	   String[] fileData=(String[])input.readObject();//2.接收文件信息
			        	   
			        	   if(DataProcessing.searchDoc(fileData[0]).getID()!=null) {
			        		   sendMessage("ID had exists");			        		   
			        	   }else {
			        		   sendMessage("Allow");
			        		   User u=DataProcessing.searchUser(fileData[1]);
				        	   u.changname(fileData[3]);//文件名修改
				        	   
				        	   File f=new File(u.upload+u.Final);
				        	   BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(f));
				        	   byte[] file=(byte[])input.readObject();//3.接收文件内容
				        	   bos.write(file);//文件内容写入f的路径中
				        	   bos.close();
				        	   
				        	   Timestamp timestamp= new Timestamp(System.currentTimeMillis());	        	  
				        	   if(DataProcessing.insertDoc(fileData[0],fileData[1],timestamp,fileData[2],u.Final)) {//4.操作数据库，反馈信息到客户端
				        		   sendMessage("SERVER_DOC_UP");	
				        		   sendFN(u.Final);//发送修改后的文件名到客户端
				        	   }else sendMessage("SERVER_DOC_UP_FAILURE");
			        	   }
			        	   
			        	  
			        	   
			           }else if(message.equals("CLIENT_DOC_DOWN")) {//------------------------------下载文件
			        	    String ID=(String) input.readObject();
			        	    
			        	    if(DataProcessing.searchDoc(ID)!=null) {
			        	    	
			        	    	sendMessage("SERVER_DOC_DOWM");
			        	    	
			        	    	Doc dgz=DataProcessing.searchDoc(ID);
								String filename=dgz.getFilename();
								File f1=new File(uploadpath+filename);
								BufferedInputStream bis=new BufferedInputStream(new FileInputStream(f1));
								int count=bis.available();
								byte[] file=new byte[count];
								bis.read(file);
								sendS(file);
								bis.close();
			        	    }else sendMessage("SERVER_DOC_DOWN_FAILURE");
			        	    
							
			           }else if(message.equals("CLIENT_SELF_MOD")) {//------------------------------修改本人密码
			        	   
			        	   String[] User=(String[])input.readObject();
			        	   System.out.println(User[0]+User[1]+User[2]);
			        	   if(DataProcessing.updateUser(User[0],User[1],User[2])) {
			        		   sendMessage("SERVER_SELF_MOD");	        		   
			        	   }
			        	   else {
			        		   sendMessage("SELF_MOD_FAILURE");
			        	   }
			           }else if(message.equals("open_userframe")) {//----------------------------用户表格	        	   
			        	   String[] columnNames = {"用户名","口令","角色"};   //列名
			       		   String[][] Value= {};
			       		   DefaultTableModel T1=new DefaultTableModel(Value,columnNames);
			        	   for(Enumeration<User> e  = DataProcessing.getAllUser();e.hasMoreElements();) {//-------------在table加入用户信息
			       			User dgz=e.nextElement();
			       			String s1=dgz.getName();
			       			String s2=dgz.getPassword();
			       			String s3=dgz.getRole();
			       			String[] ss= {s1,s2,s3};	       	
			       			T1.insertRow(0, ss);
			                }
			        	   sendTM(T1);        	   
			           }else if(message.equals("open_docframe")) {//-----------------------------文件表格
			        	   String[] columnNames = {"档案号","创建者","时间","文件名","描述"};   //列名
			        		String[][] Value= {};
			        		DefaultTableModel T1=new DefaultTableModel(Value,columnNames);
			        		for(Enumeration<Doc> e  = DataProcessing.getAllDocs();e.hasMoreElements();) {//-------------在table加入用户信息
			        			Doc dgz=e.nextElement();
			        			String s1=dgz.getID();
			        			String s2=dgz.getCreator();
			        			String s3=dgz.getTimestamp().toString();
			        			String s4=dgz.getFilename();
			        			String s5=dgz.getDescription();
			        			String[] ss= {s1,s2,s3,s4,s5};
			        			T1.insertRow(0, ss);
			        		}
			        		sendTM(T1);
			           }
			         } // end try
			         catch ( ClassNotFoundException | IOException | HeadlessException | SQLException classNotFoundException ) 
			         {
			            displayMessage( "\nUnknown object type received" );
			         } // end catch

			      } while ( !message.equals( "TERMINATE" ) );
			      closeConnection();//关闭流与socket
	}
	private void sendUser(User u) {//发送用户实例
		try {
			output.writeObject(u);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendS(byte[] file) {//发送文件内容
		try {
			output.writeObject(file);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendTM(DefaultTableModel t) {//发送表格模型
		try {
			output.writeObject(t);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendFN(String FN) {
		try {
			output.writeObject(FN);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendMessage(String message) {
		try // send object to client
	      {
			output.writeObject( message );//output到客户端，内容为：server+文本框输入的字符串，将会再客户端展示
	         output.flush(); // flush output to client 确保数据不丢失，全写入output里面
	         displayMessage( "SERVER>>> " + message );//然后在server客户端内再展示一下
	      } // end try
	      catch ( IOException ioException ) 
	      {
	         System.out.println( "\nError writing object" );
	      } // end catch
	}

	private void displayMessage(String message) {
		System.out.print(Thread+":  ");
		System.out.println(message);
	}

	private void closeConnection() {
		displayMessage( "\nTerminating connection\n" );
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