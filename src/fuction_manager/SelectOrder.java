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

public class SelectOrder extends JDialog implements ActionListener{
	/**
	 * 订单查询对话框
	 */
	private static final long serialVersionUID = 1L;
	static JTable jtOrder = new JTable();//设置表格
	JLabel jlOID = new JLabel("1.订单编号");
	JLabel jlOmoney = new JLabel("2.订单金额");
	JLabel jlOuserID = new JLabel("3.会员ID");
	JButton jbAdd = new JButton("增加");
	JButton jbUpdate = new JButton("修改");//按钮
	JButton jbDelete = new JButton("删除");
	JButton jbCancel = new JButton("返回");
	JTextField jtOID = new JTextField(20);
	JTextField jtOmoney = new JTextField(20);
	JTextField jtOuserID = new JTextField(20);
	
	public SelectOrder(){//构造方法设置布局及注册监听器
		Container cp = this.getContentPane();
		DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"订单编号","订单金额","会员ID"});//将表设置为二维表
		jtOrder.setModel(jTable1Model);
		JScrollPane js = new JScrollPane(jtOrder);
		cp.add(js,BorderLayout.NORTH);
		JPanel jp = new JPanel();
		jp.add(jlOID);
		jp.add(jtOID);
		jp.add(jlOmoney);
		jp.add(jtOmoney);
		jp.add(jlOuserID);
		jp.add(jtOuserID);
		cp.add(jp,BorderLayout.CENTER);
		JPanel jPanel = new JPanel();
		jPanel.add(jbAdd);
		jPanel.add(jbUpdate);
		jPanel.add(jbDelete);
		jPanel.add(jbCancel);
		cp.add(jPanel,BorderLayout.SOUTH);
		this.setTitle("需求查询(增、改输入:2,3;删输入:1)");//设置标题
		this.setSize(300, 600);//设置大小
		this.setLocationRelativeTo(null);//居中显示
		this.setResizable(false);//设置不可改变大小
		this.setModal(true);//设置模态
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//设置关闭缺省操作
		try {
			Connection con = new DBUtil().connection("ktv_prime");
			PreparedStatement stm = con.prepareStatement("select * from Orders ");
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				int i=1;
				jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2)});
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
				stm = con.prepareStatement("insert into Orders(Omoney,OuserID) values('"+jtOmoney.getText()+"','"+jtOuserID.getText()+"')");
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "添加成功");
				jtOID.setText("");
				jtOmoney.setText("");
				jtOuserID.setText("");
				DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"订单编号","订单金额","会员ID"});//将表设置为二维表
				jtOrder.setModel(jTable1Model);
				con = new DBUtil().connection("ktv_prime");
				stm = con.prepareStatement("select * from Orders ");
				rs = stm.executeQuery();
				while(rs.next()) {
					int i=1;
					jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2)});
				}
				rs.close();
				stm.close();
				con.close();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.toString(), "添加失败",JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
		if(e.getSource() == jbUpdate) {
			try {
				stm = con.prepareStatement("update Orders set Omoney='"+jtOmoney.getText()+"' where OuserID='"+jtOuserID.getText()+"'");
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "修改成功");
				jtOID.setText("");
				jtOmoney.setText("");
				jtOuserID.setText("");
				DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"订单编号","订单金额","会员ID"});//将表设置为二维表
				jtOrder.setModel(jTable1Model);
				con = new DBUtil().connection("ktv_prime");
				stm = con.prepareStatement("select * from Orders ");
				rs = stm.executeQuery();
				while(rs.next()) {
					int i=1;
					jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2)});
				}
				rs.close();
				stm.close();
				con.close();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.toString(), "添加失败",JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
		if(e.getSource() == jbDelete) {
			try {
				stm = con.prepareStatement("delete from Orders where OID ='"+jtOID.getText()+"'");
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "删除成功");
				jtOID.setText("");
				jtOmoney.setText("");
				jtOuserID.setText("");
				DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"订单编号","订单金额","会员ID"});//将表设置为二维表
				jtOrder.setModel(jTable1Model);
				con = new DBUtil().connection("ktv_prime");
				stm = con.prepareStatement("select * from Orders ");
				rs = stm.executeQuery();
				while(rs.next()) {
					int i=1;
					jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2)});
				}
				rs.close();
				stm.close();
				con.close();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.toString(), "添加失败",JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
		if(e.getSource() == jbCancel) {
			this.dispose();
		}
	}
}


