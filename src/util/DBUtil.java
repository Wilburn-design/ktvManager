package util;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;
/*
 * ���ݿ����ӹ�����
 */
public class DBUtil {
	String database;
	Connection con = null;//����һ������
	public Connection connection(String database){
		this.database = database;
		Thread thread = new Thread();
		thread.start();
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DataBaseName="+database,"sa","123");//�������ݿ��ȡ���Ӷ���
			this.test();//�����Ƿ����ӳɹ�
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.WARNING_MESSAGE);
		}
		return con;//�������Ӷ���
	}
	public void run() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//����jdbc����
	}
	public void test(){//���Է���
		System.out.println("���ݿ����ӳɹ���");
	}
}
