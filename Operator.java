package oop_1;
import java.util.*;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

public class Operator extends User implements Serializable{
	
	String tip_role="操作员菜单";
	String tip_exit="退出系统，感谢使用";
	String menu="************欢迎进入"+tip_role+"*************"
			+"\n \t 1.上传文件"+"\n \t 2.下载文件"+"\n \t 3.文件列表"+"\n \t 4.修改密码"+"\n \t 5.退出"
			+"\n*********************************************";
	
	String filename= null;
	String description=null;
	String ID=null;		
	String Password = null;
	
	String upload="D:\\Build\\JavaTest\\upload\\";
	String download="D:\\Build\\JavaTest\\download\\";
	
	int a;

	Operator(String name,String password,String role){
		super(name,password,role);
	}
	
	public void showMenu() {
		System.out.println(menu);
	}
	
	public void Operate() throws SQLException,IOException{
		Scanner scan=new Scanner(System.in);
		for(;;) {
			this.showMenu();
			System.out.print("请输入指令：");
			a=scan.nextInt(); 
			scan.nextLine();
			switch(a) {
			case 1: {
				System.out.println("请输入上传的文件名；");
				filename=scan.nextLine();
				System.out.println("请输入档案号：");
				ID=scan.nextLine();
				System.out.println("请输入描述：");
				description=scan.nextLine();
				if(this.upload(filename,ID,description)) System.out.println("上传成功");
				else System.out.println("上传失败");
				this.xuliehua();
				break;
			}
			case 2: {//------------------------------------------------下载文件
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
			case 3:{//-------------------------------文件列表
				try{
					this.showFileList();
				}catch(SQLException e) {
					System.out.println("Op showFile");
				}
				break;
			}
			case 4:{
				System.out.print("请输入新密码：");
				Password=scan.nextLine();
				if(this.changeSelfInfo(Password)) ;
				else System.out.println("修改失败");
				this.xuliehua();
				break;
			}
			case 5:{
				this.exitSystem();
			}
			case 6:{
				System.out.print("隐藏菜单,删除文件，输入档案号:");
				ID=scan.nextLine();
				if(DataProcessing.deleteDoc(ID)) System.out.println("删除文件成功");				
				else System.out.println("删除文件失败");
				this.xuliehua();
				break;
			}
			case 7:{
				User dgz = null;
				for(Enumeration<User> e  = DataProcessing.users.elements();e.hasMoreElements();) {
				dgz=e.nextElement();
				System.out.println(dgz.getName()+"\t"+dgz.getPassword()+"\t"+dgz.getRole());
                }
				break;
			}
			default :System.out.println("输入有误");
			}
		}
	}
	
}
