package com.jfram.user;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.dao.*;

import java.awt.*;
import java.awt.event.*;

class AlsoBook extends JFrame implements ActionListener {
	static DAO db = new DAO();
	JRadioButton a, b, c, d, e1;
	JTextField jt1, jt2;
	JButton sousuo, huanshu;
	String[] title = { "借书序号", "书号", "书名", "借书时间", "还书时间", "借书人", "状态" };
	JTable table = new JTable();
	JScrollPane js;
	Object[][] object = db.select2(5, null, 1);
	JComboBox<String> jc2;
	int row;
	String[] book = new String[3];
	JInternalFrame f = new JInternalFrame("", true, true, false, true);

	JInternalFrame AlsoBook() {
		// setIconImage(Toolkit.getDefaultToolkit().createImage(AlsoBook.class.getResource("/image/c.png")));
		f.setTitle("借书记录");
		f.setSize(500, 450);
		// setLocationRelativeTo(null);
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		f.setLayout(null);// 绝对布局
		f.setResizable(false);

		/*--------------设置模块--------------*/

		JLabel label2 = new JLabel("已借图书信息");
		label2.setBounds(170, 10, 430, 66);
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

		c = new JRadioButton("未还图书");
		c.setBounds(40, 105, 90, 18);
		f.add(c);

		d = new JRadioButton("已还图书");
		d.setBounds(140, 105, 90, 18);
		f.add(d);

		e1 = new JRadioButton("所有图书");
		e1.setBounds(230, 105, 90, 18);
		f.add(e1);

		table = new JTable(object, title);
		js = new JScrollPane();
		js.setViewportView(table);
		js.setBounds(40, 170, 420, 180);
		f.add(js);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				row = table.getSelectedRow();
				jt2.setText((String) object[row][1]);
				book[0] = (String) object[row][1];
				book[1] = (String) object[row][6];
				book[2] = (String) object[row][0];
				for (int i = 0; i < 3; i++) {
					System.out.println(book[i]);
				}
			}
		});

		ButtonGroup bg = new ButtonGroup();// 按钮组
		bg.add(c);
		bg.add(d);
		bg.add(e1);

		jc2 = new JComboBox<String>();
		jc2.addItem("书号");
		jc2.addItem("书名");
		jc2.setBounds(60, 135, 60, 20);
		f.add(jc2);
		/*-----------------------------还书-------------------------------*/

		jt2 = new JTextField("请单击上方列表中的书再点还书");
		jt2.setBounds(40, 360, 280, 22);
		f.add(jt2);
		jt2.setEnabled(false);

		huanshu = new JButton("还书");
		huanshu.setBounds(350, 360, 100, 22);
		f.add(huanshu);

		JLabel jl4 = new JLabel("*注：只有点取想要还的书再点还书才有效，自己输入书号无效。");
		f.add(jl4);
		jl4.setBounds(50, 380, 400, 22);
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

		jt2.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				jt2.setText("");
			}

			public void focusLost(FocusEvent e) {
				if (jt2.getText().equals("")) {
					jt2.setText("请单击上方列表中的书再点还书");
				}
			}
		});

		/*-------------------------搜索监听链接数据库-----------------------------------*/
		sousuo.addActionListener(this);
		huanshu.addActionListener(this);
		c.addActionListener(this);
		d.addActionListener(this);
		e1.addActionListener(this);
		return f;
	}

	int j = 0;
	DefaultTableModel model;

	@Override
	public void actionPerformed(ActionEvent e) {
		/****************** 查书按钮事件 *********************/
		if (jc2.getSelectedItem().equals("书名")) {
			j = 1;
		} else {
			j = 2;
		}
		if (e.getSource() == c) {
			j = 3;
			model = new DefaultTableModel(object = db.select2(j, null, 1),
					title);
			table.setModel(model);
		}
		if (e.getSource() == d) {
			j = 4;
			model = new DefaultTableModel(object = db.select2(j, null, 1),
					title);
			table.setModel(model);
		}
		if (e.getSource() == e1) {
			j = 5;
			model = new DefaultTableModel(object = db.select2(j, null, 1),
					title);
			table.setModel(model);
		}
		if (e.getSource() == sousuo) {
			switch (j) {
			case 1:
				model = new DefaultTableModel(object = db.select2(j,
						jt1.getText(), 1), title);
				table.setModel(model);
				break;
			case 2:
				model = new DefaultTableModel(object = db.select2(j,
						jt1.getText(), 1), title);
				table.setModel(model);
				break;
			}
		}
		/****************** 还书按钮事件 *********************/

		if (e.getSource() == huanshu) {
			try {
				if (!jt2.getText().equals("请单击上方列表中的书再点还书")) {
					if (!book[0].equals(jt2.getText())) {
						JOptionPane.showMessageDialog(null,
								"不需要自己键入书号。\n选择上面的书再还书按即可还书。");
					} else {
						if (book[1].equals("已还")) {
							JOptionPane.showMessageDialog(null, "此书已经还了。");
						} else {
							if (db.update3(1, book[0]) == 1) {
								db.update4(book[2]);
								DefaultTableModel model = new DefaultTableModel(
										object = db.select2(5, null, 1), title);
								table.setModel(model);
								JOptionPane.showMessageDialog(null, "成功还书");
							} else {
								JOptionPane.showMessageDialog(null, "此书已经还了。");
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "请选择你需要还的书再点还书。");
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null,
						"不需要自己键入书号。\n选择列表中的书再还书按即可还书。");
			}
		}
		f.repaint();
	}
}
