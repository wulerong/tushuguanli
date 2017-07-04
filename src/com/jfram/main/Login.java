package com.jfram.main;

import javax.swing.*;
import com.dao.DAO;
import com.jfram.admin.Blacklist;
import com.message.user.*;
import com.jfram.admin.*;
import com.jfram.user.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {
	static DAO db = new DAO();
	Usermessage user = new Usermessage();
	JTextField jt1;
	JPasswordField jp; 
	JButton jb2;
	JButton jb1;
	JLabel jl5 = new JLabel("’ ∫≈√‹¬Î¥ÌŒÛ");

	Login() {
		setIconImage(Toolkit.getDefaultToolkit().createImage(
				Login.class.getResource("/image/c.png")));

		setTitle("Õº Èπ‹¿ÌœµÕ≥");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setLayout(null);// æ¯∂‘≤ºæ÷
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);

		JLabel jL = new JLabel("’  ∫≈ µ« ¬Ω ");
		jL.setFont(new Font("", Font.BOLD, 20));
		JLabel jl1 = new JLabel("√‹¬Î£∫");
		JLabel jl2 = new JLabel("’ ∫≈£∫");
		jt1 = new JTextField();
		jp = new JPasswordField();
		jb1 = new JButton("µ«¬Ω");
		jb2 = new JButton("◊¢≤·");

		add(jL);
		add(jl1);
		add(jp);
		add(jb1);
		add(jl2);
		add(jt1);
		add(jb2);

		jL.setBounds(90, 10, 150, 40);
		jl2.setBounds(40, 65, 60, 20);
		jl1.setBounds(40, 95, 60, 20);
		jt1.setBounds(80, 65, 130, 20);
		jp.setBounds(80, 95, 130, 20);
		jb1.setBounds(50, 130, 70, 30);
		jb2.setBounds(160, 130, 70, 30);
		jl5.setBounds(214, 60, 140, 30);

		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					jb1.doClick();
				}
			}

		});
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jb1) {
			switch (db.denglu(jt1.getText(), jp.getText())) {
			case 5:
				jl5.setText("’À∫≈“—∑‚Ω˚");
				add(jl5);
				break;
			case 1:
				user.setUserName(jt1.getText());
				new Administrator();
				dispose();
				break;
			case 2:
				user.setUserName(jt1.getText());
				new User();
				dispose();
				break;
			case 3:
				jl5.setText("√ª”–¥À’ ∫≈");
				add(jl5);
				break;
			case 4:
				jl5.setText("√‹¬Î¥ÌŒÛ");
				add(jl5);
				break;
			}
		}
		if (e.getSource() == jb2) {
			dispose();
			new Register();
		}
		repaint();
	}

	public static void main(String[] args) {
		Font font = new Font("", Font.PLAIN, 13);
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, font);
			}
		}
		new Login();
	}

}
