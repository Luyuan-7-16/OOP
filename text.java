package oop_1;

import java.util.Enumeration;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;

//import com.sun.media.sound.Toolkit;

import java.awt.Dimension;
import java.io.*;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class text {
	static String Final=null;
	static int j;
	public static void main(String args[]) throws IOException{
		/*File f=new File("D:\\Build\\JavaTest");
		System.out.println("f name   "+f.getName());
		System.out.println("is Exists   "+f.exists());	
		System.out.println("Creat PFile   "+f.mkdir());
		System.out.println("Creat File   "+f.createNewFile());
		System.out.println("is file   "+f.isFile());
		System.out.println("is D   "+f.isDirectory());
		System.out.println("f Lenght   "+f.length());
		System.out.println("f AP   "+f.getAbsolutePath());
		System.out.println("f P   "+f.getPath());
		System.out.println("f lasrChange   "+f.lastModified());*/
		
		
//	    1574662198695
		/*System.out.println();
		System.out.println();
		File f2=new File("D:\\Build\\JavaTest.txt");
		System.out.println(f2.getName());
		System.out.println(f2.exists());
		System.out.println(f2.mkdir());
		System.out.println(f2.createNewFile());*/
		
		//--------------------------------从upload文件夹下载文件---------------------------------------------
		/*byte buffer[] = new byte[1024];
		String upload="D:\\Build\\JavaTest\\upload\\";
		String download="D:\\Build\\JavaTest\\download\\";
		String filename=null;
		Scanner cin=new Scanner(System.in);
		System.out.print("请输入文件名:");
		filename=cin.nextLine();
		File f1=new File(upload+filename);//---------------------------------------f1是文件库里的文件
		File f2=new File(download+filename);//-------------------------------------f2是下载到本地文件
		if(!f2.exists()) {
			f2.createNewFile();//--------------------------------------如果f2不存在则下载（创建一个文件，后面写入数据）
		}
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream(f1));
		BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(f2));
		int c=0;
		while((c=bis.read(buffer))!=-1) {
			for(int i=0;i<c;i++) {
				bos.write(buffer[i]);
			}
		}
		bis.close();
		bos.close();*/
		//File f1=new File("D:\\Build\\JavaTest\\123.txt");
		//File f2=new File("D:\\Build\\JavaTest\\upload\\1223.txt");
		//File f2=new File("D:\\Build\\JavaTest\\"+f1.getName()+".txt");
        //System.out.println(f1.getName());
	    //f2.createNewFile();
        
	    
	    
	    //StringTokenizer fn=new StringTokenizer("Doc.java","(");
	    //int n=fn.countTokens();//--可以调用next Token的次数，如果有3个分隔符就是4次
		//for(int i=0;fn.hasMoreTokens();i++) if(i==0) head=fn.nextToken("(");else fn.nextToken();
		
		
		//System.out.println(Final);
		
		//String[] ss= {"123.mp4","123(1).mp4","123(4).mp4"};
		/*for(int i=0;i<ss.length;i++) {
			if(Final.equals(ss[i])) {
				j++;
				Final=head+"("+j+")"+"."+tail;
			}
		}*/
		//updataname(head,tail,Final,j);
        
        /*String origion="Doc.java";
        String head=null,tail=null;
		//String Final=null;
		
		Doc dgz;
		for(Enumeration<Doc> e=DataProcessing.docs.elements();e.hasMoreElements();) {
			dgz=e.nextElement();
			if(origion.equals(dgz.getFilename())) {
				StringTokenizer fn2=new StringTokenizer(origion,"(.");
				int m=fn2.countTokens();//--可以调用next Token的次数，如果有3个分隔符就是4次
				for(int i=0;fn2.hasMoreTokens();i++) if(i==0) head=fn2.nextToken("(.");else if(i==m-1) tail=fn2.nextToken(".");else fn2.nextToken(".");				
				Final=head+"."+tail;				
			    updataname(head,tail);
			}
			if(j==0) Final=origion;
		}
		System.out.println(Final+j);
	}
	public static void updataname(String head,String tail) {
		Doc dgz;
		for(Enumeration<Doc> e=DataProcessing.docs.elements();e.hasMoreElements();) {
			dgz=e.nextElement();
			if(Final.equals(dgz.getFilename())) {
				j++;
				Final=head+"("+j+")"+"."+tail;
				updataname(head,tail);
			}	
		}*/	
		//Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		//Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		//int screenWidth = screenSize.width/2; // 获取屏幕的宽
		//int screenHeight = screenSize.height/2; // 获取屏幕的高
		//Client client=new Client("127.0.0.1");
		//ObjectOutputStream o=new ObjectOutputStream(new OutputStream());
		//String[][] s= {{"1","3"},{"2","4","6"}};
		//String[] s0= {"1","2"};
		//String[] s1= {"3","4"};
		//String[][] q= {};
		//q[0]=s0;
		//q[1]=s1;
		//for(int i=0;i<q.length*q[0].length;i++) {
		//	System.out.println(q[i]);
		//}
		/*System.out.println(s.length);
		for(int i=0;i<s0.length;i++) {
		   System.out.println(s0[i]);
		}
		for(int i=0;i<s1.length;i++) {
			System.out.println(s1[i]);
		}*/
		//File f1=new File("D:\\Build\\test.txt");
		/*File f2=new File("D:\\Build\\test.txt");
		//BufferedInputStream bis=new BufferedInputStream(new FileInputStream(f1));
		if(!f2.exists()) {
			f2.createNewFile();
		}
		BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(f2));
		//ObjectInputStream o=new ObjectInputStream(bis);
		ObjectOutputStream p=new ObjectOutputStream(bos);
		p.writeObject(bos);
*/

		/*JFileChooser j=new JFileChooser();
		j.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int i=j.showOpenDialog(null);
		if(i==JFileChooser.APPROVE_OPTION) {
			System.out.println(j.getSelectedFile().getPath());
			File f=new File(j.getSelectedFile().getPath());
			System.out.println(f.getName());
		}else if(i==JFileChooser.CANCEL_OPTION){
			
		}*/
		/*System.out.println(InetAddress.getLocalHost());*/
		String driverName="com.mysql.cj.jdbc.Driver";                                                       // 加载数据库驱动类
		String URL="jdbc:mysql://localhost:3306/document?serverTimezone=UTC&characterEncoding=utf-8";       // 声明数据库的URL
		String USER="root";                                                                                 // 数据库用户
		String PASSWORD="123456";
	    String selectDocbyIDSql="select * from doc_info where ID=?";
	    String tempID=null;
		String tempc=null;
		Timestamp tempt=null;
		String tempd=null;
		String tempf=null;
		String ID="7";

		try {
			Connection con;      //-----连接
			PreparedStatement ps;//-----sql执行用的东西
			ResultSet rs;        //-----结果集
		    Class.forName(driverName);		// 加载数据库驱动类
		    con=DriverManager.getConnection(URL,USER,PASSWORD);   // 建立数据库连接
			ps=con.prepareStatement(selectDocbyIDSql);
			ps.setString(1, ID);
		    rs=ps.executeQuery();
		    System.out.println("1"+tempID+":"+tempc+":"+tempt+":"+tempd+":"+tempf);
			while (rs.next()){
				tempID=rs.getString(1);
				tempc=rs.getString(2);
				tempt=rs.getTimestamp(3);
				tempd=rs.getString(4);
				tempf=rs.getString(5);
				System.out.println("1"+tempID+":"+tempc+":"+tempt+":"+tempd+":"+tempf);
			}			
			rs.close();
			ps.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		Doc d=new Doc(tempID,tempc,tempt,tempd,tempf);
		if(d.getID()==null) {
			System.out.println("1");
		}
		

	}
}




