package GUIFrame;

import oop_1.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import oop_1.DataProcessing;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class mainFrame {

	public static JFrame frame;
	private String name=null;
	private int index;
	public static User user;
	public static DefaultTableModel ut;
	public static DefaultTableModel dt;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame window = new mainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public mainFrame() {
		initialize();
	}
	
	public mainFrame(User user) throws SQLException {
		//this.name=name;
	    this.user=user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//System.out.print(name);
		frame = new JFrame();
		frame.setBounds(100, 100, 950, 500);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				MainGUI.client.sendMessage("TERMINATE");
			    System.exit(0);
			}
			}); 
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u7528\u6237\u7BA1\u7406");//--------------- 用户管理栏
		mnNewMenu.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnNewMenu);
		
		JMenuItem menuItem = new JMenuItem("\u4FEE\u6539\u7528\u6237");//------------新增
		menuItem.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("\u5220\u9664\u7528\u6237");//----------修改
		mnNewMenu.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("\u65B0\u589E\u7528\u6237");//------------删除
		mnNewMenu.add(menuItem_2);
		
		JMenu mnNewMenu_1 = new JMenu("\u6863\u6848\u7BA1\u7406");//------------文件管理栏
		menuBar.add(mnNewMenu_1);
		
		JMenuItem menuItem_3 = new JMenuItem("\u6863\u6848\u4E0A\u4F20");//----------上传
		mnNewMenu_1.add(menuItem_3);
		
		JMenuItem menuItem_4 = new JMenuItem("\u6863\u6848\u4E0B\u8F7D");//-------------下载
		mnNewMenu_1.add(menuItem_4);
		
		JMenu mnNewMenu_2 = new JMenu("\u4E2A\u4EBA\u4FE1\u606F\u7BA1\u7406");//------------自我信息管理
		menuBar.add(mnNewMenu_2);
		
		JMenuItem menuItem_5 = new JMenuItem("\u4FE1\u606F\u4FEE\u6539");
		mnNewMenu_2.add(menuItem_5);
		
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGUI.client.sendMessage("open_userframe");
				try {
					ut=MainGUI.client.receiveT();
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
				userFrame uf;
				uf = new userFrame(1);
				uf.frame.setVisible(true);
				
			}
		});
		
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGUI.client.sendMessage("open_userframe");
				try {
					ut=MainGUI.client.receiveT();
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
				userFrame uf=new userFrame(2);
				uf.frame.setVisible(true);
			}
		});
		
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGUI.client.sendMessage("open_userframe");
				try {
					ut=MainGUI.client.receiveT();
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
				userFrame uf=new userFrame(0);
				uf.frame.setVisible(true);
			}
		});
		
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGUI.client.sendMessage("open_docframe");
				try {
					dt=MainGUI.client.receiveT();
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
				fileFrame ff=new fileFrame(1);
				ff.frame.setVisible(true);
			}
		});
		
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGUI.client.sendMessage("open_docframe");
				try {
					dt=MainGUI.client.receiveT();
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
				fileFrame ff=new fileFrame(0);
				ff.frame.setVisible(true);
			}
		});
		
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selfFrame sf;
				try {
					sf = new selfFrame(user);
					sf.frame.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		try {
			if (user.getRole().equalsIgnoreCase("browser")){
				mnNewMenu.setEnabled(false);
				menuItem_3.setEnabled(false);
			}
			else if(user.getRole().equalsIgnoreCase("operator")) {
				mnNewMenu.setEnabled(false);
			}
			else if(user.getRole().equalsIgnoreCase("administrator")){
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
