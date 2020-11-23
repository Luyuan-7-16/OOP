package oop_1;

import GUIFrame.*;
import java.awt.EventQueue;
import java.awt.Window;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Hashtable;

import javax.swing.JFrame;

public class MainGUI extends JFrame{

	private JFrame frame;
	public static Client client;

	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//DataProcessing.disconnectFromDB();
					loginFrame window=new loginFrame();
					window.frame.setVisible(true);
					client=new Client("127.0.0.1");
					//client=new Client("192.168.43.249");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGUI() {
		initialize();
		System.out.print("MAin");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		System.out.print("MAin");
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
