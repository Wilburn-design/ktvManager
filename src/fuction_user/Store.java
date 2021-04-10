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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import util.DBUtil;

public class Store extends JDialog implements ActionListener{
	/**
	 * �鿴����Ի������
	 */
	private static final long serialVersionUID = 1L;
	static JTable jtStore = new JTable();//���ñ��
	JButton jbCancel = new JButton("����");//��ť
	public Store(){//���췽�����ò��ּ�ע�������
		Container cp = this.getContentPane();
		DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"�����","��ϵ��ʽ","��ַ","����"});//��������Ϊ��ά��
		jtStore.setModel(jTable1Model);
		JScrollPane js = new JScrollPane(jtStore);
		cp.add(js,BorderLayout.CENTER);
		JPanel jPanel = new JPanel();
		jPanel.add(jbCancel);
		cp.add(jPanel,BorderLayout.SOUTH);
		this.setTitle("�ŵ���Ϣ");//���ñ���
		this.setSize(800, 500);//���ô�С
		this.setLocationRelativeTo(null);//������ʾ
		this.setResizable(false);//���ò��ɸı��С
		this.setModal(true);//����ģ̬
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//���ùر�ȱʡ����
		try {
			Connection con = new DBUtil().connection("ktv_prime");
			PreparedStatement stm = con.prepareStatement("select * from Store");
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				int i=1;
				jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2),rs.getString(i+3)});
			}
			rs.close();
			stm.close();
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString(), "��ѯʧ��",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		jbCancel.addActionListener(this);
		this.setVisible(true);//���ÿɼ���
	}
	@Override
	public void actionPerformed(ActionEvent e) {//���ü�����
		if(e.getSource() == jbCancel) {
			this.dispose();
		}
	}
	
}