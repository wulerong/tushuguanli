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
	String[] title = { "书号", "书名", "借阅", "所属分类", "借书人", "借书时间" };
	JTable table = new JTable();
	JScrollPane js;
	Object[][] object = db.select1(5, null, 6);
	JComboBox<String> jc2;
	JInternalFrame f = new JInternalFrame("", true, true, false, true);

	JInternalFrame CheckBook() {
		// setIconImage(Toolkit.getDefaultToolkit().createImage(CheckBook.class.getResource("/image/c.png")));

		f.setTitle("查看图书");
		f.setSize(500, 370);
		// setLocationRelativeTo(null);
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		f.setLayout(null);// 绝对布局
		f.setResizable(false);

		/*--------------设置模块--------------*/

		JLabel label2 = new JLabel("查看图书信息");
		label2.setBounds(160, 10, 430, 66);
		f.add(label2);
		label2.setFont(new Font("", Font.BOLD, 28));

		JLabel label3 = new JLabel("请你选择其中一种方式进行查询：");
		label3.setBounds(40, 80, 210, 18);
		f.add(label3);

		jt1 = new JTextField("请输入书名或书号进行查询");
		jt1.setBounds(140, 135, 160, 20);
		f.add(jt1);

		sousuo = new JButton("查询");
		sousuo.setBounds(305, 135, 60, 20);
		f.add(sousuo);

		c = new JRadioButton("未借出书");
		c.setBounds(40, 105, 90, 18);
		f.add(c);

		d = new JRadioButton("已借出书");
		d.setBounds(140, 105, 90, 18);
		f.add(d);

		e1 = new JRadioButton("所有图书");
		e1.setBounds(230, 105, 90, 18);
		f.add(e1);

		table = new JTable(object, title);
		js = new JScrollPane();
		js.setViewportView(table);
		js.setBounds(40, 170, 420, 140);
		f.add(js);
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		ButtonGroup bg = new ButtonGroup();// 按钮组
		bg.add(c);
		bg.add(d);
		bg.add(e1);

		jc2 = new JComboBox<String>();
		jc2.addItem("书号");
		jc2.addItem("书名");
		jc2.setBounds(60, 135, 60, 20);
		f.add(jc2);

		/*-----------------------------焦点监听（鼠标点击文本框提示语消除）-------------------------------*/
		jt1.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (jt1.getText().equals("")) {
					jt1.setText("请输入书名或书号进行查询");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				jt1.setText("");
			}
		});

		/*-------------------------搜索监听链接数据库-----------------------------------*/
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
		if (jc2.getSelectedItem().equals("书名")) {
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
		/****************** 按钮事件 *********************/
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
