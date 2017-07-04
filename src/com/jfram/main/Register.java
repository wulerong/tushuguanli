package com.jfram.main;

import javax.swing.*;

import com.dao.DAO;
import java.awt.*;
import java.awt.event.*;

public class Register extends JFrame implements ActionListener {
	static DAO db = new DAO();
	JTextField jt1;
	JPasswordField jp;
	JButton jb1;
	JButton jb2;
	JPasswordField jp1;
	JLabel jl4 = new JLabel();
	JLabel jl5 = new JLabel();

	public Register() {
		setIconImage(Toolkit.getDefaultToolkit().createImage(
				Register.class.getResource("/image/c.png")));

		setTitle("ͼ�����ϵͳ");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setLayout(null);// ���Բ���
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);

		JLabel jL = new JLabel("ע �� �� �� ");
		jL.setFont(new Font("", Font.BOLD, 20));
		JLabel jl1 = new JLabel("�� �룺");
		JLabel jl2 = new JLabel("�� �ţ�");
		JLabel jl3 = new JLabel("ȷ�����룺");
		jt1 = new JTextField();
		jp = new JPasswordField();
		jp1 = new JPasswordField();
		jb1 = new JButton("ȷ��ע��");
		jb2 = new JButton("���ص�½");

		add(jL);
		add(jl1);
		add(jp);
		add(jp1);
		add(jb1);
		add(jl2);
		add(jl3);
		add(jt1);
		add(jb2);

		jL.setBounds(90, 10, 150, 40);
		jl3.setBounds(17, 105, 90, 20);
		jl2.setBounds(40, 45, 60, 20);
		jl1.setBounds(40, 75, 60, 20);
		jt1.setBounds(75, 45, 130, 20);
		jp.setBounds(75, 75, 130, 20);
		jp1.setBounds(75, 105, 130, 20);
		jb1.setBounds(50, 130, 90, 30);
		jb2.setBounds(160, 130, 90, 30);
		jl4.setBounds(210, 85, 140, 60);
		jl5.setBounds(210, 25, 140, 60);

		jb1.addActionListener(this);
		jb2.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jb1) {
			if (!(jt1.getText().equals(""))) {
				if (jp.getText().equals(jp1.getText())) {
					jl4.setText("����һ��");
					try {
						db.zhuce(jt1.getText(), jp.getText());
						jl5.setText("�ʺ�ע��ɹ�");
						add(jl5);
					} catch (Exception e1) {
						jl5.setText("�û���ע��");
						add(jl5);
						e1.printStackTrace();
					}
				} else {
					jl4.setText("���벻һ��");
					this.add(jl4);
				}
			} else {
				jl5.setText("�ʺŲ���Ϊ��");
				add(jl5);
			}
			repaint();
		}
		if (e.getSource() == jb2) {
			dispose();
			new Login();
		}
	}

}