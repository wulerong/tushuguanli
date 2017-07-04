package com.jfram.admin;

import com.message.user.Usermessage;
import com.sun.java.swing.plaf.windows.resources.windows;
import javax.swing.*;

import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.awt.event.*;
import java.net.URL;

public class Administrator extends JFrame implements ActionListener {
	private TrayIcon trayicon;
	Entry entry = new Entry();
	CheckRecord checkRecord = new CheckRecord();
	CheckBook book = new CheckBook();
	UpdataBook updataBook = new UpdataBook();
	UpdataPass updataPass = new UpdataPass();
	Blacklist blacklist = new Blacklist();
	JDesktopPane jdp = null;
	JPanel jp = new JPanel(new FlowLayout());
	JInternalFrame if1 = entry.Entry();
	JInternalFrame if2 = checkRecord.CheckRecord();
	JInternalFrame if3 = book.CheckBook();
	JInternalFrame if4 = updataBook.UpdataBook();
	JInternalFrame if5 = updataPass.UpdataPass();
	JInternalFrame if6 = blacklist.Blacklist();
	JButton jb1 = new JButton();
	JButton jb2 = new JButton();
	JButton jb3 = new JButton();
	JButton jb4 = new JButton();
	JButton jb5 = new JButton();
	JButton jb6 = new JButton();

	/*-----------------管理员模块-----------------------*/
	public Administrator() {
		setIconImage(Toolkit.getDefaultToolkit().createImage(
				Administrator.class.getResource("/image/c.png")));
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Icon();
		setTitle("图书管理");
		setSize(700, 700);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setVisible(true);
		setResizable(false);
		/*-------------------设置模块----------------------*/
		jp.add(jb1);
		jp.add(jb2);
		jp.add(jb3);
		jp.add(jb4);
		jp.add(jb5);
		jp.add(jb6);
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
		URL[] urls = new URL[6];
		ImageIcon[] buttonicon = new ImageIcon[6];
		for (int i = 0; i < buttonicon.length; i++) {
			urls[i] = Administrator.class.getResource("/image/button2/p_jb"
					+ (i + 1) + "1.png");
			buttonicon[i] = new ImageIcon(urls[i]);
		}
		ImageIcon[] buttonicon2 = new ImageIcon[6];
		for (int i = 0; i < buttonicon2.length; i++) {
			urls[i] = Administrator.class.getResource("/image/button2/a_jb"
					+ (i + 1) + "1.png");
			buttonicon2[i] = new ImageIcon(urls[i]);
		}
		jb1.setIcon(buttonicon[0]);
		jb2.setIcon(buttonicon[1]);
		jb3.setIcon(buttonicon[2]);
		jb4.setIcon(buttonicon[3]);
		jb5.setIcon(buttonicon[4]);
		jb6.setIcon(buttonicon[5]);
		jb1.setRolloverIcon(buttonicon2[0]);
		jb2.setRolloverIcon(buttonicon2[1]);
		jb3.setRolloverIcon(buttonicon2[2]);
		jb4.setRolloverIcon(buttonicon2[3]);
		jb5.setRolloverIcon(buttonicon2[4]);
		jb6.setRolloverIcon(buttonicon2[5]);

		/*--------------------放置模块---------------------*/
		jb1.setBackground(new Color(192, 192, 192));
		jb1.setBorder(null);
		jb2.setBackground(new Color(192, 192, 192));
		jb2.setBorder(null);
		jb3.setBackground(new Color(192, 192, 192));
		jb3.setBorder(null);
		jb4.setBackground(new Color(192, 192, 192));
		jb4.setBorder(null);
		jb5.setBackground(new Color(192, 192, 192));
		jb5.setBorder(null);
		jb6.setBackground(new Color(192, 192, 192));
		jb6.setBorder(null);
		jdp.add(if1);
		jdp.add(if2);
		jdp.add(if3);
		jdp.add(if4);
		jdp.add(if5);
		jdp.add(if6);
		/*-------------------按钮功能-------------------*/
		jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (if1.isClosed())
					jdp.add(if1);
				if1.setVisible(true);
			}
		});
		jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (if2.isClosed())
					jdp.add(if2);
				if2.setVisible(true);
			}
		});
		jb3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (if3.isClosed())
					jdp.add(if3);
				if3.setVisible(true);
			}
		});
		jb4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (if4.isClosed())
					jdp.add(if4);
				if4.setVisible(true);
			}
		});
		jb5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (if5.isClosed())
					jdp.add(if5);
				if5.setVisible(true);
			}
		});
		jb6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (if6.isClosed())
					jdp.add(if6);
				if6.setVisible(true);
			}
		});

	}
	/*-------------------托盘系统-------------------*/
	public void Icon() {
		if (SystemTray.isSupported()) {
			SystemTray systemTray = SystemTray.getSystemTray();
			String title = "欢迎使用图书管理系统";
			String company = "管理员：" + Usermessage.userName + ",欢迎你的到来！";
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
		if (e.getSource().equals(trayicon)) {
			if (!isVisible()) {
				setVisible(true);
				toFront();
			}
		}
	}
}