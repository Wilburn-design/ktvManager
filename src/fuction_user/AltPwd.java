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
import javax.swing.JPasswordField;
import javax.swing.WindowConstants;

import manner.Login;
import util.DBUtil;
/*
 * 修改密码界面对话框
 */
@SuppressWarnings("serial")
public class AltPwd extends JDialog implements ActionListener {
	JLabel jlOldPwd = new JLabel("旧密码");//设置标签
	JLabel jlNewPwd = new JLabel("新密码");
	JLabel jlIdentify = new JLabel("确认密码");
	JPasswordField jpfOldPwd = new JPasswordField(20);//密码框
	JPasswordField jpfNewPwd = new JPasswordField(20);
	JPasswordField jpfIdentify = new JPasswordField(20);
	JButton jbSubmit = new JButton("提交");//按钮
	JButton jbCancel = new JButton("返回");
	public AltPwd() {//构造方法设置布局及注册监听器
		Container cp = this.getContentPane();
		this.getRootPane().setDefaultButton(jbSubmit);//设置默认按钮
		cp.setLayout(new FlowLayout());
		cp.add(jlOldPwd);
		cp.add(jpfOldPwd);
		cp.add(jlNewPwd);
		cp.add(jpfNewPwd);
		cp.add(jlIdentify);
		cp.add(jpfIdentify);
		cp.add(jbSubmit);
		cp.add(jbCancel);
		this.setTitle("修改密码");//设置标题
		this.setSize(300, 180);//设置大小
		this.setLocationRelativeTo(null);//居中显示
		this.setResizable(false);//设置不可改变大小
		this.setModal(true);//设置模态
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//设置关闭缺省操作
		
		jbSubmit.addActionListener(this);
		jbCancel.addActionListener(this);
		this.setVisible(true);//设置可见性
	}
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {//设置监听器
		if(e.getSource() == jbSubmit) {
			if(jpfOldPwd.getText().length() == 0 ||jpfNewPwd.getText().length() == 0 || jlIdentify.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "不能输入空项");
				jpfOldPwd.setText("");
				jpfNewPwd.setText("");
				jlIdentify.setText("");
			}
			else {
				Connection con = new DBUtil().connection("manager_prime");
				PreparedStatement stm = null;
				ResultSet rs = null;
				try {
					stm = con.prepareStatement("select userPwd from users where userID='"+Login.getUsername()+"'");
					rs = stm.executeQuery();
					rs.next(); 
					if(rs.getString(1).equals(jpfOldPwd.getText())) {
						if(jpfNewPwd.getText().equals(jpfIdentify.getText())) {
							try {
								stm = con.prepareStatement("update users set userPwd='"+jpfNewPwd.getText()+"' where userID='"+Login.getUsername()+"'");
								stm.executeUpdate();
								JOptionPane.showMessageDialog(null, "修改成功！");
								this.dispose();
							} catch (SQLException e1) {
								e1.printStackTrace();
								JOptionPane.showMessageDialog(null, "您的输入不符合规范，请重新输入！","输入错误", JOptionPane.ERROR_MESSAGE);
								jpfOldPwd.setText("");
								jpfNewPwd.setText("");
								jpfIdentify.setText("");
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "两次密码输入不一致，请重新输入！","输入错误", JOptionPane.ERROR_MESSAGE);
							jpfOldPwd.setText("");
							jpfNewPwd.setText("");
							jpfIdentify.setText("");
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "原密码输入错误，请重新输入！","输入错误", JOptionPane.ERROR_MESSAGE);
						jpfOldPwd.setText("");
						jpfNewPwd.setText("");
						jpfIdentify.setText("");
					}
					rs.close();//关闭数据库
					stm.close();
					con.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}	
			}		
		}
		else {
			this.dispose();
		}
	}
}
