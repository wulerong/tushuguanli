package com.jfram.user;

import javax.swing.*;

import com.dao.DAO;
import com.message.user.Usermessage;

import java.awt.*;
import java.awt.event.*;

class UpdataPassW extends JFrame implements ActionListener {
	static DAO db = new DAO();
	JPasswordField jp1;
	JPasswordField jp2;
	JPasswordField jp3;
	JLabel jl6 = new JLabel("�޸�����ɹ�");
	JInternalFrame f = new JInternalFrame("", true, true, false, true);

	JInternalFrame UpdataPassW() {
		/*
		 * setIconImage(Toolkit.getDefaultToolkit().createImage(
		 * UpdataPassW.class.getResource("/image/c.png")));
		 */

		f.setTitle("�޸�����");
		f.setSize(300, 270);
		// setLocationRelativeTo(null);
		f.setLayout(null);// ���Բ���
		f.setResizable(false);
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		JLabel jl1 = new JLabel("�޸�����");
		jl1.setFont(new Font("", Font.BOLD, 20));
		JLabel jl2 = new JLabel("�����룺");
		JLabel jl3 = new JLabel("�����룺");
		JLabel jl4 = new JLabel("ȷ�����룺");
		jp1 = new JPasswordField();
		jp2 = new JPasswordField();
		jp3 = new JPasswordField();
		JButton jb = new JButton("�ύ");

		f.add(jl1);
		f.add(jl2);
		f.add(jl3);
		f.add(jl4);
		f.add(jp1);
		f.add(jp2);
		f.add(jp3);
		f.add(jb);

		jl1.setBounds(105, 10, 120, 40);
		jl2.setBounds(35, 75, 60, 25);
		jl3.setBounds(35, 110, 60, 25);
		jl4.setBounds(35, 145, 65, 25);
		jp1.setBounds(100, 75, 120, 25);
		jp2.setBounds(100, 110, 120, 25);
		jp3.setBounds(100, 145, 120, 25);
		jb.setBounds(105, 180, 90, 30);
		jl6.setBounds(105, 210, 120, 30);

		jb.addActionListener(this);
		return f;
	}

	public void actionPerformed(ActionEvent e) {
		if (jp2.getText().equals(jp3.getText())) {
			switch (db.update2(Usermessage.userName, jp1.getText(),
					jp2.getText())) {
			case 0:
				jl6.setText("�������");
				break;
			case 1:
				jl6.setText("�޸ĳɹ�");
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