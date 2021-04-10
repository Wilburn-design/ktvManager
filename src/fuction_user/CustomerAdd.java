package fuction_user;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import manner.Login;
import util.DBUtil;
/*
 * 添加需求类型对话框
 */
@SuppressWarnings("serial")
public class CustomerAdd extends JDialog implements ActionListener {
	JLabel jlCtype = new JLabel("请选择需求类型");//需求类型标签属性
	JComboBox<String> jcbCtype = new JComboBox<String>(); //需求类型属性
	JButton jbSubmit = new JButton("提交");//提交按钮属性
	JButton jbCanel = new JButton("返回");//返回属性
	public CustomerAdd() {
		Container cp = this.getContentPane();//获取面板
		this.getRootPane().setDefaultButton(jbSubmit);//设置默认按钮
		cp.setLayout(new FlowLayout());//设置布局
		cp.add(jlCtype);
		jcbCtype.addItem("大型房间");//添加类型
		jcbCtype.addItem("中型房间");
		jcbCtype.addItem("小型房间");
		cp.add(jcbCtype);
		JPanel jp = new JPanel();//设置新面板
		jp.add(jbSubmit);
		jp.add(jbCanel);
		cp.add(jp);
		//根据需求设置Dialog属性
		this.setTitle("添加需求");//设置标题
		this.setSize(150, 150);//设置大小
		this.setLocationRelativeTo(null);//居中显示
		this.setResizable(false);//设置不可改变大小
		this.setModal(true);//设置模态
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//设置关闭缺省操作

		//添加监听器
		jbSubmit.addActionListener(this);
		jbCanel.addActionListener(this);
		
		this.setVisible(true);//设置可见性
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//设置监听器
		if(e.getSource() == jbSubmit) {
			try {
				Connection con = new DBUtil().connection("ktv_prime");
				PreparedStatement stm = null;
				if(jcbCtype.getSelectedIndex() == 0) {
					stm = con.prepareStatement("insert into Customer(CuserID,Ctype) values('"+Login.getUsername()+"','大型房间')");
					stm.executeUpdate();
				}else if(jcbCtype.getSelectedIndex() == 1) {
					stm = con.prepareStatement("insert into Customer(CuserID,Ctype) values('"+Login.getUsername()+"','中型房间')");
					stm.executeUpdate();
				}else {
					stm = con.prepareStatement("insert into Customer(CuserID,Ctype) values('"+Login.getUsername()+"','小型房间')");
					stm.executeUpdate();
				}
				stm.close();
				con.close();
				JOptionPane.showMessageDialog(null, "添加成功");		
			}catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(null, e2.toString(), "添加失败",JOptionPane.ERROR_MESSAGE);
			}
			this.dispose();
		}
		else
			this.dispose();	

	}
}
