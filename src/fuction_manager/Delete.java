package fuction_manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import manager.KTVManager;
import manner.Login;
import util.DBUtil;
/*
 * ע������Ա��
 */
public class Delete {//ע������Ա
	public Delete(KTVManager owner) {//���췽��
		try {
			Connection con = new DBUtil().connection("manager_prime");
			PreparedStatement stm = con.prepareStatement("delete from manager where managerID='"+Login.getUsername()+"'");
			stm.executeUpdate();
			JOptionPane.showMessageDialog(null, "ע���ɹ���");
			owner.dispose();//ע�����˳�����
			new Login();//�¿�����½����
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString(),"ERROR", JOptionPane.WARNING_MESSAGE);
		}
	}
}
