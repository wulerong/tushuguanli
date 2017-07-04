package com.jfram.admin;

import javax.swing.*;

import com.dao.DAO;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

class UpdataBook extends JFrame implements ActionListener {
	DAO db = new DAO();
	JRadioButton jr1;
	JRadioButton jr2;
	JButton jb1;
	JButton jb2;
	JTextField jt;
	JTextField jt2;
	JLabel jl3 = new JLabel();
	JLabel jl2;
	JInternalFrame f = new JInternalFrame("", true, true, false, true);

	JInternalFrame UpdataBook() {
		// setIconImage(Toolkit.getDefaultToolkit().createImage(UpdataBook.class.getResource("/image/c.png")));

		f.setTitle("更新图书");
		f.setSize(300, 300);
		// setLocationRelativeTo(null);
		f.setLayout(null);// 绝对布局
		f.setResizable(false);
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		JLabel jl1 = new JLabel("修改图书");
		jl1.setFont(new Font("", Font.BOLD, 20));
		jl2 = new JLabel("修改的书名:");
		ButtonGroup gb = new ButtonGroup();
		jr1 = new JRadioButton("按书名");
		jr2 = new JRadioButton("按书号");
		gb.add(jr1);
		gb.add(jr2);
		jt = new JTextField();
		jt2 = new JTextField();
		jb1 = new JButton("修改图书");
		jb2 = new JButton("删除图书");

		f.add(jl1);
		f.add(jr1);
		f.add(jr2);
		f.add(jt);
		f.add(jb1);
		f.add(jb2);

		jl1.setBounds(105, 10, 120, 40);
		jr1.setBounds(20, 70, 70, 20);
		jr2.setBounds(20, 100, 70, 20);
		jt.setBounds(90, 85, 150, 25);
		jl2.setBounds(20, 140, 90, 25);
		jt2.setBounds(90, 140, 150, 25);
		jl3.setBounds(100, 230, 130, 20);

		jb1.setBounds(40, 190, 90, 30);
		jb2.setBounds(170, 190, 90, 30);

		jr1.addActionListener(this);
		jr2.addActionListener(this);
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		return f;

	}

	int j = 0;
	int i = 0;

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jr1) {
			j = 0;
		}
		if (e.getSource() == jr2) {
			j = 1;
		}
		if (e.getSource() == jb1) {
			f.remove(jl3);
			if (i == 1) {
				i--;
				f.remove(jl2);
				f.remove(jt2);
				f.add(jb2);
				jb1.setText("修改图书");
				try {
					switch (db.update1(j, jt2.getText(), jt.getText())) {
					case 0:
						jl3.setText("不存在此书或者id");
						break;
					case 1:
						jl3.setText("修改成功");
						break;
					}
				} catch (SQLException e1) {
					jl3.setText("修改的书名已经存在");
					e1.printStackTrace();
				}
				f.add(jl3);
			} else {
				i++;
				f.add(jl2);
				f.add(jt2);
				f.remove(jb2);
				jb1.setText("确定修改");
			}
			f.repaint();
		}
		if (e.getSource() == jb2) {
			f.remove(jl3);
			if (jt.getText().equals("")) {
				jl3.setText("书名不可为空");
			} else {

				switch (db.del1(j, jt.getText())) {
				case 0:
					jl3.setText("此书不存在");
					break;
				case 1:
					jl3.setText("成功删除此书");
					break;

				}
			}
			f.add(jl3);
			f.repaint();
		}
	}
}