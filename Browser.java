package oop_1;
import java.util.*;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

public class Browser extends User implements Serializable{
	
	String tip_role="档案浏览员菜单";
	String tip_exit="退出系统，感谢使用";
	String menu="************欢迎进入"+tip_role+"*************"
			+"\n \t 1.下载文件"+"\n \t 2.文件列表"+"\n \t 3.修改密码"+"\n \t 4.退出"
			+"\n*********************************************";
	String Password = null;
	String filename= null;//------------------------下载的文件名接收
	String ID=null;//-------------------------------下载输入ID接收
	
	String download="D:\\Build\\JavaTest\\download\\";//------文件下载地址的父目录

	Browser(String name,String password,String role){
		super(name,password,role);
	}
	
	public void showMenu() {
		System.out.println(menu);
	}
	
	public void Operate() throws SQLException,IOException{
		int a;
		Scanner scan=new Scanner(System.in);
		for(;;) {
			this.showMenu();
			System.out.print("请输入指令：");
			a=scan.nextInt(); 
			scan.nextLine();
			switch(a) {
			case 1: {
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
			case 2:{
				this.showFileList();
				break;
			}
			case 3:{
				System.out.print("请输入新密码：");
				Password=scan.nextLine();
				if(this.changeSelfInfo(Password)) ;
				else System.out.println("修改失败");
				this.xuliehua();
				break;
			}
			case 4:{
				this.exitSystem();
			}					
			default :System.out.println("输入有误");
			}
		}
	}
	
}

