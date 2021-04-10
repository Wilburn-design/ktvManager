package fuction_manager;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import util.DBUtil;

public class SelectRoom extends JDialog implements ActionListener{
	/**
	 * 房间查询对话框
	 */
	private static final long serialVersionUID = 1L;
	static JTable jtRoom = new JTable();//设置表格
	JButton jbCancel = new JButton("返回");
	
	public SelectRoom(){//构造方法设置布局及注册监听器
		Container cp = this.getContentPane();
		DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"房间编号","房间金额","房间类型"});//将表设置为二维表
		jtRoom.setModel(jTable1Model);
		JScrollPane js = new JScrollPane(jtRoom);
		cp.add(js,BorderLayout.NORTH);
		JPanel jPanel = new JPanel();
		jPanel.add(jbCancel);
		cp.add(jPanel,BorderLayout.SOUTH);
		this.setTitle("房间查询");//设置标题
		this.setSize(300, 500);//设置大小
		this.setLocationRelativeTo(null);//居中显示
		this.setResizable(false);//设置不可改变大小
		this.setModal(true);//设置模态
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//设置关闭缺省操作
		try {
			Connection con = new DBUtil().connection("ktv_prime");
			PreparedStatement stm = con.prepareStatement("select * from Room ");
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				int i=1;
				jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2)});
			}
			rs.close();
			stm.close();
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString(), "查询失败",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		jbCancel.addActionListener(this);
		this.setVisible(true);//设置可见性
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();	
	}
}