package fuction_manager;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import util.DBUtil;

public class SelectFood extends JDialog implements ActionListener{
	/**
	 * 点餐查询对话框
	 */
	private static final long serialVersionUID = 1L;
	static JTable jtFood = new JTable();//设置表格
	JLabel jlFID = new JLabel("1.食品编号");
	JLabel jlFproduce = new JLabel("2.生产厂商");
	JLabel jlFprice = new JLabel("3.价格	");
	JLabel jlFname = new JLabel("4.名称");
	JLabel jlFamount = new JLabel("5.数量");
	JLabel jlFuserID = new JLabel("6.订餐顾客");
	JButton jbAdd = new JButton("增加");
	JButton jbUpdate = new JButton("修改");//按钮
	JButton jbDelete = new JButton("删除");
	JButton jbCancel = new JButton("返回");
	JTextField jtFID = new JTextField(25);
	JTextField jtFproduce = new JTextField(25);
	JTextField jtFprice = new JTextField(25);
	JTextField jtFname = new JTextField(25);
	JTextField jtFamount = new JTextField(25);
	JTextField jtFuserID = new JTextField(25);
	
	public SelectFood(){//构造方法设置布局及注册监听器
		Container cp = this.getContentPane();
		DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"食品编号","生产厂商","价格","名称","数量","订餐顾客"});//将表设置为二维表
		jtFood.setModel(jTable1Model);
		JScrollPane js = new JScrollPane(jtFood);
		cp.add(js,BorderLayout.NORTH);
		JPanel jp = new JPanel();
		jp.add(jlFID);
		jp.add(jtFID);
		jp.add(jlFproduce);
		jp.add(jtFproduce);
		jp.add(jlFprice);
		jp.add(jtFprice);
		jp.add(jlFname);
		jp.add(jtFname);
		jp.add(jlFamount);
		jp.add(jtFamount);
		jp.add(jlFuserID);
		jp.add(jtFuserID);
		
		cp.add(jp,BorderLayout.CENTER);
		JPanel jPanel = new JPanel();
		jPanel.add(jbAdd);
		jPanel.add(jbUpdate);
		jPanel.add(jbDelete);
		jPanel.add(jbCancel);
		cp.add(jPanel,BorderLayout.SOUTH);
		this.setTitle("点餐查询(增:1,2,3,4,5,6;删输入:1;改输入:1,5,6)");//设置标题
		this.setSize(1000, 600);//设置大小
		this.setLocationRelativeTo(null);//居中显示
		this.setResizable(false);//设置不可改变大小
		this.setModal(true);//设置模态
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//设置关闭缺省操作
		try {
			Connection con = new DBUtil().connection("ktv_prime");
			PreparedStatement stm = con.prepareStatement("select * from Food ");
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				int i=1;
				jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2),rs.getString(i+3),rs.getString(i+4),rs.getString(i+5)});
			}
			rs.close();
			stm.close();
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString(), "查询失败",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		jbAdd.addActionListener(this);
		jbUpdate.addActionListener(this);
		jbDelete.addActionListener(this);
		jbCancel.addActionListener(this);
		this.setVisible(true);//设置可见性
	}
	@Override
	public void actionPerformed(ActionEvent e) {//设置监听器
		Connection con = new DBUtil().connection("ktv_prime");
		PreparedStatement stm = null;
		ResultSet rs = null;
		if(e.getSource() == jbAdd) {
			try {
				stm = con.prepareStatement("insert into Food values('"+jtFID.getText()+"','"+jtFproduce.getText()+"','"+jtFprice.getText()+"','"+jtFname.getText()+"','"+jtFamount.getText()+"',"+jtFuserID.getText()+")");
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "添加成功");
				jtFamount.setText("");
				jtFID.setText("");
				jtFname.setText("");
				jtFprice.setText("");
				jtFproduce.setText("");
				jtFuserID.setText("");
				DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"食品编号","生产厂商","价格","名称","数量","订餐顾客"});//将表设置为二维表
				jtFood.setModel(jTable1Model);
				con = new DBUtil().connection("ktv_prime");
				stm = con.prepareStatement("select * from Food ");
				rs = stm.executeQuery();
				while(rs.next()) {
					int i=1;
					jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2),rs.getString(i+3),rs.getString(i+4),rs.getString(i+5)});
				}
				rs.close();
				stm.close();
				con.close();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.toString(), "查询失败",JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
		if(e.getSource() == jbUpdate) {
			try {
				stm = con.prepareStatement("update Food set Famount='"+jtFamount.getText()+"' where FuserID='"+jtFuserID.getText()+"' and FID='"+jtFID.getText()+"'");
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "修改成功");
				jtFamount.setText("");
				jtFID.setText("");
				jtFname.setText("");
				jtFprice.setText("");
				jtFproduce.setText("");
				jtFuserID.setText("");
				DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"食品编号","生产厂商","价格","名称","数量","订餐顾客"});//将表设置为二维表
				jtFood.setModel(jTable1Model);
				con = new DBUtil().connection("ktv_prime");
				stm = con.prepareStatement("select * from Food ");
				rs = stm.executeQuery();
				while(rs.next()) {
					int i=1;
					jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2),rs.getString(i+3),rs.getString(i+4),rs.getString(i+5)});
				}
				rs.close();
				stm.close();
				con.close();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.toString(), "查询失败",JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
		if(e.getSource() == jbDelete) {
			try {
				stm = con.prepareStatement("delete from Food where FID = '"+jtFID.getText()+"'");
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "删除成功");
				jtFamount.setText("");
				jtFID.setText("");
				jtFname.setText("");
				jtFprice.setText("");
				jtFproduce.setText("");
				jtFuserID.setText("");
				DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"食品编号","生产厂商","价格","名称","数量","订餐顾客"});//将表设置为二维表
				jtFood.setModel(jTable1Model);
				con = new DBUtil().connection("ktv_prime");
				stm = con.prepareStatement("select * from Food ");
				rs = stm.executeQuery();
				while(rs.next()) {
					int i=1;
					jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2),rs.getString(i+3),rs.getString(i+4),rs.getString(i+5)});
				}
				rs.close();
				stm.close();
				con.close();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.toString(), "查询失败",JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
		if(e.getSource() == jbCancel) {
			this.dispose();
		}
	}
}


