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

public class SelectDeposit extends JDialog implements ActionListener{
	/**
	 * 寄存查询对话框
	 */
	private static final long serialVersionUID = 1L;
	static JTable jtDeposit = new JTable();//设置表格
	JLabel jlDID = new JLabel("1.寄存编号");
	JLabel jlDeposit = new JLabel("2.寄存柜号");
	JLabel jlDtel = new JLabel("3.寄存电话");
	JLabel jlDuserID = new JLabel("4.寄存用户");
	JButton jbAdd = new JButton("增加");
	JButton jbUpdate = new JButton("修改");//按钮
	JButton jbDelete = new JButton("删除");
	JButton jbCancel = new JButton("返回");
	JTextField jtDID = new JTextField(25);
	JTextField jtdeposit = new JTextField(25);
	JTextField jtDtel = new JTextField(25);
	JTextField jtDuserID = new JTextField(25);
	
	public SelectDeposit(){//构造方法设置布局及注册监听器
		Container cp = this.getContentPane();
		DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"寄存编号","寄存柜号","寄存电话","寄存用户"});//将表设置为二维表
		jtDeposit.setModel(jTable1Model);
		JScrollPane js = new JScrollPane(jtDeposit);
		cp.add(js,BorderLayout.NORTH);
		JPanel jp = new JPanel();
		jp.add(jlDID);
		jp.add(jtDID);
		jp.add(jlDeposit);
		jp.add(jtdeposit);
		jp.add(jlDtel);
		jp.add(jtDtel);
		jp.add(jlDuserID);
		jp.add(jtDuserID);
		cp.add(jp,BorderLayout.CENTER);
		JPanel jPanel = new JPanel();
		jPanel.add(jbAdd);
		jPanel.add(jbUpdate);
		jPanel.add(jbDelete);
		jPanel.add(jbCancel);
		cp.add(jPanel,BorderLayout.SOUTH);
		this.setTitle("寄存查询(改输入:1,2,3,4;增输入:2,3,4;删输入:1)");//设置标题
		this.setSize(400, 650);//设置大小
		this.setLocationRelativeTo(null);//居中显示
		this.setResizable(false);//设置不可改变大小
		this.setModal(true);//设置模态
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//设置关闭缺省操作
		try {
			Connection con = new DBUtil().connection("ktv_prime");
			PreparedStatement stm = con.prepareStatement("select * from Deposit ");
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				int i=1;
				jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2),rs.getString(i+3)});
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
				stm = con.prepareStatement("insert into Deposit(Deposit,DTelephone,DuserID) values('"+jtdeposit.getText()+"','"+jtDtel.getText()+"','"+jtDuserID.getText()+"')");
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "添加成功");
				jtDID.setText("");
				jtDtel.setText("");
				jtDuserID.setText("");
				jtdeposit.setText("");
				DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"寄存编号","寄存柜号","寄存电话","寄存用户"});//将表设置为二维表
				jtDeposit.setModel(jTable1Model);
				con = new DBUtil().connection("ktv_prime");
				stm = con.prepareStatement("select * from Deposit ");
				rs = stm.executeQuery();
				while(rs.next()) {
					int i=1;
					jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2),rs.getString(i+3)});
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
				stm = con.prepareStatement("update Deposit set Deposit='"+jtdeposit.getText()+"',DTelephone='"+jtDtel.getText()+"',DuserID='"+jtDuserID.getText()+"' where DID='"+jtDID.getText()+"'");
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "修改成功");
				jtDID.setText("");
				jtDtel.setText("");
				jtDuserID.setText("");
				jtdeposit.setText("");
				DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"寄存编号","寄存柜号","寄存电话","寄存用户"});//将表设置为二维表
				jtDeposit.setModel(jTable1Model);
				con = new DBUtil().connection("ktv_prime");
				stm = con.prepareStatement("select * from Deposit ");
				rs = stm.executeQuery();
				while(rs.next()) {
					int i=1;
					jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2),rs.getString(i+3)});
				}
				rs.close();
				stm.close();
				con.close();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.toString(), "修改失败",JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
		if(e.getSource() == jbDelete) {
			try {
				stm = con.prepareStatement("delete from Deposit where DID = '"+jtDID.getText()+"'");
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "删除成功");
				jtDID.setText("");
				jtDtel.setText("");
				jtDuserID.setText("");
				jtdeposit.setText("");
				DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"寄存编号","寄存柜号","寄存电话","寄存用户"});//将表设置为二维表
				jtDeposit.setModel(jTable1Model);
				con = new DBUtil().connection("ktv_prime");
				stm = con.prepareStatement("select * from Deposit ");
				rs = stm.executeQuery();
				while(rs.next()) {
					int i=1;
					jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2),rs.getString(i+3)});
				}
				rs.close();
				stm.close();
				con.close();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.toString(), "删除失败",JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
		if(e.getSource() == jbCancel) {
			this.dispose();
		}
	}
}

