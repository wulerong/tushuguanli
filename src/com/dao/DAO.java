package com.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import com.message.user.Usermessage;

public class DAO {
	/************** �������� **********/
	static Connection conn = null;
	static Statement st = null;
	static ResultSet re = null;
	static PreparedStatement pr = null;

	/************** �������ݿ� **********/
	public void getconn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "���ݿ���������ʧ�ܡ�");
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/tushuguanli", "root",
					"wulerong");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"���ݿ�����ʧ�ܣ�����ϵ����Ա������ݿ⡣\n" + e.getMessage());
			e.printStackTrace();
		}
	}

	/************** �ر����ݿ� **********/
	public static void guan() throws SQLException {
		if (conn != null) {
			conn.close();
		}
		if (st != null) {
			st.close();
		}
		if (re != null) {
			re.close();
		}
		if (pr != null) {
			pr.close();
		}
	}

	/************** �鿴���ݿ��м�����¼ **********/
	public int select() {
		getconn();
		int l = 0;
		try {
			st = conn.createStatement();
			re = st.executeQuery("select count(*) as au_count from tushu");
			re.next();
			l = re.getInt("au_count");
			guan();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}

	/************** ��ѯ���ݿ� **********/
	public Object[][] select1(int j, String book, int line) {
		getconn();
		String sql = null;
		switch (j) {
		case 1:
			sql = "select * from tushu where name like '%" + book + "%'";
			break;
		case 2:
			sql = "select * from tushu where id=" + book + ";";
			break;
		case 3:
			sql = "select * from tushu where jieyue=1;";
			break;
		case 4:
			sql = "select * from tushu where jieyue=0;";
			break;
		case 5:
			sql = "select * from tushu;";
			break;
		}
		try {
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			re = st.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Object[][] data = null;
		int j1 = 0;
		int i = 0;
		try {
			re.last();
			j1 = re.getRow();
			data = new Object[j1][line];
			re.beforeFirst();
			int k = 0;
			while (re.next()) {
				for (k = 1; k <= line; k++) {
					data[i][k - 1] = re.getString(k);
					if (k == 3)
						if (re.getInt(3) == 1) {
							data[i][2] = "δ���";
						} else {
							data[i][2] = "�ѽ��";
						}
				}
				i++;
			}
		} catch (SQLException ee) {
			ee.printStackTrace();
		}
		try {
			guan();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	/************** ��ѯ�����¼ **********/
	public Object[][] select2(int j, String book, int n) {
		getconn();
		String sql = null;
		String sql2 = null;
		if (n == 1) {
			if (j == 5) {
				sql2 = " where username='" + Usermessage.userName + "'";
			} else
				sql2 = "and username='" + Usermessage.userName + "'";
		} else {
			sql2 = "";
		}
		switch (j) {
		case 1:
			sql = "select * from record where bookname like '%" + book + "%' "
					+ sql2 + ";";
			break;
		case 2:
			sql = "select * from record where bookid=" + book + " " + sql2
					+ ";";
			break;
		case 3:
			sql = "select * from record where jieyue=0 " + sql2 + ";";
			break;
		case 4:
			sql = "select * from record where jieyue=1 " + sql2 + ";";
			break;
		case 5:
			sql = "select * from record" + sql2 + ";";
			break;
		case 6:
			sql = "select * from record where username='" + book + "';";
			break;
		}
		try {
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			re = st.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Object[][] data = null;
		int j1 = 0;
		int i = 0;
		try {
			re.last();
			j1 = re.getRow();
			data = new Object[j1][7];
			re.beforeFirst();
			int k = 0;
			while (re.next()) {
				for (k = 1; k <= 7; k++) {
					if (k != 5) {
						data[i][k - 1] = re.getString(k);
					} else if (re.getString(k).equals("2000-01-01")) {
						data[i][k - 1] = "�޻���ʱ��";
					} else {
						data[i][k - 1] = re.getString(k);
					}
					if (k == 7)
						if (re.getInt(7) == 1) {
							data[i][6] = "�ѻ�";
						} else {
							data[i][6] = "δ��";
						}
				}
				i++;
			}
		} catch (SQLException ee) {
			ee.printStackTrace();
		}
		try {
			guan();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	/************** ��ѯ������ **********/
	public Object[][] select3(int flag, String userName) {
		getconn();
		Object[][] data = null;
		String sql = "";
		switch (flag) {
		case 0:
		case 1:
			sql = "select username from user where hei=" + flag + "";
			break;
		case 2:
			sql = "select username from user where hei=0 and username='"
					+ userName + "'";
			break;
		case 3:
			sql = "select username from user where hei=1 and username='"
					+ userName + "'";
			break;
		}

		int j1 = 0;
		int i = 0;
		try {
			st = conn.createStatement();
			re = st.executeQuery(sql);
			re.last();
			j1 = re.getRow();
			data = new Object[j1][1];
			re.beforeFirst();
			while (re.next()) {
				data[i][0] = re.getString(1);
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			guan();
		} catch (Exception e) {
		}
		return data;
	}

	/************** ¼��ͼ�� **********/
	public void insert(String name, String fenlei, int m1) throws SQLException {
		getconn();
		String Name = name;
		String Fenlei = fenlei;
		String sql = "insert into tushu(name,fenlei) value(?,?);";

		for (int i = 0; i < m1; i++) {
			pr = conn.prepareStatement(sql);
			pr.setString(1, Name);
			pr.setString(2, Fenlei);
			pr.executeUpdate();
		}
		try {
			guan();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/************** ��ӽ����¼ **********/
	public void insert2(String bookid, String bookname) {
		getconn();
		String sql = "insert into record(bookid,bookname,borrow,username) value(?,?,?,?);";

		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			pr = conn.prepareStatement(sql);
			pr.setString(1, bookid);
			pr.setString(2, bookname);
			pr.setString(3, format.format(new Date()));
			pr.setString(4, Usermessage.userName);
			pr.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			guan();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/************** �޸�ͼ������ **********/
	public int update1(int j, String name1, String name2) throws SQLException {
		getconn();
		String Name1 = name1;
		String Name2 = name2;
		String sql = null;
		switch (j) {
		case 0:
			sql = "update tushu set name=? where name=?";
			break;
		case 1:
			sql = "update tushu set name=? where id=?";
			break;
		}

		pr = conn.prepareStatement(sql);
		pr.setString(1, Name1);
		pr.setString(2, Name2);
		int i = pr.executeUpdate();
		guan();
		return i;

	}

	/************** �޸����� **********/

	public int update2(String user, String pass, String newpass) {
		getconn();
		String User = user;
		String Pass = pass;
		String NewPass = newpass;
		String sql = "update user set pass=? where username=? and pass=?;";
		try {
			pr = conn.prepareStatement(sql);
			pr.setString(1, NewPass);
			pr.setString(2, User);
			pr.setString(3, Pass);
			int i = pr.executeUpdate();
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				guan();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	/************** �軹ͼ�� **********/
	public int update3(int n, String id) {
		getconn();
		int m = 0;
		String sql = null;
		if (n == 1) {
			sql = "update tushu set jieyue=?,jieyueren=?,date=? where id=" + id
					+ " and jieyueren='" + Usermessage.userName + "'";
		} else {
			sql = "update tushu set jieyue=?,jieyueren=?,date=? where id=" + id
					+ "";
		}
		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, n);
			if (n == 1) {
				pr.setString(2, null);
				pr.setString(3, null);
			} else {
				pr.setString(2, Usermessage.userName);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				pr.setString(3, format.format(new Date()));
			}
			m = pr.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			guan();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}

	/************** ���»����¼ **********/
	public void update4(String id) {
		getconn();
		try {
			String sql = "update record set jieyue=1,even=? where Id=" + id
					+ "";
			pr = conn.prepareStatement(sql);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			pr.setString(1, format.format(new Date()));
			pr.executeUpdate();
		} catch (Exception e) {

		}
		try {
			guan();
		} catch (Exception e2) {

		}
	}

	/************** ������ **********/

	public void update5(String userName, int flag) {
		getconn();
		String UserName = userName;
		String sql = "update user set hei=? where username=?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, flag);
			pr.setString(2, UserName);
			pr.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			guan();
		} catch (Exception e) {
		}
	}

	/************** ɾ��ͼ�� **********/
	public int del1(int j, String no) {
		getconn();
		String o = no;
		int i = 0;
		String sql = null;
		switch (j) {
		case 0:
			sql = "delete from tushu where name=?;";
			break;

		case 1:
			sql = "delete from tushu where id=?;";
			break;
		}
		try {
			pr = conn.prepareStatement(sql);
			pr.setString(1, o);
			i = pr.executeUpdate();
			guan();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return i;
	}

	/************** ��¼ **********/
	public int denglu(String user, String pass) {
		getconn();
		String User = user;
		String Pass = pass;
		int qx = 0;
		try {
			st = conn.createStatement();
			re = st.executeQuery("select * from User where username='" + User
					+ "'");
			if (re.next() && re.getRow() > 0) {
				if (re.getInt(4) == 1) {
					return 5;
				} else {
					if (Pass.equals(re.getString(2))) {
						qx = re.getInt("quan") + 1;
						return qx;
					} else {
						return 4;
					}
				}
			} else {
				return 3;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "���ݿ��쳣��\n" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				guan();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	/************** ע�� **********/

	public void zhuce(String user, String pass) throws SQLException {
		getconn();
		String User = user;
		String Pass = pass;
		String sql = "insert into User(username,pass) values(?,?);";

		PreparedStatement pr = conn.prepareStatement(sql);
		pr.setString(1, User);
		pr.setString(2, Pass);
		pr.executeUpdate();

		try {
			guan();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}