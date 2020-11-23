package oop_1;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.*;

public class Administrator extends User implements Serializable{
	
	String tip_role="管理者菜单";
	String tip_exit="退出系统，感谢使用";
	String menu="************欢迎进入"+tip_role+"*************"
			+"\n \t 1.修改用户"+"\n \t 2.删除用户"+"\n \t 3.新增用户"+"\n \t 4.列出用户"
			+"\n \t 5.下载文件"+"\n \t 6.文件列表"+"\n \t 7.修改(本人)密码"+"\n \t 8.退出"
			+"\n**********************************************";
	
	//String upload="D:\\Build\\JavaTest\\upload\\";
	String download="D:\\Build\\JavaTest\\download\\";
	
	String Name = null, Password = null, Role = null;
	String filename= null;//------------------------下载的文件名接收
	String ID=null;//-------------------------------下载输入ID接收
	int a;
	
	Administrator(String name,String password,String role){
		super(name,password,role);
	}
	
	public void showMenu() {
		System.out.println(menu);
	}
	
	public void Operate() throws SQLException,IOException, ClassNotFoundException {
		Scanner scan=new Scanner(System.in);
		for(;;) {
			this.showMenu();
			System.out.println("请输入指令");
			a=scan.nextInt();
			scan.nextLine();
			switch(a) {
			case 1:{
				System.out.print("输入要修改的用户名:");
				Name=scan.nextLine();
				//User dgz=null;
				try {
				User dgz=DataProcessing.searchUser(Name);
				dgz.ForException(); //反应是否有此用户
				System.out.println(" 1--密码 \t 2--角色");
				System.out.print("修改其什么:");					
				a=scan.nextInt();
				scan.nextLine();
				
				switch(a) {
				case 1:{
					System.out.print("输入新密码:");
					Password=scan.nextLine();
					dgz.setPassword(Password);
					System.out.print("55");
					if(DataProcessing.updateUser(dgz.getName(), dgz.getPassword(), dgz.getRole())) System.out.println("修改成功");
					else System.out.println("修改失败");
					break;
				}
				case 2:{
					System.out.print("输入改变后的角色:");
					Role=scan.nextLine();
					dgz.setRole(Role);
					if(DataProcessing.updateUser(dgz.getName(), dgz.getPassword(), dgz.getRole())) System.out.println("修改成功");
					else System.out.println("修改失败");
					break;
				}
				default: {
					System.out.println("输入错误"); 
					break;
				}
				}
				this.xuliehua();
				//ObjectInputStream ois=new ObjectInputStream(new FileInputStream("D:\\Build\\JavaUsersSave.ser"));
				//ObjectInputStream ois2=new ObjectInputStream(new FileInputStream("D:\\Build\\JavaDocsSave.ser"));
				//DataProcessing.users=(Hashtable<String, User>)ois.readObject();
				//DataProcessing.docs=(Hashtable<String, Doc>)ois2.readObject();
				//ois.close();
				//throw new NullPointerException();
			}catch (NullPointerException e) {
			    System.out.println("用户列表中不存在此用户名 Admini ");
			}
			    break;
			}
			case 2:{
				System.out.print("输入要删除的用户的名字:");
				Name=scan.nextLine();
				if(DataProcessing.deleteUser(Name)) System.out.println("删除成功");
				else System.out.println("删除失败");
				this.xuliehua();
				break;
			}
			case 3:{
				System.out.print("输入新用户的姓名:");
				Name=scan.nextLine();
				System.out.print("输入新用户的密码:");
				Password=scan.nextLine();
				System.out.print("输入新用户的角色:");
				Role=scan.nextLine();
									
				if(DataProcessing.insertUser( Name, Password, Role)) System.out.println("添加成功");
				else System.out.println("添加失败");	
				this.xuliehua();
				//ObjectInputStream ois=new ObjectInputStream(new FileInputStream("D:\\Build\\JavaUsersSave.ser"));
				//ObjectInputStream ois2=new ObjectInputStream(new FileInputStream("D:\\Build\\JavaDocsSave.ser"));
				//DataProcessing.users=(Hashtable<String, User>)ois.readObject();
				//DataProcessing.docs=(Hashtable<String, Doc>)ois2.readObject();
				//ois.close();
				break;
			}
			case 4:{
				User dgz = null;
				for(Enumeration<User> e  = DataProcessing.users.elements();e.hasMoreElements();) {
				dgz=e.nextElement();
				System.out.println(dgz.getName()+"\t"+dgz.getPassword()+"\t"+dgz.getRole());
                }
				break;
			}
			case 5:{
				System.out.print("请输入档案号：");
				ID=scan.nextLine();
				filename=DataProcessing.searchDoc(ID).getFilename();
				try{
					if(this.downloadFile(filename)) System.out.println("下载成功");
				}catch(IOException e) {
					System.out.println("Op downloadFile");
				}
				break;
			}
			case 6:{
				this.showFileList();
				break;
			}
			case 7:{
				System.out.print("请输入新密码：");
				Password=scan.nextLine();
				if(this.changeSelfInfo(Password)) ;
				else System.out.println("修改失败");
				this.xuliehua();
				//ObjectInputStream ois=new ObjectInputStream(new FileInputStream("D:\\Build\\JavaUsersSave.ser"));
				//ObjectInputStream ois2=new ObjectInputStream(new FileInputStream("D:\\Build\\JavaDocsSave.ser"));
				//DataProcessing.users=(Hashtable<String, User>)ois.readObject();
				//DataProcessing.docs=(Hashtable<String, Doc>)ois2.readObject();
				//ois.close();
				break;
			}
			case 8:this.exitSystem();
			default :System.out.println("输入有误");
		    }
	    }
    }
}	


