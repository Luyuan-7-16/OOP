package oop_1;
import java.util.*;
import java.sql.SQLException;
import java.io.*;

public class Main {

	public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
		int a;
		User u;		
		String Name = null,Password = null,Role = null;		
		String menu="*-*-*-*-*-*-*-*-欢迎使用档案管理系统-*-*-*-*-*-*-*-*"
		            +"\n\t\t\t1.登录\n\t\t\t2.退出\n"
		            +"*-*-*-*-*-*-*-*-欢迎使用档案管理系统-*-*-*-*-*-*-*-*";		
		Scanner scan=new Scanner(System.in);
		
		//File f1=new File("D:\\Build\\JavaUsersSave.ser");///*tabbedPane.setSelectedIndex();*/ f1.createNewFile();
		//File f2=new File("D:\\Build\\JavaDocsSave.ser");// f2.createNewFile();
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream("D:\\Build\\JavaUsersSave.ser"));
		ObjectInputStream ois2=new ObjectInputStream(new FileInputStream("D:\\Build\\JavaDocsSave.ser"));
		DataProcessing.users=(Hashtable<String, User>)ois.readObject();
		DataProcessing.docs=(Hashtable<String, Doc>)ois2.readObject();
		
		for(;;) {
			System.out.println(menu);
						                            
			a=scan.nextInt();                    //a=(char)System.in.read();
			scan.nextLine();                     //接受int的回车
			
			if(a==1) {
				System.out.println("\t\t\t登录\t\t\t");
				
				System.out.print("Enter Name:");
				Name=scan.nextLine();
				
				System.out.print("Enter Password:");
				Password=scan.nextLine();
			}	
			else if(a==2) {
				System.out.println("\t\t\t已退出\t\t\t");
				System.exit(0);
			}
			
			if(DataProcessing.searchUser(Name)!=null) {
				if(DataProcessing.searchUser(Name,Password)!=null) 
					u=DataProcessing.searchUser(Name, Password);
				else {
					System.out.println("密码错误");
					continue;
				}
			}
			else {
				System.out.println("用户列表内误此用户名");
				continue;
			}

			
			try {				
				//----------------------------档案管理员----------------------------------------------------
				if(u.getRole().equalsIgnoreCase("administrator")) {
					User user=new Administrator(u.getName(),u.getPassword(),u.getRole());
					user.Operate();				
				}
				//----------------------------档案浏览员----------------------------------------------------
				else if(u.getRole().equalsIgnoreCase("browser")) {
					User user=new Browser(u.getName(),u.getPassword(),u.getRole());
					user.Operate();				
				}
				//----------------------------档案操作员---------------------------------------------------
				else if(u.getRole().equalsIgnoreCase("operator")) {
					User user=new Operator(u.getName(),u.getPassword(),u.getRole());
					user.Operate();
				}
				
				//throw new NullPointerException();
			}catch(NullPointerException N) {
				N.printStackTrace();
				//System.out.println(" main try NPE");
			}catch(SQLException S) {
				S.printStackTrace();
				//System.out.println(" main try SQL");
			}catch(IOException I) {
				I.printStackTrace();
				//System.out.println(" main try IO");
			}finally {
				scan.close();
				ois.close();
				ois2.close();
				
			}
		
		}
     }
}
