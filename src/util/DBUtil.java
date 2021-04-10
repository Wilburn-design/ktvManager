package util;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;
/*
 * 数据库连接工具类
 */
public class DBUtil {
	String database;
	Connection con = null;//定义一个连接
	public Connection connection(String database){
		this.database = database;
		Thread thread = new Thread();
		thread.start();
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DataBaseName="+database,"sa","123");//连接数据库获取连接对象
			this.test();//测试是否连接成功
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.WARNING_MESSAGE);
		}
		return con;//返回连接对象
	}
	public void run() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//加载jdbc驱动
	}
	public void test(){//测试方法
		System.out.println("数据库连接成功！");
	}
}
