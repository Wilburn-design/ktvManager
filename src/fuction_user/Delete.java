package fuction_user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import manager.UserManager;
import manner.Login;
import util.DBUtil;

public class Delete {//ע���û�
	public Delete(UserManager owner) {//���췽��
		try {
			Connection con = new DBUtil().connection("ktv_prime");
			PreparedStatement stm = con.prepareStatement("delete from Vip where MuserID='"+Login.getUsername()+"'");
			stm.executeUpdate();
			
			con = new DBUtil().connection("ktv_prime");
			stm = con.prepareStatement("delete from Comment where EuserID='"+Login.getUsername()+"'");
			stm.executeUpdate();
			
			con = new DBUtil().connection("ktv_prime");
			stm = con.prepareStatement("delete from Customer where CuserID='"+Login.getUsername()+"'");
			stm.executeUpdate();
			
			con = new DBUtil().connection("ktv_prime");
			stm = con.prepareStatement("delete from Deposit where DuserID='"+Login.getUsername()+"'");
			stm.executeUpdate();
			
			con = new DBUtil().connection("ktv_prime");
			stm = con.prepareStatement("delete from Food where FuserID='"+Login.getUsername()+"'");
			stm.executeUpdate();
			
			con = new DBUtil().connection("ktv_prime");
			stm = con.prepareStatement("delete from Orders where OuserID='"+Login.getUsername()+"'");
			stm.executeUpdate();
			
			con = new DBUtil().connection("manager_prime");
			stm = con.prepareStatement("delete from users where userID='"+Login.getUsername()+"'");
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
