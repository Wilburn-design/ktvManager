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

public class SelectFood extends JDialog implements ActionListener{
	/**
	 * ��Ͳ�ѯ�Ի���
	 */
	private static final long serialVersionUID = 1L;
	static JTable jtFood = new JTable();//���ñ��
	JLabel jlFID = new JLabel("1.ʳƷ���");
	JLabel jlFproduce = new JLabel("2.��������");
	JLabel jlFprice = new JLabel("3.�۸�	");
	JLabel jlFname = new JLabel("4.����");
	JLabel jlFamount = new JLabel("5.����");
	JLabel jlFuserID = new JLabel("6.���͹˿�");
	JButton jbAdd = new JButton("����");
	JButton jbUpdate = new JButton("�޸�");//��ť
	JButton jbDelete = new JButton("ɾ��");
	JButton jbCancel = new JButton("����");
	JTextField jtFID = new JTextField(25);
	JTextField jtFproduce = new JTextField(25);
	JTextField jtFprice = new JTextField(25);
	JTextField jtFname = new JTextField(25);
	JTextField jtFamount = new JTextField(25);
	JTextField jtFuserID = new JTextField(25);
	
	public SelectFood(){//���췽�����ò��ּ�ע�������
		Container cp = this.getContentPane();
		DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"ʳƷ���","��������","�۸�","����","����","���͹˿�"});//��������Ϊ��ά��
		jtFood.setModel(jTable1Model);
		JScrollPane js = new JScrollPane(jtFood);
		cp.add(js,BorderLayout.NORTH);
		JPanel jp = new JPanel();
		jp.add(jlFID);
		jp.add(jtFID);
		jp.add(jlFproduce);
		jp.add(jtFproduce);
		jp.add(jlFprice);
		jp.add(jtFprice);
		jp.add(jlFname);
		jp.add(jtFname);
		jp.add(jlFamount);
		jp.add(jtFamount);
		jp.add(jlFuserID);
		jp.add(jtFuserID);
		
		cp.add(jp,BorderLayout.CENTER);
		JPanel jPanel = new JPanel();
		jPanel.add(jbAdd);
		jPanel.add(jbUpdate);
		jPanel.add(jbDelete);
		jPanel.add(jbCancel);
		cp.add(jPanel,BorderLayout.SOUTH);
		this.setTitle("��Ͳ�ѯ(��:1,2,3,4,5,6;ɾ����:1;������:1,5,6)");//���ñ���
		this.setSize(1000, 600);//���ô�С
		this.setLocationRelativeTo(null);//������ʾ
		this.setResizable(false);//���ò��ɸı��С
		this.setModal(true);//����ģ̬
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//���ùر�ȱʡ����
		try {
			Connection con = new DBUtil().connection("ktv_prime");
			PreparedStatement stm = con.prepareStatement("select * from Food ");
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				int i=1;
				jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2),rs.getString(i+3),rs.getString(i+4),rs.getString(i+5)});
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
				stm = con.prepareStatement("insert into Food values('"+jtFID.getText()+"','"+jtFproduce.getText()+"','"+jtFprice.getText()+"','"+jtFname.getText()+"','"+jtFamount.getText()+"',"+jtFuserID.getText()+")");
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "��ӳɹ�");
				jtFamount.setText("");
				jtFID.setText("");
				jtFname.setText("");
				jtFprice.setText("");
				jtFproduce.setText("");
				jtFuserID.setText("");
				DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"ʳƷ���","��������","�۸�","����","����","���͹˿�"});//��������Ϊ��ά��
				jtFood.setModel(jTable1Model);
				con = new DBUtil().connection("ktv_prime");
				stm = con.prepareStatement("select * from Food ");
				rs = stm.executeQuery();
				while(rs.next()) {
					int i=1;
					jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2),rs.getString(i+3),rs.getString(i+4),rs.getString(i+5)});
				}
				rs.close();
				stm.close();
				con.close();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.toString(), "��ѯʧ��",JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
		if(e.getSource() == jbUpdate) {
			try {
				stm = con.prepareStatement("update Food set Famount='"+jtFamount.getText()+"' where FuserID='"+jtFuserID.getText()+"' and FID='"+jtFID.getText()+"'");
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
				jtFamount.setText("");
				jtFID.setText("");
				jtFname.setText("");
				jtFprice.setText("");
				jtFproduce.setText("");
				jtFuserID.setText("");
				DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"ʳƷ���","��������","�۸�","����","����","���͹˿�"});//��������Ϊ��ά��
				jtFood.setModel(jTable1Model);
				con = new DBUtil().connection("ktv_prime");
				stm = con.prepareStatement("select * from Food ");
				rs = stm.executeQuery();
				while(rs.next()) {
					int i=1;
					jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2),rs.getString(i+3),rs.getString(i+4),rs.getString(i+5)});
				}
				rs.close();
				stm.close();
				con.close();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.toString(), "��ѯʧ��",JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
		if(e.getSource() == jbDelete) {
			try {
				stm = con.prepareStatement("delete from Food where FID = '"+jtFID.getText()+"'");
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
				jtFamount.setText("");
				jtFID.setText("");
				jtFname.setText("");
				jtFprice.setText("");
				jtFproduce.setText("");
				jtFuserID.setText("");
				DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"ʳƷ���","��������","�۸�","����","����","���͹˿�"});//��������Ϊ��ά��
				jtFood.setModel(jTable1Model);
				con = new DBUtil().connection("ktv_prime");
				stm = con.prepareStatement("select * from Food ");
				rs = stm.executeQuery();
				while(rs.next()) {
					int i=1;
					jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2),rs.getString(i+3),rs.getString(i+4),rs.getString(i+5)});
				}
				rs.close();
				stm.close();
				con.close();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.toString(), "��ѯʧ��",JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
		if(e.getSource() == jbCancel) {
			this.dispose();
		}
	}
}


