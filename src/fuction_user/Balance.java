package fuction_user;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import manner.Login;
import util.DBUtil;
/*
 * 查看余额对话框
 */
@SuppressWarnings("serial")
public class Balance extends JDialog implements ActionListener {
	static double restBalance = 0;//剩余余额属性
	JLabel jlBalance = new JLabel("余额");//标签
	JTextField jtBalance = new JTextField(10);//文本框
	JButton jbCharge = new JButton("充值");//按钮
	JButton jbCancel = new JButton("关闭");
	public Balance() {//构造方法设置布局及注册监听器
		Container cp = this.getContentPane();
		this.getRootPane().setDefaultButton(jbCharge);//设置默认按钮
		cp.setLayout(new FlowLayout());
		cp.add(jlBalance);
		cp.add(jtBalance);
		cp.add(jbCharge);
		cp.add(jbCancel);
		jtBalance.setEditable(false);
		try {
			Connection con = new DBUtil().connection("ktv_prime");
			PreparedStatement stm = con.prepareStatement("select Mbalance from Vip where MuserID = '"+Login.getUsername()+"'");
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				restBalance = rs.getInt(1);					
			}
			this.jtBalance.setText(String.valueOf(restBalance));
			rs.close();
			stm.close();
			con.close();
			
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.toString(),"ERROR", JOptionPane.WARNING_MESSAGE);
		}
		this.setTitle("余额查询");//设置标题
		this.setSize(200, 100);//设置大小
		this.setLocationRelativeTo(null);//居中显示
		this.setModal(true);//设置模态
		this.setResizable(false);//设置不可改变大小
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//设置关闭缺省操作
		
		jbCancel.addActionListener(this);
		jbCharge.addActionListener(this);
		
		this.setVisible(true);//设置可见性	
	}
	@Override
	public void actionPerformed(ActionEvent e) {//设置监听器
		if(e.getSource() == jbCancel) {
			this.dispose();
		}
		else {
			new Charge(this);
		}
	}
}
