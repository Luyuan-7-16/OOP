package GUIFrame;

import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import oop_1.DataProcessing;
import oop_1.Doc;
import oop_1.MainGUI;
import oop_1.User;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;
//-----------------------------------------------------------------------------------------------------------
public class fileFrame extends JFrame{

	public static JFrame frame;
	private JTextField textField;
	private JTextField textField_2;
	private JTable table;
	private DefaultTableModel T1;
	public static int index;
	private String ID;
	private String description;
	private String directory;
	private static User user;
	private String[][] dData;
	private JTextField textField_1;
	//String[] columnNames = {"档案号","创建者","时间","文件名","描述"};   //列名
	//String[][] Value= {{},{},{},{},{},{},{},{},{},{}};
	//DefaultTableModel T1=new DefaultTableModel(Value,columnNames);
	//private JScrollPane ScrollPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fileFrame window = new fileFrame();
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
	public fileFrame() {
		try {
			initialize();
			}catch(Exception e) {
				e.printStackTrace();
			}
	}

	public fileFrame(int a) {
		index=a;
		T1=mainFrame.dt;
		user=mainFrame.user;
		try {
		initialize();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setTitle("\u6587\u4EF6\u7BA1\u7406\u754C\u9762");
		frame.setBounds(150, 180, 634, 415);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, "name_1416015476862700");
		
		
		
		
		
		JPanel panel = new JPanel();	
		tabbedPane.add("文件下载",  panel);
		panel.setLayout(null);	
		//table=new JTable(T1) {public boolean isCellEditable(int row, int column){return false;}};
		
		table=new JTable(T1) {public boolean isCellEditable(int row, int column){return false;}};
		JTableHeader header = table.getTableHeader();
		header.setReorderingAllowed(false);
		table.setFont(new Font("微软雅黑", Font.PLAIN, 15));//
		JScrollPane ScrollPane = new JScrollPane(table);
		ScrollPane.setBounds(50, 60, 500, 160);//
		panel.add(ScrollPane);
		
		/*for(Enumeration<Doc> e  = DataProcessing.getAllDocs();e.hasMoreElements();) {//-------------在table加入用户信息
			Doc dgz=e.nextElement();
			String s1=dgz.getID();
			String s2=dgz.getCreator();
			String s3=dgz.getTimestamp().toString();
			String s4=dgz.getFilename();
			String s5=dgz.getDescription();
			String[] ss= {s1,s2,s3,s4,s5};
			T1.insertRow(0, ss);
		}*/
		
		JButton button_3 = new JButton("\u4E0B \u8F7D");
		button_3.addActionListener(new ActionListener() {//-------------------------下载文件
			public void actionPerformed(ActionEvent e) {
				int selecteRow=table.getSelectedRow();								
				if(selecteRow==-1) {
					JOptionPane.showMessageDialog(null, "请选择文件");
				}else if(textField_1.getText().equals("")||textField_1.getText().equals("nullnull")) {
					JOptionPane.showMessageDialog(null, "请选择保存路径");
				}else {
					String ID=table.getValueAt(selecteRow, 0).toString();
					String filename=table.getValueAt(selecteRow, 3).toString();					
					String dp=textField_1.getText();									
					try {
						MainGUI.client.sendMessage("CLIENT_DOC_DOWN");
						MainGUI.client.sendsingleData(ID);
						if(MainGUI.client.processConnection().equals("SERVER_DOC_DOWM")) {
							byte buffer[]=MainGUI.client.receiveS();
							File f2=new File(dp+"\\"+filename);
							if(!f2.exists()) {
								f2.createNewFile();
							}
							BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(f2));					
							bos.write(buffer);					
							bos.close();
						}					
					}catch(Exception c) {
						c.printStackTrace();					
					}
					JOptionPane.showMessageDialog(null, "下载成功");
				}				
			}
		});
		button_3.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button_3.setBounds(155, 270, 80, 30);
		panel.add(button_3);
		
		JButton button_4 = new JButton("\u8FD4 \u56DE");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button_4.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button_4.setBounds(354, 270, 80, 30);
		panel.add(button_4);
		
		textField_1 = new JTextField();
		textField_1.setBounds(50, 230, 390, 30);
		panel.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setEditable(false);
		
