package fuction_manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

import util.DBUtil;
/*
 *  数据库备份
 */
public class Backup {
	public Backup(){
		super();
		Connection con = new DBUtil().connection("ktv_prime");
		Connection con1 = new DBUtil().connection("manager_prime");
		try{
			boolean result = bkData("D:\\sql备份\\","ktv_prime",con);
			System.out.println("result = "+result);
			boolean result1 = bkData("D:\\sql备份\\","manager_prime",con1);
			System.out.println("result1 = "+result1);
			if(result&&result1) {
				JOptionPane.showMessageDialog(null, "备份成功！");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
			con.close();
			}catch(Exception e1){
				e1.printStackTrace();
			}

		//数据库备份
		
		}
	}
	public static boolean bkData(String path, String db_name, Connection conn) {
		boolean flag=false;
		@SuppressWarnings("unused")
		String bk_name = null;//要返回的的备份名称
		//与数据库进行操作
		PreparedStatement stm = null;
		String sql = "";
		try {
			String file = db_name + ".bak";
			sql = "backup database " + db_name + " to disk= '"+path+file+"' with format,name='full backup of "+db_name+"'";
			stm = conn.prepareStatement(sql);
			stm.executeUpdate();
			flag=true;//返回的文件名
		//异常
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.WARNING_MESSAGE);
		}finally {//状态集释放
			try {
				stm.close();
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		//返回
		return flag;
	}
}
