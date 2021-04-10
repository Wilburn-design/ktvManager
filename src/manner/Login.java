package manner;

import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import manager.KTVManager;
import manager.UserManager;
import util.DBUtil;
/*

 */

public class Login extends JDialog implements ActionListener {
	/**
	 *   用户登录类
	 *   分为管理员登录和用户登录
	 */
	private static final long serialVersionUID = 1L;
	private static String Username = null;//设置静态属性：账号
	private JLabel jlUsername = new JLabel("账号");//各个属性标签
	private JLabel jlPwd = new JLabel("密码");
	private JLabel jlUserType = new JLabel("用户类别      ");
	private JTextField jtUsername = new JTextField(20);//账号文本框
	private JPasswordField jtPwd = new JPasswordField(20);//密码框
	private JComboBox<String> jcbUserType = new JComboBox<String>();//用户类别
	private JButton jbOK = new JButton("登录");//设置按钮
	private JButton jbCancel = new JButton("退出");
	private JButton jbRegister = new JButton("顾客注册");
	public Login() {
		Container container = this.getContentPane();//获取布局
		this.getRootPane().setDefaultButton(jbOK);//设置默认按钮
		JPanel jpanel1 = new JPanel();//设置新的面板
		JPanel jpanel2 = new JPanel();
		JPanel jpanel3 = new JPanel();
		JPanel jpanel4 = new JPanel();
		JPanel jpanel5 = new JPanel(); 
		jpanel3.setLayout(new BorderLayout());//将面板变成边框布局
		jpanel1.add(this.jlUsername);//加入账号和密码标签和文本框
		jpanel1.add(this.jtUsername);
		jpanel2.add(this.jlPwd);
		jpanel2.add(this.jtPwd);
		
		jcbUserType.addItem("管理员");//加入用户类别
		jcbUserType.addItem("顾客");
		jcbUserType.setSelectedIndex(0);//设置初始用户类别
		jpanel4.add(this.jlUserType);
		jpanel4.add(jcbUserType);
		jpanel5.add(this.jbOK);//添加按钮
		jpanel5.add(this.jbCancel);
		jpanel5.add(this.jbRegister);
		jpanel3.add(jpanel4,BorderLayout.NORTH);//设置布局
		jpanel3.add(jpanel5,BorderLayout.SOUTH);
		container.add(jpanel1,BorderLayout.NORTH);
		container.add(jpanel2,BorderLayout.CENTER);
		container.add(jpanel3,BorderLayout.SOUTH);
		this.setTitle("ktv管理系统登陆界面");//设置标题
		this.setSize(300, 200);//设置大小
		this.setLocationRelativeTo(null);//居中显示
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//设置关闭缺省操作

		jbOK.addActionListener(this);//注册监听器
		jbCancel.addActionListener(this);
		jbRegister.addActionListener(this);
		this.setVisible(true);//设置可见性
	}
	public static String getUsername() {//获取账号方法
		return Username;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jbOK) {//提交后在数据库中查询账号密码
			Connection con = new DBUtil().connection("manager_prime");
			PreparedStatement stm = null;
			ResultSet st =null;
			if(jcbUserType.getSelectedIndex()==0) {//如果是管理员登录，跳到管理员界面
				try {
					@SuppressWarnings("deprecation")
					String s = "select * from manager where managerID='"+jtUsername.getText()+"' and managerPwd='"+jtPwd.getText()+"'";
					stm = con.prepareStatement(s);
					st = stm.executeQuery();
					if(st.next()) {
							Login.Username = jtUsername.getText();
							new KTVManager();
							this.dispose();
					}
					else {//输入错误
						JOptionPane.showMessageDialog(null, "您输入的账号或者密码不正确！","输入错误", JOptionPane.ERROR_MESSAGE);
						jtUsername.setText("");
						jtPwd.setText("");
					}
				}catch(Exception e1) {//抛出异常
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.toString(),"ERROR", JOptionPane.WARNING_MESSAGE);
					jtUsername.setText("");
					jtPwd.setText("");
				}
			}
			else {//如果是顾客登录，跳到顾客界面
				try {
					@SuppressWarnings("deprecation")
					String s = "select * from users where userID='"+jtUsername.getText()+"' and userPwd='"+jtPwd.getText()+"'";
					stm = con.prepareStatement(s);
					st = stm.executeQuery();
					if(st.next()) {
						Login.Username = jtUsername.getText();
						new UserManager();
						this.dispose();
					}
					else {//输入错误
						JOptionPane.showMessageDialog(null, "您输入的账号或者密码不正确！","输入错误", JOptionPane.ERROR_MESSAGE);
						jtUsername.setText("");
						jtPwd.setText("");
					}
					st.close();//关闭数据库
					stm.close();
					con.close();
				}catch(Exception e1) {//抛出异常
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.toString(),"ERROR", JOptionPane.WARNING_MESSAGE);
					jtUsername.setText("");
					jtPwd.setText("");
				}
			}
		}else if(e.getSource() == jbCancel) {//退出程序
			System.exit(0);
		}else {//用户注册
			new Register();
		}
	}
}
