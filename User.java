package oop_1;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;

public abstract class User implements Serializable{
	private static final long serialVersionUID = 6666958677216134756L;
	private String name;
	private String password;
	private String role;
	public String Final=null;
	int xuhao;
	public String upload="D:\\Build\\JavaTest\\upload\\";
	public String download="D:\\Build\\JavaTest\\download\\";
	String tempFilename=null;
	
	User(String name,String password,String role){
		this.name=name;
		this.password=password;
		this.role=role;				
	}
	
	public boolean changeSelfInfo(String password) throws SQLException {
		//写用户信息到存储
		if (DataProcessing.updateUser(name, password, role)){//哈希表里面修改成功返回true，满足if进行对象的属性修改
			this.password=password;
			System.out.println("修改成功");
			return true;
		}else
			return false;
	}
	
	public abstract void showMenu();
	public abstract void Operate() throws SQLException,IOException, ClassNotFoundException ;
	
	public void ForException() {}
	
	
	
	public boolean upload(String dest,String ID,String description) throws IOException, SQLException {//-----------上传文件
		//File UPATH=new File(upload);
		//String FA[] = UPATH.list();
		/*for(int i=0;i<FA.length;i++) {
			if(f1.getName().equals(FA[i])) {
				System.out.println("文件名与此目录下原有文件重名");
				this.showFileList();
				System.out.println("为此文件更名:");
				Scanner cin=new Scanner(System.in);
				tempFilename=cin.nextLine();
				break;
			}
		}*/
		/*for(;;) {
		if(!f2.exists()) {
			f2.createNewFile();//--------------------------------------如果f2不存在则上传（创建一个文件，后面写入数据）
			break;
		}
		else {
			System.out.println("文件名与此目录下原有文件重名");
			this.showFileList();
			System.out.println("为此文件更名:");
			Scanner cin=new Scanner(System.in);
			tempFilename=cin.nextLine();
			f2=new File(upload+tempFilename);
		}
		}*/
		
		File f1=new File(dest);//---------这几条顺序不能乱
		this.changname(f1.getName());		
		File f2=new File(upload+Final);	
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream(f1));//----------读入
		BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(f2));
		
		int c=0;
		byte buffer[]=new byte[1024];
		while((c=bis.read(buffer))!=-1) {
			for(int i=0;i<c;i++) {
				bos.write(buffer[i]);
			}
		}
		bis.close();
		bos.close();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
		DataProcessing.insertDoc(ID, this.getName(), timestamp, description, Final);
		System.out.println("上传文件...");
		
		return true;
	}
	
	public void changname(String f1name) throws SQLException {
		String origion=f1name;
        String head=null,tail=null;		
		Doc dgz;
		xuhao=0;
		for(Enumeration<Doc> e=DataProcessing.getAllDocs();e.hasMoreElements();) {
			dgz=e.nextElement();
			if(origion.equals(dgz.getFilename())) {
				StringTokenizer fn2=new StringTokenizer(origion,"(.");
				int m=fn2.countTokens();//--可以调用next Token的次数，如果有3个分隔符就是4次
				for(int i=0;fn2.hasMoreTokens();i++) if(i==0) head=fn2.nextToken("(.");else if(i==m-1) tail=fn2.nextToken(".");else fn2.nextToken(".");				
				Final=head+"."+tail;				
			    updataname(head,tail);
			}
			if(xuhao==0) Final=origion;
		}
	}
	
	public boolean downloadFile(String filename) throws IOException{
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new IOException( "Error in accessing file" );
		File f1=new File(upload+filename);
		File f2=new File(download+filename);
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream(f1));
		if(!f2.exists()) {
			f2.createNewFile();
		}
		BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(f2));
		int c=0;
		byte buffer[]=new byte[1024];
		while((c=bis.read(buffer))!=-1) {
			for(int i=0;i<c;i++) {
				bos.write(buffer[i]);
			}
		}
		bis.close();
		bos.close();
		System.out.println("下载文件... ...");
		return true;
	}
	
	public void showFileList() throws SQLException {
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new SQLException( "Error in accessing file DB" );
		Doc dgz=null;
		System.out.println("文件列表... ...");
		for(Enumeration<Doc> e=DataProcessing.docs.elements();e.hasMoreElements();) {
			dgz=e.nextElement();
			System.out.println("ID:"+dgz.getID()+"    "+"Creator:"+dgz.getCreator()+"    "+"Time:"+dgz.getTimestamp()+"    "+
					"Description:"+dgz.getDescription()+"    "+"\tName:"+dgz.getFilename());
		}
	}
	
	public  void updataname(String head,String tail) throws SQLException {
		Doc dgz;
		for(Enumeration<Doc> e=DataProcessing.getAllDocs();e.hasMoreElements();) {
			dgz=e.nextElement();
			if(Final.equals(dgz.getFilename())) {
				xuhao++;
				Final=head+"("+xuhao+")"+"."+tail;
				updataname(head,tail);
			}			
		}		
	}
	
	public void xuliehua() {
		try {
			ObjectOutputStream ops=new ObjectOutputStream(new FileOutputStream("D:\\Build\\JavaUsersSave.ser"));
			ObjectOutputStream ops2=new ObjectOutputStream(new FileOutputStream("D:\\Build\\JavaDocsSave.ser"));
			ops.writeObject(DataProcessing.users);
			ops2.writeObject(DataProcessing.docs);
			ops.close();
			ops2.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public void exitSystem(){
		System.out.println("系统退出, 谢谢使用 ! ");
		System.exit(0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	

}