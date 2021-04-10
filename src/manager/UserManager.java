package manager;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import fuction_user.AltPwd;
import fuction_user.Balance;
import fuction_user.CommentAdd;
import fuction_user.CommentSelect;
import fuction_user.CustomerAdd;
import fuction_user.CustomerSelect;
import fuction_user.Delete;
import fuction_user.Deposit;
import fuction_user.FoodAdd;
import fuction_user.FoodSelect;
import fuction_user.Order;
import fuction_user.Pay;
import fuction_user.Room;
import fuction_user.Store;
import manner.Login;
import util.DBUtil;

public class UserManager extends JFrame implements ActionListener {
	/**
	 * 用户管理界面
	 */
	private static final long serialVersionUID = 1L;

	JMenuBar jmb = new JMenuBar();//建立菜单栏
	
	JMenu jmSystem = new JMenu("系统");//设置菜单
	JMenu jmAccount = new JMenu("账户");
	JMenu jmOption = new JMenu("需求管理");
	JMenu jmComment = new JMenu("评论管理");
	JMenu jmFood = new JMenu("点餐管理");
	JMenu jmSelect = new JMenu("查询");
	JMenu jmPay = new JMenu("结账");
	JMenuItem jmiReLog = new JMenuItem("重新登录");//设置菜单项
	JMenuItem jmiExit = new JMenuItem("退出");
	
	JMenuItem jmiBalance = new JMenuItem("余额");
	JMenuItem jmiAltPwd = new JMenuItem("修改密码");
	JMenuItem jmiDelete = new JMenuItem("注销账户");
	
	JMenuItem jmiCustomerAdd = new JMenuItem("添加需求");
	JMenuItem jmiCustomerSelect = new JMenuItem("查询需求");
	
	JMenuItem jmiCommentAdd = new JMenuItem("添加评论");
	JMenuItem jmiCommentSelect = new JMenuItem("查看评论");
	
	JMenuItem jmiFoodAdd = new JMenuItem("添加点餐");
	JMenuItem jmiFoodSelect = new JMenuItem("查询点餐");
	
	JMenuItem jmiOrder = new JMenuItem("订单查询");
	JMenuItem jmiRoom = new JMenuItem("房间查询");
	JMenuItem jmiDeposit = new JMenuItem("寄存查询");
	JMenuItem jmiStore = new JMenuItem("门店查询");
	
	JMenuItem jmiPay = new JMenuItem("付款结账");
	ImageIcon img = new ImageIcon("./img/User.jpg");//添加图片
	JLabel jlimg = new JLabel(img,SwingConstants.CENTER);
	public UserManager() {//构造方法设置界面及添加监听器
		super("用户管理系统");
		this.setJMenuBar(jmb);
		getContentPane().add(jlimg);
		jmb.add(jmSystem);
		jmb.add(jmAccount);
		jmb.add(jmOption);
		jmb.add(jmComment);
		jmb.add(jmFood);
		jmb.add(jmSelect);
		jmb.add(jmPay);
		
		jmSystem.add(jmiReLog);
		jmSystem.addSeparator();
		jmSystem.add(jmiExit);
		jmAccount.add(jmiBalance);
		jmAccount.add(jmiAltPwd);
		jmAccount.addSeparator();
		jmAccount.add(jmiDelete);
		jmOption.add(jmiCustomerAdd);
		jmOption.add(jmiCustomerSelect);
		jmComment.add(jmiCommentAdd);
		jmComment.add(jmiCommentSelect);
		jmFood.add(jmiFoodAdd);
		jmFood.add(jmiFoodSelect);
		jmSelect.add(jmiOrder);
		jmSelect.add(jmiRoom);
		jmSelect.add(jmiStore);
		jmSelect.add(jmiDeposit);
		jmPay.add(jmiPay);
		
		this.setSize(800,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		jmiReLog.addActionListener(this);
		jmiExit.addActionListener(this);
		jmiBalance.addActionListener(this);
		jmiAltPwd.addActionListener(this);
		jmiDelete.addActionListener(this);
		jmiCustomerAdd.addActionListener(this);
		jmiCustomerSelect.addActionListener(this);
		jmiCommentAdd.addActionListener(this);
		jmiCommentSelect.addActionListener(this);
		jmiFoodAdd.addActionListener(this);
		jmiFoodSelect.addActionListener(this);
		jmiOrder.addActionListener(this);
		jmiRoom.addActionListener(this);
		jmiDeposit.addActionListener(this);
		jmiStore.addActionListener(this);
		jmiPay.addActionListener(this);
		this.setVisible(true);
		Connection connection = new DBUtil().connection("ktv_prime");
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("insert into Orders(Omoney,OuserID) values(0,'"+Login.getUsername()+"')");
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {//设置监听器
		if(e.getSource() == jmiReLog) {
			new Login();
			this.dispose();
		}
		if(e.getSource() == jmiExit) {
			System.exit(0);
		}
		if(e.getSource() == jmiBalance) {
			new Balance();
		}
		if(e.getSource() == jmiAltPwd) {
			new AltPwd();
		}
		if(e.getSource() == jmiDelete) {
			new Delete(this);
		}
		if(e.getSource() == jmiCustomerAdd) {
			new CustomerAdd();
		}
		if(e.getSource() == jmiCustomerSelect) {
			new CustomerSelect();
		}
		if(e.getSource() == jmiCommentAdd) {
			new CommentAdd();
		}
		if(e.getSource() == jmiCommentSelect) {
			new CommentSelect();
		}
		if(e.getSource() == jmiFoodAdd) {
			new FoodAdd();
		}
		if(e.getSource() == jmiFoodSelect) {
			new FoodSelect();
		}
		if(e.getSource() == jmiOrder) {
			new Order();
		}	
		if(e.getSource() == jmiRoom) {
			new Room();
		}
		if(e.getSource() == jmiDeposit) {
			new Deposit();
		}
		if(e.getSource() == jmiStore) {
			new Store();
		}
		if(e.getSource() == jmiPay) {
			new Pay();
		}
	}
}