		JButton btnNewButton = new JButton("\u4E0B\u8F7D\u8DEF\u5F84");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int ci=jfc.showSaveDialog(null);
				if(ci==JFileChooser.APPROVE_OPTION) {
					textField_1.setText(jfc.getSelectedFile().getPath());
				}else if(ci==JFileChooser.CANCEL_OPTION) {
					
				}				
			}
		});
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btnNewButton.setBounds(450, 230, 100, 30);
		panel.add(btnNewButton);
		

		
		JPanel panel2 = new JPanel();
		tabbedPane.add("文件上传",  panel2);
		panel2.setLayout(null);
		/*try {
			if(user.getRole().equalsIgnoreCase("browser")) {
				panel2.setEnabled(false);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		*/
		JLabel label = new JLabel("\u6863 \u6848 \u53F7");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label.setBounds(90, 35, 100, 30);
		panel2.add(label);
		
		JLabel label_1 = new JLabel("\u6863 \u6848 \u63CF \u8FF0");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label_1.setBounds(90, 115, 100, 30);
		panel2.add(label_1);
		
		JLabel label_2 = new JLabel("\u6863\u6848\u6587\u4EF6\u540D");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label_2.setBounds(90, 230, 100, 30);
		panel2.add(label_2);
		
		textField = new JTextField();
		textField.setBounds(230, 35, 155, 30);
		panel2.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		textField_2.setBounds(230, 230, 155, 30);
		panel2.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setEditable(false);
		
		JButton button = new JButton("\u4E0A\u4F20");
		button.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button.setBounds(220, 285, 85, 30);
		panel2.add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button_1.setBounds(310, 285, 85, 30);
		panel2.add(button_1);
		
		JButton button_2 = new JButton("\u6253\u5F00");
		button_2.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc=new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int i = jfc.showOpenDialog(null);
				if(i==JFileChooser.APPROVE_OPTION) {
					textField_2.setText(jfc.getSelectedFile().getPath());
					//directory=jfc.getSelectedFile().getPath();
				}else if(i==JFileChooser.CANCEL_OPTION) {
					
				}
				
				//System.out.println(fd1.getDirectory()+fd1.getFile());
				//System.out.println(fd1.getFile());
			}
		});
		button_2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button_2.setBounds(390, 230, 85, 30);
		panel2.add(button_2);
		
		JTextArea textArea=new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ID=textField.getText();
				description = textArea.getText();
				directory=textField_2.getText();
				if(ID.equals("")||description.equals("")||directory.equals("")) JOptionPane.showMessageDialog(null, "请填写完整信息");
				else {
					MainGUI.client.sendMessage("CLIENT_DOC_UP");//1.发送请求
					File f1=new File(directory);//---------这几条顺序不能乱
					MainGUI.client.sendDocData(ID, user.getName(), description, f1.getName());//2.发送文件信息
					try {
						if(MainGUI.client.processConnection().equals("Allow")) {
							BufferedInputStream bis=new BufferedInputStream(new FileInputStream(f1));//----------读入		
							int count=bis.available();
							byte[] file=new byte[count];
							bis.read(file);
							MainGUI.client.sendByte(file);//3.发送文件内容到服务器端
							bis.close();
							if(MainGUI.client.processConnection().equals("SERVER_DOC_UP")) {
								String FN=MainGUI.client.processConnection();//接收修改后的文件名
								if(!f1.getName().equals(FN)) System.out.println("文件名重复，更改为:"+FN);
								textField.setText(null);
								textField_2.setText(null);
								textArea.setText(null);
								
								int id=Integer.parseInt(ID);
								ID=Integer.toString(id);
								Timestamp timestamp= new Timestamp(System.currentTimeMillis()); 
								String[] s= {ID,user.getName(),timestamp.toString(),FN,description};
								T1.insertRow(0,s);				
								JOptionPane.showMessageDialog(null, "上传成功");
							}else JOptionPane.showMessageDialog(null, "上传失败");
						}else JOptionPane.showMessageDialog(null, "ID重复");
					} catch (IOException e1) {
						e1.printStackTrace();
					}												
				}
			}//--end event
		});
					
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(230, 115, 155, 80);
		panel2.add(scrollPane);
		tabbedPane.setSelectedIndex(index);
		
		try {
			if(user.getRole().equalsIgnoreCase("Browser")) {
				tabbedPane.setEnabledAt(1, false);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
