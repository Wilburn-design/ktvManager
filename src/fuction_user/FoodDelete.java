package fuction_user;

import java.awt.Container;
import java.awt.FlowLayout;
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
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import manner.Login;
import util.DBUtil;
/*
 * 删除点餐对话框
 */
@SuppressWarnings("serial")
public class FoodDelete extends JDialog implements ActionListener {
	JLabel jlFID = new JLabel("请输入删除编号");//标签
	JTextField jtFID = new JTextField(12);//文本框
	JButton jbSubmit = new JButton("提交");//按钮
	JButton jbCanel = new JButton("返回");
	public FoodDelete() {//构造方法设置布局及注册监听器
		Container cp = this.getContentPane();
		this.getRootPane().setDefaultButton(jbSubmit);//设置默认按钮
		cp.setLayout(new FlowLayout());
		cp.add(jlFID);
		cp.add(jtFID);
		cp.add(jbSubmit);
		cp.add(jbCanel);
		this.setTitle("删除订餐");//设置标题
		this.setSize(150, 150);//设置大小
		this.setLocationRelativeTo(null);//居中显示
		this.setResizable(false);//设置不可改变大小
		this.setModal(true);//设置模态
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//设置关闭缺省操作

		jbSubmit.addActionListener(this);
		jbCanel.addActionListener(this);
		this.setVisible(true);//设置可见性
	}
	@Override
	public void actionPerformed(ActionEvent e) {//设置监听器
		if(e.getSource() == jbSubmit) {
			if(jtFID.getText().length()==0) {
				JOptionPane.showMessageDialog(null, "不能输入空项");
			}
			else {
				try {
					Connection con1 = new DBUtil().connection("ktv_prime");
					PreparedStatement stm1 = con1.prepareStatement("select * from Food where FID='"+jtFID.getText()+"' and FuserID = '"+Login.getUsername()+"'");
					ResultSet rs = stm1.executeQuery();
					if(rs.next()) {
						try {
							Connection con = new DBUtil().connection("ktv_prime");
							PreparedStatement stm = null;
							stm = con.prepareStatement("delete from Food where FID = '"+jtFID.getText()+"'");
							stm.executeUpdate();
							stm.close();
							con.close();
							JOptionPane.showMessageDialog(null, "删除成功");
						
						}catch (Exception e2) {
							e2.printStackTrace();
							JOptionPane.showMessageDialog(null, e2.toString(), "删除失败",JOptionPane.ERROR_MESSAGE);
						}
						this.dispose();	
					}
					else {
						JOptionPane.showMessageDialog(null, "该编号不存在");
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "修改失败",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		}	
			
		else {
			this.dispose();		
		}
		try {
			DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"食品编号","生产厂商","价格","食品名称","数量"});//将表设置为二维表
			FoodSelect.jtFood.setModel(jTable1Model);
			Connection con = new DBUtil().connection("ktv_prime");
			PreparedStatement stm = con.prepareStatement("select FID,Fproduce,Fprice,Fname,Famount from Food");
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				int i=1;
				jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2),rs.getString(i+3),rs.getString(i+4)});
			}
			rs.close();
			stm.close();
			con.close();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.toString(), "查询失败",JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}			
	}
}

