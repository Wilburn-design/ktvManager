package fuction_user;

import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import manner.Login;
import util.DBUtil;
import util.NumLimit;
/*
 * 添加点餐对话框
 */
@SuppressWarnings("serial")
public class FoodAdd extends JDialog implements ActionListener {
	JLabel jlCtype = new JLabel("请点餐");//需求类型标签属性
	JLabel jcbChip = new JLabel("薯片"); //添加复选框
	JLabel jcbBeer = new JLabel("啤酒");
	JLabel jcbTablets = new JLabel("润喉片");
	JTextField jtChip = new JTextField(8);
	JTextField jtBeer = new JTextField(8);
	JTextField jtTablets = new JTextField(8);
	JButton jbSubmit = new JButton("提交");//提交按钮属性
	JButton jbCanel = new JButton("返回");//返回属性
	public FoodAdd() {
		Container cp = this.getContentPane();//获取面板
		this.getRootPane().setDefaultButton(jbSubmit);//设置默认按钮
		cp.add(jlCtype,	BorderLayout.NORTH);
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
		
		jp1.add(jcbChip);
		jp1.add(jtChip);
		jp2.add(jcbBeer);
		jp2.add(jtBeer);
		jp3.add(jcbTablets);
		jp3.add(jtTablets);
		
		jp.add(jp1,BorderLayout.NORTH);
		jp.add(jp2,BorderLayout.CENTER);
		jp.add(jp3,BorderLayout.SOUTH);
		
		cp.add(jp,BorderLayout.CENTER);
		JPanel jpb = new JPanel();//设置新面板
		jpb.add(jbSubmit);
		jpb.add(jbCanel);
		cp.add(jpb,BorderLayout.SOUTH);
		//根据需求设置Dialog属性
		this.setTitle("添加订餐");//设置标题
		this.setSize(300, 200);//设置大小
		this.setLocationRelativeTo(null);//居中显示
		this.setResizable(false);//设置不可改变大小
		this.setModal(true);//设置模态
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//设置关闭缺省操作

		//添加监听器
		jbSubmit.addActionListener(this);
		jbCanel.addActionListener(this);
		jtBeer.addCaretListener(new NumLimit());
		jtChip.addCaretListener(new NumLimit());
		jtTablets.addCaretListener(new NumLimit());
		
		this.setVisible(true);//设置可见性
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//设置监听器
		if(e.getSource() == jbSubmit) {
			if(jtChip.getText().length() != 0) {
				Connection con = new DBUtil().connection("ktv_prime");
				PreparedStatement stm = null;
				try {
					stm = con.prepareStatement("insert into Food values(95101,'第一生产商',6.5,'薯片',"+jtChip.getText()+",'"+Login.getUsername()+"')");
					stm.executeUpdate();
					stm.close();
					con.close();
					JOptionPane.showMessageDialog(null, "添加成功");
					this.dispose();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "添加失败",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
					this.dispose();
				}
			
			}
			if(jtBeer.getText().length() != 0) {
				Connection con = new DBUtil().connection("ktv_prime");
				PreparedStatement stm=null;
				try {
					stm = con.prepareStatement("insert into Food values(95102,'第二生产商',8.5,'啤酒',"+jtBeer.getText()+",'"+Login.getUsername()+"')");
					stm.executeUpdate();
					stm.close();
					con.close();
					JOptionPane.showMessageDialog(null, "添加成功");
					this.dispose();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "添加失败",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
					this.dispose();
				}		
			}
			if(jtTablets.getText().length() != 0) {
				Connection con = new DBUtil().connection("ktv_prime");
				PreparedStatement stm;
				try {
					stm = con.prepareStatement("insert into Food values(95103,'第三生产商',15.5,'润喉片',"+jtTablets.getText()+",'"+Login.getUsername()+"')");
					stm.executeUpdate();
					stm.close();
					con.close();
					JOptionPane.showMessageDialog(null, "添加成功");
					this.dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.toString(), "添加失败",JOptionPane.ERROR_MESSAGE);
					this.dispose();
				}
			}
		}
		else
			this.dispose();	
	}
}