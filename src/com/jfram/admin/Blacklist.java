package com.jfram.admin;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import com.dao.DAO;

public class Blacklist extends JFrame implements ActionListener {
	static DAO db = new DAO();
	JButton addbutton, delbutton, select;
	JScrollPane add, del;
	JTable addtable, deltable;
	Object[][] addobject, delobject;
	String[] add1 = { "�û�" }, del1 = { "������" };
	JTextField jt = new JTextField("��ѯȫ��");
	String m = "", n = "";
	int row = 0;
	JInternalFrame f = new JInternalFrame("", true, true, false, true);

	JInternalFrame Blacklist() {
		/*
		 * setIconImage(Toolkit.getDefaultToolkit().createImage(
		 * Blacklist.class.getResource("/image/c.png")));
		 */

		f.setTitle("������");
		f.setSize(300, 260);
		// setLocationRelativeTo(null);
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		f.setLayout(null);
		f.setResizable(false);

		addtable = new JTable(addobject = db.select3(0, null), add1);
		deltable = new JTable(delobject = db.select3(1, null), del1);
		JLabel jL = new JLabel("�� �� �� ");
		jL.setFont(new Font("", Font.BOLD, 20));
		JLabel jl = new JLabel("��ѯ�û�");
		add = new JScrollPane(addtable);
		del = new JScrollPane(deltable);
		select = new JButton("��ѯ");
		addbutton = new JButton("���");
		delbutton = new JButton("�Ƴ�");

		f.add(jL);
		f.add(jl);
		f.add(select);
		f.add(add);
		f.add(del);
		f.add(addbutton);
		f.add(delbutton);
		f.add(jt);

		jL.setBounds(110, 2, 90, 40);
		add.setBounds(20, 40, 100, 150);
		del.setBounds(180, 40, 100, 150);
		addbutton.setBounds(120, 80, 60, 20);
		delbutton.setBounds(120, 140, 60, 20);
		jl.setBounds(20, 200, 60, 20);
		jt.setBounds(75, 200, 130, 20);
		select.setBounds(210, 200, 60, 20);

		addtable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				row = addtable.getSelectedRow();
				m = (String) addobject[row][0];
			}
		});
		deltable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				row = deltable.getSelectedRow();
				n = (String) delobject[row][0];
			}
		});

		addbutton.addActionListener(this);
		delbutton.addActionListener(this);
		select.addActionListener(this);

		jt.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (jt.getText().equals("")) {
					jt.setText("��ѯȫ��");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				jt.setText("");
			}
		});
		return f;
	}

	DefaultTableModel model;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == select) {
			if (jt.getText().equals("��ѯȫ��")) {
				model = new DefaultTableModel(addobject = db.select3(0, null),
						add1);
				addtable.setModel(model);
				model = new DefaultTableModel(delobject = db.select3(1, null),
						del1);
				deltable.setModel(model);
			} else {
				model = new DefaultTableModel(addobject = db.select3(2,
						jt.getText()), add1);
				addtable.setModel(model);
				model = new DefaultTableModel(delobject = db.select3(3,
						jt.getText()), del1);
				deltable.setModel(model);
			}
		}
		if (e.getSource() == addbutton) {
			if (!m.equals("")) {
				db.update5(m, 1);
				m = "";
				model = new DefaultTableModel(addobject = db.select3(0, null),
						add1);
				addtable.setModel(model);
				model = new DefaultTableModel(delobject = db.select3(1, null),
						del1);
				deltable.setModel(model);
				JOptionPane.showMessageDialog(null, "����������ɹ���\n����ý�����ĺ���");
			} else {
				JOptionPane.showMessageDialog(null,
						"Are you kidding me?\n���ң���ѡ��û��\n��ȥ�����~");
			}
		}
		if (e.getSource() == delbutton) {
			if (!n.equals("")) {
				db.update5(n, 0);
				n = "";
				model = new DefaultTableModel(addobject = db.select3(0, null),
						add1);
				addtable.setModel(model);
				model = new DefaultTableModel(delobject = db.select3(1, null),
						del1);
				deltable.setModel(model);
				JOptionPane.showMessageDialog(null, "�ɹ��Ӻ�����������");
			} else {
				JOptionPane.showMessageDialog(null,
						"Are you kidding me?\n���ң���ѡ��û��\n��ȥ���ұ�~");
			}
		}
		repaint();
	}

}
