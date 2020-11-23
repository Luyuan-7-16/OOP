package GUIFrame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import oop_1.DataProcessing;
import oop_1.MainGUI;
import oop_1.User;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
//-----------------------------------------------------------------------------------------------------------
public class userFrame extends JFrame{

	public static JFrame frame;
	private JTextField textField_2;
	private JTable table;
	private DefaultTableModel T1;
	private JScrollPane ScrollPane;
	private static int index;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private String Name;
	private String name;
	private String password;
	private String role;
	public User user;
	private String[] un;
	
	Object[] options={"确定","返回"};
	

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataProcessing.disconnectFromDB();
					userFrame window = new userFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public userFrame() throws SQLException {
		initialize();
	}

	public userFrame(int a)  {
		index=a;
	    T1=mainFrame.ut;
		user=mainFrame.user;
		try {
			initialize();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setTitle("\u7528\u6237\u7BA1\u7406\u754C\u9762");
		frame.setBounds(100, 180, 610, 370);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 700, 370);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.add("新增用户", panel);
		panel.setLayout(null);
		
		JLabel label_3 = new JLabel("\u7528 \u6237 \u540D");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label_3.setBounds(100, 50, 80, 30);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("  \u53E3  \u4EE4");
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label_4.setBounds(100, 105, 80, 30);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("  \u89D2  \u8272");
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label_5.setBounds(100, 160, 80, 30);
		panel.add(label_5);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"administrator","browser","operator"}) );
		comboBox_3.setBounds(218, 160, 220, 30);
		panel.add(comboBox_3);
		
		JButton button_2 = new JButton("\u589E \u52A0");
		
		button_2.setFont(new Font("微软雅黑", Font.PLAIN, 20));//--------------xiugai
		button_2.setBounds(180, 220, 85, 30);
		panel.add(button_2);
		
		JButton btnNewButton = new JButton("\u53D6 \u6D88");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnNewButton.setBounds(325, 220, 85, 30);
		panel.add(btnNewButton);
		
		textField_2 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar()=='\n') {
					
				}
			}
		});
		textField_2.setBounds(218, 50, 220, 30);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar()!='\n') {
					password=new String(new String(passwordField.getText()+e.getKeyChar()));
				}			
			}
		});
		passwordField.setBounds(218, 105, 220, 30);
		panel.add(passwordField);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.add("修改用户",panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("\u7528 \u6237 \u540D");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label.setBounds(100, 50, 80, 30);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("  \u53E3  \u4EE4");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label_1.setBounds(100, 105, 80, 30);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("  \u89D2  \u8272");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label_2.setBounds(100, 160, 80, 30);
		panel_1.add(label_2);
		
		JComboBox comboBox = new JComboBox();//-------------------------------------角色名载入
		comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		comboBox.setBounds(218, 50, 220, 30);
		panel_1.add(comboBox);
		
		/*for(Enumeration<User> e  = DataProcessing.getAllUser();e.hasMoreElements();) {//-------------在comboBox加入用户名
			User dgz=e.nextElement();
			comboBox.addItem((String)dgz.getName());
            }*/
		
		/*for(int i=0;i<un.length;i++) {
			comboBox.addItem((String)un[i]);
		}*/
		
		
				
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"administrator","browser","operator"}) );
		comboBox_1.setBounds(218, 160, 220, 30);
		panel_1.add(comboBox_1);
		
		
		JButton button = new JButton("\u4FEE \u6539");
		button.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button.setBounds(180, 220, 85, 30);
		panel_1.add(button);
		
		JButton button_1 = new JButton("\u53D6 \u6D88");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button_1.setBounds(325, 220, 85, 30);
		panel_1.add(button_1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar()!='\n') {
					password=new String(new String(passwordField_1.getText())+e.getKeyChar());
				}
			}
		});
		passwordField_1.setBounds(218, 105, 220, 30);
		panel_1.add(passwordField_1);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.add("删除用户", panel_2);
		panel_2.setLayout(null);
		//String[] columnNames = {"用户名","口令","角色"};   //列名
		//String[][] Value= {{},{},{},{},{},{},{}};
		//DefaultTableModel T1=new DefaultTableModel(Value,columnNames);
		
		table=new JTable(T1) {public boolean isCellEditable(int row, int column){return false;}};
		JTableHeader header = table.getTableHeader();
		header.setReorderingAllowed(false);
		table.setFont(new Font("宋体", Font.PLAIN, 15));
		table.setEditingColumn(0);
		
		ScrollPane=new JScrollPane(table);
		ScrollPane.setBounds(96, 60, 391, 150);
		panel_2.add(ScrollPane);
		
		for(int i=0;i<table.getRowCount();i++) {
			comboBox.addItem((String)table.getValueAt(i, 0));//放在table后面
		}
		
		/*for(Enumeration<User> e  = DataProcessing.getAllUser();e.hasMoreElements();) {//-------------在table加入用户信息
			User dgz=e.nextElement();
			String s1=dgz.getName();
			String s2=dgz.getPassword();
			String s3=dgz.getRole();
			String[] ss= {s1,s2,s3};
			T1.insertRow(0, ss);
			//T1=new DefaultTableModel(v,v);
			//table.setModel(T1);
            }*/
		
		JButton button_3 = new JButton("\u5220 \u9664");
		button_3.addActionListener(new ActionListener() {//--------------------------------------删除按钮
			public void actionPerformed(ActionEvent e) {
				int selecteRow=table.getSelectedRow();
				int MaximunRow=table.getRowCount();
				//System.out.print(selecteRow);				
				if(selecteRow>=0&&selecteRow<=MaximunRow) {
					name=(String)table.getValueAt(selecteRow, 0);
					//System.out.print(name);
					try {						
							if(JOptionPane.showOptionDialog(null, "确定删除？", "删除用户！", JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE, null, options ,1)==0) {
								MainGUI.client.sendMessage("CLIENT_USER_DEL");
								MainGUI.client.sendsingleData(name);
								if(MainGUI.client.processConnection().equals("SERVER_USER_DEL")) {
									T1.removeRow(selecteRow);//--表格删除这一行
									comboBox.removeItem(name);//--框删除这个名字
									JOptionPane.showMessageDialog(null, "删除成功");
								}
								
								//user.xuliehua();
							}						
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		button_3.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button_3.setBounds(180, 250, 80, 30);
		panel_2.add(button_3);
		
		button_2.addActionListener(new ActionListener() {//---------------------------------增加按钮的监听
			public void actionPerformed(ActionEvent e) {
				name=textField_2.getText();
				role=(String)comboBox_3.getSelectedItem();
				password=passwordField.getText();
				if(name.equals("")||password.equals("")) {
					JOptionPane.showMessageDialog(null, "请填写完整信息");
				}else {
					MainGUI.client.sendMessage("CLIENT_USER_ADD");
					MainGUI.client.sendUserData(name,password,role);
					try {
						if(MainGUI.client.processConnection().equals("Allow")) {
							if(MainGUI.client.processConnection().equals("SERVER_USER_ADD")) {
								JOptionPane.showMessageDialog(null, "添加成功");
								comboBox.addItem(name);
							    String[] ss = {name,password,role};
								T1.insertRow(0,ss);
								textField_2.setText(null);
								passwordField.setText(null);
							}
							else JOptionPane.showMessageDialog(null, "添加失败");
						}else JOptionPane.showMessageDialog(null, "用户名重复");
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}					
				}//--end else
			}//--end event
		});
		
		button.addActionListener(new ActionListener() {//------------------------------------修 改 按 钮
			public void actionPerformed(ActionEvent e) {
				name=(String)comboBox.getSelectedItem();
				role=(String)comboBox_1.getSelectedItem();
				if(password.equals("")) {
					JOptionPane.showMessageDialog(null, "请填写完整信息");
				}
				else {
					if(JOptionPane.showOptionDialog(null, "确定修改？", "修改用户！", JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE, null, options ,1)==0) {
						MainGUI.client.sendMessage("CLIENT_USER_MOD");
						MainGUI.client.sendUserData(name, password,role);
						try {
							if(MainGUI.client.processConnection().equals("SERVER_USER_MOD")) {
								passwordField_1.setText(null);
								int Max=table.getRowCount();//------------------更新表格							
								for(int i=0;i<Max;i++) {
									//System.out.println(Max+":"+name+":"+table.getValueAt(i, 0));
									if(table.getValueAt(i, 0).equals(name)) {
										T1.setValueAt(password, i, 1);
										T1.setValueAt(role, i, 2);
									}
							    }						
								JOptionPane.showMessageDialog(null, "修改成功");
							}
						} catch (HeadlessException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						//user.xuliehua();
					}
				}//-------end els				
			}//---end event
		});
		
		JButton button_4 = new JButton("\u8FD4 \u56DE");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button_4.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button_4.setBounds(310, 250, 80, 30);
		panel_2.add(button_4);
		tabbedPane.setSelectedIndex(index);
		//panel_2.setVisible(false);
		//tabbedPane.remove(panel_1);
	}
}
