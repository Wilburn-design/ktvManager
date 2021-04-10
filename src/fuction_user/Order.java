package fuction_user;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import manner.Login;
import util.DBUtil;

@SuppressWarnings("serial")
public class Order extends JDialog implements ActionListener{
	JTable jtCustomers = new JTable();//设置表格
	JTable jtFood = new JTable(); 
	JTable jtOrder = new JTable();
	JButton jbCancel = new JButton("返回");
	double Total = 0;
	public Order(){//构造方法设置布局及注册监听器
		Container cp = this.getContentPane();
		cp.setLayout(new FlowLayout());
		DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"需求编号","用户账号","用户需求"});//将表设置为二维表
		jtCustomers.setModel(jTable1Model);
		JScrollPane js = new JScrollPane(jtCustomers);
		DefaultTableModel jTable1Model1 = new DefaultTableModel(new String[0][0] ,new String[] {"食品编号","生产厂商","价格","食品名称","数量"});//将表设置为二维表
		jtFood.setModel(jTable1Model1);
		JScrollPane js1 = new JScrollPane(jtFood);
		DefaultTableModel jTable1Model2 = new DefaultTableModel(new String[0][0] ,new String[] {"订单编号","时间","订单金额"});//将表设置为二维表
		jtOrder.setModel(jTable1Model2);
		JScrollPane js2 = new JScrollPane(jtOrder);
		cp.add(js);
		cp.add(js1);
		cp.add(js2);
		cp.add(jbCancel);
		this.setTitle("用户订单查询");//设置标题
		this.pack();
		this.setLocationRelativeTo(null);//居中显示
		this.setResizable(false);//设置不可改变大小
		this.setModal(true);//设置模态
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//设置关闭缺省操作
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
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.toString(), "查询失败",JOptionPane.ERROR_MESSAGE);
		}
		try {
			Connection con = new DBUtil().connection("ktv_prime");
			PreparedStatement stm = con.prepareStatement("select * from Customer where CuserID='"+Login.getUsername()+"'");
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				int i=1;
				jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2)});
			}
			con = new DBUtil().connection("ktv_prime");
			stm = con.prepareStatement("select FID,Fproduce,Fprice,Fname,Famount from Food where FuserID='"+Login.getUsername()+"'");
			rs = stm.executeQuery();
			while(rs.next()) {
				int i=1;
				jTable1Model1.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2),rs.getString(i+3),rs.getString(i+4)});
			}
			con = new DBUtil().connection("ktv_prime");
			stm = con.prepareStatement("select OID,Omoney from Orders where OuserID = '"+Login.getUsername()+"'");
			rs = stm.executeQuery();
			DateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			while(rs.next()) {
				int i=1;
				jTable1Model2.addRow(new String[]{rs.getString(i),date.format(new Date()) ,rs.getString(i+1)});
			}
			rs.close();
			stm.close();
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString(), "查询失败",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		jbCancel.addActionListener(this);
		this.setVisible(true);//设置可见性
	}
	@Override
	public void actionPerformed(ActionEvent e) {//设置监听器
		if(e.getSource() == jbCancel) {
			this.dispose();
		}
	}
	
}