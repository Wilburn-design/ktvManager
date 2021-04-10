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

public class SelectVip extends JDialog implements ActionListener{
	/**
	 * ��Ա��ѯ
	 */
	private static final long serialVersionUID = 1L;
	static JTable jtVip = new JTable();//���ñ��
	JLabel jlMID = new JLabel("1.��Ա���");
	JLabel jlMtime = new JLabel("2.����ʱ��");
	JLabel jlMbalance = new JLabel("3.ʣ�����");
	JLabel jlMuserID = new JLabel("4.��ԱID");
	JButton jbAdd = new JButton("����");
	JButton jbUpdate = new JButton("�޸�");//��ť
	JButton jbDelete = new JButton("ɾ��");
	JButton jbCancel = new JButton("����");
	JTextField jtMID = new JTextField(21);
	JTextField jtMtime = new JTextField(21);
	JTextField jtMbalance = new JTextField(21);
	JTextField jtMuserID = new JTextField(21);
	
	public SelectVip(){//���췽�����ò��ּ�ע�������
		Container cp = this.getContentPane();
		DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"��Ա���","����ʱ��","ʣ�����","��ԱID"});//��������Ϊ��ά��
		jtVip.setModel(jTable1Model);
		JScrollPane js = new JScrollPane(jtVip);
		cp.add(js,BorderLayout.NORTH);
		JPanel jp = new JPanel();
		jp.add(jlMID);
		jp.add(jtMID);
		jp.add(jlMtime);
		jp.add(jtMtime);
		jp.add(jlMbalance);
		jp.add(jtMbalance);
		jp.add(jlMuserID);
		jp.add(jtMuserID);
		cp.add(jp,BorderLayout.CENTER);
		JPanel jPanel = new JPanel();
		jPanel.add(jbAdd);
		jPanel.add(jbUpdate);
		jPanel.add(jbDelete);
		jPanel.add(jbCancel);
		cp.add(jPanel,BorderLayout.SOUTH);
		this.setTitle("��Ա��ѯ(��:2,3,4;������:1,2,3,4;ɾ����:1)");//���ñ���
		this.setSize(600, 580);//���ô�С
		this.setLocationRelativeTo(null);//������ʾ
		this.setResizable(false);//���ò��ɸı��С
		this.setModal(true);//����ģ̬
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//���ùر�ȱʡ����
		try {
			Connection con = new DBUtil().connection("ktv_prime");
			PreparedStatement stm = con.prepareStatement("select * from Vip ");
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
				stm = con.prepareStatement("insert into Vip(Mtime,Mbalance,MuserID) values('"+jtMtime.getText()+"','"+jtMbalance.getText()+"','"+jtMuserID.getText()+"')");
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "��ӳɹ�");
				jtMID.setText("");
				jtMtime.setText("");
				jtMbalance.setText("");
				jtMuserID.setText("");
				DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"����ID","��ϵ��ʽ","�����ַ","��������"});//��������Ϊ��ά��
				jtVip.setModel(jTable1Model);
				con = new DBUtil().connection("ktv_prime");
				stm = con.prepareStatement("select * from Vip ");
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
				stm = con.prepareStatement("update Vip set Mtime='"+jtMtime.getText()+"',Mbalance='"+jtMbalance.getText()+"',MuserID='"+jtMuserID.getText()+"' where MID='"+jtMID.getText()+"'");
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
				jtMID.setText("");
				jtMtime.setText("");
				jtMbalance.setText("");
				jtMuserID.setText("");
				DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"����ID","��ϵ��ʽ","�����ַ","��������"});//��������Ϊ��ά��
				jtVip.setModel(jTable1Model);
				con = new DBUtil().connection("ktv_prime");
				stm = con.prepareStatement("select * from Vip ");
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
		if(e.getSource() == jbDelete) {
			try {
				stm = con.prepareStatement("delete from Vip where MID = '"+jtMID.getText()+"'");
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
				jtMID.setText("");
				jtMtime.setText("");
				jtMbalance.setText("");
				jtMuserID.setText("");
				DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"����ID","��ϵ��ʽ","�����ַ","��������"});//��������Ϊ��ά��
				jtVip.setModel(jTable1Model);
				con = new DBUtil().connection("ktv_prime");
				stm = con.prepareStatement("select * from Vip ");
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
		if(e.getSource() == jbCancel) {
			this.dispose();
		}
	}
}