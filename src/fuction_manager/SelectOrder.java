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

public class SelectOrder extends JDialog implements ActionListener{
	/**
	 * ������ѯ�Ի���
	 */
	private static final long serialVersionUID = 1L;
	static JTable jtOrder = new JTable();//���ñ��
	JLabel jlOID = new JLabel("1.�������");
	JLabel jlOmoney = new JLabel("2.�������");
	JLabel jlOuserID = new JLabel("3.��ԱID");
	JButton jbAdd = new JButton("����");
	JButton jbUpdate = new JButton("�޸�");//��ť
	JButton jbDelete = new JButton("ɾ��");
	JButton jbCancel = new JButton("����");
	JTextField jtOID = new JTextField(20);
	JTextField jtOmoney = new JTextField(20);
	JTextField jtOuserID = new JTextField(20);
	
	public SelectOrder(){//���췽�����ò��ּ�ע�������
		Container cp = this.getContentPane();
		DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"�������","�������","��ԱID"});//��������Ϊ��ά��
		jtOrder.setModel(jTable1Model);
		JScrollPane js = new JScrollPane(jtOrder);
		cp.add(js,BorderLayout.NORTH);
		JPanel jp = new JPanel();
		jp.add(jlOID);
		jp.add(jtOID);
		jp.add(jlOmoney);
		jp.add(jtOmoney);
		jp.add(jlOuserID);
		jp.add(jtOuserID);
		cp.add(jp,BorderLayout.CENTER);
		JPanel jPanel = new JPanel();
		jPanel.add(jbAdd);
		jPanel.add(jbUpdate);
		jPanel.add(jbDelete);
		jPanel.add(jbCancel);
		cp.add(jPanel,BorderLayout.SOUTH);
		this.setTitle("�����ѯ(����������:2,3;ɾ����:1)");//���ñ���
		this.setSize(300, 600);//���ô�С
		this.setLocationRelativeTo(null);//������ʾ
		this.setResizable(false);//���ò��ɸı��С
		this.setModal(true);//����ģ̬
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//���ùر�ȱʡ����
		try {
			Connection con = new DBUtil().connection("ktv_prime");
			PreparedStatement stm = con.prepareStatement("select * from Orders ");
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
				stm = con.prepareStatement("insert into Orders(Omoney,OuserID) values('"+jtOmoney.getText()+"','"+jtOuserID.getText()+"')");
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "��ӳɹ�");
				jtOID.setText("");
				jtOmoney.setText("");
				jtOuserID.setText("");
				DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"�������","�������","��ԱID"});//��������Ϊ��ά��
				jtOrder.setModel(jTable1Model);
				con = new DBUtil().connection("ktv_prime");
				stm = con.prepareStatement("select * from Orders ");
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
				stm = con.prepareStatement("update Orders set Omoney='"+jtOmoney.getText()+"' where OuserID='"+jtOuserID.getText()+"'");
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
				jtOID.setText("");
				jtOmoney.setText("");
				jtOuserID.setText("");
				DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"�������","�������","��ԱID"});//��������Ϊ��ά��
				jtOrder.setModel(jTable1Model);
				con = new DBUtil().connection("ktv_prime");
				stm = con.prepareStatement("select * from Orders ");
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
		if(e.getSource() == jbDelete) {
			try {
				stm = con.prepareStatement("delete from Orders where OID ='"+jtOID.getText()+"'");
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
				jtOID.setText("");
				jtOmoney.setText("");
				jtOuserID.setText("");
				DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"�������","�������","��ԱID"});//��������Ϊ��ά��
				jtOrder.setModel(jTable1Model);
				con = new DBUtil().connection("ktv_prime");
				stm = con.prepareStatement("select * from Orders ");
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
		if(e.getSource() == jbCancel) {
			this.dispose();
		}
	}
}


