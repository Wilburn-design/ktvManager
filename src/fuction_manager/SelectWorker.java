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

public class SelectWorker extends JDialog implements ActionListener{
	/**
	 * 查询工作人员类
	 */
	private static final long serialVersionUID = 1L;
	static JTable jtVip = new JTable();//设置表格
	JLabel jlWID = new JLabel("1.员工编号");
	JLabel jlWname = new JLabel("2.员工姓名");
	JLabel jlWage = new JLabel("3.员工年龄");
	JLabel jlWsex = new JLabel("4.员工性别");
	JButton jbAdd = new JButton("增加");
	JButton jbUpdate = new JButton("修改");//按钮
	JButton jbDelete = new JButton("删除");
	JButton jbCancel = new JButton("返回");
	JTextField jtWID = new JTextField(15);
	JTextField jtWname = new JTextField(15);
	JTextField jtWage = new JTextField(15);
	JTextField jtWsex = new JTextField(15);
	
	public SelectWorker(){//构造方法设置布局及注册监听器
		Container cp = this.getContentPane();
		DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"员工编号","员工姓名","员工年龄","员工性别"});//将表设置为二维表
		jtVip.setModel(jTable1Model);
		JScrollPane js = new JScrollPane(jtVip);
		cp.add(js,BorderLayout.NORTH);
		JPanel jp = new JPanel();
		jp.add(jlWID);
		jp.add(jtWID);
		jp.add(jlWname);
		jp.add(jtWname);
		jp.add(jlWage);
		jp.add(jtWage);
		jp.add(jlWsex);
		jp.add(jtWsex);
		cp.add(jp,BorderLayout.CENTER);
		JPanel jPanel = new JPanel();
		jPanel.add(jbAdd);
		jPanel.add(jbUpdate);
		jPanel.add(jbDelete);
		jPanel.add(jbCancel);
		cp.add(jPanel,BorderLayout.SOUTH);
		this.setTitle("会员查询(增:2,3,4;改输入:1,2,3,4;删输入:1)");//设置标题
		this.setSize(480, 580);//设置大小
		this.setLocationRelativeTo(null);//居中显示
		this.setResizable(false);//设置不可改变大小
		this.setModal(true);//设置模态
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//设置关闭缺省操作
		try {
			Connection con = new DBUtil().connection("ktv_prime");
			PreparedStatement stm = con.prepareStatement("select * from Worker ");
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
				stm = con.prepareStatement("insert into Worker(Wname,Wage,Wsex) values('"+jtWname.getText()+"','"+jtWage.getText()+"','"+jtWsex.getText()+"')");
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "添加成功");
				jtWID.setText("");
				jtWname.setText("");
				jtWage.setText("");
				jtWsex.setText("");
				DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"员工编号","员工姓名","员工年龄","员工性别"});//将表设置为二维表
				jtVip.setModel(jTable1Model);
				con = new DBUtil().connection("ktv_prime");
				stm = con.prepareStatement("select * from Worker ");
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
				stm = con.prepareStatement("update Worker set Wname='"+jtWname.getText()+"',Wage='"+jtWage.getText()+"',Wsex='"+jtWsex.getText()+"' where WID='"+jtWID.getText()+"'");
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "修改成功");
				jtWID.setText("");
				jtWname.setText("");
				jtWage.setText("");
				jtWsex.setText("");
				DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"员工编号","员工姓名","员工年龄","员工性别"});//将表设置为二维表
				jtVip.setModel(jTable1Model);
				con = new DBUtil().connection("ktv_prime");
				stm = con.prepareStatement("select * from Worker ");
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
		if(e.getSource() == jbDelete) {
			try {
				stm = con.prepareStatement("delete from Worker where WID = '"+jtWID.getText()+"'");
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "删除成功");
				jtWID.setText("");
				jtWname.setText("");
				jtWage.setText("");
				jtWsex.setText("");
				DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"员工编号","员工姓名","员工年龄","员工性别"});//将表设置为二维表
				jtVip.setModel(jTable1Model);
				con = new DBUtil().connection("ktv_prime");
				stm = con.prepareStatement("select * from Worker ");
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
		if(e.getSource() == jbCancel) {
			this.dispose();
		}
	}
}
