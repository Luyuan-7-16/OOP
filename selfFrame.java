package GUIFrame;

import oop_1.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class selfFrame extends JFrame{

	public static JFrame frame;
	private JTextField textField;
	private JTextField textField_4;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private static User user=null;
	private String oldP=null;
	private String newP1=null;
	private String newP2=null;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					selfFrame window = new selfFrame();
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
	public selfFrame() {
		initialize();
	}

	public selfFrame(User user) throws SQLException {
		this.user=user;
		initialize();	
		System.out.println(user.getName()+"\t"+user.getPassword()+"\t"+user.getRole());
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u4E2A\u4EBA\u4FE1\u606F\u7BA1\u7406");
		frame.setBounds(200, 180, 564, 370);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u7528 \u6237 \u540D");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label.setBounds(65, 25, 80, 30);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u539F \u53E3 \u4EE4");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label_1.setBounds(65, 70, 80, 30);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u65B0 \u53E3 \u4EE4");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label_2.setBounds(65, 115, 80, 30);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u786E\u8BA4\u65B0\u53E3\u4EE4");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label_3.setBounds(35, 165, 110, 30);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("  \u89D2 \u8272");
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label_4.setBounds(65, 215, 80, 30);
		frame.getContentPane().add(label_4);
		
		JButton button = new JButton("\u4FEE \u6539");
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					oldP=passwordField.getText();
					newP1=passwordField_1.getText();
					newP2=passwordField_2.getText();
					if(oldP.equals("")||newP1.equals("")||newP2.equals("")) {
						JOptionPane.showMessageDialog(null, "请填写完整信息");
					}else {
						if(user.getPassword().equals(oldP)) {//------------------------原密码
							if(newP1.equals(newP2)) {//---------------------------两次输入
								MainGUI.client.sendMessage("CLIENT_SELF_MOD");
								MainGUI.client.sendUserData(user.getName(), newP1, user.getRole());
								//String m=MainGUI.client.processConnection();//反馈数据库操作是否成功
								if(MainGUI.client.processConnection().equals("SERVER_SELF_MOD")) {
									JOptionPane.showMessageDialog(null, "修改成功");
									System.out.println(newP1+":"+newP2);
									frame.dispose();
								}
							}else {//---------------------------两次输入
								JOptionPane.showMessageDialog(null, "两次新口令输入错误");
							}
						}else {//------------------------原密码
							JOptionPane.showMessageDialog(null, "原口令有误");
						}
					}										
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button.setBounds(195, 275, 85, 30);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u8FD4 \u56DE");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button_1.setBounds(285, 275, 85, 30);
		frame.getContentPane().add(button_1);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(195, 25, 175, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		try {
			textField.setText(user.getName());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(195, 215, 175, 30);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		try {
			textField_4.setText(user.getRole());
		}catch (Exception e){
			e.printStackTrace();
		}
		
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar()!='\n') {
					oldP=new String(new String(passwordField.getPassword())+e.getKeyChar());
					//passwordField_1.setText(oldP);
				}
			}
		});
		passwordField.setBounds(195, 70, 175, 30);
		frame.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar()!='\n') {//-------------------------------------------------输入回车不作反应
				//newP1=new String(new String(passwordField_1.getPassword())+e.getKeyChar());
			    }
			}
		});
		passwordField_1.setBounds(195, 115, 175, 30);
		frame.getContentPane().add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar()!='\n') {
				//newP2=new String(new String(passwordField_2.getPassword())+e.getKeyChar());			
				}
				else {
					try {
						oldP=passwordField.getText();
						newP1=passwordField_1.getText();
						newP2=passwordField_2.getText();
						if(oldP==null||newP1==null||newP2==null) {
							JOptionPane.showMessageDialog(null, "请填写完整信息");
						}else {
							if(user.getPassword().equals(oldP)) {//------------------------原密码
								if(newP1.equals(newP2)) {//---------------------------两次输入
									MainGUI.client.sendMessage("CLIENT_SELF_MOD");
									MainGUI.client.sendUserData(user.getName(), newP1, user.getRole());
									//String m=MainGUI.client.processConnection();//反馈数据库操作是否成功
									if(MainGUI.client.processConnection().equals("SERVER_SELF_MOD")) {
										JOptionPane.showMessageDialog(null, "修改成功");
										System.out.println(newP1+":"+newP2);
										frame.dispose();
									}
								}else {//---------------------------两次输入
									JOptionPane.showMessageDialog(null, "两次新口令输入错误");
								}
							}else {//------------------------原密码
								JOptionPane.showMessageDialog(null, "原口令有误");
							}
						}							
					}catch(Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		passwordField_2.setBounds(195, 165, 175, 30);
		frame.getContentPane().add(passwordField_2);
	}
}
