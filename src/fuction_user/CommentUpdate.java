package fuction_user;

import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import manner.Login;
import util.DBUtil;
/*
 * 修改评论对话框
 */
@SuppressWarnings("serial")
public class CommentUpdate extends JDialog implements ActionListener {
	JLabel jlCID = new JLabel("请输入评论编号");//设置标签
	JLabel jlContent = new JLabel("请修改：");
	JTextField jtCID = new JTextField(8);//文本框
	JTextArea jtaContent = new JTextArea(); //评论内容
	JButton jbSubmit = new JButton("提交");//按钮
	JButton jbCanel = new JButton("返回");
	public CommentUpdate() {//构造方法设置布局
		Container cp = this.getContentPane();
		this.getRootPane().setDefaultButton(jbSubmit);//设置默认按钮
		JPanel jp = new JPanel();
		JPanel jpCID = new JPanel();
		JPanel jpButton = new JPanel();
		jp.setLayout(new BorderLayout());
		jpCID.add(jlCID);
		jpCID.add(jtCID);
		jp.add(jpCID);
		jp.add(jlContent,BorderLayout.SOUTH);
		
		jpButton.add(jtaContent);
		jpButton.add(jbSubmit);
		jpButton.add(jbCanel);
		
		cp.add(jp,BorderLayout.NORTH);
		cp.add(jtaContent,BorderLayout.CENTER);
		cp.add(jpButton,BorderLayout.SOUTH);
		this.setTitle("用户评论修改");//设置标题
		this.setSize(500, 300);//设置大小
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
			if(jtCID.getText().length()==0||jtaContent.getText().length()==0) {
				JOptionPane.showMessageDialog(null, "不能输入空项！");
			}
		else {
			try {
				Connection con1 = new DBUtil().connection("ktv_prime");
				PreparedStatement stm1 = con1.prepareStatement("select EuserID from Comment where EID='"+jtCID.getText()+"'");
				ResultSet rs = stm1.executeQuery();
				if(rs.next()) {
					if(rs.getString(1).equals(Login.getUsername())) {
						try {
							DateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
							Connection con = new DBUtil().connection("ktv_prime");
							PreparedStatement stm = null;
							stm = con.prepareStatement("update Comment set Etime='"+date.format(new Date())+"',Econtent='"+jtaContent.getText()+"' where EuserID = '"+Login.getUsername()+"' and EID = '"+jtCID.getText()+"'");
							stm.executeUpdate();
							stm.close();
							con.close();
							JOptionPane.showMessageDialog(null, "修改成功");	
						}catch (Exception e2) {
							e2.printStackTrace();
							JOptionPane.showMessageDialog(null, e2.toString(), "修改失败",JOptionPane.ERROR_MESSAGE);
						}
						this.dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "不能修改别人的评论");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "该编号不存在");
				}
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.toString(), "修改失败",JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
	}
	else {
		this.dispose();
	}
		try {
			DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"评论编号","评论时间","评论内容","评论用户"});//将表设置为二维表
			CommentSelect.jtComment.setModel(jTable1Model);
			Connection con = new DBUtil().connection("ktv_prime");
			PreparedStatement stm = con.prepareStatement("select * from Comment");
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				int i=1;
				jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2),rs.getString(i+3)});
			}
			rs.close();
			stm.close();
			con.close();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.toString(), "查询失败",JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}			
		
	}
}	