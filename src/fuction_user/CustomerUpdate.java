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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import manner.Login;
import util.DBUtil;
/*
 * 需求更新对话框
 */
@SuppressWarnings("serial")
public class CustomerUpdate extends JDialog implements ActionListener {
	JLabel jlCID = new JLabel("请输入需求编号");//设置标签
	JLabel jlCtype = new JLabel("请选择修改类型");
	JTextField jtCID = new JTextField(8);//文本框
	JComboBox<String> jcbCtype = new JComboBox<String>(); //需求类型
	JButton jbSubmit = new JButton("提交");//按钮
	JButton jbCanel = new JButton("返回");
	public CustomerUpdate() {//构造方法设置布局
		Container cp = this.getContentPane();
		this.getRootPane().setDefaultButton(jbSubmit);//设置默认按钮
		cp.setLayout(new FlowLayout());
		cp.add(jlCID);
		cp.add(jtCID);
		cp.add(jlCtype);
		jcbCtype.addItem("大型房间");
		jcbCtype.addItem("中型房间");
		jcbCtype.addItem("小型房间");
		cp.add(jcbCtype);
		cp.add(jbSubmit);
		cp.add(jbCanel);
		this.setTitle("用户需求修改");//设置标题
		this.setSize(240, 150);//设置大小
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
			if(jtCID.getText().length()==0) {
				JOptionPane.showMessageDialog(null, "不能输入空项！");
				jtCID.setText("");
			}
			else {
				try {
					Connection con = new DBUtil().connection("ktv_prime");
					PreparedStatement stm = null;
					if(jcbCtype.getSelectedIndex() == 0) {
						stm = con.prepareStatement("update customer set Ctype='大型房间' where CID = '"+jtCID.getText()+"'");
						stm.executeUpdate();
					}else if(jcbCtype.getSelectedIndex() == 1) {
						stm = con.prepareStatement("update customer set Ctype='中型房间' where CID = '"+jtCID.getText()+"'");
						stm.executeUpdate();
					}else {
						stm = con.prepareStatement("update customer set Ctype='小型房间' where CID = '"+jtCID.getText()+"'");
						stm.executeUpdate();
					}
					stm.close();
					con.close();
					JOptionPane.showMessageDialog(null, "修改成功");
				
				}catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, e2.toString(), "修改失败",JOptionPane.ERROR_MESSAGE);
				}
				this.dispose();
			}
		}
		else {
			this.dispose();
		}	
			
		try {
			DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"需求编号","用户账号","用户需求"});
			CustomerSelect.jtCustomers.setModel(jTable1Model);
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
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
	}
}
			

