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

public class SelectDeposit extends JDialog implements ActionListener{
	/**
	 * �Ĵ��ѯ�Ի���
	 */
	private static final long serialVersionUID = 1L;
	static JTable jtDeposit = new JTable();//���ñ��
	JLabel jlDID = new JLabel("1.�Ĵ���");
	JLabel jlDeposit = new JLabel("2.�Ĵ���");
	JLabel jlDtel = new JLabel("3.�Ĵ�绰");
	JLabel jlDuserID = new JLabel("4.�Ĵ��û�");
	JButton jbAdd = new JButton("����");
	JButton jbUpdate = new JButton("�޸�");//��ť
	JButton jbDelete = new JButton("ɾ��");
	JButton jbCancel = new JButton("����");
	JTextField jtDID = new JTextField(25);
	JTextField jtdeposit = new JTextField(25);
	JTextField jtDtel = new JTextField(25);
	JTextField jtDuserID = new JTextField(25);
	
	public SelectDeposit(){//���췽�����ò��ּ�ע�������
		Container cp = this.getContentPane();
		DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"�Ĵ���","�Ĵ���","�Ĵ�绰","�Ĵ��û�"});//��������Ϊ��ά��
		jtDeposit.setModel(jTable1Model);
		JScrollPane js = new JScrollPane(jtDeposit);
		cp.add(js,BorderLayout.NORTH);
		JPanel jp = new JPanel();
		jp.add(jlDID);
		jp.add(jtDID);
		jp.add(jlDeposit);
		jp.add(jtdeposit);
		jp.add(jlDtel);
		jp.add(jtDtel);
		jp.add(jlDuserID);
		jp.add(jtDuserID);
		cp.add(jp,BorderLayout.CENTER);
		JPanel jPanel = new JPanel();
		jPanel.add(jbAdd);
		jPanel.add(jbUpdate);
		jPanel.add(jbDelete);
		jPanel.add(jbCancel);
		cp.add(jPanel,BorderLayout.SOUTH);
		this.setTitle("�Ĵ��ѯ(������:1,2,3,4;������:2,3,4;ɾ����:1)");//���ñ���
		this.setSize(400, 650);//���ô�С
		this.setLocationRelativeTo(null);//������ʾ
		this.setResizable(false);//���ò��ɸı��С
		this.setModal(true);//����ģ̬
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//���ùر�ȱʡ����
		try {
			Connection con = new DBUtil().connection("ktv_prime");
			PreparedStatement stm = con.prepareStatement("select * from Deposit ");
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
				stm = con.prepareStatement("insert into Deposit(Deposit,DTelephone,DuserID) values('"+jtdeposit.getText()+"','"+jtDtel.getText()+"','"+jtDuserID.getText()+"')");
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "��ӳɹ�");
				jtDID.setText("");
				jtDtel.setText("");
				jtDuserID.setText("");
				jtdeposit.setText("");
				DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"�Ĵ���","�Ĵ���","�Ĵ�绰","�Ĵ��û�"});//��������Ϊ��ά��
				jtDeposit.setModel(jTable1Model);
				con = new DBUtil().connection("ktv_prime");
				stm = con.prepareStatement("select * from Deposit ");
				rs = stm.executeQuery();
				while(rs.next()) {
					int i=1;
					jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2),rs.getString(i+3)});
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
				stm = con.prepareStatement("update Deposit set Deposit='"+jtdeposit.getText()+"',DTelephone='"+jtDtel.getText()+"',DuserID='"+jtDuserID.getText()+"' where DID='"+jtDID.getText()+"'");
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
				jtDID.setText("");
				jtDtel.setText("");
				jtDuserID.setText("");
				jtdeposit.setText("");
				DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"�Ĵ���","�Ĵ���","�Ĵ�绰","�Ĵ��û�"});//��������Ϊ��ά��
				jtDeposit.setModel(jTable1Model);
				con = new DBUtil().connection("ktv_prime");
				stm = con.prepareStatement("select * from Deposit ");
				rs = stm.executeQuery();
				while(rs.next()) {
					int i=1;
					jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2),rs.getString(i+3)});
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
				stm = con.prepareStatement("delete from Deposit where DID = '"+jtDID.getText()+"'");
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
				jtDID.setText("");
				jtDtel.setText("");
				jtDuserID.setText("");
				jtdeposit.setText("");
				DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"�Ĵ���","�Ĵ���","�Ĵ�绰","�Ĵ��û�"});//��������Ϊ��ά��
				jtDeposit.setModel(jTable1Model);
				con = new DBUtil().connection("ktv_prime");
				stm = con.prepareStatement("select * from Deposit ");
				rs = stm.executeQuery();
				while(rs.next()) {
					int i=1;
					jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2),rs.getString(i+3)});
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

