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
	 * �û��������
	 */
	private static final long serialVersionUID = 1L;

	JMenuBar jmb = new JMenuBar();//�����˵���
	
	JMenu jmSystem = new JMenu("ϵͳ");//���ò˵�
	JMenu jmAccount = new JMenu("�˻�");
	JMenu jmOption = new JMenu("�������");
	JMenu jmComment = new JMenu("���۹���");
	JMenu jmFood = new JMenu("��͹���");
	JMenu jmSelect = new JMenu("��ѯ");
	JMenu jmPay = new JMenu("����");
	JMenuItem jmiReLog = new JMenuItem("���µ�¼");//���ò˵���
	JMenuItem jmiExit = new JMenuItem("�˳�");
	
	JMenuItem jmiBalance = new JMenuItem("���");
	JMenuItem jmiAltPwd = new JMenuItem("�޸�����");
	JMenuItem jmiDelete = new JMenuItem("ע���˻�");
	
	JMenuItem jmiCustomerAdd = new JMenuItem("�������");
	JMenuItem jmiCustomerSelect = new JMenuItem("��ѯ����");
	
	JMenuItem jmiCommentAdd = new JMenuItem("�������");
	JMenuItem jmiCommentSelect = new JMenuItem("�鿴����");
	
	JMenuItem jmiFoodAdd = new JMenuItem("��ӵ��");
	JMenuItem jmiFoodSelect = new JMenuItem("��ѯ���");
	
	JMenuItem jmiOrder = new JMenuItem("������ѯ");
	JMenuItem jmiRoom = new JMenuItem("�����ѯ");
	JMenuItem jmiDeposit = new JMenuItem("�Ĵ��ѯ");
	JMenuItem jmiStore = new JMenuItem("�ŵ��ѯ");
	
	JMenuItem jmiPay = new JMenuItem("�������");
	ImageIcon img = new ImageIcon("./img/User.jpg");//���ͼƬ
	JLabel jlimg = new JLabel(img,SwingConstants.CENTER);
	public UserManager() {//���췽�����ý��漰��Ӽ�����
		super("�û�����ϵͳ");
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
	public void actionPerformed(ActionEvent e) {//���ü�����
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
