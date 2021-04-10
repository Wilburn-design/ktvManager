package fuction_user;

import java.awt.Container;
import java.awt.FlowLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import manner.Login;
import util.DBUtil;

@SuppressWarnings("serial")
public class Order extends JDialog implements ActionListener{
	JTable jtCustomers = new JTable();//���ñ��
	JTable jtFood = new JTable(); 
	JTable jtOrder = new JTable();
	JButton jbCancel = new JButton("����");
	double Total = 0;
	public Order(){//���췽�����ò��ּ�ע�������
		Container cp = this.getContentPane();
		cp.setLayout(new FlowLayout());
		DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"������","�û��˺�","�û�����"});//��������Ϊ��ά��
		jtCustomers.setModel(jTable1Model);
		JScrollPane js = new JScrollPane(jtCustomers);
		DefaultTableModel jTable1Model1 = new DefaultTableModel(new String[0][0] ,new String[] {"ʳƷ���","��������","�۸�","ʳƷ����","����"});//��������Ϊ��ά��
		jtFood.setModel(jTable1Model1);
		JScrollPane js1 = new JScrollPane(jtFood);
		DefaultTableModel jTable1Model2 = new DefaultTableModel(new String[0][0] ,new String[] {"�������","ʱ��","�������"});//��������Ϊ��ά��
		jtOrder.setModel(jTable1Model2);
		JScrollPane js2 = new JScrollPane(jtOrder);
		cp.add(js);
		cp.add(js1);
		cp.add(js2);
		cp.add(jbCancel);
		this.setTitle("�û�������ѯ");//���ñ���
		this.pack();
		this.setLocationRelativeTo(null);//������ʾ
		this.setResizable(false);//���ò��ɸı��С
		this.setModal(true);//����ģ̬
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//���ùر�ȱʡ����
		Connection connection = new DBUtil().connection("ktv_prime");
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select Famount from Food where FuserID='"+Login.getUsername()+"' and FID=95101");
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				Total+=resultSet.getInt(1)*6.5;
			}	
			preparedStatement = connection.prepareStatement("select Famount from Food where FuserID='"+Login.getUsername()+"' and FID=95102");
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				Total+=resultSet.getInt(1)*8.5;
			}
			preparedStatement = connection.prepareStatement("select Famount from Food where FuserID='"+Login.getUsername()+"' and FID=95103");
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				Total+=resultSet.getInt(1)*15.5;				
			}
			preparedStatement = connection.prepareStatement("select Ctype from Customer where CuserID='"+Login.getUsername()+"'");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				if(resultSet.getString(1).equals("С�ͷ���")) {
					Total+=99;
				}else if(resultSet.getString(1).equals("���ͷ���")) {
					Total+=149;
				}else {
					Total+=199;
				}
			}
			preparedStatement = connection.prepareStatement("update Orders set Omoney='"+Total+"' where OuserID='"+Login.getUsername()+"'");
			preparedStatement.executeUpdate();
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.toString(), "��ѯʧ��",JOptionPane.ERROR_MESSAGE);
		}
		try {
			Connection con = new DBUtil().connection("ktv_prime");
			PreparedStatement stm = con.prepareStatement("select * from Customer where CuserID='"+Login.getUsername()+"'");
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				int i=1;
				jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2)});
			}
			con = new DBUtil().connection("ktv_prime");
			stm = con.prepareStatement("select FID,Fproduce,Fprice,Fname,Famount from Food where FuserID='"+Login.getUsername()+"'");
			rs = stm.executeQuery();
			while(rs.next()) {
				int i=1;
				jTable1Model1.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2),rs.getString(i+3),rs.getString(i+4)});
			}
			con = new DBUtil().connection("ktv_prime");
			stm = con.prepareStatement("select OID,Omoney from Orders where OuserID = '"+Login.getUsername()+"'");
			rs = stm.executeQuery();
			DateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			while(rs.next()) {
				int i=1;
				jTable1Model2.addRow(new String[]{rs.getString(i),date.format(new Date()) ,rs.getString(i+1)});
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