package com.jfram.admin;

import javax.swing.*;

import com.dao.DAO;

import java.awt.*;
import java.awt.event.*;

class UpdataPass extends JFrame implements ActionListener {
	static DAO db = new DAO();
	JTextField jt1;
	JPasswordField jp1;
	JPasswordField jp2;
	JPasswordField jp3;
	JLabel jl6 = new JLabel("�޸�����ɹ�");
	JInternalFrame f = new JInternalFrame("", true, true, false, true);

	JInternalFrame UpdataPass() {
		/*
		 * setIconImage(Toolkit.getDefaultToolkit().createImage(
		 * UpdataPass.class.getResource("/image/c.png")));
		 */

		f.setTitle("�޸�����");
		f.setSize(300, 300);
		// setLocationRelativeTo(null);
		f.setLayout(null);// ���Բ���
		f.setResizable(false);
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		JLabel jl1 = new JLabel("�޸�����");
		jl1.setFont(new Font("", Font.BOLD, 20));
		JLabel jl2 = new JLabel("�����룺");
		JLabel jl3 = new JLabel("�����룺");
		JLabel jl4 = new JLabel("ȷ�����룺");
		JLabel jl5 = new JLabel("�ʺţ�");
		jt1 = new JTextField();
		jp1 = new JPasswordField();
		jp2 = new JPasswordField();
		jp3 = new JPasswordField();
		JButton jb = new JButton("�ύ");

		f.add(jl1);
		f.add(jl2);
		f.add(jl3);
		f.add(jl4);
		f.add(jl5);
		f.add(jt1);
		f.add(jp1);
		f.add(jp2);
		f.add(jp3);
		f.add(jb);

		jl1.setBounds(105, 10, 120, 40);
		jl2.setBounds(35, 105, 60, 25);
		jl3.setBounds(35, 140, 60, 25);
		jl4.setBounds(35, 175, 65, 25);
		jl5.setBounds(35, 70, 60, 25);
		jt1.setBounds(100, 70, 120, 25);
		jp1.setBounds(100, 105, 120, 25);
		jp2.setBounds(100, 140, 120, 25);
		jp3.setBounds(100, 175, 120, 25);
		jb.setBounds(105, 210, 90, 30);
		jl6.setBounds(105, 240, 120, 30);

		jb.addActionListener(this);
		return f;
	}

	public void actionPerformed(ActionEvent e) {
		if (jp2.getText().equals(jp3.getText())) {
			switch (db.update2(jt1.getText(), jp1.getText(), jp2.getText())) {
			case 0:
				jl6.setText("�ʺŻ����������");
				break;
			case 1:
				jl6.setText("�޸ĳɹ�");
				jt1.setText("");
				jp1.setText("");
				jp2.setText("");
				jp3.setText("");
				break;
			}
		} else {
			jl6.setText("���������벻һ��");
		}
		f.add(jl6);
		f.repaint();
	}
}