package fuction_manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import manager.KTVManager;
import manner.Login;
import util.DBUtil;
/*
 * 注销管理员类
 */
public class Delete {//注销管理员
	public Delete(KTVManager owner) {//构造方法
		try {
			Connection con = new DBUtil().connection("manager_prime");
			PreparedStatement stm = con.prepareStatement("delete from manager where managerID='"+Login.getUsername()+"'");
			stm.executeUpdate();
			JOptionPane.showMessageDialog(null, "注销成功！");
			owner.dispose();//注销后退出界面
			new Login();//新开启登陆界面
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString(),"ERROR", JOptionPane.WARNING_MESSAGE);
		}
	}
}
