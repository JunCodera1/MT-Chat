package com.themt.mtchat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Client extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private String name, address;
	private int port;
	private JTextField txtMessage;
	private JTextArea txtrHistory;

	public Client(String name, String address, int port) {
		setTitle("MT Chat Client");
		this.name = name;
		this.port = port;
		this.address = address;
		createWindow();
		console("Attempting a connection to " + address + ":" + port + ", user: " + name);
	}
	
	private void createWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(880, 550);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{28, 815, 30, 7}; // SUM = 880
		gbl_contentPane.rowHeights = new int[]{35, 475, 40}; //SUM = 550
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		txtrHistory = new JTextArea();
		txtrHistory.setEditable(false);
		GridBagConstraints gbc_txtrHistory = new GridBagConstraints();
		gbc_txtrHistory.insets = new Insets(0, 0, 5, 5);
		gbc_txtrHistory.fill = GridBagConstraints.BOTH;
		gbc_txtrHistory.gridx = 1;
		gbc_txtrHistory.gridy = 1;
		gbc_txtrHistory.gridwidth = 2;
		gbc_txtrHistory.insets = new Insets(0, 5, 0, 0);
		contentPane.add(txtrHistory, gbc_txtrHistory);
		
		JButton btnSend = new JButton("Send");
		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				send(txtMessage.getText());
			}
		});
		
		txtMessage = new JTextField();
		txtMessage.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					send(txtMessage.getText());
				}
			}
		});
		txtMessage.setFont(new Font("Arial", Font.PLAIN, 13));
		GridBagConstraints gbc_txtMessage = new GridBagConstraints();
		gbc_txtMessage.insets = new Insets(0, 0, 0, 5);
		gbc_txtMessage.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMessage.gridx = 1;
		gbc_txtMessage.gridy = 2;
		contentPane.add(txtMessage, gbc_txtMessage);
		txtMessage.setColumns(10);
		GridBagConstraints gbc_btnSend = new GridBagConstraints();
		gbc_btnSend.insets = new Insets(0, 0, 0, 5);
		gbc_btnSend.gridx = 2;
		gbc_btnSend.gridy = 2;
		contentPane.add(btnSend, gbc_btnSend);
		
		requestFocus();
		txtMessage.requestFocus();
		setVisible(true);
		
		txtMessage.requestFocusInWindow();
	}
	private void send(String message) {
		if(message.equals("")) return;
		message = name + ": " + message;
		console(message);
		txtMessage.setText("");
	}
	
	public void console(String message) {
		txtrHistory.append(message + "\n\r");
	}

}
