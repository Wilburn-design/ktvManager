package fuction_manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.DBUtil;

/*
 * 数据库还原
 */
public class Restore {
	public Restore(){
		super();
		Connection con = new DBUtil().connection("ktv_prime");
		Connection con1 = new DBUtil().connection("manager_prime");
		try{
			boolean result = hfData("D:\\sql备份\\", "ktv_prime.bak", "ktv_prime", con); 
			System.out.println("result = "+result);
			boolean result1 = hfData("D:\\sql备份\\", "manager_prime.bak", "manager_prime", con1); 
			System.out.println("result1 = "+result1);
			if(result&&result1) {
				JOptionPane.showMessageDialog(null, "还原成功");
			}
			else {
				JOptionPane.showMessageDialog(null, "还原失败");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			}catch(Exception e1){
				JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			}

		//数据库备份
		
		}
	}

	public static boolean hfData(String path,String bk_name, String db_name, Connection con) {
		boolean result = false;//要返回的备份名称及位置是否正确
		try {
			PreparedStatement stm = null;
			String sql = "";
			sql = "alter database "+db_name+" set offline with rollback immediate; restore database "+db_name+" from disk='"+path+bk_name+"' with replace alter database "+db_name+" set onLine with rollback immediate;";
			stm = con.prepareStatement(sql);
			stm.executeUpdate();
			result = true;	
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		return result;
	}
}
