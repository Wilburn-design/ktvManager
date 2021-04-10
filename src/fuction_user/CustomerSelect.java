package fuction_user;

import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import manner.Login;
import util.DBUtil;
/*
 * 查询顾客需求对话框
 */
@SuppressWarnings("serial")
public class CustomerSelect extends JDialog implements ActionListener{
	static JTable jtCustomers = new JTable();//设置表格
	JButton jbUpdate = new JButton("修改");//按钮
	JButton jbDelete = new JButton("删除");
	JButton jbCancel = new JButton("返回");
	public CustomerSelect(){//构造方法设置布局及注册监听器
		Container cp = this.getContentPane();
		DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"需求编号","用户账号","用户需求"});//将表设置为二维表
		jtCustomers.setModel(jTable1Model);
		JScrollPane js = new JScrollPane(jtCustomers);
		cp.add(js,BorderLayout.CENTER);
		JPanel jPanel = new JPanel();
		jPanel.add(jbUpdate);
		jPanel.add(jbDelete);
		jPanel.add(jbCancel);
		cp.add(jPanel,BorderLayout.SOUTH);
		this.setTitle("用户需求查询");//设置标题
		this.setSize(300, 500);//设置大小
		this.setLocationRelativeTo(null);//居中显示
		this.setResizable(false);//设置不可改变大小
		this.setModal(true);//设置模态
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//设置关闭缺省操作
		try {
			Connection con = new DBUtil().connection("ktv_prime");
			PreparedStatement stm = con.prepareStatement("select * from Customer where CuserID='"+Login.getUsername()+"'");
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				int i=1;
				jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2)});
			}
			rs.close();
			stm.close();
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString(), "查询失败",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		jbUpdate.addActionListener(this);
		jbDelete.addActionListener(this);
		jbCancel.addActionListener(this);
		this.setVisible(true);//设置可见性
	}
	@Override
	public void actionPerformed(ActionEvent e) {//设置监听器
		if(e.getSource() == jbUpdate) {
			new CustomerUpdate();
		}
		if(e.getSource() == jbDelete) {
			new CustomerDelete();
		}
		if(e.getSource() == jbCancel) {
			this.dispose();
		}
	}
	
}
