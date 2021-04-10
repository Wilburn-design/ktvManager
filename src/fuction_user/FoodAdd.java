package fuction_user;

import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import manner.Login;
import util.DBUtil;
import util.NumLimit;
/*
 * ��ӵ�ͶԻ���
 */
@SuppressWarnings("serial")
public class FoodAdd extends JDialog implements ActionListener {
	JLabel jlCtype = new JLabel("����");//�������ͱ�ǩ����
	JLabel jcbChip = new JLabel("��Ƭ"); //��Ӹ�ѡ��
	JLabel jcbBeer = new JLabel("ơ��");
	JLabel jcbTablets = new JLabel("���Ƭ");
	JTextField jtChip = new JTextField(8);
	JTextField jtBeer = new JTextField(8);
	JTextField jtTablets = new JTextField(8);
	JButton jbSubmit = new JButton("�ύ");//�ύ��ť����
	JButton jbCanel = new JButton("����");//��������
	public FoodAdd() {
		Container cp = this.getContentPane();//��ȡ���
		this.getRootPane().setDefaultButton(jbSubmit);//����Ĭ�ϰ�ť
		cp.add(jlCtype,	BorderLayout.NORTH);
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
		
		jp1.add(jcbChip);
		jp1.add(jtChip);
		jp2.add(jcbBeer);
		jp2.add(jtBeer);
		jp3.add(jcbTablets);
		jp3.add(jtTablets);
		
		jp.add(jp1,BorderLayout.NORTH);
		jp.add(jp2,BorderLayout.CENTER);
		jp.add(jp3,BorderLayout.SOUTH);
		
		cp.add(jp,BorderLayout.CENTER);
		JPanel jpb = new JPanel();//���������
		jpb.add(jbSubmit);
		jpb.add(jbCanel);
		cp.add(jpb,BorderLayout.SOUTH);
		//������������Dialog����
		this.setTitle("��Ӷ���");//���ñ���
		this.setSize(300, 200);//���ô�С
		this.setLocationRelativeTo(null);//������ʾ
		this.setResizable(false);//���ò��ɸı��С
		this.setModal(true);//����ģ̬
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//���ùر�ȱʡ����

		//��Ӽ�����
		jbSubmit.addActionListener(this);
		jbCanel.addActionListener(this);
		jtBeer.addCaretListener(new NumLimit());
		jtChip.addCaretListener(new NumLimit());
		jtTablets.addCaretListener(new NumLimit());
		
		this.setVisible(true);//���ÿɼ���
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//���ü�����
		if(e.getSource() == jbSubmit) {
			if(jtChip.getText().length() != 0) {
				Connection con = new DBUtil().connection("ktv_prime");
				PreparedStatement stm = null;
				try {
					stm = con.prepareStatement("insert into Food values(95101,'��һ������',6.5,'��Ƭ',"+jtChip.getText()+",'"+Login.getUsername()+"')");
					stm.executeUpdate();
					stm.close();
					con.close();
					JOptionPane.showMessageDialog(null, "��ӳɹ�");
					this.dispose();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "���ʧ��",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
					this.dispose();
				}
			
			}
			if(jtBeer.getText().length() != 0) {
				Connection con = new DBUtil().connection("ktv_prime");
				PreparedStatement stm=null;
				try {
					stm = con.prepareStatement("insert into Food values(95102,'�ڶ�������',8.5,'ơ��',"+jtBeer.getText()+",'"+Login.getUsername()+"')");
					stm.executeUpdate();
					stm.close();
					con.close();
					JOptionPane.showMessageDialog(null, "��ӳɹ�");
					this.dispose();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "���ʧ��",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
					this.dispose();
				}		
			}
			if(jtTablets.getText().length() != 0) {
				Connection con = new DBUtil().connection("ktv_prime");
				PreparedStatement stm;
				try {
					stm = con.prepareStatement("insert into Food values(95103,'����������',15.5,'���Ƭ',"+jtTablets.getText()+",'"+Login.getUsername()+"')");
					stm.executeUpdate();
					stm.close();
					con.close();
					JOptionPane.showMessageDialog(null, "��ӳɹ�");
					this.dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.toString(), "���ʧ��",JOptionPane.ERROR_MESSAGE);
					this.dispose();
				}
			}
		}
		else
			this.dispose();	
	}
}