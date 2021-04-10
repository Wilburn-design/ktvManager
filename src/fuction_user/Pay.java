package fuction_user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import manner.Login;
import util.DBUtil;

public class Pay {
	public Pay(){
		double Total = 0;
		double restbalance = 0;
		Connection connection = new DBUtil().connection("ktv_prime");
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select Famount from Food where FuserID='"+Login.getUsername()+"' and FID=95101");
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				Total+=resultSet.getInt(1)*6.5;
			}
			preparedStatement = connection.prepareStatement("select Famount from Food where FuserID='"+Login.getUsername()+"' and FID=95102");
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				Total+=resultSet.getInt(1)*8.5;
			}
			preparedStatement = connection.prepareStatement("select Famount from Food where FuserID='"+Login.getUsername()+"' and FID=95103");
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				Total+=resultSet.getInt(1)*15.5;
			}
			preparedStatement = connection.prepareStatement("select Ctype from Customer where CuserID='"+Login.getUsername()+"'");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				if(resultSet.getString(1).equals("小型房间")) {
					Total+=99;
				}else if(resultSet.getString(1).equals("中型房间")) {
					Total+=149;
				}else {
					Total+=199;
				}
			}
			preparedStatement = connection.prepareStatement("update Orders set Omoney='"+Total+"' where OuserID='"+Login.getUsername()+"'");
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement("select Mbalance from Vip where MuserID = '"+Login.getUsername()+"'");
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				restbalance = resultSet.getFloat(1);
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if(restbalance<Total) {
			JOptionPane.showMessageDialog(null, "余额不足");
		}
		else {
		restbalance-=Total;
		PreparedStatement stm=null;
		try {
			stm = connection.prepareStatement("update Vip set Mbalance='"+restbalance+"' where MuserID='"+Login.getUsername()+"'");
			stm.executeUpdate();
			JOptionPane.showMessageDialog(null, "付款成功！");
			stm = connection.prepareStatement("delete from Food where FuserID='"+Login.getUsername()+"'");
			stm.executeUpdate();
			
			stm = connection.prepareStatement("delete from Customer where CuserID='"+Login.getUsername()+"'");
			stm.executeUpdate();
			
			stm = connection.prepareStatement("delete from deposit where DuserID='"+Login.getUsername()+"'");
			stm.executeUpdate();
			
			stm = connection.prepareStatement("delete from Orders where OuserID='"+Login.getUsername()+"'");
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		
	}
	
}
