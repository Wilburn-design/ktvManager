package fuction_user;

import java.awt.BorderLayout;

import java.awt.Container;
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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import manner.Login;
import util.DBUtil;
/*
 * 添加评论对话框
 */
@SuppressWarnings("serial")
public class CommentAdd extends JDialog implements ActionListener {
	JLabel jlCtype = new JLabel("请输入评论");//评论标签属性
	JTextArea jtaContent = new JTextArea(); //评论区文本域属性
	JButton jbSubmit = new JButton("提交");//提交按钮属性
	JButton jbCanel = new JButton("返回");//返回按钮属性
	public CommentAdd() {//构造方法设置布局和注册监听器
		Container cp = this.getContentPane();
		this.getRootPane().setDefaultButton(jbSubmit);//设置默认按钮
		cp.add(jtaContent,BorderLayout.CENTER);
		JPanel jp = new JPanel();
		jp.add(jbSubmit);
		jp.add(jbCanel);
		cp.add(jp,BorderLayout.SOUTH);
		this.setTitle("添加评论");//设置标题
		this.setSize(500, 200);//设置大小
		this.setLocationRelativeTo(null);//居中显示
		this.setResizable(false);//设置不可改变大小
		this.setModal(true);//设置模态
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//设置关闭缺省操作
	
		jbSubmit.addActionListener(this);
		jbCanel.addActionListener(this);
		
		this.setVisible(true);//设置可见性
	}
	@Override
	public void actionPerformed(ActionEvent e) {//设置监听器
		if(e.getSource() == jbSubmit) {
			if(jtaContent.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "输入评论不能为空！");
			}
			else {
				try {
					Connection con = new DBUtil().connection("ktv_prime");
					PreparedStatement stm = null;
					DateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					stm = con.prepareStatement("insert into Comment(Etime,Econtent,EuserID) values('"+date.format(new Date())+"','"+jtaContent.getText()+"','"+Login.getUsername()+"')");
					stm.executeUpdate();
					stm.close();
					con.close();
					JOptionPane.showMessageDialog(null, "添加成功");
				}catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, e2.toString(), "添加失败",JOptionPane.ERROR_MESSAGE);
				}
				this.dispose();
			}	
		}
		else
			this.dispose();	
	}
}