package com.jfram.admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.dao.*;

import java.awt.*;
import java.awt.event.*;

class CheckBook extends JFrame implements ActionListener {
	static DAO db = new DAO();
	JRadioButton c, d, e1;
	JTextField jt1;
	JButton sousuo;
	String[] title = { "���", "����", "����", "��������", "������", "����ʱ��" };
	JTable table = new JTable();
	JScrollPane js;
	Object[][] object = db.select1(5, null, 6);
	JComboBox<String> jc2;
	JInternalFrame f = new JInternalFrame("", true, true, false, true);

	JInternalFrame CheckBook() {
		// setIconImage(Toolkit.getDefaultToolkit().createImage(CheckBook.class.getResource("/image/c.png")));

		f.setTitle("�鿴ͼ��");
		f.setSize(500, 370);
		// setLocationRelativeTo(null);
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		f.setLayout(null);// ���Բ���
		f.setResizable(false);

		/*--------------����ģ��--------------*/

		JLabel label2 = new JLabel("�鿴ͼ����Ϣ");
		label2.setBounds(160, 10, 430, 66);
		f.add(label2);
		label2.setFont(new Font("", Font.BOLD, 28));

		JLabel label3 = new JLabel("����ѡ������һ�ַ�ʽ���в�ѯ��");
		label3.setBounds(40, 80, 210, 18);
		f.add(label3);

		jt1 = new JTextField("��������������Ž��в�ѯ");
		jt1.setBounds(140, 135, 160, 20);
		f.add(jt1);

		sousuo = new JButton("��ѯ");
		sousuo.setBounds(305, 135, 60, 20);
		f.add(sousuo);

		c = new JRadioButton("δ�����");
		c.setBounds(40, 105, 90, 18);
		f.add(c);

		d = new JRadioButton("�ѽ����");
		d.setBounds(140, 105, 90, 18);
		f.add(d);

		e1 = new JRadioButton("����ͼ��");
		e1.setBounds(230, 105, 90, 18);
		f.add(e1);

		table = new JTable(object, title);
		js = new JScrollPane();
		js.setViewportView(table);
		js.setBounds(40, 170, 420, 140);
		f.add(js);
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		ButtonGroup bg = new ButtonGroup();// ��ť��
		bg.add(c);
		bg.add(d);
		bg.add(e1);

		jc2 = new JComboBox<String>();
		jc2.addItem("���");
		jc2.addItem("����");
		jc2.setBounds(60, 135, 60, 20);
		f.add(jc2);

		/*-----------------------------���������������ı�����ʾ��������-------------------------------*/
		jt1.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (jt1.getText().equals("")) {
					jt1.setText("��������������Ž��в�ѯ");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				jt1.setText("");
			}
		});

		/*-------------------------���������������ݿ�-----------------------------------*/
		sousuo.addActionListener(this);
		c.addActionListener(this);
		d.addActionListener(this);
		e1.addActionListener(this);
		// setVisible(true);
		return f;
	}

	int j = 0;
	DefaultTableModel model;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (jc2.getSelectedItem().equals("����")) {
			j = 1;
		} else {
			j = 2;
		}
		if (e.getSource() == c) {
			j = 3;
			model = new DefaultTableModel(db.select1(j, null, 6), title);
			table.setModel(model);
		}
		if (e.getSource() == d) {
			j = 4;
			model = new DefaultTableModel(db.select1(j, null, 6), title);
			table.setModel(model);
		}
		if (e.getSource() == e1) {
			j = 5;
			model = new DefaultTableModel(db.select1(j, null, 6), title);
			table.setModel(model);
		}
		/****************** ��ť�¼� *********************/
		if (e.getSource() == sousuo) {
			switch (j) {
			case 1:
				model = new DefaultTableModel(db.select1(j, jt1.getText(), 6),
						title);
				table.setModel(model);
				break;
			case 2:
				model = new DefaultTableModel(db.select1(j, jt1.getText(), 6),
						title);
				table.setModel(model);
				break;
			}
			f.repaint();
		}
	}
}
