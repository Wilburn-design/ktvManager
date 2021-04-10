package fuction_user;

import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

import manner.Login;
import util.DBUtil;
/*
 * 充值对话框
 */
@SuppressWarnings("serial")
public class Charge extends JDialog implements ActionListener,ItemListener {
	static double balance = 0;//充值金额属性
	JLabel jlCharge = new JLabel("充值金额");//标签
	JRadioButton jrb50 = new JRadioButton("50元");//充值金额单选按钮
	JRadioButton jrb100 = new JRadioButton("100元");
	JRadioButton jrb500 = new JRadioButton("500元");
	JRadioButton jrb1000 = new JRadioButton("1000元");
	JButton jbSubmit = new JButton("提交");//按钮
	JButton jbCancel = new JButton("返回");
	public Charge(Balance owner) {//构造方法设置布局及注册监听器
		super(owner,"充值");
		Container cp = this.getContentPane();
		this.getRootPane().setDefaultButton(jbSubmit);//设置默认按钮
		JPanel jp = new JPanel();
		JPanel jp1 = new JPanel();
		ButtonGroup bg = new ButtonGroup();
		bg.add(jrb50);
		bg.add(jrb100);
		bg.add(jrb500);
		bg.add(jrb1000);
		cp.add(jlCharge,BorderLayout.NORTH);
		jp.setLayout(new FlowLayout());
		jp.add(jrb50);
		jp.add(jrb100);
		jp.add(jrb500);
		jp.add(jrb1000);
		cp.add(jp,BorderLayout.CENTER);
		jp1.setLayout(new FlowLayout());
		jp1.add(jbSubmit);
		jp1.add(jbCancel);
		cp.add(jp1,BorderLayout.SOUTH);
		this.setSize(300, 120);//设置大小
		this.setLocationRelativeTo(null);//居中显示
		this.setResizable(false);//设置不可改变大小
		this.setModal(true);//设置模态
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//设置关闭缺省操作

		jbSubmit.addActionListener(this);
		jbCancel.addActionListener(this);
		jrb50.addItemListener(this);
		jrb50.setSelected(true);
		jrb100.addItemListener(this);
		jrb500.addItemListener(this);
		jrb1000.addItemListener(this);
		this.setVisible(true);//设置可见性
		owner.dispose();
	}
	@Override
	public void actionPerformed(ActionEvent e) {//设置监听器
		if(e.getSource() == jbSubmit) {
			try {
				Balance.restBalance += Charge.balance;
				Connection con = new DBUtil().connection("ktv_prime");
				PreparedStatement stm = con.prepareStatement("select Mbalance from Vip where MuserID = '"+Login.getUsername()+"'");
				stm = con.prepareStatement("update Vip set Mbalance='"+Balance.restBalance+"' where MuserID='"+Login.getUsername()+"'");
				stm.executeLargeUpdate();
				stm.close();
				con.close();
				JOptionPane.showMessageDialog(null, "充值成功！");
				new Balance();
				this.dispose();
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.toString(),"ERROR", JOptionPane.WARNING_MESSAGE);
			}
		}
		else {
			this.dispose();
		}
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(jrb50.isSelected()) {
			balance = 50;
		}else if(jrb100.isSelected()) {
			balance = 100;
		}else if(jrb500.isSelected()) {
			balance = 500;
		}else {
			balance = 1000;
		}
	}
}
