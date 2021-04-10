package fuction_manager;

import java.awt.Container;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import util.DBUtil;

public class NewManager extends JDialog implements ActionListener {
	/**
	 * 注册新的管理员类
	 */
	private static final long serialVersionUID = 1L;
	JLabel jlManagerID = new JLabel("注册账号");//设置标签
	JLabel jlPwd = new JLabel("权限码");
	JLabel jlNewPwd = new JLabel("密码");
	JLabel jlIdentify = new JLabel("确认密码");
	JTextField jtfManagerID = new JTextField(20);
	JTextField jpfPwd = new JTextField(20);//密码框
	JPasswordField jpfNewPwd = new JPasswordField(20);
	JPasswordField jpfIdentify = new JPasswordField(20);
	JButton jbSubmit = new JButton("提交");//按钮
	JButton jbCancel = new JButton("返回");
	public NewManager() {//构造方法设置布局及注册监听器
		Container cp = this.getContentPane();
		this.getRootPane().setDefaultButton(jbSubmit);//设置默认按钮
		cp.setLayout(new FlowLayout());
		cp.add(jlManagerID);
		cp.add(jtfManagerID);
		cp.add(jlPwd);
		cp.add(jpfPwd);
		cp.add(jlNewPwd);
		cp.add(jpfNewPwd);
		cp.add(jlIdentify);
		cp.add(jpfIdentify);
		cp.add(jbSubmit);
		cp.add(jbCancel);
		this.setTitle("注册管理员");//设置标题
		this.setSize(290, 200);//设置大小
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
			if(jtfManagerID.getText().length() == 0 || jpfPwd.getText().length() == 0 || jpfNewPwd.getText().length() == 0 || jlIdentify.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "不能输入空项");
				jtfManagerID.setText("");
				jpfPwd.setText("");
				jpfNewPwd.setText("");
				jlIdentify.setText("");
			}
			else {
					
					if(jpfPwd.getText().equals("周绍斌老师的JAVA课最好听！")) {
						if(jpfNewPwd.getText().equals(jpfIdentify.getText())) {
							try {
								Connection con = new DBUtil().connection("manager_prime");
								PreparedStatement stm = null;
								stm = con.prepareStatement("insert into manager values('"+jtfManagerID.getText()+"','"+jpfNewPwd.getText()+"')") ;
								stm.executeUpdate();
								JOptionPane.showMessageDialog(null, "添加成功！");
								//关闭数据库
								stm.close();
								con.close();
								this.dispose();
							} catch (SQLException e1) {
								e1.printStackTrace();
								JOptionPane.showMessageDialog(null, "您的输入不符合规范，请重新输入！","输入错误", JOptionPane.ERROR_MESSAGE);
								jtfManagerID.setText("");
								jpfPwd.setText("");
								jpfNewPwd.setText("");
								jpfIdentify.setText("");
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "两次密码输入不一致，请重新输入！","输入错误", JOptionPane.ERROR_MESSAGE);
							jtfManagerID.setText("");
							jpfPwd.setText("");
							jpfNewPwd.setText("");
							jpfIdentify.setText("");
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "权限码错误！","输入错误", JOptionPane.ERROR_MESSAGE);
						jtfManagerID.setText("");
						jpfPwd.setText("");
						jpfNewPwd.setText("");
						jpfIdentify.setText("");
					}
			}		
		}
		else {
			this.dispose();
		}
	}
}
