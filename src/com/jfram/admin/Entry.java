package com.jfram.admin;

import javax.swing.*;

import com.dao.DAO;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.awt.*;
import java.awt.event.*;

class Entry extends JFrame implements ActionListener {
	static DAO db = new DAO();
	JButton jb;
	JTextField jt;
	JTextField jt2, jt3;
	JCheckBox jc;
	JInternalFrame f = new JInternalFrame("", true, true, false, true);

	JInternalFrame Entry() {
		/*
		 * setIconImage(Toolkit.getDefaultToolkit().createImage(
		 * Entry.class.getResource("/image/c.png")));
		 */
		f.setTitle("���ͼ��");
		f.setSize(300, 220);
		// setLocationRelativeTo(null);
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		f.setLayout(null);// ���Բ���
		f.setResizable(false);

		JLabel jl1 = new JLabel("¼��ͼ��");
		jl1.setFont(new Font("", Font.BOLD, 20));
		JLabel jl2 = new JLabel("������");
		JLabel jl4 = new JLabel("�������ࣺ");
		jt = new JTextField();
		jt2 = new JTextField("������Բ���");
		jb = new JButton("���ͼ��");
		jc = new JCheckBox("����¼��");
		jt3 = new JTextField();
		JLabel jl5 = new JLabel("��");

		f.add(jl1);
		f.add(jl2);
		f.add(jl4);
		f.add(jt);
		f.add(jt2);
		f.add(jb);
		f.add(jc);
		f.add(jt3);
		f.add(jl5);

		jl1.setBounds(105, 10, 120, 40);
		jl2.setBounds(60, 50, 60, 25);
		jl4.setBounds(40, 80, 70, 25);
		jt.setBounds(100, 53, 120, 20);
		jt2.setBounds(100, 83, 120, 20);
		jb.setBounds(105, 140, 90, 30);
		jc.setBounds(35, 110, 77, 25);
		jt3.setBounds(112, 113, 30, 20);
		jl5.setBounds(142, 113, 20, 20);

		jb.addActionListener(this);
		jt2.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (jt2.getText().equals("")) {
					jt2.setText("������Բ���");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				jt2.setText("");
			}
		});
		// setVisible(true);
		return f;
	}

	public void actionPerformed(ActionEvent e) {
		String m = jt2.getText();
		int m1 = 1;
		try {
			if (jc.isSelected()) {
				m1 = Integer.valueOf(jt3.getText());
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "��������ȷ�����֣�");
		}
		if (!(jt.getText().equals(""))) {
			try {
				if (jt2.getText().equals("������Բ���")) {
					m = "";
				}
				db.insert(jt.getText(), m, m1);
				JOptionPane.showMessageDialog(null, "¼��ͼ��ɹ���");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "��������Ϊ�գ�");
		}
	}
}
