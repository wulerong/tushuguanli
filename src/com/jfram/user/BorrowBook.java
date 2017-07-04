package com.jfram.user;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import com.dao.DAO;

public class BorrowBook extends JFrame implements ActionListener {
	static DAO db = new DAO();
	JRadioButton a, b, c, d, e1;
	JTextField jt1, jt2;
	JButton sousuo, jieshu;
	String[] title = { "���", "����", "����", "��������" };
	JTable table = new JTable();
	JScrollPane js;
	Object[][] object = db.select1(5, null, 4);
	JComboBox<String> jc2;
	int row;
	String[] book = new String[3];
	JInternalFrame f = new JInternalFrame("", true, true, false, true);

	JInternalFrame BorrowBook() {
		// setIconImage(Toolkit.getDefaultToolkit().createImage(BorrowBook.class.getResource("/image/c.png")));

		f.setTitle("�鿴ͼ����Ϣ");
		f.setSize(500, 450);
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
		js.setBounds(40, 170, 420, 180);
		f.add(js);
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				row = table.getSelectedRow();
				jt2.setText((String) object[row][0]);
				for (int i = 0; i < 3; i++) {
					book[i] = (String) object[row][i];
				}
			}
		});

		ButtonGroup bg = new ButtonGroup();// ��ť��
		bg.add(c);
		bg.add(d);
		bg.add(e1);

		jc2 = new JComboBox<String>();
		jc2.addItem("���");
		jc2.addItem("����");
		jc2.setBounds(60, 135, 60, 20);
		f.add(jc2);
		/*-----------------------------����-------------------------------*/

		jt2 = new JTextField("�뵥���Ϸ��б��ٵ����");
		jt2.setBounds(40, 360, 280, 22);
		f.add(jt2);
		jt2.setEnabled(false);

		jieshu = new JButton("����");
		jieshu.setBounds(350, 360, 100, 22);
		f.add(jieshu);

		JLabel jl4 = new JLabel("*ע��ֻ�е�ȡ��Ҫ��ȡ�����ٵ�������Ч���Լ����������Ч��");
		f.add(jl4);
		jl4.setBounds(50, 380, 400, 22);
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

		jt2.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				jt2.setText("");
			}

			public void focusLost(FocusEvent e) {
				if (jt2.getText().equals("")) {
					jt2.setText("�뵥���Ϸ��б��ٵ����");
				}
			}
		});

		/*-------------------------���������������ݿ�-----------------------------------*/
		sousuo.addActionListener(this);
		jieshu.addActionListener(this);
		c.addActionListener(this);
		d.addActionListener(this);
		e1.addActionListener(this);
		return f;
	}

	int j = 0;
	DefaultTableModel model;

	@Override
	public void actionPerformed(ActionEvent e) {
		/****************** ���鰴ť�¼� *********************/
		if (jc2.getSelectedItem().equals("����")) {
			j = 1;
		} else {
			j = 2;
		}
		if (e.getSource() == c) {
			j = 3;
			model = new DefaultTableModel(object = db.select1(j, null, 4),
					title);
			table.setModel(model);
		}
		if (e.getSource() == d) {
			j = 4;
			model = new DefaultTableModel(object = db.select1(j, null, 4),
					title);
			table.setModel(model);
		}
		if (e.getSource() == e1) {
			j = 5;
			model = new DefaultTableModel(object = db.select1(j, null, 4),
					title);
			table.setModel(model);
		}
		if (e.getSource() == sousuo) {
			switch (j) {
			case 1:
				model = new DefaultTableModel(object = db.select1(j,
						jt1.getText(), 4), title);
				table.setModel(model);
				break;
			case 2:
				model = new DefaultTableModel(object = db.select1(j,
						jt1.getText(), 4), title);
				table.setModel(model);
				break;
			}
		}
		/****************** ���鰴ť�¼� *********************/

		if (e.getSource() == jieshu) {
			try {
				if (!jt2.getText().equals("�뵥���Ϸ��б��ٵ����")) {
					if (!book[0].equals(jt2.getText())) {
						JOptionPane.showMessageDialog(null,
								"����Ҫ�Լ�������š�\nѡ����������ٽ��鰴���ɽ��顣");
					} else {
						if (book[2].equals("�ѽ��")) {
							JOptionPane.showMessageDialog(null, "�����Ѿ��������");
						} else {
							if (db.update3(0, book[0]) == 1) {
								DefaultTableModel model = new DefaultTableModel(
										object = db.select1(5, null, 4), title);
								table.setModel(model);
								JOptionPane.showMessageDialog(null, "�ɹ�����");
								db.insert2(book[0], book[1]);
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "��ѡ������Ҫ�����ٵ���顣");
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null,
						"����Ҫ�Լ�������š�\nѡ����������ٽ��鰴���ɽ��顣");
			}
		}
		f.repaint();
	}
}
