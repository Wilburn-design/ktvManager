package manner;

import java.awt.Container;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import util.DBUtil;
import util.NumLimit;

public class Register extends JDialog implements ActionListener {
	/**
	 * 用户vip注册页面，设置登录账号和密码
	 */
	private static final long serialVersionUID = 1L;
	JTextField jtaUserName = new JTextField(20);//输入账号
	JPasswordField jpfPwd = new JPasswordField(20);//输入密码
	JPasswordField jpfIdentify = new JPasswordField(20);//确认密码
	JTextField jtbalance = new JTextField(20);//存入余额
	JLabel jlUserName= new JLabel("输入账号");//文本框和密码框标签
	JLabel jlPwd = new JLabel("输入密码");
	JLabel jlIdentify = new JLabel("确认密码");
	JLabel jlbalance = new JLabel("预存金额");
	JButton jbSubmit = new JButton("提交");//设置提交按钮
	JButton jbCancel = new JButton("返回");//设置返回登录界面按钮
	public Register(){
		Container cp = this.getContentPane();//设置面板
		cp.setLayout(new FlowLayout());//设置流式布局
		cp.add(jlUserName);//将标签和文本框加入布局中
		cp.add(jtaUserName);
		cp.add(jlPwd);
		cp.add(jpfPwd);
		cp.add(jlIdentify);
		cp.add(jpfIdentify);
		cp.add(jlbalance);
		cp.add(jtbalance);
		cp.add(jbSubmit);
		cp.add(jbCancel);

		this.setTitle("注册信息");//设置标题
		this.setSize(300, 200);//设置大小
		this.getRootPane().setDefaultButton(jbSubmit);//设置默认按钮
		this.setLocationRelativeTo(null);//居中显示
		this.setResizable(false);
		this.setModal(true);//设置模态
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//设置关闭缺省操作

		jbSubmit.addActionListener(this);//注册监听器
		jbCancel.addActionListener(this);
		jtbalance.addCaretListener(new NumLimit());//让余额只能输入数字
		this.setVisible(true);//设置可见性
	}
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jbSubmit) {//提交后把注册数据添加到对应的数据库的表中
			if(jtaUserName.getText().length() == 0||jtbalance.getText().length() == 0 || jpfIdentify.getText().length() == 0 || jpfPwd.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "不能输入空项");
			}
			else {
				Connection con1 = new DBUtil().connection("manager_prime");
				String s1 = "insert into users values('"+jtaUserName.getText()+"','"+jpfPwd.getText()+"')";
				Connection con2 = new DBUtil().connection("ktv_prime");
				DateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String s2 = "insert into VIP(Mtime,Mbalance,MuserID) values('"+date.format(new Date())+"','"+jtbalance.getText()+"','"+jtaUserName.getText()+"')";
				PreparedStatement stm = null;
				if(jpfIdentify.getText().equals(jpfPwd.getText())) {//若输入正确则添加成功
					try {
						stm = con2.prepareStatement(s2);
						stm.executeUpdate();
						stm = con1.prepareStatement(s1);
						stm.executeUpdate();
						JOptionPane.showMessageDialog(null, "注册成功！");
						stm.close();//关闭数据库
						con1.close();
						con1.close();
						this.dispose();
					} catch (Exception e1) {//当账号已被注册或者输入的值不符合规范则报出错误提示，并清空文本框和密码框
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "您的输入不符合规范，请重新输入！","输入错误", JOptionPane.ERROR_MESSAGE);
						jtaUserName.setText("");
						jpfPwd.setText("");
						jpfIdentify.setText("");
						jtbalance.setText("");
					}
				}
				else {//当两次密码输入不一样报出错误提示，并清空文本框和密码框
					JOptionPane.showMessageDialog(null, "两次密码输入不一致，请重新输入！","输入错误", JOptionPane.ERROR_MESSAGE);
					jtaUserName.setText("");
					jpfPwd.setText("");
					jpfIdentify.setText("");
					jtbalance.setText("");
				}
			}	
		}
		else {//返回后退出界面
			this.dispose();
		}
	}
}

