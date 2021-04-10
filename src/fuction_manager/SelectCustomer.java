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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import util.DBUtil;

public class SelectCustomer extends JDialog implements ActionListener{
	/**
	 * �û������ѯ�Ի���
	 */
	private static final long serialVersionUID = 1L;
	static JTable jtCustomers = new JTable();//���ñ��
	JLabel jlCID = new JLabel("1.������");
	JLabel jlCuserID = new JLabel("2.��ԱID");
	JLabel jlCtype = new JLabel("3.��������");
	JButton jbAdd = new JButton("����");
	JButton jbUpdate = new JButton("�޸�");//��ť
	JButton jbDelete = new JButton("ɾ��");
	JButton jbCancel = new JButton("����");
	JTextField jtCID = new JTextField(20);
	JTextField jtCuserID = new JTextField(20);
	JTextField jtCtpye = new JTextField(20);
	
	public SelectCustomer(){//���췽�����ò��ּ�ע�������
		Container cp = this.getContentPane();
		DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"������","�û��˺�","�û�����"});//��������Ϊ��ά��
		jtCustomers.setModel(jTable1Model);
		JScrollPane js = new JScrollPane(jtCustomers);
		cp.add(js,BorderLayout.NORTH);
		JPanel jp = new JPanel();
		jp.add(jlCID);
		jp.add(jtCID);
		jp.add(jlCuserID);
		jp.add(jtCuserID);
		jp.add(jlCtype);
		jp.add(jtCtpye);
		cp.add(jp,BorderLayout.CENTER);
		JPanel jPanel = new JPanel();
		jPanel.add(jbAdd);
		jPanel.add(jbUpdate);
		jPanel.add(jbDelete);
		jPanel.add(jbCancel);
		cp.add(jPanel,BorderLayout.SOUTH);
		this.setTitle("�����ѯ(������:1,2,3;������:2,3;ɾ����:1)");//���ñ���
		this.setSize(300, 600);//���ô�С
		this.setLocationRelativeTo(null);//������ʾ
		this.setResizable(false);//���ò��ɸı��С
		this.setModal(true);//����ģ̬
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//���ùر�ȱʡ����
		try {
			Connection con = new DBUtil().connection("ktv_prime");
			PreparedStatement stm = con.prepareStatement("select * from Customer ");
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				int i=1;
				jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2)});
			}
			rs.close();
			stm.close();
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString(), "��ѯʧ��",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		jbAdd.addActionListener(this);
		jbUpdate.addActionListener(this);
		jbDelete.addActionListener(this);
		jbCancel.addActionListener(this);
		this.setVisible(true);//���ÿɼ���
	}
	@Override
	public void actionPerformed(ActionEvent e) {//���ü�����
		Connection con = new DBUtil().connection("ktv_prime");
		PreparedStatement stm = null;
		ResultSet rs = null;
		if(e.getSource() == jbAdd) {
			try {
				stm = con.prepareStatement("insert into Customer(CuserID,Ctype) values('"+jtCuserID.getText()+"','"+jtCtpye.getText()+"')");
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "��ӳɹ�");
				jtCID.setText("");
				jtCtpye.setText("");
				jtCuserID.setText("");
				DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"������","�û��˺�","�û�����"});//��������Ϊ��ά��
				jtCustomers.setModel(jTable1Model);
				con = new DBUtil().connection("ktv_prime");
				stm = con.prepareStatement("select * from Customer ");
				rs = stm.executeQuery();
				while(rs.next()) {
					int i=1;
					jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2)});
				}
				rs.close();
				stm.close();
				con.close();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.toString(), "���ʧ��",JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
		if(e.getSource() == jbUpdate) {
			try {
				stm = con.prepareStatement("update Customer set CuserID='"+jtCuserID.getText()+"',Ctype='"+jtCtpye.getText()+"' where CID='"+jtCID.getText()+"'");
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
				jtCID.setText("");
				jtCtpye.setText("");
				jtCuserID.setText("");
				DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"������","�û��˺�","�û�����"});//��������Ϊ��ά��
				jtCustomers.setModel(jTable1Model);
				con = new DBUtil().connection("ktv_prime");
				stm = con.prepareStatement("select * from Customer ");
				rs = stm.executeQuery();
				while(rs.next()) {
					int i=1;
					jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2)});
				}
				rs.close();
				stm.close();
				con.close();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.toString(), "�޸�ʧ��",JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
		if(e.getSource() == jbDelete) {
			try {
				stm = con.prepareStatement("delete from Customer where CID ='"+jtCID.getText()+"'");
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
				jtCID.setText("");
				jtCtpye.setText("");
				jtCuserID.setText("");
				DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"������","�û��˺�","�û�����"});//��������Ϊ��ά��
				jtCustomers.setModel(jTable1Model);
				con = new DBUtil().connection("ktv_prime");
				stm = con.prepareStatement("select * from Customer ");
				rs = stm.executeQuery();
				while(rs.next()) {
					int i=1;
					jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2)});
				}
				rs.close();
				stm.close();
				con.close();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.toString(), "ɾ��ʧ��",JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
		if(e.getSource() == jbCancel) {
			this.dispose();
		}
	}
}

