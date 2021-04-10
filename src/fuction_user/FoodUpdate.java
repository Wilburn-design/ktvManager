package fuction_user;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import manner.Login;
import util.DBUtil;

@SuppressWarnings("serial")
public class FoodUpdate extends JDialog implements ActionListener {
	JLabel jlCID = new JLabel("食品编号");//设置标签
	JLabel jlAmount = new JLabel("修改数量");
	JTextField jtCID = new JTextField(8);//文本框
	JTextField jtAmount = new JTextField(8); //修改数量属性
	JButton jbSubmit = new JButton("提交");//按钮
	JButton jbCanel = new JButton("返回");
	public FoodUpdate() {//构造方法设置布局
		Container cp = this.getContentPane();
		this.getRootPane().setDefaultButton(jbSubmit);//设置默认按钮
		JPanel jp = new JPanel();
		JPanel jpCID = new JPanel();
		JPanel jpButton = new JPanel();
		jpCID.add(jlCID);
		jpCID.add(jtCID);
		jp.add(jlAmount);
		jp.add(jtAmount);
		
		jpButton.add(jbSubmit);
		jpButton.add(jbCanel);
		
		cp.add(jpCID,BorderLayout.NORTH);
		cp.add(jp,BorderLayout.CENTER);
		cp.add(jpButton,BorderLayout.SOUTH);
		this.setTitle("用户订餐修改");//设置标题
		this.setSize(200, 150);//设置大小
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
			if(jtAmount.getText().length()==0||jtCID.getText().length()==0) {
				JOptionPane.showConfirmDialog(null, "不能输入空项!");
				jtAmount.setText("");
				jtCID.setText("");
			}
			else {
				try {
						Connection con = new DBUtil().connection("ktv_prime");
						PreparedStatement stm = null;
						stm = con.prepareStatement("update Food set Famount="+jtAmount.getText()+" where FuserID = '"+Login.getUsername()+"' and FID = "+jtCID.getText()+"");
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
		}	
		else {
			this.dispose();	
		}	
		try {
			DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"食品编号","生产厂商","价格","食品名称","数量"});//将表设置为二维表
			FoodSelect.jtFood.setModel(jTable1Model);
			Connection con = new DBUtil().connection("ktv_prime");
			PreparedStatement stm = con.prepareStatement("select FID,Fproduce,Fprice,Fname,Famount from Food");
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				int i=1;
				jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2),rs.getString(i+3),rs.getString(i+4)});
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