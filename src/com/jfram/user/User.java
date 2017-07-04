package com.jfram.user;

import javax.swing.*;

import com.jfram.admin.Administrator;
import com.message.user.Usermessage;

import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.awt.event.*;
import java.net.URL;

public class User extends JFrame implements ActionListener {
	JDesktopPane jdp = null;
	AlsoBook alsoBook = new AlsoBook();
	BorrowBook borrowBook = new BorrowBook();
	JPanel jp = new JPanel(new FlowLayout());
	UpdataPassW passW = new UpdataPassW();
	JInternalFrame if1 = alsoBook.AlsoBook();
	JInternalFrame if2 = borrowBook.BorrowBook();
	JInternalFrame if3 = passW.UpdataPassW();
	JButton jb1 = new JButton();
	JButton jb2 = new JButton();
	JButton jb3 = new JButton();
	
	public User() {
		Icon();
		setIconImage(Toolkit.getDefaultToolkit().createImage(
				User.class.getResource("/image/c.png")));

		/*-----------------面板设置----------------------  */
		setTitle("图书管理");
		setSize(700, 700);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		/*------------------设置模块------------------------*/
		jp.add(jb1);
		jp.add(jb2);
		jp.add(jb3);
		add(jp, BorderLayout.NORTH);
		jdp = new JDesktopPane();
		jdp.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		add(jdp, BorderLayout.CENTER);
		final JLabel jl = new JLabel();
		URL url = Administrator.class.getResource("/image/a.jpg");
		ImageIcon icon = new ImageIcon(url);
		jl.setIcon(icon);
		jl.setBounds(0, 0, icon.getIconWidth(), (icon.getIconHeight() - 50));
		jdp.add(jl);
		/*--------------------图片按钮---------------------*/
		URL[] urls = new URL[3];
		ImageIcon[] button = new ImageIcon[3];
		for (int i = 0; i < button.length; i++) {
			urls[i] = Administrator.class.getResource("/image/button2/p_jb"
					+ (i + 7) + "1.png");
			button[i] = new ImageIcon(urls[i]);
		}
		ImageIcon[] button2 = new ImageIcon[3];
		for (int i = 0; i < button2.length; i++) {
			urls[i] = Administrator.class.getResource("/image/button2/a_jb"
					+ (i + 7) + "1.png");
			button2[i] = new ImageIcon(urls[i]);
		}
		jb1.setIcon(button[1]);
		jb2.setIcon(button[0]);
		jb3.setIcon(button[2]);
		jb1.setRolloverIcon(button2[1]);
		jb2.setRolloverIcon(button2[0]);
		jb3.setRolloverIcon(button2[2]);
		jb1.setBackground(new Color(192, 192, 192));
		jb1.setBorder(null);
		jb2.setBackground(new Color(192, 192, 192));
		jb2.setBorder(null);
		jb3.setBackground(new Color(192, 192, 192));
		jb3.setBorder(null);

		/*-------------------放置模块-----------------------*/
		jdp.add(if1);
		jdp.add(if2);
		jdp.add(if3);

		/*--------------------按钮触发动作-----------------------*/
		jb1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (if1.isClosed())
					jdp.add(if1);
				if1.setVisible(true);
			}
		});

		jb2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (if2.isClosed())
					jdp.add(if2);
				if2.setVisible(true);
			}
		});

		jb3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (if3.isClosed())
					jdp.add(if3);
				if3.setVisible(true);
			}
		});

	}

	private TrayIcon trayicon;

	public void Icon() {
		if (SystemTray.isSupported()) {
			SystemTray systemTray = SystemTray.getSystemTray();
			String title = "欢迎使用图书管理系统";
			String company = "用户:" + Usermessage.userName + ",欢迎你的到来！";
			Image image = Toolkit.getDefaultToolkit().getImage(
					getClass().getResource("/image/b.png"));
			trayicon = new TrayIcon(image, company, createMenu());
			trayicon.addActionListener(this);
			try {
				systemTray.add(trayicon);
				trayicon.displayMessage(title, company, MessageType.INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private PopupMenu createMenu() {
		PopupMenu menu = new PopupMenu();
		MenuItem exit = new MenuItem("关闭");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				System.exit(0);
			}
		});
		MenuItem open = new MenuItem("打开");
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				if (!isVisible()) {
					setVisible(true);
					toFront();
				} else {
					toFront();
				}
			}
		});
		menu.add(open);
		menu.addSeparator();
		menu.add(exit);
		return menu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(trayicon)) {
			if (!isVisible()) {
				setVisible(true);
				toFront();
			}
		}
	}
}
